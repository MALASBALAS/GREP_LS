package xyz.balbe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Grep {

    // Mensaje de error
    public static final String MSG_ERROR = "Se ha producido un error al ejecutar el comando";
    // Palabra a buscar
    public static final String ATRIBUTO1 = "-i";
    public static final String ATRIBUTO2 = "psp";
    // Comando grep con búsqueda insensible a mayúsculas
    public static final String[] COMANDO = {"grep", ATRIBUTO1, ATRIBUTO2};

    public static void main(String[] args) {
        // Líneas de texto a analizar
        String[] lineas = new String[]{
                "Me gusta PSP y java",
                "PSP se programa en java",
                "es un módulo de DAM",
                "y se programa de forma concurrente en PSP",
                "PSP es programación."
        };

        try {
            // Lanza el proceso grep
            Process process = Runtime.getRuntime().exec(COMANDO);

            // Escribe las líneas en la entrada estándar del proceso hijo
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                for (String l : lineas) {
                    writer.write(l);
                    writer.newLine();
                }
                writer.flush(); // Señala EOF a grep
            }

            // Recoge la salida estándar del proceso (líneas que contienen la palabra)
            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append(System.lineSeparator());
                }
            }

            // Espera a que termine el proceso grep
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output); // Muestra coincidencias
                System.exit(0);
            } else {
                System.out.println(MSG_ERROR);
                System.exit(1);
            }

        } catch (IOException | InterruptedException e) {
            // Si ocurre un error, termina con código 34
            System.exit(34);
        }
    // Nota: Este programa demuestra cómo interactuar con procesos externos desde Java
    // y cómo filtrar texto usando grep desde un programa Java.
	}
}