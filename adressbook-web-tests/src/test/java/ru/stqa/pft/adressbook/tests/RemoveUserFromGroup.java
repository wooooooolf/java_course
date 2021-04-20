package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;
import ru.stqa.pft.adressbook.model.UserData;
import ru.stqa.pft.adressbook.model.Users;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveUserFromGroup extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData()
              .withName("test87")
              .withFooter("09887"));
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

  }


  @Test

  public void testUserRemoveFromGroup() {

    app.goTo().homePage();
    UserData user = selectUser();
    GroupData groupToRemoveFrom = selectGroup(user);
    Groups before = user.getGroups();
    app.goTo().homePage();
    app.contact().selectGroupFromList(groupToRemoveFrom.getId());
    app.contact().removeUserFromGroup(user, groupToRemoveFrom);
    UserData usersAfter = selectUserById(user);
    Groups after = usersAfter.getGroups();
    assertThat(after, equalTo(before.withOut(groupToRemoveFrom)));
  }

  private UserData selectUserById(UserData user) {
    Users usersById = app.db().users();
    return usersById.iterator().next().withId(user.getId());
  }


  private GroupData selectGroup(UserData removeUser) {

    UserData user = selectUserById(removeUser);
    Groups userToBeRemoved =  user.getGroups();
    return userToBeRemoved.iterator().next();

  }

  private UserData selectUser() {
    Users allUsers = app.db().users();
    for (UserData user : allUsers) {
      if (user.getGroups().size() > 0) {
        return user;
      }
    }

    UserData addUser = app.db().users().iterator().next();
    app.contact().addUserToGroup(addUser, app.db().groups().iterator().next());
    return addUser;
  }

}
