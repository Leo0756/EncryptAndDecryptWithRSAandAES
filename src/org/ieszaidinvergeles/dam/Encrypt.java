package org.ieszaidinvergeles.dam;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

public class Encrypt {

    private static final File RSA_PUBLIC_KEY_FILE = new File("public_rsa.key");
    private static final File AES_SECRET_KEY_FILE = new File("secret_aes.key");
    private static final File FILE_TO_ENCRYPT = new File("ficheros/poema.txt");
    private static final File AES_SECRET_KEY_ENCRYPTED_FILE = new File("secret_aes_encrypted.key");
    private static final File ENCRYPTED_FILE = new File("encrypted_file.bin");


    public static void encryptFile() throws Exception {
        // Cargar clave pública RSA
        PublicKey publicKey = loadPublicKey();

        // Cargar clave secreta AES
        SecretKey secretKey = loadSecretKey();

        // Inicializar el cifrado AES con la clave secreta
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Cifrar los datos del archivo a cifrar
        FileInputStream fileInputStream = new FileInputStream(FILE_TO_ENCRYPT);
        byte[] inputBytes = fileInputStream.readAllBytes();
        byte[] encryptedBytes = aesCipher.doFinal(inputBytes);
        fileInputStream.close();

        // Inicializar el cifrado RSA con la clave pública
        Cipher rsaCipher = Cipher.getInstance("RSA");
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Cifrar la clave AES con RSA
        byte[] encryptedKey = rsaCipher.doFinal(secretKey.getEncoded());

        // Escribir la clave AES cifrada en un archivo
        FileOutputStream keyFileOutputStream = new FileOutputStream(AES_SECRET_KEY_ENCRYPTED_FILE);
        keyFileOutputStream.write(encryptedKey);
        keyFileOutputStream.close();

        // Escribir los datos cifrados en un archivo
        FileOutputStream dataFileOutputStream = new FileOutputStream(ENCRYPTED_FILE);
        dataFileOutputStream.write(encryptedBytes);
        dataFileOutputStream.close();
    }

    private static PublicKey loadPublicKey() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(RSA_PUBLIC_KEY_FILE);
        byte[] publicKeyBytes = fileInputStream.readAllBytes();
        fileInputStream.close();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
    }

    private static SecretKey loadSecretKey() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(AES_SECRET_KEY_FILE);
        byte[] secretKeyBytes = fileInputStream.readAllBytes();
        fileInputStream.close();
        return new SecretKeySpec(secretKeyBytes, "AES");
    }

    public static void main(String[] args) {
        try {
            encryptFile();
            System.out.println("Archivo encriptado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}