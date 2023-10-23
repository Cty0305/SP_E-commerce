package Helpers;

import Exception.encryptException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class  encryptionUtils {


    public static String encryptString(String data) throws encryptException {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new encryptException("SHA256處理異常",e);
        }
        messageDigest.update(data.getBytes());
        byte[] bytes = messageDigest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b:bytes){
            String hex = Integer.toHexString(b & 0xff);
            if(hex.length() == 1){
                hexString.append(0);
            }
            hexString.append(hex);
        }
        return hexString.toString();

    }

}
