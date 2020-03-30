package gameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import game.GameHandle;

public class Bullet implements GameObject {
	
	protected int xPos,yPos,velX,velY,atk;
	protected Image image;
	protected static GameHandle handle;
	protected Rectangle hitbox;
	protected Type type;
	
	public Bullet(int velX,int velY,int xPos,int yPos,int atk, GameHandle handle, Type type, int Rotate) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.velX = velX;
		this.velY = velY;
		this.type = type;
		this.atk = atk;
		Bullet.handle = handle;
		if(type == Type.PLAYERBULLET) {
			if(Rotate == 0) {
				image = Bullet.handle.getLoad_image().getPlayerBullet();
				this.hitbox = new Rectangle(5,20);
			}else if(Rotate == 30) {
				image = Bullet.handle.getLoad_image().getPlayerBullet30();
				this.hitbox = new Rectangle(5,20);
				this.hitbox.setRotate(30);
			} else if(Rotate == 60) {
				image = Bullet.handle.getLoad_image().getPlayerBullet60();
				this.hitbox = new Rectangle(5,20);
				this.hitbox.setRotate(60);
			}else if(Rotate == -30) {
				image = Bullet.handle.getLoad_image().getPlayerBulletMinus30();
				this.hitbox = new Rectangle(5,20);
				this.hitbox.setRotate(-30);
			} else if(Rotate == -60) {
				image = Bullet.handle.getLoad_image().getPlayerBulletMinus60();
				this.hitbox = new Rectangle(5,20);
				this.hitbox.setRotate(-60);
			}
		}else {
			if(Rotate == 0) {
				image = Bullet.handle.getLoad_image().getEnemyBullet();
				this.hitbox = new Rectangle(10,20);
			}else if(Rotate == 30) {
				image = Bullet.handle.getLoad_image().getEnemyBullet30();
				this.hitbox = new Rectangle(10,20);
				this.hitbox.setRotate(30);
			} else if(Rotate == 60) {
				image = Bullet.handle.getLoad_image().getEnemyBullet60();
				this.hitbox = new Rectangle(10,20);
				this.hitbox.setRotate(60);
			}else if(Rotate == -30) {
				image = Bullet.handle.getLoad_image().getEnemyBulletMinus30();
				this.hitbox = new Rectangle(10,20);
				this.hitbox.setRotate(-30);
			} else if(Rotate == -60) {
				image = Bullet.handle.getLoad_image().getEnemyBulletMinus60();
				this.hitbox = new Rectangle(10,20);
				this.hitbox.setRotate(-60);
			}
		}
	}

	@Override
	public void tick() {
		move();
		if(xPos < 0 || yPos < 0 || xPos > 700 || yPos > 800  ) {
			handle.remove(this);
		}
		this.hitbox.setX(xPos);
		this.hitbox.setY(yPos);
	}

	@Override
	public void render(GraphicsContext game) {
		game.drawImage(image, xPos, yPos);
	}
	
	@Override
	public void move() {
		xPos += velX;
		yPos += velY;
	}

	public int getXPos() {
		return xPos;
	}

	public void setXPos(int xPos) {
		this.xPos = xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setYPos(int yPos) {
		this.yPos = yPos;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public Rectangle getHitbox() {
		return this.hitbox;
	}

	@Override
	public void remove() {
		handle.remove(this);
	}
}
