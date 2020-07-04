package com.inspur.lib_base.util;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author lichun
 */
public class Md5Util {
    private static final String TAG = "Md5Util";

    public static String encrypt32(String msg){
        byte[] hash = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(msg.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert hash != null;
        StringBuilder sb = new StringBuilder(hash.length*2);
        for (byte b:hash) {
            if((b & 0xFF) < 0x10){
                sb.append("0");
            }
            sb.append(Integer.toHexString(b & 0xFF));
        }
        return sb.toString();
    }
}
