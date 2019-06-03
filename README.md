<h1> Proyecto FIS</h1>

<h2>Descripción General</h2>


<h3> Para crear un nuevo componente:</h3>
<h5>
  -> Crear un nuevo proyecto para el módulo y alojarlo en la carpeta /implementacion/ <br>
  -> Para probar el funcionamiento del módulo, se debe crear una clase con el método main y probarlo desde ahí, no se generará un .jar para agregarlo al proyecto de addAdministrativo o appCliente<br>
  -> Una vez depurado el módulo, se generará el .jar y se agregará como referencia del proyecto en cuestión, dicho archivo .jar se alojará en la carpeta /implementacion/comp/
</h5>

<h3>Proyecto 'basicos' y archivo basics.jar</h3>
<h5>Este proyecto y archivo contienen funcionalidades básicas para el funcionamiento de cada módulo tales como:
  <br>
  <ul>
    <li type="disc">Gestor</li>
    <li type="disc">GestorDB</li>
    <li type="disc">GestorCine</li>
    <li type="disc">FachadaCine</li>
    <li type="disc">Funciones</li>
  </ul>
</h5>

<h3>Nomenclatura de paquetes</h3>
<h5>Los paquetes deberán ser nombrados de la siguiente manera: edu.udistrital.fis.<b>[nombre-del-modulo]</b>.<b>[nombre-de-la-capa]</b></h5>

<h3>NOTAS IMPORTANTES:</H3>
<ul>
	<li type="disc">
	Todos los métodos de las clases que extiendan de la clase Gestor deben tener modificador de tipo paquete, esto para evitar que dichos métodos puedan ser llamados por fuera
	del paquete en el que se encuentran
	</il>
</ul>

