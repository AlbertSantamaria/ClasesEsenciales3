#Clases Esenciales 3

Repositorio contenedor de las soluciones de los ejercicios del 12 al 15 de java propuestos en Atenea en el documento DSA-ClasesEsenciales20131002.pdf.

Para solucionarlos, he dividido las soluciones en 3 bloques cada uno con su paquete. 

La manera de hacer de este y de las otras soluciones de ejercicios es la misma: un servidor, un cliente se envian un objetos llamados PeticionUDP (Peticion para TCP o PartidaChip para el Juego de Chinos), estrictamente se pedia que como minimo se enviasen un String pero he considerado mas sencillo y entendible esta estructura.

Basicamente cada una de las tres soluciones tiene un cliente y un servidor, que se deberian configurar para poder usarlos en maquinas distantes. Tan solo se deberia cambiar la IP de los clientes para que apunte a la direccion del servidor.

A continuacion comento un poco cada bloque:

Protocolo Hora UDP

El primer modelo de la solucion lo hice por consola. En el cliente se pregunta el tipo de formato que se desea recibir (1 completo, 2 numerico) si por error se diese una opcion no contemplada se saca un mensaje y se envia peticion por defecto al servidor con el formato completo. El servidor muestra por consola las peticiones que ha ido recibiendo y las numera.

Protocolo Hora TCP

Para este cree unos frames para practicar de manera que no usan la consola. El cliente dispone de dos botones uno para cada tipo de formato que desea recibir. Cuando hace click en uno se envia la correspondiente solicitud y muestra la respuesta del servidor en el area de respuestas. Tambien dispone de un boton para salir cerrando el programa. El servidor de igual forma muestra la informacion de las peticiones enviadas y recibidas en su area.

Juego de los Chinos

El programa sigue el enunciado. Solo destacar que como sigue el modelo delos anteriores lo que se envia no es un String sino unobjeto de la clase PartidaChip donde de almacenan los datos necesarios para cada jugada. Antes de usarlo se debe cambiar la IP del servidor definida en el cliente (String ip_server). Por defecto se usan los puertos 9001 y 9003 tanto en servidor como en cliente. Sino se inician 2 clientes CON DIRECCIONES IP DIFERENTES no se realizara el juego.


Para ejecutar cualquiera de los tres se debe hacer un maven build sobre el proyecto para poder correr la clase que se quiera (cliente/servidor), el resto de clases son auxiliares.


Albert Santamaria