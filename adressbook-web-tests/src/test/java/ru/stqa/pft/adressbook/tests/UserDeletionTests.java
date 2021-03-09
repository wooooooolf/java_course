package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase{

  @Test
  public void testUserDeletion() {

    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectUserPage();
    app.getContactHelper().deleteUserPage();
    app.getContactHelper().alertOk();
  }
}
