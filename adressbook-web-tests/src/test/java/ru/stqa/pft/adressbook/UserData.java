package ru.stqa.pft.adressbook;

public class UserData {
  private final String name;
  private final String surname;
  private final String job;
  private final String phone;
  private final String email;

  public UserData(String name, String surname, String job, String phone, String email) {
    this.name = name;
    this.surname = surname;
    this.job = job;
    this.phone = phone;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getJob() {
    return job;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }
}
