package com.DesignPatterns.builder_3;

public abstract class Computer {
    protected String mBoard;
    protected String mDisplay;
    protected String mOs;

    public String getmBoard() {
        return mBoard;
    }

    public void setmBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    public String getmDisplay() {
        return mDisplay;
    }

    public void setmDisplay(String mDisplay) {
        this.mDisplay = mDisplay;
    }


    public abstract void setmOs();

    @Override
    public String toString() {
        return "Computer [mBoard = " + mBoard + ";mDisplay=" + mDisplay + ";mOs=" + mOs + "]";
    }
}
