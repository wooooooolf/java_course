package ru.stqa.pft.rest;

import Model.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

  @BeforeMethod
  public void checkIfIssueIsFixed() throws IOException {
    skipIfNotFixed(1);
  }

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = app.restHelper().getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("new test issue");
    int issueId = app.restHelper().createIssue(newIssue);
    Set<Issue> newIssues = app.restHelper().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

}
