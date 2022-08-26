from flask import Flask, request
import json

from bidding.main import bidding_by_pref
from utils import requestUtil

app = Flask(__name__)


@app.route('/')
def hello_world():  # put application's code here
    return 'Hello World!'


@app.route('/bidding', methods=["POST"])
def make_bidding():
    # Receive and process json data requests
    data = json.loads(request.data)  # Convert json string to dict
    res = bidding_by_pref(data)
    if isinstance(res, str):
        # Error in result
        return requestUtil.http_result(status=False, message=res)
    return requestUtil.http_result(data=res)


if __name__ == '__main__':
    app.run()
