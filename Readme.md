
# API REST BACKEND

**El api-rest funciona con java 21** 

# PARA ACCEDER A LA NUEVA DOCUMENTACION, SE TIENEQUE 
- CLONAR EL REPOSITORIO EN LA RAMA MASTER  
- EJECUTAR EL BACKEND (BASE DE DATOS YA FUNCIONANDO)
- DESPUES DE DEPLEGARSE ACCEDER A LA DIRECCION: http://localhost:8090/swagger-ui/index.html   

USUARIOS REGISTRADOS EN LA BASE DE DATOS 
-------------------------------------------------------------------------
| Usuario | Password | Rol | 
|--|--|--|
| admin@admin.com | 123456 | Administrador |
| Lon@gai.com | 651984 | Voluntario |
| Londres@gmail.com | 651984  |  | 
| London@gmail.com | 651984 | Donante |
| Minion@gmail.com | 987654 | Donante |
| Lones@gail.com | 651984 | Voluntario |
| Stev@gmail.com | 123456 | Voluntario |
| rowell@gmail.com | 123456 | Receptor |
| jhonson@gmail.com | 123456 | Receptor |
| tony@gmail.com | 123456 | Voluntario |
-------------------------------------------------------------------------

## NOTA: Para la tabla usuario, el atributo correo no se puede repetir
## NOTA: En la base de datos existe un usuario administrador con los siguientes datos   
## **"correo": "admin@admin.com" ,  "password": "123456"**  

## Tabla de direcciones de todos los endpoints

|  |  | Metodo | Direcciones(EndPoints) |   |
| --- |--- | --- | --- | --- |  
|Usuario|
| |[Datos basicos de Usuario](#getUserBasic)  | POST | http://localhost:8090/user/getSimpleUser | Devuelve los datos basicos e importantes de un usuario |
| Authenticacion |   
| | [Login](#log) | POST | http://localhost:8090/auth/login | Para que un Usuario Habilitado pueda Iniciar sesion |
| Registrarse  |  
| |[Register](#register) | POST | http://localhost:8090/auth/register | Un usuario puede registrarse y mandara los datos para que el administrador:acepte o rechace  |  
| Nuestra organizacion |  
| |[Guardar](#guardar) | POST |http://localhost:8090/nuestraOrg/save | Para guardar datos de nuestra organizacion: mision, vision y que_hacemos (Solo administrador) |
| |[Obtener](#obtener) | GET | http://localhost:8090/nuestraOrg/{id} | Devuelve los datos de nuestra organizacion: mision, vision y que_hacemos( se puede acceder sin token ) |
| |[Actualizar](#update) | PATCH |http://localhost:8090/nuestraOrg | Actualiza los datos de nuestra organizacion: mision, vision o que_hacemos (Solo administrador)  |
| Mensaje para postulacion a Usuario normal |  
| | [Obtener mensajes usuario normal](#getpostuser)| GET | http://localhost:8090/messageUser/getPostUsuario |  Devuelve los datos de un usuario que de postulo a **usuario normal** ( solo administradores ) |   
| | [Aceptar usuario](#aceptarUser) | GET | http://localhost:8090/messageUser/acceptUser/{id}  |  Acepta a un usuario que postulo a ser **usuario normal**( solo administradores )  |
| | [Rechazar usuario](#refusedUser) | GET | http://localhost:8090/messageUser/refusedUser/{id} |  Rechaza a un usuario que postulo a ser **usuario normal**( solo administradores )  |
| | [Inabilitar usuario](#inabilitarUser) | GET | http://localhost:8090/messageUser/inabilitarUser/{id} |  Inabilita a un usuario( solo para usuario que fueron habilitados no es valido para usuarios con estado: Pendiente o rechazado )( solo administradores )   |  
| | [Eliminar usuario que postula](#deleteUser)| DELETE | http://localhost:8090/messageUser/deleteUser/{id} |  Elimina a un postulante a **usuario normal**(no es valido para usuario que estan Habilitados o Inabilitados ya que tiene datos que dependen de estos usuario)( solo administradores ) | 
| | [Obtener a usuarios normales habilitados](#getUserh)| GET | http://localhost:8090/messageUser/getHabilitados |  Devuelve a usuarios que fueron habilitados  |
| | [Obtener a usuarios normales Inabilitados](#getUserI)| GET | http://localhost:8090/messageUser/getInabilitados | Devuelve a usuarios que fueron inabilitados   | 
| | [Obtener a usuarios normales Pendientes](#getUserp)| GET | http://localhost:8090/messageUser/getPendientes |  Devuelve a usuarios que aun no fueron aceptados  | 
| | [Obtener a usuarios normales Rechazados ](#getUserr)| GET | http://localhost:8090/messageUser/getRechazados |  Devuelve a usuario que fueron rechazados  | 
| | [Enviar el rol a escoger](#escogerRol)| POST | http://localhost:8090/userPostRol/escogerRol |  Un usuario envia a que rol quiere posutular: Voluntario, Donante, Receptor  | 
| | [Obtener a postulantes Donantes](#getUserD)| GET | http://localhost:8090/messageUser/getPostAllDonantes |  Devuelve a todos los pustulantes a ser Donantes  | 
| | [Obtener a postulantes Donantes Representantes](#getUserDR)| GET | http://localhost:8090/messageUser/getPostDonantesOrg |  Devuelve solo a todos los postulantes a ser Donantes Representantes de una organizacion benefica  | 
| | [Obtener a postulantes Donantes no representante ](#getUserDNR)| GET | http://localhost:8090/messageUser/getPostDonantesSinOrg |  Devuelve solo a todos los postulantes a ser Donantes que no representan a una organizacion benefica | 
| | [Aceptar el rol de un postulante ](#acceptRol)| GET | http://localhost:8090/messageUser/acceptMUserRol/{id} |  Se acepta, guarda(rol elegido) a un usuario que halla postulado a tener el rol de: Voluntario, Donante, Receptor y elimina su mensaje de postulacion | 
| | [Rechazar el rol de un postulante ](#refusedRol)| DELETE | http://localhost:8090/messageUser/refusedMUserRol/{id} | rechaza y elimina automaticamente su mensaje de postulacion | 

<br/>
<br/>


<div id="getUserBasic"/>

## USUARIO


| Metodo | Direccion | 
| --- | --- |
| POST |http://localhost:8090/user/getSimpleUser |

### Entrada 
Ejemplo de variables a mandar en formato json: 

```json
{ 
    "correo": "minio@minion.com", 
}
```

### Salida

Se devolveran los datos importantes del usuario.
Los atrivutos rol, rolVol, nombreOrg pueden ser "" ya que un usuario donante no tiene responsable o colaborador como un usuario voluntario, un usuario voluntario puede tener "nombreOrg" = "" ya que no puede representar a ninguna organizacion como donante o receptor.  

```json
{
    "nombre": "Minion",
    "apellido": "Stalone",
    "correo": "Minion@gmail.com",
    "telefono": 1351684,
    "rol": "Donante",
    "rolVol": "",
    "nombreOrg": "Feeding America 2"
}
```



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


<div id="getUserh"/>


## - Obtener a usuarios normales habilitados

| Metodo | Direccion | 
| --- | --- |
| GET |http://localhost:8090/messageUser/getHabilitados |

### Entrada 
No se necesita entrada json
   
### Salida

Se devolvera una lista de usuarios habilitados 

```json
[
    {
        "id": 2,
        "nombre": "admin",
        "apellido": "minion",
        "correo": "admin@admin.com",
        "telefono": 1598328,
        "rol": "",
        "estado": "Habilitado"
    },
    {
        "id": 6,
        "nombre": "Londres",
        "apellido": "minion",
        "correo": "Londres@gmail.com",
        "telefono": 156489,
        "rol": "Donante",
        "estado": "Habilitado"
    },
    {
        "id": 8,
        "nombre": "Londres",
        "apellido": "minion",
        "correo": "Lones@gmail.com",
        "telefono": 156489,
        "rol": "",
        "estado": "Habilitado"
    },
    {
        "id": 12,
        "nombre": "London",
        "apellido": "Rowell",
        "correo": "London@gmail.com",
        "telefono": 156489,
        "rol": "Donante",
        "estado": "Habilitado"
    }
   
]
```

<br/>
<br/>


<div id="getUserI"/>

## - Obtener a usuarios normales Inabilitados  
Devuelve a usuarios que fueron inabilitados.

| Metodo | Direccion | 
| --- | --- |
| GET |http://localhost:8090/messageUser/getInabilitados  |

### Entrada

No necesita entra json. 

### Salida

Retornara una lista de usuario inabilitados. 

```json
[
    {
        "id": 10,
        "nombre": "Londres",
        "apellido": "minion",
        "correo": "Lone@gail.com",
        "telefono": 156489,
        "rol": "",
        "estado": "Inabilitado"
    }
]
```
IMPORTANTE: en este caso solo se inabilito a un usuario

<br/>
<br/>



<div id="getUserp"/>


## - Obtener a usuarios normales Pendientes
Devuelve a usuarios que aun no fueron aceptados 

| Metodo | Direccion | 
| --- | --- |
| GET |http://localhost:8090/messageUser/getPendientes  |

### Entrada

No necesita entrada json.

### Salida
Retornara una lista de usuarios pendientes. 

```json
[
    {
        "id": 11,
        "nombre": "Londres",
        "apellido": "minion",
        "correo": "Lon@gai.com",
        "telefono": 156489,
        "estado": "Pendiente"
    },
    {
        "id": 13,
        "nombre": "Minion",
        "apellido": "Stalone",
        "correo": "Minion@gmail.com",
        "telefono": 1351684,
        "estado": "Pendiente"
    }
]
```

<br/>
<br/>


<div id="getUserr"/>

## - Obtener a usuarios normales Rechazados 
Devuelve a usuario que fueron rechazados

| Metodo | Direccion | 
| --- | --- |
| GET |http://localhost:8090/messageUser/getRechazados  |

### Entrada

No necesita entrada json. 

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
    }
]
```

<br/>
<br/>

<div id="escogerRol"/>


## - Enviar el rol a escoger 
Un usuario envia a que rol quiere posutular: Voluntario, Donante, Receptor. 

| Metodo | Direccion | 
| --- | --- |
| POST |http://localhost:8090/userPostRol/escogerRol  |

### Entrada

Ejemplo de variables a mandar en formato json: 

Si se quiere postular a Donante representando a una organizacion. 
El **contenido** en el siguiente orden: Ubicacion,tipo_alimento,NombreDeOrganizacion,area_servicio

```json
{ 
    "correo": "Minion@gmail.com",
    "rol": "Donante",
    "contenido": "La Paz,Alimentos secos,Feeding America 2,Centro Juvenil"
}
```

Si se quiere postuar a Donante sin ser representante.

```json
{ 
    "correo": "Londres@gmail.com",
    "rol": "Donante",
    "contenido": ""
}
```

Si se quiere postular a se Receptor representando a una organizacion  
El **contenido** en el siguiente orden: tipo_org,Ubicacion,NombreDeOrganizacion

```json
{ 
    "correo": "Rowell@gmail.com",
    "rol": "Receptor",
    "contenido": "Comunitaria ,Peres calle 7 ,Feeding America 12354"
}
```

Si se quiere postuar a Receptor sin ser representante.


```json
{ 
    "correo": "Londres@gmail.com",
    "rol": "Receptor",
    "contenido": ""
}
```

Si se quiere postuar a Voluntario.

```json
{ 
    "correo": "Londres@gmail.com",
    "rol": "Voluntario",
    "contenido": ""
}
```


### Salida

Solo retornara un valor booleano **true** al realizarse correctamente.

<br/>
<br/>

<div id="getUserD"/>

## - Obtener a postulantes Donantes 
Devuelve a todos los **pustulantes** a ser Donantes. 

| Metodo | Direccion | 
| --- | --- |
| GET |http://localhost:8090/messageUser/getPostAllDonantes |

### Entrada

No necesita entrada json. 

### Salida

Devolvera una lista de todos los usuario normales que estan **postulando** al rol de donantes.

```json
[
    {
        "id": 4,
        "nombre": "Minion",
        "apellido": "Stalone",
        "correo": "Minion@gmail.com",
        "telefono": 1351684,
        "rol": "Donante"
    },
    {
        "id": 5,
        "nombre": "Londres",
        "apellido": "minion",
        "correo": "Lones@gmail.com",
        "telefono": 156489,
        "rol": "Donante"
    }
]
```


<br/>
<br/>

<div id="getUserDR"/>

## - Obtener a postulantes Donantes Representantes 
Devuelve solo a todos los **postulantes** a ser **Donantes**  **Representantes** de una **organizacion** **benefica**.

| Metodo | Direccion | 
| --- | --- |
| GET |http://localhost:8090/messageUser/getPostDonantesOrg  |

### Entrada

No necesita entrada json. 

### Salida

Devuelve una lista de todos los **postulantes** a donantes que representan a una organizacion benefica. 

```json
[
    {
        "id": 4,
        "nombre": "Minion",
        "apellido": "Stalone",
        "correo": "Minion@gmail.com",
        "telefono": 1351684,
        "rol": "Donante",
        "nombreOrg": "Feeding America 2"
    }
]
```
IMPORTANTE: en este caso solo devuelve un elemento ya que solo un usuario se postulo para tener el rol de donantes que representan a una organizacion benefica . 


<br/>
<br/>

<div id="getUserDNR"/>

## - Obtener a postulantes Donantes no representante
Devuelve solo a todos los postulantes a ser Donantes que no representan a una organizacion benefica

| Metodo | Direccion | 
| --- | --- |
| GET |http://localhost:8090/messageUser/getPostDonantesSinOrg |

### Entrada

No necesita entrada json. 

### Salida

Devuelve una lista de todos los **postulantes** a donantes que no representan a una organizacion. 

```json
[
    {
        "id": 5,
        "nombre": "Londres",
        "apellido": "minion",
        "correo": "Lones@gmail.com",
        "telefono": 156489,
        "rol": "Donante"
    }
]
```

IMPORTANTE: en este caso solo devuelve un elemento ya que solo un usuario se postulo para tener el rol de donantes que no representan a una organizacion . 

<br/>
<br/>

<div id="acceptRol"/>

## - Aceptar el rol de un postulante 
Se acepta, guarda(rol elegido) a un usuario que halla postulado a tener el rol de: Voluntario, Donante, Receptor y elimina su mensaje de postulacion

| Metodo | Direccion | 
| --- | --- |
| GET | http://localhost:8090/messageUser/acceptMUserRol/**{id}** |

### Entrada

No requiere una entrada json. 

### Salida

Solo retornara un valor booleano **true** al aceptarse al usuario.

<br/>
<br/>

<div id="refusedRol"/>

## - Rechazar el rol de un postulante
Rechaza y elimina automaticamente su mensaje de postulacion

| Metodo | Direccion | 
| --- | --- |
| DELETE |http://localhost:8090/messageUser/refusedMUserRol/**{id}**  |

### Entrada

No requiere una entrada json. 

### Salida

Solo retornara un valor booleano **true** al rechazar y eliminarse el mensaje de postulacion automaticamente y correctamente.

<br/>
<br/>


