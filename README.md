# autopost_news_api
**Суть проекта: Создание rest api, с помошью которого возможно создание новостного канала, основываясь на активности пользователей**

# Стек:
- **Java**
- **Spring**
- **Postgresql**
- **и тд.))**

<br>

# Описание функций API

## Авторизация
### http://api.ru/auth
**GET** metod

### Parametrs
- **login** - **String** (mail)
- **password** - **String**
### Return
- **token** - **String** (неизменный ключ, который дает доступ к персонализированной новостной ленте)

## Регистрация
### http://api.ru/registration
**POST** metod

### Parametrs
- **login** - **String** (mail)
- **password** - **String**
- **firstName** - **String**
- **lastName** - **String**
- **dateBirth** - **String** (Дата рождения(для лучшей пресонализации рекламы))(через .)
- **gender** - **String** (men/women)
### Return 
- **Null**

## Новостная лента
### http://api.ru/news
**GET** metod

### Parametrs
- **token** - **String | None** (ключ авторизации для персонализированной рекламы) (В случае **None** показывать последние популярные новости/опираться на другие параметры)
- **query** - **String | None** (Запрос, если необходима определенная тема/жанр и тд) (В случае **None** показывать популярные новости/опираться на другие параметры)
- **id** - **Integer | None** (разработке) (Если необходимо получить определенную статью)
- **date** **String | None** (Временой промежуток, в течении которого необходимо найти новость) (Формат: dd.mm.yyyy-dd.mm.yyyy)
- **filtres** - **ArrayList<String> | None** (Дополнительные параметры, не упомянутые выше)
- **sorted** - **Map<String, String> | None** (Словарь типа "Ключ": "Значение". Сортирует список новостей в указанном порядке(одном из 3))(Список ключей: date, popularity, likes)(Список значений: BottomToTop, TopToBottom)
### Оценка ответа
### http://api.ru/estimation
- **token** - **String**
- **id** - **Integer** (id новостного поста)
- **estimation** - Boolean (Оценка поста)(True - хорошо, False - Плохо)
### Return
- **page** - **Sting** (html код с новостью) 
### Список может дополняться по мере разработки

<br><br>

# Описание классов
## Database
**Класс для взаимодействия с бд.**
### connect
**подключение к бд. Получает данные из config.ini**
- **Parameters:**
    - **None**
- **Return**
    - **None**
### send 
**отправка запроса, не ожидая ответ от бд.**
- **Parameters:**
    - **execute** - **String** Запрос, отправляемый в бд в формате sql кода.
- **Return**
    - **successful** - **boolean** - true - запрос успешно выполнен. false - произошла ошибка во время выполнения
### get
**отправка запроса, ожидая ответ от бд.**
- **Parameters:**
    - **execute - Запрос, отправляемый в бд в формате sql кода.**
- **Return**
    - **response** - **List<Map<String, Object>>** - Данные из таблицы при успешном выполнении. None в случае ошибки.
