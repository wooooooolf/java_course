package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;

public class UserModificationTests extends TestBase{

  @Test
  public void testUserModification() {
    app.getContactHelper().initUserModification();
    app.getContactHelper().fillUserForm(new UserData("Andrey Ivanovich", "Rublev", "Boring Company", "222111333", "rublev@rublev.com"));
    app.getContactHelper().submitUserModification();
    app.getContactHelper().returnToHomePage();

  }
}
