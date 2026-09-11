# POO_-Bloody-ROAR
 
Bloody Roar: es un videojuego de rol por turnos en el que el jugador controla a un grupo de combatientes (Party) que deben superar batallas consecutivas contra diversos enemigos. No requiere navegación ni exploración libre de mapa, enfocándose 100% en la estrategia y la gestión de recursos en combate.  

-Mecánica de Transformación (Sistema Bestia) 

Cada personaje dispone de un medidor de energía/maná.   

Durante la batalla, el personaje puede consumir su recurso para activar la Transformación en Bestia.   

Al transformarse, las estadísticas de ataque y defensa aumentan temporalmente y se habilita una técnica especial propia de su forma animal.   

 
Definición de la Party y Clases de Personajes 

Guerrero (Yugo - Forma Lobo): Especialista en daño físico y absorción de impacto. Posee estadísticas elevadas de vida y defensa.   

Atributos específicos:  

escudoAbsorcion: cantidad de daño extra que puede absorber antes de que le bajen la vida real. 

probabilidadBloqueo: porcentaje de probabilidad de bloquear o mitigar un ataque entrante. 

furiaLobo: acumulador de daño extra que aumenta automáticamente a medida que le reducen la vida. 


Metodos: 

ActivarFuriaLobo(vida_actual int): int : Este método genera un aumento del daño del personaje en función del porcentaje de vida 

Vida_actual < 5% = +5% de ataque  

Vida_actual < 25% = +1.5% de ataque 

Vida_actual < 50% = +0.5% de ataque 

 

Mago (Bakuryu - Forma Topo): Atacante táctico. Posee estadísticas elevadas de maná y velocidad, enfocado en habilidades de área y control de estado.   

Atributos específicos: 

poderMagico: multiplicador que aumenta el daño de sus hechizos elementales y de área. 

probabilidadEfectoEstado: porcentaje de probabilidad de aplicar estados negativos (ceguera, aturdimiento) 

estaBajoTierra: estado booleano que le permite esquivar ataques completamente durante un turno. 

 

Arquero (Long - Forma Tigre): Atacante rápido a distancia. Posee alto nivel de ataque y velocidad.   

Atributos específicos:  

probabilidadCritico: porcentaje de posibilidad de que un ataque a distancia aseste un golpe crítico. 

multiplicadorCritico: daño extra que inflige cuando conecta un golpe critico (ej. 1.5x o 2.0x). 

cargasInstintoTigre: contador de marcas que acumula al atacar para activar una ráfaga de golpes consecutivos. 

 

Curador (Alice - Forma Conejo): Soporte del grupo. Su maná alto le permite restaurar puntos de vida y eliminar efectos de estado negativos en sus aliados.   

Atributos especificos:  

potenciaCurativa: valor que aumenta la cantidad de vida que restauran sus hechizos de curación. 

resistenciaEfectos:probabilidad de ignorar efectos de estado negativos. 

BonificadorAgilidad: puntos extra de velocidad que le permiten acortar el tiempo entre sus turnos de soporte. 

 

Sistema de enemigos: 

Se define una clase padre Enemigo, que contiene atributos comunes: 

nombre 

vida 

ataque 

defensa 

velocidad 

 

A partir de esta clase se derivan las siguientes subclases: 

EnemigoVolador: mayor probabilidad de esquivar ataques físicos. 

EnemigoBoss: estadísticas superiores, habilidades únicas y fases múltiples. 

EnemigoTerrestre: enemigo estándar, sin ventajas especiales. 

 

Reglas del Sistema de Combate 

Determinación del Turno: Se calcula el orden de actuación comparando la variable velocidad de todos los combatientes activos.   

Acciones por Turno: El jugador selecciona en la interfaz una acción: atacar, defender, usar habilidad (descontando manaActual) o consumir un Item del inventario.   

Fin del Combate y Experiencia: Si la vida de todos los enemigos llega a 0, la party recibe experiencia. Si la variable experiencia supera el límite, se ejecuta el método subirDeNivel(), el cual incrementa de forma permanente las estadísticas del personaje.   

 

 

 

 

 
