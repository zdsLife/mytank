package com.zds.tank4;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    //引入容器装子弹
    List<Bullet> bullets = new ArrayList<Bullet>();
    List<Tank> tanks = new ArrayList<Tank>();
    //这里把这个对象传入给了坦克
    Tank tank = new Tank(200,200, Dir.DOWN,10,Group.GOOD,this);
    Bullet bullet =new Bullet(300,300,Dir.DOWN,Group.GOOD,this);
    //根据爆炸产生肯定是坦克被击毁的位置 这里先看爆炸效果 不考虑爆炸位置
    //如何动态获取坦克爆炸的位置 为爆炸效果图片定位
//    Explode e = new Explode(100,100,this);
    //產生多個爆炸 需要一個放爆竹的容器
    List<Explode> explodes = new ArrayList<Explode>();
    //考虑后期 这个游戏的界面宽度和高度 这里定义位全局变量
//    public static int GAME_WIDTH=800,GAME_HEIGHT=600;
    public static int GAME_WIDTH=1080,GAME_HEIGHT=960;
    public TankFrame() throws HeadlessException {
        setVisible(true);
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank===war");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MykeyListener());

    }
    class MykeyListener extends KeyAdapter{
        boolean bl=false,bu=false,br=false,bd=false;


        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("keyPressed");
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bl=true;
                    break;
                case KeyEvent.VK_UP:
                    bu=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br=true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd=true;
                    break;
                default :
                    break;

            }
            setMainTankDir();
            repaint();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("keyReleased");
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bl=false;
                    break;
                case KeyEvent.VK_UP:
                    bu=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br=false;
                    break;

                case KeyEvent.VK_DOWN:
                    bd=false;
                    break;
                //添加坦克发射的监听方法
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;

                default :
                    break;

            }
            setMainTankDir();
        }

        public void setMainTankDir(){

            //什么时候静止 就是这几个方向的键都没有摁
            if(!bl&&!bu&&!br&&!bd&&!(bl&&bu)&&!(bu&&br)&&!(br&&bd)&&!(bd&&bl)){
                tank.setMoving(false);
            }else {
                //移动方向时 修改坦克的状态
                tank.setMoving(true);
                if (bl) {
                    tank.setDir(Dir.LEFT);
                }
                if (bu) {
                    tank.setDir(Dir.UP);
                }
                if (br) {
                    tank.setDir(Dir.RIGHT);
                }
                if (bd) {
                    tank.setDir(Dir.DOWN);
                }
                if (bl && bu) {
                    tank.setDir(Dir.LEFTUP);
                }
                if (bu && br) {
                    tank.setDir(Dir.UPRIGHT);
                }
                if (br && bd) {
                    tank.setDir(Dir.RIGHTDOWN);
                }
                if (bd && bl) {
                    tank.setDir(Dir.DOWNlEFT);
                }

            }
        }
    }


    @Override
    public void update(Graphics g) {
        //base
        if(offScreenImage==null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        //拿到这个图片的画笔
        Graphics graphics = offScreenImage.getGraphics();
        //给这个图片染色
        Color color = graphics.getColor();
        //游戏背景设为黑色
        graphics.setColor(Color.BLACK);
        //画背景
        graphics.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        //给背景作色
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage,0,0,null);


    }

    @Override
    public void paint(Graphics g) {
        System.out.println("Tankrame==>paint");
        //画板左上角打印子弹的数量
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("打出的子弹数量为"+bullets.size(),10,60);
        g.drawString("敌方的坦克数量为"+tanks.size(),10,80);
        g.drawString("爆炸数量为"+explodes.size(),10,100);
        g.setColor(c);
        tank.paint(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
          }

          //画地方tank
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
          }
          //畫各個爆炸圖片
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
         //碰撞檢測 在碰撞的地方 new 出爆炸的對象

        //判断坦克和子弹是否相交
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
               //拿坦克和子弹进行碰撞检测
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

        //画出爆炸的效果
//        e.paint(g);

    }

   //消除子弹的闪烁 涉及屏幕的刷新
    // 拿一个图片覆盖这个游戏界面
    Image offScreenImage = null;


}
