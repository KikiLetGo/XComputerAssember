package com.elexlab.xcomputerassmber.exceptions;

/**
 * @ClassName: BadInstructionException
 * @Description: TODO
 * @author: BruceYoung
 * @date: 2020年10月27日 17:25
 */
public class BadInstructionException extends RuntimeException {
    public BadInstructionException() {
    }

    public BadInstructionException(String message) {
        super(message);
    }
}
