package com.zds.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args){

//        //实例化一个jdk  自带的ui包下的窗口
//        Frame frame = new Frame();
//        //让窗口可视化 Shows or hides this
//        frame.setVisible(true);
//        //给窗口设定大小
//        frame.setSize(800,800);
//        //让窗口不可修改
//        frame.setResizable(false);
//        //给窗口标题设置一个值
//        frame.setTitle("tank===war");
//        //如何可以通过鼠标点击x关闭这个窗口
//        //需要给这个窗口添加一个监听事件来触发这个窗口的退出
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });

        //这里面new 出tankFrame 的实例
        TankFrame tankFrame = new TankFrame();
//        new Thread(()->{
//            TankFrame tankFrame1 = new TankFrame();
//        }).start();
//
//        new Thread(()->{
//            TankFrame tankFrame2 = new TankFrame();
//        }).start();
//        for(;;){
//            new Thread(()->{
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                }
//                tankFrame.repaint();
//            }).start();
//        }

        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
            tankFrame.repaint();
        }


    }
}
