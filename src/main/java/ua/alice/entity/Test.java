package ua.alice.entity;

import java.applet.Applet;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Лис on 12.06.2016.
 */
public class Test extends Applet {
    Test(){
    }
public String Ret(){
    return "Bla";
}
    public static String hashIt(String file) {

        String fileHash = null;
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            FileInputStream fis = new FileInputStream(file);


            byte[] data = new byte[1024];
            int read = 0;
            while ((read = fis.read(data)) != -1) {
                sha1.update(data, 0, read);
            }
            byte[] hashBytes = sha1.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hashBytes.length; i++) {
                sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            fileHash = sb.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println(e);
        }
        return fileHash;
    }
}
