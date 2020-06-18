package com.zds.tank4;

import lombok.Data;

import java.awt.*;

@Data
public class Bullet {
    //子弹是否还活着 触发死亡时因为飞出画板界面了 所以移动方法里面要做边界的限制
    private boolean live= true;
    private int x,y;
    //后期坦克的炮管是否于子弹的方向一致
    private Dir dir = Dir.DOWN;
    private int speed = 10;
    //还是分装坦克的状态熟悉 默认静止状态
    private boolean moving = false;
    // 定义子弹的宽高
    public int width=20,height=20;
    //子弹也要引用这个画板大管家
    private TankFrame tf;


    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }
    //画出子弹 制作子弹
    public void paint(Graphics g) {
        System.out.println("bullet==>paint");
        //如果当前子弹越界死亡则从子弹容器中移除这个子弹  但是此时引入了一个bug
        if(!live){
            tf.bullets.remove(this);
        }
//        //给子弹上色
//        Color color = g.getColor();
//        g.setColor(Color.GREEN);
//        //画⚪型
//        g.fillOval(x, y, width, height);
//        g.setColor(color);

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;

        }
        move();

    }
    //坦克根据传入的方向移动
    public void move() {

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

        //子弹越界 就会消失死亡
        if(x<0|| y<0||x>TankFrame.GAME_WIDTH||y>TankFrame.GAME_HEIGHT){
            live =false;
        }
        //子弹也需要移动的
    }

}
