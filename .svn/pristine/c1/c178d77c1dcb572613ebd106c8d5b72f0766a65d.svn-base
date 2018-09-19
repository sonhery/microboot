package com.dee.xql.api.utils;

public class WindowsHelper {

    public static void main(String[] args) {
        killProcess("WINPROJ.EXE");
    }

    public static void killProcess(String name) {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("cmd.exe /C start wmic process where name='" + name + "' call terminate");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
