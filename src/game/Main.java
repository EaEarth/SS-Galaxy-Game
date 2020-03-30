package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	
	private SceneSelect scene ;
	
	@Override
	public void start(Stage primary) throws Exception {

		this.scene = new SceneSelect(primary);
		primary.setResizable(false);
		primary.setTitle("SS.Galaxy");
		
		Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        
        KeyFrame kf = new KeyFrame(
            Duration.seconds(0.034),                // 30 FPS
            new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {
                    tick();
                    render();
                }
            });
        
        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
		primary.show();
	}

	public void tick() {
		scene.tick();
	}
	
	public void render() {
		scene.render();
	}
	
	public static void main(String [] args) {
		launch(args);
	}
}
