package com.zds.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//作业根据四个方向的布尔值 来确定移动的方向
public class TankFrame extends Frame {
    //定义主战坦克的在画板上的初始位置
    int x=200,y=200;
    //给一个tank 的默认向下的方向
    Dir dir = Dir.DOWN;
    // 给坦克的移动定义一个速度变量
    int speed = 10;
    public TankFrame() throws HeadlessException {
        //让窗口可视化 Shows or hides this
        setVisible(true);
        //给窗口设定大小
        setSize(800,600);
        //让窗口不可修改
        setResizable(false);
        //给窗口标题设置一个值
        setTitle("tank===war");
        //如何可以通过鼠标点击x关闭这个窗口
        //需要给这个窗口添加一个监听事件来触发这个窗口的退出
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //添加键盘的监听事件
        addKeyListener(new MykeyListener());

    }
    class MykeyListener extends KeyAdapter{
        boolean bl=false,bu=false,br=false,bd=false;


        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("keyPressed");
            //如何获取键盘的键值
            //根据键的事件拿到键值
            int keyCode = e.getKeyCode();

            //假如传入的是上下左右四个方向的按键 分别根据方向四向移动
            switch (keyCode){
                //如果是水平的向左移动则需要变动x轴
                // 这种坦克只能四向移动 不能斜上45度来走
                // 如何处理这种走斜的逻辑呢 有说同时按两个方向上的键 实际电脑还是一个一个键命令执行的
                // 因为你按的再快 对于电脑还是能分别出按键的先后顺序
                // 可以常识定义一些按键的布尔值来判断这个键被按下
                case KeyEvent.VK_LEFT:
                    bl=true;
//                    x-=10;
                    break;
                case KeyEvent.VK_UP:
                    bu=true;
//                    y-=10;
                    break;
                case KeyEvent.VK_RIGHT:
                    br=true;
//                    x+=10;
                    break;
                case KeyEvent.VK_DOWN:
                    bd=true;
//                    y+=10;
                    break;
                default :
                    break;

            }
            // 加个判断方向的逻辑
//            if(bl){ x-=10; }
//            if(bu){ y-=10; }
//            if(br){ x+=10; }
//            if(bd){ y+=10; }
//            if(bl&&bu){x-=10; y-=10;}
//            if(bu&&br){y-=10; x+=10;}
//            if(br&&bd){x+=10; y+=10;}
//            if(bd&&bl){y+=10; x-=10;}

            //设定主战坦克的方向
            setMainTankDir();




            //触发键盘 挪动矩形块
//            x+=50;
//            y+=50;
            //重绘
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
                default :
                    break;

            }
            //设定主战坦克的方向
            setMainTankDir();
        }

        //这个定义主战坦克的方向的方法 为为什么会定义在这个内部类内部
        public void setMainTankDir(){
            if(bl) {dir = Dir.LEFT;}
            if(bu) {dir = Dir.UP;}
            if(br) {dir = Dir.RIGHT;}
            if(bd) {dir = Dir.DOWN;}
            if(bl&&bu) {dir = Dir.LEFTUP;}
            if(bu&&br) {dir = Dir.UPRIGHT;}
            if(br&&bd) {dir = Dir.RIGHTDOWN;}
            if(bd&&bl) {dir = Dir.DOWNlEFT;}
        }
    }
//
//    //这里的paint 方法是一个画板  Graphics是画笔
//    @Override
//    public void paint(Graphics g) {
//        //画一个矩形
////        g.drawRect(200,200,100,100);
////        g.fillRect(300,300,100,100);
//        //如何保证这个矩形是移动的 需要改变坐标值 引入变量
//        System.out.println("paint");
//
//        g.fillRect(x,y,50,50);
////        x+=100;
////        y+=100;
//
//    }



    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        g.fillRect(x,y,50,50);
        switch (dir){
            case LEFT:
                x-=speed;
                break;

            case UP:
                y-=speed;
                break;

            case RIGHT:
                x+=speed;
                break;

            case DOWN:
                y+=speed;
                break;

            case LEFTUP:
                x-=speed;
                y-=speed;
                break;

            case UPRIGHT:
                y-=speed;
                x+=speed;
                break;

            case RIGHTDOWN:
                y+=speed;
                x+=speed;
                break;

            case DOWNlEFT:
                y+=speed;
                x-=speed;
                break;

        }

    }
}
