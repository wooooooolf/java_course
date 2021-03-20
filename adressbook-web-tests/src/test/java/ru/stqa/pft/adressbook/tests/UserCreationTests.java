package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;

import java.util.List;

public class UserCreationTests extends TestBase {


  @Test
  public void testUserCreation() throws Exception {

    List<UserData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initUserCreation();
    app.getContactHelper().createUser(new UserData("Andrey", "Rublev", "Boring Company",
            "222111333", "rublev@rublev.com"));
    app.getNavigationHelper().goToHomePage();
    List<UserData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() + 1);
  }


}