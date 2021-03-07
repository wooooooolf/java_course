package ru.stqa.pft.adressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{


  @Test
  public void testGroupCreation() throws Exception {

    app.goToGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("group1", "group group", "group group group"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
