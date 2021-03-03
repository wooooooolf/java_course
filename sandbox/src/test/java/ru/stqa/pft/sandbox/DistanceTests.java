package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

  @Test
  public void testDistance() {

    Point P1 = new Point (1, 1);
    Point P2 = new Point (2, 2);

    Assert.assertEquals(P1.distance(P2),1.3142135623730951);

  }
}
