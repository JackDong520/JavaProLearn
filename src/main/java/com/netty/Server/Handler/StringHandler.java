package com.netty.Server.Handler;

import com.google.gson.Gson;
import com.netty.Controller.GroupController;
import com.netty.Share.Request.RequestPicture;
import com.netty.Share.RequestType;
import com.netty.Share.TransportData.PaintData;
import com.netty.Share.TransportData.RoomData;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


public class StringHandler extends SimpleChannelInboundHandler<String> {
    private Gson gson;
    private static RequestPicture requestPicture;
    private static GroupController groupController;
    private static ChannelGroup channelGroup;
    private static Channel incoming;


    public StringHandler() {
        this.gson = new Gson();
        requestPicture = new RequestPicture();
        groupController = new GroupController();
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) {
        requestPicture = gson.fromJson(s, RequestPicture.class);
        System.out.println(requestPicture.getType());
        System.out.println(s);
        switch (requestPicture.getType()) {
            case RequestType.ROOM:
                RoomData roomData = gson.fromJson(requestPicture.getObj(), RoomData.class);
                if (roomData.getRoomAction() == RequestType.ROOM_CREATE) {
                    if (!groupController.createGroup(roomData.getRoomHash())) {
                        System.out.println("room exist");
                        break;
                    }
                    groupController.getChannelGrourp(roomData.getRoomHash()).add(ctx.channel());
                    System.out.println("Room ADD success：" + groupController.getChannelGrourp(roomData.getRoomHash()));
                }
                if (roomData.getRoomAction() == RequestType.ROOM_ADD) {
                    int status = groupController.addGroup(roomData.getRoomHash(), ctx.channel());
                    if (status == RequestType.ROOM_ADD_FAIL_DONTEXIST) {
                        System.out.println("ROOM_ADD_FAIL_DONTEXIST");
                    }
                    if (status == RequestType.ROOM_ADD_FAIL_HASADD) {
                        System.out.println("ROOM_ADD_FAIL_HASADD");
                    }
                    if (status == RequestType.ROOM_ADD_SUCCESS) {
                        System.out.println("ROOM_ADD_SUCCESS");
                    }

                }
                if (roomData.getRoomAction() == RequestType.ROOM_QUIT) {
                    if (groupController.quitGroup(roomData.getRoomHash(), ctx.channel()))
                        System.out.println("quit room：" + groupController.getChannelGrourp(roomData.getRoomHash()));
                    else
                        System.out.println("don'exist room");

                }
                if (roomData.getRoomAction() == RequestType.ROOM_DESTROY) {
                    if (groupController.getChannelGrourp(roomData.getRoomHash()) != null) {
                        groupController.destroyGroup(roomData.getRoomHash());
                        System.out.println("destroy room !");
                    } else {
                        System.out.println("destroy room fail");
                    }

                }
                break;
            case RequestType.PICTURE_DATA:
                PaintData paintData = gson.fromJson(requestPicture.getObj(), PaintData.class);
                channelGroup = groupController.getChannelGrourp(paintData.getRoomHash());
                if (!channelGroup.contains(ctx.channel()))
                {
                    System.out.println("you don't in the room");
                    break;
                }


                System.out.println(channelGroup);
                incoming = ctx.channel();
                for (Channel channel : channelGroup) {
                    if (channel != incoming) {
                        channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + s + "\n");
                    } else {
                        channel.writeAndFlush("[you]" + s + "\n");
                    }
                }
                break;

        }

        ctx.writeAndFlush(s);
    }
}
