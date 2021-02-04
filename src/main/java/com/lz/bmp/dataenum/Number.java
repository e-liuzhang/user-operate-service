package com.lz.bmp.dataenum;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 14:48
 */

public enum Number {

    ZERO(0),

    ONE(1),

    TWO(2),

    THIRTY_TWO(32),
    ;

    private int number;

    Number(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
