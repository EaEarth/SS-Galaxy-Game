package gameObject;

import javafx.scene.shape.Shape;

import game.GameHandle;

public abstract class Item implements GameObject{
	
	protected Type type;
	protected int xPos,yPos,yVelocity,delay = 1;
	protected Shape hitbox;
	protected GameHandle handle;
	
	public Item(int xPos, int yPos, GameHandle handle) {
		this.type = Type.ITEM;
		this.xPos = xPos;
		this.yPos = yPos;
		this.yVelocity = -5;
		this.handle = handle;
	}
	
	@Override
	public void tick() {
		move();
		if(this.yVelocity < 10) {
			if(delay == 2) {
				this.yVelocity++;
				delay = 1;
			}else {
				++delay;
			}
		}
		if(this.yPos >= 800 || this.yPos <= -250 || this.xPos >= 800 || this.xPos <= -300) {
			handle.remove(this);
		}
	}
	
	@Override 
	public void move(){
		this.yPos += this.yVelocity;
	}
	
	@Override
	public Shape getHitbox() {
		return this.hitbox;
	}
	
	@Override
	public Type getType() {
		return this.type;
	}
}
