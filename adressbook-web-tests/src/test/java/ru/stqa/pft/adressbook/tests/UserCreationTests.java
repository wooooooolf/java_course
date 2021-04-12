package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.UserData;
import ru.stqa.pft.adressbook.model.Users;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsers(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new UserData().withName("Andrey").withSurname("Rublev").withPhone("21212121")
            .withJob("Boring Company").withPhoto(new File("src/test/resources/12.jpg"))});
    list.add(new Object[]{new UserData().withName("Andrey").withSurname("Rublev").withPhone("44444444")
            .withJob("KroshkaKartoshka").withPhoto(new File("src/test/resources/13.jpg"))});
    list.add(new Object[]{new UserData().withName("Andrey").withSurname("Rublev").withPhone("892888")
            .withJob("Soyuzpechat").withPhoto(new File("src/test/resources/14.jpg"))});
    return list.iterator();
  }
  @Test (dataProvider = "validUsers")
  public void testUserCreation(UserData user){

    app.goTo().homePage();
    Users before = app.contact().all();
    //File photo = new File("src/test/resources/12.jpg");
    app.contact().create(user);
    assertThat(app.contact().count(),equalTo(before.size() + 1));
    Users after = app.contact().all();
    assertThat(after, equalTo
            (before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }


}