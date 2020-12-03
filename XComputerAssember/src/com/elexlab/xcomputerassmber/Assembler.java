package com.elexlab.xcomputerassmber;


import com.elexlab.xcomputerassmber.utils.DataUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Assembler
 * @Description: TODO
 * @author: DJ008854
 * @date: 2020年10月27日 20:18
 */
public class Assembler {

    public Map<Integer,String> instructions = new HashMap<>();
    public Map<String,Integer> labelMap = new HashMap<>();
    public Map<String,Integer> wordMap = new HashMap<>();

    public Assembler() {
    }

    public String assemble(String filePath){

        System.out.println(filePath);
        InstructionLoader instructionLoader = new InstructionLoader(filePath);
        List<String> validLines = instructionLoader.loadIns();

        List<String> hexCodes = assembleIns(validLines);

        String hexs = "";
        for(String hexCode:hexCodes){
            hexs+= hexCode+" ";
        }
        hexs = "v2.0 raw\n"+hexs;
        System.out.println(hexs);
        return hexs;



    }

    private List<String> assembleIns(List<String > instructionLines){

        List<String> hexCodes = new ArrayList<>();
        for(int i=0;i<instructionLines.size();i++){
            String instruction = instructionLines.get(i);
            int instructionNum = i;
            InstructionsDecoder instructionsDecoder =  new InstructionsDecoder(instructionNum);
            System.out.println(Long.toString(instructionNum,16));
            System.out.println(instruction);
            String binCode = instructionsDecoder.decodeInstruction(instruction);
            System.out.println(binCode);
            String hexCode = DataUtils.binaryStr2HexStr(binCode,8);
            hexCodes.add(hexCode);
        }
        return hexCodes;

    }


    private void parseWord(String content){
        String[] segments = content.trim().split(" ");
        wordMap.put(segments[0], Integer.parseInt(segments[2]));
    }

}
