
package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

  public SessionHelper(ApplicationManager app) {
    super(app);
  }

  public void logIn(String username, String password) {
    wd.get(app.getProperty("web.BaseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void logOut() {

    click(By.className("user-info"));
    click(By.cssSelector(String.format("a[href='/mantisbt-2.25.0/logout_page.php']")));
  }
}