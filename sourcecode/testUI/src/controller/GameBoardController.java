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


public class GameBoardController{
	GameBoard gameBoard = new GameBoard();
	
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
	
	public void initListText() {
		this.listText = new Text[] {cell0, cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9, cell10, cell11};
	}
	
	public void setListText() {
		for(int i = 0; i<12; i++) {
			this.listText[i].setText(Integer.toString(gameBoard.getListCell()[i].getValue()));
		}
	}
	
	static int count;
	
	public void changeTurn() {
		if((count%2) == 0) {
			gameBoard.getPlayer1().setTurn(true);
			gameBoard.getPlayer2().setTurn(false);
			flag1.setVisible(true);
			flag2.setVisible(false);
			surrenderButton1.setVisible(true);
			surrenderButton2.setVisible(false);
		}else {
			gameBoard.getPlayer1().setTurn(false);
			gameBoard.getPlayer2().setTurn(true);
			flag1.setVisible(false);
			flag2.setVisible(true);
			surrenderButton1.setVisible(false);
			surrenderButton2.setVisible(true);
		}
		count++;
	}
	
	public void setIdAndName(String id1, String name1, String id2, String name2) {
		this.id1.setText(id1);
		this.name1.setText(name1);
		this.id2.setText(id2);
		this.name2.setText(name2);
		
		this.gameBoard.getPlayer1().setId(id1);
		this.gameBoard.getPlayer1().setName(name1);
		this.gameBoard.getPlayer2().setId(id2);
		this.gameBoard.getPlayer2().setName(name2);
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
	
	public Coordinate dichDen(int i) {
		
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
	
	@FXML
	public void initialize() {
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
	
	public void setNewTurn() {
	    cungChieuButton.setVisible(false);
	    nguocChieuButton.setVisible(false);
	    cungChieuButton1.setVisible(false);
	    nguocChieuButton1.setVisible(false);
	    choosing1.setText(null);
	    choosing2.setText(null);
	}

	
	@FXML
	public void cungChieu() {
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
        	setListText();
        	if(endGame()) {
            	GameOver();
            }else {
            	changeTurn();
            }
        	return;
        } else if((gameBoard.getListCell()[indexCell].getListGem().isEmpty()) && (!gameBoard.getListCell()[(indexCell+1)%12].getListGem().isEmpty())){
        	eatByPlayerMove1();
        } else {
        	setListText();
        	if(endGame()) {
            	GameOver();
            }else {
            	changeTurn();
            }
        }
	}

	public SequentialTransition moveGemClockwiseWithReturn() {
	    SequentialTransition sequentialTransition = new SequentialTransition();
	    int j = 1;

	    for (Gem gem : gameBoard.getListCell()[indexCell].getListGem()) {
	        // Di chuyển viên ngọc sang ô tiếp theo
	        gameBoard.getListCell()[(indexCell + j) % 12].add(gem);

	        // Tạo TranslateTransition
	        TranslateTransition translate = new TranslateTransition();
	        translate.setNode(gem.getImage());
	        translate.setDuration(Duration.millis(Setting.getSpeed()));

	        double actualX = gem.getImage().getTranslateX() + gem.getImage().getLayoutX();
	        double actualY = gem.getImage().getTranslateY() + gem.getImage().getLayoutY();

	        Coordinate td = dichDen((indexCell + j) % 12);

	        translate.setByX(td.getX() - actualX);
	        translate.setByY(td.getY() - actualY);

	        sequentialTransition.getChildren().add(translate);

	        // Thêm thời gian tạm dừng
	        PauseTransition pause = new PauseTransition(Duration.millis(50));
	        sequentialTransition.getChildren().add(pause);
	        j++;
	    }

	    // Xóa danh sách ngọc trong ô hiện tại
	    gameBoard.getListCell()[indexCell].getListGem().clear();

	    // Cập nhật ô hiện tại
	    indexCell = (indexCell + j) % 12;

	    return sequentialTransition;
	}
	
	
	private void moveCounterClockwiseUntilEmpty() {
	    // Kiểm tra khi kết thúc animation
	    
	    if ((!gameBoard.getListCell()[indexCell].getListGem().isEmpty()) && (indexCell != 0) && (indexCell != 6)) {
	    	SequentialTransition transition = moveGemCounterClockwiseWithReturn();
	    	transition.setOnFinished(event -> {
		            moveCounterClockwiseUntilEmpty();
		    });
	    	transition.play();
        } else if((indexCell == 0) || (indexCell == 6)) {
        	setListText();
        	if(endGame()) {
            	GameOver();
            }else {
            	changeTurn();
            }
        	return;
        } else if((gameBoard.getListCell()[indexCell].getListGem().isEmpty()) && (!gameBoard.getListCell()[(12+indexCell-1)%12].getListGem().isEmpty())){
        	eatByPlayerMove2();
        } else {
        	setListText();
        	if(endGame()) {
            	GameOver();
            }else {
            	changeTurn();
            }
        }
	}

	public SequentialTransition moveGemCounterClockwiseWithReturn() {
	    SequentialTransition sequentialTransition = new SequentialTransition();
	    int j = 1;

	    for (Gem gem : gameBoard.getListCell()[indexCell].getListGem()) {
	        // Di chuyển viên ngọc sang ô tiếp theo
	        gameBoard.getListCell()[(12 + indexCell - j) % 12].add(gem);

	        // Tạo TranslateTransition
	        TranslateTransition translate = new TranslateTransition();
	        translate.setNode(gem.getImage());
	        translate.setDuration(Duration.millis(Setting.getSpeed()));

	        double actualX = gem.getImage().getTranslateX() + gem.getImage().getLayoutX();
	        double actualY = gem.getImage().getTranslateY() + gem.getImage().getLayoutY();

	        Coordinate td = dichDen((12 + indexCell - j) % 12);

	        translate.setByX(td.getX() - actualX);
	        translate.setByY(td.getY() - actualY);

	        sequentialTransition.getChildren().add(translate);

	        // Thêm thời gian tạm dừng
	        PauseTransition pause = new PauseTransition(Duration.millis(50));
	        sequentialTransition.getChildren().add(pause);
	        j++;
	    }

	    // Xóa danh sách ngọc trong ô hiện tại
	    gameBoard.getListCell()[indexCell].getListGem().clear();

	    // Cập nhật ô hiện tại
	    indexCell = (12 + indexCell - j) % 12;

	    return sequentialTransition;
	}
	
	@FXML
	public void nguocChieu() {
	    moveCounterClockwiseUntilEmpty();
	    setNewTurn();
	}

	
	public SequentialTransition eat1() {
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

	        // Thêm hiệu ứng chuyển động vào danh sách
	        sequentialTransition.getChildren().add(translate);

	        // Thêm tạm dừng sau mỗi hiệu ứng
	        PauseTransition pause = new PauseTransition(Duration.millis(50));
	        sequentialTransition.getChildren().add(pause);
	        gameBoard.getPlayer1().setScore(gameBoard.getPlayer1().getScore()+gem.getValue());
	        scoreText1.setText(Integer.toString(gameBoard.getPlayer1().getScore()));
	    }
	    
	    gameBoard.getListCell()[indexCell].getListGem().clear();
	    
	    return sequentialTransition;
	}
	
	public SequentialTransition eat2() {
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

	        // Thêm hiệu ứng chuyển động vào danh sách
	        sequentialTransition.getChildren().add(translate);

	        // Thêm tạm dừng sau mỗi hiệu ứng
	        PauseTransition pause = new PauseTransition(Duration.millis(50));
	        sequentialTransition.getChildren().add(pause);
	        gameBoard.getPlayer2().setScore(gameBoard.getPlayer2().getScore()+gem.getValue());
	        scoreText2.setText(Integer.toString(gameBoard.getPlayer2().getScore()));
	    }
	    
	    gameBoard.getListCell()[indexCell].getListGem().clear();
	    
	    return sequentialTransition;
	}
	
	public void eatByPlayerMove1() {
    		if(gameBoard.getListCell()[indexCell].getListGem().isEmpty()) {
    			if(gameBoard.getListCell()[(indexCell + 1)%12].getListGem().isEmpty()) {
    				setListText();
    				if(endGame()) {
        				GameOver();
    				}else {
    					changeTurn();
    				}
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
    			setListText();
    			if(endGame()) {
        			GameOver();
    			}else {
    				changeTurn();
    			}
    		}
	}
	
	public void eatByPlayerMove2() {
		if(gameBoard.getListCell()[indexCell].getListGem().isEmpty()) {
			if(gameBoard.getListCell()[(12+ indexCell - 1)%12].getListGem().isEmpty()) {
				setListText();
    			if(endGame()) {
        			GameOver();
    			}else {
    				changeTurn();
    			}
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
			setListText();
			if(endGame()) {
    			GameOver();
			}else {
				changeTurn();
			}
		}
	}
	
	public boolean endGame() {
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
	
	
	public void click() {
		if (gameBoard.getPlayer1().isTurn()) {
			cungChieuButton.setVisible(true);
			nguocChieuButton.setVisible(true);
		}else {
			cungChieuButton1.setVisible(true);
			nguocChieuButton1.setVisible(true);
		}
	}
	
	@FXML
	public void click1(){
		if(gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[1].getValue()!=0)) {
			click();
			indexCell = 1;
			choosing1.setText(Integer.toString(indexCell));
		}
	}

	@FXML
	public void click2() {
		if(gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[2].getValue()!=0)) {
			click();
			indexCell = 2;
			choosing1.setText(Integer.toString(indexCell));
		}
	}
	@FXML
	public void click3() {
		if(gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[3].getValue()!=0)) {
			click();
			indexCell = 3;
			choosing1.setText(Integer.toString(indexCell));
		}
	}
	@FXML
	public void click4() {
		if(gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[4].getValue()!=0)) {
			click();
			indexCell = 4;
			choosing1.setText(Integer.toString(indexCell));
		}
	}
	@FXML
	public void click5() {
		if(gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[5].getValue()!=0)) {
			click();
			indexCell = 5;
			choosing1.setText(Integer.toString(indexCell));
		}
	}
	@FXML
	public void click7() {
		if(!gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[7].getValue()!=0)) {
			click();
			indexCell = 7;
			choosing2.setText(Integer.toString(indexCell));
		}
	}
	@FXML
	public void click8() {
		if(!gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[8].getValue()!=0)) {
			click();
			indexCell = 8;
			choosing2.setText(Integer.toString(indexCell));
		}
	}
	@FXML
	public void click9() {
		if(!gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[9].getValue()!=0)) {
			click();
			indexCell = 9;
			choosing2.setText(Integer.toString(indexCell));
		}
	}
	@FXML
	public void click10() {
		if(!gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[10].getValue()!=0)) {
			click();
			indexCell = 10;
			choosing2.setText(Integer.toString(indexCell));
		}
	}
	@FXML
	public void click11() {
		if(!gameBoard.getPlayer1().isTurn() && (gameBoard.getListCell()[11].getValue()!=0)) {
			click();
			indexCell = 11;
			choosing2.setText(Integer.toString(indexCell));
		}
	}
	
	private void GameOver() {
	    Platform.runLater(() -> {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/end_game/EndGame.fxml"));
	            Parent root = loader.load();
	            
	            Stage popupStage = new Stage();
	            
	            popupStage.setResizable(false);
	            
	            popupStage.setOnCloseRequest(event -> {
	                event.consume();
	            });
	            
	            popupStage.initModality(Modality.APPLICATION_MODAL);
	            popupStage.setScene(new Scene(root));
	            popupStage.setTitle("Game Over");
	            
	            EndGameController endGameController = loader.getController();
	            
	            endGameController.setPlayer(id1.getText(), name1.getText(), Integer.parseInt(scoreText1.getText()), id2.getText(), name2.getText(), Integer.parseInt(scoreText2.getText()));
	            
	            endGameController.setMatchStage(((Stage) imagePane.getScene().getWindow()));
	            
	            popupStage.showAndWait();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    });
	}
	
	
	@FXML
	public void surrender() {
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
	
}
