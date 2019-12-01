package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AdminPanelMenuTest {
  private WebDriver driver;

  @Before
  public void start() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test
  public void headersTest(){
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();

    int menuItemsCount = driver.findElements(By.id("app-")).size();
    int subMenuItemsCount;
    int notFoundCount = 0;

    for (int i = 1; i <= menuItemsCount; i++) {
      driver.findElement(By.xpath("//li[contains(@id,'app')][" + i + "]")).click();
      subMenuItemsCount = driver.findElements(By.xpath("//li[contains(@id,'doc')]")).size();
      for (int j = 1; j <= subMenuItemsCount; j++) {
          driver.findElement(By.xpath("//li[contains(@id,'doc')][" + j + "]")).click();
          if(driver.findElements(By.tagName("h1")).size()==0){
            notFoundCount++;
          }
      }
    }
    System.out.println("count "+ notFoundCount);
    Assert.assertEquals(0, notFoundCount);

}

  @After
  public void stop(){
    driver.quit();
    driver = null;
  }
}
