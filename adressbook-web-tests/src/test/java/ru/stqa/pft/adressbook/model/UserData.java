package ru.stqa.pft.adressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

  @XStreamAlias("user")
  @Entity
  @Table(name = "addressbook")
  public class UserData {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String name;

  @Expose
  @Column(name = "lastname")
  private String surname;

  @Expose
  @Column(name = "company")
  private String job;

  /*@Expose
  private String phone;*/

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  /*@Expose
  @Transient
  private String groupName;*/

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Transient
  private String allPhones;

  @Transient
  private String allEmails;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Transient
  private String editAddress;

  @Column(name = "address")
  @Type(type = "text")
  private String mainAddress;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  public File getPhoto() {
    if (photo == null) {
      return null;
    } else {
      return new File(photo);
    }
  }

  public UserData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }


  @ManyToMany (fetch = FetchType.EAGER)
  @JoinTable (name = "address_in_groups",
          joinColumns = @JoinColumn (name = "id"), inverseJoinColumns = @JoinColumn (name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  /*public String getgroupName() {
    return groupName;
  }

  public UserData withgroupName(String group_name) {
    this.groupName = groupName;
    return this;
  }*/




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

  /*public String getEmail1() {
    return email1;
  }

  public UserData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }*/

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

    public String getName() {
      return name;
    }

  public UserData withSurname(String surname) {
    this.surname = surname;
    return this;
  }
    public String getSurname() {
      return surname;
    }

  public UserData withJob(String job) {
    this.job = job;
    return this;
  }
    public String getJob() {
      return job;
    }

  /*public UserData withPhone(String phone) {
    this.phone = phone;
    return this;
  }*/
      /*public String getPhone() {
    return phone;
  }*/

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

    public String getEmail() {
      return email;
    }

  public UserData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
    public Groups getGroups() {
      return new Groups(groups);
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
      if (surname != null ? !surname.equals(userData.surname) : userData.surname != null) return false;
      if (email != null ? !email.equals(userData.email) : userData.email != null) return false;
      return mobilePhone != null ? mobilePhone.equals(userData.mobilePhone) : userData.mobilePhone == null;
    }

    @Override
    public int hashCode() {
      int result = name != null ? name.hashCode() : 0;
      result = 31 * result + (surname != null ? surname.hashCode() : 0);
      result = 31 * result + (email != null ? email.hashCode() : 0);
      result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
      return result;
    }

}
