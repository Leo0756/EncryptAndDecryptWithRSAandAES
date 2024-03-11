# 📝 Instrucciones para el proyecto de encriptación 

Este proyecto Java proporciona una implementación básica de encriptación y desencriptación de archivos utilizando los algoritmos RSA y AES.

## 🗝️ Generación de claves

La clase `KeysGenerator.java` se encarga de generar las claves necesarias para la encriptación. Este proceso generará un par de claves RSA (pública y privada) y una clave secreta AES. Las claves generadas se guardarán en archivos locales:

- Clave pública RSA: `public_rsa.key`.
- Clave privada RSA: `private_rsa.key`.
- Clave secreta AES: `secret_aes.key`.

## 🔒 Encriptación de archivos

La clase `Encrypt.java` toma un archivo de entrada y lo encripta utilizando la clave secreta AES. Luego, la clave secreta AES se encripta con la clave pública RSA y se guarda junto con el archivo encriptado. Los archivos generados son:

- Archivo encriptado: `encrypted_file.bin`.
- Clave secreta AES encriptada: `secret_aes_encrypted.key`.

## 🔓 Desencriptación de archivos

La clase `Decrypt.java` desencripta el archivo encriptado utilizando la clave secreta AES. La clave secreta AES se desencripta utilizando la clave privada RSA. El archivo desencriptado se guarda como `decrypted_file.txt`.

## 🛠️ Requisitos de ejecución
- JDK 21.

## 🚀 Ejecución del proyecto en IntelliJ IDEA
1. Clonar el proyecto en IntelliJ.
2. Abrir el proyecto.
3. Ejecutar la clase `KeysGenerator.java`: Esto nos generará las claves necesarias para la encriptación y la desencriptación del proyecto.
4. Ejecutar la clase `Encrypt.java`: Encriptará el archivo que le indiquemos (el nombre del archivo esta asignado en una constante).
5. Ejectuar la clase `Decrypt.java`: Desencripta el archivo.

## 🚀 Ejecución del proyecto en Netbeans
1. Crear un proyecto nuevo.
2. Copiar los tres clases necesarias dentro del proyecto: `KeysGenerator.java`, `Encrypt.java`, `Decrypt.java`.
4. Ejecutar la clase `KeysGenerator.java`: Esto nos generará las claves necesarias para la encriptación y la desencriptación del proyecto.
5. Ejecutar la clase `Encrypt.java`: Encriptará el archivo que le indiquemos (el nombre del archivo esta asignado en una constante).
6. Ejectuar la clase `Decrypt.java`: Desencripta el archivo.


## 💡 Notas adicionales
- Los algoritmos de encriptación utilizados son RSA para la encriptación de claves y AES para la encriptación de archivos.

## 📚 Fuentes
Estas son las fuentes de las que me he basado y me han servido de inspiración para realizar el proyecto:
- [Seguridad, criptografía y comercio electrónico con Java](https://silo.tips/download/seguridad-criptografia-y-comercio-electronico-con-java)
- [Criptografía asimétrica](https://es.wikipedia.org/wiki/Criptograf%C3%ADa_asim%C3%A9trica)
