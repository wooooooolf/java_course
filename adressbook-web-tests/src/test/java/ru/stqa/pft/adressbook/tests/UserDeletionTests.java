package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.UserData;

import java.util.List;

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

    List<UserData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().homePage();
    List<UserData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);


  }


}
