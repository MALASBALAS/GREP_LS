package xyz.balbe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LS {

    public static final String MSG_ERROR = "Se ha producido un error al ejecutar ls";
    // --- CONSTANTES ---

    // Listar /home por defecto
    public static final String PATH = "/home/";

    // Comando LS y parametro
    public static final String LS_COMMAND = "ls " + PATH;

    // Entorno de ejecución
    public static final String SHELL = "bash";

    // Parámetro para el shell
    public static final String SHELL_PARAM = "-c";

    public static void main(String[] args) {
        // Ejecuta el comando ls en un proceso hijo y vuelca su salida por stdout
        try {
            ProcessBuilder pb = new ProcessBuilder(SHELL, SHELL_PARAM, LS_COMMAND);
            Process p = pb.start();

            // Leemos la salida del proceso ls y la imprimimos en la salida estándar
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Esperamos a que termine el proceso hijo y devolvemos su código
            int exitVal = p.waitFor();
            if (exitVal != 0) {
                System.err.println(MSG_ERROR);
                System.exit(exitVal);
            }

        } catch (IOException | InterruptedException e) {
            // Si ocurre un error, lo mostramos por stderr y salimos con código 1
            System.err.println(MSG_ERROR);
            System.exit(1);
        }
    }

    public static String getLS_COMMAND() {
        return LS_COMMAND;
    }
}
