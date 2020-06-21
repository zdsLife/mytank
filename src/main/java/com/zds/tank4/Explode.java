package com.zds.tank4;

import lombok.Data;

import java.awt.*;

@Data
public class Explode {
    private boolean live= true;
    private int x,y;
    //获取爆炸效果图的高度和宽度
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    //子弹也要引用这个画板大管家
    private TankFrame tf;

    //定义一个下标索引步骤 判断画到这个爆炸数组的第几步
    private int step = 0;

    public  void paint(Graphics g) {
        //画出加载到内存的图片 每画一次 步骤加一
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        //不画的终止条件 爆炸数组里面的图片画完了
        if(step>=ResourceMgr.explodes.length){
//            step = 0;//這裏爆炸完後會繼續重繪
            tf.explodes.remove(this);//爆炸的16個圖片畫完后移除這個爆炸對象
        }
    }

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Audio("audio/explode.wav").run();
    }

}
