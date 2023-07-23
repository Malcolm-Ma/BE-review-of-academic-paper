# @File Init database
# @Author Mingze Ma

import requests
import json
from faker import Faker
import hashlib
import pandas as pd
import pymysql
import random
import datetime
from tqdm import tqdm

# Constants
SERVICE_URL = 'http://localhost:8080'
USER_COUNT = 400
ORG_COUNT = 20

ADMIN_EMAIL = 'admin@apex.org'
ADMIN_PASSWORD = 'admin'

# Init faker
fake = Faker('en')

sql_conn = pymysql.connect(host='127.0.0.1', port=3306, user='root', password='root', db='apex')


def set_token():
    data = {
        'email': ADMIN_EMAIL,
        'password': ADMIN_PASSWORD
    }
    req = requests.post(SERVICE_URL + '/user/login', json=data)
    result = json.loads(req.text)
    authorization = result['data']['token_head'] + result['data']['token']
    header_auth = {
        'Authorization': authorization
    }
    return header_auth


def create_user():
    first_name = fake.first_name()
    last_name = fake.last_name()
    title = fake.prefix()
    email = fake.safe_email()
    password = fake.password()
    avatar = 'https://www.gravatar.com/avatar/' + hashlib.md5(email.encode('utf-8')).hexdigest() + '?d=identicon'

    data = {
        'first_name': first_name,
        'last_name': last_name,
        'title': title,
        'email': email,
        'password': password,
        'avatar': avatar
    }

    req = requests.post(SERVICE_URL + '/user/register', json=data)
    # result = json.loads(req.text)
    # print(req.status_code)
    # print(result)

    return email, password


def create_user_batch():
    email_list = []
    password_list = []

    for i in tqdm(range(USER_COUNT)):
        cur_email, cur_password = create_user()
        email_list.append(cur_email)
        password_list.append(cur_password)
    df = pd.DataFrame({'email': email_list, 'password': password_list})
    df.to_csv('./login_info.csv', index=False, sep=',')


def create_org(header):
    org_list = []
    for i in range(ORG_COUNT):
        data = {
            "name": fake.company(),
            "email": fake.company_email(),
            "description": fake.text()
        }
        req = requests.post(SERVICE_URL + '/org/create', json=data, headers=header)
        result = json.loads(req.text)
        org_list.append(result['data']['id'])
        print(result)
    return org_list


def insert_user_to_org(org_list):
    user_id_list = []
    sql = "SELECT id from user_base WHERE id != 'admin'"
    try:
        with sql_conn.cursor() as cursor:
            cursor.execute(sql)
            select_result = cursor.fetchall()
            for i in select_result:
                user_id_list.append(i[0])
    except Exception as e:
        print(e)
    # print(user_id_list)

    for org_id in org_list:
        sample_id = random.sample(user_id_list, 70)
        for user_id in sample_id:
            date_time = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            sql = "INSERT INTO user_org_merge (org_id, user_id, type, create_time) VALUES ('{0}','{1}','{2}','{3}')" \
                .format(org_id, user_id, 1, date_time)
            with sql_conn.cursor() as cursor:
                cursor.execute(sql)
            sql_conn.commit()


def get_user_id_from_org(header, org_id):
    req = requests.get(SERVICE_URL + '/org/member_list/get?org_id=' + org_id, headers=header)
    result = json.loads(req.text)
    user_id_list = []
    for user in result['data']:
        user_id_list.append(user['id'])
    return user_id_list


def get_org_list_by_user_id(header, user_id):
    req = requests.get(SERVICE_URL + '/org/list/get?user_id=' + user_id, headers=header)
    result = json.loads(req.text)
    org_id_list = []
    for org in result['data']:
        org_id_list.append(org['id'])
    return org_id_list


def create_review_task(header, org_list):
    for org_id in tqdm(org_list):
        # get user id list
        user_id_list = get_user_id_from_org(headers, org_id)
        # generate fake data
        for user_id in user_id_list:
            for _ in range(2):
                author_list = []
                for i in range(fake.random_int(min=1, max=5)):
                    author_list.append(fake.name())
                params = {
                    "abstracts": fake.text(),
                    "authors": ",".join(author_list),
                    "contact_email": fake.safe_email(),
                    "deadline": "2022-09-02T22:53:27.379Z",
                    "keywords": ",".join(fake.words(unique=True)),
                    "org_id": org_id,
                    "published_time": "2022-08-01T00:00:27.379Z",
                    "resource_url": "https://minio.malcolmpro.com/apex/02-08-2022/fbbf07d3-1659463084353.pdf",
                    "title": fake.sentence(),
                    "user_id": user_id,
                }
                # print(params)
                req = requests.post(SERVICE_URL + '/review/submission/create', json=params, headers=header)
                result = json.loads(req.text)
                # print(result)


def bidding_interest(header):
    org_list = []
    sql = "SELECT id from org_base"
    try:
        with sql_conn.cursor() as cursor:
            cursor.execute(sql)
            select_result = cursor.fetchall()
            for i in select_result:
                org_list.append(i[0])
    except Exception as e:
        print(e)
    for org_id in tqdm(org_list):
        user_id_list = []
        sql = "SELECT user_id from user_org_merge WHERE user_id != 'admin' and org_id = '{}'".format(org_id)
        try:
            with sql_conn.cursor() as cursor:
                cursor.execute(sql)
                select_result = cursor.fetchall()
                for i in select_result:
                    user_id_list.append(i[0])
        except Exception as e:
            print(e)
        # print(user_id_list)

        submission_id_list = []
        sql = "SELECT id from submission_base WHERE org_id = '{0}'".format(org_id)
        try:
            with sql_conn.cursor() as cursor:
                cursor.execute(sql)
                select_result = cursor.fetchall()
                for i in select_result:
                    submission_id_list.append(i[0])
        except Exception as e:
            print(e)
        # print(user_id_list)

        # random bind interest
        review_demand = 3
        submission_num = len(submission_id_list)
        user_count = len(user_id_list)
        min_task = int(submission_num * review_demand / user_count)
        user_pref = {}
        for user in user_id_list:
            random_project = random.sample(submission_id_list, submission_num)  # random sort project
            # interest_count = fake.random_int(min=0, max=PROJECT_NUM / 2)
            # interest_count = MIN_TASK
            conflict_count = fake.random_int(min=0, max=int(submission_num / 10))
            interest_count = fake.random_int(min=0, max=min_task * 2)
            maybe_count = fake.random_int(min=int(submission_num / 2), max=submission_num - interest_count)
            no_count = submission_num - interest_count - maybe_count - conflict_count
            # slice random list
            interest_projects = random_project[:interest_count]
            maybe_projects = random_project[interest_count: (interest_count + maybe_count)]
            conflict_project = random_project[(interest_count + maybe_count): (interest_count + maybe_count + conflict_count)]
            no_projects = []
            if no_count != 0:
                no_projects = random_project[-no_count:]
            user_pref[user] = [interest_projects, maybe_projects, no_projects, conflict_project]

        for user_id, interest_pref in user_pref.items():
            [yes_projects, maybe_projects, no_projects, conflict_project] = interest_pref
            insert_preference(org_id, user_id, yes_projects, 3)
            insert_preference(org_id, user_id, maybe_projects, 2)
            insert_preference(org_id, user_id, no_projects, 1)
            insert_preference(org_id, user_id, conflict_project, 0)


def insert_preference(org_id, user_id, submission_list, preference):
    if preference == 2 and fake.random_int(min=0, max=9) > 3:
        return

    data = [[org_id, user_id, i, preference] for i in submission_list]
    sql = "INSERT INTO bidding_preference (org_id, user_id, submission_id, preference) values(%s, %s, %s, %s)"
    cur = sql_conn.cursor()
    cur.executemany(sql, data)
    sql_conn.commit()


if __name__ == '__main__':
    # get token
    headers = set_token()

    # create user in batch
    create_user_batch()

    # create org
    org_id_list = create_org(headers)

    insert_user_to_org(org_id_list)

    admin_org_id_list = get_org_list_by_user_id(headers, 'admin')
    create_review_task(headers, admin_org_id_list)

    bidding_interest(headers)

sql_conn.close()
