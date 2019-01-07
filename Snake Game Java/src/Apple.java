import java.awt.Color;
import java.awt.Graphics;

public class Apple {

	private int xCoordinate,yCoordinate,width,heigth;
	
	public Apple(int xCoordinate,int yCoordinate,int tileSize)
	{
		this.xCoordinate=xCoordinate;
		this.yCoordinate=yCoordinate;
		width=tileSize;
		heigth=tileSize;
	}
	public void tick() 
	{
		
	}
	public void draw(Graphics g) 
	{
		g.setColor(Color.GREEN);
		g.fillRect(xCoordinate*width, yCoordinate*heigth, width, heigth);
	}
	public int getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public int getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
}