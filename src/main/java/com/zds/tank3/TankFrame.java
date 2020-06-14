package com.zds.tank3;

import lombok.var;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    //画板上添一辆坦克
    Tank tank = new Tank(200,200, Dir.DOWN,10);
    //画板上添加一颗子弹
    Bullet bullet =new Bullet(300,300,Dir.DOWN);
    public TankFrame() throws HeadlessException {
        setVisible(true);
        setSize(800,600);
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
    public void paint(Graphics g) {
        System.out.println("paint");
         tank.paint(g);
         //画出子弹
        bullet.paint(g);
        }



}
