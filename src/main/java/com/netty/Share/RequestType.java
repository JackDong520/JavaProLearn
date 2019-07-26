package com.netty.Share;

public final class RequestType {
    public static final int TALK = 1;
    public static final int PICTURE = 2;

    public static final int PICTURE_DATA = 3;

    public static final int ROOM = 11;
    public static final int ROOM_CREATE = 12;
    public static final int ROOM_ADD = 13;
    public static final int ROOM_QUIT = 14;
    public static final int ROOM_DESTROY = 15;


    public static final int ROOM_ADD_SUCCESS = 21;
    public static final int ROOM_ADD_FAIL = 22;
    public static final int ROOM_DESTROY_FAIL = 23;
    public static final int ROOM_ADD_FAIL_HASADD = 24;
    public static final int ROOM_ADD_FAIL_DONTEXIST = 25;

    public static final int ROOM_EMPTY = 31;
}
