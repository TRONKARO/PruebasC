# UADE-2026-PROGRA-II — Sistema de Operaciones para Micros

## Integrantes

- Felipe Troncaro
- Franco Bonorino

## Descripción

Sistema de gestión para una empresa de transporte terrestre. Incluye planificación de rutas entre terminales argentinas, gestión de flota de micros, priorización de viajes, análisis de conexiones y simulación de operaciones vía consola.

## TDAs utilizados y justificación

### Grafo dirigido (`GraphADT`) — terminales y rutas

Las terminales son vértices del grafo y cada ruta entre dos ciudades es una arista dirigida con peso (distancia en km). Se eligió un **grafo dirigido** porque una ruta BUE → COR no implica necesariamente la existencia de COR → BUE, y el peso permite modelar distancias reales. El grafo permite aplicar DFS para encontrar todos los caminos posibles entre dos terminales con un máximo de paradas, algo que con una lista o diccionario sería mucho más difícil de implementar.

### Cola de prioridad (`PriorityQueueADT`) — viajes

Los viajes se encolan según su nivel de urgencia (prioridad numérica). Se eligió una **cola de prioridad** porque el sistema debe atender primero los viajes más urgentes, independientemente del orden en que fueron cargados. A diferencia de una cola FIFO, este TDA garantiza que `getElement()` siempre devuelva el viaje de mayor prioridad, lo cual es fundamental para la operación real de una empresa de transporte.

### Diccionario simple (`SimpleDictionaryADT`) — micros por patente

Cada micro se identifica unívocamente por su patente. Se eligió un **diccionario simple** (clave → valor único) para poder acceder, actualizar o verificar la existencia de un micro en O(n) sin necesidad de recorrer toda la estructura. La clave es la patente y el valor es el objeto `Micro`.

### Diccionario múltiple (`MultipleDictionaryADT`) — asignaciones de viajes a micros

Un mismo micro puede tener varios viajes asignados a lo largo del tiempo. Se eligió un **diccionario múltiple** (clave → lista de valores) porque permite asociar una clave (patente del micro) con una colección dinámica de viajes, sin necesidad de predefinir cuántos viajes tendrá cada micro. Un diccionario simple no alcanzaba porque el valor no puede ser único.

## Consideraciones

- No se utilizaron interfaces ni implementaciones de TDAs propias de Java (`List`, `Map`, `Stack`, `Set`, `Tree`).
- Todas las implementaciones son dinámicas (basadas en nodos enlazados).
- Las excepciones son específicas al dominio: `ElementNotFoundException`, `EmptyStructureException`, `DuplicateElementException`, `FullStructureException`, `InvalidIndexException`.
- El proyecto fue creado con Maven.
