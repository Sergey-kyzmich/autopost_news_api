import requests

data = {
    "login": "ksks2@mail.ru",
    "password": "my pass"
}

# # data = {
# #     "message": "my message"
# # }
res = requests.post("http://localhost/auth", data = data)
print(res.status_code)
print(res.text)

# import keyboard
# import time
# time.sleep(2)
# keyboard.write("YP4Q7VUeP4Af")