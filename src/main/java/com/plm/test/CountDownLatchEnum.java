package com.plm.test;

import lombok.Getter;

/**
 *  Enum 和 CountDownLatch 结合使用
 */
public enum CountDownLatchEnum {
    ONE(1,"韩商言"),TWO(2,"白十三"),THREE(3,"良乡"),FOUR(4,"药不然"),FIVE(5,"李现");

    @Getter
    private Integer retCode;
    @Getter
    private String retMessage;

    CountDownLatchEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountDownLatchEnum forEach_Enum(int index){
        CountDownLatchEnum[] myEntry = CountDownLatchEnum.values();
        for(CountDownLatchEnum element : myEntry){
            if(index == element.getRetCode()){
                return element;
            }
        }
        return null;
    }
}
