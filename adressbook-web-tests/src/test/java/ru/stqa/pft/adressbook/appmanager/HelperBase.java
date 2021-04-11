package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }

  }

  protected void type1(By locator1, String text1) {
    wd.findElement(locator1).click();
    wd.findElement(locator1).clear();
    wd.findElement(locator1).sendKeys(text1);
  }

  protected void attach(By locator1, File file) {
    if (file != null) {

      wd.findElement(locator1).sendKeys(file.getAbsolutePath());
    }

  }


  protected boolean isElementPresent(By locator){
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException exception) {
      return false;
    }

  }
}