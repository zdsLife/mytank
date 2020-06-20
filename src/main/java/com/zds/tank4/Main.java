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

            for (int i = 0; i < 5; i++) {
                tankFrame.tanks.add(new Tank(50+i*80,100,Dir.DOWN,10,tankFrame));

            }
            tankFrame.repaint();
        }


    }
}
