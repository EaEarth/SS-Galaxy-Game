package gameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import game.GameHandle;

public class BossLaser extends Bullet {

	private Boss boss;
	private int count; 
	
	public BossLaser(Boss boss,int xPos, int yPos, int atk,GameHandle handle, Type type) {
		super(boss.getXVelocity(), boss.getYVelocity(), xPos, yPos, atk, handle, type, 0);
		this.hitbox = new Rectangle(60,800);
		this.hitbox.setX(xPos);
		this.hitbox.setY(yPos);
		this.boss = boss;
		this.count = 0;
	}
	
	@Override
	public void tick() {
		this.count++;
		this.velX = boss.getXVelocity();
		this.velY = boss.getYVelocity();
		move();
		if(xPos < 0 || yPos < 0 || xPos > 700 || yPos > 800  ) {
			handle.remove(this);
		}
		this.hitbox.setX(xPos);
		this.hitbox.setY(yPos);
		if(this.count >= 60) {
			handle.remove(this);
		}
	}

	@Override
	public void render(GraphicsContext game) {
		game.setFill(Color.LIGHTGOLDENRODYELLOW);
		if(handle.getGameLogic().isPause()) {
			game.fillOval(xPos, yPos-30, 60, 60);
		}
		game.fillRect(xPos, yPos, 60, 800);
	}
}