package ru.stqa.pft.adressbook.model;

public class UserData {
  private int id = Integer.MAX_VALUE;
  private String name;
  private String surname;
  private String job;
  private String phone;
  private String email;

  public int getId() {
    return id;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withName(String name) {
    this.name = name;
    return this;
  }

  public UserData withSurname(String surname) {
    this.surname = surname;
    return this;
  }

  public UserData withJob(String job) {
    this.job = job;
    return this;
  }

  public UserData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
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

  @Override
  public String toString() {
    return "UserData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserData userData = (UserData) o;

    if (id != userData.id) return false;
    if (name != null ? !name.equals(userData.name) : userData.name != null) return false;
    return surname != null ? surname.equals(userData.surname) : userData.surname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (surname != null ? surname.hashCode() : 0);
    return result;
  }
}
