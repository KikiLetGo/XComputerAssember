package com.elexlab.xcomputerassmber;

import com.elexlab.xcomputerassmber.pojo.Instruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstructionsPretreater {
    private Map<String,Integer> labelMap = new HashMap<>();
    private Map<String,Integer> wordMap = new HashMap<>();
    private List<String> normalIns = new ArrayList<>();
    private List<String> int0Ins = new ArrayList<>();
    private List<String> int1Ins = new ArrayList<>();
    private List<String> int2Ins = new ArrayList<>();
    private List<String> int3Ins = new ArrayList<>();
    public InstructionsPretreater preatreat(List<String> validLines) {
        parseLabelAndWord(validLines,0);
        for(int i=0;i<validLines.size();i++){
            String line = validLines.get(i);
            if(isWord(line) || isLable(line)){
                continue;
            }
            for(String key:labelMap.keySet()){
                validLines.set(i,validLines.get(i).replaceAll(key,String.valueOf(labelMap.get(key))));
            }
            for(String key:wordMap.keySet()){
                validLines.set(i,validLines.get(i).replaceAll(",+"+key,String.valueOf(wordMap.get(key))));
            }
        }
        fillIns(validLines);

        return this;
    }
    public Map<String,List<String>> getIns(){
        Map<String,List<String>> map = new HashMap<>();
        map.put("NORMAL",normalIns);
        map.put("INT0",int0Ins);
        map.put("INT1",int1Ins);
        map.put("INT2",int2Ins);
        map.put("INT3",int3Ins);
        return map;
    }

    private void parseLabelAndWord (List<String> validLines,int baseNum){
        int instructionNum = baseNum;
        for(String line:validLines){

            if(line.endsWith(":")){//label
                if("INT_0:".equals(line)){
                    labelMap.put(line.replace(":",""),216);

                }else if("INT_1:".equals(line)){
                    labelMap.put(line.replace(":",""),226);

                }else if("INT_2:".equals(line)){
                    labelMap.put(line.replace(":",""),236);

                }else if("INT_3:".equals(line)){
                    labelMap.put(line.replace(":",""),246);

                }else{
                    labelMap.put(line.replace(":",""),instructionNum);//label point to next instruction position

                }
                continue;
            }
            if(line.contains(".word")){//word 映射
                parseWord(line);
                continue;
            }

            instructionNum ++;
        }
        return;
    }
    private void fillIns (List<String> validLines){
        List<String> currentnins = normalIns;
        for(String line:validLines){

            if(isLable(line)){
                if("INT_0:".equals(line)){
                    currentnins = int0Ins;

                }else if("INT_1:".equals(line)){
                    currentnins = int1Ins;


                }else if("INT_2:".equals(line)){
                    currentnins = int2Ins;


                }else if("INT_3:".equals(line)){
                    currentnins = int3Ins;


                }else{
                    currentnins = normalIns;

                }
                continue;
            }
            if(isWord(line)){
                continue;
            }

            currentnins.add(line);
        }
        return;
    }
    private boolean isWord(String line){
        if(line == null){
            return false;
        }
        return line.contains(".word");
    }
    private boolean isLable(String line){
        if(line == null){
            return false;
        }
        return line.endsWith(":");
    }
    private void parseWord(String content){
        String[] segments = content.trim().split(" ");
        wordMap.put(segments[0], Integer.parseInt(segments[2]));
    }
}
