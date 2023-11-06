/**
 * 
 */
package flappybirds;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Trang
 *
 */
public class RunGame {
	FlappyBirds fl ;
	 List<Observer>list = new ArrayList<>();
	 
	
	public RunGame(FlappyBirds fl) {
		super();
		this.fl = fl;
		ChimneyGroup cn = fl.getChimneyGroup();
		list.add(cn);
		Bird b = fl.bird;
		list.add(b);
		Ground g = fl.ground;
		list.add(g);
	}
	public void GAME_UPDATE(long deltaTime) {
		
		if (this.fl.getCurrentScreen() == this.fl.getBEGIN_SCREEN()) {

			resetGame();
			fl.bird.base.play();

		} else if (fl.getCurrentScreen() == fl.getGAMEPLAY_SCREEN()) {

			fl.bird.update();
			fl.ground.update();
			fl.getChimneyGroup().update();
			if (fl.bird.isLive())
				fl.bird_anim.Update_Me(deltaTime);
			
			
			this.notifyObserver();


			for (int i = 0; i < fl.getChimneyGroup().SIZE; i++) {
				// chim la mot hcn, moi cai cot cung la mot hinh chu nhat, kiem tra xem neu 2
				// hình chu nhat co va nhau hay khong
				if (fl.bird.getRect().intersects(fl.getChimneyGroup().getChimney(i).getRect())) {
					if (fl.bird.isLive())
						fl.bird.bupSounf.play();
					fl.bird.setLive(false);

				}

			}
			// duyet qua tat ca ong khoi neu toa do cua chim> toa do ua ong khoi va ong khoi
			// nam sau con chim thi diem cong them 1
			for (int i = 0; i < fl.getChimneyGroup().SIZE; i++) {
				if (i < 6) {
					if (i % 2 == 0) {
						if (fl.bird.getPosX() > fl.getChimneyGroup().getChimney(i).getPosX()
								&& !fl.getChimneyGroup().getChimney(i).isBehindBird()) {
							fl.Point++;
							if(fl.maxPoint< fl.Point)fl.maxPoint=fl.Point;

							fl.bird.getMoneySound.play();
							fl.getChimneyGroup().getChimney(i).setBehindBird(true);
						}
					}
				} else {
					if (fl.bird.getPosX() > fl.getChimneyGroup().getChimney(i).getPosX()
							&& !fl.getChimneyGroup().getChimney(i).isBehindBird()) {
						fl.Point++;
						fl.getChimneyGroup().getChimney(i).setBehindBird(true);
					}
				}

			}
//			//tang lever cho game
//			if(this.Point>= 7) {
//				lever+=1;
//				this.bird.setVt(this.bird.getVt()-1);
//				int size = this.chimneyGroup.chimneys.getSize();
//				for (int i = 0; i < size; i++) {
//					float x = this.chimneyGroup.chimneys.get(i).getPosX();
//					
//					this.chimneyGroup.chimneys.get(i).setPosX(x-3);
//				}
//			}

			if (fl.bird.getPosY() + fl.bird.getH() > fl.ground.getYGround())
				fl.setCurrentScreen(fl.getGAMEOVER_SCREEN()); 

		}

		

	}
	public void resetGame() {
		fl.bird.setPos(350, 250);
		fl.bird.setVt(0);
		fl.bird.setLive(true);
		fl.Point= 0;
		fl.getChimneyGroup().resertChimney();
	}
	
	
	public void notifyObserver() {
		for (Observer observer : list) {
			if(observer !=null)
			observer.update();
		}
	}
	public void KEY_ACTION(KeyEvent e, int Event) {
		// TODO Auto-generated method stub
		if (Event == fl.KEY_PRESSED) {

			if (fl.getCurrentScreen() == fl.getBEGIN_SCREEN()) {
				fl.setCurrentScreen(fl.getGAMEPLAY_SCREEN());

			} else if (fl.getCurrentScreen() == fl.getGAMEPLAY_SCREEN()) {
				if (fl.bird.getLive())
					fl.bird.fly();
			} else if (fl.getCurrentScreen() == fl.getGAMEOVER_SCREEN()) {
				fl.setCurrentScreen(fl.getBEGIN_SCREEN());;
			}

		}

	}

}
