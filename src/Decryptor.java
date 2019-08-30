public class Decryptor extends Type{

    public Decryptor(String key, String msg){
        super.setKey(key);
        super.setMsg(msg);
    }

    public  void decrypt(Object obj){
        if(obj.getClass() == RC4.class) {
            RC4 rc4 = (RC4) obj;
            String decryptedMsg = rc4.decrypt(super.getKey(),
                    super.getMsg());
            System.out.println("Decrypted Msg: " + decryptedMsg);
        } else if(obj.getClass() == ChaCha.class){
            ChaCha chaCha = (ChaCha)obj;
        }
    }


}
