package com.elexlab.xcomputerassmber.utils;

/**
 * @ClassName: DataUtils
 * @Description: TODO
 * @author: BruceYoung
 * @date: 2020年10月27日 18:24
 */
public class DataUtils {
    public static String binaryStr2HexStr(String str,int num){
        String[] strs = str.split ("\\s+");
        String result = "";
        for ( String s : strs )
        {
            long step1 = Long.parseLong (s, 2);
            String hex = Long.toString (step1, 16);
            result += hex;
        }
        return result;
    }

    public static String integer2Bin(int integer,int len){
        return String.format("%0"+len+"d",Integer.parseInt(Integer.toBinaryString(integer)));
    }
}
