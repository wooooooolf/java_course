package ru.stqa.pft.sandbox;

public class Distance {

  public static void main(String[] args) {

    Point P = new Point(1, 2, 3, 4);
    System.out.println("points distance =" + distance(P));
  }

  public static double distance(Point P) {
    return Math.sqrt((P.p3 - P.p1) * (P.p3 - P.p1) + (P.p4 - P.p2) * (P.p4 - P.p2));
  }
}