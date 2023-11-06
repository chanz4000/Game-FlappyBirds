/**
 * 
 */
package pkg2dgamesframework;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import flappybirds.FlappyBirds;

/**
 * @author Trang
 *
 */
public class MouseHandler implements MouseListener {
	private FlappyBirds fl;

	public MouseHandler(FlappyBirds fl) {
		this.fl = fl;

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int x = e.getX();
		if (fl.btnMusic.checkCollision(e.getX(), e.getY(), fl.btnMusic)) {
			fl.btnMusic.pressed= true;
            
			if(fl.bird.base.clip.isRunning()) {
				fl.btnMusic = new Buttonn(650, 10, 80, 80, fl.musicoff);
				
				System.out.println("off");
				fl.bird.base.stop();
			}else if(!fl.bird.base.clip.isRunning()) {
				fl.btnMusic = new Buttonn(650, 10, 80, 80, fl.musicon);
				
				fl.bird.base.play();;
			} 
			

		}
		if (fl.btnStart.checkCollision(e.getX(), e.getY(), fl.btnStart)) {
			fl.btnStart.pressed = true;
			this.fl.setCurrentScreen(this.fl.getGAMEPLAY_SCREEN());
			
		}
		
		if(fl.btnReplay !=null) {
			if (fl.btnReplay.checkCollision(e.getX(), e.getY(), fl.btnReplay)) {
				fl.btnReplay.pressed = true;

				this.fl.setCurrentScreen(this.fl.getBEGIN_SCREEN());
				
			}
		}

		
		

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
