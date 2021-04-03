package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;
import ru.stqa.pft.adressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {


  @Test
  public void testUserCreation() throws Exception {

    app.goTo().homePage();
    Users before = app.contact().all();
    UserData user = new UserData().withName("Andrey").withSurname("Rublev").withJob("Boring Company")
            .withPhone("222111333").withEmail("rublev@rublev.com");
    app.contact().create(user);
    assertThat(app.contact().count(),equalTo(before.size() + 1));
    Users after = app.contact().all();
    assertThat(after, equalTo
            (before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }


}