package com.zds.tank4;


import lombok.Data;

import java.awt.*;
import java.util.Random;

@Data
public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private int speed = 1;
    //还是分装坦克的状态熟悉 默认静止状态
    private boolean moving = true;
    //持有tankframe 的引用来获取画板 这样构造方法里面new tank的时候可以拿到这个画板
    private TankFrame tf;
    //获取坦克的高度和宽度
    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();
    public boolean live = true;
    //拿到一个随机值
    public Random random = new Random();
    //给坦克分组 默认为敌方
    private Group group = Group.BAD;


    public Tank(int x, int y, Dir dir, int speed,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = speed;
        this.group = group;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        //这个绘画的颜色为什么没有被调用
        System.out.println("tank4===>paint");
        //如果tank死亡则不用画
        if(!this.live){
//            return;
        tf.tanks.remove(this);
        }

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
        //坦克移动后间隔一个随机值发射子弹 加一個分組判斷條件(沒加之前我方坦克也會隨著這個方法發射子彈)
        // 因爲需要敵方坦克自動發射子彈
        //我方 通過方向鍵控制方向
        if(this.group==Group.BAD&&random.nextInt(100)>95){
            this.fire();
        }
        //坦克移動后 隨機移動方向
        if(this.group == Group.BAD&&random.nextInt(100)>95){
        randomDir();
        }

        //坦克的移動過程中添加邊界條件檢查
        boundsCheck();
    }
    public void boundsCheck(){
        //橫向和縱向的邊界條件
        if(this.x<0){x=0;}
        if(this.y<30){y=30;}
        if(this.x>TankFrame.GAME_WIDTH-Tank.WIDTH){this.x=TankFrame.GAME_WIDTH-Tank.WIDTH;}
        if(this.y>TankFrame.GAME_HEIGHT-Tank.HEIGHT){this.y=TankFrame.GAME_HEIGHT-Tank.HEIGHT;}
    }
    public void randomDir() {
     //弄四個隨機數 映射到四個方向上

            //如果不加分組條件 你會發現坦克非常鬼畜的移動s
            this.dir =Dir.values()[random.nextInt(4)];


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
        //根据子弹的分组
       tf.bullets.add(new Bullet(bX,bY,this.dir,this.group,this.getTf()));
    }


    public void die(){
        System.out.println("tank die");
        this.live = false;
    }
}
