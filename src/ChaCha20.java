import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class ChaCha20
{
    static String plainText = "This is a plain text which will be encrypted by ChaCha20 Algorithm";

//    public static void main(String[] args) throws Exception
//    {
//        SecretKey key = new SecretKeySpec("12345678912345678912345678912345".getBytes(),"ChaCha20");//keyGenerator.generateKey();
//        System.out.println("Original Text  : " + plainText);
//
//        byte[] cipherText = encrypt(plainText, key);
//        System.out.println("Encrypted Text : " + Base64.getEncoder().encodeToString(cipherText));
//
//        String decryptedText = decrypt(cipherText, key);
//        System.out.println("DeCrypted Text : " + decryptedText);
//
//    }

    public static byte[] encrypt(String plaintext, SecretKey key) throws Exception
    {
        byte[] nonceBytes = new byte[12];
        int counter = 5;

        Cipher cipher = Cipher.getInstance("ChaCha20");
        ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonceBytes, counter);

        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "ChaCha20");

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);

        byte[] cipherText = cipher.doFinal(plaintext.getBytes());

        return cipherText;
    }

    public static String decrypt(byte[] cipherText, SecretKey key) throws Exception
    {
        byte[] nonceBytes = new byte[12];
        int counter = 5;

        Cipher cipher = Cipher.getInstance("ChaCha20");

        ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonceBytes, counter);

        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "ChaCha20");

        cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);

        byte[] decryptedText = cipher.doFinal(cipherText);

        return new String(decryptedText);
    }

}