package main.services;

import java.security.MessageDigest;

/**
 * Created by brama on 1/11/17.
 */
public class HashService {
    public String stringToMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte byteData[] = md.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            //System.out.println("Digest(in hex format):: " + hexString.toString());
            return hexString.toString();
        } catch (Exception e) {
            System.out.print(e);
        }


        return "";
    }
}
