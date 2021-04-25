
package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void manageLink() {
    click(By.cssSelector(String.format("a[href='/mantisbt-2.25.0/manage_overview_page.php']")));
    click(By.cssSelector(String.format("a[href='/mantisbt-2.25.0/manage_user_page.php']")));
  }


}