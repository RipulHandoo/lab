import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Inputs - key, ct-8it, key-10bit, pt-8bit
        int[] plainText = { 1, 0, 1, 0, 0, 1, 0, 1 };
        int[] key = { 0, 0 , 1, 0, 0, 1, 0, 1, 1, 1 };
        Encrypt e = new Encrypt(plainText, key);

        e.convertText();
        
        System.out.println("Plain Text: " + Arrays.toString(e.plainText));
        System.out.println("Key: " + Arrays.toString(e.key));
        System.out.println("Cypher text : " + Arrays.toString(e.cypherText));
    }
}   

