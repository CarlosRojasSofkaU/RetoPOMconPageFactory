#language: es

Característica: Como cliente de parabank
    necesito contactarme con el servicio al cliente
    con el fin de que le den una solucion a mi problematica

    @Regresion
    Escenario: Ingreso de una problematica con los campos obligatorios.
        Dado que el cliente se encuentra en la pagina web de contactanos
        Cuando el cliente ingresa los campos obligatorios y confirma la accion
        Entonces el sistema debera mostrar en pantalla un mensaje de agradecimiento con el nombre del cliente