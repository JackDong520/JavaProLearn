package com.netty.Server.Handler;

import com.netty.Server.NettyChannelMap;
import com.netty.Share.Request.RequestPicture;
import com.netty.Share.Request.RequestTalk;
import com.netty.Share.Request.RequestType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

public class PictureHandler extends SimpleChannelInboundHandler<RequestTalk> {
    private final ChannelGroup group;

    public PictureHandler(ChannelGroup group) {
        this.group = group;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyChannelMap.remove((SocketChannel) ctx.channel());
        System.out.println("PictureHandler channelInactive");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestTalk request) throws Exception {
        if (request.getRequestStatus() == RequestType.PICTURE) {
            System.out.println("接受到 00012 号请求");

            System.out.println("Picture" + request.getObj());
        }
        if (request.getRequestStatus() == 1) {
            System.out.println("接受到 1 号请求");
            System.out.println(request.getObj());
        }
        ReferenceCountUtil.release(request);
    }

    /**
     * 将新加入的连接放入group中
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        group.add(ctx.channel());
        System.out.println("PictureHandler handlerAdded");
    }

    /**
     * 删除group中下线的连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        group.remove(ctx.channel());
        System.out.println("PictureHandler handlerRemoved");
    }


}
