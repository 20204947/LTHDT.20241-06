package model.gem;

import java.io.File;

import javafx.scene.image.ImageView;
import model.setting.Setting;

public class SmallGem extends Gem{
	
	public SmallGem(int inCell) {
		super(inCell);
		this.setValue(Setting.getValueOfSmallGem());
		this.setImage(setImg());
	}
	
	public ImageView setImg() {
    	File file = new File("view/image/small-gem.png");
    	ImageView img = new ImageView(file.toURI().toString());
        img.setFitWidth(20);
        img.setFitHeight(20);
        return img;
	}
}
