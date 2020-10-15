package com.mypls;

public class Test {

    public static void main(String[] args) {

       String test="Kim Wexler Professor ID: 13";

        String[] temp=test.split("Professor ID:");
        test=temp[1];
        System.out.println(" "+test);
    }
}
