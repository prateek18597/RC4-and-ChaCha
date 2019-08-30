import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Decryptor extends Process {

    public Decryptor(String key, String msg){
        super.setKey(key);
        super.setMsg(msg);
    }

    public  void decrypt(Object obj) throws IOException {
        if(obj.getClass() == RC4.class) {
            RC4 rc4 = (RC4) obj;
            String decryptedMsg = rc4.decrypt(super.getKey(),
                    super.getMsg());
            File file = new File("Decrypted.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(decryptedMsg);
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Decrypted Msg: " + decryptedMsg);
        } else if(obj.getClass() == ChaCha.class){
            ChaCha chaCha = (ChaCha)obj;
        }
    }


}
