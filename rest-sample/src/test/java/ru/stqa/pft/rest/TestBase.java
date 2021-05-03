package ru.stqa.pft.rest;

import appmanager.ApplicationManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager();

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }


  public Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  boolean isIssueOpen(int issueId) throws IOException {

    String json = getExecutor().execute(Request.Get(app.getProperty("rest.baseUrl")+issueId+".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issue_data = parsed.getAsJsonObject().get("issues");
    JsonElement obj= issue_data .getAsJsonArray().get(0);
    String issue_status = obj.getAsJsonObject().get("state_name").getAsString();
    return !issue_status.equals("Closed");
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId) == true) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
