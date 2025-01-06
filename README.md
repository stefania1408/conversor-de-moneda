Conversor de Monedas

Es una aplicación de Java que permite realizar el cambio entre diferentes monedas

Instalación y configuración:
1.	Clona el repositorio en tu máquina local usando Git o descarga el archivo ZIP.
2.	Importa la Biblioteca Gson 2.10.1 en adelante 
3.	Obten una clave API
     Ingresa a (https://app.exchangerate-api.com/sign-up) ExchangeRate-API y registrarte.
     Después de registrarse, se enviará una clave API gratuita a su correo electrónico
4.	Una vez que tenga su clave API, debe configurarla para que la use la aplicación:
   
     Navegue a la carpeta src/ ConsultaCambio API_KEY = "";

  	  Remplace con la clave enviada a su correo

Uso:

Aparece el siguiente menú 

Por favor seleccione una opción:
1. Convertir Moneda
2. Salir

con la primera opcien puede elegir las monedas disponibles (COP, ARS, MXN, BRL, USD, EUR)

la segunda opcion finaliza el programa en cualquier momento mediante una opción dedicada.

Ventajas:
1. La aplicación genera un archivo JSON. En el cual se registran las conversiones realizadas.
2. Utiliza la API de ExchangeRate para obtener tasas de cambio actualizadas.
3. Menú de fácil navegación.



