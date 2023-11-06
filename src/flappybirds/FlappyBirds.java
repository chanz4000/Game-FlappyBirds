/**
 * 
 */
package flappybirds;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.Buttonn;
import pkg2dgamesframework.GameScreen;
import pkg2dgamesframework.MouseHandler;
import pkg2dgamesframework.SoundPlayer;

/**
 * @author Trang
 *
 */
public class FlappyBirds extends GameScreen {

	private BufferedImage birds;
	public Animation bird_anim;
	private BufferedImage chimney;
	
	private RunGame runGame = new RunGame(this);;

	private BufferedImage logo;
	private BufferedImage start;
	private BufferedImage replay;
	public BufferedImage musicon;
	public BufferedImage musicoff;
	public int Point = 0;
	public int maxPoint =0;
	private int lever = 1;

	public Bird bird;
	public Ground ground;
	private ChimneyGroup chimneyGroup;;

   
	public Buttonn btnStart, btnReplay, btnMusic;
	
	

	// gia toc roi tu do
	public static float g = 0.15f;

	private int BEGIN_SCREEN = 0;
	private int GAMEPLAY_SCREEN = 1;
	private int GAMEOVER_SCREEN = 2;

	private int CurrentScreen = BEGIN_SCREEN;

	public FlappyBirds() {
		// kich thuoc cua so game
		super(800, 600);
		// hien thi chinh giua man hinh
		setLocationRelativeTo(null);

		addMouseListener(new MouseHandler(this));

		try {
			birds = ImageIO.read(new File("Assets/bird_sprite.png"));
			musicon = ImageIO.read(new File("Assets/musicOn.png"));
			musicoff = ImageIO.read(new File("Assets/musicOff.png"));
			logo = ImageIO.read(new File("Assets/thanhniengame-flappy-birds-family-2 (2).jpg"));
			

		} catch (IOException ex) {
		}

		// tham so nay nghia la buc hinh se chuyen dong trong bao nhieu lau=> toc do
		// canh cua chim;

		bird_anim = new Animation(70);
		btnMusic = new Buttonn(650, 10, 80, 80, musicon);
		AFrameOnImage f;

		f = new AFrameOnImage(0, 0, 60, 60);
		bird_anim.AddFrame(f);
		f = new AFrameOnImage(60, 0, 60, 60);
		bird_anim.AddFrame(f);
		f = new AFrameOnImage(120, 0, 60, 60);
		bird_anim.AddFrame(f);
		f = new AFrameOnImage(60, 0, 60, 60);
		bird_anim.AddFrame(f);

		bird = new Bird(350, 350, 50, 50);

		chimneyGroup = new ChimneyGroup();

		ground = new Ground();
		BeginGame();
		
		

	}

	@Override
	public void GAME_UPDATE(long deltaTime) {

		
		runGame.GAME_UPDATE(deltaTime);

	}



	@SuppressWarnings("deprecation")
	@Override
	public void GAME_PAINT(Graphics2D g2) {

		chimneyGroup.paint(g2);

		ground.Paint(g2);
		if(btnMusic == null) {
	        btnMusic = new Buttonn(650, 10, 80, 80, musicon);
	    }
		this.btnMusic.render(g2);

		if(btnMusic.pressed) {
	        if(bird != null && bird.base != null && bird.base.clip != null) {
	            if(bird.base.clip.isRunning()){
	            	btnMusic = new Buttonn(650, 10, 80, 80, musicoff);
	                bird.base.clip.stop();
	            } else {
	            	btnMusic = new Buttonn(650, 10, 80, 80, musicon);
	                bird.base.clip.start();
	            }
	        }
	    }
		

		// neu chim bay thi dau se nguoc len
		if (bird.isGetPlaying())
			bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, -1);
		else
			bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, 0);

		if (CurrentScreen == BEGIN_SCREEN) {
			g2.setFont(new Font("SuperMario", Font.BOLD, 20));
			g2.setColor(Color.BLACK);
			g2.drawImage(logo, 200, 140, 400, 100, null);

			g2.drawString("press space to play game", 300, 320);
			try {
				start = ImageIO.read(new File("Assets/btnStart.png"));
				btnStart = new Buttonn(340, 340, 120, 60, start);
				this.btnStart.render(g2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (CurrentScreen == GAMEOVER_SCREEN) {

			try {
				BufferedImage imgGameOver = ImageIO.read(new File("Assets/gameOver.jpg"));
				g2.drawImage(imgGameOver, 290, 100, 250, 150, null);
                
				btnMusic = new Buttonn(650, 10, 80, 80, musicon);
				this.btnMusic.render(g2);
				
				replay = ImageIO.read(new File("Assets/btnReplay.png"));
				btnReplay = new Buttonn(370, 250, 80, 80, replay);

				this.btnReplay.render(g2);

			} catch (IOException ex) {
			}

		}

//		Font fontScore = Font.loadFont(String.valueOf(getClass().getResource("font/fontScore.ttf")),48);

		g2.setFont(new Font("SuperMario", Font.BOLD, 30));
		g2.setColor(Color.BLACK);
		g2.drawString("POINT: " + Point, 350, 50);
		g2.setColor(Color.red);
		g2.drawString("MAX: " + maxPoint, 80, 50);
		
//		g2.drawString("LEVER: " + lever, 430, 70);
	}

	@Override
	public void KEY_ACTION(KeyEvent e, int Event) {

		runGame.KEY_ACTION(e, Event);

	}

	/**
	 * @return the gAMEPLAY_SCREEN
	 */
	public int getGAMEPLAY_SCREEN() {
		return GAMEPLAY_SCREEN;
	}

	/**
	 * @param currentScreen the currentScreen to set
	 */
	public void setCurrentScreen(int currentScreen) {
		CurrentScreen = currentScreen;
	}

	/**
	 * @return the bEGIN_SCREEN
	 */
	public int getBEGIN_SCREEN() {
		return BEGIN_SCREEN;
	}
	

	/**
	 * @return the gAMEOVER_SCREEN
	 */
	public int getGAMEOVER_SCREEN() {
		return GAMEOVER_SCREEN;
	}

	/**
	 * @param gAMEOVER_SCREEN the gAMEOVER_SCREEN to set
	 */
	public void setGAMEOVER_SCREEN(int gAMEOVER_SCREEN) {
		GAMEOVER_SCREEN = gAMEOVER_SCREEN;
	}

	/**
	 * @return the bird
	 */
	public Bird getBird() {
		return bird;
	}

	/**
	 * @return the currentScreen
	 */
	public int getCurrentScreen() {
		return CurrentScreen;
	}
	

	/**
	 * @return the chimneyGroup
	 */
	public ChimneyGroup getChimneyGroup() {
		return chimneyGroup;
	}

	/**
	 * @param chimneyGroup the chimneyGroup to set
	 */
	
	public void setChimneyGroup(ChimneyGroup chimneyGroup) {
		this.chimneyGroup = chimneyGroup;
	}

	public static void main(String[] args) throws IOException {
		new FlappyBirds();
	}

}
