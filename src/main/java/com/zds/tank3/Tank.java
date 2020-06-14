package com.zds.tank3;


import lombok.Data;

import java.awt.*;

//如何处理坦克的静止状态
//给坦克加上炮弹 一个坦克炮弹有多个 定义一个炮弹类
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
        System.out.println("paint");
        g.fillRect(x, y, 50, 50);

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
