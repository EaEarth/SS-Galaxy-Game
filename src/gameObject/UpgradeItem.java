package gameObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

import game.GameHandle;

public class UpgradeItem extends Item {

	public UpgradeItem(int xPos, int yPos, GameHandle handle) {
		super(xPos, yPos, handle);
		this.hitbox = new Rectangle(this.xPos,this.yPos,25,25);
	}

	@Override
	public void render(GraphicsContext game) {
		((Rectangle)this.hitbox).setX(this.xPos);
		((Rectangle)this.hitbox).setY(this.yPos);
		game.drawImage(this.handle.getLoad_image().getUpgradeItem(), xPos, yPos );

	}

	@Override
	public void remove() {
		this.handle.remove(this);
	}
}
