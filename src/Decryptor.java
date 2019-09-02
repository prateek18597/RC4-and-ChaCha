import org.apache.commons.io.FileUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class Decryptor extends Process {

    public Decryptor(String key, String msg){
        super.setKey(key);
        super.setMsg(msg);
    }

    public String decrypt(Object obj) throws Exception {
        if(obj.getClass() == RC4.class) {
            RC4 rc4 = (RC4) obj;
            String decryptedMsg = rc4.decrypt(super.getKey(),
                    super.getMsg());
            File file = new File("Decrypted.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(decryptedMsg);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Decrypted Msg:\n " + decryptedMsg);
            return decryptedMsg;
        } else if(obj.getClass() == ChaCha20.class){
            ChaCha20 chaCha = (ChaCha20) obj;
            SecretKey key = new SecretKeySpec(super.getKey().getBytes(),"ChaCha20");
            File file1 = new File("Encrypted.txt");
            byte[] cipherMsg = FileUtils.readFileToByteArray(file1);
            String decryptedMsg = chaCha.decrypt(cipherMsg,key);
            File file = new File("Decrypted.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(decryptedMsg);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Decrypted Data:\n " + decryptedMsg);
            return decryptedMsg.toString();
        } else {
            return null;
        }
    }


}
