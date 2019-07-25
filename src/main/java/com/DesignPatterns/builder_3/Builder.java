package com.DesignPatterns.builder_3;

/**
 * 对于复杂的对象的构造
 */
public abstract class Builder {
    public abstract void buildBoard(String board);
    public abstract void buildDisplay(String dispaly);
    public abstract void buildOs();
    public abstract Computer create();
}
