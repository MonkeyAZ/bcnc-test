# Título del Proyecto

_BCNC Group Test Application_

## Comenzando

_Para descargar el proyecto en su pc, ejecutar la instrucción_

```
git clone https://github.com/MonkeyAZ/bcnc-test.git
```

Mira **Deployment** para conocer como desplegar el proyecto.

### Pre-requisitos

_Para compilar/ejectuar el proyecto es necesario tener instaladas las siguientes dependencias_

```
Java JDK 11
Maven 3.8.6
```

### Instalación

_Una vez instalados los requisitos mencionados en el paso anterior, puedes importar el proyecto en tu editor de codigo elegido (en mi caso he utilizado Visual Studio Code)_

_Para compilar el proyecto nos dirigiremos al directorio raiz del proyecto y ejecutaremos_

```
mvn clean install
```

_Tras ejecutar esta instruccion debe haberse generado el artefacto en el directorio target_
_Podemos ejecutar la aplicación con la instrucción_

```
java -jar app-0.0.1-SNAPSHOT.jar
```

_La base de datsos H2 nos provee de una consola que consultar en la direccion_

```
http://localhost:8080/h2-console
```

_Estos son los parámetros con los que se ha configurado la base de datos, puede modificarlos en el archivo application.properties del proyecto_

```
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=bcnc
spring.datasource.password=bcnc
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=none

```

## Ejecutando las pruebas

_Para ejecutar los test unitarios y de integración, ejecutar la instrucción_

```
mvn test
```

### Analisis de pruebas

_Los test comprueban que dados un conjunto de parámetros (product_id, brand_id, date) el sistema recupera el precio correcto de la base de datos_

_Puede llamar a la api usando una petición POST al siguiente endpoint_

```
http://localhost:8080/prices/get
```

_Deberá añadir los parámetros en el body de la petición en formato JSON_

```
{
  "product_id": 35455,
  "brand_id": 1,
  "date": "2020-12-30 00:00:00.000"
}
```

## Despliegue

_Para realizar el despliegue utilizar el archivo JAR del directoio target y ejecutarlo usando_

```
java -jar app-0.0.1-SNAPSHOT.jar
```

## Construido con

_Estas son las herramientas usadas para construir el proyecto_

- [Spring Boot](https://start.spring.io) - Proyecto generado con la herramienta Spring Initializr
- [Maven](https://maven.apache.org/) - Gestor de dependencias
- [Visual Studio Code](https://code.visualstudio.com) - Editor de código
