package com.shiv.exception.sqlite;

import org.sqlite.SQLiteConfig;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class PasswordDecryptor {

    private String[] roles={"Admin","Auditor","Manager","Technician","SuperAdmin"};
    private String keyString="spectrum and lattice";
    private static SecretKeySpec secretKey;
    private static byte[] key;
    public static final String FETCH_DETAILS="select user_master.user_id,user_master.username,user_role_map.is_active,user_role_map.role_id from\n" +
            " user_master,user_role_map where user_role_map.user_id=user_master.user_id";
    public static final String FETCH_CREDENTIALS="select * from user_credentials where pass_is_active='Y' and user_id=?";
    private Connection connection;
    public PasswordDecryptor() throws SQLException, ClassNotFoundException {
        System.out.println(System.getProperty("user.dir"));
        Class.forName("org.sqlite.JDBC");
        // inside current dir create dir named with jre and paste db file inside the jre dir
        connection= DriverManager.getConnection("jdbc:sqlite:file:./jre/kfcomm2c.db?cipher=aes256cbc&legacy=1&kdf_iter=4000&key=a+9R]y(=%VpbPryLvTBp",getSqliteConfig().toProperties());
        System.out.println("Connection successfully");
    }

    private SQLiteConfig getSqliteConfig() {
        int cache_size = -50000;
        SQLiteConfig config = new SQLiteConfig();
        config.setCacheSize(cache_size);
        config.setSynchronous(SQLiteConfig.SynchronousMode.NORMAL);
        config.setTempStore(SQLiteConfig.TempStore.MEMORY);
        config.setJournalMode(SQLiteConfig.JournalMode.WAL);
        return config;
    }

    public List<?> getCredentials() throws Exception {
        List<Credential> credentials=new ArrayList<>();
        PreparedStatement preparedStatement= connection.prepareStatement(PasswordDecryptor.FETCH_DETAILS);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            PreparedStatement preparedStatement1= connection.prepareStatement(PasswordDecryptor.FETCH_CREDENTIALS);
            preparedStatement1.setInt(1,resultSet.getInt("user_id"));
            ResultSet resultSet1=preparedStatement1.executeQuery();
            if(resultSet1.next())
                credentials.add(new Credential(resultSet.getInt("user_id"),
                        roles[resultSet.getInt("role_id")-1],
                        resultSet.getString("is_active").equals("Y"),
                        resultSet.getString("username"),
                        PasswordDecryptor.decrypt(resultSet1.getString("password"),
                                keyString)));
        }
        return credentials;
    }

    public static void main(String[] args) throws Exception {
        PasswordDecryptor passwordDecryptor=new PasswordDecryptor();
        List<Credential> credentials= (List<Credential>) passwordDecryptor.getCredentials();
        credentials.forEach(System.out::println);
    }

    public static String decrypt(String strToDecrypt, String secret) throws Exception {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void setKey(String myKey) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key,"AES");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
