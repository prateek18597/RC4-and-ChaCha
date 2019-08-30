import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Encryptor extends Process {

    public Encryptor(String key, String msg){
        super.setKey(key);
        super.setMsg(msg);
    }

    public  void encrypt(Object obj) throws IOException {
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
        } else {

        }
    }


}
