BIBLIOTECA


FUNCIONAMIENTO DE LA APLICACIÓN

Al iniciar la aplicación, se muestra en pantalla un pequeño formulario para introducir la cuenta de usuario de Dropbox y 
la contraseña. Una vez introducidos dichos datos, al pulsar el botón la aplicación debería mostrar el contenido (archivos 
y carpetas) de la cuenta de Dropbox del usuario.


PUNTOS IMPLEMENTADOS DEL CASO

Punto 1. Pantalla inicial de login --> OK.

Punto 2. Mostrar los libros .ebup --> Sólo se ha implementado el mostrar todo el contenido de la cuenta de Dropbox (no sólo
los libros .ebup). Contiene errores por dificultades encontradas en el proceso (ver DIFICULTADES ENCONTRADAS al final del 
documento).

Punto 3. Menú desplegable --> No implementado por falta de tiempo.

Punto 4. Mostrar portada con doble click --> No implementado por falta de tiempo.


PASOS SEGUIDOS

1. Descarga e instalación del SDK de Android y del entorno de desarrollo Eclipse.

2. Creación de emulador para testear la aplicación.

3. Creación de la página de inicio de la aplicación con el formulario de log in.

4. Análisis de los fetches (GET y POST) que se realizan para realizar el log in en una cuenta de Dropbox (1º GET página 
de inicio, 2º POST con datos de usuario). Se ha realizado este análisis mediante la herramienta HTTP Analyzer.

5. Implementación de dichos fetches en la aplicación.

6. Mostrar el contenido de la respuesta del segundo fetch (POST con los dates de autenticación) que debería ser el contenido 
completo de la cuenta de Dropbox del usuario (todos los archivos y carpetas).


DIFICULTADES ENCONTRADAS

-A la hora del manejo de 'regular expressions' con Java para extraer información de las páginas web cargadas (por ello no se 
ha podido implementar la lógica para extraer las cookies del primer fetch o para filtrar los archivos con extensión .ebup).

-A la hora de cargar un contenido en JavaScript en Android (por eso no se ha podido cargar la información completa de los 
archivos y carpetas contenidos en una cuenta de Dropbox, ya que se muestran en JavaScript).