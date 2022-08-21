import random
import networkx as nx


def bidding_by_pref(data):
    # Deconstruction
    project_data = data['project_id_list']
    user_data = data['user_id_list']
    user_pref = data['user_pref']
    review_demand = data['review_demand']
    min_task = data.get('min_task_per_user')
    project_size = len(project_data)
    user_size = len(user_data)
    # Check params
    min_task_limit = project_size * review_demand / user_size
    if min_task is None:
        min_task = min_task_limit
    if min_task > min_task_limit:
        return None

    # Generate perf dict in project view
    project_user_dict = {}
    for user, prefs in user_pref.items():
        [interest, maybe, no] = prefs
        deliver_project(project_user_dict, 'interest', user, interest)
        deliver_project(project_user_dict, 'maybe', user, maybe)
        deliver_project(project_user_dict, 'no', user, no)
    # Print generation result
    debug_project_user_dict(project_user_dict)

    G = nx.DiGraph()
    G.add_node('dest', demand=project_size * review_demand - user_size * min_task)

    for proj, selected_user_list in project_user_dict.items():
        G.add_node(proj, demand=-review_demand)
        for i, preferred_user_list in enumerate(selected_user_list):
            if i == 0:
                cost = -100  # happy to assign first choices
            elif i == 1:
                cost = 0  # slightly unhappy to assign second choices
            else:
                cost = 50  # very unhappy to assign third choices
            for user in preferred_user_list:
                G.add_edge(proj, user, capacity=1, weight=cost)  # Edge taken if person does this project
    for user in user_data:
        G.add_node(user, demand=min_task)
        G.add_edge(user, 'dest', weight=1)  # remove capacity here

    # Run the flow
    flow_dict = nx.min_cost_flow(G)
    # reformat the result
    flow_result_dict = {}
    for project in project_data:
        for user, flow in flow_dict[project].items():
            if flow:
                pre_res = flow_result_dict.get(user)
                if pre_res is None:
                    pre_res = []
                pre_res.append(project)
                flow_result_dict[user] = pre_res
                # print(user, 'joins', project)

    # Generate result record
    user_project_merge_list = []
    for user_id, project_id_list in flow_result_dict.items():
        for project_id in project_id_list:
            user_project_merge_list.append({
                'user_id': user_id,
                'review_id': project_id
            })

    # Init result dict
    result = {
        'result_map': flow_result_dict,
        'result_record': user_project_merge_list,
        'summary': {}
    }

    return result


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


def debug_project_user_dict(data):
    for project, pref_list in data.items():
        print()
        print('----', project, '----')
        print('--interest--')
        print(pref_list[0])
        print('--maybe--')
        print(pref_list[1])
        print('--no--')
        print(pref_list[2])

