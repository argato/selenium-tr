package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoodsHasStickerTest {
  private WebDriver driver;

  @Before
  public void start() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test
  public void goodsHasStickerTest(){
    driver.get("http://localhost/litecart");
    int stickersCount;
    int errorsCount = 0;
    List<WebElement> goodsCount;

    goodsCount  = driver.findElements(By.xpath("//li[contains(@class,'product')]"));

    for(WebElement item: goodsCount){
      stickersCount = item.findElements(By.xpath(".//div[contains(@class,'sticker')]")).size();
      if(stickersCount != 1) {
        errorsCount++;
      }
    }

    Assert.assertEquals(0, errorsCount);
  }

  @After
  public void stop(){
    driver.quit();
    driver = null;
  }
}
