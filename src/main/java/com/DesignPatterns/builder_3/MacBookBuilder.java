package com.DesignPatterns.builder_3;

public class MacBookBuilder extends Builder {
    private Computer mComputer = new Macbook();

    @Override
    public void buildBoard(String board) {
        mComputer.setmBoard(board);
    }

    @Override
    public void buildDisplay(String dispaly) {
        mComputer.setmBoard(dispaly);
    }

    @Override
    public void buildOs() {
        mComputer.setmOs();
    }

    @Override
    public Computer create() {
        return mComputer;
    }
}
