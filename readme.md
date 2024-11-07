# prueba tecnica

Aplicación Spring Boot que proporciona una API para gestionar naves espaciales.

## Características

- CRUD basico para la gestión de naves espaciales.

## Prerrequisitos

- Java 23
- Maven

### Construir el proyecto
 Construir el proyecto con Maven
```bash
mvn clean install
```

### Uso

1. Inicia la aplicación Spring Boot
```bash
mvn spring-boot:run
```
La aplicación comenzará a ejecutarse en `http://localhost:8080`.

## Uso de la API

La aplicación proporciona los siguientes endpoints:

- `GET /naves/{id}`

Este endpoint recupera la nave espacial correspondiente al id introducido.

Reemplaza `{id}` con el ID de la nave espacial.

Ejemplo:

```bash
curl -X GET 'http://localhost:8080/naves?id=5'
```


- `GET /naves?size={size}&page={page}`

Este endpoint recupera las naves espaciales con una paginación.

Reemplaza `{size}` y `{page}` con el numero de naves por página y número de la página.

Ejemplo:

```bash
curl -X GET 'http://localhost:8080/naves?size=10&page=0'
```

- `GET /naves?name={name}`

Este endpoint recupera las naves espaciales con el nombre ya sea completo o parcial de la nave.

Reemplaza `{name}` con el nombre de la nave espacial.

Ejemplo:

```bash
curl -X GET 'http://localhost:8080/naves?name=falcon'
```
- `POST /naves`

Este endpoint crea una nueva nave espacial

Ejemplo:

```bash
curl -X POST 'http://localhost:8080/naves'
```

- `PUT /naves`

Este endpoint modifica una nave espacial existente.

Ejemplo:

```bash
curl -X PUT 'http://localhost:8080/naves'
```

- `DELETE /naves?id={id}`

Este endpoint elimina la nave espacial con un determinado id.

Reemplaza `{id}` con el id de la nave espacial.

Ejemplo:

```bash
curl -X DELETE 'http://localhost:8080/naves?id=5'
```

## Pruebas

Puedes ejecutar las pruebas unitarias con el siguiente comando:

```bash
mvn test
```

## Construido con

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [H2 Database](https://www.h2database.com/html/main.html)
