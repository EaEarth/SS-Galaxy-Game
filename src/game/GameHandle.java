package game;

import java.util.LinkedList;

import gameObject.GameObject;
import gameObject.Boss;
import gameObject.Player;
import component.Shop;
import component.PlayerProfile;

import javafx.scene.canvas.GraphicsContext;

public class GameHandle {

	private static final LinkedList<GameObject> allObject = new LinkedList<GameObject>();
	private static final ImageLoader load_Image = new ImageLoader();
	private static final SoundLoader load_Sound = new SoundLoader();
	private final GameHud hud = new GameHud(this);
	private final GameLogic gameLogic = new GameLogic(this);
	private final PlayerProfile player = new PlayerProfile(this);
	private final Shop shop = new Shop(this);
	private static SceneSelect sceneManage;
	
	public GameHandle(SceneSelect scene) {
		sceneManage = scene;
	}
	
	public void tick() {
		if(!this.gameLogic.isPause()) {
			gameLogic.tick();
			for(int i = 0 ; i < allObject.size(); i++) {
				allObject.get(i).tick();
			}
		}
	}
	
	public void render(GraphicsContext game) {
		for(int i = 0 ; i<allObject.size() ; i++) {
			allObject.get(i).render(game);
		}
		hud.render(game);
		this.gameLogic.render(game);
		
	}
	
	public void add(GameObject gameObject) {
		allObject.add(gameObject);
	}
	
	public void remove(GameObject gameObject) {
		if(gameObject instanceof Boss) {
			this.gameLogic.setBossDefeat(this.gameLogic.getBossDefeat()+1);
			this.gameLogic.setHasBoss(false);
		}else if(gameObject instanceof Player) {
			this.gameLogic.playerDestroyed();
		}
		allObject.remove(gameObject);
	}
	
	public Shop getShop() {
		return shop;
	}

	public LinkedList<GameObject> getAllObject() {
		return allObject;
	}
	
	public GameLogic getGameLogic() {
		return this.gameLogic;
	}

	public ImageLoader getLoad_image() {
		return load_Image;
	}

	public GameHud getHud() {
		return hud;
	}
	
	public SceneSelect getSceneManage() {
		return sceneManage;
	}
	
	public SoundLoader getLoad_Sound() {
		return load_Sound;
	}

	public PlayerProfile getPlayer() {
		return player;
	}
	
}

