package ru.stqa.pft.sandbox;

public class Distance {

  public static void main(String[] args) {


    Point p1 = new Point(1.0, 1.0);
    Point p2 = new Point(2.0, 2.0);


    System.out.println("Point 1 p1: x = " + p1.x + ", y = " + p1.y);
    System.out.println("Point 2 p2: x = " + p2.x + ", y = " + p2.y);
    System.out.println("Distance between p1 и p2 = " + p1.distance(p2));

  }
}