package io.home.junit5app.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TempDirTest {

    public TempDirTest() {
        System.out.println("TempDirTest instance created");
    }

    /** Temp Directory is NOT shared with other test cases **/
    @Test
    void tempFileTest(@TempDir Path fileDir) throws IOException {
        /* File will be created in temp directory (i.e. /tmp/junit3556576321462541209/abc.txt) */
        Path filePath = fileDir.resolve("abc.txt");

        String text = "Sample text";
        Files.write(filePath, text.getBytes(StandardCharsets.UTF_8));

        List<String> allLines = Files.readAllLines(filePath);

        assertEquals(Collections.singletonList(text), allLines);
    }

}
