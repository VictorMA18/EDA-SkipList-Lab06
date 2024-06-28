# :skull: <samp>Data Structures Implementation: Skip List & Splay Tree</samp>

Este repositorio contiene la implementación de dos estructuras de datos avanzadas en Java: una `Skip List` y un `Splay Tree`. Ambas estructuras están diseñadas para ofrecer operaciones eficientes de búsqueda, inserción y eliminación.

## <samp>Skip List</samp>
La `Skip List` es una estructura de datos probabilística que permite operaciones rápidas de búsqueda, inserción y eliminación. Funciona mediante múltiples niveles de punteros que permiten saltar sobre grandes segmentos de la lista enlazada, proporcionando un tiempo de operación promedio de \(O(\log n)\).

### :hammer_and_pick: <samp>Características Principales</samp>

- **Búsqueda Eficiente**: Usa múltiples niveles para realizar búsquedas rápidas.
- **Inserción y Eliminación**: Mantiene un balance probabilístico para operaciones rápidas.
- **Iterador Incorporado**: Permite recorrer la lista fácilmente.

### :wrench: <samp>Ejecución</samp>

> **Compilación**: Usa el siguiente comando para compilar el archivo `SkipList.java`.

```sh
javac SkipList.java
```

> **Ejecución**: Ejecuta el archivo compilado.

```sh
java SkipList
```

## <samp>Splay Tree</samp>

El `Splay Tree` es una estructura de datos autoajustable que realiza operaciones de búsqueda, inserción y eliminación de manera eficiente. Después de cada operación, el árbol se ajusta de manera que el nodo recién accedido se mueve a la raíz, mejorando la eficiencia de accesos futuros a elementos recientemente utilizados.

### :hammer_and_pick: <samp>Características Principales</samp>

- **Autoajustable**: Optimiza el acceso a elementos recientemente usados.
- **Operaciones Eficientes**: Búsqueda, inserción y eliminación en \(O(\log n)\) amortizado.
- **Visualización Gráfica**: Usa la biblioteca `GraphStream` para representar el árbol gráficamente.

### :wrench: <samp>Ejecución</samp>

> **Compilación**: Usa el siguiente comando para compilar los archivos `SplayTree.java` y `TestSplayTree.java`.

```sh
javac -cp .:jar/gs-algo-2.0.jar:jar/gs-core-2.0.jar:jar/gs-ui-swing-2.0.jar TestSplayTree.java
```

> **Ejecución**: Ejecuta el archivo compilado.

```sh
java -cp .:jar/gs-algo-2.0.jar:jar/gs-core-2.0.jar:jar/gs-ui-swing-2.0.jar TestSplayTree
```

> :warning: **Visualización**: Asegúrate de tener la biblioteca `GraphStream` en tu CLASSPATH para ver la representación gráfica del `Splay Tree`.
