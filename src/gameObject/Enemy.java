package gameObject;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import game.GameHandle;

public abstract class Enemy implements GameObject {
	protected int Hp, xVelocity, yVelocity, xPos, yPos, hpItemRate, upgradeItemRate, atk, score;
	protected static GameHandle handle;
	protected Shape hitbox;
	protected static final Type type = Type.ENEMY;
	protected Image image;
	protected Random rnd = new Random();
	
	public Enemy(int hp,int xVelocity,int yVelocity,int xPos,int yPos, GameHandle handle,
			int atk, int hpItemRate,int upgradeItemRate) {
		this.Hp = hp;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.xPos = xPos;
		this.yPos = yPos;
		Enemy.handle = handle;
		this.hpItemRate = hpItemRate;
		this.upgradeItemRate = upgradeItemRate;
		this.atk = atk;
	}
	
	
	protected void destroyed(int hpRate, int upgradeRate) {
		int hpChance = rnd.nextInt(100)+1;
		int upgradeChance = rnd.nextInt(100)+1;
		if(this instanceof Meteor) {
			handle.getLoad_Sound().getMeteorExplosion().play();
		}else if(this instanceof Boss) {
			handle.getLoad_Sound().getBossExplosion().play();
		}else {
			handle.getLoad_Sound().getAlienDestroyed().play();
		}
		if(hpRate >= hpChance) {
			if(this.xPos < 10) this.xPos = 10;
			if(this instanceof Boss) xPos += 230;
			handle.add(new HpItem(xPos,yPos,handle) );
		}
		if(upgradeRate >= upgradeChance) {
			if(this.xPos < 10) this.xPos = 10;
			if(this instanceof Boss) yPos += 230;
			handle.add(new UpgradeItem(xPos,yPos,handle));
		}
		handle.remove(this);
	};
	
	@Override
	public void move() {
		this.xPos += this.xVelocity;
		this.yPos += this.yVelocity;
	}
	
	public void collision() {
		Bullet playerBullet;
		for(int i = 0; i<handle.getAllObject().size(); i++) {
			if(handle.getAllObject().get(i) instanceof Bullet) {
				if(((Bullet)handle.getAllObject().get(i)).getType() == Type.PLAYERBULLET) {
					playerBullet = (Bullet)handle.getAllObject().get(i);
					if(this.hitbox.intersects(playerBullet.getHitbox().getBoundsInLocal())) {
						this.getHit(playerBullet.getAtk());
						playerBullet.remove();
						if(this.Hp<=0) {
							this.destroyed(this.hpItemRate,this.upgradeItemRate);
						}
					}
				}
			}
		}
	}
	
	public void getHit(int damage) {
		this.Hp -= damage;
		if(this.Hp<= 0) {
			remove();
		}
	}
	
	public void remove() {
		handle.getPlayer().setNumEnemyDefeat(handle.getPlayer().getNumEnemyDefeat()+1);
		handle.remove(this);
		handle.getGameLogic().setEnemy(handle.getGameLogic().getEnemy() - 1);
		handle.getGameLogic().setScore(handle.getGameLogic().getScore() + this.score);
	};
	
	public Shape getHitbox() {
		return this.hitbox;
	}

	public int getHp() {
		return Hp;
	}

	public void setHp(int hp) {
		Hp = hp;
	}
	
	public void setAtk(int atk) {
		this.atk = atk;
	}
	
	public int getAtk() {
		return this.atk;
	}

	public int getXVelocity() {
		return xVelocity;
	}

	public void setXVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}

	public int getYVelocity() {
		return yVelocity;
	}

	public void setYVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
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

	public void setYpos(int yPos) {
		this.yPos = yPos;
	}
	
	public Type getType() {
		return type;
	}
}
