package gameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

public interface GameObject {
	
	public void tick();
	public void render(GraphicsContext game);
	public void move();
	public void remove();
	public Type getType();
	public Shape getHitbox();
	
}
