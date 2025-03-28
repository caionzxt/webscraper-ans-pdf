package com.meuprojeto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class AppTest {

    @Test
    public void testDownloadFolderExists() {
        File dir = new File("downloads/");
        assertTrue(dir.exists(), "A pasta de downloads deveria existir.");
    }

    @Test
    public void testZipFileCreated() {
        File zipFile = new File("anexos.zip");
        assertTrue(zipFile.exists(), "O arquivo ZIP deveria ser criado.");
    }
}
