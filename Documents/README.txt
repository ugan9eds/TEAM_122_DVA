NHL DFS Project  - Team 122

Description: Welcome to the National Hockey League (NHL) Daily Fantasy Sports (DFS) Lineup optimizer.  This web application helps you to find the best DFS lineup on any given night to win your DFS contest.

This web application is fed by the NHL’s player/game data ingested via the NHL API.  We then apply 3 different modeling techniques to determine the expected player DFS score for that day and run it through an optimization algorithm to create the ideal lineup.  This algorithm also allows you to select players that you definitely want to include in that day’s lineup.

Last but not least, we provide visualizations and access to all the player data to allow you to make a fully informed lineup decision.  The Lineup Optimizer tab shows the performance of your optimized lineup over time and gives access to the player price and predicted DFS scores.  The game stats shows you the different variables (goals, shots, etc.) that go into the predicted DFS score for each player.

Enjoy and good luck in your contests!

Installation: 
Set working directory to project folder
Run command: pip install -r requirements.txt
     This will install all necessary packages:
To run this on a Windows computer you will also need to install Visual Studio Build Tools 2019 to install Visual C++ compilers libs and DLLs to install the cxvpy package.
Set working directory to FlaskApp folder within project folder
Run: python3 app.py
Take http address and paste into browser

Execution:
To demo this webapp, start by clicking on the “Lineup Optimizer” Tab.
Take a look at the chart at the bottom of page showing each player, their price, their opponent and their predicted score from each of the 3 models (lgbm_projection, Linear_DFS_Prediction, NN_DFS_Prediction).
Explore the search functionality and the ability to scroll between pages/set the number of rows per page.
Press the optimize button to see an optimal lineup without any players forced to be included.  You will see the names of all the selected players and a chart below showing their DFS performance over their last 5 games.  Hover over any dot in the chart to see their exact score for that game.
Try entering a name in the textbox above the optimize button to force the optimizer to include that player.  Please enter the name exactly as it appears in the prices table below.  If you want to force include multiple players, please enter the names separated by a comma with no space before or after the comma.  You will see the forced players are included in the list.  If you force too many of the same position (above the max threshold) one will be excluded automatically.

Next, press on the “Player Stats” tab.
This will show every players’ stats over the last 10 games.  It has similar functionality to the chart in the “Lineup Optimizer” tab (search, pagination).
Click on the green plus icon next to the player name, and the table will drop down to show you even more stats about that player for further investigation.

Demo Video: https://www.youtube.com/watch?v=RrgTV-sW88Y&feature=youtu.be