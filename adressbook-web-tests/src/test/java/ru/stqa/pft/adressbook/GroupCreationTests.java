package ru.stqa.pft.adressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{


  @Test
  public void testGroupCreation() throws Exception {

    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("group1", "group group", "group group group"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
