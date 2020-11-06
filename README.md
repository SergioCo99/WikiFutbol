# WikiFutbol
Una aplicación para que los amantes del deporte rey, el balompié, puedan consultar la información de sus clubes preferidos y a final de temporada puedan votar por sus equipos favoritos para el reconocido premio “Roncero Team of the Year”.

La aplicación consta de un listado de equipos en los cuales el usuario podrá visualizar los jugadores y los datos del mismo. En la parte de inicio de sesión será posible acceder como usuario o como administrador. En caso de que fuese la primera vez en usar la aplicación, el usuario se podrá registrar, añadiendo algunos datos personales.
El administrador tendrá unos privilegios que el usuario no tiene, como por ejemplo, modificar equipo, agregar usuario, borrar usuario, etc… En resumen, tiene la posibilidad de gestionar gran parte de las características de la aplicación.
El usuario podrá visualizar la lista de los equipos, y al seleccionar uno de ellos podrá acceder a la información relacionada al club. Esta nueva ventana te permitirá acceder a distintas ventanas en las cuales se muestra la información más detallada, por ejemplo, del estadio.
Además, el usuario podrá votar a sus equipos favoritos, sumando un punto al equipo seleccionado. El equipo con más votos será galardonado con el premio “Roncero Team of the Year”.  

## Getting Started
### Prerrequisitos
* Java 
* MySQL Workbench Server
* Online server (opcional)

### Creacion del "schema" en MySQL
En el repositorio hay adjunto un archivo .sql con el que se pueden importar las tablas vacias, es decir sin nignun dato (por si se quisiera escribir desde 0), de la base de datos, incluyendo las claves primarias, secundarias y las relaciones entre ellas y las tablas. Ademas de esto tambien hay un "view" para poder visualizar el Team Of The Year con mayor comodidad.

#### 2.  Ejecutar el programa
1.  La clase principal, es decir el main, se encuentra en el siguiente directorio:
```
/WikiFutbol/src/mainPackage/MainWikiFutbol.java
```
Ejecutando esa clase, la unica que debería tener un metodo main, podrá ser utilizado todo el programa sin ningun problema.
Tambien existe (todavia no), la oportunidad de ejecutar el archivo WikiFutbol.jar para poder usar el software.

## Execute the tests
These are the commands that need to be introduced in order to run the project tests on Windows:
###  Tests without performance:
1. Open a new CMD window and run the following commands:

Tests the compiled source code using a suitable testing Framework.
```
mvn clean test
```
Processes and deploys the package if necessary in an environment where testing can be run.
```
mvn integration-test
```
