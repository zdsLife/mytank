package com.zds.tank1;

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
