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
    docker compose up -d
	```

    The servers will start on ports 8080, 8081 and 8082.

    Frontend (STARTER) to start the BPM Process
    
    Go to the <http://localhost:8080/>


    Worker from-to Camunda 8

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

## 

5. **Running a Local Cluster

	https://docs.camunda.io/docs/self-managed/setup/deploy/local/local-kubernetes-cluster/

	Add local host mapping so you can resolve the domain name 
	that will be used to access the Camunda 8 cluster camunda.poc to the local IP address 127.0.0.1.
 	If you are using Mac or Linux, modify the sudo vi /etc/hosts file. 
 	For Windows, modify c:\Windows\System32\Drivers\etc\hosts. Add the following two lines:
 
	```python
	127.0.0.1 camunda.poc
	127.0.0.1 zeebe.camunda.poc
	```
	```python
	kind create cluster --name camunda-platform-poc --config kind.config
	kubectl apply -f \
	https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml
	```
	
	ingress-ngnix controller resources (pods, services, etc.) 
	will be deployed into the ingress-nginx namespace. 
	It may take a few minutes to download container images and configure deployments. 
	Make sure all pods are running with the 
	kubectl get pods --namespace ingress-nginx command before continuing.

	```python
	kubectl config use-context kind-camunda-platform-poc
	helm repo add camunda https://helm.camunda.io
	helm repo update

	helm install --name camunda-platform camunda/camunda-platform \ 
	-f values-combined-ingress.yaml
	```

6. **Install MySQL**
	```python
	helm install --name mysql \ 
	--set mysqlRootPassword=root8080,mysqlUser=pictures_u,mysqlPassword=pictures_p,mysqlDatabase=pictures_db \ 
	stable/mysql
	```

6. **Install the Solution**

	```python
    cd ./camunda8/animal-picture-app
    helm install --name animal-picture-app ./animal-picture-app

    cd ./camunda8/animal-picture-front
    helm install --name animal-picture-front ./animal-picture-front

    cd ./camunda8/animal-picture-worker
    helm install --name animal-picture-worker ./animal-picture-worker
	```

7. **Accessing the Solution**

	Go to http://camunda.poc/animal-picture-front
