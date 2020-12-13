package com.elexlab.xcomputerassmber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: Test
 * @Description: TODO
 * @author: DJ008854
 * @date: 2020年12月03日 13:14
 */
public class Test {
    public static void main(String[] args) {
       // String ins = "movi,r0,4";
        String ins = "addi r1,r0,10";
        //Pattern reg = Pattern.compile("^(.+),(.+,.+),(\\d+)");
        //Pattern reg = Pattern.compile("r([0-7])+");
        //Pattern reg = Pattern.compile(".*i,");
        //Pattern reg = Pattern.compile("[^r](\\d)+");
        Pattern reg = Pattern.compile("(r[0-7],r[0-7],r[0-7])");
        Matcher matcher = reg.matcher(ins);

        while (matcher.find()){
            int groupCount = matcher.groupCount();
            for(int i=0;i<=groupCount;i++){
                System.out.println(i);

                String g = matcher.group(i);
                System.out.println(g);
            }

        }


    }
}
