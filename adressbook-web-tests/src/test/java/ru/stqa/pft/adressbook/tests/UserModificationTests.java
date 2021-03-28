package ru.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;
import ru.stqa.pft.adressbook.model.Users;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new UserData().withName("Andrey").withSurname("Rublev").withJob("Boring Company")
              .withPhone("222111333").withEmail("rublev@rublev.com"));
    }
  }

  @Test
  public void testUserModification() {

    Users before = app.contact().all();
    UserData modifiedUser = before.iterator().next();
    UserData user = new UserData()
            .withId(modifiedUser.getId()).withName("Andrey Ivanovich").withSurname("Rublev")
            .withJob("Boring Company").withPhone("222111333").withEmail("rublev@rublev.com");
    app.contact().modify(user);
    Users after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(modifiedUser).withAdded(user)));

  }


}