package xyz.balbe;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class LSTest {

    @Test
    void lsPrintsSomething() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/test-classes:target/classes:./src/main:.", "xyz.balbe.LS");
        Process p = pb.start();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (InputStream is = p.getInputStream()) {
            is.transferTo(out);
        }
        int exit = p.waitFor();
        String result = out.toString().trim();

        assertEquals(0, exit);
        assertFalse(result.isEmpty(), "ls should print at least one line");
    }
}
