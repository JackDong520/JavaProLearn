package com.netty.Controller;

import com.netty.Share.RequestType;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.junit.Test;

import java.util.ArrayList;

public class GroupController {
    private ArrayList<GroupInfo> groupInfos;
    private static ChannelGroup channelGroup;
    private GroupInfo groupInfo;

    static {
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    }


    public GroupController() {
        groupInfos = new ArrayList<GroupInfo>();
        this.groupInfo = new GroupInfo();
    }

    /**
     * 传输groupHash 的值 来查找 ChannelGroup
     *
     * @param groupHash
     */
    public ChannelGroup getChannelGrourp(int groupHash) {
        groupInfo = getGroup(groupHash);
        if (groupInfo == null)
            return null;
        return groupInfo.getChannelGroup();
    }

    private GroupInfo getGroup(int groupHash) {
        for (int i = 0; i < groupInfos.size(); i++) {
            if (groupInfos.get(i).getGroupHash() == groupHash) {
                return groupInfos.get(i);
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
        if (getChannelGrourp(groupHash) != null)
            return false;
        ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        groupInfos.add(new GroupInfo(groupHash, channelGroup));
        return true;
    }

    public int addGroup(int groupHsh, Channel channel) {
        groupInfo = getGroup(groupHsh);
        if (groupInfo == null)
            return RequestType.ROOM_ADD_FAIL_DONTEXIST;//要加入的房间不存在
        channelGroup = groupInfo.getChannelGroup();
        for (Channel mChannel : channelGroup) {
            if (mChannel.equals(channel))
                return RequestType.ROOM_ADD_FAIL_HASADD;//不存在要加入的房间
        }
        channelGroup.add(channel);
        return RequestType.ROOM_ADD_SUCCESS;
    }

    /**
     * 根据传入的hash值删除一个分组
     *
     * @param groupHash
     */
    public boolean quitGroup(int groupHash, Channel channel) {
        channelGroup = getChannelGrourp(groupHash);
        if (channelGroup == null)
            return false;

        System.out.println(channelGroup.remove(channel));

        return true;
    }

    /**
     * 销毁分组
     *
     * @param groupHash
     */

    public void destroyGroup(int groupHash) {
        channelGroup = getChannelGrourp(groupHash);
        for (Channel channel : channelGroup) {
            channelGroup.remove(channel);
        }
        channelGroup.close();
        groupInfos.remove(getGroup(groupHash));

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

        ChannelGroup channelGroup = groupController.getChannelGrourp(123566789);
        System.out.println(channelGroup);
    }
}
