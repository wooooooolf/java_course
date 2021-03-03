package ru.stqa.pft.sandbox;

public class Point {
  public final double x;
  public final double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;

  }

  public double distance(Point p2) {
    double distance = Math.sqrt((Math.pow((p2.x - this.x), 2) + Math.pow((p2.y - this.y), 2)));
    return distance;

  }

}