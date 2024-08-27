## Running Animal Picture Solution with CAMUNDA 8


1. **Solution Architecture**

![Solution Architecture](arquitecture.png)

2. **Java Spring Architecture**

![Java Clean Architecture](clean.png)

## Steps to Run the Spring Boot ( Must have Docker installed )

1. **Clone the solution**

	```python
    git clone https://github.com/setembrinolusa/camunda8.git
    cd camunda8
	```

2. **Setting up the Cluster vars**

    Change the values as per your Camunda 8 Cluster inside the application.properties files
    
	```bash
    .../camunda8/tree/develop/animal-picture-front/src/main/resources
    .../camunda8/tree/develop/animal-picture-worker/src/main/resources
	```

	```bash
	zeebe.client.cloud.region=
	zeebe.client.cloud.clusterId=
	zeebe.client.cloud.clientId=
	zeebe.client.cloud.clientSecret=
	```

3. **Running the solution**

    You can run the solution by typing the following command

	```python
    docker-compose up
	```

    The servers will start on ports 8080, 8081 and 8082.

    Frontend to start the application 
    Go to the <http://localhost:8080/>

    Worker to execute tasks from-to Camunda 8
    Go to the <http://localhost:8081/>

    Application to persist and search pictures from-to MySQL database
    Go to the <http://localhost:8081/>

4. **Steps to Access the app documentation**

    Go to the <http://localhost:8082/swagger-ui/>

![swagger](swagger.png)

## Database MySQL 5.7
```python
    MYSQL_DATABASE: pictures_db
    MYSQL_USER: pictures_u
    MYSQL_PASSWORD: pictures_p
    MYSQL_ROOT_PASSWORD: root0808
```

| Tables       | Rows |
|--------------|:----:|
| picture      |  0   |

## Pictures table

```python
    mysql> mysql -u pictures_u -p
    mysql> Enter password: `pictures_p`
    mysql> use pictures_db;
    mysql> SELECT * FROM pictures_db.picture;
```

| id  | animal_type | data       | name       | path       | type       |
|-----|:-----------:|:----------:|:----------:|:----------:|:----------:|
|     |             |            |            |            |            |

