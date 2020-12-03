package com.elexlab.xcomputerassmber;


import com.elexlab.xcomputerassmber.exceptions.BadInstructionException;

public class PseudoInstructionsDecoder {

    public String decodeInstruction(String instruction){
        instruction = instruction.trim();
        String[] segments = instruction.split(" ");
        if(segments == null || segments.length != 2){
            throw new BadInstructionException("instruction must contains code and register");
        }
        String code = segments[0];
        String registersOrIm = segments[1];
        String trueCode = "";
        if("movi".equals(code)){
            trueCode += "addi ";
            trueCode += parseMovi(registersOrIm);
        }
        if("mov".equals(code)){
            trueCode += "addi ";
            trueCode += parseMov(registersOrIm);
        }
        return trueCode;
    }
    private String parseMovi(String registersOrIm){
        String[] segments = registersOrIm.split(",");
        String rt = segments[0];
        String im = segments[1];
        String trueCode = "";
        trueCode += rt;
        trueCode += ",r7,";
        trueCode += im;
        return trueCode;
    }
    private String parseMov(String registersOrIm){
        String[] segments = registersOrIm.split(",");
        String rt = segments[0];
        String rs = segments[1];
        String trueCode = "";
        trueCode += rt;
        trueCode += ","+rs+",0";
        return trueCode;
    }
}
