import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import org.apache.commons.io.FileUtils;

public class Encryptor extends Process {

    public Encryptor(String key, String msg){
        super.setKey(key);
        super.setMsg(msg);
    }

    public  String encrypt(Object obj) throws Exception {
        if(obj.getClass() == RC4.class) {
            RC4 rc4 = (RC4) obj;
            String encryptedMsg = rc4.encrypt(super.getKey(),
                    super.getMsg());
            File file = new File("Encrypted.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(encryptedMsg);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Encrypted Data: " + encryptedMsg);
            StringUtil.countFreq(encryptedMsg);
            return encryptedMsg;
        } else if (obj.getClass() == ChaCha20.class) {
            ChaCha20 chaCha = (ChaCha20) obj;
            SecretKey key = new SecretKeySpec(super.getKey().getBytes(),"ChaCha20");
            byte[] encryptedMsg = chaCha.encrypt(super.getMsg(),key);
            File file = new File("Encrypted.txt");
            FileUtils.writeByteArrayToFile(file, encryptedMsg);
            System.out.println("Encrypted Data: " + Base64.getEncoder().encodeToString(encryptedMsg));
            StringUtil.countFreq(Base64.getEncoder().encodeToString(encryptedMsg));
            return Base64.getEncoder().encodeToString(encryptedMsg);
        } else {
            return null;
        }
    }


}
