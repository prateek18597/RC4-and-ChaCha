import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runner {
    public static void print(String s){
        System.out.println(s);
    }
    public static void main(String[] args) throws FileNotFoundException {
        print("Select Algorithm:\n1 : RC4\n2: ChaCha\n");
        Scanner scan = new Scanner(System.in);
        Console console = System.console();
        int algo = scan.nextInt();
        String key = console.readPassword().toString();
        String msg = "";
        print("Taking message from Input.txt file of this directory.");
        File file = new File("Input.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine())
            msg += sc.nextLine();
        print("Select Process:\n1 : Encryption\n2: Decryption\n");
        int process = scan.nextInt();

        switch (process){
            case 1:
                Encryptor encryptor = new Encryptor(key, msg);
                if(algo == 1){
                    RC4 rc4 = new RC4();
                    encryptor.encrypt(rc4);
                } else if( algo ==2){
                    ChaCha chaCha = new ChaCha();
                    encryptor.encrypt(chaCha);
                } else {
                    print("Wrong input for Algorithm.");
                }
                break;
            case 2:
                Decryptor decryptor = new Decryptor(key, msg);
                if(algo == 1){
                    RC4 rc4 = new RC4();
                    decryptor.decrypt(rc4);
                } else if( algo ==2){
                    ChaCha chaCha = new ChaCha();
                    decryptor.decrypt(chaCha);
                } else {
                    print("Wrong input for Algorithm.");
                }
                break;
            default:
                print("Wrong input for Process");
        }
    }
}
