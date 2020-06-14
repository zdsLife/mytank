package com.zds.tank4;


import com.zds.tank3.Dir;
import lombok.Data;

import java.awt.*;

@Data
public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private int speed = 10;
    //还是分装坦克的状态熟悉 默认静止状态
    private boolean moving = false;


    public Tank(int x, int y, Dir dir, int speed) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = speed;
    }

    public void paint(Graphics g) {
        //这个绘画的颜色为什么没有被调用
        System.out.println("tank4===>paint");
        Color color = g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(x, y, 50, 50);
        g.setColor(color);

        //坦克根据传入的方向移动
        move();
    }

    public void move(){
        if(!moving) {return;}
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
    }
}
