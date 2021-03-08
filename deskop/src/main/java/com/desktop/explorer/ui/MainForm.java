package com.desktop.explorer.ui;

import com.desktop.explorer.util.*;
import com.desktop.util.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainForm extends JFrame implements ActionListener {
    public static MainForm instance;
    private JPanel rootPanel, showPanel, functionPanel;
    private JScrollPane scrollShow;
    private JButton homeButton, newFolderButton;
    public String curURL = "";
    public JList<String> list;
    private DefaultListModel defaultListModel;
    public Stack<String> stack, stackReturn;
    private JPopupMenu fileJPopupMenu;
    private JPopupMenu deleteJPopupMenu;
    private JMenuItem[] fileJMenuItems = new JMenuItem[10];
    private JMenuItem deleteJMenuItem = new JMenuItem("删除");
    //保存GB,MB,KB,B对应的字节数，方便换算文件大小及单位
    long[] sizes = {1073741824, 1048576, 1024, 1};
    String[] sizeNames = {"GB", "MB", "KB", "B"};

    public String studentNo = "2017";

    public MainForm() {
        instance = this;
        this.setTitle("答案文件夹");
        this.setBounds(500, 500, 810, 650);
        this.getContentPane().setLayout(null);
        init();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
    }

    public void init() {
        // 属性初始化
        studentNo = Constant.student.getStudentNo();

        // 各个panel初始化
        rootPanel = new JPanel();
        showPanel = new JPanel();
        functionPanel = new JPanel();
        rootPanel.setBounds(5, 5, 800, 50);
        rootPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

        //中上导航栏
        functionPanel = new JPanel();
        functionPanel.setBounds(5, 20, 790, 30);
        functionPanel.setLayout(null);
        homeButton = new JButton("Home");
        homeButton.setFont(new Font("Serif", Font.PLAIN, 15));
        homeButton.setBounds(0, 5, 80, 25);
        homeButton.addActionListener(this);
        newFolderButton = new JButton("新建文件夹");
        newFolderButton.setFont(new Font("Serif", Font.PLAIN, 15));
        newFolderButton.setBounds(90, 5, 150, 25);
        newFolderButton.addActionListener(this);

        functionPanel.add(homeButton);
        functionPanel.add(newFolderButton);
        this.add(functionPanel);

        //中部文件列表
        stack = new Stack<String>();
        stackReturn = new Stack<String>();
        showPanel.setSize(800, 610);
        showPanel.setLocation(0, 90);
        showPanel.setLayout(null);
        list = new JList<String>();
        //文件/文件夹的属性菜单
        fileJPopupMenu = new JPopupMenu();
        fileJMenuItems[0] = new JMenuItem("打开");
        fileJMenuItems[1] = new JMenuItem("删除");
        fileJMenuItems[2] = new JMenuItem("重命名");
        fileJMenuItems[3] = new JMenuItem("属性");
        //文件/文件夹的属性菜单初始化
        for (int k = 0; k < 4; ++k) {
            fileJMenuItems[k].addActionListener(this);
            fileJPopupMenu.add(fileJMenuItems[k]);
        }

        deleteJPopupMenu = new JPopupMenu();
        deleteJMenuItem.addActionListener(this);
        deleteJPopupMenu.add(deleteJMenuItem);
        list.add(deleteJPopupMenu);

        list.add(fileJPopupMenu);

        homeList();//显示磁盘根目录
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (list.getSelectedIndex() != -1) {
                    if (e.getClickCount() == 1) {//单击list时，暂无事件

                    } else if (e.getClickCount() == 2) {//双击list时，打开文件或进入该子目录
                        System.out.println(list.getSelectedValue());
                        twoClick(list.getSelectedValue());
                    }
                    if (e.getButton() == 3) {//右击list时，打开菜单栏
                        if (!"".equals(curURL)) {
                            if (list.getSelectedValuesList().size() == 1) {
                                fileJPopupMenu.show(list, e.getX(), e.getY()); //如果右击的是单个文件夹和文件，则应打开一个功能齐全的菜单栏
                            } else if (list.getSelectedValuesList().size() > 1) {//如果选中多个文件夹和文件，则只支持删除功能
                                deleteJPopupMenu.show(list, e.getX(), e.getY());
                            }
                        }
                    }
                }
            }
        });
        scrollShow = new JScrollPane(list);
        showPanel.add(scrollShow);
        scrollShow.setSize(790, 520);
        scrollShow.setLocation(5, 5);
        this.add(showPanel);
        this.add(rootPanel);
    }

    /**
     * 点击两次时的事件
     *
     * @param choice
     */
    public void twoClick(String choice) {
        choice += "\\";
        File file = new File(curURL + choice);
        if (file.isDirectory()) {
            curURL += choice;
            stack.push(curURL);
            goThere();
        } else {
            openIt(file);
        }
    }

    /**
     * 回到初始界面
     */
    public void homeList() {
        System.out.println(studentNo);
        curURL = "F:\\" + studentNo + "\\";

        File folder = new File(curURL);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        defaultListModel = new DefaultListModel();
        String[] getString = GetFileNames.getFileName(curURL);
        for (String s : getString) {
            defaultListModel.addElement(s);
        }
        Icon[] icons = GetFileIcon.getSmallIcon(curURL);
        list.setModel(defaultListModel);
        list.setCellRenderer(new MyCellRenderer(icons));
        stack.push(curURL);
    }

    /**
     * 调用电脑中的程序“打开”文件的方法
     *
     * @param file
     */
    public void openIt(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * 跳转函数
     */
    public void goThere() {
        if (!"".equals(curURL)) {
            defaultListModel.clear();
            String[] getString = GetFileNames.getFileName(curURL);
            for (String s : getString) {
                defaultListModel.addElement(s);
            }
            Icon[] icons = GetFileIcon.getSmallIcon(curURL);
            list.setModel(defaultListModel);
            list.setCellRenderer(new MyCellRenderer(icons));
        } else {//Cur_URL为空时，就跳转回根目录
            homeList();
        }
    }

    /**
     * 新建文件夹
     */
    private void newFolder() {
        File rootFolder = new File(curURL);
        String newFolderName = "新建文件夹";
        if (rootFolder.list().length == 0) {
            new File(curURL + newFolderName).mkdirs();
            homeList();
            return;
        }
        String[] folderList = rootFolder.list();
        Arrays.sort(folderList);
        Stream<String> folderListStream = Arrays.stream(folderList).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                String pattern = "新建文件夹\\s\\((\\d)\\)";
                return Pattern.matches(pattern, s) || "新建文件夹".equals(s);
            }
        });
        List<String> collect = folderListStream.collect(Collectors.toList());
        if (collect.isEmpty()) {
            new File(curURL + newFolderName).mkdirs();
            homeList();
            return;
        }
        String lastFolderName = collect.get(collect.size() - 1);
        if (lastFolderName.equals(newFolderName)) {
            new File(curURL + newFolderName + " (1)").mkdirs();
            homeList();
            return;
        } else {
            String pattern = "新建文件夹\\s\\((\\d)\\)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(lastFolderName);
            if (m.find()) {
                int number = Integer.parseInt(m.group(1)) + 1;
                new File(curURL + newFolderName + " (" + number + ")").mkdirs();
                homeList();
            } else {
                System.out.println("NO MATCH");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fileJMenuItems[0]) {    //打开文件/文件夹
            String url = curURL + list.getSelectedValue();
            if (!"".equals(curURL)) {
                url += "\\";
            }
            File file = new File(url);
            if (file.isDirectory()) {
                twoClick(url);
            } else {
                openIt(file);
            }
        } else if (e.getSource() == homeButton) {
            homeList();
        } else if (e.getSource() == newFolderButton) {
            newFolder();
        } else if (e.getSource() == fileJMenuItems[1]) {//删除
            File file = new File(curURL + "/" + list.getSelectedValue());
            int n;
            if (file.isFile()) {
                n = JOptionPane.showConfirmDialog(null, "确定要删除文件 " + file.getName() + " 么?", "文件删除", JOptionPane.YES_NO_OPTION);
            } else {
                n = JOptionPane.showConfirmDialog(null, "确定要删除 " + file.getName() + " 及其目录下的文件么?", "文件夹删除", JOptionPane.YES_NO_OPTION);
            }
            if (n == 0) {
                FileDelete.delete(curURL + list.getSelectedValue() + "\\");
                goThere();
            }
        } else if (e.getSource() == deleteJMenuItem) {//多选下的删除
            List<String> selectedStr = list.getSelectedValuesList();
            File file;
            int num = selectedStr.size();
            int n = JOptionPane.showConfirmDialog(null, "确定要删除 " + selectedStr.get(0) + " 等" + num + "项么?", "文件删除", JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                for (String s : selectedStr) {
                    FileDelete.delete(curURL + s + "\\");
                }
                goThere();
            }
        } else if (e.getSource() == fileJMenuItems[2]) {//重命名
            String before = list.getSelectedValue();
            File file = new File(curURL + before + "\\");
            String after = "";
            if (file.isDirectory()) {
                after = (String) JOptionPane.showInputDialog(null, "请输入新文件夹名:\n", "重命名", JOptionPane.PLAIN_MESSAGE, null, null,
                        list.getSelectedValue());
            } else {
                after = (String) JOptionPane.showInputDialog(null, "请输入新文件名:\n", "重命名", JOptionPane.PLAIN_MESSAGE, null, null,
                        list.getSelectedValue());
            }
            if (!before.equals(after) && after != null) {
                new File(curURL + before + "\\").renameTo(new File(curURL + after + "\\"));
                goThere();
            } else {
                goThere();
            }
        } else if (e.getSource() == fileJMenuItems[3]) {//打开文件/文件夹属性窗口
            String temp_url = curURL + list.getSelectedValue() + "\\";
            File file = new File(temp_url);
            Icon icon = GetFileIcon.getSingleSmallIcon(temp_url);
            String name = list.getSelectedValue();
            long size;
            double final_size;
            long File_Num = 0, Directory_Num = 0;
            int flag = 0;
            String file_size = "";

            String Create_Time = FileTime.getCreateTime(temp_url);
            String Modify_Time = FileTime.getModifiedTime(temp_url);
            String Last_Access = FileTime.getLatestAccessTime(temp_url);

            if (file.isDirectory()) {//目录属性初始化所需参数
                DirectoryInfo DInfo = new DirectoryInfo();
                size = DirectoryInfo.instance.getDirSize(file);
                File_Num = DInfo.File_Num;
                Directory_Num = DInfo.Directory_Num;
                flag = 1;
            } else {//文件属性初始化所需参数
                size = file.length();
            }
            final_size = 0;
            for (int i = 0; i < sizes.length; ++i) {
                if (size / sizes[i] > 0) {
                    final_size = size * 1.0 / sizes[i];
                    DecimalFormat fnum = new DecimalFormat("##0.00");
                    file_size = fnum.format(final_size) + sizeNames[i];
                    break;
                }
            }
            if (flag == 1) {
                FileProperties properties = new FileProperties(icon, name, file_size, File_Num, Directory_Num - 1, temp_url, Create_Time);
            } else {
                FileProperties properties = new FileProperties(icon, name, file_size, temp_url, Create_Time, Modify_Time, Last_Access);
            }
        }
    }

    public static void main(String[] args) {
        MainForm m = new MainForm();
    }
}
