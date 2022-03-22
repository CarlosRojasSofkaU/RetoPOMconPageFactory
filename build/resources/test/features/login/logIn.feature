#language: es

Característica: Como cliente de parabank
  necesito loguearme en la plataforma de parabank
  con el fin de poder acceder a las funciones de mi cuenta en la plataforma.

  Antecedentes: que el cliente se encuentra en el home de la pagina web.

  @Regresion
  Escenario: Ingreso exitoso con los campos obligatorios.
    Dado que el cliente se encuentra en el home de la pagina web
    Cuando el cliente ingresa sus credenciales para loguearse y confirma la accion
    Entonces el sistema debera mostrar en pantalla un resumen de la cuenta y varias funcionalidades como la de salir de la cuenta

  @Regresion
  Escenario: Ingreso fallido con los campos obligatorios y contraseña erronea.
    Dado que el cliente se encuentra en el home de la pagina web
    Cuando el cliente ingresa sus credenciales pero con contrasena erronea y confirma la accion
    Entonces el sistema debera mostrar en pantalla un mensaje de error indicando que la contrasena o el usuario no se pudieron validar