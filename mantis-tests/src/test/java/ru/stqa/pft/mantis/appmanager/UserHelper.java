package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.tests.TestBase;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void selectUserById(int id) {
    click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + id + "']"));
  }

  public void resetPassword () {
    click(By.xpath("//input[@value='Reset Password']"));
  }

  public void initUserCreation() {
    click(By.cssSelector(String.format("a[href='/mantisbt-2.25.0/manage_user_create_page.php']")));
  }

  public void fillNewUserForm(UserData userData) {
    type(By.id("user-name"), userData.getUsername());
    type(By.id("email-field"), userData.getEmail());
  }

  public void sumbitUserCreation() {
    click(By.cssSelector("input[type='submit']"));
  }
}