package com.inspur.blockchain;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author lichun
 * 中英文对应转换
 */
public class Translate {
    private static Map<String,String> words = new HashMap<>(10);
    private static Map<String,String> reverseWords = new HashMap<>(10);
    static{
        words.put("nation","民族");
        words.put("natio","民族");
        words.put("dob","出生日期");
        words.put("name","姓名");
        words.put("nam","姓名");
        words.put("sex","性别");
        words.put("tx_hash","交易哈希");
        words.put("issuer_name","签发人");
        words.put("holder_name","持有人");
        words.put("addr","地址");
        words.put("did_desc","凭证描述");

        reverseWords.put("民族","nation");
        reverseWords.put("出生日期","dob");
        reverseWords.put("姓名","name");
        reverseWords.put("性别","sex");
        reverseWords.put("交易哈希","tx_hash");
        reverseWords.put("签发人","issuer_name");
        reverseWords.put("持有人","holder_name");
        reverseWords.put("地址","addr");
        reverseWords.put("凭证描述","did_desc");
    }

    public static String getChineseName(String englishName){
        for (String key:words.keySet()) {
            if(TextUtils.equals(key,englishName)){
                return words.get(key);
            }
        }
        return "";
    }

    public static String getEnglishName(String chineseName){
        for (String key:reverseWords.keySet()) {
            if(TextUtils.equals(key,chineseName)){
                return reverseWords.get(key);
            }
        }
        return "";
    }





}
