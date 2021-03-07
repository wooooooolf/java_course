package ru.stqa.pft.adressbook;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UserCreationTests extends TestBase {


  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  @Test
  public void testUserCreation() throws Exception {

    initUserCreation();
    fillUserForm(new UserData("Andrey", "Rublev", "Boring Company", "222111333", "rublev@rublev.com"));
    submitUserCreation();
    returnToHomePage();

  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

}
