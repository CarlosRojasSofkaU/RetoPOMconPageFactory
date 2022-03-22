#language: es

Característica: Como cliente de parabank
    necesito contactarme con el servicio al cliente
    con el fin de que le den una solucion a mi problematica.

    Antecedentes: que el cliente se encuentra en la pagina web de contactanos.

    @Regresion
    Escenario: Ingreso de una problematica con los campos obligatorios.
        Dado que el cliente se encuentra en la pagina web de contactanos
        Cuando el cliente ingresa los campos obligatorios y confirma la accion
        Entonces el sistema debera mostrar en pantalla un mensaje de agradecimiento con el nombre del cliente

    @Regresion
    Escenario: Ingreso de una problematica con los campos obligatorios diligenciados menos el de mensaje.
        Dado que el cliente se encuentra en la pagina web de contactanos
        Cuando el cliente ingresa los campos obligatorios menos el de mensaje y confirma la accion
        Entonces el sistema debera mostrar en pantalla un mensaje de error diciendo que el campo mensaje es requerido