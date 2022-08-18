import json
import random
from faker import Faker
import networkx as nx

'''
We need guarantee:
the result of PROJECT_NUM * REVIEW_DEMAND
is similar to
the result of USER_COUNT * MIN_TASK

so as to make sure algorithm allocate an appropriate number of projects to each user
'''
PROJECT_NUM = 142
REVIEW_DEMAND = 3
USER_COUNT = 70
MIN_TASK = int(PROJECT_NUM * REVIEW_DEMAND / USER_COUNT)


def deliver_project(result_map=None, pref_type='maybe', user_name='', proj_list=None):
    if result_map is None:
        result_map = {}
    if proj_list is None:
        proj_list = []
    index_map = {
        'interest': 0,
        'maybe': 1,
        'no': 2
    }
    for item in proj_list:
        cur_obj = result_map.get(item)
        if cur_obj is None:
            cur_obj = [[], [], []]
        prev_list = cur_obj[index_map[pref_type]]
        prev_list.append(user_name)
        for i_obj, detail in enumerate(cur_obj):
            random.shuffle(detail)
            cur_obj[i_obj] = detail

        result_map[item] = cur_obj


# Init faker
fake = Faker('en')

# init data
project_list = ['Project-' + str(u) for u in range(PROJECT_NUM)]
user_list = [fake.name() for u in range(USER_COUNT)]

project_pref = {}
user_pref = {}
for user in user_list:
    random_project = random.sample(project_list, PROJECT_NUM)  # random sort project
    # interest_count = fake.random_int(min=0, max=PROJECT_NUM / 2)
    # interest_count = MIN_TASK
    interest_count = fake.random_int(min=0, max=MIN_TASK)
    maybe_count = fake.random_int(min=int(PROJECT_NUM / 2), max=PROJECT_NUM - interest_count)
    no_count = PROJECT_NUM - interest_count - maybe_count
    # print('interest, maybe, no, total', interest_count, maybe_count, no_count, interest_count + maybe_count + no_count)
    # slice random list
    interest_projects = random_project[:interest_count]
    maybe_projects = random_project[interest_count: (interest_count + maybe_count)]
    if no_count != 0:
        no_projects = random_project[-no_count:]
    else:
        no_projects = []

    for project in interest_projects:
        project_count = project_pref.get(project, 0)
        project_pref[project] = project_count + 1
    user_pref[user] = [interest_projects, maybe_projects, no_projects]
project_pref = dict(sorted(project_pref.items(), key=lambda item: item[1], reverse=True))

# print(json.dumps(project_list))
print(json.dumps(user_list))
print()
print(json.dumps(user_pref))
# print(project_pref)

project_user_dict = {}
for user, prefs in user_pref.items():
    [interest, maybe, no] = prefs
    deliver_project(project_user_dict, 'interest', user, interest)
    deliver_project(project_user_dict, 'maybe', user, maybe)
    deliver_project(project_user_dict, 'no', user, no)
print('\n\n', project_user_dict)

G = nx.DiGraph()

# G.add_node('dest', demand=PROJECT_NUM * capacity - USER_COUNT * REVIEW_DEMAND)
G.add_node('dest', demand=PROJECT_NUM * REVIEW_DEMAND - USER_COUNT * MIN_TASK)

for proj, selected_user_list in project_user_dict.items():
    G.add_node(proj, demand=-REVIEW_DEMAND)
    for i, preferred_user_list in enumerate(selected_user_list):
        if i == 0:
            cost = -100  # happy to assign first choices
        elif i == 1:
            cost = 0  # slightly unhappy to assign second choices
        else:
            cost = 50  # very unhappy to assign third choices
        for user in preferred_user_list:
            G.add_edge(proj, user, capacity=1, weight=cost)  # Edge taken if person does this project

for user in user_list:
    G.add_node(user, demand=MIN_TASK)
    G.add_edge(user, 'dest', weight=1)  # remove capacity here

flow_dict = nx.min_cost_flow(G)
# print(flow_dict)

result_dict = {}
for project in project_list:
    for user, flow in flow_dict[project].items():
        if flow:
            pre_res = result_dict.get(user)
            if pre_res is None:
                pre_res = []
            pre_res.append(project)
            result_dict[user] = pre_res
            # print(user, 'joins', project)
# print(result_dict)

# validate
score_dict = {}
allocate_dict = {}
review_times = {}
for person, projects in result_dict.items():
    cur_pref = user_pref[person]
    score = 0
    allocate_score = [0, 0, 0]
    for project in projects:
        if project in cur_pref[0]:
            score += 10
            allocate_score[0] += 1
        if project in cur_pref[1]:
            score += 5
            allocate_score[1] += 1
        if project in cur_pref[2]:
            score += 1
            allocate_score[2] += 1
        cur_times = review_times.get(project)
        if cur_times is None:
            cur_times = []
        cur_times.append(person)
        review_times[project] = cur_times
    score_dict[person] = score
    allocate_dict[person] = allocate_score

print('\n\nScore of allocation:')
for person, score in score_dict.items():
    print(person, ':', score)
print(allocate_dict)

# print('\nNumber of times the project was reviewed:')
# print('project, number of times')
# review_per_user = 0
# for project, users in review_times.items():
#     review_per_user += len(users)
#     print(project, '\t,\t', len(users))
# review_per_user = review_per_user / len(review_times)
#
# print('\nreview_per_user:', review_per_user)
print('Total review times:', len(review_times))
