//@author alieb
package building;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.Random;
import Positioning.PointMath;

public class Projectile extends Item {
    
    public Color color = Color.LIGHT_GRAY;
    public Color fillColor = Color.WHITE;
    public float xmomentum;
    public float ymomentum;
    public float xCurrent;
    public float yCurrent;
    public int minDistance;
    public Point start;
    public boolean launched = false;
    public boolean canRemove = true;
    private PointMath geo = new PointMath();
    private Random random = new Random();
    
    public Projectile(int x, int y, float xmom, float ymom, int size) {
        xmomentum= xmom*(float)Math.sqrt(size)*2;
        ymomentum= ymom*(float)Math.sqrt(size)*2;
        minDistance = size/2+1;
        location = new Point(x, y);
        start = new Point(x, y);
        drawLocation = new Point(x-2, y-2);
        canRemove = false;
        launched = true;
        color = Color.red;
        fillColor = Color.ORANGE;
    }
    public Projectile(int x, int y, float xmom, float ymom, int size, boolean tracer) {
        xmomentum= xmom*(float)Math.sqrt(size)*2;
        ymomentum= ymom*(float)Math.sqrt(size)*2;
        minDistance = size/2+1;
        location = new Point(x, y);
        start = new Point(x, y);
        canRemove = false;
        drawLocation = new Point(x-2, y-2);
    }
    
    public void step(List<Planet> planets) {
        for(int i=0;i<planets.size();i++) {
            Planet planet = planets.get(i);
            float apart = geo.findDistance(planet.drawLocation, location);
            if(apart<planet.well) {
                float pull = geo.findGravity(apart, planet.size);
                xmomentum -= pull/2 * geo.XDistance(location, planet.location);
                ymomentum -= pull/2 * geo.YDistance(location, planet.location);
            }
        }
        float centerPull = geo.findGravity(geo.findDistance(location, origin), 24);
        xmomentum-=centerPull* geo.XDistance(location, origin);
        ymomentum-=centerPull* geo.YDistance(location, origin);
        xCurrent = location.x + xmomentum/244;
        yCurrent = location.y + ymomentum/244;
        location.move((int)xCurrent, (int)yCurrent);
        drawLocation.move(location.x-2, location.y-2);
        if(!canRemove) {
            if(geo.findDistance(start, location)>(float)minDistance) {
                canRemove=true;
            }
        }
    }
    public void draw(Graphics g, List<Planet> list) {
        step(list);
        g.setColor(fillColor);
        g.fillOval(drawLocation.x, drawLocation.y, 4, 4);
        g.setColor(color);
        g.drawOval(drawLocation.x, drawLocation.y, 4, 4);
    }
}
