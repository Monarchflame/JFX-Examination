package com.desktop.monitor;

import com.desktop.util.AlertMaker;
import com.desktop.util.Constant;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author qxt
 * @date 2020/12/26 22:15
 */
public class MonitorTread implements Runnable {
    @Override
    public void run() {
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
        // 教师端端口
        int teacherPort = 33000;
        boolean connectResult = monitorClient.connect(teacherHost, teacherPort);
        if (!connectResult) {
            AlertMaker.showErrorMessage("错误","连接教师端失败！");
            return;
        }
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
}
