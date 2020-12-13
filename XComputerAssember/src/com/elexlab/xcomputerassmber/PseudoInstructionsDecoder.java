package com.elexlab.xcomputerassmber;


import com.elexlab.xcomputerassmber.exceptions.BadInstructionException;

import java.util.List;

public class PseudoInstructionsDecoder extends InstructionsDecoder{


    public String decodeInstruction(String instruction){
        System.out.println("伪指令："+instruction);

        String trueCode = "";

        trueCode += "addi ";
        if(isImIns(instruction)){
            int register = extractRegisters(instruction,1).get(0);
            trueCode += "r"+register+",r7,";
            int im = extractIm(instruction);
            trueCode += im;

        }else{
            List<Integer> rns = extractRegisters(instruction,2);//rt,rs
            int rt = rns.get(0);
            int rs = rns.get(1);
            trueCode += "r"+rt+",";
            trueCode += "r"+rs+",r7";
        }

        return trueCode;
    }
}
