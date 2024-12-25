package controller;

import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.coordinate.Coordinate;
import model.game_board.GameBoard;
import model.gem.Gem;
import model.setting.Setting;


public class GameBoard2Controller{
	GameBoard gameBoard = new GameBoard();
	int cellOfBot;
	int moveOfBot;
	
	@FXML
	private Text id1;
	
	@FXML
	private Text id2;
	
	@FXML
	private Text name1;
	
	@FXML
	private Text name2;
	
	@FXML
	private Text choosing1;
	
	@FXML
	private Text choosing2;
	
	@FXML
	private Text cell0;
	
	@FXML
	private Text cell1;
	
	@FXML
	private Text cell2;
	
	@FXML
	private Text cell3;
	
	@FXML
	private Text cell4;
	
	@FXML
	private Text cell5;
	
	@FXML
	private Text cell6;
	
	@FXML
	private Text cell7;
	
	@FXML
	private Text cell8;
	
	@FXML
	private Text cell9;
	
	@FXML
	private Text cell10;
	
	@FXML
	private Text cell11;
	
	private Text[] listText;
	
	private void initListText() {
		this.listText = new Text[] {cell0, cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9, cell10, cell11};
	}
	
	private void setListText() {
		for(int i = 0; i<12; i++) {
			this.listText[i].setText(Integer.toString(gameBoard.getListCell()[i].getValue()));
		}
	}
	
	static int count;
	
	private void changeTurn() {
		if((count%2) == 0) {
			gameBoard.getPlayer1().setTurn(true);
			gameBoard.getPlayer2().setTurn(false);
			flag1.setVisible(true);
			flag2.setVisible(false);
			surrenderButton1.setVisible(true);
		}else {
			gameBoard.getPlayer1().setTurn(false);
			gameBoard.getPlayer2().setTurn(true);
			flag1.setVisible(false);
			flag2.setVisible(true);
			surrenderButton1.setVisible(false);
		}
		count++;
	}
	
	protected void setIdAndName(String id, String name) {
		this.id1.setText(id);
		this.name1.setText(name);
		this.id2.setText(gameBoard.getPlayer2().getId());
		this.name2.setText(gameBoard.getPlayer2().getName());
		
		this.gameBoard.getPlayer1().setId(id);
		this.gameBoard.getPlayer1().setName(name);
	}
	
	int indexCell;
	
	@FXML
	private ImageView myImage;
	
	@FXML
    private Pane imagePane;
	
	@FXML
	private Button cungChieuButton1;
	
	@FXML
	private Button nguocChieuButton1;
	
	@FXML
	private Button cungChieuButton;
	
	@FXML
	private Button nguocChieuButton;
	
	@FXML
	private Button surrenderButton1;
	
	@FXML
	private Button surrenderButton2;
	
	@FXML
	private Text scoreText1;
	
	@FXML
	private Text scoreText2;
	
	@FXML
	private ImageView flag1;
	
	@FXML
	private ImageView flag2;
	
	Coordinate td = new Coordinate();
	
	private Coordinate dichDen(int i) {
		
		switch (i) {
			case 0: 
				td.setX(40+Math.random()*40);
				td.setY(30+Math.random()*120);
				break;
			case 1: 
			case 2: 
			case 3: 
			case 4: 
			case 5: 
				td.setX(10+i*100+Math.random()*60);
				td.setY(10+Math.random()*60);
				break;
			case 6: 
				td.setX(600+Math.random()*40);
				td.setY(30+Math.random()*120);
				break;
			case 7: 
			case 8: 
			case 9: 
			case 10:
			case 11: 
				td.setX(10+(12-i)*100+Math.random()*60);
				td.setY(110+Math.random()*60);
				break;
			case 13:
				td.setX(100+Math.random()*60);
				td.setY(300+Math.random()*60);
				break;
			case 12:
				td.setX(100+Math.random()*60);
				td.setY(-200+Math.random()*60);
				break;
		}
		return td;
	}
	
	private void checkEndGame() {
    	setListText();
    	if(endGame()) {
        	GameOver();
        }else {
        	changeTurn();
        	if(!gameBoard.getPlayer1().isTurn()) {
        		botMove();
        	}
        }
	}
	
	@FXML
	private void initialize() {
		indexCell=0;
		count = 1;
		initListText();
		setListText();
		id1.setText(gameBoard.getPlayer1().getId());
		id2.setText(gameBoard.getPlayer2().getId());
		name1.setText(gameBoard.getPlayer1().getName());
		name2.setText(gameBoard.getPlayer2().getName());
		cungChieuButton.setVisible(false);
		nguocChieuButton.setVisible(false);
		cungChieuButton1.setVisible(false);
		nguocChieuButton1.setVisible(false);
		flag1.setVisible(true);
		flag2.setVisible(false);
		surrenderButton1.setVisible(true);
		surrenderButton2.setVisible(false);
		scoreText1.setText(Integer.toString(0));
		scoreText2.setText(Integer.toString(0));
		Coordinate td = new Coordinate();
		for(int i = 0; i<12; i++) {
			for(Gem gem : gameBoard.getListCell()[i].getListGem()) {
				td = dichDen(i);
				if((gameBoard.getListCell()[i] != gameBoard.getListCell()[0]) && (gameBoard.getListCell()[i] != gameBoard.getListCell()[6])) {
					gem.getImage().setLayoutX(td.getX());
					gem.getImage().setLayoutY(td.getY());
				}else if (gameBoard.getListCell()[i] == gameBoard.getListCell()[0]) {
					gem.getImage().setLayoutX(30);
					gem.getImage().setLayoutY(25);
				} else {
					gem.getImage().setLayoutX(620);
					gem.getImage().setLayoutY(125);
				}
				imagePane.getChildren().add(gem.getImage());
			}
		}
	}
	
	private void setNewTurn() {
	    cungChieuButton.setVisible(false);
	    nguocChieuButton.setVisible(false);
	    cungChieuButton1.setVisible(false);
	    nguocChieuButton1.setVisible(false);
	    choosing1.setText(null);
	    choosing2.setText(null);
	}

	
	@FXML
	private void cungChieu() {
	    moveClockwiseUntilEmpty();
	    setNewTurn();
	}

	private void moveClockwiseUntilEmpty() {
	    if ((!gameBoard.getListCell()[indexCell].getListGem().isEmpty()) && (indexCell != 0) && (indexCell != 6)) {
	    	SequentialTransition transition = moveGemClockwiseWithReturn();
	    	transition.setOnFinished(event -> {
		            moveClockwiseUntilEmpty();
		    });
	    	transition.play();
        } else if((indexCell == 0) || (indexCell == 6)) {
        	checkEndGame();
        	return;
        } else if((gameBoard.getListCell()[indexCell].getListGem().isEmpty()) && (!gameBoard.getListCell()[(indexCell+1)%12].getListGem().isEmpty())){
        	eatByPlayerMove1();
        } else {
        	checkEndGame();
        }
	}

	private SequentialTransition moveGemClockwiseWithReturn() {
	    SequentialTransition sequentialTransition = new SequentialTransition();
	    int j = 1;

	    for (Gem gem : gameBoard.getListCell()[indexCell].getListGem()) {
	        gameBoard.getListCell()[(indexCell + j) % 12].add(gem);

	        TranslateTransition translate = new TranslateTransition();
	        translate.setNode(gem.getImage());
	        translate.setDuration(Duration.millis(Setting.getSpeed()));

	        double actualX = gem.getImage().getTranslateX() + gem.getImage().getLayoutX();
	        double actualY = gem.getImage().getTranslateY() + gem.getImage().getLayoutY();

	        Coordinate td = dichDen((indexCell + j) % 12);

	        translate.setByX(td.getX() - actualX);
	        translate.setByY(td.getY() - actualY);

	        sequentialTransition.getChildren().add(translate);

	        PauseTransition pause = new PauseTransition(Duration.millis(50));
	        sequentialTransition.getChildren().add(pause);
	        j++;
	    }

	    gameBoard.getListCell()[indexCell].getListGem().clear();

	    indexCell = (indexCell + j) % 12;

	    return sequentialTransition;
	}
	
	
	private void moveCounterClockwiseUntilEmpty() {
	    
	    if ((!gameBoard.getListCell()[indexCell].getListGem().isEmpty()) && (indexCell != 0) && (indexCell != 6)) {
	    	SequentialTransition transition = moveGemCounterClockwiseWithReturn();
	    	transition.setOnFinished(event -> {
		            moveCounterClockwiseUntilEmpty();
		    });
	    	transition.play();
        } else if((indexCell == 0) || (indexCell == 6)) {
        	checkEndGame();
        	return;
        } else if((gameBoard.getListCell()[indexCell].getListGem().isEmpty()) && (!gameBoard.getListCell()[(12+indexCell-1)%12].getListGem().isEmpty())){
        	eatByPlayerMove2();
        } else {
        	checkEndGame();
        }
	}

	private SequentialTransition moveGemCounterClockwiseWithReturn() {
	    SequentialTransition sequentialTransition = new SequentialTransition();
	    int j = 1;

	    for (Gem gem : gameBoard.getListCell()[indexCell].getListGem()) {
	        gameBoard.getListCell()[(12 + indexCell - j) % 12].add(gem);

	        TranslateTransition translate = new TranslateTransition();
	        translate.setNode(gem.getImage());
	        translate.setDuration(Duration.millis(Setting.getSpeed()));

	        double actualX = gem.getImage().getTranslateX() + gem.getImage().getLayoutX();
	        double actualY = gem.getImage().getTranslateY() + gem.getImage().getLayoutY();

	        Coordinate td = dichDen((12 + indexCell - j) % 12);

	        translate.setByX(td.getX() - actualX);
	        translate.setByY(td.getY() - actualY);

	        sequentialTransition.getChildren().add(translate);

	        PauseTransition pause = new PauseTransition(Duration.millis(50));
	        sequentialTransition.getChildren().add(pause);
	        j++;
	    }

	    gameBoard.getListCell()[indexCell].getListGem().clear();

	    indexCell = (12 + indexCell - j) % 12;

	    return sequentialTransition;
	}
	
	@FXML
	private void nguocChieu() {
	    moveCounterClockwiseUntilEmpty();
	    setNewTurn();
	}

	
	private SequentialTransition eat1() {
	    SequentialTransition sequentialTransition = new SequentialTransition();

	    for (Gem gem : gameBoard.getListCell()[indexCell].getListGem()) {
	        TranslateTransition translate = new TranslateTransition();
	        translate.setNode(gem.getImage());
	        translate.setDuration(Duration.millis(Setting.getSpeed()));

	        double actualX = gem.getImage().getTranslateX() + gem.getImage().getLayoutX();
	        double actualY = gem.getImage().getTranslateY() + gem.getImage().getLayoutY();
	        Coordinate td = dichDen(12);

	        translate.setByX(td.getX() - actualX);
	        translate.setByY(td.getY() - actualY);

	        sequentialTransition.getChildren().add(translate);

	        PauseTransition pause = new PauseTransition(Duration.millis(50));
	        sequentialTransition.getChildren().add(pause);
	        gameBoard.getPlayer1().setScore(gameBoard.getPlayer1().getScore()+gem.getValue());
	        scoreText1.setText(Integer.toString(gameBoard.getPlayer1().getScore()));
	    }
	    
	    gameBoard.getListCell()[indexCell].getListGem().clear();
	    
	    return sequentialTransition;
	}
	
	private SequentialTransition eat2() {
	    SequentialTransition sequentialTransition = new SequentialTransition();

	    for (Gem gem : gameBoard.getListCell()[indexCell].getListGem()) {
	        TranslateTransition translate = new TranslateTransition();
	        translate.setNode(gem.getImage());
	        translate.setDuration(Duration.millis(Setting.getSpeed()));

	        double actualX = gem.getImage().getTranslateX() + gem.getImage().getLayoutX();
	        double actualY = gem.getImage().getTranslateY() + gem.getImage().getLayoutY();
	        Coordinate td = dichDen(13);

	        translate.setByX(td.getX() - actualX);
	        translate.setByY(td.getY() - actualY);

	        sequentialTransition.getChildren().add(translate);

	        PauseTransition pause = new PauseTransition(Duration.millis(50));
	        sequentialTransition.getChildren().add(pause);
	        gameBoard.getPlayer2().setScore(gameBoard.getPlayer2().getScore()+gem.getValue());
	        scoreText2.setText(Integer.toString(gameBoard.getPlayer2().getScore()));
	    }
	    
	    gameBoard.getListCell()[indexCell].getListGem().clear();
	    
	    return sequentialTransition;
	}
	
	private void eatByPlayerMove1() {
    		if(gameBoard.getListCell()[indexCell].getListGem().isEmpty()) {
    			if(gameBoard.getListCell()[(indexCell + 1)%12].getListGem().isEmpty()) {
    				checkEndGame();
    				return;
    			}
    			if(gameBoard.getPlayer1().isTurn()) {
            		indexCell = (indexCell + 1)%12;
    	        	SequentialTransition transitionEat1 = eat1();
    	        	transitionEat1.setOnFinished(event -> {
    	        		indexCell = (indexCell + 1)%12;
    	        		eatByPlayerMove1();
    	        	});
    	        	
    	        	transitionEat1.play();
            	}
            	else {
            		indexCell = (indexCell + 1)%12;
            		SequentialTransition transitionEat2 = eat2();
    	        	transitionEat2.setOnFinished(event -> {
    	        		indexCell = (indexCell + 1)%12;
    	        		eatByPlayerMove1();
    	        	});
    	        	
    	        	transitionEat2.play();
            	}
    		}else {
    			checkEndGame();
    		}
	}
	
	private void eatByPlayerMove2() {
		if(gameBoard.getListCell()[indexCell].getListGem().isEmpty()) {
			if(gameBoard.getListCell()[(12+ indexCell - 1)%12].getListGem().isEmpty()) {
				checkEndGame();
				return;
			}
			if(gameBoard.getPlayer1().isTurn()) {
        		indexCell = (12+ indexCell - 1)%12;
	        	SequentialTransition transitionEat1 = eat1();
	        	transitionEat1.setOnFinished(event -> {
	        		indexCell = (12 + indexCell - 1)%12;
	        		eatByPlayerMove2();
	        	});
	        	
	        	transitionEat1.play();
        	}
        	else {
        		indexCell = (12 + indexCell - 1)%12;
        		SequentialTransition transitionEat2 = eat2();
	        	transitionEat2.setOnFinished(event -> {
	        		indexCell = (12 + indexCell - 1)%12;
	        		eatByPlayerMove2();
	        	});
	        	
	        	transitionEat2.play();
        	}
		}else {
			checkEndGame();
		}
	}
	
	private boolean endGame() {
		int total1 = 0;
		int total2 = 0;
		if((gameBoard.getListCell()[0].getListGem().isEmpty()) && (gameBoard.getListCell()[6].getListGem().isEmpty())) {
			return true;
		}
		for(int i = 1; i<6; i++) {
			total1 += gameBoard.getListCell()[i].getValue();
		}
		for(int i = 7; i<12; i++) {
			total2 += gameBoard.getListCell()[i].getValue();
		}
		if((total1 == 0) || (total2 == 0)) {
			return true;
		}
		return false;
	}
	
	
	private void click() {
		if (gameBoard.getPlayer1().isTurn()) {
			cungChieuButton.setVisible(true);
			nguocChieuButton.setVisible(true);
		}else {
			cungChieuButton1.setVisible(true);
			nguocChieuButton1.setVisible(true);
		}
	}
	
	@FXML
	private void click1(){
		if(gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[1].getValue()!=0)) {
			click();
			indexCell = 1;
			choosing1.setText(Integer.toString(indexCell));
		}
	}

	@FXML
	private void click2() {
		if(gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[2].getValue()!=0)) {
			click();
			indexCell = 2;
			choosing1.setText(Integer.toString(indexCell));
		}
	}
	@FXML
	private void click3() {
		if(gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[3].getValue()!=0)) {
			click();
			indexCell = 3;
			choosing1.setText(Integer.toString(indexCell));
		}
	}
	@FXML
	private void click4() {
		if(gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[4].getValue()!=0)) {
			click();
			indexCell = 4;
			choosing1.setText(Integer.toString(indexCell));
		}
	}
	@FXML
	private void click5() {
		if(gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[5].getValue()!=0)) {
			click();
			indexCell = 5;
			choosing1.setText(Integer.toString(indexCell));
		}
	}
	
	private void GameOver() {
	    Platform.runLater(() -> {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/end_game/EndGame2.fxml"));
	            Parent root = loader.load();
	            
	            Stage popupStage = new Stage();
	            popupStage.setResizable(false);
	            popupStage.setOnCloseRequest(event -> {
	                event.consume();
	            });
	            
	            popupStage.initModality(Modality.APPLICATION_MODAL);
	            popupStage.setScene(new Scene(root));
	            popupStage.setTitle("Game Over");
	            
	            EndGame2Controller endGame2Controller = loader.getController();
	            endGame2Controller.setPlayer(id1.getText(), name1.getText(), Integer.parseInt(scoreText1.getText()), id2.getText(), name2.getText(), Integer.parseInt(scoreText2.getText()));
	            endGame2Controller.setMatchStage(((Stage) imagePane.getScene().getWindow()));

	            popupStage.showAndWait();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    });
	}
	
	
	@FXML
	private void surrender() {
	    Platform.runLater(() -> {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/surrender/Surrender.fxml"));
	            Parent root = loader.load();

	            Stage popupStage = new Stage();
	            popupStage.setResizable(false);  
	            popupStage.setOnCloseRequest(event -> {
	                event.consume();
	            });
	            
	            SurrenderController endGameController = loader.getController();  
	            endGameController.setPlayer(id1.getText(), name1.getText(), Integer.parseInt(scoreText1.getText()), id2.getText(), name2.getText(), Integer.parseInt(scoreText2.getText()), gameBoard.getPlayer1().isTurn());
	            endGameController.setGameBoardStage(((Stage) imagePane.getScene().getWindow()));
	            
	            popupStage.initModality(Modality.APPLICATION_MODAL);
	            popupStage.setScene(new Scene(root));
	            popupStage.setTitle("Surrender");
	            popupStage.showAndWait();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    });
	}
	
	private void botMove() {
		botChooseCell();
		indexCell = cellOfBot;
		if(moveOfBot == 0) {
			cungChieu();
		}else {
			nguocChieu();
		}
	}
	
	private void botChooseCell() {
		do {
			botRandom();
		}while(gameBoard.getListCell()[cellOfBot].getListGem().isEmpty());
	}
	
	private void botRandom() {
      long currentTime = System.currentTimeMillis();
      cellOfBot = 7+ ((int) (currentTime % 5));
      moveOfBot = (int) (currentTime % 2);
	}
	
}
