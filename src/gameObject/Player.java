package gameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import game.GameHandle;

public class Player implements GameObject {
	
	private int count,lv,invincibleFrame = 0,maxHp,hp,sp,atk,xPos,yPos,ultFrame=0;
	private boolean goNorth,goSouth,goWest,goEast,isGetHit = false;
	private final Type type = Type.PLAYER;
	private Image image;
	private static GameHandle handle;
	private Circle hitbox;
	
	public Player(int hp, int xPos, int yPos, int atk,GameHandle handle ) {
		sp = 0;
		this.hp = hp;
		maxHp = hp;
		this.xPos = xPos;
		this.yPos = yPos;
		Player.handle = handle;
		this.atk = atk;
		count = 0;
		lv = 1;
		goNorth = false;
		goSouth = false;
		goWest = false;
		goEast = false;
		hitbox = new Circle(xPos+40,yPos+70,10);
		image = Player.handle.getLoad_image().getShipLv1();
	}

	public void tick() {
		if(ultFrame != 0) {
			for(int i = 0; i<handle.getAllObject().size(); i++) {
				GameObject temp = handle.getAllObject().get(i);
				if(temp.getType() == Type.ENEMY) {
					if(((Enemy)temp).getHp() <=20 )--i;
					((Enemy)temp).getHit(20);
				}else if(temp.getType() == Type.ENEMYBULLET) {
					((Bullet)temp).remove();
					--i;
				}
			}
			ultFrame++;
		}
		if(ultFrame >= 30) {
			ultFrame = 0;
		}
		++sp;
		if(sp > 1800) {
			sp = 1800;
		}
		move();
		runInvincFrame();
		((Circle)hitbox).setCenterX(xPos+40);
		((Circle)hitbox).setCenterY(yPos+70);
		collision();
		shoot();
	}
	
	private void shoot() {
		if(count <= 0 ) {
			if(lv == 1) {
				handle.add(new Bullet(0,-20,getXpos()+43,getYpos(),atk,handle,Type.PLAYERBULLET,0));
				handle.add(new Bullet(0,-20,getXpos()+33,getYpos(),atk,handle,Type.PLAYERBULLET,0));
				count = 5;
			}else if(lv >= 2) {
				handle.add(new Bullet(0,-20,getXpos()+50,getYpos(),atk,handle,Type.PLAYERBULLET,0));
				handle.add(new Bullet(0,-20,getXpos()+40,getYpos(),atk,handle,Type.PLAYERBULLET,0));
				handle.add(new Bullet(0,-20,getXpos()+30,getYpos(),atk,handle,Type.PLAYERBULLET,0));
				if(lv == 3) {
					handle.add(new Bullet(10,-20,getXpos()+70,getYpos()+10,atk,handle,Type.PLAYERBULLET,30));
					handle.add(new Bullet(20,-20,getXpos()+70,getYpos()+20,atk,handle,Type.PLAYERBULLET,60));
					handle.add(new Bullet(-10,-20,getXpos()+10,getYpos()+10,atk,handle,Type.PLAYERBULLET,-30));
					handle.add(new Bullet(-20,-20,getXpos()+10,getYpos()+20,atk,handle,Type.PLAYERBULLET,-60));
				}
				count = 3;
			}
		}
		count--;
	}
	
	private void runInvincFrame() {
		if(isGetHit) {
			if(invincibleFrame < 90) {
				invincibleFrame++;
				if(invincibleFrame % 5 == 0) {
					if(lv == 1) {
						if(image == Player.handle.getLoad_image().getShipLv1GetHit()) {
							image = Player.handle.getLoad_image().getShipLv1();
						}else {
							image = Player.handle.getLoad_image().getShipLv1GetHit();
						}
					} else if(lv == 2) {
						if(image == Player.handle.getLoad_image().getShipLv2GetHit()) {
							image = Player.handle.getLoad_image().getShipLv2();
						}else {
							image = Player.handle.getLoad_image().getShipLv2GetHit();
						}
					} else {
						if(image == Player.handle.getLoad_image().getShipLv3()) {
							image = Player.handle.getLoad_image().getShipLv3GetHit();
						}else {
							image = Player.handle.getLoad_image().getShipLv3();
						}
					}
				}
			}else {
				if(lv == 1) {
					image = Player.handle.getLoad_image().getShipLv1();
				}else if(lv == 2) {
					image = Player.handle.getLoad_image().getShipLv2();
				}else {
					image = Player.handle.getLoad_image().getShipLv3();
				}
				isGetHit = false;
			}
		}
	}
	
	public void getItem(GameObject item) {
		if(item instanceof HpItem) {
			handle.getLoad_Sound().getItemCollected().play();
			if(hp == maxHp) {
				handle.getGameLogic().setScore(handle.getGameLogic().getScore()+100);
			}
			hp += ((HpItem)item).getHpGain();
			if(hp > maxHp) {
				hp = maxHp;
			}
		}else {
			handle.getLoad_Sound().getItemCollected().play();
			if(lv < 3) {
				if(image == Player.handle.getLoad_image().getShipLv1()) {
					image = Player.handle.getLoad_image().getShipLv2();
				}else if(image == Player.handle.getLoad_image().getShipLv1GetHit()) {
					image = Player.handle.getLoad_image().getShipLv2GetHit();
				}else if(image == Player.handle.getLoad_image().getShipLv2()) {
					image = Player.handle.getLoad_image().getShipLv3();
				}else {
					image = Player.handle.getLoad_image().getShipLv3GetHit();
				}
				++lv;
			}else {
				handle.getGameLogic().setScore(handle.getGameLogic().getScore()+100);
			}
		}
	}
	
	public void getHit(int damage) {
		if(!isGetHit) {
			hp -= damage;
			isGetHit = true;
			if(lv == 1) {
				image = Player.handle.getLoad_image().getShipLv1GetHit();
			} else if(lv == 2) {
				image = Player.handle.getLoad_image().getShipLv1GetHit();
				--lv;
			}else {
				image = Player.handle.getLoad_image().getShipLv2GetHit();
				--lv;
			}
			invincibleFrame = 1;
		}
		if(hp <= 0) {
			hp = 0;
			this.remove();
		}
	}

	public void render(GraphicsContext game) {
		game.drawImage(image,xPos,yPos);
		if(ultFrame != 0 && ultFrame % 3 == 0) {
			game.setFill(Color.WHITE);
			game.fillRect(0,0 , 800, 800);
		}
	}
	
	public void move() {
		if(xPos < 0) {
			goWest = false;
		}
		if(yPos < -20) {
			goNorth = false;
		}
		if(xPos > 520) {
			goEast = false;
		}
		if(yPos > 600) {
			goSouth = false;;
		}
		if(goNorth) {
			yPos -= 10;
		}
		if(goSouth) {
			yPos += 10;
		}
		if(goEast) {
			xPos += 10;
		}
		if(goWest) {
			xPos -= 10;
		}
	}


	public void remove() {
		for(int i = 0; i<handle.getAllObject().size(); i++) {
			if(handle.getAllObject().get(i) instanceof Supporter) {
				((Supporter)handle.getAllObject().get(i)).remove();
				i--;
			}
		}
		handle.remove(this);
	}
	

	public void collision() {
		for(int i = 0; i<handle.getAllObject().size(); i++) {
			GameObject temp = handle.getAllObject().get(i);
			if(temp.getType() == Type.ITEM) {
				if(this.getHitbox().intersects(((Item)temp).getHitbox().getBoundsInLocal())){
					((Player)this).getItem(temp);
					((Item)temp).remove();
				}
			}else if(temp.getType() == Type.ENEMY) {
				if(this.getHitbox().intersects(((Enemy)temp).getHitbox().getBoundsInLocal())){
					((Player)this).getHit(((Enemy)temp).getAtk());
				}
			}else if(temp.getType() == Type.ENEMYBULLET) {
				if(this.getHitbox().intersects(((Bullet)temp).getHitbox().getBoundsInLocal())){
					((Player)this).getHit(((Bullet)temp).getAtk());
					if(!(temp instanceof BossLaser)) ((Bullet)temp).remove();
				}
			}
		}
	}
	
	public void ultimate() {
		handle.getLoad_Sound().getExplosion().play();
		if(sp >= 1800) {
			sp = 0;
			ultFrame = 1;
		}
	}


	public void setGoNorth(boolean goNorth) {
		this.goNorth = goNorth;
	}

	public void setGoSouth(boolean goSouth) {
		this.goSouth = goSouth;
	}

	public void setGoWest(boolean goWest) {
		this.goWest = goWest;
	}

	public void setGoEast(boolean goEast) {
		this.goEast = goEast;
	}

	public void setIsGetHit(boolean getHit) {
		this.isGetHit = getHit;
	}
	
	public boolean isGoNorth() {
		return goNorth;
	}

	public boolean isGoSouth() {
		return goSouth;
	}

	public boolean isGoWest() {
		return goWest;
	}

	public boolean isGoEast() {
		return goEast;
	}

	public boolean getIsGetHit() {
		return isGetHit;
	}
	
	public Circle getHitbox() {
		return hitbox;
	}
	
	public int getXpos() {
		return xPos;
	}
	
	public int getYpos() {
		return yPos;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getSp() {
		return sp;
	}
	
	public void setSp(int sp) {
		this.sp = sp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	@Override
	public Type getType() {
		return type;
	}
}
