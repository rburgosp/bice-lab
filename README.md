<body lang=ES-CL style='tab-interval:35.4pt'>

<div class=WordSection1>

<p class=MsoNormal># <span class=SpellE>bice-lab</span><o:p></o:p></p>

<p class=MsoNormal>Test Banco <span class=SpellE>Bice</span><o:p></o:p></p>

<p class=MsoNormal>Dentro del Archivo Zip EntregaRicardoBurgos.zip se encuentra
<span class=GramE>el proyectos</span><o:p></o:p></p>

<p class=MsoNormal>Este consta de <o:p></o:p></p>

<p class=MsoNormal>2 microservicios <span class=SpellE>backend</span>
desarrollados con Java 1.8 Base de datos en Memoria H2 y <span class=SpellE>Springboot</span>(v2.3.<span
class=GramE>1.RELEASE</span>)<o:p></o:p></p>

<p class=MsoNormal>el <span class=SpellE>front</span> desarrollado con <span
class=SpellE>react</span>.<o:p></o:p></p>

<p class=MsoNormal>ambos se encuentran <span class=SpellE>dockerizados</span>
con sus respectivos <span class=SpellE><b>DockerFile</b></span> y <span
class=SpellE><b>docker-compose.yml</b></span> para su ejecución<o:p></o:p></p>

<p class=MsoNormal>para ejecutarlo de manera que corra sobre <span
class=SpellE>docker</span> se debe instalar previamente desde
https://www.docker.com/products/docker-desktop<o:p></o:p></p>

<p class=MsoNormal>una vez instalado desde una terminal se debe acceder a la
ruta donde está el archivo <span class=SpellE><b>docker-compose.yml</b></span>
y ejecutar el comando <span class=SpellE><b>docker-compose</b></span><b> up --<span
class=SpellE>build</span> -d</b><o:p></o:p></p>

<p class=MsoNormal><o:p>&nbsp;</o:p></p>

<p class=MsoNormal>también se puede ejecutar de manera individual cada uno de
ellos <o:p></o:p></p>

<p class=MsoNormal><o:p>&nbsp;</o:p></p>

<p class=MsoNormal>Ejecutar <span class=SpellE>Backend</span> de manera individual<o:p></o:p></p>

<p class=MsoNormal>desde una terminal acceder a la carpeta <span class=GramE>target</span>
de cada uno de los proyectos y ejecutar el comando<o:p></o:p></p>

<p class=MsoNormal><b>java -<span class=SpellE>jar</span> <i>&lt;nombre-<span
class=SpellE>jar</span>&gt;.</i><span class=SpellE>jar</span><o:p></o:p></b></p>

<p class=MsoNormal><o:p>&nbsp;</o:p></p>

<p class=MsoNormal>Ejecutar <span class=SpellE>FrontEnd</span> de manera individual<o:p></o:p></p>

<p class=MsoNormal>desde una terminal acceder a la carpeta <span class=SpellE>react-commodity-bice</span>
y ejecutar los comandos<o:p></o:p></p>

<p class=MsoNormal><span class=SpellE><b>npm</b></span><b> <span class=SpellE>install</span><o:p></o:p></b></p>

<p class=MsoNormal><span class=SpellE><b>npm</b></span><b> <span class=SpellE>start</span><o:p></o:p></b></p>

<p class=MsoNormal><o:p>&nbsp;</o:p></p>

<p class=MsoNormal>para esta implementación se creó un sitio de compra de <span
class=SpellE>commoditys</span><o:p></o:p></p>

<p class=MsoNormal>lo primero es crear una persona Presionando desde el botón Crear<o:p></o:p></p>

<p class=MsoNormal>se debe ingresar el Rut, <span class=SpellE>Dv</span>, Nombre,
Apellido y Email una vez creada la persona esta se puede editar o eliminar.<o:p></o:p></p>

<p class=MsoNormal>una vez creado el usuario se puede <span class=GramE>proceder
a realizar</span> la compra de <span class=SpellE>commodityes</span><o:p></o:p></p>

<p class=MsoNormal>primero seleccionado desde el combo Persona la persona y la
cantidad de <span class=SpellE>commodityes</span> a adquirir, al presionar el botón
pagar el monto de los <span class=SpellE>commodityes</span><o:p></o:p></p>

<p class=MsoNormal>se anexará a los datos de la persona en la tabla <o:p></o:p></p>

<p class=MsoNormal>los datos de persona almacenado y obtención se obtienen
desde el Microservicio <span class=SpellE><b>MsRestGestionUsuario</b></span><o:p></o:p></p>

<p class=MsoNormal>y los <span class=SpellE>commodityes</span> y valor dólar se
obtienen desde el servicio <span class=SpellE><b>MsProxyIndicesEconomicos</b></span>
el cual llama a los servicios de <b>https://www.indecon.online/</b><o:p></o:p></p>

<p class=MsoNormal><o:p>&nbsp;</o:p></p>

<p class=MsoNormal>se implementaron 2 pruebas unitarias básicas en el <span
class=SpellE>front</span> se creó test unitario para detectar que los componentes
se creen exitosamente<o:p></o:p></p>

<p class=MsoNormal>este se ejecuta desde algún terminal con el <span
class=GramE>comando<span style='mso-spacerun:yes'>  </span><span class=SpellE><b>npm</b></span></span><b>
run test</b><o:p></o:p></p>

<p class=MsoNormal><o:p>&nbsp;</o:p></p>

<p class=MsoNormal>y en el <span class=SpellE>backend</span> se <span
class=SpellE>implemento</span> <span class=GramE>un test</span> en el servicio <span
class=SpellE><b>MsProxyIndicesEconomicos</b></span> el cual se puede ejecutar
quitando la propiedad <b>&lt;<span class=SpellE>skipTests</span>&gt;</b> desde
el <b>pom.xml</b><o:p></o:p></p>

<p class=MsoNormal>o desde algún id como eclipse ejecutar la clase test con
Junit</p>

</div>

</body>
