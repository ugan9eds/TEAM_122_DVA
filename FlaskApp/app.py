import json

from flask import Flask, render_template, request

import numpy as np
import pandas as pd
    

from wtforms import Form, TextField, TextAreaField, validators, StringField, SubmitField

import cvxpy as cp

app = Flask(__name__)


class ReusableForm(Form):
    player_names = TextAreaField('Names:')


@app.route("/",  methods=['GET', 'POST'])
def index():
    return render_template('home.html')
	
@app.route("/player.html", methods=['GET', 'POST'])
def player():
    df = pd.read_csv('data/player_prices_w_pred.csv')
    df = df.drop(['Name + ID'], axis=1)
    
    form = ReusableForm(request.form)
    optimized_output = []
    #print(form.errors)
    if request.method == 'POST':
        player_names=request.form['Names']
        print(player_names)

        player_names_list = player_names.split(",")
        
        optimized_output = optimization_model(player_names_list)

        print(optimized_output)
    
    return render_template('player.html', column_names=df.columns.values, row_data=list(df.values.tolist()),
                           link_column="Name", zip=zip, data = optimized_output)
	
@app.route("/chart.html")
def chart():
    return render_template('chart.html')	
	
@app.route("/signup.html")
def signup():
    return render_template('signup.html')	

@app.route("/login.html")
def login():
    return render_template('login.html')	

@app.route("/confirmation.html",  methods=['GET', 'POST'])
def confirmation():
    return render_template('confirmation.html')	


@app.route("/getData", methods=['POST', 'GET'])
def dataStream():
    df = pd.read_csv('data/data.csv')
    chart_data = df.to_dict(orient='records')
    chart_data = json.dumps(chart_data, indent=2)
    data = {'chart_data': chart_data}

    return chart_data

# @app.route("/player/prices", methods=['POST', 'GET'])
# def player_prices():
#     df = pd.read_csv('data/player_prices.csv')
#     df = df.drop(['Name + ID'], axis=1)
#     chart_data = df.to_dict(orient='records')
#     chart_data = json.dumps(chart_data, indent=2)
#     data = {'chart_data': chart_data}

#     return chart_data


def optimization_model(already_selected=[]):
    data = pd.read_csv("data/player_prices_w_pred.csv")

    already_selected_data = data.loc[data['Name'].isin(already_selected)]
    
    C_selected = already_selected_data.loc[already_selected_data['Position']=='C'].count()['Position']
    D_selected = already_selected_data.loc[already_selected_data['Position']=='D'].count()['Position']
    W_selected = already_selected_data.loc[already_selected_data['Position'].isin(['LW','RW'])].count()['Position']
    G_selected = already_selected_data.loc[already_selected_data['Position']=='G'].count()['Position']
    total_selected = C_selected + W_selected + D_selected + G_selected
    selected_salary = np.sum(already_selected_data['Salary'])
    
    data = data.loc[~data['Name'].isin(already_selected)]
    
    
    salary = np.array(data['Salary'])
    projected_DFS = np.array(data['lgbm_projection'])
    center = np.array(data['Position']=='C')
    winger = np.array(np.logical_or(data['Position']=='LW',data['Position']=='RW'))
    defense = np.array(data['Position']=='D')
    goalie = np.array(data['Position']=='G')
    selection = cp.Variable(len(salary), boolean=True)
    budget = 50000-selected_salary
    max_players = 8-total_selected
    
    
    budget_constraint = salary*selection <= budget
    player_constraint = sum(selection) == max_players
    center_min = selection*center >=2-C_selected
    center_max = selection*center <=3-C_selected
    winger_min = selection*winger >=2-W_selected
    winger_max = selection*winger <=3-W_selected
    defender_min = selection*defense >=2-D_selected
    defender_max = selection*defense <=3-D_selected
    goalie_constraint = selection*goalie == 1-G_selected
    
    total_projected_value = projected_DFS * selection
    objective = cp.Problem(cp.Maximize(total_projected_value), [budget_constraint,player_constraint, center_min, center_max, winger_min, winger_max, defender_min, defender_max, goalie_constraint])
    
    objective.solve()
    
    opt_selection = selection.value >= 0.9
    player_list = data['Name'][opt_selection].append(already_selected_data['Name'])
    opt_positions = data['Position'][opt_selection].append(already_selected_data['Position'])
    opt_salary = data['Salary'][opt_selection].append(already_selected_data['Salary'])

    return player_list.tolist()

if __name__ == "__main__":
    app.run(debug=True)

