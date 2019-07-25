package com.netty.Controller;

import com.netty.Server.NettyServerBootstrap;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GroupController {
    private ArrayList<GroupInfo> groupInfos;

    public GroupController() {
        groupInfos = new ArrayList<GroupInfo>();
    }

    /**
     * 传输groupHash 的值 来查找 ChannelGroup
     *
     * @param groupHash
     */
    public ChannelGroup getGrourp(int groupHash) {
        for (int i = 0; i < groupInfos.size(); i++) {
            if (groupInfos.get(i).getGroupHash() == groupHash) {
                return groupInfos.get(i).getChannelGroup();
            }
        }
        return null;
    }

    /**
     * 通过 groupHash值创建一个GroupChannel
     *
     * @param groupHash
     */
    public boolean createGroup(int groupHash) {
        if (getGrourp(groupHash) != null)
            return false;
        ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        groupInfos.add(new GroupInfo(groupHash, channelGroup));
        return true;
    }

    /**
     * 根据传入的hash值删除一个分组
     *
     * @param groupHash
     */
    public void deleteGroup(String groupHash) {

    }

    private class GroupInfo {
        private int groupHash;
        private ChannelGroup channelGroup;

        public GroupInfo() {
        }

        public GroupInfo(int groupHash, ChannelGroup channelGroup) {
            this.groupHash = groupHash;
            this.channelGroup = channelGroup;
        }

        public int getGroupHash() {
            return groupHash;
        }

        public void setGroupHash(int groupHash) {
            this.groupHash = groupHash;
        }

        public ChannelGroup getChannelGroup() {
            return channelGroup;
        }

        public void setChannelGroup(ChannelGroup channelGroup) {
            this.channelGroup = channelGroup;
        }
    }

    @Test
    public void test() {
        GroupController groupController = new GroupController();
        groupController.createGroup(123456789);
        groupController.createGroup(123465689);
        groupController.createGroup(123898989);
        groupController.createGroup(123566789);

        ChannelGroup channelGroup = groupController.getGrourp(123566789);
        System.out.println(channelGroup);
    }
}
