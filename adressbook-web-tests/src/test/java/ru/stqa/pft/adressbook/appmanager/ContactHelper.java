package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.adressbook.model.Groups;
import ru.stqa.pft.adressbook.model.UserData;
import ru.stqa.pft.adressbook.model.Users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


  public void initUserCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

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

  public void create(UserData userData) {
    initUserCreation();
    fillUserForm(userData);
    submitUserCreation();
    userCache = null;
    returnToHomePage();

  }

  public void modify(UserData user) {
    selectUserPageById(user.getId());
    initUserModificationById(user.getId());
    fillUserForm(user);
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

  public void fillUserForm(UserData userData) {
    type1(By.name("firstname"), userData.getName());
    type1(By.name("lastname"), userData.getSurname());
    type1(By.name("company"), userData.getJob());
    type1(By.name("home"), userData.getPhone());
    type1(By.name("email"), userData.getEmail());
  }

  public boolean isThereAUser() {
    return isElementPresent(By.name("selected[]"));
  }

  private boolean isElementPresent(By locator1) {
    try {
      wd.findElement(locator1);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

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

  public Users userCache = null;

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
      String phones = items.get(5).getText();
      String emails = items.get(4).getText();
      String address = items.get(3).getText();

      userCache.add(new UserData()
              .withId(id)
              .withName(name)
              .withSurname(lastname));


    }
    return new Users(userCache);
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }
}


