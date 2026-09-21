
# TDA Gestor de Impresiones

## Descripción

Simulador de un **gestor de impresiones** desarrollado en **Java** como ejercicio de
**Estructura de Datos** (APE de Listas Secuenciales: Pilas y Colas - caso básica).
El objetivo pedagógico es aplicar las estructuras **Cola (FIFO)** y **Pila (LIFO)**
usando la interfaz nativa `Deque&lt;String&gt;` de Java (`ArrayDeque`), **no** construirlas
desde cero.

- Una **cola** conserva los documentos pendientes: el primero que entra es el primero
  que se imprime.
- Una **pila** registra los documentos ya impresos para permitir una recuperación:
  el último impreso es el primero en recuperarse.

### Enunciado

&gt; Una cola conserva los documentos pendientes. Una pila registra los documentos ya
&gt; impresos para permitir una recuperación. Se debe usar `Deque&lt;String&gt;` para ambas
&gt; variables, pero respetando el contrato de cada estructura.
&gt;
&gt; | Acción | Estructura | Operación sugerida | Resultado |
&gt; |---|---|---|---|
&gt; | Registrar documento | pendientes | `offerLast(nombre)` | Entra al final |
&gt; | Imprimir siguiente | pendientes | `pollFirst()` | Sale el más antiguo |
&gt; | Guardar impresión | historial | `push(nombre)` | Queda en la cima |
&gt; | Recuperar última | historial y pendientes | `pop()` y `addFirst()` | Vuelve al frente |
&gt;
&gt; Validar estructuras vacías antes de retirar. Probar al menos seis operaciones
&gt; combinadas. Entregar código, salida y traza manual.

## Estructura del proyecto

```
.
├── src/
│   └── GestorImpresiones.java      # Clase principal (menú + lógica del TDA)
├── diagramas/
│   ├── diagrama-clases.md          # Diagrama de clases en Mermaid
│   └── diagrama-clases.png         # Imagen exportada del diagrama
└── README.md
```

## Requisitos

- JDK 8 o superior
- (Opcional) IntelliJ IDEA / Eclipse / VS Code

## Compilación y ejecución

```bash
# Compilar
javac GestorImpresiones.java

# Ejecutar
java GestorImpresiones
```

## Uso

El programa muestra un menú interactivo en consola:

```
========================================
       GESTOR DE IMPRESIONES (MENU)
========================================
1. Registrar documento (Entra al final)
2. Imprimir siguiente (Sale el más antiguo)
3. Guardar impresión (Queda en la cima)
4. Recuperar última (Vuelve al frente)
5. Mostrar estado actual
6. Salir
Seleccione una opción:
```

### Ejemplo de sesión (operaciones combinadas)

```
1 → Informe.pdf    (pendientes: [Informe.pdf])
1 → Tarea.docx     (pendientes: [Informe.pdf, Tarea.docx])
2                  (imprime Informe.pdf → historial: [Informe.pdf])
1 → Foto.png       (pendientes: [Tarea.docx, Foto.png])
2                  (imprime Tarea.docx → historial: [Tarea.docx, Informe.pdf])
4                  (recupera Tarea.docx → pendientes: [Tarea.docx, Foto.png])
2                  (reimprime Tarea.docx)
5                  (muestra estado de ambas estructuras)
```

## Diseño del TDA

### Valores
G = (P, H), donde:
- **P**: secuencia de documentos pendientes (orden **FIFO**).
- **H**: historial de documentos impresos (orden **LIFO**).

Ambas se representan con `Deque&lt;String&gt;` (implementación `ArrayDeque`):
sobre **P** solo se usan operaciones de **extremos opuestos** (`offerLast`/`pollFirst`)
y sobre **H** solo operaciones del **mismo extremo** (`push`/`pop`).

### Operaciones

| Operación | Firma | Precondición | Postcondición |
|---|---|---|---|
| crear | `crear() → GestorImpresiones` | — | P = ∅ ∧ H = ∅ |
| registrarDocumento | `registrar(G, nombre)` | nombre ≠ "" | P = P' + nombre |
| imprimirSiguiente | `imprimir(G) → doc` | P ≠ ∅ | doc = frente(P'); P pierde el frente; H apila doc |
| guardarImpresion | `guardar(G, nombre)` | nombre ≠ "" | H apila nombre |
| recuperarUltima | `recuperar(G) → doc` | H ≠ ∅ | doc = cima(H'); H desapila; P inserta doc al frente |
| hayPendientes / hayHistorial | `→ boolean` | — | P ≠ ∅ / H ≠ ∅ |

### Invariantes

- Sobre `pendientes` solo se usan `offerLast`/`pollFirst`: nunca `push`/`pop` (FIFO).
- Sobre `historial` solo se usan `push`/`pop`: nunca `offerLast`/`pollFirst` (LIFO).
- Ningún retiro se ejecuta sin validar `isEmpty()` primero.
- Colas y pilas son dinámicas: sin tope ni capacidad máxima.
- Los nombres de documentos no se aceptan vacíos (`trim().isEmpty()`).


**Asignatura:** Estructura de Datos — **Docente:** Ing. José Caiza
**Universidad Técnica de Ambato — Carrera de Software — 3-B**
