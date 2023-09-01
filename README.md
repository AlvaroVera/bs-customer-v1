# BS CUSTOMERS V1



## Dependencias
Hay una serie de dependencias de terceros utilizadas en el proyecto. Explore el archivo Maven pom.xml para obtener detalles de las bibliotecas y versiones utilizadas.
## Construyendo el proyecto
Vas a necesitar lo siguiente:

- Java JDK 11
- Maven 3.1.1 or higher
- Git
- Docker

Clona el proyecto y usa maven para construir la aplicación

``  
    mvn clean install
``

## Instrucciones para probar el API
1. Ejecutar el docker-compose.yml que se encuentra en la raiz con el siguiente comando.
   
    ``  
   docker compose up -d
   ``
2. Es comando creara un contenedor con MySql.
3. Ejecutar el script de la carpeta sql para crear la tabla e insertar datos de prueba.
4. Probar los endpoints con el postman collection que se encuentra en la carpeta **postman**