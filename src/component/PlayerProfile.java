package component;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import game.GameHandle;
import exception.*;

public class PlayerProfile extends VBox {
	
	
	private static final Group playerProfile = new Group();
	private static GameHandle handle;
	private static Text error,nameShow,enemyDefeatShow,bossDefeatShow,scoreShow;
	private static Rectangle window;
	private static TextField text;
	private static int highscore,numEnemyDefeat,numBossDefeat,atkLv,hpLv,supporterLv,coin;
	
	public PlayerProfile(GameHandle handle) {
		PlayerProfile.handle = handle;
		coin = 0;
		atkLv = 0;
		hpLv = 0;
		supporterLv = 0;
		highscore = 0;
		numBossDefeat = 0;
		numEnemyDefeat = 0;
		this.setPadding(new Insets(5));
		this.setPrefWidth(157);
		this.setPrefHeight(700);
		this.setSpacing(20);
		this.profileWindow();
	}
	
	private void profileWindow() {
		Text name = new Text();
		name.setFont(new Font("ERAS BOLD ITC",18));
		name.setText("Codename :");
		name.setFill(Color.YELLOW);
		
		nameShow = new Text();
		nameShow.setFill(Color.WHITE);
		nameShow.setFont(new Font("ERAS BOLD ITC",18));
		nameShow.setText("Mr.Empty");
		
		Text score = new Text();
		score.setFont(new Font("ERAS BOLD ITC",18));
		score.setText("Highscore :");
		score.setFill(Color.YELLOW);
		
		scoreShow = new Text();
		scoreShow.setFill(Color.WHITE);
		scoreShow.setFont(new Font("ERAS BOLD ITC",18));
		scoreShow.setText("   "+highscore);
		
		text = new TextField();
		text.setMaxWidth(140);
		text.setPrefWidth(140);
		
		error = new Text();
		error.setFill(Color.RED);
		error.setFont(new Font("ERAS ITC",10));
		
		Text enemyDefeat = new Text();
		enemyDefeat.setFill(Color.YELLOW);
		enemyDefeat.setFont(new Font("ERAS BOLD ITC",18));
		enemyDefeat.setText("Enemy defeat :");
		
		enemyDefeatShow = new Text();
		enemyDefeatShow.setFill(Color.WHITE);
		enemyDefeatShow.setFont(new Font("ERAS BOLD ITC",18));
		enemyDefeatShow.setText("   "+numEnemyDefeat);
		
		Text bossDefeat = new Text();
		bossDefeat.setFill(Color.YELLOW);
		bossDefeat.setFont(new Font("ERAS BOLD ITC",18));
		bossDefeat.setText("Boss Defeat :");
		
		bossDefeatShow = new Text();
		bossDefeatShow.setFill(Color.WHITE);
		bossDefeatShow.setFont(new Font("ERAS BOLD ITC",18));
		bossDefeatShow.setText("   "+numBossDefeat);
		
		Text confirm = new Text();
		confirm.setFill(Color.WHITE);
		confirm.setFont(new Font("ERAS BOLD ITC",15));
		confirm.setText("Change name");
		confirm.setX(200);
		
		confirm.setOnMouseClicked((event)->{
			this.setName();
		});
		
		confirm.setOnMouseEntered((event)->{
			confirm.setUnderline(true);
		});
		
		confirm.setOnMouseExited((event)->{
			confirm.setUnderline(false);
		});
		
		Text back = new Text();
		back.setFill(Color.WHITE);
		back.setFont(new Font("ERAS BOLD ITC",20));
		back.setText("X");
		
		back.setOnMouseClicked((event)->{
			handle.getLoad_Sound().getMenuSelect().play();
			this.closeWindow();
		});
		
		back.setOnMouseEntered((event)->{
			back.setFill(Color.RED);
		});
		
		back.setOnMouseExited((event)->{
			back.setFill(Color.WHITE);
		});
		
		window = new Rectangle(0,0,600,700);
		window.setFill(new ImagePattern(PlayerProfile.handle.getLoad_image().getProfile()));
		this.getChildren().addAll(back,name, nameShow, text, confirm, error, enemyDefeat, enemyDefeatShow, bossDefeat, bossDefeatShow,score,scoreShow);
		playerProfile.getChildren().addAll(window,this);
		this.closeWindow();
	}
	
	private void closeWindow() {
		playerProfile.setVisible(false);
	}
	
	public void show() {
		scoreShow.setText("   "+highscore);
		bossDefeatShow.setText("   "+numBossDefeat);
		enemyDefeatShow.setText("   "+numEnemyDefeat);
		playerProfile.setVisible(true);
	}
	
	private void setName() {
		try {
			error.setText(null);
			if(text.getText().compareTo("Cheater")==0) {
				this.setCoin(100000);
			}
			if(text.getText().length() == 0) {
				throw new EmptyNameException();
			}
			if(text.getText().length()>10) {
				throw new LongNameException();
			}
			char c;
			for(int i = 0; i<text.getText().toCharArray().length; i++) {
				c = text.getText().toCharArray()[i];
				if(!((c >= 'a' && c <='z')||(c >= 'A' && c<='Z')|| c=='_'|| c=='.' || c==' ' || (c >= '0' && c<='9'))) {
					throw new SpecialCharacterException(String.valueOf(text.getText().toCharArray()[i]));
				}
			}
			nameShow.setText(text.getText());
			text.clear();
			
		}catch(LongNameException e) {
			error.setText(e.getMessage());
			
		}catch(SpecialCharacterException e) {
			error.setText(e.getMessage());
	
		}catch(EmptyNameException e) {
			error.setText(e.getMessage());
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}

	public int getSupporterLv() {
		return supporterLv;
	}

	public void setSupporterLv(int supporterLv) {
		PlayerProfile.supporterLv = supporterLv;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		PlayerProfile.coin = coin;
	}

	public int getAtkLv() {
		return atkLv;
	}

	public void setAtkLv(int atkLv) {
		PlayerProfile.atkLv = atkLv;
	}

	public int getHpLv() {
		return hpLv;
	}

	public void setHpLv(int hpLv) {
		PlayerProfile.hpLv = hpLv;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		PlayerProfile.highscore = highscore;
	}

	public int getNumEnemyDefeat() {
		return numEnemyDefeat;
	}

	public void setNumEnemyDefeat(int numEnemyDefeat) {
		PlayerProfile.numEnemyDefeat = numEnemyDefeat;
	}

	public int getNumBossDefeat() {
		return numBossDefeat;
	}

	public void setNumBossDefeat(int numBossDefeat) {
		PlayerProfile.numBossDefeat = numBossDefeat;
	}

	public Group getPlayerProfile() {
		return playerProfile;
	}
	
}
