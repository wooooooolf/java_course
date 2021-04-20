package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;
import ru.stqa.pft.adressbook.model.UserData;
import ru.stqa.pft.adressbook.model.Users;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserToGroupTest extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData()
              .withName("test1")
              .withFooter("2341"));
    }

    /*app.goTo().homePage();*/
    if (app.db().users().size() == 0) {
      app.contact().create(new UserData()
              .withName("Andrey")
              .withSurname("Rublev")
              .withMobilePhone("89288")
              .withEmail("andrey@rublev.com")
              .withJob("Boring Company")
              .withPhoto(new File("src/test/resources/14.jpg")));
      app.goTo().homePage();
    }

      /*Users users = app.db().users();
      if (users.stream().iterator().next().getGroups().size() == 0){
        app.contact().selectUserPageById(users.iterator().next().getId());
        app.contact().selectGroup(users);
        app.contact().addToGroupButton();
        app.goTo().goToGroupPageAfter();
      }*/
  }


  @Test

  public void testUserToGroup() {

    app.goTo().homePage();
    UserData addUser = selectUser();
    GroupData groupToAddTo = selectGroup(addUser);
    Groups before = addUser.getGroups();
    app.goTo().homePage();
    app.contact().addUserToGroup(addUser, groupToAddTo);
    app.goTo().homePage();
    UserData addUserAfter = selectUserById(addUser);
    Groups after = addUserAfter.getGroups();
    assertThat(after, equalTo(before.withAdded(groupToAddTo)));
  }

  private UserData selectUserById(UserData addUser) {
    Users usersById = app.db().users();
    return usersById.iterator().next().withId(addUser.getId());

  }

  private GroupData selectGroup(UserData user) {
    Groups allGroups = app.db().groups();
    Groups usersInGroups = user.getGroups();

    Collection<GroupData> userGroups = new HashSet<>(usersInGroups);
    Collection<GroupData> avaliableGroups = new HashSet<>(allGroups);
    avaliableGroups.removeAll(userGroups);
    return avaliableGroups.iterator().next();
  }


  private UserData selectUser() {

    Users allUsers = app.db().users();
    Groups allGroups = app.db().groups();
    for (UserData user : allUsers) {
      if (user.getGroups().size() < allGroups.size()) {
        return user;
      }
    }
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test group").withHeader("test header"));
    return allUsers.iterator().next();
  }
}


