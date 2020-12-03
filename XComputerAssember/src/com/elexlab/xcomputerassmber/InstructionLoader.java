package com.elexlab.xcomputerassmber;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstructionLoader {
    private List<String> validLines = new ArrayList<>();
    private String sourceFilePath;

    public InstructionLoader(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public List<String> loadIns(){

        File file = new File(sourceFilePath);
        String binCodes = "";
        String hexCodes = "";
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader  = new BufferedReader(fileReader);
            String line = "";
            int instructionNum = 0;
            while((line = bufferedReader.readLine())!=null){

                line = line.trim();
                if("".equals(line) || "\n".equals(line)){//空行
                    continue;
                }
                if(line.startsWith("//")){//注释
                    continue;
                }
                line = cleanEndLineAnnotation(line);
                validLines.add(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.validLines;
    }
    private String cleanEndLineAnnotation(String content){
        int index = content.indexOf("//");
        if(index == -1){
            return content;
        }
        content = content.substring(0,index);
        return content;
    }
}
