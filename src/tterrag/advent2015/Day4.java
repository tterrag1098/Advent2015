package tterrag.advent2015;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class Day4 {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String key = "ckczppom";
        int n = 0;
        String hash;
        
        do {
            n++;
            hash = DigestUtils.md5Hex(key + n);
        } while (!hash.startsWith("00000"));
        System.out.println(n);

        n = 0;
        do { 
            n++;
            hash = DigestUtils.md5Hex(key + n);
        } while (!hash.startsWith("0000000"));
        System.out.println(n);
    }
}
