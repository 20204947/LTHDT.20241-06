package testUI;

import java.io.File;

import javafx.scene.image.ImageView;

public class BigGem extends Gem{

	public BigGem(int inCell) {
		super(inCell);
		this.setValue(10);
		this.setImage(setImg());
		if(inCell == 0) {
			this.getImage().setLayoutX(25);
			this.getImage().setLayoutY(75);
		} else if(inCell == 6) {
			this.getImage().setLayoutX(625);
			this.getImage().setLayoutY(75);
		}
	}
	
	public ImageView setImg() {
    	File file = new File("C:\\Users\\ADMIN\\Desktop\\testUI\\testUI\\src\\testUI\\big-gem.png");
    	ImageView img = new ImageView(file.toURI().toString());
        img.setFitWidth(50);
        img.setFitHeight(50);
        return img;
	}
	
}
