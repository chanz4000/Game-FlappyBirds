/**
 * 
 */
package flappybirds;

import java.awt.Rectangle;

import pkg2dgamesframework.Objects;

/**
 * @author Trang
 *
 */
public class Chimney extends Objects{
	
	private Rectangle rect;
	//xac dinh xem ong khoi nam sau con chim hay chua
	private boolean isBehindBird = false;
	
	public Chimney(int x, int y, int w, int h) {
		//toa do cua doi tuong
		super(x, y, w, h);
		rect = new Rectangle(x, y, w, h);
	}
	public Rectangle getRect() {
		return rect;
	}
	//lam cho nhung ong khoi chuyen dong va thay doi toa do hinh chu nhat quanh no
	public void update() {
		setPosX(getPosX()-2);
		
		rect.setLocation((int)this.getPosX(),(int) this.getPosY());
	}
	/**
	 * @return the isBehindBird
	 */
	public boolean isBehindBird() {
		return isBehindBird;
	}
	/**
	 * @param isBehindBird the isBehindBird to set
	 */
	public void setBehindBird(boolean isBehindBird) {
		this.isBehindBird = isBehindBird;
	}
	

}
