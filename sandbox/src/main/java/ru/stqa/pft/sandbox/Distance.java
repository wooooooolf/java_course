package ru.stqa.pft.sandbox;

public class Distance {
  public static void main(String[] args) {

    Point1 P1 = new Point1(2, 2);

    Point2 P2 = new Point2(3, 3);
    System.out.println("points distance =" + distance(P1, P2));
  }

  public static double distance(Point1 P1, Point2 P2) {
    return Math.sqrt((P2.x - P1.x) * (P2.x - P1.x) + (P2.y - P1.y) * (P2.y - P1.y));
  }
}