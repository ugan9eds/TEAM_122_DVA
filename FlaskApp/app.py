import json

from flask import Flask, render_template
import pandas as pd

app = Flask(__name__)

@app.route("/",  methods=['GET', 'POST'])
def index():
    return render_template('home.html')
	
@app.route("/player.html")
def player():
    df = pd.read_csv('data/player_prices.csv')
    df = df.drop(['Name + ID'], axis=1)
    
    return render_template('player.html', column_names=df.columns.values, row_data=list(df.values.tolist()),
                           link_column="Name", zip=zip)
	
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


if __name__ == "__main__":
    app.run(debug=True)

