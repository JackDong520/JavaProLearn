package com.DesignPatterns.builder_3;

import org.junit.Test;

public class Director {
    Builder mBuilder = null;

    public Director(Builder mBuilder) {
        this.mBuilder = mBuilder;
    }

    public void construct(String board, String display) {
        mBuilder.buildBoard(board);
        mBuilder.buildDisplay(display);
        mBuilder.buildOs();
    }

    public static class test{
        public static void main(String[] aegs){
            Builder builder = new MacBookBuilder();
            Director pcDirector = new Director(builder);
            pcDirector.construct("英特尔主板","Retina 显示器");
            System.out.println("Computer info : "+ builder.create().toString());
        }


    }
}
