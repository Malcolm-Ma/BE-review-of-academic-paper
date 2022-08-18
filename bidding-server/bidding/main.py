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

    return {}


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

