package ru.stqa.pft.adressbook.generators;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {

  @Parameter (names = "-c", description = "Group count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main(String [] args) throws IOException {
    UserDataGenerator generator = new UserDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);}
    catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    List <UserData> users = generateUsers(count);
    if (format.equals("csv")) {
      saveAsCsv(users, new File(file));
    } else if (format.equals("xml")){
      saveAsXml(users, new File(file));
    } else if (format.equals("json")){
      saveAsJson(users, new File(file));
    }
    else {
      System.out.println("Unrecognized format" + format);
    }

  }

  private void saveAsJson(List<UserData> users, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(users);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<UserData> users, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    String xml = xstream.toXML(users);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }
  private static void saveAsCsv(List<UserData> users, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (UserData user : users) {
      writer.write(String.format("%s;%s;%s;%s;%s\n", user.getName(),
              user.getSurname(),
              user.getHomePhone(),
              user.getEmail(),
              /*user.getGroups(),*/
              user.getPhoto()));
    }
    writer.close();
  }

  private static List<UserData> generateUsers(int count) {

    List <UserData> users = new ArrayList<UserData>();
    for (int i = 0; i < count; i++) {
      users.add(new UserData()
              .withName(String.format("Andrey %s", i))
              .withSurname(String.format("Rublev %s", i))
              .withHomePhone(String.format("8988 %s", i))
              .withEmail(String.format("rublev@rublev.ru %s", i))
              /*.withgroupName(String.format("group %s", i))*/
              .withPhoto(new File("src/test/resources/14.jpg"))
      );
    }
    return users;
  }
}

