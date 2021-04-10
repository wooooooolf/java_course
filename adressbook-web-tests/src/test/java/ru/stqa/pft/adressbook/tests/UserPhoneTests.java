package ru.stqa.pft.adressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.adressbook.tests.TestBase.app;


public class UserPhoneTests extends TestBase {

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("group"));

    }


    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new UserData()
              .withName("Andrey")
              .withSurname("Rublev")
              .withJob("Boring Company")
              .withPhone("222111333")
              .withMobilePhone("2213133")
              .withWorkPhone("444333")
              .withEmail1("rublev@rublev.com")
              .withEmail2("andrey@rublev.com")
              .withEmail3("rublev@andrey.com")
              .withMainAddress("Perm")
      );
    }
  }

  @Test
  public void testUserPhones() {

    app.goTo().homePage();
    UserData user = app.contact().all().iterator().next();
    UserData userInfoFromEditForm = app.contact().infoFromEditForm(user);

    assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEditForm)));
    assertThat(user.getAllEmails(), equalTo(mergeEmails(userInfoFromEditForm)));
    assertThat(user.getMainAddress(), equalTo(mergeAddress(userInfoFromEditForm)));
  }

  private String mergePhones(UserData contact) {
    return Arrays.asList(contact.getPhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter(s -> !s.equals(""))
            .map(UserPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public String mergeEmails(UserData contact) {
    return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
            .stream().filter(s -> !s.equals(""))
            .map(UserPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public String mergeAddress(UserData contact) {
    return Arrays.asList(contact.getEditAddress())
            .stream().filter(s -> !s.equals(""))
            //.map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

}

