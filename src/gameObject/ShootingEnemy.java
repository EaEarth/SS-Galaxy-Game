package gameObject;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;

import game.GameHandle;

public class ShootingEnemy extends Enemy {
	
	private Random rand = new Random();
	private int randMove = rand.nextInt(100) ,randRow = rand.nextInt(20)+1,
				delayImage = 1, imageNow = 1;

	public ShootingEnemy(int XVelocity, int YVelocity, int Xpos, int Ypos, GameHandle handle) {
		super(150, XVelocity, YVelocity, Xpos, Ypos, handle, 20, 15, 15);
		this.hitbox = new Circle(20);
		this.image = ShootingEnemy.handle.getLoad_image().getShootingAlien1();
		this.score = 150;
	}

	@Override
	public void tick() {
		int shootingChance = rand.nextInt(100)+1;
		collision();
		move();
		if(shootingChance <= 2) {
			handle.add(new Bullet(0,15,this.xPos,this.yPos,this.atk,handle,Type.ENEMYBULLET,0));
		}
		if(this.yPos >= 800 || this.yPos <= -250 || this.xPos >= 800 || this.xPos <= -300) {
			handle.remove(this);
			handle.getGameLogic().setEnemy(handle.getGameLogic().getEnemy() - 1);
		}
		((Circle)hitbox).setCenterX(xPos+21);
		((Circle)hitbox).setCenterY(yPos+20);

	}

	@Override
	public void render(GraphicsContext game) {
		if(!handle.getGameLogic().isPause()) {
			setRenderImage();
		}
		game.drawImage(image,xPos,yPos);
	}
	
	@Override
	public void move() {
		if(this.yPos == 100+randRow*10) {
			if(this.yVelocity != 0 ) {
				this.yVelocity = 0;
				if(this.randMove <= 50) {
					this.xVelocity = 5;
				}else {
					this.xVelocity = -5;
				}
			}
			if(xPos < 1) {
				this.xVelocity = 5;
			} else if(xPos > 550) {
				this.xVelocity = -5;
			}
		}
		this.xPos += this.xVelocity;
		this.yPos += this.yVelocity;
	}
	
	private void setRenderImage() {
		if(this.delayImage == 3) {
			if(this.imageNow == 1) {
				this.image = ShootingEnemy.handle.getLoad_image().getShootingAlien1();
			}else if(this.imageNow == 2) {
				this.image = ShootingEnemy.handle.getLoad_image().getShootingAlien2();
			}else if(this.imageNow == 3) {
				this.image = ShootingEnemy.handle.getLoad_image().getShootingAlien3();
			}else if(this.imageNow == 4) {
				this.image = ShootingEnemy.handle.getLoad_image().getShootingAlien2();
			}
			if(this.imageNow == 4) {
				this.imageNow = 1;
			}else {
				++this.imageNow;
			}
			this.delayImage = 1;
		}else {
			++this.delayImage;
		}
	}
}
