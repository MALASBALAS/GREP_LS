
# Grep - Descripción

Repositorio en GitHub: [https://github.com/MALASBALAS/GREP_LS](https://github.com/MALASBALAS/GREP_LS)

Programa Java que usa grep -i a para filtrar líneas que contienen la letra "a" (sin distinguir mayúsculas/minúsculas).
Lanza un proceso ls y pasa su salida a grep, mostrando únicamente las coincidencias.

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

## Dónde editar la búsqueda

Las constantes para personalizar el comportamiento están en:

- `src/main/java/xyz/balbe/Grep.java`
  - `SEARCH_TERM` — texto/ palabra a buscar (por ejemplo `"a"` o `"PSP"`).
  - Constantes reales en el código:
    - `SEARCH_TERM` — texto/palabra por defecto a buscar (valor por defecto: `"a"`).
    - `IGNORAR_MAYUSCULAS` — si es `true`, se añade `-i` al comando `grep` para ignorar mayúsculas/minúsculas (valor por defecto: `true`).
    - `MSG_ERROR` — mensaje que se muestra en stderr si ocurre un error al ejecutar `grep`.

- `src/main/java/xyz/balbe/LS.java`
  - `PATH` — ruta que lista `ls` por defecto (ahora `/home/`).
  - Constantes reales en el código:
    - `PATH` — ruta listada por `ls` (valor por defecto: `/home/`).
    - `LS_COMMAND` — comando completo construido a partir de `PATH` (ej. `"ls /home/"`).
    - `SHELL` — shell que se usa para ejecutar el comando (valor por defecto: `"bash"`).
    - `SHELL_PARAM` — parámetro usado para pasar el comando al shell (valor por defecto: `"-c"`).
    - `MSG_ERROR` — mensaje que se muestra en stderr si ocurre un error al ejecutar `ls`.

- `src/main/java/xyz/balbe/Main.java`
  - `CLASS_PATH` — classpath que se pasa a los subprocesos Java.
  - Constantes reales en el código:
    - `JAVA_CMD` — comando para invocar Java (valor por defecto: `"java"`).
    - `CLASS_PATH` — classpath usado para ejecutar las clases Java desde procesos hijos (valor por defecto: `"target/classes:target/test-classes:./src:."`).
    - `LS_CLASS` — nombre completo (FQCN) de la clase que lista el directorio (`"xyz.balbe.LS"`).
    - `GREP_CLASS` — nombre completo (FQCN) de la clase que ejecuta `grep` (`"xyz.balbe.Grep"`).
    - Nota: `Main` lanza `LS` como proceso Java, recoge toda su salida y la pasa por stdin a `Grep` (también proceso Java).

## Ejemplo de salida

Al listar el directorio */home* (ruta por defecto), la salida debe incluir todas las líneas que grep encuentre al buscar la letra *"a"*.

```text
Success!
debian
alvaro
```

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

Álvaro Balas Jiménez
