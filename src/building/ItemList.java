package building;

import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import Positioning.PointMath;
import java.awt.Color;

public class ItemList {
	private List<Planet> planetList = new ArrayList<Planet>();
	private List<Projectile> projectileList = new ArrayList<Projectile>();
        private Status status;
        private int ArmedPlanet = -1;
        private int ArmedPlanet2= -1;
        private boolean win = false;
        PointMath geo = new PointMath();
	public ItemList() {
            status = new Status();
            Planet first = new Planet(true);
            planetList.add(first);
            for(int i=0;i<8;i++) {
                Planet ball = new Planet(planetList);
                if(ball.distance<400) {
                    planetList.add(ball);
                }
            }
	}
        public void middleClick(int x, int y) {
            int clicked = planetAt(x, y);
            if(clicked!=-1 && planetList.get(clicked).canArm && ArmedPlanet==-1) {
                planetList.get(clicked).armed=true;
                planetList.get(clicked).color=Color.RED;
                ArmedPlanet = clicked;
            } else {
                if(clicked!=-1 && planetList.get(clicked).canArm && ArmedPlanet2==-1) {
                planetList.get(clicked).armed=true;
                planetList.get(clicked).color=Color.cyan;
                ArmedPlanet2 = clicked;
                }
            }
            
        }
	public void rightClick(int x, int y) {
            if(ArmedPlanet!=-1 && ArmedPlanet2!=-1) {
                Planet launchPlanet;
                if(status.turn==1) {
                launchPlanet = planetList.get(ArmedPlanet);
                } else {
                launchPlanet = planetList.get(ArmedPlanet2);
                }
                Point click = new Point(x, y);
                Point launch = launchPlanet.location;
                float xmom = geo.XDistance(click, launch);
                float ymom = geo.YDistance(click, launch);
                Projectile missile = new Projectile(launch.x, launch.y, xmom, ymom, launchPlanet.size);
                projectileList.add(missile);
                status.shot();
                if(status.shots==5) {
                        status.turn();
                }
            }
        }
        public void leftClick(int x, int y) {
                if(ArmedPlanet!=-1 && ArmedPlanet2!=-1) {
                Planet launchPlanet;
                if(status.turn==1) {
                launchPlanet = planetList.get(ArmedPlanet);
                } else {
                launchPlanet = planetList.get(ArmedPlanet2);
                }
                Point click = new Point(x, y);
                Point launch = launchPlanet.location;
                float xmom = geo.XDistance(click, launch);
                float ymom = geo.YDistance(click, launch);
                Projectile missile = new Projectile(launch.x, launch.y, xmom, ymom, launchPlanet.size, true);
                projectileList.add(missile);
            }
        }
	
	public void draw(Graphics g) {
            for(int i=0;i<planetList.size();i++) {
                    planetList.get(i).draw(g);
            }
            for(int i=0;i<projectileList.size();i++) {
                Projectile pro = projectileList.get(i);
                pro.draw(g, planetList);
                int found = planetAt(pro.location.x, pro.location.y);
                if(found!=-1) {
                    float spacing = geo.findDistance(planetList.get(found).location, pro.location);
                    if(spacing<2+planetList.get(found).size) {
                        if(pro.canRemove) {
                            if(pro.launched) {
                                projectileList.remove(i);
                                i--;
                                planetList.get(found).hit(pro.location);
                                if(planetList.get(ArmedPlanet).hit && !win) {
                                    win=true;
                                    status.win(2);
                                } else if(planetList.get(ArmedPlanet2).hit && !win) {
                                    win=true;
                                    status.win(1);
                                }
                            } else {
                                projectileList.remove(i);
                                i--;
                            }
                        }
                    }
                }
            }
            status.draw(g);
	}
        // Collision Detection
	public int planetAt(int x, int y) {
            int onPlanet = -1;
            for(int i=0;i<planetList.size();i++) {
                Planet planet = planetList.get(i);
                if(geo.findDistance(planet.location, new Point(x, y))<planet.size/2) {
                    onPlanet = i;
                }
            }
            return onPlanet;
        }
	
}
