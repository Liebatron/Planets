package building;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import building.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DisplayPanel extends JPanel {
	private Random random = new Random();
    private Timer timer = null;
	private ItemList list = new ItemList();
    public DisplayPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                	// Right click
                	
                } else if((e.getModifiers() & InputEvent.BUTTON2_MASK) != 0) {
                    // Middle click

                } else {
                	// Left click
                	list.addBall();
                }
            }
        });
        timer = new Timer(60, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                list.progress();
                repaint();
            }
        });
        timer.start();
    }
	public Dimension getPreferredSize() {
        return new Dimension(800,700);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        list.draw(g);
    }

    private void addActionListener(ActionListener actionListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
