package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("group", "group group", "group group group"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
  }

}
