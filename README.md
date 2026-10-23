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
<p><span data-contrast="auto">La party estará conformada por un mínimo de 2 y un máximo de 4 personajes. Al iniciar una nueva partida, el jugador deberá seleccionar cuáles de los 4 combatientes disponibles formarán parte de su equipo inicial.</span></p>

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
<p><span data-contrast="auto"><strong>Habilidad - Furia Lobo:</strong> Costo: 15 Maná. Efecto: Aumenta el ataque según la fórmula detallada por 3 turnos.</span></p>

<p><span data-contrast="auto"><strong>Mago (Bakuryu - Forma Topo):</strong> Atacante táctico. Posee estadísticas elevadas de maná y velocidad, enfocado en habilidades de área y control de estado.</span></p>
<p><strong><span data-contrast="auto">Atributos específicos:</span></strong></p>
<ul>
<li><span data-contrast="auto">poderMagico: multiplicador que aumenta el daño de sus hechizos elementales y de área.</span></li>
<li><span data-contrast="auto">probabilidadEfectoEstado: porcentaje de probabilidad de aplicar estados negativos (ceguera, aturdimiento).</span></li>
<li><span data-contrast="auto">estaBajoTierra: estado booleano que le permite esquivar ataques completamente durante un turno.</span></li>
</ul>
<p><span data-contrast="auto"><strong>Habilidad - Topo Ciego:</strong> Costo: 20 Maná. Efecto: Inflige daño mágico puro (ignora defensa) y tiene un 30% de probabilidad de aplicar el estado "Cegado".</span></p>

<p><span data-contrast="auto"><strong>Arquero (Long - Forma Tigre):</strong> Atacante rápido a distancia. Posee alto nivel de ataque y velocidad.</span></p>
<p><strong><span data-contrast="auto">Atributos específicos:</span></strong></p>
<ul>
<li><span data-contrast="auto">probabilidadCritico: porcentaje de posibilidad de que un ataque a distancia aseste un golpe crítico.</span></li>
<li><span data-contrast="auto">multiplicadorCritico: daño extra que inflige cuando conecta un golpe crítico (ej. 1.5x o 2.0x).</span></li>
<li><span data-contrast="auto">cargasInstintoTigre: contador de marcas que acumula al atacar para activar una ráfaga de golpes consecutivos.</span></li>
</ul>
<p><span data-contrast="auto"><strong>Habilidad - Ráfaga de Tigre:</strong> Costo: 10 Maná. Efecto: Realiza 3 ataques consecutivos. Si los 3 impactan, el tercero es crítico asegurado.</span></p>

<p><span data-contrast="auto"><strong>Curador (Alice - Forma Conejo):</strong> Soporte del grupo. Su maná alto le permite restaurar puntos de vida y eliminar efectos de estado negativos en sus aliados.</span></p>
<p><strong><span data-contrast="auto">Atributos específicos:</span></strong></p>
<ul>
<li><span data-contrast="auto">potenciaCurativa: valor que aumenta la cantidad de vida que restauran sus hechizos de curación.</span></li>
<li><span data-contrast="auto">resistenciaEfectos: probabilidad de ignorar efectos de estado negativos.</span></li>
<li><span data-contrast="auto">BonificadorAgilidad: puntos extra de velocidad que le permiten acortar el tiempo entre sus turnos de soporte.</span></li>
</ul>
<p><span data-contrast="auto"><strong>Habilidad - Curación Conejo:</strong> Costo: 25 Maná. Efecto: Cura 30 de vida a toda la Party y limpia efectos negativos.</span></p>

<p><strong><span data-contrast="auto">Sistema de enemigos</span></strong></p>
<p><span data-contrast="auto">Tanto los Personajes como los Enemigos heredan de una clase abstracta superior llamada <strong>Entidad</strong>, la cual concentra las estadísticas base (nombre, vida, ataque, defensa, velocidad) y métodos comunes (recibirDaño, estaVivo, atacar) para permitir el polimorfismo en el motor de batalla.</span></p>
<p><span data-contrast="auto">A partir de la clase Enemigo se derivan las siguientes subclases:</span></p>
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

<p><strong><span data-contrast="auto">Reglas del Sistema de Combate (Fórmulas)</span></strong></p>
<p><span data-contrast="auto"><strong>Cálculo de Daño Físico:</strong> Daño = (Ataque del Atacante + Bonus de Arma) - Defensa del Defensor. Si el resultado es menor a 1, el daño será siempre 1.</span></p>
<p><span data-contrast="auto"><strong>Acción Defender:</strong> Al seleccionar defender, el personaje suma un 50% extra a su estadística de Defensa hasta que sea su turno nuevamente.</span></p>
<p><span data-contrast="auto"><strong>Determinación del Turno:</strong> Se utiliza la clase Batalla que arma una colaTurnos (List&lt;Entidad&gt;). Se ordenan de mayor a menor según el atributo velocidad. Empates se resuelven aleatoriamente.</span></p>
<p><span data-contrast="auto"><strong>Acciones por Turno:</strong> El jugador selecciona en la interfaz una acción: atacar, defender, usar habilidad (descontando manaActual) o consumir un Item del inventario.</span></p>
<p><span data-contrast="auto"><strong>Fin del Combate y Experiencia:</strong> Si la vida de todos los enemigos llega a 0, la party recibe experiencia.</span></p>

<p><strong><span data-contrast="auto">Sistema de Progresión</span></strong></p>
<p><span data-contrast="auto"><strong>Fórmula de Experiencia:</strong> La experiencia necesaria para subir del nivel N al nivel N+1 se calcula como: Nivel Actual * 100. (Ej: Nivel 1 a 2 = 100 XP; Nivel 2 a 3 = 200 XP).</span></p>
<p><span data-contrast="auto"><strong>Subida de Nivel:</strong> Cuando un personaje acumula la XP necesaria (método verificarSubidaNivel()), ejecuta subirNivel(). Esto aumenta permanentemente su Vida Máxima en 20, su Maná en 10 y su Ataque en 5. Además, su vida y maná se restauran al 100%.</span></p>

<p><strong><span data-contrast="auto">Persistencia (Guardado de Partida)</span></strong></p>
<p><span data-contrast="auto">El juego permitirá guardar el estado de la partida en un archivo local mediante la clase <strong>GameManager</strong>. Se guarda el estado de la Party (niveles, XP, vida actual, oro, inventario, armas equipadas) y el progreso de las salas superadas.</span></p>
<p><span data-contrast="auto"><strong>Restricción:</strong> El jugador solo podrá guardar fuera de combate (en la pantalla de estado o menú principal). No se permite guardar en medio de una batalla para evitar inconsistencias de estado.</span></p>

<p><strong><span data-contrast="auto">Recompensas</span></strong></p>
<p><span data-contrast="auto">Al finalizar una batalla, la party recibe como recompensa principal experiencia.</span></p>
<p><span data-contrast="auto">Adicionalmente, el sistema contempla la posibilidad de otorgar ítems u oro, que se incorporan al inventario de la party.</span></p>

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
