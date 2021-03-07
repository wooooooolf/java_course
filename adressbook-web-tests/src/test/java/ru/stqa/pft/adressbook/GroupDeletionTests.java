package ru.stqa.pft.adressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() throws Exception {

   goToGroupPage();
    selectGroup();
    deleteGroup();
    returnToGroupPage();
  }

}
