package ru.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;
import ru.stqa.pft.adressbook.model.Users;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {


    if (app.db().users().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new UserData()
              .withName("Andrey")
              .withSurname("Rublev")
              .withJob("Boring Company")
              .withMobilePhone("222111333")
              .withEmail("rublev@rublev.com"));
    }
  }

  @Test (enabled = true)

  public void testUserModification() throws Exception{

    app.goTo().homePage();
    Users before = app.db().users();
    UserData modifiedUser = before.iterator().next();
    UserData user = new UserData()
            .withId(modifiedUser.getId())
            .withName("Andrey Ivanovich")
            .withSurname("Rublev")
            .withJob("Boring Company")
            .withMobilePhone("222111333")
            .withEmail("rublev@rublev.com");
    app.goTo().homePage();
    app.contact().modify(user);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() ));
    Users after = app.db().users();
    assertThat(after, equalTo(before.withOut(modifiedUser).withAdded(user)));


  }


}