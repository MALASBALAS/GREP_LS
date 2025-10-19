package xyz.balbe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Main {
    // --- CONSTANTES ---
    public static final String JAVA_CMD = "java";
    public static final String CLASS_PATH = "target/classes:target/test-classes:./src:.";
    public static final String LS_CLASS = "xyz.balbe.LS";
    public static final String GREP_CLASS = "xyz.balbe.Grep";

    public static void main(String[] args) {
        try {
            // 1) Ejecutar la clase LS como proceso Java y capturar toda su salida
            Process lsProc = new ProcessBuilder(JAVA_CMD, "-cp", CLASS_PATH, LS_CLASS).start();

            StringBuilder lsOutput = new StringBuilder();
            try (BufferedReader r = new BufferedReader(new InputStreamReader(lsProc.getInputStream()))) {
                String line;
                while ((line = r.readLine()) != null) {
                    lsOutput.append(line).append('\n');
                }
            }

            int lsExit = lsProc.waitFor();
            if (lsExit != 0) {
                System.err.println(LS.MSG_ERROR);
                System.exit(lsExit);
            }

            // 2) Ejecutar la clase Grep como proceso Java y pasarle la salida de LS por stdin
            Process grepProc = new ProcessBuilder(JAVA_CMD, "-cp", CLASS_PATH, GREP_CLASS, "a").start();

            try (OutputStream toGrep = grepProc.getOutputStream()) {
                toGrep.write(lsOutput.toString().getBytes());
                toGrep.flush(); // señal de fin de datos
            }

            // 3) Leer la salida de Grep y mostrarla por consola
            StringBuilder grepOut = new StringBuilder();
            try (BufferedReader gr = new BufferedReader(new InputStreamReader(grepProc.getInputStream()))) {
                String line;
                while ((line = gr.readLine()) != null) {
                    grepOut.append(line).append('\n');
                }
            }

            // Dejamos el programa bloqueado hasta que termine el proceso grep
            int exitVal = grepProc.waitFor();
            // Imprimimos la salida capturada
            System.out.print(grepOut.toString());

            if (exitVal == 0) {
                // todo correcto
                System.exit(0);
            } else {
                // volcar stderr del proceso grep para diagnóstico
                try (java.io.BufferedReader err = new java.io.BufferedReader(new java.io.InputStreamReader(grepProc.getErrorStream()))) {
                    String el;
                    while ((el = err.readLine()) != null) {
                        System.err.println(el);
                    }
                } catch (java.io.IOException ioe) {
                    // ignorar problemas al volcar stderr
                }
                System.err.println(Grep.MSG_ERROR);
                System.exit(1);
            }

        } catch (IOException | InterruptedException e) {
            // Salir con 34 como en tu ejemplo original y mostrar causa breve
            System.err.println("Error encadenando procesos: " + e.getMessage());
            System.exit(34);
        }
    }
}
