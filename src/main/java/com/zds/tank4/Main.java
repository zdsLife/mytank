package com.zds.tank4;

//import com.zds.tank3.TankFrame;
//这个导包直接导致我这里面上一次提交修改坦克的颜色 居然没有改变
public class Main {
    public static void main(String[] args){
        TankFrame tankFrame = new TankFrame();

        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
            tankFrame.repaint();
        }


    }
}
