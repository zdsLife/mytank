package com.zds.tank1;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//作业根据四个方向的布尔值 来确定移动的方向
//设想一下 此时这个游戏有两个人 是不是需要两个tank
// 如何确保界面多出十个敌人的坦克 而且敌人的坦克随机自行移动
public class TankFrame extends Frame {
    Tank tank = new Tank(200,200,Dir.DOWN,10);
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
            //设定主战坦克的方向  如果这句话调用失去会怎么样
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
                default :
                    break;

            }
            //设定主战坦克的方向  如果这句话调用失去会怎么样
            setMainTankDir();
        }

        //这个定义主战坦克的方向的方法 为为什么会定义在这个内部类内部
        public void setMainTankDir(){
            if(bl) {tank.setDir( Dir.LEFT);}
            if(bu) {tank.setDir( Dir.UP);}
            if(br) {tank.setDir( Dir.RIGHT);}
            if(bd) {tank.setDir( Dir.DOWN);}
            if(bl&&bu) {tank.setDir( Dir.LEFTUP);}
            if(bu&&br) {tank.setDir( Dir.UPRIGHT);}
            if(br&&bd) {tank.setDir( Dir.RIGHTDOWN);}
            if(bd&&bl) {tank.setDir( Dir.DOWNlEFT);}
        }
    }



    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
         tank.paint(g);
        }



}
