package gameObject;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import java.util.Random;

import game.GameHandle;


public class NormalEnemy extends Enemy {

	private Random rand = new Random();
	private int randRow = rand.nextInt(20)+1;
	private int delayImage = 1, imageNow = 1;
	private static Image alienMove1,alienMove2,alienMove3;
	
	public NormalEnemy(int XVelocity, int YVelocity,int Xpos,int Ypos,GameHandle handle) {
		super(100, XVelocity, YVelocity, Xpos, Ypos,handle,15,10,10);
		this.hitbox = new Circle(20);
		alienMove1 = NormalEnemy.handle.getLoad_image().getNormalEnemy1();
		alienMove2 = NormalEnemy.handle.getLoad_image().getNormalEnemy2();
		alienMove3 = NormalEnemy.handle.getLoad_image().getNormalEnemy3();
		this.image = alienMove1;
		this.score = 100;
	}
	
	private void setRenderImage() {
		if(this.delayImage == 3) {
			if(this.imageNow == 1) {
				this.image = alienMove1;
			}else if(this.imageNow == 2) {
				this.image = alienMove2;
			}else if(this.imageNow == 3) {
				this.image = alienMove3;
			}else if(this.imageNow == 4) {
				this.image = alienMove2;
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
	
	@Override
	public void tick() {
		collision();
		move();
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
		int randMove = rand.nextInt(100);
		if(this.yPos == 100+randRow*10) {
			if(this.yVelocity != 0 ) {
				this.yVelocity = 0;
				if(randMove <= 50) {
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
		if(this.yVelocity == 0 && (this.xVelocity == 5 || this.xVelocity == -5)) {
			if(randMove < 1) {
				int playerXPos=300;
				int playerYPos=300;
				for(int i = 0; i<handle.getAllObject().size(); i++) {
					if(handle.getAllObject().get(i) instanceof Player) {
						playerXPos = ((Player)handle.getAllObject().get(i)).getXpos();
						playerYPos = ((Player)handle.getAllObject().get(i)).getYpos();
						break;
					}
				}
				if(Math.abs(playerYPos - yPos) == 0){
					this.yVelocity = 0;
					if(playerXPos >= this.xPos) {
						this.xVelocity = 10 ;
					}else {
						this.xVelocity = -10;
					}
				}else {
					this.xVelocity = (playerXPos- xPos)/((Math.abs(playerYPos - yPos))/10+1);
					this.yVelocity = (playerYPos - yPos )/(Math.abs(playerYPos - yPos))*10;
				}
			}
		}
		this.xPos += this.xVelocity;
		this.yPos += this.yVelocity;	
	}
}
