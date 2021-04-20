package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {


  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    click(By.linkText("groups"));
  }

  public void goToGroupPageAfter() {
    wd.findElement(By.partialLinkText("group page")).click();
  }

  public void homePage() {
    if (isElementPresent(By.tagName("h1"))
            &&  wd.findElement(By.tagName("h1")).getText().equals("home")) {
      return;
    }

    click(By.linkText("home"));
  }
}
