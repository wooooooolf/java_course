package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.adressbook.model.UserData;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void deleteUserPage() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
  }

  public void selectUserPage() {
    wd.findElement(By.name("selected[]")).click();
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

  public void initUserModification() {
    wd.findElement(By.xpath("(//img[@alt='Edit'])[2]")).click();
  }

  public void submitUserModification() {
    wd.findElement(By.name("update")).click();
  }
}
