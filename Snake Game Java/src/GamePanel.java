import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable,KeyListener {
private static final long serialVersionUID=1L;

public static final int WIDTH=500,HEIGHT=500;

private Thread thread;
private boolean running;
private boolean right=true,left= false,up=false,down=false;
private BodyPart b;
private ArrayList<BodyPart> snake;
private int xCoor=10, yCoor=10, size=5;
private int ticks=0;
private Apple apple;
private ArrayList<Apple> apples;
private Random r;

	public GamePanel() 
	{
		
		setFocusable(true);
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		snake=new ArrayList<BodyPart>();
		apples= new ArrayList<Apple>();
		r= new Random();
		start();
		
	}
	public void start() { 
		
	
	    running=true;
		thread= new Thread(this);
		thread.start();
	
	}
	public void stop() 
	{
	
		running=false;
		
		try {
			
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void tick() 
	{
		if (snake.size()==0) {
			b= new BodyPart(xCoor,yCoor,10);
			snake.add(b);
			
		}
		ticks++;
		if (ticks>250000) {
			if(right)xCoor++;
			if(left)xCoor--;
			if(up)yCoor--;
			if(down)yCoor++;
			 ticks=0;
			 b=new BodyPart(xCoor,yCoor,10);
			 snake.add(b);
			 
			 if (snake.size()>size) {
				snake.remove(0);
				
			}
			
		
		}
		
		if (apples.size()==0) {
			int xCoor=r.nextInt(49);
			int yCoor=r.nextInt(49);
			apple= new Apple(xCoor,yCoor,10);
			apples.add(apple);
		}
		
		//Collision with apple
		for (int i = 0; i < apples.size(); i++) {
			if (xCoor==apples.get(i).getxCoordinate()&& yCoor==apples.get(i).getyCoordinate()) {
				size++;
				apples.remove(i);
				i++;
			}
		}
		
		//Collision with snake body
		for (int i = 0; i < snake.size(); i++) {
			if (xCoor==snake.get(i).getxCoordinate() && yCoor==snake.get(i).getyCoordinate()) {
				if (i!=snake.size()-1) {
					
					System.out.println("GAME OVER");
					
					stop();
				}
			}
		}
		if (xCoor<0 || xCoor>49 || yCoor<0 || yCoor>49)  {
		
	
		System.out.println("GAME OVER");
			stop();
		}
		
		
	}
	public void paint(Graphics g) 
	{
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		/*for (int i = 0; i < WIDTH/10; i++) {
		for (int j = 0; j < HEIGHT/10; j++) {
			g.setColor(Color.RED);
			g.fillRect(i*10, j*10, WIDTH/10,HEIGHT/10);
		}
	}Managed to make red grid with black outline!*/
		for (int i = 0; i < WIDTH/10; i++) {
	g.drawLine(i*10, 0, i*10, HEIGHT);
        }
		for (int i = 0; i < HEIGHT/10; i++) {
			g.drawLine(0, i*10, HEIGHT, i*10);
		        }
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
	
		
	}
	@Override
	public void run() {
while (running) {
	tick();
	repaint();
}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if (key==KeyEvent.VK_RIGHT && !left) {
			right=true;
			up=false;
			down=false;
		}
		if (key==KeyEvent.VK_LEFT && !right) {
			left=true;
			up=false;
			down=false;
		}
		if (key==KeyEvent.VK_UP && !down) {
			left=false;
			up=true;
			right=false;
		}
		if (key==KeyEvent.VK_DOWN && !up) {
			left=false;
			right=false;
			down=true;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
