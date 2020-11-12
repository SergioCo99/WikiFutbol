# WikiFutbol
Una aplicación para que los amantes del deporte rey, el balompié, puedan consultar la información de sus clubes preferidos y a final de temporada puedan votar por sus equipos favoritos para el reconocido premio “Roncero Team of the Year”.

La aplicación consta de un listado de equipos en los cuales el usuario podrá visualizar los jugadores y los datos del mismo. En la parte de inicio de sesión será posible acceder como usuario o como administrador. En caso de que fuese la primera vez en usar la aplicación, el usuario se podrá registrar, añadiendo algunos datos personales.
El administrador tendrá unos privilegios que el usuario no tiene, como por ejemplo, modificar equipo, agregar usuario, borrar usuario, etc. En resumen, tiene la posibilidad de gestionar gran parte de las características de la aplicación.
El usuario podrá visualizar la lista de los equipos, y al seleccionar uno de ellos podrá acceder a la información relacionada con el club. Esta nueva ventana te permitirá acceder a distintas ventanas en las cuales se muestra la información más detallada, por ejemplo, del estadio.
Además, el usuario podrá votar a sus equipos favoritos, sumando un punto al equipo seleccionado. El equipo con más votos será galardonado con el premio “Roncero Team of the Year”.  

## Getting Started
### Prerrequisitos
* Java 
* MySQL Workbench Server
* Servidor online (opcional)

### Creacion del "schema" en MySQL
En el repositorio hay adjunto un archivo .sql con el que se pueden importar las tablas vacías, es decir sin ningún dato (por si se quisiera escribir desde 0) de la base de datos, incluyendo las claves primarias, secundarias y las relaciones entre ellas y las tablas. Además de esto también hay un "view" para poder visualizar el Team Of The Year con mayor comodidad.

Para conectarse a la base de datos, hay que copiar y pegar lo siguiente, y escribir en un fichero llamado jdbc.properties el controlador, la URL, el usuario y la contraseña adecuados, ademas de la posibilidad de añadir las tablas que quieren excluirse.
```
/WikiFutbol/jdbc.properties
```
```
DB.CONTROLADOR=com.mysql.cj.jdbc.Driver
DB.URL=
DB.USUARIO=
DB.CONTRASENA=
# en DB.TABLASEXCLUIDA las tablas se diferencian entre comas y todo JUNTO (p.e.:usuario,jugador,club), ¡SI LO CAMBIAS REVISA QUE FUNCIONA en VentanaDescargar!
DB.TABLASEXCLUIDAS=usuario
```

#### Ejecutar el programa
La clase principal, es decir el main, se encuentra en el siguiente directorio:
```
/WikiFutbol/src/mainPackage/MainWikiFutbol.java
```
Ejecutando esa clase, la única que debería tener un método main, podrá ser utilizado todo el programa sin ningún problema.
También existe (todavía no), la oportunidad de ejecutar el archivo WikiFutbol.jar para poder usar el software.

### Ejecutar los test
Todos los test se encuentran en el siguiente directorio:
```
/WikiFutbol/test
```
Dentro existen varios packages referenciados con el mismo nombre que los packages del src, y todas las clases tienen el mismo nombre que en el src añadiéndole el nombre test al final de él, es decir, claseTest.java.
