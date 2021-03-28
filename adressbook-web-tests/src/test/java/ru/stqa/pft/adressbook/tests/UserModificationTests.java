package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

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

    List<UserData> before = app.contact().list();
    int index = before.size() - 1;
    UserData user = new UserData().withId(before.get(index).getId()).withName("Andrey Ivanovich").withSurname("Rublev")
            .withJob("Boring Company").withPhone("222111333").withEmail("rublev@rublev.com");
    app.contact().modify(index, user);
    List<UserData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}