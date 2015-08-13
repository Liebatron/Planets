//@author alieb
package Positioning;

import java.awt.Point;

public class PointMath {
    
    
    public float findGravity(float distance, int size) {
        return size/distance;
    }
    public float findDistance(Point a, Point b) {
        float closex = Math.abs(a.x)-Math.abs(b.x);
        float closey = Math.abs(a.y)-Math.abs(b.y);
        float apart = Math.round((float)Math.sqrt((closex*closex)+(closey*closey)));
        return apart;
    }
    public float absXDistance(Point a, Point b) {
        return Math.abs(a.x)-Math.abs(b.x);
    }
    public float absYDistance(Point a, Point b) {
        return Math.abs(a.y)-Math.abs(b.y);
    }
    public float XDistance(Point a, Point b) {
        return a.x-b.x;
    }
    public float YDistance(Point a, Point b) {
        return a.y-b.y;
    }
}
