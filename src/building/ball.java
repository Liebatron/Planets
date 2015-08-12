package building;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.Random;

public class ball {
	public Point location;
	public int distance;
	public int size;
	public float steps;
	public float speed;
	public Point origin;
	public ball(List<ball> list) {
		Random random = new Random();

		int sizeLast=0;
		if(list.size()>0) {
                    distance=list.get(list.size()-1).getDistance();
                    sizeLast=list.get(list.size()-1).size*3/4;
		} else {
                    distance = 10;
                    sizeLast=0;
                }
		size = random.nextInt(((list.size()+1)*10)+list.size()*10);
		float speedSize = (float)size;
                distance += sizeLast;
		distance +=size;
		location = new Point(400, 350);		
		origin = new Point(400, 350);
		steps = 0;
		speed = (float)3*((speedSize*speedSize)/((float)distance*(float)distance));
	}
	public int getDistance() {
		return distance;
	}
	
	public void step() {
		steps+=speed;
		double angle = Math.toRadians(steps);
		double x = Math.cos(angle) * distance + origin.x-(size/2);
		double y = Math.sin(angle) * distance + origin.y-(size/2);
		int intX = (int)x;
		int intY = (int)y;
		location = new Point(intX, intY);
	}
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.drawOval(400-distance, 350-distance, distance*2, distance*2);
                g.setColor(Color.CYAN.darker());
		g.fillOval(location.x, location.y, size, size);
	}
	
	
}
