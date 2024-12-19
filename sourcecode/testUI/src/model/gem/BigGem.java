package model.gem;

import java.io.File;

import javafx.scene.image.ImageView;
import model.setting.Setting;

public class BigGem extends Gem{
	
	public BigGem(int inCell) {
		super(inCell);
		this.setValue(Setting.getValueOfBigGem());
		this.setImage(setImg());
	}
	
	public ImageView setImg() {
    	File file = new File("src/view/image/big-gem.png");
    	ImageView img = new ImageView(file.toURI().toString());
        img.setFitWidth(50);
        img.setFitHeight(50);
        return img;
	}
	
}
