package com.desktop.monitor;

import com.desktop.util.Constant;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 监考学生端
 *
 * @author qxt
 */
@Slf4j
public class MonitorClient implements Runnable {
    // 教师端端口
    private static final int TEACHER_PORT = 33000;

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
    private boolean connect(InetAddress address, int port) {
        try {
            socket = new Socket(address, port);
            dos = new DataOutputStream(socket.getOutputStream());
            log.info("连接" + address + ":" + port + "成功");
            return true;
        } catch (IOException e) {
            log.info("连接" + address + ":" + port + "失败");
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
        MyProtocol.send(MyProtocol.LOGIN, bytes, dos);
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
            MyProtocol.send(MyProtocol.IMAGE, outputStream.toByteArray(), dos);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public void close() {
        MyProtocol.send(MyProtocol.LOGOUT, new String("logout").getBytes(), dos);
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

    @Override
    public void run() {
        boolean connectResult = false;
        while (!connectResult) {
            MonitorClient monitorClient = Constant.student.getMonitorClient();
            if (monitorClient == null) {
                monitorClient = new MonitorClient();
                Constant.student.setMonitorClient(monitorClient);
            }
            // 教师端IP todo:这里先写死
            InetAddress teacherHost = null;
            try {
                teacherHost = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return;
            }

            connectResult = monitorClient.connect(teacherHost, TEACHER_PORT);
            if (connectResult) {
                // 登录
                monitorClient.load();
                while (monitorClient.isLive) {
                    monitorClient.sendImage(monitorClient.getScreenShot());
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                Thread.sleep(100);
                log.info("重新连接中");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        Thread thread = new Thread(new MonitorClient());
        thread.start();
    }
}
