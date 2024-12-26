# Foro API

## Descripción

Foro API es una aplicación RESTful construida con Spring Boot que permite a los usuarios interactuar con un foro. Los usuarios pueden crear, leer, actualizar y eliminar tópicos en el foro. La API también incluye autenticación y validación de datos.

## Características

- **Autenticación**: Implementación de JWT para la autenticación de usuarios.
- **CRUD de Tópicos**: Permite a los usuarios crear, leer, actualizar y eliminar tópicos.
- **Validación de Datos**: Validación de entradas utilizando anotaciones de validación de Jakarta.
- **Manejo de Errores**: Manejo centralizado de errores con respuestas JSON estructuradas.

## Tecnologías Utilizadas

- Java 23
- Spring Boot 3.4.0
- Spring Data JPA
- Hibernate
- MySQL
- Lombok
- JWT (JSON Web Tokens)

## Requisitos Previos

- JDK 23 o superior
- Maven
- MySQL

## Instalación

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/tu_usuario/foroApi.git
   cd foroApi

2. **Configurar la base de datos**:
- Crea una base de datos en MySql llamada "foro_api", abre tu cliente MySQL (como MySQL Workbench o la terminal) y ejecuta el siguiente comando para crear la base de datos:
     ```sql
     CREATE DATABASE foro_api;
     ```
3. **Crea un usuario en la base de datos**
Elige un usuario y una clave (Encryptada puedes usar BCrypt para ello):

    Ejemplo usuario: usuario.ejemplo
  
    Ejemplo clave: "claveEjemplo" -> $2a$10$HCEIjT2w29UhYQtxj.IYsuVDWnhlQ2SL7ChPHIHK3mjyCds5OW6UK 

  Ejecuta el siguiente comando SQL en tu cliente de MySql para crear un usuario:

    INSERT INTO usuarios (login, clave) VALUES ('usuario.ejemplo', 'claveEjemplo')

4. **Instalar dependencias**
Asegúrate de que Maven esté instalado en tu sistema y estando en el directorio del proyecto, ejecuta en la terminal:

    ```bash
    mvn install
5. **Configurar el Archivo `application.properties`**:
   
   Asegúrate de que el archivo `application.properties` esté configurado correctamente para conectarse a tu base de datos MySQL. Debe contener algo similar a lo siguiente:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/foro_api
     spring.datasource.username=root
     spring.datasource.password=tu_contraseña
     spring.jpa.hibernate.ddl-auto=update
     spring.flyway.enabled=true
     spring.flyway.locations=classpath:db/migration
     ```

## Ejecución del Proyecto

Para ejecutar la aplicación, utiliza el siguiente comando en la raíz del proyecto:

```bash
mvn spring-boot:run
```

## Uso de la API

Para utilizar la API de ForoApi, puedes emplear herramientas como **Insomnia** o **Postman** para realizar solicitudes HTTP a los diferentes endpoints disponibles. A continuación, se detallan los pasos para interactuar con la API.

### 1. Autenticación

Antes de acceder a los endpoints protegidos, debes autenticarte para obtener un token JWT. 

- **Endpoint**: `/login`
- **Método**: POST
- **Cuerpo de la Solicitud**:
  ```json
  {
    "login": "usuarioEjemplo",
    "clave": "claveEjemplo"
  }
![Login Image](https://github.com/Arlo-O/ForoApi/blob/main/images%20README/login.png)
El retorno será el siguiente Json, aseguresé de copiar el token proporcionado ya que tendrá que usarlo a continuación
```json
{
  "jwTtoken": "tu_token_jwt_aqui"
}
```
### 2. Uso del Token
Ahora, en cada uno de los endpoints dirijase a la seccion "Auth" allí seleccione "Bearer Token" y en el campo "TOKEN" pegué el token que ha copiado:

![Pegado del token](https://github.com/Arlo-O/ForoApi/blob/main/images%20README/tokenBearer.png)

### 3. Resgitro topicos.
- **Endpoint**: `/topicos`
- **Método**: POST
- **Cuerpo de la Solicitud**:
  ```json
  {
  "titulo": "Título del Tópico",
  "mensaje": "Mensaje del Tópico",
  "status": "ABIERTO",
  "autor": "usuarioEjemplo",
  "curso": "Curso Ejemplo"
  }
  ```

  Respuesta exitosa:
```json
  {
  "id": 1,
  "titulo": "Título del Tópico",
  "mensaje": "Mensaje del Tópico",
  "fechaCreacion": "2024-01-01T00:00:00",
  "status": "ABIERTO",
  "autor": "usuarioEjemplo",
  "curso": "Curso Ejemplo"
  }
```
![Registro topico Exitoso](https://github.com/Arlo-O/ForoApi/blob/main/images%20README/registroTopicoCorrecto.png)

Como puede ver en la imagen es necesario que cada uno de las claves que componen el topico deben de tener un valor asignado (el valor de la fecha de creacion puede ser o no enviada en el Json, el programa le dará la fecha actual de ser necesario), en caso de que haya cometido algún error en el envio del Json la respuesta será alguna de las siguientes:

Topico con un campo vacio: Debe de proporcionar el campo para que se pueda registrar el topico.

![Topico con campo vacio](https://github.com/Arlo-O/ForoApi/blob/main/images%20README/topicoRegistroCampoVacio.png)

Topico duplicado (Titulo y mensaje ya resgitrado en la base de datos): Registre un topico con un mensaje y titulo diferente.

![Topico duplicado](https://github.com/Arlo-O/ForoApi/blob/main/images%20README/topicoDuplicadoRegistro.png)

### 4. Listado de Topicos
- **Endpoint**: `/topicos`
- **Método**: GET
  
Respuesta exitosa:
```json
{
  "content": [
    {
      "id": 1,
      "titulo": "Título del Tópico",
      "mensaje": "Mensaje del Tópico",
      "fechaCreacion": "2024-01-01T00:00:00",
      "status": "ABIERTO",
      "autor": "usuarioEjemplo",
      "curso": "Curso Ejemplo"
    }
  ],
  "pageable": {
    "sort": {
      "sorted": false,
      "unsorted": true,
      "empty": true
    },
    "offset": 0,
    "pageSize": 3,
    "pageNumber": 0,
    "unpaged": false,
    "paged": true
  },
  "last": false,
  "totalElements": 1,
  "totalPages": 1,
  "size": 3,
  "number": 0,
  "sort": {
    "sorted": false,
    "unsorted": true,
    "empty": true
  },
  "numberOfElements": 1,
  "first": true,
  "empty": false
}
```
![Listado Topicos](https://github.com/Arlo-O/ForoApi/blob/main/images%20README/getTopicos.png)

### 5. Topico Detallado
Para este endpoint debe de proporcionar en la url el id del topico que desea consultar.
- **Endpoint**: `/topicos/{id}`
- **Método**: GET
  
Respuesta exitosa:
```json
{
  "id": 1,
  "titulo": "Título del Tópico",
  "mensaje": "Mensaje del Tópico",
  "fechaCreacion": "2024-01-01T00:00:00",
  "status": "ABIERTO",
  "autor": "usuarioEjemplo",
  "curso": "Curso Ejemplo"
}
```
![Topico Detallado Exitoso](https://github.com/Arlo-O/ForoApi/blob/main/images%20README/topicoDetaalleEncontrado.png)

En caso de que el id del topico no sea encontrado la respuesta será la mostrada en la imagen, debe elegir  un id de topico existente.
![Topico detalle no encontrado](https://github.com/Arlo-O/ForoApi/blob/main/images%20README/detalleTopicoNoEncontrado.png)

### 6. Actualizar Topico
Para este endpoint debe de proporcionar en la url el id del topico que desea de actualizar y además en el body de la petición debe de proporcionar el Json con los datos del topico a actualizar y su debido id.
- **Endpoint**: `/topicos/{id}`
- **Método**: PUT
- **Cuerpo de la solicitud:**
  ```json
  {
    "id": 1,
    "titulo": "Título Actualizado",
    "mensaje": "Mensaje Actualizado"
  }
  ```

Respuesta exitosa:
```json
{
  "id": 1,
  "titulo": "Título Actualizado",
  "mensaje": "Mensaje Actualizado",
  "fechaCreacion": "2024-01-01T00:00:00",
  "status": "ABIERTO",
  "autor": "usuarioEjemplo",
  "curso": "Curso Ejemplo"
}
```

![Respuesta exitosa topico actualizado](https://github.com/Arlo-O/ForoApi/blob/main/images%20README/topicoEliminado.png)

Tenga en cuenta que para este endpoint el id proporcionado en el Json del Body de la petición y el id de la url deben de ser iguales, en caso contrario tendrá como respuesta:
![Id diferente en json y en url](https://github.com/Arlo-O/ForoApi/blob/main/images%20README/topicoErrorActualizacion.png)

### 7. Eliminar Topico
En este endpoint usted deberá de proporcionar en la url de la petición el valor del id del topico que desea eliminar.
- **Endpoint**: `/topicos/{id}`
- **Método**: DELETE
  
  Respuesta exitosa:
```
HTTP/1.1 204 No Content
```
![Topico eliminado correctamente](https://github.com/Arlo-O/ForoApi/blob/main/images%20README/topicoEliminado.png)

En caso de que el id del topico no exista en la base de datos la respuesta será:
![id topico a elimianr no existe](https://github.com/Arlo-O/ForoApi/blob/main/images%20README/topicoEliminadoNoExiste.png)

## Autores ✒️
* **Arlo Ocampo** - [Arlo-O](https://github.com/Arlo-O)
