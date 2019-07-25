package com.netty.Server.Handler;

import com.google.gson.Gson;
import com.netty.Controller.GroupController;
import com.netty.Share.Request.RequestPicture;
import com.netty.Share.RequestType;
import com.netty.Share.TransportData.RoomData;
import com.sun.org.apache.regexp.internal.RE;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class StringHandler extends SimpleChannelInboundHandler<String> {
    private Gson gson;
    private static RequestPicture requestPicture;
    private static GroupController groupController;

    public StringHandler() {
        this.gson = new Gson();
        requestPicture = new RequestPicture();
        groupController = new GroupController();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        RequestPicture requestPicture = gson.fromJson(s, RequestPicture.class);
        System.out.println(requestPicture.getType());
        switch (requestPicture.getType()) {
            case RequestType.ROOM:
                RoomData roomData = gson.fromJson(requestPicture.getObj(), RoomData.class);
                if (roomData.getRoomAction() == RequestType.ROOM_CREATE) {
                    groupController.createGroup(roomData.getRoomHash());
                    System.out.println(groupController.getGrourp(roomData.getRoomHash()));
                }
        }
        System.out.println(s);
        ctx.writeAndFlush(s);
    }
}
