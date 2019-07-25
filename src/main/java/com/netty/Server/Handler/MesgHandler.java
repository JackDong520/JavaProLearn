package com.netty.Server.Handler;


import com.netty.Server.NettyChannelMap;
import com.netty.Share.Request.RequestPicture;

import com.netty.Share.RequestType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by yaozb on 15-4-11.
 */
public class MesgHandler extends SimpleChannelInboundHandler<RequestPicture> {
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyChannelMap.remove((SocketChannel) ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestPicture request) throws Exception {
        if (request.getRequestStatus() == RequestType.PICTURE) {
            System.out.println("���ܵ�����");
            System.out.println("MesgHandle this msg");
            System.out.println("MesgHandle" + request.getObj());
            ctx.writeAndFlush("MesgHandle ��������" + request.getObj());
        }
        if (request.getRequestStatus() == 1) {
            System.out.println("���ܵ� 1 ������");
            System.out.println(request.getObj());
        }
        ReferenceCountUtil.release(request);
    }

    /**
     * ���¼�������ӷ���group��
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        System.out.println("MesgHandler handlerAdded");
    }

    /**
     * ɾ��group�����ߵ�����
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MesgHandler handlerRemoved");
    }


}
