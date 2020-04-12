package org.maven.demo;

import com.baidu.aip.ocr.AipOcr;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Random;


public class App {

    static Sheet sheet;
    static Cell cell_1;
    //设置APPID/AK/SK
    public static String APP_ID = "";
    public static String API_KEY = "";
    public static String SECRET_KEY = "";

    static WebDriver driver = null;

    public static void main(String[] args) throws Exception {
        APP_ID = args[0];
        API_KEY = args[1];
        SECRET_KEY = args[2];
        try{
            //设置无头模式
            ChromeOptions chromeOptions=new ChromeOptions();
            chromeOptions.setHeadless(Boolean.TRUE);
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            driver = new ChromeDriver(chromeOptions);

            Workbook book = Workbook.getWorkbook(new File("test.xls"));
            sheet=book.getSheet(0);
            long row = sheet.getRows();
            for(int i=1;i<row;i++){
                cell_1 = sheet.getCell(8,i);
                System.out.println("正在为手机号------->"+cell_1.getContents().trim().substring(0,4)+"***"+cell_1.getContents().trim().substring(7,11)+"-------抽奖");
                run();
            }
            driver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    调用百度API接口识别验证码
     */
    public static String ocrBaidu() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        String OriginalImg = "oi.jpg";
        //识别样本输出地址
        String ocrResult = "or.jpg";
        //去噪点
        org.maven.demo.App.removeBackground(OriginalImg, ocrResult);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        //  调用接口
        String path = "or.jpg";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        //返回正确的验证码
        return res.getJSONArray("words_result").getJSONObject(0).get("words").toString().trim();
    }
    /*
    开始的地方
     */
    public static void run() {
        String password;
        int n = 3;
        int m = 0;
        do {
            try {
                m++;
                driver.get("http://m.client.10010.com/sma-lottery/qpactivity/qingpiindex");
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[3]/div")).click();
                Thread.sleep(1000);
                WebElement phone = driver.findElement(By.xpath("//*[@id=\"ipt1\"]"));
                phone.sendKeys(cell_1.getContents().trim());
                WebElement element = driver.findElement(By.xpath("//*[@id=\"tpyzm\"]"));
                Thread.sleep(4000);
                org.apache.commons.io.FileUtils.copyFile(elementSnapshot(element), new File("oi.jpg"));
                password = ocrBaidu();
                WebElement pass = driver.findElement(By.xpath("//*[@id=\"ipt2\"]"));
                System.out.println(m+"------------->"+password);
                pass.sendKeys(password);
                if(isok(driver)){
                    driver.findElement(By.xpath("/html/body/div[3]/div/div[3]")).click();
                    Thread.sleep(4000);
                    n--;
                }
            } catch (Exception e) {

            }
        } while (n!=0);
    }
    /*
    判断验证码的正确性
     */
    public static boolean isok(WebDriver driver) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("/html/body/div[3]/div/p[2]"));
        String s = element.getAttribute("style");
        if("display: block; opacity: 0;".equals(s)){
            return true;
        }
        else{
            return false;
        }
    }
    /*
    降噪并二值化
     */
    public static void removeBackground(String imgUrl, String resUrl) {
        //定义一个临界阈值
        int threshold = 300;
        try {
            BufferedImage img = ImageIO.read(new File(imgUrl));
            int width = img.getWidth();
            int height = img.getHeight();
            for (int i = 1; i < width; i++) {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        Color color = new Color(img.getRGB(x, y));
//                        System.out.println("red:" + color.getRed() + " | green:" + color.getGreen() + " | blue:" + color.getBlue());
                        int num = color.getRed() + color.getGreen() + color.getBlue();
                        if (num >= threshold) {
                            img.setRGB(x, y, Color.WHITE.getRGB());
                        }
                    }
                }
            }
            for (int i = 1; i < width; i++) {
                Color color1 = new Color(img.getRGB(i, 1));
                int num1 = color1.getRed() + color1.getGreen() + color1.getBlue();
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        Color color = new Color(img.getRGB(x, y));

                        int num = color.getRed() + color.getGreen() + color.getBlue();
                        if (num == num1) {
                            img.setRGB(x, y, Color.BLACK.getRGB());
                        } else {
                            img.setRGB(x, y, Color.WHITE.getRGB());
                        }
                    }
                }
            }
            File file = new File(resUrl);
            if (!file.exists()) {
                File dir = file.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try {
                    file.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ImageIO.write(img, "png", file);
        } catch (Exception e) {
            System.out.println("降噪二值化部分");
        }
    }
    /*
    截验证码部分图片
     */
    public static File elementSnapshot(WebElement element){
        try{
            //创建全屏截图
            WrapsDriver wrapsDriver = (WrapsDriver)element;
            File screen = ((TakesScreenshot)wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
            BufferedImage image = ImageIO.read(screen);
            //获取元素的高度、宽度
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();

            //创建一个矩形使用上面的高度，和宽度
            Rectangle rect = new Rectangle(width, height);
            //元素坐标
            Point p = element.getLocation();
            BufferedImage img = image.getSubimage(p.getX(), p.getY()+10, rect.width-15, rect.height-14);
            ImageIO.write(img, "png", screen);
            return screen;
        } catch (Exception e){
            System.out.println("截取验证码图片");
            return null;
        }
    }
}