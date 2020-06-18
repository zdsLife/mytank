package com.zds.tank4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

//定义资源管理类 管理这些图片
public class ResourceMgr {
    public static BufferedImage tankL,tankU,tankR,tankD;
    //通过一个静态代码块来加载这些图片
    static{

        try {
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

}
