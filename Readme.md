# Prueba técnica Cristian López

## Objetivo

Desarrollar una API con Spring Boot, arquitectura hexagonal y MongoDB.

## Tech Stack

Se ha realizado el proyecto siguiendo una arquitectura hexagonal con:

- **Java 21**
- **Spring Boot**
- **Spring Data MongoDB** para persistencia de datos.
- **H2** para base de datos.## Tech Stack

Se ha realizado el proyecto siguiendo una arquitectura hexagonal con:

- **Java 21**
- **Spring Boot**
- **Spring Data JPA** para persistencia de datos.
- **H2** para base de datos.
- **Spring Security** (JWT) para el token de autenticación.
- **Spring Boot Test** for testing.
- **MapStruct** para mapear Modelo, DTO y Entities.
- **Swagger** para documentación de la API.
- **JaCoco** para cobertura de tests.
- **Docker**: Uso de docker-compose para gestionar la infraestructura.
- **Postman**: Colección de Postman preconfigurada con todas las llamadas.## Tech Stack

Se ha realizado el proyecto siguiendo una arquitectura hexagonal con:

- **Java 21**
- **Spring Boot**
- **Spring Data MongoDB** para persistencia de datos.
- **Spring Security** (JWT) para el token de autenticación.
- **Spring Boot Test** for testing.
- **Swagger** para documentación de la API.
- **JaCoco** para cobertura de tests.
- **Docker**: Uso de docker-compose para gestionar la infraestructura.
- **Postman**: Colección de Postman preconfigurada con todas las llamadas.

## Desarrollo


### Llamada GET /token/login

Aunque no se pide seguridad para el Endpoint, se ha implementado un token ficticio con JWT que se puede obtener sin usuario ni contraseña. Es una forma de intentar hacer la API lo más real posible, aunque el token se obtenga con un GET y NO con un `POST`y un body del tipo:

		{
			"usuario":"test", 
			"password":"test"
		}
		
Esta llamada devolverá un token para usarse en caso de estar activada la seguridad (se mostraraá como activar/desactivar la seguridad)

### Llamada POST /products/sort

Un Endpoint que consulta mediante criterios de entrada: ventas por unidades y
el criterio de ratio de stock.

	{
	    "salesWeight": 0.7,
	    "stockRatioWeight": 0.3
	}
	
La respuesta devuelve el cálculo ordenado por el mayor número de ventas y el ratio de stock de las tallas que contenga en ese momento.

	[
	  {
	        "id": "5",
	        "name": "CONTRASTING LACE T-SHIRT",
	        "salesUnits": 650,
	        "stock": {
	            "S": 0,
	            "M": 1,
	            "L": 0
	        },
	        "score": 0.7999999999999999
	    }
	]
	
#### Seguridad

Como se ha nombrado anteriormente, se puede activar o desactivar la seguridad como parte extra en el ejercicio. Para hacerlo basta con acceder al archivo `infrastructure.security.SecurityProperties` y modificar el valor de:

```
	private boolean enabled = true;
```

## 🚀 Iniciar la aplicación con Docker

Para lanzar la aplicación será necesario Docker instalado. Desde la carpeta principal del proyecto se podrá usar el siguiente comando:

```
	docker-compose up --build app mongo
```

Este comando levantará Mongo, compilará el proyecto y ejecutará la apliación.

## Test y cobertura con JaCoco

Existen Tests en el proyecto y cobertura de test mediante JaCoco. Para poder ejecutar los test y obtener el reporte de cobertura, desde la carpeta principal del proyecto ejecutaremos:

```
	docker-compose up --build test-runner
```

Para verificar la cobertura de los Tests, dentro de la carpeta `jacoco-reports` existirá un archivo index.html que permitirá observar el porcentaje de cobertura. Se ha asegurado un 83% de cobertura.

## POSTMAN

Existe una colección en la carpeta de `resources` con las llamadas predefinidas para usar la API. En la colección se incluyen las llamadas para obtener el Token y realizar las llamadas definidas en el ejericico, tanto GET Prices en general, como los Test del 1 al 5. 

Como se comenta en la sección de seguridad, si ésta está desactivada, no es necesaria la cabecera de Authorization, por lo que puede deseleccionarse si se desea en las cabeceras de las llamadas en Postman si no se está usando JWT.

## Authors

- [@cristianlopgon](https://github.com/CristianLopGon)