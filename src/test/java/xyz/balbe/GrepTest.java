package xyz.balbe;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class GrepTest {

    //--- TESTS ---
    @Test
    void grepFiltrosLineasConA() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/test-classes:target/classes:./src:.", "xyz.balbe.Grep");
        Process p = pb.start();

        // Entrada para el proceso Grep
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
            w.write("hola\n");
            w.write("bye\n");
            w.write("Ada\n");
            w.flush();
        }

        // Captura de la salida del proceso Grep
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (InputStream is = p.getInputStream()) {
            is.transferTo(out);
        }
        int exit = p.waitFor();
        String result = out.toString().trim();

        assertEquals(0, exit);

        // Debe contener líneas con 'a' o 'A' (case-insensitive)
        assertTrue(result.contains("hola"));
        assertTrue(result.contains("Ada"));
        assertFalse(result.contains("bye"));
    }
}
