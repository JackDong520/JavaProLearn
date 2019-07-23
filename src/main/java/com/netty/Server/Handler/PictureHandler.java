package com.netty.Server.Handler;

import com.netty.Server.NettyChannelMap;
import com.netty.Share.Request.RequestPicture;
import com.netty.Share.Request.RequestType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

public class PictureHandler extends SimpleChannelInboundHandler<RequestPicture> {
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyChannelMap.remove((SocketChannel)ctx.channel());
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestPicture request) throws Exception {
        if (request.getRequestStatus() == RequestType.PICTURE){
            System.out.println("接受到 00012 号请求");

            System.out.println( request.getObj());
        }
        if (request.getRequestStatus() == 1) {
            System.out.println("接受到 1 号请求");
            System.out.println(request.getObj());
        }
        ReferenceCountUtil.release(request);
    }


}
