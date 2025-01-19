package com.shiv.exception;


import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Class to handle encrypted properties with DPAPI-based encryption for sensitive data.
 */
public class EncryptedProperties {

    private final DataProtector protector;
    private final Properties properties;
    private final Map<String, String> keys;

    /**
     * Constructor initializes the data protector, properties, and key mappings.
     */
    public EncryptedProperties() {
        this.protector = new DataProtector(); // Initialize DataProtector (wraps native DPAPI calls)
        this.properties = new Properties();   // Properties to store key-value pairs
        this.keys = new HashMap<>();          // Mapping of original keys to encrypted keys
    }

    /**
     * Stores the properties into a writer. The properties are written in an encrypted form.
     */
    public void store(Writer writer) throws IOException {
        properties.store(writer, "Encrypted properties");
    }

    /**
     * Loads the properties from a reader and decrypts the keys.
     */
    public void load(Reader reader) throws IOException {
        properties.load(reader);

        // Decrypt each key to restore the original key mappings
        for (String encryptedKey : properties.stringPropertyNames()) {
            keys.put(decrypt(encryptedKey), encryptedKey);
        }
    }

    /**
     * Sets a property by encrypting both the key and value before storing them.
     */
    public void setProperty(String key, String value) {
        // Encrypt the key and value before storing in properties
        String encryptedKey = encrypt(key);
        String encryptedValue = encrypt(value);

        // Store the encrypted key and value in the properties map
        keys.put(key, encryptedKey);
        properties.setProperty(encryptedKey, encryptedValue);
    }

    /**
     * Retrieves the decrypted value for a given key.
     */
    public String getProperty(String key) {
        // Retrieve the encrypted key and value
        String encryptedKey = keys.get(key);
        String encryptedValue = properties.getProperty(encryptedKey);

        // Decrypt the value and return it
        return decrypt(encryptedValue);
    }

    /**
     * Encrypts a string using DPAPI and then encodes the result in Base64.
     */
    private String encrypt(String input) {
        byte[] data = protector.protect(input);  // Protect (encrypt) using DPAPI
        return new String(Base64.getEncoder().encode(data));   // Encode the encrypted data in Base64
    }

    /**
     * Decrypts a Base64-encoded string and unprotects it using DPAPI.
     */
    private String decrypt(String encryptedString) {
        byte[] data = Base64.getDecoder().decode(encryptedString.getBytes());  // Decode Base64
        return protector.unprotect(data);  // Unprotect (decrypt) using DPAPI
    }

    // Load the native library for DPAPI calls
    static {
        System.loadLibrary("jdpapi-native.dll");  // Load the native DPAPI library
    }
}

/**
 * Wrapper class for Windows DPAPI encryption/decryption (Data Protector).
 * This would interact with native code for encryption and decryption.
 */
class DataProtector {

    // Native methods (these need to be implemented in jdpapi-native.dll)
    public native byte[] protect(String data);

    public native String unprotect(byte[] encryptedData);

    // You will need to implement these native methods in the DLL using Windows DPAPI
}