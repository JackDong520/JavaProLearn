package com.netty.Server.Handler;

import com.netty.Share.Request.RequestTalk;
import com.netty.Share.Request.RequestType;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class ChatHandler extends SimpleChannelInboundHandler<RequestTalk> {
    private final ChannelGroup group;

    public ChatHandler(ChannelGroup group) {
        this.group = group;
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
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestTalk msg) throws Exception {
        if (msg.getRequestStatus() == RequestType.PICTURE) {
            System.out.println(msg.getObj());
            Channel incoming = ctx.channel();
            for (Channel channel : group) {
                if (channel != incoming) {
                    channel.writeAndFlush(msg.getName()+":"+msg.getObj() + "\n");
                } else {
                    channel.writeAndFlush(msg.getName()+":"+msg.getObj() + "\n");
                }
            }

        }
    }
}