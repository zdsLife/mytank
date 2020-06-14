package com.zds.tank3;

//这一版是画出了坦克和子弹 子弹自动移动 坦克可以根据上下左右按键拼出8个方向
public class Main {
    public static void main(String[] args){
        TankFrame tankFrame = new TankFrame();

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
