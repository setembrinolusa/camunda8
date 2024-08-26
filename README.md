## Running animal-picture-app app with Spring Boot

![Java Clean Architecture](clean.png)

![Solution Architecture](arquitecture.png)

## Steps to Run the Spring Boot ( Must have Docker installed )

1. **Clone the application**

    ```bash
    git clone https://github.com/setembrinolusa/camunda8.git
    cd metadata
    ```

2. **Run the app**

    You can run the app by typing the following command

    ```bash
    docker-compose up
    ```

    The server will start on port 8082.

## Steps to Access the app documentation

Go to the <http://localhost:8082/swagger-ui/>

![swagger](swagger.png)

## Auto inserted rows on startup
```python
MYSQL_DATABASE: pictures_db
MYSQL_USER: pictures_u
MYSQL_PASSWORD: pictures_p
MYSQL_ROOT_PASSWORD: root0808
```

| Tables       | Rows |
|--------------|:----:|
| picture      |  0   |

## Pictures

```python
    mysql> mysql -u pictures_u -p
    mysql> Enter password: `pictures_p`
    mysql> use pictures_db;
    mysql> SELECT * FROM pictures_db.picture;
```

| id  | animal_type | data       | name       | path       | type       |
|-----|:-----------:|:----------:|:----------:|:----------:|:----------:|
|     |             |            |            |            |            |

