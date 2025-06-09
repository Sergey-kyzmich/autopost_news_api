from gigachat.schema import GigaChat
from gigachat.schema import HumanMessage, SystemMessage

# Ваша инструкция для модели
system_message = """
Ты помощник по юридическим вопросам. 
Отвечай только на юридически значимые вопросы. 
В остальных случаях направляй пользователя к специалисту.
"""

# Сообщение пользователя
human_message = "Что значит понятие 'суброгация'?"

# Создание сессии с GigaChat
giga = GigaChat(credentials="NGE1ZTA5MjUtNjE3ZC00ZTIxLThlZmMtNTViZmEzYWMwMDMzOmIwMjQ0YTg2LTg2YmUtNDUzNS04NWQxLTNhODg0ZWU0MGY5Mw==", verify_ssl_certs=False)

# Структура запроса
messages = [
    SystemMessage(content=system_message),
    HumanMessage(content=human_message)
]

# Отправка запроса и получение ответа
response = giga.chat(messages)

# Печать результата
print("Ответ GigaChat:", response.choices[0].message.content)