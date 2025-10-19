
# Grep - Descripción

Repositorio en GitHub: [https://github.com/MALASBALAS/GREP_LS](https://github.com/MALASBALAS/GREP_LS)

Programa *Java* que usa `grep -i psp` para filtrar líneas que contienen "psp" (sin distinguir mayúsculas/minúsculas) desde un array de cadenas. Envía las líneas al proceso `grep` y muestra las coincidencias o un mensaje si no hay ninguna.

**Claves:**

- Uso de `Process` para ejecutar y comunicar con procesos externos.
- Ejemplo simple de entrada/salida entre Java y un comando externo.
- Código comentado para fácil comprensión.

**Constantes**
Preaparado para modificar las siguientes constantes en el código:

- `MSG_ERROR`: Mensaje mostrado si ocurre un error al ejecutar el comando externo.
- `ATRIBUTO1`: Primer argumento para grep (por ejemplo, `-i` para ignorar mayúsculas/minúsculas; puedes dejarlo vacío si no quieres opciones).
- `ATRIBUTO2`: Palabra que se busca en las líneas de texto.
- `COMANDO`: Array con el comando y argumentos que se pasan al proceso `grep` (puedes modificar el orden o añadir más argumentos si lo necesitas).

## Ejemplo de salida

```text
Success!
Me gusta PSP y java
PSP se programa en java
y se programa de forma concurrente en PSP
PSP es programación.
```

Álvaro Balas Jiménez

## Cómo ejecutar

Compilar y ejecutar con maven:

```bash
mvn test            # compila y ejecuta tests
mvn -q -DskipTests=false -f pom.xml test
```

O ejecutar la clase `Main` (desde la raíz del proyecto):

```bash
mvn -q -DskipTests -f pom.xml package
java -cp target/classes:target/test-classes:. xyz.balbe.Main
```

## Dónde editar la búsqueda

Las constantes para personalizar el comportamiento están en:

- `src/main/java/xyz/balbe/Grep.java`
  - `SEARCH_TERM` — texto/ palabra a buscar (por ejemplo `"a"` o `"PSP"`).
  - `MATCH_WHOLE_WORD` — true para buscar palabra completa, false para subcadena.
  - `CASE_INSENSITIVE` — true para ignorar mayúsculas/minúsculas.

- `src/main/java/xyz/balbe/LS.java`
  - `PATH` — ruta que lista `ls` por defecto (ahora `/home/`).

- `src/main/java/xyz/balbe/Main.java`
  - `CLASS_PATH` — classpath que se pasa a los subprocesos Java.

## Checklist para entrega (objetivo: nota 9)

1. Funcionamiento: el programa lanza `LS` y `Grep` como procesos separados y muestra por consola las líneas que contienen la búsqueda (OK).
2. Tests: JUnit 5 está configurado y los tests del repositorio pasan (OK).
3. Código limpio: constantes configurables y breve documentación incluida (mejorable).
4. Robustez: mejorar manejo de errores y cerrar/executor shutdown ordenado.
5. Opcional (para 9+): permitir pasar `SEARCH_TERM` desde `Main` o línea de comandos; usar `Pattern` con \b para mejor detección de palabras; añadir tests que cubran edge-cases.

Si quieres, implemento los puntos 4 y 5 (rápido) y añado tests nuevos para subir la nota.
