<p><strong><span data-contrast="auto">GRUPO 6 — RPG por Turnos: Bloody Roar</span></strong></p>
<p><em><span data-contrast="auto"><span style="text-decoration: underline;"><strong>INTEGRANTES:</strong></span></span></em></p>
<ul>
<li><em><span data-contrast="auto">Culshaw, Federico: LU1174721</span></em></li>
<li><em><span data-contrast="auto">Costana lazo, Joselin: LU1218192</span></em></li>
<li><em><span data-contrast="auto">Bergantino, Adriel A.: LU1111637</span></em></li>
<li><em><span data-contrast="auto">Moragas, Catalina: LU1177331</span></em></li>
</ul>

<p><strong><span data-contrast="auto">Bloody Roar</span></strong></p>
<p><span data-contrast="auto">El juego es un RPG por turnos donde controlas a un grupo de combatientes (Party) que debe superar batallas consecutivas contra diversos enemigos.</span></p>
<p><span data-contrast="auto">No requiere navegación ni exploración libre de mapa, enfocándose 100% en la estrategia y la gestión de recursos en combate.</span></p>

<p><strong><span data-contrast="auto">Mecánica de Transformación (Sistema Bestia)</span></strong></p>
<p><span data-contrast="auto">Cada personaje dispone de un medidor de energía/maná.</span></p>
<p><span data-contrast="auto">Durante la batalla, el personaje puede consumir su recurso para activar la Transformación en Bestia.</span></p>
<p><span data-contrast="auto">Al transformarse, las estadísticas de ataque y defensa aumentan temporalmente y se habilita una técnica especial propia de su forma animal.</span></p>

<p><strong><span data-contrast="auto">Definición de la Party y Clases de Personajes</span></strong></p>
<p><span data-contrast="auto"><strong>Guerrero (Yugo - Forma Lobo):</strong> Especialista en daño físico y absorción de impacto. Posee estadísticas elevadas de vida y defensa.</span></p>
<p><strong><span data-contrast="auto">Atributos específicos:</span></strong></p>
<ul>
<li><span data-contrast="auto">escudoAbsorcion: cantidad de daño extra que puede absorber antes de que le bajen la vida real.</span></li>
<li><span data-contrast="auto">probabilidadBloqueo: porcentaje de probabilidad de bloquear o mitigar un ataque entrante.</span></li>
<li><span data-contrast="auto">furiaLobo: acumulador de daño extra que aumenta automáticamente a medida que le reducen la vida.</span></li>
</ul>
<p><strong><span data-contrast="auto">Métodos:</span></strong></p>
<p><span data-contrast="auto">ActivarFuriaLobo(vida_actual int): int — Este método genera un aumento del daño del personaje en función del porcentaje de vida:</span></p>
<ul>
<li><span data-contrast="auto">Vida_actual &lt; 5% = +5% de ataque</span></li>
<li><span data-contrast="auto">Vida_actual &lt; 25% = +1.5% de ataque</span></li>
<li><span data-contrast="auto">Vida_actual &lt; 50% = +0.5% de ataque</span></li>
</ul>

<p><span data-contrast="auto"><strong>Mago (Bakuryu - Forma Topo):</strong> Atacante táctico. Posee estadísticas elevadas de maná y velocidad, enfocado en habilidades de área y control de estado.</span></p>
<p><strong><span data-contrast="auto">Atributos específicos:</span></strong></p>
<ul>
<li><span data-contrast="auto">poderMagico: multiplicador que aumenta el daño de sus hechizos elementales y de área.</span></li>
<li><span data-contrast="auto">probabilidadEfectoEstado: porcentaje de probabilidad de aplicar estados negativos (ceguera, aturdimiento).</span></li>
<li><span data-contrast="auto">estaBajoTierra: estado booleano que le permite esquivar ataques completamente durante un turno.</span></li>
</ul>

<p><span data-contrast="auto"><strong>Arquero (Long - Forma Tigre):</strong> Atacante rápido a distancia. Posee alto nivel de ataque y velocidad.</span></p>
<p><strong><span data-contrast="auto">Atributos específicos:</span></strong></p>
<ul>
<li><span data-contrast="auto">probabilidadCritico: porcentaje de posibilidad de que un ataque a distancia aseste un golpe crítico.</span></li>
<li><span data-contrast="auto">multiplicadorCritico: daño extra que inflige cuando conecta un golpe crítico (ej. 1.5x o 2.0x).</span></li>
<li><span data-contrast="auto">cargasInstintoTigre: contador de marcas que acumula al atacar para activar una ráfaga de golpes consecutivos.</span></li>
</ul>

<p><span data-contrast="auto"><strong>Curador (Alice - Forma Conejo):</strong> Soporte del grupo. Su maná alto le permite restaurar puntos de vida y eliminar efectos de estado negativos en sus aliados.</span></p>
<p><strong><span data-contrast="auto">Atributos específicos:</span></strong></p>
<ul>
<li><span data-contrast="auto">potenciaCurativa: valor que aumenta la cantidad de vida que restauran sus hechizos de curación.</span></li>
<li><span data-contrast="auto">resistenciaEfectos: probabilidad de ignorar efectos de estado negativos.</span></li>
<li><span data-contrast="auto">BonificadorAgilidad: puntos extra de velocidad que le permiten acortar el tiempo entre sus turnos de soporte.</span></li>
</ul>

<p><strong><span data-contrast="auto">Sistema de enemigos</span></strong></p>
<p><span data-contrast="auto">Se define una clase padre Enemigo, que contiene atributos comunes:</span></p>
<ul>
<li><span data-contrast="auto">nombre</span></li>
<li><span data-contrast="auto">vida</span></li>
<li><span data-contrast="auto">ataque</span></li>
<li><span data-contrast="auto">defensa</span></li>
<li><span data-contrast="auto">velocidad</span></li>
</ul>
<p><span data-contrast="auto">A partir de esta clase se derivan las siguientes subclases:</span></p>
<ul>
<li><span data-contrast="auto">EnemigoVolador: mayor probabilidad de esquivar ataques físicos.</span></li>
<li><span data-contrast="auto">EnemigoBoss: estadísticas superiores, habilidades únicas y fases múltiples.</span></li>
<li><span data-contrast="auto">EnemigoTerrestre: enemigo estándar, sin ventajas especiales.</span></li>
</ul>

<p><strong><span data-contrast="auto">Sistema de Objetos y Economía</span></strong></p>
<p><span data-contrast="auto">El sistema cuenta con una clase padre <strong>Items</strong> que define la interfaz unificada de todos los elementos utilizables e inventariables.</span></p>
<p><span data-contrast="auto">Los ítems pueden ser de distintos tipos según su efecto (consumibles, equipables, etc.).</span></p>
<p><span data-contrast="auto">La clase <strong>Tienda</strong> actúa como gestor comercial y modula el intercambio entre el jugador y el catálogo global de objetos.</span></p>

<p><strong><span data-contrast="auto">Efectos de Estado</span></strong></p>
<p><span data-contrast="auto">La clase <strong>EfectoEstado</strong> encapsula alteraciones temporales que pueden aplicarse durante el combate, tanto beneficiosas (buffs) como perjudiciales (debuffs).</span></p>
<p><span data-contrast="auto">Cada efecto tiene una duración determinada y modifica el comportamiento del personaje que lo posee mientras esté activo.</span></p>

<p><strong><span data-contrast="auto">Sistema de Inventario</span></strong></p>
<p><span data-contrast="auto">La party dispone de un inventario compartido donde se almacenan los ítems obtenidos durante las batallas.</span></p>
<p><span data-contrast="auto">Los ítems pueden ser de distintos tipos:</span></p>
<ul>
<li><span data-contrast="auto">Consumibles: restauran vida o maná al ser utilizados durante un turno.</span></li>
<li><span data-contrast="auto">Equipables: otorgan bonificaciones permanentes de ataque o defensa mientras estén equipados.</span></li>
</ul>
<p><span data-contrast="auto">Cada ítem produce un efecto coherente sobre el estado del personaje que lo usa.</span></p>

<p><strong><span data-contrast="auto">Reglas del Sistema de Combate</span></strong></p>
<p><span data-contrast="auto"><strong>Determinación del Turno:</strong> Se calcula el orden de actuación comparando la variable velocidad de todos los combatientes activos.</span></p>
<p><span data-contrast="auto"><strong>Acciones por Turno:</strong> El jugador selecciona en la interfaz una acción: atacar, defender, usar habilidad (descontando manaActual) o consumir un Item del inventario.</span></p>
<p><span data-contrast="auto"><strong>Fin del Combate y Experiencia:</strong> Si la vida de todos los enemigos llega a 0, la party recibe experiencia. Si la variable experiencia supera el límite, se ejecuta el método subirDeNivel(), el cual incrementa de forma permanente las estadísticas del personaje.</span></p>

<p><strong><span data-contrast="auto">Sistema de Progresión</span></strong></p>
<p><span data-contrast="auto">Cada personaje acumula experiencia al finalizar cada batalla.</span></p>
<p><span data-contrast="auto">Cuando la experiencia alcanza el límite definido para su nivel actual, se ejecuta el método <strong>subirDeNivel()</strong>, que incrementa de forma permanente una o más estadísticas del personaje (vida, maná, ataque, defensa o velocidad).</span></p>
<p><span data-contrast="auto">El nivel máximo y la curva de experiencia quedan a definir durante la implementación.</span></p>

<p><strong><span data-contrast="auto">Recompensas</span></strong></p>
<p><span data-contrast="auto">Al finalizar una batalla, la party recibe como recompensa principal experiencia.</span></p>
<p><span data-contrast="auto">Adicionalmente, el sistema contempla la posibilidad de otorgar ítems u oro, que se incorporan al inventario de la party.</span></p>

<p><strong><span data-contrast="auto">Cierre</span></strong></p>
<p><span data-contrast="auto">El presente documento define las bases del sistema Bloody Roar: sus personajes, enemigos, mecánicas de combate, sistema de transformación, inventario, objetos, efectos de estado, progresión y recompensas.</span></p>
<p><span data-contrast="auto">El diseño presentado servirá como punto de partida para la implementación de la Fase B, donde se desarrollará el motor de combate, la interfaz gráfica y la persistencia de partidas.</span></p>
<p><span data-contrast="auto">Los diagramas de clases y de secuencia incluidos en este documento reflejan las decisiones de diseño tomadas por el grupo y serán la base para el desarrollo del código.</span></p>

<p><strong><span data-contrast="auto">Propuesta Visual Inicial</span></strong></p>
<p><span data-contrast="auto">Se presentan las pantallas principales del juego, que servirán de base para la implementación de la interfaz gráfica en la Fase B.</span></p>

<p><strong><span data-contrast="auto">Pantalla Inicial</span></strong></p>
<p><img src="https://github.com/cmoragasuade/POO_-Bloody-ROAR/blob/main/src/resources/inicio.png.png" /></p>

<p><strong><span data-contrast="auto">Pantalla de Estado de la Party</span></strong></p>
<p><img src="https://github.com/cmoragasuade/POO_-Bloody-ROAR/blob/main/src/resources/party.png.png" /></p>

<p><strong><span data-contrast="auto">Pantalla de Batalla</span></strong></p>
<p><img src="https://github.com/cmoragasuade/POO_-Bloody-ROAR/blob/main/src/resources/bloody roar fight.jpeg" /></p>

<p><strong><span data-contrast="auto">Pantalla de Resultados</span></strong></p>
<p><img src="https://github.com/cmoragasuade/POO_-Bloody-ROAR/blob/main/src/resources/resultados.png.png" /></p>
