package xyz.balbe;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Grep {
    public static final String MSG_ERROR = "Se ha producido un error al ejecutar grep";
    public static final String SEARCH_TERM = "a";
    public static final boolean IGNORAR_MAYUSCULAS = true;

    public static void main(String[] args) {
        String term = (args != null && args.length > 0 && args[0] != null && !args[0].isEmpty())
                ? args[0] : SEARCH_TERM;

        String[] cmd = IGNORAR_MAYUSCULAS ? new String[]{"grep", "-i", term} : new String[]{"grep", term};

        try {
            Process p = Runtime.getRuntime().exec(cmd);

            // Leer todo System.in a memoria (bloqueante, sin hilos) y pasarlo a grep
            byte[] input = System.in.readAllBytes();
            try (OutputStream to = p.getOutputStream()) {
                to.write(input);
                to.flush();
            }

            // Leer todo stdout de grep y volcarlo
            try (InputStream out = p.getInputStream()) {
                out.transferTo(System.out);
                System.out.flush();
            }

            int exit = p.waitFor();
            if (exit != 0) {
                // volcar stderr si hay error
                try (InputStream err = p.getErrorStream()) {
                    err.transferTo(System.err);
                }
                System.err.println(MSG_ERROR);
            }
            System.exit(exit);

		} catch (IOException | InterruptedException e) {
            System.err.println(MSG_ERROR);
            System.exit(1);
        }
    }
}