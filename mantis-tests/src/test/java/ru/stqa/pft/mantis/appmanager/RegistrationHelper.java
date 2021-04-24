package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.BaseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    /*wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));*/
    click(By.cssSelector("input[type='Submit']"));

  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }
}
