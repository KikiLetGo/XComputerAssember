package com.elexlab.xcomputerassmber;


import com.elexlab.xcomputerassmber.exceptions.BadInstructionException;
import com.elexlab.xcomputerassmber.utils.DataUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: InstructionsDecoder
 * @Description: TODO
 * @author: BruceYoung
 * @date: 2020年10月27日 17:23
 */
public class InstructionsDecoder {

    private static Map<String,String> opCodeMap = new HashMap<>();
    private static Map<String,String> registersAddressMap = new HashMap<>();
    private static Map<String,String> functMap = new HashMap<>();
    static {

        opCodeMap.put("add","100001");
        opCodeMap.put("sub","000000");
        opCodeMap.put("addi","100001");
        opCodeMap.put("subi","100001");

        opCodeMap.put("sw","001001");
        opCodeMap.put("lw","110001");
        opCodeMap.put("beq","000010");
        opCodeMap.put("jump","000100");


        registersAddressMap.put("r0","00000");
        registersAddressMap.put("r1","00001");
        registersAddressMap.put("r2","00010");
        registersAddressMap.put("r3","00011");
        registersAddressMap.put("r4","00100");
        registersAddressMap.put("r5","00101");
        registersAddressMap.put("r6","00110");
        registersAddressMap.put("r7","00111");
        registersAddressMap.put("rzero","00111");

        functMap.put("add","000110");
        functMap.put("addi","000110");
        functMap.put("sub","000100");
        functMap.put("subi","000100");
        functMap.put("and","000001");
        functMap.put("or","000000");
        functMap.put("sw","000110");
        functMap.put("lw","000110");
        functMap.put("beq","000100");

    }
    private int instructionNum;

    public InstructionsDecoder() {
    }

    public InstructionsDecoder(int instructionNum) {
        this.instructionNum = instructionNum;
    }

    /**
     *
     * @param instruction
     */
    public String decodeInstruction(String instruction){
        instruction = instruction.trim();
        if(isPseudoIns(instruction)){
            return decodeInstruction(new PseudoInstructionsDecoder().decodeInstruction(instruction));
        }
        System.out.println(instruction);

        String binCode = "";

        String opCode = extractOp(instruction);
        binCode += opCodeMap.get(opCode);
        if(isJumpIns(instruction)){
            int jumpPos = extractIm(instruction);
            binCode += DataUtils.integer2Bin(jumpPos,26);

        }else if(isImIns(instruction)){
            List<Integer> rns = extractRegisters(instruction,2);//rt,rs
            int rt = rns.get(0);
            int rs = rns.get(1);
            binCode += DataUtils.integer2Bin(rs,5);
            binCode += DataUtils.integer2Bin(rt,5);
            binCode += DataUtils.integer2Bin(extractIm(instruction),10);
            binCode += functMap.get(opCode);
        }else if(isRIns(instruction)){
            List<Integer> rns = extractRegisters(instruction,3);//rt,rs
            int rt = rns.get(0);
            int rs = rns.get(1);
            int rd = rns.get(2);
            binCode += DataUtils.integer2Bin(rs,5);
            binCode += DataUtils.integer2Bin(rt,5);
            binCode += DataUtils.integer2Bin(rd,5);
            binCode += "00000";
            binCode += functMap.get(opCode);

        }

        return binCode;
    }

    protected String extractOp(String ins){
        Pattern reg = Pattern.compile("(.+)(\\s+)");
        Matcher matcher = reg.matcher(ins);
        if(matcher.find()){
           return matcher.group(1);
        }else {
            throw new BadInstructionException("Can not found OpCode!");
        }

    }
    protected int extractIm(String ins){
        Pattern reg = Pattern.compile("[^r](\\d+)");
        return parseIntegers(reg,ins,1).get(0);

    }

    protected List<Integer> extractRegisters(String ins, int counts){
        Pattern reg = Pattern.compile("r([0-7])+");
        return parseIntegers(reg,ins,counts);

    }
    protected boolean isRIns(String ins){
        Pattern reg = Pattern.compile("(r[0-7],r[0-7],r[0-7])");
        return reg.matcher(ins).find();

    }
    protected boolean isImIns(String ins){
        Pattern reg = Pattern.compile("[^r]\\d+");
        return reg.matcher(ins).find();

    }

    protected boolean isJumpIns(String ins){
        return ins.toLowerCase().startsWith("jump");

    }
    private boolean isPseudoIns(String ins){
        Pattern reg = Pattern.compile("mov+");
        return reg.matcher(ins).find();

    }

    private List<Integer> parseIntegers(Pattern reg, String ins, int counts){
        Matcher matcher = reg.matcher(ins);
        List<Integer> integers = new ArrayList<>();
        while(matcher.find()){
            String im = matcher.group(1);
            integers.add(Integer.parseInt(im));
        }
        if(integers.size() != counts){
            throw new BadInstructionException("Integers counts not match!");

        }
        return integers;
    }





}
