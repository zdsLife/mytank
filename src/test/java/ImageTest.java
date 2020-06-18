import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class ImageTest {

    @Test
    public void test() throws Exception{
         //com.zds.tank4.Tank
        System.out.println("test");
        //尝试着把之前画的蓝色款矩形 改成图片
        //把图片读入到图片流中
//        BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/bulletD.gif"));
//        BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/bulletD.gif"));
        BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().
                getResourceAsStream("tank/src/images/bulletD.gif"));
        assertNotNull(image);
//        BufferedImage image1 = ImageIO.read(new File("F:/tank/src/images/bulletD.gif"));
        System.out.println(image);
//        System.out.println(image1);
    }

    public static void main(String[] args)throws Exception {
//        BufferedImage image1 = ImageIO.read(new File("F:/tank/src/images/bulletD.gif"));
        BufferedImage image1 = ImageIO.read(new File("F:/tank/src/main/resources/images/bulletD.gif"));
        System.out.println(image1);
    }
}
