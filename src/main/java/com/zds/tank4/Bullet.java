package com.zds.tank4;

import com.zds.tank3.Dir;
import lombok.Data;

import java.awt.*;

@Data
public class Bullet {
    private int x,y;
    //后期坦克的炮管是否于子弹的方向一致
    private Dir dir = Dir.DOWN;
    private int speed = 5;
    //还是分装坦克的状态熟悉 默认静止状态
    private boolean moving = false;
    // 定义子弹的宽高
    public int width=20,height=20;


    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    //画出子弹 制作子弹
    public void paint(Graphics g) {
        System.out.println("bullet==>paint");
        //给子弹上色
        Color color = g.getColor();
        g.setColor(Color.GREEN);
        //画⚪型
        g.fillOval(x, y, width, height);
        g.setColor(color);
        move();

    }
    //坦克根据传入的方向移动
    public void move() {
//        if (!moving) {
//            return;
//        } 子弹与坦克的区别 一旦发射就会变为不可控的
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case UP:
                y -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFTUP:
                x -= speed;
                y -= speed;
                break;
            case UPRIGHT:
                y -= speed;
                x += speed;
                break;
            case RIGHTDOWN:
                y += speed;
                x += speed;
                break;
            case DOWNlEFT:
                y += speed;
                x -= speed;
                break;

        }

        //子弹也需要移动的
    }

}
