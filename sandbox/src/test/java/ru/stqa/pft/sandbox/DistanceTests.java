package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {

  @Test
  public void testDistance() {

    Distance D = new Distance();
    Point1 P1 = new Point1(2, 2);
    Point2 P2 = new Point2(3, 3);

    Assert.assertEquals(D.distance(P1,P2),1.3142135623730951);

  }
}
