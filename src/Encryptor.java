public class Encryptor extends Type{

    public Encryptor(String key, String msg){
        super.setKey(key);
        super.setMsg(msg);
    }

    public  void encrypt(Object obj){
        if(obj.getClass() == RC4.class) {
            RC4 rc4 = (RC4) obj;
            String encryptedMsg = rc4.encrypt(super.getKey(),
                    super.getMsg());
            System.out.println("Encrypted Data: " + encryptedMsg);
        } else {

        }
    }


}
