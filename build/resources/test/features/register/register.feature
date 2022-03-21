#language: es

Característica: Como cliente de parabank
  necesito registrarme en la plataforma de parabank
  con el fin de poder obtener una cuenta en la plataforma

  Antecedentes: que el cliente se encuentra en la pagina web de registrarse

  @Regresion
  Escenario: Registro exitoso con los campos obligatorios.
    Dado que el cliente se encuentra en la pagina web de registrarse
    Cuando el cliente ingresa los campos obligatorios y confirma la accion
    Entonces el sistema debera mostrar en pantalla un mensaje de bienvenida con el nombre de usuario del cliente y otro con su nombre completo.

  @Regresion
  Escenario: Registro fallido con los campos obligatorios y contraseña y su confirmacion diferentes.
    Dado que el cliente se encuentra en la pagina web de registrarse
    Cuando el cliente ingresa los campos obligatorios con una contrasena y confirmacion diferentes y confirma la accion
    Entonces el sistema debera mostrar en pantalla un mensaje de error indicando que la contrasena y la confirmacion de la contrasena son diferentes.