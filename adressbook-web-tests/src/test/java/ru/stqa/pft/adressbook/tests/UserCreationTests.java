package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {


  @Test
  public void testUserCreation() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List<UserData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initUserCreation();
    UserData user = new UserData("Andrey", "Rublev", "Boring Company",
            "222111333", "rublev@rublev.com");
    app.getContactHelper().createUser(user);
    List<UserData> after = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToHomePage();
    Assert.assertEquals(after.size(), before.size() + 1);


    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}