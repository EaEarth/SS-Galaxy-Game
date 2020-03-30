package inputHandle;

import gameObject.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import game.GameHandle;

public class GameKeyReleased implements EventHandler<KeyEvent> {
	
	private static GameHandle handle;

	public GameKeyReleased(GameHandle handle) {
		GameKeyReleased.handle = handle;
	}
	
	@Override
	public void handle(KeyEvent key) {
		if(key.getCode() == KeyCode.UP) {
			for(int i = 0; i < handle.getAllObject().size(); i++) {
				if(handle.getAllObject().get(i) instanceof Player) {
					((Player)handle.getAllObject().get(i)).setGoNorth(false);
					break;
				}
			}
		}else  if(key.getCode() == KeyCode.LEFT) {
			for(int i = 0; i < handle.getAllObject().size(); i++) {
				if(handle.getAllObject().get(i) instanceof Player) {
					((Player)handle.getAllObject().get(i)).setGoWest(false);
					break;
				}
			}
		}else  if(key.getCode() == KeyCode.DOWN) {
			for(int i = 0; i < handle.getAllObject().size(); i++) {
				if(handle.getAllObject().get(i) instanceof Player) {
					((Player)handle.getAllObject().get(i)).setGoSouth(false);
					break;
				}
			}
		}else  if(key.getCode() == KeyCode.RIGHT) {
			for(int i = 0; i < handle.getAllObject().size(); i++) {
				if(handle.getAllObject().get(i) instanceof Player) {
					((Player)handle.getAllObject().get(i)).setGoEast(false);
					break;
				}
			}
		}
	}
}
