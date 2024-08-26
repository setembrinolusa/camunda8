## Running Animal Picture Solution with CAMUNDA 8

![Java Clean Architecture](clean.png)

![Solution Architecture](arquitecture.png)

## Steps to Run the Spring Boot ( Must have Docker installed )

1. **Clone the application**

    ```bash
    git clone https://github.com/setembrinolusa/camunda8.git
    cd camunda8
    ```

2. **Setting up the Cluster vars**

## Change the values as per your Camunda 8 Cluster
```python
ZEEBE_ADDRESS: 
ZEEBE_CLIENT_ID: 
ZEEBE_CLIENT_SECRET: 
ZEEBE_AUTHORIZATION_SERVER_URL: 
ZEEBE_TOKEN_AUDIENCE: 
```
3. **Run the solution**

    You can run the solution by typing the following command

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

