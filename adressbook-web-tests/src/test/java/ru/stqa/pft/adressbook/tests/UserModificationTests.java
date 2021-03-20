package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;

import java.util.List;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
    if (!app.getContactHelper().isThereAUser()) {
      app.getContactHelper().createUser(new UserData("Andrey", "Rublev", "Boring Company",
              "222111333", "rublev@rublev.com"));
    }
    app.getNavigationHelper().goToHomePage();
    List<UserData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initUserModification(before.size() - 1);
    app.getContactHelper().fillUserForm(new UserData("Andrey Ivanovich", "Rublev", "Boring Company",
            "222111333", "rublev@rublev.com"));
    app.getContactHelper().submitUserModification();
    app.getContactHelper().returnToHomePage();
    List<UserData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size());

  }
}
