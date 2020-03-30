package gameObject;

import game.GameHandle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;

public class HpItem extends Item {
	
	private static final int hpGain = 30;

	public HpItem(int xPos, int yPos, GameHandle handle) {
		super(xPos, yPos, handle);
		this.hitbox = new Circle(this.xPos+13,this.yPos+13,13);
	}

	@Override
	public void render(GraphicsContext game) {
		((Circle)this.hitbox).setCenterX(this.xPos+13);
		((Circle)this.hitbox).setCenterY(this.yPos+13);
		game.drawImage(this.handle.getLoad_image().getHpItem(),this.xPos, this.yPos);

	}

	@Override
	public void remove() {
		this.handle.remove(this);

	}
	
	public int getHpGain() {
		return hpGain;
	}
	
}
