package game;

import java.util.Vector;

import inputHandle.GameKeyPressed;
import inputHandle.GameKeyReleased;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneSelect {
	
	private static GameHandle handle;
	private static Scene mainMenu,game;
	private static Stage stage;
	public static final Canvas canvas = new Canvas(600,700);
	public static final GraphicsContext gc = canvas.getGraphicsContext2D();
	private static Vector<Integer> gameBG1_coordinate,gameBG2_coordinate,menuBG1_coordinate,menuBG2_coordinate;
	private static Rectangle menuBg1,menuBg2,gameBg1,gameBg2;
	private static int sceneNumber;
	public final static int gameScene=2;
	public final static int menuScene=1;
	
	public SceneSelect(Stage stage) {
		handle = new GameHandle(this);
		SceneSelect.stage = stage;
		gameScene();
		mainMenuScene();
		sceneNumber = menuScene;
		stage.setScene(mainMenu);
	}

	private void mainMenuScene() {
		
		menuBG1_coordinate = new Vector<>();
		menuBG1_coordinate.add(-20);
		menuBG1_coordinate.add(0);
		menuBG2_coordinate = new Vector<>();
		menuBG2_coordinate.add(1290);
		menuBG2_coordinate.add(0);
		
		menuBg1 = new Rectangle(menuBG1_coordinate.get(0),menuBG1_coordinate.get(1),1320,720);
		menuBg2 = new Rectangle(menuBG2_coordinate.get(0),menuBG2_coordinate.get(1),1320,720);
		menuBg1.setFill(new ImagePattern(handle.getLoad_image().getMainMenuBg()));
		menuBg2.setFill(new ImagePattern(handle.getLoad_image().getMainMenuBg()));
		
		Rectangle title = new Rectangle(0,0,600,700);
		title.setFill( new ImagePattern(handle.getLoad_image().getTitle()));
		
		
		Rectangle howToPlayScreen = new Rectangle(0,0,600,700);
		howToPlayScreen.setFill(new ImagePattern(handle.getLoad_image().getHowToPlayWindow()));
		howToPlayScreen.setVisible(false);
		
		Text start = new Text();
		start.setText("START");
		start.setFont(new Font("ERAS BOLD ITC",30));
		start.setX(250);
		start.setY(400);
		start.setFill(Color.WHITE);
		
		start.setOnMouseEntered((event)->{
			start.setFont(new Font("ERAS BOLD ITC",50));
			start.setX(215);
			start.setFill(Color.AQUAMARINE);
		});
		
		start.setOnMouseExited((event)->{
			start.setFont(new Font("ERAS BOLD ITC",30));
			start.setX(250);
			start.setFill(Color.WHITE);
		});
		
		start.setOnMouseClicked((event)->{
			handle.getLoad_Sound().getMenuSelect().play();
			change();
		});
		
		Text howToPlay = new Text(185,500,"HOW TO PLAY");
		howToPlay.setFont(new Font("ERAS BOLD ITC",30));
		howToPlay.setFill(Color.WHITE);
		
		Text back = new Text(480,170,"X");
		back.setFont(new Font("ERAS BOLD ITC",50));
		back.setFill(Color.RED);
		back.setVisible(false);
		
		back.setOnMouseEntered((event)->{
			back.setFont(new Font("ERAS BOLD ITC",70));
		});
		
		back.setOnMouseExited((event)->{
			back.setFont(new Font("ERAS BOLD ITC",50));
		});
		
		back.setOnMouseClicked((event)->{
			handle.getLoad_Sound().getMenuSelect().play();
			howToPlayScreen.setVisible(false);
			back.setVisible(false);
		});
		
		howToPlay.setOnMouseEntered((event)->{
			howToPlay.setFont(new Font("ERAS BOLD ITC",50));
			howToPlay.setX(115);
			howToPlay.setFill(Color.YELLOW);
		});
		
		howToPlay.setOnMouseExited((event)->{
			howToPlay.setFont(new Font("ERAS BOLD ITC",30));
			howToPlay.setX(185);
			howToPlay.setFill(Color.WHITE);
		});
		
		howToPlay.setOnMouseClicked((event)->{
			handle.getLoad_Sound().getMenuSelect().play();
			howToPlayScreen.setVisible(true);
			back.setVisible(true);
		});
		
		Text exit = new Text(265,600,"EXIT");
		exit.setFont(new Font("ERAS BOLD ITC",30));
		exit.setFill(Color.WHITE);
		
		exit.setOnMouseEntered((event)->{
			exit.setFont(new Font("ERAS BOLD ITC",50));
			exit.setX(245);
			exit.setFill(Color.RED);
		});
		
		exit.setOnMouseExited((event)->{
			exit.setFont(new Font("ERAS BOLD ITC",30));
			exit.setX(265);
			exit.setFill(Color.WHITE);
		});
		
		exit.setOnMouseClicked((event)->{
			handle.getLoad_Sound().getMenuSelect().play();
			System.exit(0);
		});
		
		Text profile = new Text(20,660,"Profile");
		profile.setFont(new Font("ERAS BOLD ITC",20));
		profile.setFill(Color.WHITE);
		
		profile.setOnMouseClicked((event)->{
			handle.getLoad_Sound().getMenuSelect().play();
			handle.getPlayer().show();
		});
		
		profile.setOnMouseEntered((event)->{
			profile.setUnderline(true);
		});
		
		profile.setOnMouseExited((event)->{
			profile.setUnderline(false);
		});
		
		Rectangle shop = new Rectangle(490,600,92,82);
		shop.setFill(new ImagePattern(handle.getLoad_image().getShopIcon()));
		
		shop.setOnMouseClicked((event)->{
			handle.getLoad_Sound().getMenuSelect().play();
			handle.getShop().openShop();
		});
		
		Group drawMenu = new Group();
		drawMenu.getChildren().addAll(menuBg1,menuBg2,title,start,howToPlay,exit,profile,shop,handle.getShop(),
									  howToPlayScreen,handle.getPlayer().getPlayerProfile(),back);
		mainMenu = new Scene(drawMenu,600,700);
	}
	
	private void gameScene() {
		
		gameBG1_coordinate = new Vector<>();
		gameBG1_coordinate.add(-50);
		gameBG1_coordinate.add(-2148);
		
		gameBG2_coordinate = new Vector<>();
		gameBG2_coordinate.add(-50);
		gameBG2_coordinate.add(3);
		
		gameBg1 = new Rectangle(gameBG1_coordinate.get(0),gameBG1_coordinate.get(1),700,2160);
		gameBg2 = new Rectangle(gameBG2_coordinate.get(0),gameBG2_coordinate.get(1),700,2160);
		gameBg1.setFill(new ImagePattern(handle.getLoad_image().getGameBg()));
		gameBg2.setFill(new ImagePattern(handle.getLoad_image().getGameBg()));
		
		Group drawGame = new Group();
		drawGame.getChildren().addAll(gameBg1,gameBg2,canvas);
		
		
		game = new Scene(drawGame,600,700);
		game.setOnKeyPressed(new GameKeyPressed(handle));
		game.setOnKeyReleased(new GameKeyReleased(handle));
	}
	
	public void tick() {
		if(sceneNumber == menuScene) {
			if(!handle.getLoad_Sound().getMainMenuMusic().isPlaying())handle.getLoad_Sound().getMainMenuMusic().play();
			menuBG1_coordinate.setElementAt(menuBG1_coordinate.get(0)-1,0);
			if(menuBG1_coordinate.get(0)<-1320) { 
				menuBG1_coordinate.set(0, 1290);
			}
			menuBg1.setX(menuBG1_coordinate.get(0));
			menuBG2_coordinate.setElementAt(menuBG2_coordinate.get(0)-1,0);
			if(menuBG2_coordinate.get(0)<-1320) {
				menuBG2_coordinate.set(0,1290);
			}
			menuBg2.setX(menuBG2_coordinate.get(0));
		}else {
			if(handle.getGameLogic().isPause())return;
			if(!handle.getLoad_Sound().getGameMusic().isPlaying())handle.getLoad_Sound().getGameMusic().play();
			handle.tick();
			gameBg1.setY(gameBG1_coordinate.get(1));
			gameBg2.setY(gameBG2_coordinate.get(1));
			gameBG1_coordinate.setElementAt(gameBG1_coordinate.get(1)+3, 1);
			if(gameBG1_coordinate.get(1) >= 2154) {
				gameBG1_coordinate.set(1, -2148);
			}
			gameBG2_coordinate.setElementAt(gameBG2_coordinate.get(1)+3, 1);
			if(gameBG2_coordinate.get(1) >= 2154) {
				gameBG2_coordinate.set(1, -2148);
			}
		}
	}
	
	public void render() {
		gc.clearRect(0, 0, 600,700);
		if(sceneNumber == gameScene) {
			handle.render(gc);
		}
	}
	
	public void change() {
		if(sceneNumber == menuScene) {
			if(handle.getLoad_Sound().getMainMenuMusic().isPlaying())handle.getLoad_Sound().getMainMenuMusic().stop();
			handle.getLoad_Sound().getGameMusic().play();
			sceneNumber = gameScene;
			stage.setScene(game);
			handle.getGameLogic().generatePlayer();
		}else {
			if(handle.getLoad_Sound().getGameMusic().isPlaying())handle.getLoad_Sound().getGameMusic().stop();
			handle.getLoad_Sound().getMainMenuMusic().play();
			handle.getGameLogic().setHasBoss(false);
			handle.getGameLogic().setScore(0);
			handle.getGameLogic().setDelay(0);
			handle.getAllObject().clear();
			sceneNumber = menuScene;
			stage.setScene(mainMenu);
		}
	}
	
	public int getSceneNumber() {
		return sceneNumber;
	}	
}
