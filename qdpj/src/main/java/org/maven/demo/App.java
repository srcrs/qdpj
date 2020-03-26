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
    static char str[] = {'0','1','2','3','4','5','6','7','8','9'};

    public static void main(String[] args) throws Exception {
        try{
            Workbook book = Workbook.getWorkbook(new File("test.xls"));
            sheet=book.getSheet(0);
            for(int i=1;i<2;i++){
                cell_1 = sheet.getCell(8,i);
                run();
            }
        } catch (Exception e) {

        }
    }
    /*
    开始的地方
     */
    public static void run() {
        WebDriver driver = null;
        try {
            //设置无头模式
            ChromeOptions chromeOptions=new ChromeOptions();
            chromeOptions.setHeadless(Boolean.TRUE);
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            //如果不采用无界面模式则不需要使用chromeOptions参数
            //这句话相当于创建了一个窗口
            driver = new ChromeDriver(chromeOptions);
            String password;
            int n = 3;
            int m = 0;
            do {
                m++;
                driver.get("http://m.client.10010.com/sma-lottery/qpactivity/qingpiindex");
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[3]/div")).click();
                Thread.sleep(1000);
                WebElement phone = driver.findElement(By.xpath("//*[@id=\"ipt1\"]"));
                phone.sendKeys(cell_1.getContents().trim());
                WebElement pass = driver.findElement(By.xpath("//*[@id=\"ipt2\"]"));
                //     int ran = (int)(Math.random()*10);
                int ran = 4;
                boolean flag = false;
                System.out.println(m+"-------------->"+ran);
                for(int i=0;i<10;i++){
                    for(int j=0;j<10;j++){
                        pass.sendKeys(""+str[i]+str[j]+ran+ran);
                        if(isok(driver)){
                            System.out.println(""+str[i]+str[j]+ran+ran);
                            driver.findElement(By.xpath("/html/body/div[3]/div/div[3]")).click();
                            Thread.sleep(4000);
                            n--;
                            m=0;
                            flag = true;
                            break;
                        }
                        else{
                            pass.clear();
                        }
                    }
                    if(flag){
                        break;
                    }
                }
            } while(n!=0);
            driver.close();
        } catch (Exception e) {
            driver.close();
            System.out.println(cell_1.getContents().trim());
        }
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
}
