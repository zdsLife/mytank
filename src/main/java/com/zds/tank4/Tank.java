package com.zds.tank4;


//import com.zds.tank3.Dir;
import lombok.Data;

import java.awt.*;

@Data
public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private int speed = 10;
    //还是分装坦克的状态熟悉 默认静止状态
    private boolean moving = false;
    //持有tankframe 的引用来获取画板 这样构造方法里面new tank的时候可以拿到这个画板
    private TankFrame tf;
    //获取坦克的高度和宽度
    private static int WIDTH = ResourceMgr.tankD.getWidth();
    private static int HEIGHT = ResourceMgr.tankD.getHeight();


    public Tank(int x, int y, Dir dir, int speed,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = speed;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        //这个绘画的颜色为什么没有被调用
        System.out.println("tank4===>paint");

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;

        }

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
    //定义坦克的发射方法
    public void fire(){
        //注意这里给子弹传的参数是坦克的方向参数
        //这里是坦克持有子弹 但是如何把这颗子弹 画在tankframe上
        //这里把创建的tank交给tankFrame
//       tf.bullet = new Bullet(this.x,this.y,this.dir);
        //画一颗子弹 容器中new 出一个子弹

        //计算子弹的位置
        int bX =this.x+Tank.WIDTH/2-Bullet.WIDTH;
        int bY =this.y+Tank.HEIGHT/2-Bullet.HEIGHT;
       tf.bullets.add(new Bullet(bX,bY,this.dir,this.getTf()));
    }

}
