package gameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;
import game.GameHandle;

public class Supporter implements GameObject{

	private static final Type type = Type.SUPPORT;
	private int xPos,yPos,countFrame;
	private static boolean goNorth,goSouth,goWest,goEast;
	private static GameHandle handle;
	
	public Supporter(int xPos, int yPos, GameHandle handle) {
		this.xPos = xPos;
		this.yPos = yPos;
		Supporter.handle = handle;
		this.countFrame = 0;
	}
	@Override
	public void tick() {
		for(int i = 0; i<handle.getAllObject().size(); i++) {
			if(handle.getAllObject().get(i) instanceof Player) {
				goNorth = ((Player)handle.getAllObject().get(i)).isGoNorth();
				goEast = ((Player)handle.getAllObject().get(i)).isGoEast();
				goSouth = ((Player)handle.getAllObject().get(i)).isGoSouth();
				goWest = ((Player)handle.getAllObject().get(i)).isGoWest();
				break;
			}
		}
		move();
		shoot();
	}

	@Override
	public void render(GraphicsContext game) {
		game.drawImage(Supporter.handle.getLoad_image().getSupporter(),xPos,yPos);
	}

	@Override
	public void move() {
		if(goNorth) {
			this.yPos -= 10;
		}
		if(goSouth) {
			this.yPos += 10;
		}
		if(goEast) {
			this.xPos += 10;
		}
		if(goWest) {
			this.xPos -= 10;
		}
	}
	
	private void shoot() {
		if(countFrame <= 0 ) {
			for(int i = 0; i<handle.getAllObject().size(); i++) {
				if(handle.getAllObject().get(i) instanceof Player) {
					Player player = ((Player)handle.getAllObject().get(i));
					if(player.getLv() == 1) {
						handle.add(new Bullet(0,-20,xPos+23,yPos,player.getAtk()/2,handle,Type.PLAYERBULLET,0));
						countFrame = 5;
					}else {
						handle.add(new Bullet(0,-20,xPos+23,yPos,player.getAtk()/2,handle,Type.PLAYERBULLET,0));
						countFrame = 3;
					}
					break;
				}
			}
		}
		countFrame--;
	}

	@Override
	public void remove() {
		handle.remove(this);
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Shape getHitbox() {
		return null;
	}

	
}
