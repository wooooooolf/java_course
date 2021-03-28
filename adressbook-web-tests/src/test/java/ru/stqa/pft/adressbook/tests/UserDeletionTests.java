package ru.stqa.pft.adressbook.tests;

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
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new UserData().withName("Andrey").withSurname("Rublev").withJob("Boring Company")
              .withPhone("222111333").withEmail("rublev@rublev.com"));
    }
  }

  @Test
  public void testUserDeletion() {

    Users before = app.contact().all();
    UserData deletedUser = before.iterator().next();
    app.contact().delete(deletedUser);
    app.goTo().homePage();
    Users after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(after.withOut(deletedUser)));


  }


}
