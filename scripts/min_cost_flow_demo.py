import random
from faker import Faker
import networkx as nx

PROJECT_NUM = 100
USER_COUNT = 50
capacity = 6  # when debugging, please focus this var
# Init faker
fake = Faker('en')

# init data
project_list = ['Project ' + str(u) for u in range(PROJECT_NUM)]
user_list = [fake.first_name() for u in range(USER_COUNT)]

project_pref = {}
user_pref = {}
for user in user_list:
    random_project = random.sample(project_list, PROJECT_NUM)  # random sort project
    interest_count = fake.random_int(min=0, max=PROJECT_NUM / 2)
    no_count = fake.random_int(min=0, max=PROJECT_NUM / 2)
    maybe_count = PROJECT_NUM - interest_count - no_count
    print('interest, maybe, no, total', interest_count, maybe_count, no_count, interest_count + maybe_count + no_count)
    # slice random list
    interest_projects = random_project[:interest_count]
    maybe_projects = random_project[interest_count: (interest_count + maybe_count)]
    no_projects = random_project[-no_count:]

    for project in interest_projects:
        project_count = project_pref.get(project, 0)
        project_pref[project] = project_count + 1
    user_pref[user] = [interest_projects, maybe_projects, no_projects]
project_pref = dict(sorted(project_pref.items(), key=lambda item: item[1], reverse=True))

print(project_list)
print(user_list)
print(user_pref)
print(project_pref)

num_persons = len(user_pref)

G = nx.DiGraph()

G.add_node('dest', demand=num_persons * capacity)

for person, pref_list in user_pref.items():
    G.add_node(person, demand=-capacity)
    for i, pref in enumerate(pref_list):
        if i == 0:
            cost = -100  # happy to assign first choices
        elif i == 1:
            cost = -60  # slightly unhappy to assign second choices
        else:
            cost = -30  # very unhappy to assign third choices
        for project in pref:
            G.add_edge(person, project, capacity=1, weight=cost)  # Edge taken if person does this project

for project in project_list:
    G.add_edge(project, 'dest', capacity=capacity, weight=0)

flow_dict = nx.min_cost_flow(G)
print(flow_dict)

result_dict = {}
for person in user_pref:
    for project, flow in flow_dict[person].items():
        if flow:
            pre_res = result_dict.get(person)
            if pre_res is None:
                pre_res = []
            pre_res.append(project)
            result_dict[person] = pre_res
            # print(person, ' joins ', project)
print(result_dict)

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
            cur_times = 0
        review_times[project] = cur_times + 1
    score_dict[person] = score
    allocate_dict[person] = allocate_score


for person, score in score_dict.items():
    print(person, ': ', score)
print(allocate_dict)
print(review_times)

print(len(review_times))
