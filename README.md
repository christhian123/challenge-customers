# challenge-customers
Solución de la Kata Junior Fullstack/Cloud – Ciclo de Vida de una Aplicación.


- Backend: Spring Boot + Java 17 + Maven
- Frontend: Angular 19
- Base de datos: PostgreSQL
- Orquestación local: Docker Compose

El proyecto está preparado para ejecutarse en dos ambientes: local, dev y prod (usando Docker).


## Cómo construir

###  Local (sin Docker)

1. Asegurarse de tener un servidor PostgreSQL corriendo en localhost con la base de datos y usuario configurados en application.properties.
2. Construir el proyecto backend:
```
    cd api
    mvn clean install
```
3. Ejecutar backend:
```
    mvn spring-boot:run
```
4. Ejecutar frontend (Angular):
```
    # En otra terminal
    cd ui
    npm install
    # Angular Dev
    ng serve
    # Angular Prod
    ng serve --configuration=production --port 4201
```

### Con Docker

1. Se incluye un Dockerfile y docker-compose.yml que levanta backend y base de datos en contenedores.
```
    # Para ambiente dev
    export SPRING_PROFILE=dev
    export SERVER_PORT=8080
    export POSTGRES_USER=user_kata
    export POSTGRES_PASSWORD=password
    export SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/customers_dev
    export SPRING_DATASOURCE_USERNAME=user_kata
    export SPRING_DATASOURCE_PASSWORD=password

    # Para ambiente prod
    export SPRING_PROFILE=prod
    export SERVER_PORT=9090
    export POSTGRES_USER=user_kata
    export POSTGRES_PASSWORD=password
    export SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/customers_prod
    export SPRING_DATASOURCE_USERNAME=user_kata
    export SPRING_DATASOURCE_PASSWORD=password
```

Los valores de puerto y nombres de base de datos vienen configurados por defecto en docker-compose.yml y no se deben cambiar para la demostración.

2. Levantar la aplicación con Docker Compose:
```
docker compose up --build
```

## Ejecutar en DEV y PROD

Para escoger entre ambientes, exportar el conjunto de variables correspondiente y ejecutar **docker compose up --build.**

### Frontend Angular

* **Dev:** usar ng serve (consume backend en puerto 8080)
* **Prod:** usar ng serve --configuration=production --port 4201 (consume backend en puerto 9090)