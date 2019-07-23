package com.broadcast;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Broadcast extends Thread {
    private final int port;


    public Broadcast(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receive() throws IOException {
        byte[] buffer = new byte[65507];
        @SuppressWarnings("resource")
        DatagramSocket ds = new DatagramSocket(port);
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        while (true) {
            ds.receive(packet);
            String s = new String(packet.getData(), 0, packet.getLength());
            System.out.println(packet.getAddress() + ":" + packet.getPort() + "    →    " + s);
        }
    }

    public void send(String msg, int port) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length,
                InetAddress.getByName("255.255.255.255"), port);
        ds.send(dp);
        ds.close();
    }

    public static void main(String args[]) throws IOException {
        Broadcast broadcast = new Broadcast(35502);
        broadcast.run();

        broadcast.send("sdasd",35502);

    }
}