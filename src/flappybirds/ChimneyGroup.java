/**
 * 
 */
package flappybirds;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Queue;
import java.util.Random;

import javax.imageio.ImageIO;

import flappybirds.Chimney;
import pkg2dgamesframework.QueueList;

/**
 * @author Trang
 *
 */
public class ChimneyGroup extends Object implements Observer{

	public QueueList<Chimney> chimneys;
	private BufferedImage chimneyImage, chimneyImage2, background;
	public static int SIZE = 6;
	//khoảng cách 2 ống khói trên dưới
	private int topChimney = -350;
	private int bottomChimney = 200;
	
	public Chimney getChimney(int i) {
		return chimneys.get(i);
	}

	public ChimneyGroup() {

		try {
			background = ImageIO.read(new File("Assets/background.png"));
			chimneyImage = ImageIO.read(new File("Assets/chimney.png"));
			chimneyImage2 = ImageIO.read(new File("Assets/chimney2.png"));

		} catch (IOException ex) {
		}

		chimneys = new QueueList<Chimney>();

		Chimney cn;
		//tao ra cac ong khoi va athem vao hag doi
		
		for (int i = 0; i < SIZE/2; i++) {
			int detal = this.random();
			cn = new Chimney(830 + i * 300, bottomChimney+ detal, 74, 400);
			chimneys.push(cn);

			cn = new Chimney(830 + i * 300, topChimney+ detal, 74, 400 );
			chimneys.push(cn);
		}

	}
	//chinh do chenh lecch cua cac ong khoi tiep theo
	public int random() {
		Random rd = new Random();
		int a = rd.nextInt(10);
		return a*30;
	}

	@Override
	public void update() {
		
		for(int i = 0;i < SIZE; i++){
            chimneys.get(i).update();
        }
        
        //kiem tra xem ong khoi dau tien có bé hơn 0? neu be hon thi xoa ra khỏi hàng đợi, thay đổi kích thước và cho vào lại vòng lặp
        if(chimneys.get(0).getPosX()<-74) {
        	
        	
        	int detal = this.random();
            Chimney cn;
            cn = chimneys.pop();
            cn.setPosX(chimneys.get(4).getPosX() + 300);
            cn.setPosY(bottomChimney+ detal);
            cn.setBehindBird(false);
            chimneys.push(cn);
            
            cn = chimneys.pop();
            cn.setPosX(chimneys.get(4).getPosX());
            cn.setPosY(topChimney+ detal);
            chimneys.push(cn);
        }
	}

	public void paint(Graphics2D g2) {
		g2.drawImage(background, 0, 0, 800, 600, null);
		
		for (int i = 0; i < 6; i++) {
			if (i % 2 == 0)
				g2.drawImage(chimneyImage, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);
			else
				g2.drawImage(chimneyImage2, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);

		}
	}
	//tao hang doi chua 3 ong khoi va cho chung lap di lap lai
	public void resertChimney() {
		chimneys = new QueueList<Chimney>();

		Chimney cn;
		for (int i = 0; i < SIZE/2; i++) {
			int detal = this.random();
			cn = new Chimney(830 + i * 300,bottomChimney +detal, 74, 400);
			chimneys.push(cn);

			cn = new Chimney(830 + i * 300, topChimney+detal, 74, 400 );
			chimneys.push(cn);
	}

	}
}
