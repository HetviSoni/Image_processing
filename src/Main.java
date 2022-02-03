import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Main
{
    static void Red()
    {
            BufferedImage image = null;
            File file = null;
            try
            {
                file = new File("./input images/cat.jpg");
                image = null;
                image=ImageIO.read(file);
            }
            catch(IOException e)
            {
                System.out.println(e);
            }
            int width = image.getWidth();
            int height = image.getHeight();
            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    int p = image.getRGB(x,y);
                    int a = (p>>24) & 0xff;
                    int r = (p>>16) & 0xff;
                    p = (a<<24) | (r<<16) | (0<<8) | 0;
                    image.setRGB(x, y, p);
                }
            }
            try
            {
                file = new File("./output images/red_cat.jpg");
                System.out.println("Successfully converted a colored image into a red image");
                ImageIO.write(image, "jpeg", file);
            }
            catch(IOException e)
            {
                System.out.println(e);
            }
        }
    static void greyScale()
    {
        BufferedImage image = null;
        File file = null;
        try
        {
            file = new File("./input images/colors.jpg");
            image =null;
            image = ImageIO.read(file);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int p = image.getRGB(x,y);
                int a = (p>>24) & 0xff;
                int r = (p>>16) & 0xff;
                int g = (p>>8) & 0xff;
                int b = p & 0xff;
                int avg = (r+g+b)/3;
                p = (a<<24) | (avg<<16) | (avg<<8) | avg;
                image.setRGB(x, y, p);
            }
        }
        try
        {
            file = new File("./output images/colors_grey.jpg");
            ImageIO.write(image, "jpg", file);
            System.out.println("Successfully converted a colored image into a grayscale image");
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    static void mirrorImage() throws IOException
    {
        BufferedImage simg = null;
        File f = null;
        try {
            f = new File(
                    "./input images/girl.png");
            simg = ImageIO.read(f);
        }

        catch (IOException e) {
            System.out.println("Error: " + e);
        }
        int width = simg.getWidth();
        int height = simg.getHeight();
        BufferedImage mimg = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int lx = 0, rx = width - 1; lx < width; lx++, rx--) {
                int p = simg.getRGB(lx, y);
                mimg.setRGB(rx, y, p);
            }
        }
        try {
            f = new File(
                    "./output images/mirror_girl.png");
            ImageIO.write(mimg, "png", f);
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    static void waterMark()
    {
        BufferedImage image = null;
        File file = null;
        try
        {
            file = new File("./input images/girl.jpg");
            image = ImageIO.read(file);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        BufferedImage temp = new BufferedImage(image.getWidth(),
                image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics graphics = temp.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.setFont(new Font("waterMark", Font.PLAIN, 80));
        graphics.setColor(new Color(220,0,0,40));
        String watermark = "Copyright";
        graphics.drawString(watermark, image.getWidth()/5, image.getHeight()/3);
        graphics.dispose();
        file = new File("./output images/watermark.jpg");
        try
        {
            ImageIO.write(temp, "jpg", file);
            System.out.println("Created a watermarked image");
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    static void green() throws IOException
    {
        BufferedImage img = null;
        File f = null;
        try {
            f = new File(
                    "./input images/cat.jpg");
            img = ImageIO.read(f);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        int width = img.getWidth();
        int height = img.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int g = (p >> 8) & 0xff;
                p = (a << 24) | (0 << 16) | (g << 8) | 0;

                img.setRGB(x, y, p);
            }
        }
        try {
            f = new File(
                    "./output images/greencat.jpg");
            ImageIO.write(img, "jpg", f);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String args[])throws IOException
    {
        //Call the functions and provide input image path according to your need
        greyScale();
        green();
        Red();
        mirrorImage();
        waterMark();

    }
}