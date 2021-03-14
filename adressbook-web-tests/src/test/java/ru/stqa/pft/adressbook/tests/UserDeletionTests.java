package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {

    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAUser()) {
      app.getContactHelper().createUser(new UserData("Andrey", "Rublev", "Boring Company",
              "222111333", "rublev@rublev.com"));
    }
    app.getContactHelper().selectUserPage();
    app.getContactHelper().deleteUserPage();
    app.getContactHelper().alertOk();
  }
}
