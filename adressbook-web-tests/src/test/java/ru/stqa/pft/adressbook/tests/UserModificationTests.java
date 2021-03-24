package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAUser()) {
      app.getContactHelper().createUser(new UserData("Andrey", "Rublev", "Boring Company",
              "222111333", "rublev@rublev.com"));
    }
    List<UserData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initUserModification(before.size() - 1);
    UserData user = new UserData(before.get(before.size() - 1).getId(), "Andrey Ivanovich", "Rublev", "Boring Company",
            "222111333", "rublev@rublev.com");
    app.getContactHelper().fillUserForm(user);
    app.getContactHelper().submitUserModification();
    app.getContactHelper().returnToHomePage();
    List<UserData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}