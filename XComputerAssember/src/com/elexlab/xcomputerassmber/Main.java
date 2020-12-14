package com.elexlab.xcomputerassmber;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File f = new File("");
        String baseDir = null;
        try {
            baseDir = f.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = args[0];
        System.out.println("asm path:"+filePath);
        //String sourceCodeName = "snake";
        //String filePath = baseDir+"/testasm/"+sourceCodeName+".asm";
        String hex = new Assembler().assemble(filePath);

        //String hexFilePath = baseDir+"/hex/"+sourceCodeName+".hex";
        String fileName = new File(filePath).getName();
        String name = fileName.substring(0,fileName.indexOf('.'));
        String hexPath = "./"+name+".hex";
        String hexFilePath =hexPath;
        saveHex(hex,hexFilePath);


    }
    private static void saveHex(String content,String filePath){

        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(content);



        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
