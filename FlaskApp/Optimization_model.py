#!/usr/bin/env python
# coding: utf-8

# In[1]:

def optimization_model(data,already_selected=[]):
    import cvxpy as cp
    import numpy as np
    import pandas as pd
    
    
    # In[2]:
    
    # In[14]:
    
    
    #Test of forcing player selection    
    already_selected_data = data.loc[data['Name'].isin(already_selected)]
    
    C_selected = already_selected_data.loc[already_selected_data['Position']=='C'].count()['Position']
    D_selected = already_selected_data.loc[already_selected_data['Position']=='D'].count()['Position']
    W_selected = already_selected_data.loc[already_selected_data['Position'].isin(['LW','RW'])].count()['Position']
    G_selected = already_selected_data.loc[already_selected_data['Position']=='G'].count()['Position']
    total_selected = C_selected + W_selected + D_selected + G_selected
    selected_salary = np.sum(already_selected_data['Salary'])
    
    data = data.loc[~data['Name'].isin(already_selected)]
    
    
    # Create Variable arrays for model
    
    # In[15]:
    
    
    salary = np.array(data['Salary'])
    projected_DFS = np.array(data['lgbm_projection'])
    center = np.array(data['Position']=='C')
    winger = np.array(np.logical_or(data['Position']=='LW',data['Position']=='RW'))
    defense = np.array(data['Position']=='D')
    goalie = np.array(data['Position']=='G')
    selection = cp.Variable(len(salary), boolean=True)
    budget = 50000-selected_salary
    max_players = 8-total_selected
    
    
    # Create Constraints
    
    # In[16]:
    
    
    budget_constraint = salary*selection <= budget
    player_constraint = sum(selection) == max_players
    center_min = selection*center >=2-C_selected
    center_max = selection*center <=3-C_selected
    winger_min = selection*winger >=2-W_selected
    winger_max = selection*winger <=3-W_selected
    defender_min = selection*defense >=2-D_selected
    defender_max = selection*defense <=3-D_selected
    goalie_constraint = selection*goalie == 1-G_selected
    
    
    # Objective Function
    
    # In[17]:
    
    
    total_projected_value = projected_DFS * selection
    objective = cp.Problem(cp.Maximize(total_projected_value), [budget_constraint,player_constraint, center_min, center_max, winger_min, winger_max, defender_min, defender_max, goalie_constraint])
    
    
    # In[18]:
    
    
    objective.solve()
    
    
    # Create Optimal Player List
    
    # In[19]:
    
    
    opt_selection = selection.value >= 0.9
    player_list = data['Name'][opt_selection].append(already_selected_data['Name'])
    opt_positions = data['Position'][opt_selection].append(already_selected_data['Position'])
    opt_salary = data['Salary'][opt_selection].append(already_selected_data['Salary'])
    return player_list.tolist()
    #print(player_list)
    #print(opt_positions)
    #print(np.sum(opt_salary))

