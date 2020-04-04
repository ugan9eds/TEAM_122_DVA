import json

from flask import Flask, render_template
import pandas as pd

app = Flask(__name__)

@app.route("/")
def index():
    return render_template('index.html')


@app.route("/getData", methods=['POST', 'GET'])
def dataStream():
    df = pd.read_csv('data/data.csv')
    chart_data = df.to_dict(orient='records')
    chart_data = json.dumps(chart_data, indent=2)
    data = {'chart_data': chart_data}

    return chart_data


if __name__ == "__main__":
    app.run(debug=True)

