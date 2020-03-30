package gameObject;

import java.util.Random;

import game.GameHandle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Boss extends Enemy {

	private Random rnd;
	private int  shootFrame;
	private boolean isShootingFirstForm, isShootingSecondForm;
	
	public Boss(int hp, GameHandle handle) {
		super(hp, 0, 5, 10, -400, handle,20,100,100);
		this.image = Boss.handle.getLoad_image().getBoss();
		this.hitbox = new Rectangle(460,260);
		this.rnd = new Random();
		this.isShootingFirstForm = false;
		this.isShootingSecondForm = false;
		this.score = 1000;
	}

	@Override
	public void tick() {
		collision();
		move();
		if(this.yPos >= 10) shoot();
		((Rectangle)this.hitbox).setX(xPos);
		((Rectangle)this.hitbox).setY(yPos);
		if(this.yPos >= 800) {
			handle.remove(this);
		}
	}
	
	@Override
	public void render(GraphicsContext game) {
		game.drawImage(image, xPos, yPos);
		if(this.isShootingSecondForm && !handle.getGameLogic().isPause()) {
			this.shootingSecondForm(game);
		}
	}

	@Override
	public void move() {
		if(this.yPos < 10) {
			this.yVelocity = 5;
		}else if(this.yPos == 10 && this.xVelocity == 0) {
			this.yVelocity = 0;
			this.xVelocity = -2;
		}
		if(this.xPos <= 0) {
			this.xVelocity = 2;
		}else if(this.xPos >= 140) {
			this.xVelocity = -2;
		}
		this.xPos += this.xVelocity;
		this.yPos += this.yVelocity;
	}

	@Override
	public void remove() {
		handle.getPlayer().setNumBossDefeat(handle.getPlayer().getNumBossDefeat()+1);
		for(int i = 0; i<handle.getAllObject().size(); i++) {
			if(handle.getAllObject().get(i) instanceof BossLaser) {
				handle.remove(handle.getAllObject().get(i));
				i--;
			}
		}
		handle.remove(this);
		handle.getGameLogic().setScore(handle.getGameLogic().getScore() + this.score);
	}
	
	private void shoot() {
		if(this.isShootingFirstForm) {
			this.shootingFirstForm();
			return;
		}
		if(this.isShootingSecondForm) {
			return;
		}
		int shootChance = rnd.nextInt(100)+1;
		if(shootChance <= 3) {
			int shootForm = 0;
			shootForm = rnd.nextInt(4)+1;
			if(shootForm <=3) {
				this.isShootingFirstForm = true;
				this.shootingFirstForm();
			}else {
				this.isShootingSecondForm = true;
			}
		}
	}
	
	private void shootingFirstForm() {
		if(this.shootFrame%5 == 0) {
			handle.add(new Bullet(0,10,this.xPos+35,this.yPos+240,this.atk,handle,Type.ENEMYBULLET,0));
			handle.add(new Bullet(5,10,this.xPos+35,this.yPos+240,this.atk,handle,Type.ENEMYBULLET,-30));
			handle.add(new Bullet(10,10,this.xPos+35,this.yPos+240,this.atk,handle,Type.ENEMYBULLET,-60));
			handle.add(new Bullet(-5,10,this.xPos+35,this.yPos+240,this.atk,handle,Type.ENEMYBULLET,30));
			handle.add(new Bullet(-10,10,this.xPos+35,this.yPos+240,this.atk,handle,Type.ENEMYBULLET,60));
			handle.add(new Bullet(0,10,this.xPos+425,this.yPos+240,this.atk,handle,Type.ENEMYBULLET,0));
			handle.add(new Bullet(5,10,this.xPos+425,this.yPos+240,this.atk,handle,Type.ENEMYBULLET,-30));
			handle.add(new Bullet(10,10,this.xPos+425,this.yPos+240,this.atk,handle,Type.ENEMYBULLET,-60));
			handle.add(new Bullet(-5,10,this.xPos+425,this.yPos+240,this.atk,handle,Type.ENEMYBULLET,30));
			handle.add(new Bullet(-10,10,this.xPos+425,this.yPos+240,this.atk,handle,Type.ENEMYBULLET,60));
		}
		if(this.shootFrame == 25) {
			this.shootFrame = 0;
			this.isShootingFirstForm = false;
			return;
		}
		this.shootFrame++;
	}
	
	private void shootingSecondForm(GraphicsContext game) {
		game.setFill(Color.LIGHTGOLDENRODYELLOW);
		if(!Boss.handle.getLoad_Sound().getBossCharge().isPlaying()&&this.shootFrame<45)Boss.handle.getLoad_Sound().getBossCharge().play();
		if(this.shootFrame % 2 == 0 && this.shootFrame <45) {
			game.fillOval(xPos+135, yPos+205, 40, 40);
			game.fillOval(xPos+135, yPos+115, 40, 40);
			game.fillOval(xPos+285, yPos+205, 40, 40);
			game.fillOval(xPos+285, yPos+115, 40, 40);
			game.fillOval(xPos+100, yPos+160, 40, 40);
			game.fillOval(xPos+320, yPos+160, 40, 40);
		}else {
			game.fillOval(xPos+125, yPos+195, 60, 60);
			game.fillOval(xPos+125, yPos+105, 60, 60);
			game.fillOval(xPos+275, yPos+195, 60, 60);
			game.fillOval(xPos+275, yPos+105, 60, 60);
			game.fillOval(xPos+90, yPos+150, 60, 60);
			game.fillOval(xPos+310, yPos+150, 60, 60);
		}
		if(this.shootFrame == 45) {
			if(Boss.handle.getLoad_Sound().getBossCharge().isPlaying())Boss.handle.getLoad_Sound().getBossCharge().stop();
			Boss.handle.getLoad_Sound().getBossLaser().play();
			handle.getAllObject().add(2,new BossLaser(this,xPos+125,yPos+135,35,handle,Type.ENEMYBULLET));
			handle.getAllObject().add(2,new BossLaser(this,xPos+275,yPos+135,35,handle,Type.ENEMYBULLET));
			handle.getAllObject().add(2,new BossLaser(this,xPos+90,yPos+180,35,handle,Type.ENEMYBULLET));
			handle.getAllObject().add(2,new BossLaser(this,xPos+310,yPos+180,35,handle,Type.ENEMYBULLET));
		}
		if(this.shootFrame == 105) {
			this.isShootingSecondForm = false;
			this.shootFrame = 0;
			return;
		}
		++this.shootFrame;
	}
}
