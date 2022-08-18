"""
@file request utils
@author Mingze Ma
"""


def http_result(status=True, data=None, message='', code=200):
    if data is None:
        data = {}
    if not status:
        code = 400
        if message == '':
            message = 'Bad Request'
    response = {
        'status': 'success' if status else 'error',
        'code': code,
        'data': data,
        'message': message
    }
    return response
