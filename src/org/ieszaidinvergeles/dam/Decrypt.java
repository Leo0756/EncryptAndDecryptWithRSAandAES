package org.ieszaidinvergeles.dam;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

public class Decrypt {
    private static final File RSA_PRIVATE_KEY_FILE = new File("private_rsa.key");
    private static final File AES_SECRET_KEY_ENCRYPTED_FILE = new File("secret_aes_encrypted.key");
    private static final File ENCRYPTED_FILE = new File("encrypted_file.bin");
    private static final File DECRYPTED_FILE = new File("decrypted_file.txt");

    public static void decryptFile() throws Exception {
        // Cargar clave privada RSA
        PrivateKey privateKey = loadPrivateKey();

        // Cargar clave AES cifrada
        byte[] encryptedAesKey = loadEncryptedAesKey();

        // Inicializar el descifrado RSA con la clave privada
        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);

        // Descifrar la clave AES con RSA
        byte[] aesKeyBytes = rsaCipher.doFinal(encryptedAesKey);
        SecretKey aesKey = new SecretKeySpec(aesKeyBytes, "AES");

        // Inicializar el descifrado AES
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey);

        // Descifrar el archivo encriptado
        FileInputStream fileInputStream = new FileInputStream(ENCRYPTED_FILE);
        byte[] encryptedBytes = fileInputStream.readAllBytes();
        byte[] decryptedBytes = aesCipher.doFinal(encryptedBytes);
        fileInputStream.close();

        // Escribir el archivo desencriptado
        FileOutputStream fileOutputStream = new FileOutputStream(DECRYPTED_FILE);
        fileOutputStream.write(decryptedBytes);
        fileOutputStream.close();
    }

    private static PrivateKey loadPrivateKey() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(RSA_PRIVATE_KEY_FILE);
        byte[] privateKeyBytes = fileInputStream.readAllBytes();
        fileInputStream.close();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
    }

    private static byte[] loadEncryptedAesKey() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(AES_SECRET_KEY_ENCRYPTED_FILE);
        byte[] encryptedKeyBytes = fileInputStream.readAllBytes();
        fileInputStream.close();
        return encryptedKeyBytes;
    }

    public static void main(String[] args) {
        try {
            decryptFile();
            System.out.println("Archivo desencriptado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}