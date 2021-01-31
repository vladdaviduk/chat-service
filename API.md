**Get active users**
----
  Returns list of currently active users.

* **URL**

  /active-users

* **Method:**

  `GET`
  
*  **URL Params**

   None

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
    ```json 
    [
        "user1",
        "user2"
    ]
    ```
* **Sample Call:**

```
 curl --location --request GET '/active-users' \
 --header 'Content-Type: application/json' \
```
  
**Get a specific Chat**
----
Returns a chat with messages of two users (currently logged user and user 
which name was specified). If such a chat doesn't exist, it will be 
automatically created.

* **URL**

/chats

* **Method:**

`GET`

*  **URL Params**

 **Required:**

 `participant=[String] (username of the second chat participant)`

* **Data Params**

 None

* **Success Response:**

* **Code:** 200 <br />
  **Content:** 
  ```json
    {
      "id": 1,
      "firstOwner": "user2",
      "secondOwner": "user1",
      "messages": [
        {
          "owner": "user1",
          "timestamp": "2021-01-30 21:08:54",
          "content": "Hello!",
          "chatId": 1
        },
        {
          "owner": "user2",
          "timestamp": "2021-01-30 21:09:58",
          "content": "Hello!",
          "chatId": 1
        }
      ]
    }
  ```
     
* **Sample Call:**

```
 curl --location --request GET '/chats?participant=user1' \
 --header 'Content-Type: application/json' \
```

**Get a user Chats**
----
Returns list of current user chats 

* **URL**

/user-chats

* **Method:**

`GET`

*  **URL Params**

 None

* **Data Params**

 None

* **Success Response:**

* **Code:** 200 <br />
  **Content:** 
  ```json
    [
      {
        "id": 1,
        "firstOwner": "user2",
        "secondOwner": "user1",
        "messages": [
          {
            "owner": "user1",
            "timestamp": "2021-01-30 21:08:54",
            "content": "Hello!",
            "chatId": 1
          },
          {
            "owner": "user2",
            "timestamp": "2021-01-30 21:09:58",
            "content": "Hello!",
            "chatId": 1
          }
        ]
      },
      {
        "id": 2,
        "firstOwner": "user2",
        "secondOwner": "user3",
        "messages": []
      }
    ]
  ```
     
* **Sample Call:**

```
 curl --location --request GET '/user-chats' \
 --header 'Content-Type: application/json' \
```

**Save message**
----
Saves current user message

* **URL**

/messages

* **Method:**

`POST`

*  **URL Params**

None

* **Data Params**

    **Request body:** 
    ```json
       {
           "chatId": 1,
           "content": "Hello!"
       }
     ```

* **Success Response:**

  **Code:** 200 <br />
  **Content:** 
    ```json
     {
         "owner": "user2",
         "timestamp": "2021-01-30 21:40:46",
         "content": "Hello!",
         "chatId": 1
     }
    ```
  
* **Sample Call:**

    ```
     curl --location --request POST '/messages' \
     --header 'Content-Type: application/json' \
     --data-raw '{
        ... 
     }'
    ```