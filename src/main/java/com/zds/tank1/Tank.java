package com.zds.tank1;


import lombok.Data;

import java.awt.*;

//既然需要多辆坦克 是否可以抽象出一个模板 每new 出一个对象 就多了一辆tank
@Data
public class Tank {
    //定义tank 在画板上的初始位置
    private int x,y;
    //定义tank默认的炮管朝向
    private Dir dir = Dir.DOWN;
    //每个坦克都可有自己的速度变化 这里定义一个tank的速度变量
    private int speed = 10;


    public Tank(int x, int y, Dir dir, int speed) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = speed;
    }

    public void paint(Graphics g) {
        System.out.println("paint");
        g.fillRect(x, y, 50, 50);
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
