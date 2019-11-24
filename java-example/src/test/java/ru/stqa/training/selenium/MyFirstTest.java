package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MyFirstTest {

  private WebDriver driver;

  @Before
  public void start() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void myFistTest(){
    driver.get("http://www.google.com/");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    Assert.assertEquals(driver.getTitle(),"webdriver - Поиск в Google");
  }

  @After
  public void stop(){
    driver.quit();
    driver = null;
  }
}