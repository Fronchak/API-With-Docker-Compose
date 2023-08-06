#SpringBoot API and Docker Compose
##Requirements
* Docker
* Docker Compose
##How to Use
1. Initialize your Docker
2. Configure the env files using the examples
3. Open the terminal in the project's folder
4. Run 'docker compose build && docker compose up -d'
5. Wait until all the containers are running
6. Send a request to 'localhost:8080/users'
7. The only entity in the project, has two properties: firstName and lastName
8. When your are done, run 'docker compose down' to remove the containers
