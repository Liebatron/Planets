package building;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class ItemList {
	private List<ball> list = new ArrayList<ball>();
	public ItemList() {
	}
	
	public void addBall() {
		ball ball = new ball(list);
    	list.add(ball);

	}
	
	public void progress() {
		for(int i=0;i<list.size();i++) {
			list.get(i).step();
		}
	}
	
	public void draw(Graphics g) {
		for(int i=0;i<list.size();i++) {
			list.get(i).draw(g);
		}
	}
	
	
	
}
