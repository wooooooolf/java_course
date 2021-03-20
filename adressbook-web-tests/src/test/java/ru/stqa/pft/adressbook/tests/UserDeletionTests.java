package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;

import java.util.List;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {


    if (!app.getContactHelper().isThereAUser()) {
      app.getContactHelper().createUser(new UserData("Andrey", "Rublev", "Boring Company",
              "222111333", "rublev@rublev.com"));
    }
    List<UserData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectUserPage(before.size() - 1);
    app.getContactHelper().deleteUserPage();
    app.getContactHelper().alertOk();
    app.getNavigationHelper().goToHomePage();
    List<UserData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() - 1);
  }
}
