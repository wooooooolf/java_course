package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;

public class UserCreationTests extends TestBase {


  @Test
  public void testUserCreation() throws Exception {

    app.getContactHelper().initUserCreation();
    app.getContactHelper().fillUserForm(new UserData("Andrey", "Rublev", "Boring Company", "222111333", "rublev@rublev.com"));
    app.getContactHelper().submitUserCreation();
    app.getContactHelper().returnToHomePage();

  }


}