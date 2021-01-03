package com.desktop.monitor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 连接到教师端
 *
 * @author qxt
 */
public class MonitorClient {
    public Socket socket;

    public DataOutputStream dos = null;
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int width = (int) screenSize.getWidth();
    public int height = (int) screenSize.getHeight();
    public Robot robot;
    public boolean isLive = true;

    public MonitorClient() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接
     */
    public boolean connect(InetAddress address, int port) {
        try {
            socket = new Socket(address, port);
            dos = new DataOutputStream(socket.getOutputStream());
            // dos.writeUTF("client");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            // 连接失败
            System.out.println("连接失败");
            return false;
        }
    }

    /**
     * 获取屏幕截图
     *
     * @return
     */
    public BufferedImage getScreenShot() {
        BufferedImage bfImage = robot.createScreenCapture(new Rectangle(0, 0, width, height));
        return bfImage;
    }

    /**
     * 登录
     */
    public void load() {
        byte[] bytes = "client".getBytes();
        Protocol.send(Protocol.TYPE_LOAD, bytes, dos);
    }

    /**
     * 发送屏幕截图
     *
     * @param buff
     */
    public void sendImage(BufferedImage buff) {
        if (buff == null) {
            return;
        }
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(buff, "png", outputStream);
            Protocol.send(Protocol.TYPE_IMAGE, outputStream.toByteArray(), dos);
            outputStream.close();
            System.out.println("send file successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public void close() {
        Protocol.send(Protocol.TYPE_LOGOUT, new String("logout").getBytes(), dos);
        try {
            if (dos != null) {
                dos.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片缩放
     *
     * @param bfImage
     * @param scale
     * @return
     */
    public BufferedImage scale(BufferedImage bfImage, double scale) {
        int width = bfImage.getWidth();
        int height = bfImage.getHeight();
        Image image = bfImage.getScaledInstance((int) (width * scale), (int) (height * scale), Image.SCALE_DEFAULT);
        BufferedImage tag = new BufferedImage((int) (width * scale), (int) (height * scale), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = tag.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return tag;
    }

//    public static void main(String[] args) throws UnknownHostException {
//        final MonitorClient client = new MonitorClient();
//        client.connect(InetAddress.getLocalHost(), 33000);
//        client.load();
//        while (client.isLive) {
//            client.sendImage(client.getScreenShot());
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException ev) {
//
//            }
//        }
//    }
}
