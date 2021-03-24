package ru.stqa.pft.adressbook.model;

public class UserData {
  private int id;
  private final String name;
  private final String surname;
  private final String job;
  private final String phone;
  private final String email;

  public UserData(int id, String name, String surname, String job, String phone, String email) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.job = job;
    this.phone = phone;
    this.email = email;
  }

  public UserData(String name, String surname, String job, String phone, String email) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.surname = surname;
    this.job = job;
    this.phone = phone;
    this.email = email;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

    if (name != null ? !name.equals(userData.name) : userData.name != null) return false;
    return surname != null ? surname.equals(userData.surname) : userData.surname == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (surname != null ? surname.hashCode() : 0);
    return result;
  }
}
