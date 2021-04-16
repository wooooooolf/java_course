package ru.stqa.pft.adressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;
import ru.stqa.pft.adressbook.model.UserData;
import ru.stqa.pft.adressbook.model.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {

    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @DataProvider
  public Iterator<Object[]> validUsers() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new UserData().withName(split[0]).withSurname(split[1])
              /*.withPhone(split[2])*/.withEmail(split[3])/*withgroupName(split[4])*/.withPhoto(new File("src/test/resources/14.jpg"))});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validUsersFromXml() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null){
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<UserData> users = (List<UserData>) xstream.fromXML(xml);
    return users.stream().map ((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

  }
  @DataProvider
  public Iterator<Object[]> validUsersFromJson() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<UserData> users = gson.fromJson(json,new TypeToken<List<UserData>>(){}.getType());
    return users.stream().map ((u) -> new Object[] {u}).collect(Collectors.toList()).iterator();

  }


  /*@Test(dataProvider = "validUsersFromJson")
  public void testUserCreation(UserData user) {

    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/12.jpg");
    UserData newUser = new UserData()
            .withName("Andrey")
            .withSurname("Rublev")
            .withPhoto(photo)
            .inGroup(groups.iterator().next());
    app.goTo().homePage();
    Users before = app.db().users();
    app.contact().create(newUser);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Users after = app.db().users();
    assertThat(after, equalTo
            (before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
    verifyUserListInUI();
  }*/

  @Test (dataProvider = "validUsersFromJson")
  public void testUserCreation(UserData user) throws Exception {

    Groups groups = app.db().groups();
    app.goTo().homePage();
    Users before = app.db().users();
    UserData userInGroup = user.inGroup(groups.iterator().next());
    app.contact().create(userInGroup);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Users after = app.db().users();
    assertThat(after, equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
    verifyUserListInUI();

  }

}