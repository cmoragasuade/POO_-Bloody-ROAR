# GRUPO 6 — RPG por Turnos: Bloody Roar

**Paradigma Orientado a Objetos — UADE · Trabajo Práctico Integrador**

## Integrantes

- Culshaw, Federico: LU1174721
- Costana lazo, Joselin: LU1218192
- Bergantino, Adriel A.: LU1111637
- Moragas, Catalina: LU1177331

---

## Bloody Roar

El juego es un RPG por turnos donde controlas a un grupo de combatientes (Party) que debe superar batallas consecutivas contra diversos enemigos.

No requiere navegación ni exploración libre de mapa, enfocándose 100% en la estrategia y la gestión de recursos en combate.

## Cómo ejecutar el proyecto

1. Clonar el repositorio:
   ```
   git clone https://github.com/cmoragasuade/POO_-Bloody-ROAR.git
   ```
2. Importarlo en Eclipse: *File → Import → Existing Projects into Workspace* y seleccionar la carpeta clonada.
3. Verificar que la carpeta `src` esté configurada como *Source Folder* (las imágenes se cargan desde `src/resources`).
4. Ejecutar la clase `main.Main` (*Run As → Java Application*).

## Arquitectura (MVC)

El proyecto separa la lógica de negocio de la interfaz gráfica:

| Paquete | Responsabilidad |
|---|---|
| `vista` | Pantallas Swing (inicio, selección de party, estado de la party, batalla y resultados). Solo muestran información y le avisan al controlador qué se tocó. No contienen reglas de negocio. |
| `vista.paneles` | Componentes reutilizables de la vista (`PanelFondo`, `PanelEstadoEntidad`, `PanelRegistro`). |
| `controlador` | `GameManager` coordina el flujo del juego y la persistencia; `ControladorBatalla` recibe las acciones de la pantalla de batalla. |
| `dto` | Objetos de transferencia de datos (`EstadoBatallaDTO`, `EstadoPartidaDTO`, `ResultadoBatallaDTO`, `EntidadDTO`, `ItemDTO`) con los datos que la vista necesita mostrar, sin exponer el modelo. |
| `util` | Utilidades de apoyo (por ejemplo, carga de imágenes). |

Flujo de una acción: el usuario interactúa con la vista → la vista notifica al controlador → el controlador llama al modelo → el modelo cambia su estado → la vista se actualiza con un DTO nuevo.

---

## Mecánica de Transformación (Sistema Bestia)

Cada personaje dispone de un medidor de energía/maná.

Durante la batalla, el personaje puede consumir su recurso para activar la Transformación en Bestia.

Al transformarse, las estadísticas de ataque y defensa aumentan temporalmente y se habilita una técnica especial propia de su forma animal.

## Definición de la Party y Clases de Personajes

La party estará conformada por un mínimo de 2 y un máximo de 4 personajes. Al iniciar una nueva partida, el jugador deberá seleccionar cuáles de los 4 combatientes disponibles formarán parte de su equipo inicial.

### Guerrero (Yugo - Forma Lobo)

Especialista en daño físico y absorción de impacto. Posee estadísticas elevadas de vida y defensa.

**Atributos específicos:**

- `escudoAbsorcion`: cantidad de daño extra que puede absorber antes de que le bajen la vida real.
- `probabilidadBloqueo`: porcentaje de probabilidad de bloquear o mitigar un ataque entrante.
- `furiaLobo`: acumulador de daño extra que aumenta automáticamente a medida que le reducen la vida.

**Métodos:**

`ActivarFuriaLobo(vida_actual int): int` — Este método genera un aumento del daño del personaje en función del porcentaje de vida:

- Vida_actual < 5% = +5% de ataque
- Vida_actual < 25% = +1.5% de ataque
- Vida_actual < 50% = +0.5% de ataque

**Habilidad - Furia Lobo:** Costo: 15 Maná. Efecto: Aumenta el ataque según la fórmula detallada por 3 turnos.

### Mago (Bakuryu - Forma Topo)

Atacante táctico. Posee estadísticas elevadas de maná y velocidad, enfocado en habilidades de área y control de estado.

**Atributos específicos:**

- `poderMagico`: multiplicador que aumenta el daño de sus hechizos elementales y de área.
- `probabilidadEfectoEstado`: porcentaje de probabilidad de aplicar estados negativos (ceguera, aturdimiento).
- `estaBajoTierra`: estado booleano que le permite esquivar ataques completamente durante un turno.

**Habilidad - Topo Ciego:** Costo: 20 Maná. Efecto: Inflige daño mágico puro (ignora defensa) y tiene un 30% de probabilidad de aplicar el estado "Cegado".

### Arquero (Long - Forma Tigre)

Atacante rápido a distancia. Posee alto nivel de ataque y velocidad.

**Atributos específicos:**

- `probabilidadCritico`: porcentaje de posibilidad de que un ataque a distancia aseste un golpe crítico.
- `multiplicadorCritico`: daño extra que inflige cuando conecta un golpe crítico (ej. 1.5x o 2.0x).
- `cargasInstintoTigre`: contador de marcas que acumula al atacar para activar una ráfaga de golpes consecutivos.

**Habilidad - Ráfaga de Tigre:** Costo: 10 Maná. Efecto: Realiza 3 ataques consecutivos. Si los 3 impactan, el tercero es crítico asegurado.

### Curador (Alice - Forma Conejo)

Soporte del grupo. Su maná alto le permite restaurar puntos de vida y eliminar efectos de estado negativos en sus aliados.

**Atributos específicos:**

- `potenciaCurativa`: valor que aumenta la cantidad de vida que restauran sus hechizos de curación.
- `resistenciaEfectos`: probabilidad de ignorar efectos de estado negativos.
- `BonificadorAgilidad`: puntos extra de velocidad que le permiten acortar el tiempo entre sus turnos de soporte.

**Habilidad - Curación Conejo:** Costo: 25 Maná. Efecto: Cura 30 de vida a toda la Party y limpia efectos negativos.

## Sistema de enemigos

Tanto los Personajes como los Enemigos heredan de una clase abstracta superior llamada **Entidad**, la cual concentra las estadísticas base (nombre, vida, ataque, defensa, velocidad) y métodos comunes (`recibirDaño`, `estaVivo`, `atacar`) para permitir el polimorfismo en el motor de batalla.

A partir de la clase Enemigo se derivan las siguientes subclases:

- **EnemigoVolador:** mayor probabilidad de esquivar ataques físicos.
- **EnemigoBoss:** estadísticas superiores, habilidades únicas y fases múltiples.
- **EnemigoTerrestre:** enemigo estándar, sin ventajas especiales.

## Sistema de Objetos y Economía

El sistema cuenta con una clase padre **Items** que define la interfaz unificada de todos los elementos utilizables e inventariables.

Los ítems pueden ser de distintos tipos según su efecto (consumibles, equipables, etc.).

La clase **Tienda** actúa como gestor comercial y modula el intercambio entre el jugador y el catálogo global de objetos.

## Efectos de Estado

La clase **EfectoEstado** encapsula alteraciones temporales que pueden aplicarse durante el combate, tanto beneficiosas (buffs) como perjudiciales (debuffs).

Cada efecto tiene una duración determinada y modifica el comportamiento del personaje que lo posee mientras esté activo.

## Sistema de Inventario

La party dispone de un inventario compartido donde se almacenan los ítems obtenidos durante las batallas.

Los ítems pueden ser de distintos tipos:

- **Consumibles:** restauran vida o maná al ser utilizados durante un turno.
- **Equipables:** otorgan bonificaciones permanentes de ataque o defensa mientras estén equipados.

Cada ítem produce un efecto coherente sobre el estado del personaje que lo usa.

## Reglas del Sistema de Combate (Fórmulas)

- **Cálculo de Daño Físico:** Daño = (Ataque del Atacante + Bonus de Arma) - Defensa del Defensor. Si el resultado es menor a 1, el daño será siempre 1.
- **Acción Defender:** Al seleccionar defender, el personaje suma un 50% extra a su estadística de Defensa hasta que sea su turno nuevamente.
- **Determinación del Turno:** Se utiliza la clase Batalla que arma una `colaTurnos` (`List<Entidad>`). Se ordenan de mayor a menor según el atributo velocidad. Empates se resuelven aleatoriamente.
- **Acciones por Turno:** El jugador selecciona en la interfaz una acción: atacar, defender, usar habilidad (descontando `manaActual`) o consumir un Item del inventario.
- **Fin del Combate y Experiencia:** Si la vida de todos los enemigos llega a 0, la party recibe experiencia.

## Sistema de Progresión

- **Fórmula de Experiencia:** La experiencia necesaria para subir del nivel N al nivel N+1 se calcula como: Nivel Actual * 100. (Ej: Nivel 1 a 2 = 100 XP; Nivel 2 a 3 = 200 XP).
- **Subida de Nivel:** Cuando un personaje acumula la XP necesaria (método `verificarSubidaNivel()`), ejecuta `subirNivel()`. Esto aumenta permanentemente su Vida Máxima en 20, su Maná en 10 y su Ataque en 5. Además, su vida y maná se restauran al 100%.

## Persistencia (Guardado de Partida)

El juego permite guardar el estado de la partida en un archivo local mediante la clase **GameManager**. Se guarda el estado de la Party (niveles, XP, vida actual, oro, inventario, armas equipadas) y el progreso de las salas superadas.

**Restricción:** El jugador solo puede guardar fuera de combate (en la pantalla de estado de la party). No se permite guardar en medio de una batalla para evitar inconsistencias de estado.

## Recompensas

Al finalizar una batalla, la party recibe como recompensa principal experiencia.

Adicionalmente, el sistema contempla la posibilidad de otorgar ítems u oro, que se incorporan al inventario de la party.

---

## Pantallas del juego

Capturas de la aplicación en ejecución (Fase B).

### Pantalla Inicial

![Pantalla inicial](docs/captura_inicio.png)

### Pantalla de Estado de la Party

![Estado de la party](docs/captura_party.png)

### Pantalla de Batalla

![Pantalla de batalla](docs/captura_batalla.png)

### Pantalla de Resultados

![Pantalla de resultados](docs/captura_resultados.png)

---

## Documentación de diseño (Fase A)

- Diagrama de clases: `DDC.jpeg`
- Diagramas de secuencia: `DDS1..txt` y `DDS2..txt`
- Documento de diseño: `BLOODY ROAR.pdf`
