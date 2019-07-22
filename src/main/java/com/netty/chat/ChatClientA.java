package com.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatClientA {

    private static Channel myChannel;

    public ChatClientA() {
        this.myChannel = null;
    }



    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                            ch.pipeline().addLast("decoder", new StringDecoder());
                            ch.pipeline().addLast("encoder", new StringEncoder());
                            ch.pipeline().addLast("handler", new ChatClientHandler());

                        }
                    });
            ChannelFuture future = bootstrap.connect("localhost", 8080).sync();
            myChannel = future.channel();

//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            while (true) {
//                writeData(in.readLine());
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }
    public  void writeData(String data){
        System.out.println(myChannel.remoteAddress());
        myChannel.writeAndFlush("ChatClientA说："+ data + "\r\n");

    }

    public static void main(String[] args) throws IOException {
        // new ChatClientA().start();
       ChatClientA chatClientA = new ChatClientA();
        chatClientA.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            chatClientA.writeData(in.readLine());
        }
    }

}