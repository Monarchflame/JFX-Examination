package com.desktop.invigilation;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;

public class KeyboardHook implements Runnable {
    private WinUser.HHOOK hhk;
    // 钩子回调函数
    private WinUser.LowLevelKeyboardProc keyboardProc = new WinUser.LowLevelKeyboardProc() {
        /**
         * 回调函数 <link>https://docs.microsoft.com/en-us/previous-versions/windows/desktop/legacy/ms644985(v=vs.85)</link>
         * @param nCode 挂钩过程用来确定如何处理消息的代码。
         * @param wParam 键盘消息的标识符。此参数可以是以下消息之一：WM_KEYDOWN(0x0100)，WM_KEYUP(0x0101)，WM_SYSKEYDOWN或WM_SYSKEYUP。
         * @param event KBDLLHOOKSTRUCT结构。包含有关低级键盘输入事件的信息。
         * @return
         */
        @Override
        public LRESULT callback(int nCode, WPARAM wParam, WinUser.KBDLLHOOKSTRUCT event) {
            if (nCode >= 0) {
                // vkCode是一个虚拟键码。该代码必须是1到254之间的值。见：<link>https://docs.microsoft.com/en-us/windows/win32/inputdev/virtual-key-codes</link>
                System.out.println("nCode:" + wParam + ", KEY: " + event.vkCode);
                // 屏蔽windows，alt，tab,del键
                switch (event.vkCode) {
                    case 27:// esc
                    case 91: // windows
                    case 162:// ctl
                    case 164:// alt
                    case 9:// tab
                    case 115:// f4
                    case 116:// f5
                    case 123:// f12
                    case 46:// del
                        return new LRESULT(1);
                }
            }
            return User32.INSTANCE.CallNextHookEx(hhk, nCode, wParam, null);
        }

    };

    @Override
    public void run() {
        System.out.println("KeyboardHook Hook On!");
        HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        hhk = User32.INSTANCE.SetWindowsHookEx(User32.WH_KEYBOARD_LL, keyboardProc, hMod, 0);
        int result;
        WinUser.MSG msg = new WinUser.MSG();
        while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {
            System.out.println(System.currentTimeMillis() + "!");
            if (result == -1) {
                setHookOff();
            } else {
                User32.INSTANCE.TranslateMessage(msg);
                User32.INSTANCE.DispatchMessage(msg);
            }
        }
    }

    /**
     * 移除钩子并退出
     */
    public void setHookOff() {
        System.out.println("KeyboardHook Hook Off!");
        User32.INSTANCE.UnhookWindowsHookEx(hhk);
        System.exit(0);
    }

    public static void main(String[] args) {
        KeyboardHook kbhook = new KeyboardHook();
        new Thread(kbhook).start();
    }
}