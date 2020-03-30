package game;

import javafx.scene.media.AudioClip;

public class SoundLoader {
	
	private static AudioClip mainMenuMusic,gameMusic,menuSelect,explosion,bossCharge,bossLaser,meteorExplosion,alienDestroyed,bossExplosion,itemCollected;
	
	public SoundLoader() {
		try {
			menuSelect = new AudioClip(getClass().getClassLoader().getResource("menu_Select_00.mp3").toString());
			mainMenuMusic = new AudioClip(getClass().getClassLoader().getResource("menuBgMusic.mp3").toString());
			gameMusic = new AudioClip(getClass().getClassLoader().getResource("battleThemeA.mp3").toString());
			gameMusic.setVolume(0.5);
			explosion = new AudioClip(getClass().getClassLoader().getResource("explosion1.wav").toString());
			bossCharge = new AudioClip(getClass().getClassLoader().getResource("bossCharge.mp3").toString());
			bossLaser = new AudioClip(getClass().getClassLoader().getResource("bossLaser.wav").toString());
			meteorExplosion = new AudioClip(getClass().getClassLoader().getResource("meteorExplosion.wav").toString());
			alienDestroyed = new AudioClip(getClass().getClassLoader().getResource("alienDestroyed.wav").toString());
			bossExplosion = new AudioClip(getClass().getClassLoader().getResource("bossExplosion.wav").toString());
			itemCollected = new AudioClip(getClass().getClassLoader().getResource("itemCollected.wav").toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public AudioClip getItemCollected() {
		return itemCollected;
	}

	public AudioClip getBossExplosion() {
		return bossExplosion;
	}

	public AudioClip getAlienDestroyed() {
		return alienDestroyed;
	}

	public AudioClip getExplosion() {
		return explosion;
	}

	public AudioClip getMeteorExplosion() {
		return meteorExplosion;
	}

	public AudioClip getMainMenuMusic() {
		return mainMenuMusic;
	}

	public AudioClip getGameMusic() {
		return gameMusic;
	}

	public AudioClip getMenuSelect() {
		return menuSelect;
	}

	public AudioClip getBossCharge() {
		return bossCharge;
	}

	public AudioClip getBossLaser() {
		return bossLaser;
	}
	
}
