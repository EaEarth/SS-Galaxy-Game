package gameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;

import game.GameHandle;

public class Meteor extends Enemy {

	public Meteor(int Xpos, int Ypos,GameHandle handle) {
		super(100, 0, 10, Xpos, Ypos,handle,15,15,30);
		if(this.xPos < 0) {
			this.xPos = 0;
		}else if(this.xPos > 500) {
			this.xPos = 500;
		}
		if(this.yPos > -100) {
			this.yPos = -100;
		}
		this.hitbox = new Circle(45);
		this.image = Meteor.handle.getLoad_image().getMeteor();
		this.score = 200;
	}
	
	@Override
	public void tick() {
		move();
		((Circle)hitbox).setCenterX(xPos+54);
		((Circle)hitbox).setCenterY(yPos+53);
		collision();
		if(this.yPos >= 800 || this.yPos <= -250 || this.xPos >= 800 || this.xPos <= -300) {
			handle.remove(this);
			handle.getGameLogic().setEnemy(handle.getGameLogic().getEnemy() - 1);
		}
	}

	@Override
	public void render(GraphicsContext game) {
		game.drawImage(image, this.xPos, this.yPos);
	}
}
