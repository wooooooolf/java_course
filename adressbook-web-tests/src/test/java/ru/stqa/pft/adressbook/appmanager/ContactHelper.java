package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;
import ru.stqa.pft.adressbook.model.UserData;
import ru.stqa.pft.adressbook.model.Users;

import java.util.ArrayList;
import java.util.List;



public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void deleteUserPage() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
  }

  public void selectUserPage(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectUserPageById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void addNewUser() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void alertOk() {
    wd.switchTo().alert().accept();
  }

  public void ensureUserDeleted() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void submitUserCreation() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void initUserCreation() { wd.findElement(By.linkText("add new")).click(); }

  public void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void initUserModification() {
    wd.findElement(By.xpath("(//img[@alt='Edit'])")).click();
  }

  public void submitUserModification() {
    wd.findElement(By.name("update")).click();
  }

  private void initUserModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }
  public void selectUserCheckboxById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }
  public void selectGroupFromList (int groupId) {
    new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(groupId));
  }
  public void selectGroupFromListToAdd (int groupId) {
    new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(groupId));
  }
  public void addToGroupButton() {
    wd.findElement(By.name("add")).click();
  }

  public void goToGroupPageAfter() {
    wd.findElement(By.partialLinkText("group page")).click();
    /*wd.findElement(By.cssSelector(String.format("a[href='./?group=%s']", id))).click();*/
  }


  public void create(UserData user) {
    initUserCreation();
    fillUserForm(user,true);
    submitUserCreation();
    userCache = null;
    returnToHomePage();

  }

  public void modify(UserData user) {
    selectUserPageById(user.getId());
    initUserModificationById(user.getId());
    fillUserForm(user, false);
    submitUserModification();
    userCache = null;
    returnToHomePage();
  }

  public void delete(UserData user) {
    selectUserPageById(user.getId());
    deleteUserPage();
    alertOk();
    ensureUserDeleted();
    userCache = null;
  }

  public void fillUserForm(UserData userData, boolean creation) {
    type1(By.name("firstname"), userData.getName());
    type1(By.name("lastname"), userData.getSurname());
    type1(By.name("company"), userData.getJob());
    type1(By.name("mobile"), userData.getMobilePhone());
    type1(By.name("email"), userData.getEmail());
    attach(By.name("photo"), userData.getPhoto());

    if (creation) {
      if (userData.getGroups().size() > 0) {
        Assert.assertTrue(userData.getGroups().size() == 1);
      }
      new Select(wd.findElement(By.name("new_group"))).getFirstSelectedOption();/*selectByVisibleText(userData.getGroups().iterator().next().getName()*/;
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public boolean isThereAUser() {
    return isElementPresent(By.name("selected[]"));
  }

  //private boolean isElementPresent(By locator1) {
  //  try {
  //    wd.findElement(locator1);
  //    return true;
  //  } catch (NoSuchElementException ex) {
  //    return false;
  //  }
  // }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }


  public List<UserData> list() {
    List<UserData> users = new ArrayList<UserData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> items = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String name = items.get(2).getText();
      String lastname = items.get(1).getText();
      users.add(new UserData().withId(id).withName(name).withSurname(lastname));

    }
    return users;

  }

  private Users userCache = null;

  public Users all() {

    if (userCache != null) {
      return new Users(userCache);
    }
    userCache = new Users();
    List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr"));
    elements.remove(0);

    for (WebElement element : elements) {

      List<WebElement> items = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String name = items.get(2).getText();
      String lastname = items.get(1).getText();
      String allPhones = items.get(5).getText();
      String allEmails = items.get(4).getText();
      String mainAddress = items.get(3).getText();

      userCache.add(new UserData()
              .withId(id)
              .withName(name)
              .withSurname(lastname)
              .withAllEmails(allEmails)
              .withAllPhones(allPhones)
              .withMainAddress(mainAddress));


    }
    return new Users(userCache);
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public UserData infoFromEditForm(UserData user) {

    initUserModificationById(user.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String editAddress = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();

    return new UserData()
            .withId(user.getId())
            .withName(firstname)
            .withSurname(lastname)
            /*.withPhone(home)*/
            .withMobilePhone(mobile)
            .withWorkPhone(work)
            //.withEmail1(email1)
            .withEmail2(email2)
            .withEmail3(email3)
            .withEditAddress(editAddress);

  }
  public void selectGroup(Users userData) {
    if (userData.iterator().next().getGroups().size() > 1) {
      Assert.assertTrue(userData.iterator().next().getGroups().size() == 1);
      new Select(wd.findElement(By.name("group"))).selectByVisibleText(userData.iterator().next().getGroups().iterator().next().getName());
    }


  }

  public void addUserToGroup(UserData userData, GroupData groupData) {
    selectUserPageById(userData.getId());
    selectGroupFromListToAdd(groupData.getID());
    addToGroupButton();
    goToGroupPageAfter();
    userCache = null;

  }
}



