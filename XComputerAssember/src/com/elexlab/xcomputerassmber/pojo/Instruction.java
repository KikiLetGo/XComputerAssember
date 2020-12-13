package com.elexlab.xcomputerassmber.pojo;

/**
 * @ClassName: Instruction
 * @Description: TODO
 * @author: DJ008854
 * @date: 2020年12月04日 17:01
 */
public class Instruction {
    private String asm;
    private String hex;
    private String bin;
    private int lineNumber;

    public String getAsm() {
        return asm;
    }

    public void setAsm(String asm) {
        this.asm = asm;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
