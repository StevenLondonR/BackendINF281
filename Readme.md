
# API REST BACKEND
## NOTA: Para la tabla usuario, el atributo correo no se puede repetir

**Indice**
1. [Autenticacion](#auth)  
    1.1 [Login](#login)  
    1.2 [Registrar](#register)  
    1.2 [Output](#output)  
2. [Nuestra organizacion](#nuestraOrg)  
    2.1 [Guardar](#guardar)  
    2.2 [Obtener](#obtener)  
    2.3 [Actualizar](#update)

## Tabla de direcciones de todos los endpoints

|  | Metodo | Direcciones |
| --- | --- | --- |
| [Login](#log) | POST | http://localhost:8080/auth/login |
| [Register](#register) | POST | http://localhost:8080/auth/register |
|  |  |  |
| [Guardar](#guardar) | POST |http://localhost:8080/nuestraOrg/save |  
| [Obtener](#obtener) | GET | http://localhost:8080/nuestraOrg/{id} |
| [Actualizar](#update) | PATCH |http://localhost:8080/nuestraOrg |


<div id="auth"/>

## AUTENTICACION

<div id="login"/>

## - LOGIN

| Metodo | Direccion | 
| --- | --- |
| POST |http://localhost:8080/auth/login |

Ejemplo de variables a mandar en formato json: 

```json
{ 
    "correo": "minio@minion.com", 
    "password": "15354ler"
}
```

<div id="register"/>

## - REGISTRAR  

| Metodo | Direccion | 
| --- | --- |
| POST |http://localhost:8080/auth/register |

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

<div id="output"/>

## - SALIDA  

Un usuario al autenticarse se devolvera un token en el siguiente formato:

```json
{
    "token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSb2JlcnQiLCJpYXQiOjE3MTE5MTE5NDIsImV4cCI6MTcxMTkxMzM4Mn0.XlqUxBUkyV26jCTDvLFZfMD55NQ0nG135L9RJfbT44k"  
}
```

<div id="nuestraOrg"/>

## NUESTRA ORGANIZACION   

Un usuario admnistrador( actualmente aun no se tiene restriccion para que el administrador lo modifique ) al entrar podra obtener y modificar los datos de la organizacion

<div id="guardar"/>

## - Guardar  


| Metodo | Direccion | 
| --- | --- |
| POST |http://localhost:8080/nuestraOrg/save |

Ejemplo de variables a mandar en formato json: 

```json
{ 
    "mision": "donar",
    "vision": "realizar mas donaciones",
    "que_hacemos": "gestionar donaciones"
}
```


<div id="obtener"/>

## - Obtener  

| Metodo | Direccion | 
| --- | --- |
| GET | http://localhost:8080/nuestraOrg/{id} |

Ejemplo url a mandar : 

| Por metodo GET | http://localhost:8080/nuestraOrg/1 |
| --- | --- | 

Se obtendra los datos con id = 1 de nuestra organizacion en la siguiente forma:  
```json
{
    "idorganizacion": 1,
    "mision": "verdadero",
    "vision": "cresemos y avanzamos",
    "quehacemos": "donaciones"
}  
```


<div id="update"/>

## - Actualizar


| Metodo | Direccion | 
| --- | --- |
| PATCH |http://localhost:8080/nuestraOrg |

Se puede actualizar cualquier dato de la organizacion: mision, vision, que_hacemos. 

Ejemplo de variables a mandar en formato json: 

```json
{ 
    "id": 1,
    "mision": "donar",
    "vision": "realizar mas donaciones",
    "que_hacemos": "gestionar donaciones"
}
```
Es obligatorio que el atributo **id** este presente, pero para los otros atributos (mision, vision, que_hacemos) pueden o no estar presentes al actualizar los datos. 


