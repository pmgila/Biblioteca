BIBLIOTECA


FUNCIONAMIENTO DE LA APLICACI�N

Al iniciar la aplicaci�n, se muestra en pantalla un peque�o formulario para introducir la cuenta de usuario de Dropbox y 
la contrase�a. Una vez introducidos dichos datos, al pulsar el bot�n la aplicaci�n deber�a mostrar el contenido (archivos 
y carpetas) de la cuenta de Dropbox del usuario.


PUNTOS IMPLEMENTADOS DEL CASO

Punto 1. Pantalla inicial de login --> OK.

Punto 2. Mostrar los libros .ebup --> S�lo se ha implementado el mostrar todo el contenido de la cuenta de Dropbox (no s�lo
los libros .ebup). Contiene errores por dificultades encontradas en el proceso (ver DIFICULTADES ENCONTRADAS al final del 
documento).

Punto 3. Men� desplegable --> No implementado por falta de tiempo.

Punto 4. Mostrar portada con doble click --> No implementado por falta de tiempo.


PASOS SEGUIDOS

1. Descarga e instalaci�n del SDK de Android y del entorno de desarrollo Eclipse.

2. Creaci�n de emulador para testear la aplicaci�n.

3. Creaci�n de la p�gina de inicio de la aplicaci�n con el formulario de log in.

4. An�lisis de los fetches (GET y POST) que se realizan para realizar el log in en una cuenta de Dropbox (1� GET p�gina 
de inicio, 2� POST con datos de usuario). Se ha realizado este an�lisis mediante la herramienta HTTP Analyzer.

5. Implementaci�n de dichos fetches en la aplicaci�n.

6. Mostrar el contenido de la respuesta del segundo fetch (POST con los dates de autenticaci�n) que deber�a ser el contenido 
completo de la cuenta de Dropbox del usuario (todos los archivos y carpetas).


DIFICULTADES ENCONTRADAS

-A la hora del manejo de 'regular expressions' con Java para extraer informaci�n de las p�ginas web cargadas (por ello no se 
ha podido implementar la l�gica para extraer las cookies del primer fetch o para filtrar los archivos con extensi�n .ebup).

-A la hora de cargar un contenido en JavaScript en Android (por eso no se ha podido cargar la informaci�n completa de los 
archivos y carpetas contenidos en una cuenta de Dropbox, ya que se muestran en JavaScript).