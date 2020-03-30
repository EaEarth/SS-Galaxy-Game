package component;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import game.GameHandle;

public class Shop extends Group {
	private static GameHandle handle;
	private static Rectangle atk1,atk2,atk3,atk4,atk5,hp1,hp2,hp3,hp4,hp5,supporter1,supporter2,
					  buttonAtkUpgrade,buttonHpUpgrade,buttonSupporterUpgrade;
	private static Text cantUpgradeAtk,cantUpgradeHp,cantUpgradeSupporter,coin;
	
	public Shop(GameHandle handle) {
		Shop.handle = handle;
		buildShop();
	}
	
	private void buildShop() {
		Rectangle shopWindow = new Rectangle(0,0,600,700);
		shopWindow.setFill(new ImagePattern(handle.getLoad_image().getShop()));
		
		coin = new Text(215,155,"0");
		coin.setFill(Color.WHITE);
		coin.setFont(new Font("ERAS BOLD ITC",30));
		
		atk1 = new Rectangle(250,267,30,20);
		atk1.setFill(Color.GRAY);
		
		atk2 = new Rectangle(285,267,30,20);
		atk2.setFill(Color.GRAY);
		
		atk3 = new Rectangle(320,267,30,20);
		atk3.setFill(Color.GRAY);
		
		atk4 = new Rectangle(355,267,30,20);
		atk4.setFill(Color.GRAY);
		
		atk5 = new Rectangle(390,267,30,20);
		atk5.setFill(Color.GRAY);
		
		hp1 = new Rectangle(250,367,30,20);
		hp1.setFill(Color.GRAY);
		
		hp2 = new Rectangle(285,367,30,20);
		hp2.setFill(Color.GRAY);
		
		hp3 = new Rectangle(320,367,30,20);
		hp3.setFill(Color.GRAY);
		
		hp4 = new Rectangle(355,367,30,20);
		hp4.setFill(Color.GRAY);
		
		hp5 = new Rectangle(390,367,30,20);
		hp5.setFill(Color.GRAY);
		
		supporter1 = new Rectangle(275,461,70,20);
		supporter1.setFill(Color.GRAY);
		
		supporter2 = new Rectangle(350,461,70,20);
		supporter2.setFill(Color.GRAY);
		
		buttonHpUpgrade = new Rectangle(138,407,120,40);
		buttonHpUpgrade.setFill(new ImagePattern(handle.getLoad_image().getButton1()));
		
		buttonHpUpgrade.setOnMousePressed((event)->{
			handle.getLoad_Sound().getMenuSelect().play();
			cantUpgradeAtk.setText(null);
			cantUpgradeHp.setText(null);
			cantUpgradeSupporter.setText(null);
			buttonHpUpgrade.setFill(new ImagePattern(handle.getLoad_image().getButton2()));
			this.buy("hp");
		});
		
		buttonHpUpgrade.setOnMouseReleased((event)->{
			buttonHpUpgrade.setFill(new ImagePattern(handle.getLoad_image().getButton1()));
		});
		
		buttonAtkUpgrade = new Rectangle(138,307,120,40);
		buttonAtkUpgrade.setFill(new ImagePattern(handle.getLoad_image().getButton1()));
		
		buttonAtkUpgrade.setOnMousePressed((event)->{
			handle.getLoad_Sound().getMenuSelect().play();
			cantUpgradeAtk.setText(null);
			cantUpgradeHp.setText(null);
			cantUpgradeSupporter.setText(null);
			buttonAtkUpgrade.setFill(new ImagePattern(handle.getLoad_image().getButton2()));
			this.buy("atk");
		});
		
		buttonAtkUpgrade.setOnMouseReleased((event)->{
			buttonAtkUpgrade.setFill(new ImagePattern(handle.getLoad_image().getButton1()));
		});
		
		buttonSupporterUpgrade = new Rectangle(138,501,120,40);
		buttonSupporterUpgrade.setFill(new ImagePattern(handle.getLoad_image().getButton1()));
		
		buttonSupporterUpgrade.setOnMousePressed((event)->{
			handle.getLoad_Sound().getMenuSelect().play();
			cantUpgradeAtk.setText(null);
			cantUpgradeHp.setText(null);
			cantUpgradeSupporter.setText(null);
			buttonSupporterUpgrade.setFill(new ImagePattern(handle.getLoad_image().getButton2()));
			this.buy("support");
		});
		
		buttonSupporterUpgrade.setOnMouseReleased((event)->{
			buttonSupporterUpgrade.setFill(new ImagePattern(handle.getLoad_image().getButton1()));
		});
		
		Text atkPrice = new Text(280,322,"$1000");
		atkPrice.setFont(new Font("ERAS BOLD ITC",18));
		atkPrice.setFill(Color.WHITE);
		
		Text hpPrice = new Text(280,422,"$1000");
		hpPrice.setFont(new Font("ERAS BOLD ITC",18));
		hpPrice.setFill(Color.WHITE);
		
		Text supportPrice = new Text(280,516,"$3000");
		supportPrice.setFont(new Font("ERAS BOLD ITC",18));
		supportPrice.setFill(Color.WHITE);
		
		Text back = new Text(480,155,"X");
		back.setFont(new Font("ERAS BOLD ITC",50));
		back.setFill(Color.WHITE);
		
		back.setOnMouseEntered((event)->{
			back.setFill(Color.RED);
		});
		
		back.setOnMouseExited((event)->{
			back.setFill(Color.WHITE);
		});
		
		back.setOnMouseClicked((event)->{
			cantUpgradeAtk.setText(null);
			cantUpgradeHp.setText(null);
			cantUpgradeSupporter.setText(null);
			handle.getLoad_Sound().getMenuSelect().play();
			closeShop();
		});
		
		
		cantUpgradeAtk = new Text(280,345,null);
		cantUpgradeAtk.setFont(new Font("ERAS BOLD ITC",15));
		cantUpgradeAtk.setFill(Color.RED);
		
		cantUpgradeHp = new Text(280,445,null);
		cantUpgradeHp.setFont(new Font("ERAS BOLD ITC",15));
		cantUpgradeHp.setFill(Color.RED);
		
		cantUpgradeSupporter = new Text(280,539,null);
		cantUpgradeSupporter.setFont(new Font("ERAS BOLD ITC",15));
		cantUpgradeSupporter.setFill(Color.RED);
		
		this.getChildren().addAll(shopWindow,coin,atk1,atk2,atk3,atk4,atk5,hp1,hp2,hp3,hp4,hp5,supporter1,supporter2,back,
								  buttonHpUpgrade,buttonAtkUpgrade,buttonSupporterUpgrade,atkPrice,hpPrice,supportPrice,
								  cantUpgradeAtk,cantUpgradeHp,cantUpgradeSupporter);
		this.closeShop();
	}
	
	private void buy(String type) {
		if(type == "atk") {
			if(handle.getPlayer().getAtkLv() >= 5) {
				cantUpgradeAtk.setText("Your attack level is max.");
				return;
			}
			if(handle.getPlayer().getCoin() >= 1000) {
				handle.getPlayer().setCoin(handle.getPlayer().getCoin()-1000);
				coin.setText(String.valueOf(handle.getPlayer().getCoin()));
				if(handle.getPlayer().getAtkLv() == 0) {
					handle.getPlayer().setAtkLv(1);
					atk1.setFill(Color.GREEN);
				}else if(handle.getPlayer().getAtkLv() == 1) {
					handle.getPlayer().setAtkLv(2);
					atk2.setFill(Color.GREEN);
				}else if(handle.getPlayer().getAtkLv() == 2) {
					handle.getPlayer().setAtkLv(3);
					atk3.setFill(Color.GREEN);
				}else if(handle.getPlayer().getAtkLv() == 3) {
					handle.getPlayer().setAtkLv(4);
					atk4.setFill(Color.GREEN);
				}else{
					handle.getPlayer().setAtkLv(5);
					atk5.setFill(Color.GREEN);
				}
			}else {
				cantUpgradeAtk.setText("Not enough coin");
			}
		}else if(type == "hp") {
			if(handle.getPlayer().getHpLv() >= 5) {
				cantUpgradeHp.setText("Your hp level is max.");
				return;
			}
			if(handle.getPlayer().getCoin() >= 1000) {
				handle.getPlayer().setCoin(handle.getPlayer().getCoin()-1000);
				coin.setText(String.valueOf(handle.getPlayer().getCoin()));
				if(handle.getPlayer().getHpLv() == 0) {
					handle.getPlayer().setHpLv(1);
					hp1.setFill(Color.GREEN);
				}else if(handle.getPlayer().getHpLv() == 1) {
					handle.getPlayer().setHpLv(2);
					hp2.setFill(Color.GREEN);
				}else if(handle.getPlayer().getHpLv() == 2) {
					handle.getPlayer().setHpLv(3);
					hp3.setFill(Color.GREEN);
				}else if(handle.getPlayer().getHpLv() == 3) {
					handle.getPlayer().setHpLv(4);
					hp4.setFill(Color.GREEN);
				}else{
					handle.getPlayer().setHpLv(5);
					hp5.setFill(Color.GREEN);
				}
			}else {
				cantUpgradeHp.setText("Not enough coin");
			}
		}else {
			if(handle.getPlayer().getSupporterLv() >= 2) {
				cantUpgradeSupporter.setText("Your supporter level is max.");
				return;
			}
			if(handle.getPlayer().getCoin() >= 3000) {
				handle.getPlayer().setCoin(handle.getPlayer().getCoin()-3000);
				coin.setText(String.valueOf(handle.getPlayer().getCoin()));
				if(handle.getPlayer().getSupporterLv() == 0) {
					handle.getPlayer().setSupporterLv(1);
					supporter1.setFill(Color.GREEN);
				}else {
					handle.getPlayer().setSupporterLv(2);
					supporter2.setFill(Color.GREEN);
				}
			}else {
				cantUpgradeSupporter.setText("Not enough coin");
			}
		}
	}
	
	public void openShop() {
		coin.setText(String.valueOf(handle.getPlayer().getCoin()));
		this.setVisible(true);
	}
	
	private void closeShop() {
		this.setVisible(false);
	}

	public Group getShop() {
		return this;
	}
	
}
