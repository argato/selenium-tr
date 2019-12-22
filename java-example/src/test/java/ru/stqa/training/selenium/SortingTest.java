package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SortingTest {
  private WebDriver driver;

  @Before
  public void start() {
    driver = new InternetExplorerDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test
  public void countriesTest() {
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
    driver.findElement(By.cssSelector("li#app- a[href*=countries]")).click();

    List<WebElement> countries;
    List<String> countriesHasZones = new ArrayList<>();;
    ArrayList<String> arrayCountries = new ArrayList<>();
    ArrayList<String> sortedArrayCountries;

    List<WebElement> zones;
    ArrayList<String> arrayZones = new ArrayList<>();
    ArrayList<String> sortedArrayZones;

    countries = driver.findElements(By.cssSelector("form[name=countries_form] table.dataTable tr.row"));
    for (WebElement item : countries) {
      arrayCountries.add(item.findElements(By.cssSelector("td")).get(4).getText());
      if(Integer.parseInt(item.findElements(By.cssSelector("td")).get(5).getText()) > 0){
        countriesHasZones.add(item.findElements(By.cssSelector("td")).get(3).getText());
      }
    }

    sortedArrayCountries = arrayCountries;
    Collections.sort(sortedArrayCountries);
    Assert.assertEquals(sortedArrayCountries, arrayCountries);

    for (String item : countriesHasZones) {
      driver.findElement(By.cssSelector("form[name=countries_form] table.dataTable tr.row td a[href*=" + item + "]")).click();
      zones = driver.findElements(By.cssSelector("table.dataTable#table-zones tr.row:not(.header)"));
      for (WebElement el : zones) {
        String nameZone = el.findElements(By.cssSelector("td")).get(3).getText();
        if (!nameZone.isEmpty()) {
          arrayZones.add(nameZone);
        }
        sortedArrayZones = arrayZones;
        Collections.sort(sortedArrayZones);
        Assert.assertEquals(sortedArrayZones, arrayZones);
      }
      driver.findElement(By.xpath("//a[contains(@href, 'countries')]")).click();
    }
  }

  @After
  public void stop(){
    driver.quit();
    driver = null;
  }
}
