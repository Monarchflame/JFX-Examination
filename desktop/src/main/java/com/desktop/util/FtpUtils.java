package com.desktop.util;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;

/**
 * @author qxt
 * @date 2021/3/10 23:25
 */
@Slf4j
public class FtpUtils {

    public static final String FTP_PROPERTIES_PATH = "desktop/src/main/resources/ftp.properties";

    /**
     * 利用JSch包实现SFTP向服务器上传文件
     *
     * @param file                本地文件路径
     * @param remoteDirectoryPath 服务器保存路径
     * @param fileName            保存在服务器中的文件名
     * @throws Exception
     */
    public static boolean sshPut(File file, String remoteDirectoryPath, String fileName) throws Exception {
        byte[] bytes = toByteArray(file);
        Properties properties = new Properties();

        FileReader fileReader = new FileReader(new File(FTP_PROPERTIES_PATH));
        properties.load(fileReader);
        String ip = properties.getProperty("ip");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        int port = Integer.parseInt(properties.getProperty("port"));
        String rootPath = properties.getProperty("rootPath");

        Session session = null;
        Channel channel = null;

        JSch jsch = new JSch();

        if (port <= 0) {
            //连接服务器，采用默认端口
            session = jsch.getSession(user, ip);
        } else {
            //采用指定的端口连接服务器
            session = jsch.getSession(user, ip, port);
        }

        //如果服务器连接不上，则抛出异常
        if (session == null) {
            throw new Exception("session is null");
        }

        //设置登陆主机的密码
        session.setPassword(password);
        //设置第一次登陆的时候提示，可选值：(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        //设置登陆超时时间
        session.connect(30000);

        OutputStream outstream = null;
        try {
            // 创建sftp通信通道
            channel = (Channel) session.openChannel("sftp");
            channel.connect(1000);
            ChannelSftp sftp = (ChannelSftp) channel;
            // 进入根文件夹
            sftp.cd(rootPath);
            // 判断子目录文件夹是否存在，不存在即创建
            SftpATTRS attrs = null;
            try {
                attrs = sftp.stat(remoteDirectoryPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (attrs == null) {
                sftp.mkdir(remoteDirectoryPath);
                log.info("创建子目录：{}", remoteDirectoryPath);
            }
            // 进入服务器指定的文件夹
            sftp.cd(remoteDirectoryPath);
            //以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
            outstream = sftp.put(fileName);
            outstream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关流操作
            if (outstream != null) {
                outstream.flush();
                outstream.close();
            }
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
        return true;
    }

    /**
     * 将文件转换为比特流
     */
    private static byte[] toByteArray(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            int bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, bufSize))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    /**
     * 从服务器下载文件
     *
     * @param directoryPath       下载文件保存的地址
     * @param remoteDirectoryPath 服务器保存路径
     * @param fileName            要下载的文件名
     * @throws Exception
     */
    public static boolean sshPull(String directoryPath, String remoteDirectoryPath, String fileName) throws Exception {
        Properties properties = new Properties();


        FileReader fileReader = new FileReader(new File(FTP_PROPERTIES_PATH));
        properties.load(fileReader);

        String ip = properties.getProperty("ip");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        int port = Integer.parseInt(properties.getProperty("port"));
        String rootPath = properties.getProperty("rootPath");

        Session session = null;
        Channel channel = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        JSch jsch = new JSch();

        if (port <= 0) {
            //连接服务器，采用默认端口
            session = jsch.getSession(user, ip);
        } else {
            //采用指定的端口连接服务器
            session = jsch.getSession(user, ip, port);
        }

        //如果服务器连接不上，则抛出异常
        if (session == null) {
            throw new Exception("session is null");
        }

        //设置登陆主机的密码
        session.setPassword(password);
        //设置第一次登陆的时候提示，可选值：(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        //设置登陆超时时间
        session.connect(30000);

        try {
            //创建sftp通信通道
            channel = (Channel) session.openChannel("sftp");
            channel.connect(1000);
            ChannelSftp sftp = (ChannelSftp) channel;

            // 进入根文件夹
            sftp.cd(rootPath);
            // 判断子目录文件夹是否存在，不存在即创建
            SftpATTRS attrs = null;
            try {
                attrs = sftp.stat(remoteDirectoryPath);
            } catch (Exception e) {
//                e.printStackTrace();
            }
            if (attrs == null) {
                log.info("服务器存放考题文件夹：{}不存在", rootPath + remoteDirectoryPath);
                return false;
            }

            //进入服务器指定的文件夹
            sftp.cd(remoteDirectoryPath);
            // 判断本机文件夹是否存在
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                log.info("新建存放考题文件夹：{}", directoryPath);
                directory.mkdirs();
            }
            // 获取文件
            inputStream = sftp.get(fileName);
            fileOutputStream = new FileOutputStream(directoryPath + "//" + fileName);
            byte[] b = new byte[1024];
            while ((inputStream.read(b)) != -1) {
                // 写入文件
                fileOutputStream.write(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关流操作
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
        return true;
    }
}