Proyecto final para Programación Orientada a Objetos
Diccionario simple - Por Santiago Vergara

Link de la carpeta en drive: https://drive.google.com/drive/folders/14y9ZrqrIPXs85kcrGJteBpGEuYlExQOI?usp=sharing
Link del repositorio en Github: https://github.com/enchiladaSuiza/DiccionarioSimple

Requisitos:
- Tener instalado Java, de preferencia en su versión más reciente, o mínimo la version 15
- Tener instalado MySQL, especialmente MySQL Workbench
- Haber configurado un servidor en MySQL en el puerto 3306 (el que viene por defecto)

Antes de ejecutar el programa:
1. Abrir MySQL y correr el servidor con el puerto 3306, si esque no está corriendo. Esto se puede lograr en la barra de menú -> Server -> Startup/Shutdown -> Start Server

2. Abrir el archivo DiccionarioBD.sql que viene en esta carpeta en MySQL y ejecutarlo. Esto creará la base de datos "diccionario". Se logra en File -> Open SQL Script. (No ejecutar nigún script dentro de la carpeta Scripts SQL! Esos son los scripts que se realizarón durante la creación de la base de datos).

3. Dar doble clic en la base de datos diccionario en el navegadro de MySQL (parte izquierda), al hacer esto se pondrá en negritas y se volverá el esquema por defecto. También es posible dar clic derecho -> Set as Default Schema 

4. Ejecutar el archivo Diccionario.jar. Al abrirlo por primera vez saldrá una ventana que pedirá el nombre de usuario que se usó para establecer conexión con el servidor, y es "root" por defecto. Posteriormente pedirá la contraseña que brindó al crear el servidor en MySQL.

5. Si los datos concuerdan, se abrirá la aplicación. De otra forma aparecerá un mensaje de error y el programa finalizará. En este caso revise que ingresó los datos corretos, que el servidor está corriendo correctamente y que seleccionó "dicconario" como el esquema por defecto.

Como usar:
El programa iniciará en la pantalla de búsqueda.

Búsqueda - Puede escribir la palabra que desee buscar en el campo de texto y presionar enter para obtener sus definiciones, ejemplos, traducciones, y sinónimos si es que los tiene, mientras la palabra se encuentre en la base de datos.

Todas las palabras - Muestra una malla con botones de todas las palabras disponibles en la base de datos. Al hacer clic en algún botón lo llevará a la pantalla de búsqueda con los detalles de la palabra seleccionada. Actualmente hay 100 palabras disponibles.

Listas personales - La base de datos viene con una lista llamada "Favoritos" y algunas palabras en ella. Puede crear nuevas listas con el botón de añadir, lo que le preguntará el nombre de la lista, o eliminarlas con el botón de eliminar, lo cual hará lo mismo pero la lista que escriba se eliminará de la base de datos. Para añadir palabras a las listas tendrá que hacer clic en el botón de añadir a una lista que aparece en el panel de búsqueda. Esto generará un diálogo donde podrá marcar y desmarcar las listas en donde se encuentra la palabra.

Disfrute de la aplicación!
