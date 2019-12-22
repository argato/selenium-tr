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

public class GeoZonesTest {
  private WebDriver driver;

  @Before
  public void start() {
    driver = new InternetExplorerDriver();
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }


  @Test
  public void zonesTest() {
    driver.get("http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
    driver.findElement(By.cssSelector("li#app- a[href*=geo_zones]")).click();

    List<WebElement> zonesLinks;

    List<WebElement> zones;
    ArrayList<String> arrayZones = new ArrayList<>();
    ArrayList<String> sortedArrayZones;
    int countCountries;

    countCountries = driver.findElements(By.cssSelector("form[name=geo_zones_form] table.dataTable tr.row")).size();

    for (int i = 0; i < countCountries; i++) {
      zonesLinks = driver.findElements(By.cssSelector("form[name=geo_zones_form] table.dataTable tr.row")).get(i).findElements(By.cssSelector("td"));
      zonesLinks.get(2).findElement(By.cssSelector("a")).click();

      zones = driver.findElements(By.cssSelector("form[name=form_geo_zone] table.dataTable tr td select[name*=zone_code] option[selected=selected]"));
      for (WebElement zone : zones) {
        arrayZones.add(zone.getAttribute("textContent"));

      }
      sortedArrayZones = arrayZones;
      Collections.sort(sortedArrayZones);
      Assert.assertEquals(sortedArrayZones, arrayZones);
      sortedArrayZones.clear();
      arrayZones.clear();

      driver.findElement(By.cssSelector("li#app- a[href*=geo_zones]")).click();
    }
  }


  @After
  public void stop(){
    driver.quit();
    driver = null;
  }
}
