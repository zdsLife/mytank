package com.zds.tank4;

//import com.zds.tank3.TankFrame;
//这个导包直接导致我这里面上一次提交修改坦克的颜色 居然没有改变
public class Main {
    public static void main(String[] args){
        TankFrame tankFrame = new TankFrame();
        for (int i = 0; i < 5; i++) {
            tankFrame.tanks.add(new Tank(50+i*80,50,Dir.DOWN,1,Group.BAD,tankFrame));

        }
        //上面这个书写敌方的tank 放在了下面的这个是循环里面 导致坦克的数量不断变多
        //导致 子弹击中坦克 但是坦克没有消失 原因是这个坦克是重复生成的 实际这个位置的坦克已经被消灭
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
