package com.betofroes.desafiotecnicobetofroes.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class FileUtilTest {

    @TempDir
    static Path sharedTempDir;

    @AfterEach
    void tearDown() {
        File fileFinal = sharedTempDir.toFile();
        if(fileFinal.exists() && fileFinal.isDirectory()) {
            if(fileFinal.isDirectory()){
                File[] files = fileFinal.listFiles();
                for(File f : files) {
                    f.delete();
                }
            } else {
                fileFinal.delete();
            }
        }
    }

    @Test
    void should_find_all_filesInFolder() throws IOException {

        File file = new File(sharedTempDir + "/input");
        Files.write(file.toPath(), Arrays.asList("should_find_all_filesInFolder"));

        List<String> listReuslt = FileUtil.findAllFilesInFolder(sharedTempDir.toFile());

        assertNotNull(listReuslt);
        assertEquals(1, listReuslt.size());
    }

    @Test
    void should_throw_exception_when_folder_does_not_exist() {
        assertThrows(IllegalArgumentException.class, () -> FileUtil.findAllFilesInFolder(null));
    }

    @Test
    void should_write_outPut_file() throws IOException {

        String outPut = "should_write_outPut_file";
        String fileName = sharedTempDir + "/output.txt";

        FileUtil.writeOutPutFile(outPut, fileName);

        List<String> listReuslt = FileUtil.findAllFilesInFolder(sharedTempDir.toFile());

        assertNotNull(listReuslt);
        assertEquals(1, listReuslt.size());
        assertEquals("should_write_outPut_file", listReuslt.get(0));
    }
}