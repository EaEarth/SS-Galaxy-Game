package inputHandle;

import gameObject.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import game.GameHandle;

public class GameKeyPressed implements EventHandler<KeyEvent> {
	
	private static GameHandle handle;

	public GameKeyPressed(GameHandle handle) {
		GameKeyPressed.handle = handle;
	}
	
	@Override
	public void handle(KeyEvent key) {
		if(handle.getGameLogic().getPlayerDestroyedFrame() == 0) {
			if(key.getCode() == KeyCode.UP) {
				for(int i = 0; i < handle.getAllObject().size(); i++) {
					if(handle.getAllObject().get(i) instanceof Player) {
						((Player)handle.getAllObject().get(i)).setGoNorth(true);
						break;
					}
				}
			}else  if(key.getCode() == KeyCode.LEFT) {
				for(int i = 0; i < handle.getAllObject().size(); i++) {
					if(handle.getAllObject().get(i) instanceof Player) {
						((Player)handle.getAllObject().get(i)).setGoWest(true);
						break;
					}
				}
			}else  if(key.getCode() == KeyCode.DOWN) {
				for(int i = 0; i < handle.getAllObject().size(); i++) {
					if(handle.getAllObject().get(i) instanceof Player) {
						((Player)handle.getAllObject().get(i)).setGoSouth(true);
						break;
					}
				}
			}else  if(key.getCode() == KeyCode.RIGHT) {
				for(int i = 0; i < handle.getAllObject().size(); i++) {
					if(handle.getAllObject().get(i) instanceof Player) {
						((Player)handle.getAllObject().get(i)).setGoEast(true);
						break;
					}
				}
			}else if(key.getCode() == KeyCode.SPACE) {
				for(int i = 0; i < handle.getAllObject().size(); i++) {
					if(handle.getAllObject().get(i) instanceof Player) {
						((Player)handle.getAllObject().get(i)).ultimate();
						break;
					}
				}
			}else if(key.getCode() == KeyCode.ESCAPE) {
				handle.getLoad_Sound().getMenuSelect().play();
				handle.getGameLogic().setPause(!handle.getGameLogic().isPause());
			}
		}else if(handle.getGameLogic().getPlayerDestroyedFrame() > 130){
			handle.getGameLogic().setPlayerDestroyedFrame(0);
			handle.getSceneManage().change();
		}
	}
}
