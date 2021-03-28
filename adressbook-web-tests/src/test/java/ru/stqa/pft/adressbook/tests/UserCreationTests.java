package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {


  @Test
  public void testUserCreation() throws Exception {
    app.goTo().homePage();
    List<UserData> before = app.contact().list();
    UserData user = new UserData().withName("Andrey").withSurname("Rublev").withJob("Boring Company")
            .withPhone("222111333").withEmail("rublev@rublev.com");
    app.contact().create(user);
    List<UserData> after = app.contact().list();
    app.goTo().homePage();
    Assert.assertEquals(after.size(), before.size() + 1);


    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}