public class RC4 {

    private String initialVector;
    private String key;

    public void setInitialVector(String initialVector){
        this.initialVector = initialVector;
    }

    public void setKey(String key){
        this.key = key;
    }

    public RC4(){
        setInitialVector("");
    }

    public void keyScheduling(){
        setInitialVector("");
        for(int i = 0; i <= 255; i++ ){
            initialVector += (char)i;
        }
        int j = 0;
        for(int i = 0; i <= 255; i++ ){
            j = (j + initialVector.charAt(i) + key.charAt(i % key.length())) % 256;
            initialVector = StringUtil.swap(initialVector, i, j);
        }
    }

    public String pseudoRandomGenerator(){
        int i = 0, j = 0;
        String keyStream = "";
        for(int k = 0; k < 5000; k++ ){
            i = (i + 1) % 256;
            j = (j + initialVector.charAt(i)) % 256;
            initialVector = StringUtil.swap(initialVector, i, j);
            keyStream += initialVector.charAt((initialVector.charAt(i) + initialVector.charAt(j)) % 256);
        }
        return StringUtil.binaryString(keyStream);
    }

    public String encrypt(String key, String msg){
        setKey(key);
        keyScheduling();
        System.out.println("Key Scheduling completed");
        String keyStream = pseudoRandomGenerator();
        System.out.println("Key Stream completed");
        String binMsg = StringUtil.binaryString(msg);
        System.out.println(binMsg);
        int lenMsg = binMsg.length();
        int lenKeyStream = keyStream.length();
        String bitCipher = "";
        for(int i = 0; i < lenMsg ; i++){
            bitCipher += binMsg.charAt(i) == keyStream.charAt(i % lenKeyStream) ? "0" : "1";
        }
        String cipher = StringUtil.asciiString(bitCipher);
        return cipher;
    }

    public String decrypt(String key, String cipher){
        setKey(key);
        keyScheduling();
        String keyStream = pseudoRandomGenerator();
        String binCipher = StringUtil.binaryString(cipher);
        int lenCipher = binCipher.length();
        int lenKeyStream = keyStream.length();
        String bitMsg = "";
        for(int i = 0; i < lenCipher ; i++){
            bitMsg += binCipher.charAt(i) == keyStream.charAt(i % lenKeyStream) ? "0" : "1";
        }
        String msg = StringUtil.asciiString(bitMsg);
        return msg;
    }
}
