package com.desktop.monitor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 封装一个协议
 *
 * @author Administrator
 */
public enum MyProtocol {
    // 图片
    IMAGE(1),
    // 登录
    LOGIN(2),
    // 退出
    LOGOUT(3);
    private final int index;

    MyProtocol(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static MyProtocol getMyProtocolByIndex(int index) {
        for (MyProtocol protocol : MyProtocol.values()) {
            if (index == protocol.getIndex()) {
                return protocol;
            }
        }
        return null;
    }

    public static void send(MyProtocol protocol, byte[] bytes, DataOutputStream dos) {
        int totalLen = 1 + 4 + bytes.length;
        try {
            dos.writeByte(protocol.getIndex());
            dos.writeInt(totalLen);
            dos.write(bytes);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}