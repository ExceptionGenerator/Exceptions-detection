package com.shiv.exception.research;

import lombok.Builder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Builder
public class CopyFileData {
    public static void main(String[] args) throws IOException {
        long totalBytes= CopyFileData.builder().build().copy("shg.txt");
        System.out.println(totalBytes);
    }

    /**
     *
     * @param sourceFileName source file name which will copy
     * @return return no. of bytes that copied
     * @throws IOException
     */
    public long copy(String sourceFileName) throws IOException {
        Path destinationPath= Paths.get("E:\\Shiv Projects\\installer\\copy"+sourceFileName);
        InputStream sourceInputStream=new FileInputStream("E:\\Shiv Projects\\installer\\"+sourceFileName);
        return Files.copy(sourceInputStream,destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }
}
