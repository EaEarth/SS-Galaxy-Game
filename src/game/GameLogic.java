package game;

import java.util.Random;
import java.util.Vector;

import gameObject.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameLogic {
	private static GameHandle handle;
	private static int score = 0, enemy = 0,delay = 0, bossDefeat = 0,playerDestroyedFrame = 0,coinGain = 0;
	private static boolean hasBoss = false, isPause = false;
	private static Random rand = new Random();
	private static Vector<Integer> gameOver = new Vector<Integer>();
	
	public GameLogic (GameHandle handle) {
		GameLogic.handle = handle;
	}
	
	public void generatePlayer() {
		coinGain = 0;
		handle.add(new Player(100+10*handle.getPlayer().getHpLv(),300,500,10+handle.getPlayer().getAtkLv()*2,handle));
		if(handle.getPlayer().getSupporterLv()==1) {
			handle.add(new Supporter(200,550,handle));
		}else if(handle.getPlayer().getSupporterLv()==2) {
			handle.add(new Supporter(220,550,handle));
			handle.add(new Supporter(410,550,handle));
		}
	}
	
	public void tick() {
		if(score > handle.getPlayer().getHighscore()) handle.getPlayer().setHighscore(score);
		if(delay == 60) this.generateEnemy();
		if(delay < 60) ++delay;
	}
	
	public void render(GraphicsContext game) {
		if(playerDestroyedFrame > 0 ){
			game.setFill(Color.WHITE);
			game.fillRect(gameOver.get(0), gameOver.get(1), gameOver.get(2),gameOver.get(3));
			if(playerDestroyedFrame <= 50) {
				gameOver.set(1, gameOver.get(1)-2);
				gameOver.set(3, gameOver.get(3)+4);
			}else {
				game.setFont(new Font("ERAS BOLD ITC",50));
				game.setFill(Color.BLACK);
				if(playerDestroyedFrame >= 51 && playerDestroyedFrame <=60) {
					game.fillText("G", 280, 305);
				}else if(playerDestroyedFrame > 60 && playerDestroyedFrame <= 70) {
					game.fillText("GA", 260, 305);
				}else if(playerDestroyedFrame > 70 && playerDestroyedFrame <= 80) {
					game.fillText("GAM", 240, 305);
				}else if(playerDestroyedFrame > 80 && playerDestroyedFrame <= 90) {
					game.fillText("GAME", 220, 305);
				}else if(playerDestroyedFrame > 90 && playerDestroyedFrame <= 100) {
					game.fillText("GAME O", 200, 305);
				}else if(playerDestroyedFrame > 100 && playerDestroyedFrame <= 110) {
					game.fillText("GAME OV", 180, 305);
				}else if(playerDestroyedFrame > 110 && playerDestroyedFrame <= 120) {
					game.fillText("GAME OVE", 160, 305);
				}else if(playerDestroyedFrame > 120 ) {
					if(playerDestroyedFrame == 121) {
						this.coinGain();
					}
					game.fillText("GAME OVER", 140, 305);
					if(playerDestroyedFrame > 130) {
						game.setFont(new Font("ERAS ITC BOLD",20));
						game.fillText("Score : "+handle.getGameLogic().getScore(), 242, 335);
						game.fillText("highscore : "+handle.getPlayer().getHighscore(), 225, 365);
						game.drawImage(handle.getLoad_image().getCoin(), 260, 375);
						game.fillText("+"+coinGain, 300, 400);
						game.setFont(new Font("ERAS ITC BOLD",30));
						game.setFill(Color.GRAY);
						game.fillText("[Press any key to continue]",120, 435);
					}
				}
			}
			playerDestroyedFrame++;
		}
	}
	
	private void coinGain() {
		coinGain = score/20;
		handle.getPlayer().setCoin(handle.getPlayer().getCoin() + coinGain);
	}
	
	private void generateEnemy() {
		int enemyRate = score/1000;
		int enemyTypeRate = rand.nextInt(5)+1;
		int rnd = rand.nextInt(200);
		int xPos = rand.nextInt(620)-20;
		int yPos = -100;
		if(enemyRate<3) {
			enemyRate = 3;
		}else if(enemyRate > 20) {
			enemyRate = 20;
		}
		if(enemy <= 2) {
			enemyRate = 100;
		}
		if(hasBoss) {
			enemyRate = 1;
		}
		if(enemy >= 15) {
			enemyRate = 0;
		}
		if(enemyRate>=rnd) {
			int velY = 10;
			int velX = 0;
			if(rnd < 33) {
				velX = -2;
			}else if(rnd > 66) {
				velX = 2;
			}
			if(xPos < 0) {
				velX = 5;
			}else if(xPos > 550) {
				velX = -5;
			}
			if(enemyTypeRate <= 2 ) {
				handle.add(new NormalEnemy(velX, velY, xPos, yPos, handle));
				enemy++;
					
			}else if(enemyTypeRate > 4) {
				handle.add(new Meteor(xPos, yPos, handle));
				enemy++;
			}else {
				handle.add(new ShootingEnemy(velX,velY,xPos,yPos, handle));
				enemy++;
			}
		}
		if(score%5000 <= 500 && score >500 &&!hasBoss) {
			handle.getAllObject().add(1, new Boss(4000+bossDefeat*1000, handle));
			hasBoss = true;
		}
	}
	
	public void playerDestroyed() {
		if(gameOver.size()!=0) {
			gameOver.clear();
		}
		gameOver.add(0);
		gameOver.add(350);
		gameOver.add(600);
		gameOver.add(0);
		playerDestroyedFrame = 1;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		GameLogic.score = score;
	}
	
	public void setEnemy(int enemy) {
		GameLogic.enemy = enemy;
	}
	
	public int getEnemy() {
		return enemy;
	}
	
	public void setBossDefeat(int bossDefeat) {
		GameLogic.bossDefeat = bossDefeat;
	}
	
	public int getBossDefeat() {
		return bossDefeat;
	}
	
	public boolean getHasBoss() {
		return hasBoss;
	}
	
	public void setHasBoss(boolean hasBoss) {
		GameLogic.hasBoss = hasBoss;
	}
	
	public int getPlayerDestroyedFrame() {
		return playerDestroyedFrame;
	}
	
	public void setPlayerDestroyedFrame(int frame) {
		GameLogic.playerDestroyedFrame = frame;
	}
	
	public void setDelay(int delay) {
		GameLogic.delay = delay;
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean isPause) {
		GameLogic.isPause = isPause;
	}

}
