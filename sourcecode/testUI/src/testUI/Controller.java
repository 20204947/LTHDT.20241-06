package testUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


public class Controller{
	
	Player player1 = new Player(true);
	Player player2 = new Player(false);
	
	static int count = 0;
	
	public void changeTurn() {
		if((count%2) == 0) {
			player1.setTurn(true);
			player2.setTurn(false);
			flag1.setVisible(false);
			flag2.setVisible(true);
		}else {
			player1.setTurn(false);
			player2.setTurn(true);
			flag1.setVisible(true);
			flag2.setVisible(false);
		}
		count++;
	}
	
	
	List<SmallGem> gemList = new ArrayList<SmallGem>();	
	GameBoard gameBoard = new GameBoard();
	int indexCell = 0;
	private boolean flag;
	
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
	private Text scoreText1;
	
	@FXML
	private Text scoreText2;
	
	@FXML
	private ImageView flag1;
	
	@FXML
	private ImageView flag2;
	
	ToaDo td = new ToaDo();
	
	public ToaDo dichDen(int i) {
		
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
		cungChieuButton.setVisible(false);
		nguocChieuButton.setVisible(false);
		cungChieuButton1.setVisible(false);
		nguocChieuButton1.setVisible(false);
		flag1.setVisible(true);
		flag2.setVisible(false);
		scoreText1.setText(Integer.toString(0));
		scoreText2.setText(Integer.toString(0));
		ToaDo td = new ToaDo();
		for(Square cell : gameBoard.getListCell()){
			for(Gem gem : cell.getListGem()) {
				td = dichDen(cell.getId());
				if((cell != gameBoard.getListCell()[0]) && (cell != gameBoard.getListCell()[6])) {
					gem.getImage().setLayoutX(td.getX());
					gem.getImage().setLayoutY(td.getY());
				}
				imagePane.getChildren().add(gem.getImage());
			}
			System.out.println(cell.getValue());
		}
	}

	
	@FXML
	public void cungChieu() {
	    moveClockwiseUntilEmpty();
	    cungChieuButton.setVisible(false);
	    nguocChieuButton.setVisible(false);
	    cungChieuButton1.setVisible(false);
	    nguocChieuButton1.setVisible(false);
	    changeTurn();
	}

	private void moveClockwiseUntilEmpty() {
	    SequentialTransition transition = moveGemClockwiseWithReturn();

	    // Kiểm tra khi kết thúc animation
	    transition.setOnFinished(event -> {
	        if ((!gameBoard.getListCell()[indexCell].getListGem().isEmpty()) && (indexCell != 0) && (indexCell != 6)) {
	            moveClockwiseUntilEmpty();
	        }else if ((indexCell == 0) || (indexCell == 6)){
	        	return;
	        }else {
	        	eatByPlayerMove1();
	        }
	        if(endGame()) {
	        	System.out.println("END GAME");
	        	showGameOverPopup();
	        }
	        
	    });
	    

	    transition.play();
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
	        translate.setDuration(Duration.millis(500));

	        double actualX = gem.getImage().getTranslateX() + gem.getImage().getLayoutX();
	        double actualY = gem.getImage().getTranslateY() + gem.getImage().getLayoutY();

	        ToaDo td = dichDen((indexCell + j) % 12);

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
	    SequentialTransition transition = moveGemCounterClockwiseWithReturn();

	    // Kiểm tra khi kết thúc animation
	    transition.setOnFinished(event -> {
	        if ((!gameBoard.getListCell()[indexCell].getListGem().isEmpty()) && (indexCell != 0) && (indexCell != 6)) {
	            moveCounterClockwiseUntilEmpty();
	        }else if ((indexCell == 0) || (indexCell == 6)){
	        	return;
	        }else {
	        	eatByPlayerMove2();
	        }
	        if(endGame()) {
	        	System.out.println("END GAME");
	        	showGameOverPopup();
	        }
	    });
	    

	    transition.play();
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
	        translate.setDuration(Duration.millis(500));

	        double actualX = gem.getImage().getTranslateX() + gem.getImage().getLayoutX();
	        double actualY = gem.getImage().getTranslateY() + gem.getImage().getLayoutY();

	        ToaDo td = dichDen((12 + indexCell - j) % 12);

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
	    cungChieuButton.setVisible(false);
	    nguocChieuButton.setVisible(false);
	    cungChieuButton1.setVisible(false);
	    nguocChieuButton1.setVisible(false);
	    changeTurn();
	}

	
	public SequentialTransition eat1() {
	    SequentialTransition sequentialTransition = new SequentialTransition();

	    for (Gem gem : gameBoard.getListCell()[indexCell].getListGem()) {
	        TranslateTransition translate = new TranslateTransition();
	        translate.setNode(gem.getImage());
	        translate.setDuration(Duration.millis(500));

	        double actualX = gem.getImage().getTranslateX() + gem.getImage().getLayoutX();
	        double actualY = gem.getImage().getTranslateY() + gem.getImage().getLayoutY();
	        ToaDo td = dichDen(12);

	        translate.setByX(td.getX() - actualX);
	        translate.setByY(td.getY() - actualY);

	        // Thêm hiệu ứng chuyển động vào danh sách
	        sequentialTransition.getChildren().add(translate);

	        // Thêm tạm dừng sau mỗi hiệu ứng
	        PauseTransition pause = new PauseTransition(Duration.millis(50));
	        sequentialTransition.getChildren().add(pause);
	        player1.setScore(player1.getScore()+gem.getValue());
	        scoreText1.setText(Integer.toString(player1.getScore()));
	    }
	    
	    gameBoard.getListCell()[indexCell].getListGem().clear();
	    
	    return sequentialTransition;
	}
	
	public SequentialTransition eat2() {
	    SequentialTransition sequentialTransition = new SequentialTransition();

	    for (Gem gem : gameBoard.getListCell()[indexCell].getListGem()) {
	        TranslateTransition translate = new TranslateTransition();
	        translate.setNode(gem.getImage());
	        translate.setDuration(Duration.millis(500));

	        double actualX = gem.getImage().getTranslateX() + gem.getImage().getLayoutX();
	        double actualY = gem.getImage().getTranslateY() + gem.getImage().getLayoutY();
	        ToaDo td = dichDen(13);

	        translate.setByX(td.getX() - actualX);
	        translate.setByY(td.getY() - actualY);

	        // Thêm hiệu ứng chuyển động vào danh sách
	        sequentialTransition.getChildren().add(translate);

	        // Thêm tạm dừng sau mỗi hiệu ứng
	        PauseTransition pause = new PauseTransition(Duration.millis(50));
	        sequentialTransition.getChildren().add(pause);
	        player2.setScore(player2.getScore()+gem.getValue());
	        scoreText2.setText(Integer.toString(player2.getScore()));
	    }
	    
	    gameBoard.getListCell()[indexCell].getListGem().clear();
	    
	    return sequentialTransition;
	}
	
	public void eatByPlayerMove1() {
    		if(gameBoard.getListCell()[indexCell].getListGem().isEmpty()) {
    			if(gameBoard.getListCell()[(indexCell + 1)%12].getListGem().isEmpty()) {
    				return;
    			}
    			if(player1.isTurn()) {
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
    		}
	}
	
	public void eatByPlayerMove2() {
		if(gameBoard.getListCell()[indexCell].getListGem().isEmpty()) {
			if(gameBoard.getListCell()[(12+ indexCell - 1)%12].getListGem().isEmpty()) {
				return;
			}
			if(player1.isTurn()) {
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
		}
	}
	
	public boolean endGame() {
		int total1 = 0;
		int total2 = 0;
		System.out.println("---------------");
		if((gameBoard.getListCell()[0].getListGem().isEmpty()) && (gameBoard.getListCell()[6].getListGem().isEmpty())) {
			return true;
		}
		for(int i = 1; i<6; i++) {
			total1 += gameBoard.getListCell()[i].getValue();
			System.out.println(i + "= " + gameBoard.getListCell()[i].getValue());
		}
		for(int i = 7; i<12; i++) {
			total2 += gameBoard.getListCell()[i].getValue();
			System.out.println(i + "= " + gameBoard.getListCell()[i].getValue());
		}
		if((total1 == 0) || (total2 == 0)) {
			return true;
		}
		return false;
	}
	
	
	@FXML
	public void click() {
		System.out.println("----------------------");
		for (Square cell : gameBoard.getListCell()) {
			System.out.println(cell.getValue());
		}
		
	}
	
	@FXML
	public void click1(){
		if (player1.isTurn()) {
			cungChieuButton.setVisible(true);
			nguocChieuButton.setVisible(true);
		}else {
			cungChieuButton1.setVisible(true);
			nguocChieuButton1.setVisible(true);
		}
		indexCell = 1;
	}

	@FXML
	public void click2() {
		if (player1.isTurn()) {
			cungChieuButton.setVisible(true);
			nguocChieuButton.setVisible(true);
		}else {
			cungChieuButton1.setVisible(true);
			nguocChieuButton1.setVisible(true);
		}
		indexCell = 2;
	}
	@FXML
	public void click3() {
		if (player1.isTurn()) {
			cungChieuButton.setVisible(true);
			nguocChieuButton.setVisible(true);
		}else {
			cungChieuButton1.setVisible(true);
			nguocChieuButton1.setVisible(true);
		}
		indexCell = 3;
	}
	@FXML
	public void click4() {
		if (player1.isTurn()) {
			cungChieuButton.setVisible(true);
			nguocChieuButton.setVisible(true);
		}else {
			cungChieuButton1.setVisible(true);
			nguocChieuButton1.setVisible(true);
		}
		indexCell = 4;
	}
	@FXML
	public void click5() {
		cungChieuButton.setVisible(true);
		nguocChieuButton.setVisible(true);
		indexCell = 5;
	}
	@FXML
	public void click7() {
		if (player1.isTurn()) {
			cungChieuButton.setVisible(true);
			nguocChieuButton.setVisible(true);
		}else {
			cungChieuButton1.setVisible(true);
			nguocChieuButton1.setVisible(true);
		}
		indexCell = 7;
	}
	@FXML
	public void click8() {
		if (player1.isTurn()) {
			cungChieuButton.setVisible(true);
			nguocChieuButton.setVisible(true);
		}else {
			cungChieuButton1.setVisible(true);
			nguocChieuButton1.setVisible(true);
		}
		indexCell = 8;
	}
	@FXML
	public void click9() {
		if (player1.isTurn()) {
			cungChieuButton.setVisible(true);
			nguocChieuButton.setVisible(true);
		}else {
			cungChieuButton1.setVisible(true);
			nguocChieuButton1.setVisible(true);
		}
		indexCell = 9;
	}
	@FXML
	public void click10() {
		if (player1.isTurn()) {
			cungChieuButton.setVisible(true);
			nguocChieuButton.setVisible(true);
		}else {
			cungChieuButton1.setVisible(true);
			nguocChieuButton1.setVisible(true);
		}
		indexCell = 10;
	}
	@FXML
	public void click11() {
		if (player1.isTurn()) {
			cungChieuButton.setVisible(true);
			nguocChieuButton.setVisible(true);
		}else {
			cungChieuButton1.setVisible(true);
			nguocChieuButton1.setVisible(true);
		}
		indexCell = 11;
	}
	
	private void showGameOverPopup() {
	    Platform.runLater(() -> {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("EndGame.fxml"));
	            Parent root = loader.load();

	            Stage popupStage = new Stage();
	            popupStage.initModality(Modality.APPLICATION_MODAL);
	            popupStage.setScene(new Scene(root));
	            popupStage.setTitle("Game Over");
	            
	            EndGameController endGameController = loader.getController();
	            
	            endGameController.setMatchStage(((Stage) imagePane.getScene().getWindow()));
	            
	            popupStage.showAndWait();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    });
	}

	

	
}
