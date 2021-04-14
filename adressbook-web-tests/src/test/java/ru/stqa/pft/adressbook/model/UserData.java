package ru.stqa.pft.adressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;

@XStreamAlias("user")
@Entity
@Table (name = "addressbook")
public class UserData {
  @XStreamOmitField
  @Id
  @Column (name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column (name = "firstname")
  private String name;
  @Expose
  @Column (name = "lastname")
  private String surname;
  private String job;
  @Expose
  private String phone;
  @Expose
  @Column (name = "email")
  @Type(type = "text")
  private String email;
  @Expose
  private String groupName;
  @Expose
  @Column (name = "home")
  @Type(type = "text")
  private String homePhone;
  @Expose
  @Column (name = "mobile")
  @Type(type = "text")
  private String mobilePhone;
  @Column (name = "work")
  @Type(type = "text")
  private String workPhone;
  private String allPhones;
  private String allEmails;
  private String email1;
  private String email2;
  private String email3;
  private String editAddress;
  private String mainAddress;
  @Column (name = "photo")
  @Type(type = "text")
  private String photo;

  public String getgroupName() {
    return groupName;
  }

  public UserData withgroupName(String group_name) {
    this.groupName = groupName;
    return this;
  }

  public File getPhoto() {
    return new File(photo);
  }

  public UserData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }


  public String getMainAddress() {
    return mainAddress;
  }

  public UserData withMainAddress(String mainAddress) {
    this.mainAddress = mainAddress;
    return this;
  }


  public String getEditAddress() {
    return editAddress;
  }

  public UserData withEditAddress(String editAddress) {
    this.editAddress = editAddress;
    return this;
  }

  public String getEmail1() {
    return email1;
  }

  public UserData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public UserData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public UserData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public UserData withAllEmails(String emails) {
    this.allEmails = emails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public UserData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public UserData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public UserData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public UserData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }


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
