import requests
import json
from faker import Faker
import hashlib
import pandas as pd

# Constants
SERVICE_URL = 'http://localhost:8080'
USER_COUNT = 50

# Init faker
fake = Faker('en')


def create_user():
    first_name = fake.first_name()
    last_name = fake.last_name()
    title = fake.prefix()
    email = fake.safe_email()
    password = fake.password()
    avatar = 'https://www.gravatar.com/avatar/' + hashlib.md5(email.encode('utf-8')).hexdigest() + '?d=retro'

    data = {
        'first_name': first_name,
        'last_name': last_name,
        'title': title,
        'email': email,
        'password': password,
        'avatar': avatar
    }

    req = requests.post(SERVICE_URL + '/user/register', json=data)
    result = json.loads(req.text)

    print(req.status_code)
    print(result)

    return email, password


if __name__ == '__main__':
    email = []
    password = []

    for i in range(USER_COUNT):
        cur_email, cur_password = create_user()
        email.append(cur_email)
        password.append(cur_password)
    df = pd.DataFrame({'email': email, 'password': password})
    df.to_csv('./login_info.csv', index=False, sep=',')
