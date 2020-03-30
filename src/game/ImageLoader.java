package game;

import javafx.scene.image.Image;

public class ImageLoader {
	private static Image boss,shipLv1,shipLv1GetHit,shipLv2,shipLv2GetHit,shipLv3,shipLv3GetHit,
				  normalEnemy1,normalEnemy2,normalEnemy3,shootingAlien1,shootingAlien2,shootingAlien3,
				  playerBullet,playerBullet30,playerBullet60,playerBulletMinus30,playerBulletMinus60,
				  enemyBullet,enemyBullet30,enemyBullet60,enemyBulletMinus30,enemyBulletMinus60,
				  meteor,hpItem,upgradeItem,hpAndSpBar,hpbar,spbar,mainMenuBg,gameBg,howToPlayWindow,
				  title,profile,shop,supporter,shopIcon,button1,button2,coin;
	private static String image_path;
	
	public ImageLoader() {
		image_path = ClassLoader.getSystemResource("obstacle/bossAlien.png").toString();
		boss = new Image(image_path,460,280,false,false);
		image_path = ClassLoader.getSystemResource("ship/shipLv1.png").toString();
		shipLv1 = new Image(image_path,80,120,false,false);
		image_path = ClassLoader.getSystemResource("ship/shipLv1GetHit.png").toString();
		shipLv1GetHit = new Image(image_path,80,120,false,false);
		image_path = ClassLoader.getSystemResource("ship/shipLv2.png").toString();
		shipLv2 = new Image(image_path,80,120,false,false);
		image_path = ClassLoader.getSystemResource("ship/shipLv2GetHit.png").toString();
		shipLv2GetHit = new Image(image_path,80,120,false,false);
		image_path = ClassLoader.getSystemResource("ship/shipLv3.png").toString();
		shipLv3 = new Image(image_path,88,132,false,false);
		image_path = ClassLoader.getSystemResource("ship/shipLv3GetHit.png").toString();
		shipLv3GetHit = new Image(image_path,88,132,false,false);
		image_path = ClassLoader.getSystemResource("bullet/playerBullet.png").toString();
		playerBullet = new Image(image_path,5,20,false,false);
		image_path = ClassLoader.getSystemResource("bullet/playerBullet30.png").toString();
		playerBullet30 = new Image(image_path,20,20,false,false);
		image_path = ClassLoader.getSystemResource("bullet/playerBullet60.png").toString();
		playerBullet60 = new Image(image_path,20,20,false,false);
		image_path = ClassLoader.getSystemResource("bullet/playerBullet-30.png").toString();
		playerBulletMinus30 = new Image(image_path,20,20,false,false);
		image_path = ClassLoader.getSystemResource("bullet/playerBullet-60.png").toString();
		playerBulletMinus60 = new Image(image_path,20,20,false,false);
		image_path = ClassLoader.getSystemResource("bullet/enermyBullet.png").toString();
		enemyBullet = new Image(image_path,10,20,false,false);
		image_path = ClassLoader.getSystemResource("bullet/enermyBullet30.png").toString();
		enemyBullet30 = new Image(image_path,20,20,false,false);
		image_path = ClassLoader.getSystemResource("bullet/enermyBullet60.png").toString();
		enemyBullet60 = new Image(image_path,20,20,false,false);
		image_path = ClassLoader.getSystemResource("bullet/enermyBullet-30.png").toString();
		enemyBulletMinus30 = new Image(image_path,20,20,false,false);
		image_path = ClassLoader.getSystemResource("bullet/enermyBullet-60.png").toString();
		enemyBulletMinus60 = new Image(image_path,20,20,false,false);
		image_path = ClassLoader.getSystemResource("obstacle/normalAlien.png").toString();
		normalEnemy1 = new Image(image_path,40,50,false,false);
		image_path = ClassLoader.getSystemResource("obstacle/normalAlien2.png").toString();
		normalEnemy2 = new Image(image_path,40,50,false,false);
		image_path = ClassLoader.getSystemResource("obstacle/normalAlien3.png").toString();
		normalEnemy3 = new Image(image_path,40,50,false,false);
		image_path = ClassLoader.getSystemResource("obstacle/shootingAlien1.png").toString();
		shootingAlien1 = new Image(image_path,40,50,false,false);
		image_path = ClassLoader.getSystemResource("obstacle/shootingAlien2.png").toString();
		shootingAlien2 = new Image(image_path,40,50,false,false);
		image_path = ClassLoader.getSystemResource("obstacle/shootingAlien3.png").toString();
		shootingAlien3 = new Image(image_path,40,50,false,false);
		image_path = ClassLoader.getSystemResource("obstacle/meteor.png").toString();
		meteor = new Image(image_path,100,100,false,false);
		image_path = ClassLoader.getSystemResource("item/upgradeItem.png").toString();
		upgradeItem = new Image(image_path,25,25,false,false);
		image_path = ClassLoader.getSystemResource("item/hpItem.png").toString();
		hpItem = new Image(image_path,26,26,false,false);
		image_path = ClassLoader.getSystemResource("hud/healthbarAndSpbar.png").toString();
		hpAndSpBar = new Image(image_path,261,70,false,false);
		image_path = ClassLoader.getSystemResource("hud/Hpbar.png").toString();
		hpbar = new Image(image_path,261,70,false,false);
		image_path = ClassLoader.getSystemResource("hud/Spbar.png").toString();
		spbar = new Image(image_path,261,70,false,false);
		image_path = ClassLoader.getSystemResource("background/mainmenu_background.png").toString();
		mainMenuBg= new Image(image_path,1320,720,false,false);
		image_path = ClassLoader.getSystemResource("background/game_background.png").toString();
		gameBg= new Image(image_path,700,2160,false,false);
		image_path = ClassLoader.getSystemResource("window/howtoplay_window.png").toString();
		howToPlayWindow= new Image(image_path,600,700,false,false);
		image_path = ClassLoader.getSystemResource("window/title.png").toString();
		title= new Image(image_path,600,700,false,false);
		image_path = ClassLoader.getSystemResource("window/profile.png").toString();
		profile= new Image(image_path,600,700,false,false);
		image_path = ClassLoader.getSystemResource("window/shop.png").toString();
		shop= new Image(image_path,600,700,false,false);
		image_path = ClassLoader.getSystemResource("ship/supporter.png").toString();
		supporter= new Image(image_path,50,50,false,false);
		image_path = ClassLoader.getSystemResource("window/shopIcon.png").toString();
		shopIcon= new Image(image_path,92,82,false,false);
		image_path = ClassLoader.getSystemResource("window/button1.png").toString();
		button1= new Image(image_path,120,40,false,false);
		image_path = ClassLoader.getSystemResource("window/button2.png").toString();
		button2= new Image(image_path,120,40,false,false);
		image_path = ClassLoader.getSystemResource("item/coin.png").toString();
		coin= new Image(image_path,30,30,false,false);
	}
	
	public Image getCoin() {
		return coin;
	}

	public Image getButton1() {
		return button1;
	}

	public Image getButton2() {
		return button2;
	}

	public Image getShopIcon() {
		return shopIcon;
	}

	public Image getSupporter() {
		return supporter;
	}

	public Image getShop() {
		return shop;
	}

	public Image getProfile() {
		return profile;
	}

	public Image getMainMenuBg() {
		return mainMenuBg;
	}

	public Image getTitle() {
		return title;
	}

	public Image getHowToPlayWindow() {
		return howToPlayWindow;
	}

	public Image getGameBg() {
		return gameBg;
	}

	public Image getHpAndSpBar() {
		return hpAndSpBar;
	}

	public Image getHpbar() {
		return hpbar;
	}

	public Image getSpbar() {
		return spbar;
	}

	public String getImage_path() {
		return image_path;
	}

	public Image getBoss() {
		return boss;
	}

	public Image getShipLv1() {
		return shipLv1;
	}

	public Image getShipLv1GetHit() {
		return shipLv1GetHit;
	}

	public Image getShipLv2() {
		return shipLv2;
	}

	public Image getShipLv2GetHit() {
		return shipLv2GetHit;
	}

	public Image getShipLv3() {
		return shipLv3;
	}

	public Image getShipLv3GetHit() {
		return shipLv3GetHit;
	}

	public Image getNormalEnemy1() {
		return normalEnemy1;
	}

	public Image getNormalEnemy2() {
		return normalEnemy2;
	}

	public Image getNormalEnemy3() {
		return normalEnemy3;
	}

	public Image getShootingAlien1() {
		return shootingAlien1;
	}

	public Image getShootingAlien2() {
		return shootingAlien2;
	}

	public Image getShootingAlien3() {
		return shootingAlien3;
	}

	public Image getPlayerBullet() {
		return playerBullet;
	}

	public Image getPlayerBullet30() {
		return playerBullet30;
	}

	public Image getPlayerBullet60() {
		return playerBullet60;
	}

	public Image getPlayerBulletMinus30() {
		return playerBulletMinus30;
	}

	public Image getPlayerBulletMinus60() {
		return playerBulletMinus60;
	}

	public Image getEnemyBullet() {
		return enemyBullet;
	}

	public Image getEnemyBullet30() {
		return enemyBullet30;
	}

	public Image getEnemyBullet60() {
		return enemyBullet60;
	}

	public Image getEnemyBulletMinus30() {
		return enemyBulletMinus30;
	}

	public Image getEnemyBulletMinus60() {
		return enemyBulletMinus60;
	}

	public Image getMeteor() {
		return meteor;
	}

	public Image getHpItem() {
		return hpItem;
	}

	public Image getUpgradeItem() {
		return upgradeItem;
	}
}
