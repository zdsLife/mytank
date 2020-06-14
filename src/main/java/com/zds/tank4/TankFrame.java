package com.zds.tank4;

//import com.zds.tank3.Bullet;
//import com.zds.tank3.Dir;
//import com.zds.tank3.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    Tank tank = new Tank(200,200, Dir.DOWN,10);
    Bullet bullet =new Bullet(300,300,Dir.DOWN);
    //考虑后期 这个游戏的界面宽度和高度 这里定义位全局变量
    public int GAME_WIDTH=800,GAME_HEIGHT=600;
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
         tank.paint(g);
         //画出子弹
         bullet.paint(g);
        }

   //消除子弹的闪烁 涉及屏幕的刷新
    // 拿一个图片覆盖这个游戏界面
    Image offScreenImage = null;

}
