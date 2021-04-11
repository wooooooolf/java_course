package ru.stqa.pft.adressbook.generators;


import ru.stqa.pft.adressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {

  public static void main(String [] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<UserData> users = generateUsers(count);
    save(users, file);
  }

  private static void save(List<UserData> users, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (UserData user : users) {
      writer.write(String.format("%s;%s,%s\n", user.getName(), user.getSurname(), user.getHomePhone()));
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
      );
    }
    return users;
  }
}

