package game;

import gameObject.Player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class GameHud {

	private static GameHandle handle;
	private static int maxHp = 100;
	
	public GameHud(GameHandle handle) {
		GameHud.handle = handle;
	}
	
	public void render(GraphicsContext game) {
		game.setFill(Color.WHITE);
		int hp=0 , sp=0;
        for(int i = 0; i<handle.getAllObject().size(); i++) {
        	 if(handle.getAllObject().get(i) instanceof Player) {
        		 maxHp = ((Player)handle.getAllObject().get(i)).getMaxHp();
        		 hp = ((Player)handle.getAllObject().get(i)).getHp();
        		 sp = ((Player)handle.getAllObject().get(i)).getSp()/18;
        		 break;
        	 }
         }
         
          game.drawImage(GameHud.handle.getLoad_image().getHpbar(), 10, 620);
          game.drawImage(GameHud.handle.getLoad_image().getSpbar(), 10, 620);
          game.setFill(Color.RED);
          game.fillRect(65 + ((hp*100)/maxHp)*185/100, 628, (100-((hp*100)/maxHp))*185/100, 10);
          game.setFill(Color.DARKGRAY);
          game.fillRect(66+sp*185/100, 660,(100-sp)*185/100, 10);
          game.drawImage(GameHud.handle.getLoad_image().getHpAndSpBar(), 10, 620);
          game.setFill(Color.WHITE);
          game.setFont(Font.font("haettenschweiler",FontWeight.BOLD, FontPosture.REGULAR, 30));
          game.fillText("Score : "+handle.getGameLogic().getScore(), 10, 40);
          game.fillText("Highscore : "+handle.getPlayer().getHighscore(), 10, 80);
          game.setFont(Font.font("haettenschweiler",FontWeight.BOLD, FontPosture.REGULAR, 15));
          game.fillText(hp + " / "+maxHp, 192, 651);
          game.fillText(sp + " / 100 ", 192, 685);
          game.setFont(new Font("ERAS BOLD ITC",20));
          if(sp == 100) {
        	  game.fillText("ULT READY", 290, 685);
          }
          game.fillText("Pause [ESC]", 470, 30);
          if(handle.getGameLogic().isPause()) {
        	  game.setFont(new Font("ERAS BOLD ITC",100));
        	  game.fillText("PAUSE",130 , 340);
          }
	}
}
