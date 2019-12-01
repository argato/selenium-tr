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

    List<WebElement> goods = driver.findElements(By.className(("image-wrapper")));
    List<WebElement> goodsHasSticker = driver.findElements(By.xpath("//*[@class='image-wrapper'][.//*[contains(@class,'sticker')]]"));
    Assert.assertEquals(goods, goodsHasSticker);
  }

  @After
  public void stop(){
    driver.quit();
    driver = null;
  }
}
