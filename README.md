# Grep - Descripción

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
# GREP
