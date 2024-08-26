## Running Animal Picture Solution with CAMUNDA 8


1. **Solution Architecture**

![Solution Architecture](arquitecture.png)

2. **Java Spring Architecture**

![Java Clean Architecture](clean.png)

## Steps to Run the Spring Boot ( Must have Docker installed )

1. **Clone the solution**

    ```bash
    git clone https://github.com/setembrinolusa/camunda8.git
    cd camunda8
    ```

2. **Setting up the Cluster vars**

    Change the values as per your Camunda 8 Cluster inside the docker-compose-yml file
```python
    ZEEBE_ADDRESS: 
    ZEEBE_CLIENT_ID: 
    ZEEBE_CLIENT_SECRET: 
    ZEEBE_AUTHORIZATION_SERVER_URL: 
    ZEEBE_TOKEN_AUDIENCE: 
```
3. **Running the solution**

    You can run the solution by typing the following command

    ```bash
    docker-compose up
    ```

    The server will start on ports 8080, 8081 and 8082.

    Frontend to start the application 
    Go to the <http://localhost:8080/>

    Worker to execute tasks from-to Camunda 8
    Go to the <http://localhost:8081/>

    Application to persist and search pictures from-to MySQL database
    Go to the <http://localhost:8081/>

4. **Steps to Access the app documentation**

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

