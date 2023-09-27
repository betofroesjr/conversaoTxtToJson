package com.betofroes.desafiotecnicobetofroes.util;

import com.betofroes.desafiotecnicobetofroes.dto.TxtDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileUtil {

    public static ObjectMapper mapper = new ObjectMapper() ;

    public static void removeAllFilesInFolder(File folder) {
        if(Objects.isNull(folder) || !folder.exists()) {
            throw new IllegalArgumentException("Folder does not exist or is empty");
        }
        Arrays.stream(folder.listFiles()).forEach(File::delete);
    }

    public static List<String> findAllFilesInFolder(File folder) {
        if(Objects.isNull(folder) || !folder.exists()) {
            throw new IllegalArgumentException("Folder does not exist or is empty");
        }
        return Arrays.stream(folder.listFiles())
                .map(FileUtil::getString).collect(Collectors.toList());
    }

    public static String getString(File file) {
        try {
            return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeOutPutFile(String outPut, String fileName) throws IOException {
        try(RandomAccessFile stream = new RandomAccessFile(fileName, "rw");
            FileChannel channel = stream.getChannel()) {
                byte[] strBytes = outPut.getBytes(StandardCharsets.UTF_8);
                ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
                buffer.put(strBytes);
                buffer.flip();
                channel.write(buffer);
        }
    }

    public static List<TxtDto> readProperties(String propertiesTxt) {
        try {
            return mapper.readValue(propertiesTxt, new TypeReference<List<TxtDto>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<TxtDto> readProperties(File file) {
        try {
            return mapper.readValue(file, new TypeReference<List<TxtDto>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
