
# API REST BACKEND

**El api-rest funciona con java 21** 

## NOTA: Para la tabla usuario, el atributo correo no se puede repetir
## NOTA: En la base de datos existe un usuario administrador con los siguientes datos   
## **"correo": "admin@admin.com" ,  "password": "123456"**  
 

**Indice**
1. [Autenticacion](#auth)  
    1.1 [Login](#login)   
    1.2 [Output](#output) 
2. [Registrar](#register)  
3. [Nuestra organizacion](#nuestraOrg)  
    3.1 [Guardar](#guardar)  
    3.2 [Obtener](#obtener)  
    3.3 [Actualizar](#update)   
4. [Mensajes usuario normal](#mensajePostUser)   
    4.1 [Obtener mensajes usuario normal](#getpostuser)  
    4.2 [Aceptar usuario](#aceptarUser)  
    4.3 [Rechazar usuario](#refusedUser)  
    4.4 [Inabilitar usuario](#inabilitarUser)  
    4.5 [Eliminar usuario que postula](#deleteUser)  



## Tabla de direcciones de todos los endpoints

|  |  | Metodo | Direcciones(EndPoints) |
| --- |--- | --- | --- |
| Authenticacion |
| | [Login](#log) | POST | http://localhost:8090/auth/login |
| Registrarse  |
| |[Register](#register) | POST | http://localhost:8090/auth/register |
| Nuestra organizacion |
| |[Guardar](#guardar) | POST |http://localhost:8090/nuestraOrg/save |  
| |[Obtener](#obtener) | GET | http://localhost:8090/nuestraOrg/{id} |
| |[Actualizar](#update) | PATCH |http://localhost:8090/nuestraOrg |
| Mensaje para postulacion a Usuario normal |
| | [Obtener mensajes usuario normal](#getpostuser)| GET | http://localhost:8090/messageUser/getPostUsuario |  
| | [Aceptar usuario](#aceptarUser) | GET | http://localhost:8090/messageUser/acceptUser/{id}  |
| | [Rechazar usuario](#refusedUser) | GET | http://localhost:8090/messageUser/refusedUser/{id} |
| | [Inabilitar usuario](#inabilitarUser) | GET | http://localhost:8090/messageUser/inabilitarUser/{id} |
| | [Eliminar usuario que postula](#deleteUser)| DELETE | http://localhost:8090/messageUser/deleteUser/{id} |

<br/>
<br/>

<div id="auth"/>

## AUTENTICACION

<div id="login"/>

## - LOGIN

| Metodo | Direccion | 
| --- | --- |
| POST |http://localhost:8090/auth/login |

### Entrada 
Ejemplo de variables a mandar en formato json: 

```json
{ 
    "correo": "minio@minion.com", 
    "password": "15354ler"
}
```
   
### Salida

Un usuario al autenticarse se devolvera un token en el siguiente formato:

```json
{
    "token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSb2JlcnQiLCJpYXQiOjE3MTE5MTE5NDIsImV4cCI6MTcxMTkxMzM4Mn0.XlqUxBUkyV26jCTDvLFZfMD55NQ0nG135L9RJfbT44k"  
}
```

<br/>
<br/>


<div id="register"/>

## REGISTRAR  

| Metodo | Direccion | 
| --- | --- |
| POST |http://localhost:8090/auth/register |

### Entrada

Ejemplo de variables a mandar en formato json: 

```json
{ 
    "nombre": "Robert",
    "apellido": "migo",
    "password": "15354ler",
    "correo": "minio@minion.com",
    "ubicacion": "Perez",
    "telefono": 1598328
}
```
### Salida

```json
Se envio la solicitud
```
La salida esta en formato **String**

<br/>
<br/>

   
<div id="nuestraOrg"/>

## NUESTRA ORGANIZACION   

Un usuario admnistrador( actualmente aun no se tiene restriccion para que el administrador lo modifique ) al entrar podra obtener y modificar los datos de la organizacion

<div id="guardar"/>

## - Guardar  


| Metodo | Direccion | 
| --- | --- |
| POST |http://localhost:8090/nuestraOrg/save |

### Entrada

Ejemplo de variables a mandar en formato json: 

```json
{ 
    "mision": "donar",
    "vision": "realizar mas donaciones",
    "que_hacemos": "gestionar donaciones"
}
```
### Salida

Solo retornara un valor booleano **true** al guardarse.

<br/>

<div id="obtener"/>

## - Obtener  

**No se nesecita autenticacion para obtener los datos.**

| Metodo | Direccion | 
| --- | --- |
| GET | http://localhost:8090/nuestraOrg/{id} |

### Entrada
  
Como metodo **get** la entrada es un {id} en la url.   

Ejemplo url a mandar : 

| Por metodo GET | http://localhost:8090/nuestraOrg/1 |
| --- | --- | 


### Salida

Se devolveran los datos con id = 1 de nuestra organizacion en la siguiente forma:  
```json
{
    "idorganizacion": 1,
    "mision": "verdadero",
    "vision": "cresemos y avanzamos",
    "quehacemos": "donaciones"
}  
```
<br/>


<div id="update"/>

## - Actualizar


| Metodo | Direccion | 
| --- | --- |
| PATCH |http://localhost:8090/nuestraOrg |

Se puede actualizar cualquier dato de la organizacion: mision, vision, que_hacemos. 

### Entrada

Ejemplo de variables a mandar en formato json: 

```json
{ 
    "id": 1,
    "mision": "donar",
    "vision": "realizar mas donaciones",
    "que_hacemos": "gestionar donaciones"
}
```
### Salida

Solo retornara un valor booleano **true** al actualizarse.

Es obligatorio que el atributo **id** este presente, pero para los otros atributos (mision, vision, que_hacemos) pueden o no estar presentes al actualizar los datos. 

<br/>
<br/>


<div id="mensajePostUser"/>

## Mensajes usuario normal

<div id="getpostuser"/>

## - Obtener mensajes usuario normal


| Metodo | Direccion | 
| --- | --- |
| GET |http://localhost:8090/messageUser/getPostUsuario |

### Entrada 

No requiere entrada de ningun valor. 

### Salida

```json

[
    {
        "id": 9,
        "nombre": "Londres",
        "apellido": "minion",
        "correo": "Lone@gmail.com",
        "telefono": 156489,
        "estado": "Rechazado"
    },
    {
        "id": 10,
        "nombre": "Londres",
        "apellido": "minion",
        "correo": "Lone@gail.com",
        "telefono": 156489,
        "estado": "Inabilitado"
    },
    {
        "id": 11,
        "nombre": "Londres",
        "apellido": "minion",
        "correo": "Lon@gai.com",
        "telefono": 156489,
        "estado": "Pendiente"
    }
]

```

Se devolvera una lista de usuarios que estan con estado: **Pendiente, Inabilidato, Rechazado**. 

<br/>

<div id="aceptarUser"/>

## - Aceptar usuario

| Metodo | Direccion | 
| --- | --- |
| GET |http://localhost:8090/messageUser/acceptUser/{id}|


### Entrada 

Como metodo **get** la entrada es un **{id} => id_del_usuario** en la url.   

Ejemplo url a mandar : 

| Por metodo GET | http://localhost:8090/messageUser/acceptUser/8 |
| --- | --- | 

### Salida

```json
Usuario Aceptado
```
La salida esta en formato **String**

<br/>


<div id="refusedUser"/>

## - Rechazar usuario


| Metodo | Direccion | 
| --- | --- |
| GET |http://localhost:8090/messageUser/refusedUser/{id} |


### Entrada 

Como metodo **get** la entrada es un **{id} => id_del_usuario** en la url.   

Ejemplo url a mandar : 

| Por metodo GET | http://localhost:8090/messageUser/refusedUser/9 |
| --- | --- | 

### Salida

```json
Usuario Rechazado
```
La salida esta en formato **String**

<br/>


<div id="inabilitarUser"/>

## - Inabilitar usuario


| Metodo | Direccion | 
| --- | --- |
| GET |http://localhost:8090/messageUser/inabilitarUser/{id} |


### Entrada 

Como metodo **get** la entrada es un **{id} => id_del_usuario** en la url.   

Ejemplo url a mandar : 

| Por metodo GET | http://localhost:8090/messageUser/inabilitarUser/10 |
| --- | --- | 

### Salida

```json
Usuario Inabilitado
```
La salida esta en formato **String**

<br/>


<div id="deleteUser"/>

## - Eliminar usuario que postula


| Metodo | Direccion | 
| --- | --- |
| DELETE |http://localhost:8090/messageUser/deleteUser/{id}  |


### Entrada 

Como metodo **DELETE** la entrada es un **{id} => id_del_usuario** en la url.   

Ejemplo url a mandar : 

| Por metodo DELETE | http://localhost:8090/messageUser/deleteUser/12 |
| --- | --- | 

### Salida

```json
Usuario Eliminado
```
La salida esta en formato **String**


