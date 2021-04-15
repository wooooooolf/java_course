package ru.stqa.pft.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;
import ru.stqa.pft.adressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().users().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new UserData().withName("Andrey").withSurname("Rublev").withJob("Boring Company")
              /*.withPhone("222111333")*/.withEmail("rublev@rublev.com"));
    }
  }

  @Test
  public void testUserDeletion() {

    app.goTo().homePage();
    Users before = app.db().users();
    System.out.println(before);
    UserData deletedUser = before.iterator().next();
    app.contact().delete(deletedUser);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()-1));
    Users after = app.db().users();
    System.out.println(after);
    assertThat(after, equalTo(before.withOut(deletedUser)));
    verifyUserListInUI();

  }


}
