import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import javax.imageio.ImageIO;

//Image Loader class to generate images online
public class ImageLoader {
	public BufferedImage player, background, enemy;
	
	//Constructor of the class
	public ImageLoader(Game game) {
		getImage();
	}
	
	//Load method to load buffered images
	public BufferedImage loadImage(String url) {
		BufferedImage loader = null;
		try {
			loader = ImageIO.read(new URL(url));
		} catch (MalformedURLException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return loader;
	}
	
	//Get the images online
	public void getImage() {
		player = loadImage("https://dashboard.snapcraft.io/site_media/appmedia/2019/03/icon_3mrgAxU.png");
		background = loadImage("https://media.indiumgames.com/medialibrary/2014/07/MakingMap1.png");
		enemy = loadImage("https://static.wikia.nocookie.net/fantendo/images/b/b9/SMMUSMBMushroom.png/revision/latest/scale-to-width-down/340?cb=20160701183739");
	}
}
