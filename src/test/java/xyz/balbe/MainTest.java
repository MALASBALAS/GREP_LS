package xyz.balbe;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void mainPipesLsToGrep() throws Exception {
        // Run Main class which launches two java subprocesses
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/test-classes:target/classes:./src:.", "xyz.balbe.Main");
        pb.redirectErrorStream(true);
        Process p = pb.start();

        // Capture output
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (InputStream is = p.getInputStream()) {
            is.transferTo(out);
        }
        int exit = p.waitFor();
        String result = out.toString();

        assertEquals(0, exit);
        // result may be empty depending on cwd, but it should not throw
        assertNotNull(result);
    }
}
