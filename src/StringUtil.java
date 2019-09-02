import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class StringUtil {
    public static String swap( String s, int i, int j){
        StringBuilder sb = new StringBuilder(s);
        char temp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, temp);
        return sb.toString();
    }

    public static String binaryString(String s){
        String str = "";
        for(int i = 0; i < s.length(); i++) {
            String bit = Integer.toBinaryString(s.charAt(i));
            while (bit.length() != 8) {
                bit = "0" + bit;
            }
            str += bit;
        }
        return str;
    }

    public static void countFreq(String s) throws IOException {
        int[] count = new int[256];
        Arrays.fill(count,0);
        for(int i=0; i<s.length(); i++){
            int c = s.charAt(i);
            count[c]++;
        }
        File file = new File("Freq.txt");
        String str = "";
        for(int i = 0; i < 256; i++){
            str += ((char)i) + "\t" + count[i] + "\n";
        }
        FileUtils.write(file, str);
    }

    public static String asciiString(String s){
        String str = "";
        for(int i = 0; i < s.length(); i+=8){
            String st = s.substring(i,i+8);
            str += (char)Integer.parseInt(st, 2);
        }
        return str;
    }
}
