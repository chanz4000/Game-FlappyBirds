/**
 * 
 */
package flappybirds;

import java.awt.Rectangle;
import java.io.File;

import pkg2dgamesframework.Objects;
import pkg2dgamesframework.SoundPlayer;

/**
 * @author Trang
 *
 */
public class Bird extends Objects implements Observer{
	
	//toc do bay len cua chim
	private float vt = 0; 
	private boolean isFlying =false;
	private Rectangle rect;
	//kiem tra xem chim co con song hay khong
	private boolean isLive = true;
	public SoundPlayer plapSound, bupSounf, getMoneySound, base;
	
	
	public Bird(int x, int y, int w, int h) {
		super(x, y, w, h);
		rect = new Rectangle(x, y, w, h);
		plapSound = new SoundPlayer(new File("Assets/jump.wav"));
		bupSounf = new SoundPlayer(new File("Assets/collide.wav"));
		getMoneySound = new SoundPlayer(new File("Assets/congdiem.wav"));
		base = new SoundPlayer(new File("Assets/FirstDate-Frad-5510525.wav"));
	}
	
	/**
	 * @return the isLive
	 */
	public boolean isLive() {
		return isLive;
	}

	/**
	 * @param isLive the isLive to set
	 */
	public void setLive(boolean isLive) {
		
		this.isLive = isLive;
	}
	public boolean getLive() {
		return this.isLive;
	}
	

	public Rectangle getRect() {
		return rect;
	}
	
	
	//update viec lam roi con chim
	@Override
	public void update() {
		
		//lay van toc cong voi gia toc roi tu do => tang toc do len
		vt+=FlappyBirds.g;
		
		this.setPosY(this.getPosY()+ vt);
		this.rect.setLocation((int)this.getPosX(),(int) this.getPosY());
		if(vt<0)isFlying =true;
		else isFlying = false;
		
	}
	//toc do bay len
	public void fly() {
		vt=-3;
		plapSound.play();
	}
	public boolean isGetPlaying() {
		return isFlying;
	}
	public void setVt(float vt) {
		this.vt = vt;
	}

	/**
	 * @return the vt
	 */
	public float getVt() {
		return vt;
	}
  
}
