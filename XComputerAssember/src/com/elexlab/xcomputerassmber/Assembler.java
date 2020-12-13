package com.elexlab.xcomputerassmber;


import com.elexlab.xcomputerassmber.utils.DataUtils;

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
        Map<String,List<String>> ins = new InstructionsPretreater().preatreat(validLines).getIns();
        List<String> normalIns = ins.get("NORMAL");
        List<String> int0Ins = ins.get("INT0");
        List<String> int1Ins = ins.get("INT1");
        List<String> int2Ins = ins.get("INT2");
        List<String> int3Ins = ins.get("INT3");

        List<String> normalHexCodes = assembleIns(normalIns);

        while(normalHexCodes.size()<216){
            normalHexCodes.add("00000000");
        }

        List<String> int0HexCodes = assmbelIntIns(int0Ins);
        List<String> int1HexCodes = assmbelIntIns(int1Ins);
        List<String> int2HexCodes = assmbelIntIns(int2Ins);
        List<String> int3HexCodes = assmbelIntIns(int3Ins);


        List<String> allHexCode = new ArrayList<>();
        allHexCode.addAll(normalHexCodes);
        allHexCode.addAll(int0HexCodes);
        allHexCode.addAll(int1HexCodes);
        allHexCode.addAll(int2HexCodes);
        allHexCode.addAll(int3HexCodes);

        String hexs = "";
        for(String hexCode:allHexCode){
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
            //System.out.println(instruction);
            String binCode = instructionsDecoder.decodeInstruction(instruction);
            System.out.println(binCode);
            String hexCode = DataUtils.binaryStr2HexStr(binCode,8);
            hexCodes.add(hexCode);
        }
        return hexCodes;

    }


    private List<String> assmbelIntIns(List<String > instructionLines){
        List<String> hexCodes = assembleIns(instructionLines);
        while(hexCodes.size()<10){
            hexCodes.add("00000000");
        }
        return hexCodes;

    }


    private void parseWord(String content){
        String[] segments = content.trim().split(" ");
        wordMap.put(segments[0], Integer.parseInt(segments[2]));
    }

}
