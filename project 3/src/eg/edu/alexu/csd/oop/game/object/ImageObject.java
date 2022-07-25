package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

/**
 * Created by Mohamed Abdelrehim on 5/9/2017.
 */
public abstract class ImageObject extends Observable implements GameObject{

  private BufferedImage[] spriteImages = new BufferedImage[1];
  public static final int ROACH_TYPE = 0;
  public static final int BIN_TYPE = 1;
  private int x;
  private int y;
  private boolean visible;
  private int type;
  private int leftMargin;
  private int rightMargin;

  public ImageObject (int posX, int posY, int type, String path, int left, int right) {

    this.leftMargin = left;
    this.rightMargin = right;
    this.x = posX;
    this.y = posY;
    this.type = type;
    this.visible = true;
    // create a bunch of buffered images and place into an array, to be displayed sequentially
    try {
      spriteImages[0] = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  public void setMargins(int left, int right) {
    this.leftMargin = left;
    this.rightMargin = right;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public void setX(int x) {
    if (x > leftMargin && x < rightMargin) {
      this.x = x;
    }
    else {
      return;
    }
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public void setY(int y) {
    this.y = y;
  }

  @Override
  public int getWidth() {
    return spriteImages[0].getWidth();
  }

  @Override
  public int getHeight() {
    return spriteImages[0].getHeight();
  }

  @Override
  public boolean isVisible() {
    return this.visible;
  }

  @Override
  public BufferedImage[] getSpriteImages() {
    return spriteImages;
  }

  public int getType() {
    return this.type;
  }

}
