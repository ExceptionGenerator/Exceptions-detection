package com.shiv.exception.sqlite;
import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * This utility extracts files and directories of a standard zip file to
 * a destination directory.
 * @author www.codejava.net
 *
 */
public class UnzipUtility {
    /**
     * Size of the buffer to read/write data
     */
    private static final int BUFFER_SIZE = 4096;
    /**
     * Extracts a zip file specified by the zipFilePath to a directory specified by
     * destDirectory (will be created if does not exists)
     * @param zipFilePath
     * @param destDirectory
     * @throws IOException
     */
    public void unzip(String zipFilePath, String destDirectory) throws IOException {
        new File(destDirectory).mkdirs();
        ZipFile zipFile=new ZipFile(zipFilePath);
        FileSystem fileSystem= FileSystems.getDefault();
        Enumeration<? extends ZipEntry> entries=zipFile.entries();
        while(entries.hasMoreElements()){
            ZipEntry entry=entries.nextElement();
            InputStream inputStream=zipFile.getInputStream(entry);
            BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
            Path path=fileSystem.getPath(destDirectory+File.separator+entry.getName());
            Files.createFile(path);
            FileOutputStream fileOutputStream=new FileOutputStream(destDirectory+File.separator+entry.getName());
            while(bufferedInputStream.available()>0)
                fileOutputStream.write(bufferedInputStream.read());
            fileOutputStream.flush();
            fileOutputStream.close();
            System.out.println(entry.getName());
        }
    }
    /**
     * Extracts a zip entry (file entry)
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        File file=new File(filePath);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[(int) file.length()];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    public static void main(String[] args) throws IOException {
        UnzipUtility unzipUtility=new UnzipUtility();
        unzipUtility.unzip("E://Lattice tasks//Projects//Learning//detect Ex//Exceptions-detection//Detect Exceptions//jre//kfcomm2.zip","E://Lattice tasks//Projects//Learning//detect Ex//Exceptions-detection//Detect Exceptions//jre//dest");
    }
}