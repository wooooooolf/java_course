package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.adressbook.appmanager.ApplicationManager;

public class TestBase {


    protected static ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  //public WebDriver wd;

  @BeforeSuite
    public void setUp() throws Exception {
      app.init();
    }



    @AfterSuite
    public void tearDown() throws Exception {
      app.stop();
    }

}

