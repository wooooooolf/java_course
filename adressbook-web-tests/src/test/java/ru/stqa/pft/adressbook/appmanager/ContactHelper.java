package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.adressbook.model.UserData;

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

  public void addNewUser() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void alertOk() {
    wd.switchTo().alert().accept();
  }

  public void submitUserCreation() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void fillUserForm(UserData userData) {
    type1(By.name("firstname"), userData.getName());
    type1(By.name("lastname"), userData.getSurname());
    type1(By.name("company"), userData.getJob());
    type1(By.name("home"), userData.getPhone());
    type1(By.name("email"), userData.getEmail());
  }

  public void initUserCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void initUserModification(int index) {
    wd.findElements(By.xpath("(//img[@alt='Edit'])")).get(index).click();
  }

  public void submitUserModification() {
    wd.findElement(By.name("update")).click();
  }

  public void createUser(UserData userData) {
    initUserCreation();
    fillUserForm(userData);
    submitUserCreation();
    returnToHomePage();

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

  public List<UserData> getContactList() {
    List<UserData> users = new ArrayList<UserData>();
    List<WebElement> elements = wd.findElements(By.xpath("(//img[@alt='Edit'])"));
    for (WebElement element : elements) {
      String name = element.getText();
      UserData user = new UserData(name, null, null, null, null);
      users.add(user);
    }
    return users;
  }
}

