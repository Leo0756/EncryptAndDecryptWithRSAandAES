package org.ieszaidinvergeles.dam;

import javax.crypto.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class KeysGenerator {
    private final static String FILE_NAME_RSA_PUBLIC_KEY = "public_rsa.key";
    private final static String FILE_NAME_RSA_PRIVATE_KEY = "private_rsa.key";
    private final static String FILE_NAME_AES_SECRET_KEY = "secret_aes.key";
    private final static int RSA_KEY_SIZE = 4096;
    private final static int AES_KEY_SIZE = 256;
    private final static String ALGORITHM_RSA = "RSA";
    private final static String ALGORITHM_AES = "AES";

    public static void generateKeys() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // Inicializamos el objeto KeyPairGenerator con la encriptación RSA, el cual lo usaremos para crear tanto la clave privada como la pública.
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_RSA);

        // Creamos un número random seguro con el objeto SecureRandom.
        SecureRandom secureRandomNumber = new SecureRandom();

        // Usaremos una clave con el tamaño de 2048 bits, seguido del número random que antes hemos creado. Se puede cambiar por 1024 y 2048.
        keyPairGenerator.initialize(RSA_KEY_SIZE, secureRandomNumber);

        // Generamos las claves.
        KeyPair keys = keyPairGenerator.generateKeyPair();

        // Guardamos la clave pública en un archivo
        FileOutputStream fileOutputStreamPublicKey = new FileOutputStream(FILE_NAME_RSA_PUBLIC_KEY);
        fileOutputStreamPublicKey.write(keys.getPublic().getEncoded());
        fileOutputStreamPublicKey.close();

        // Guardamos la clave privada en un archivo
        FileOutputStream fileOutputStreamPrivateKey = new FileOutputStream(FILE_NAME_RSA_PRIVATE_KEY);
        fileOutputStreamPrivateKey.write(keys.getPrivate().getEncoded());
        fileOutputStreamPrivateKey.close();

        // Generamos una clave AES
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_AES);
        keyGenerator.init(AES_KEY_SIZE);
        SecretKey secretKey = keyGenerator.generateKey();

        // Guardamos la clave secreta en un archivo
        FileOutputStream fileOutputStreamSecretKey = new FileOutputStream(FILE_NAME_AES_SECRET_KEY);
        fileOutputStreamSecretKey.write(secretKey.getEncoded());
        fileOutputStreamSecretKey.close();
    }

    public static void main(String[] args) {
        try {

            // Genera las claves necesarias para la encriptación
            generateKeys();
            System.out.println("Claves generadas correctamente.");
        } catch (NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | IOException |
                 InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
