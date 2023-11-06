/**
 * 
 */
package pkg2dgamesframework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Trang
 *
 */
public class Buttonn {
	public int x;
	public int y;
	public int w;
	public int h;
	public BufferedImage img;

	public boolean pressed = false;

	public Buttonn(int x, int y, int w, int h, BufferedImage img) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.img = img;
	}

	public static boolean checkCollision(int mouseX, int mouseY, Buttonn btn) {
		if (mouseX >= btn.x && mouseY > btn.y && mouseY <= btn.y+ btn.h && mouseX <= btn.x+ btn.w) {
			btn.pressed=true;
			return true;
		} else {
			return false;
		}
	}
	

	/**
	 * @return the img
	 */
	public BufferedImage getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public void render(Graphics g) {
			g.drawImage(img, x , y, w, h,null);
	}
	
}
