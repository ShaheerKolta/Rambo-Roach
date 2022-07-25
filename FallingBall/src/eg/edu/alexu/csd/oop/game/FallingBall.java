package eg.edu.alexu.csd.oop.game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Mohamed Abdelrehim on 5/15/2017.
 */
public class FallingBall implements GameObject {

  protected int y, x;
  protected BufferedImage[] spriteImages;
  protected static final int SPRITE_WIDTH = 40;
  protected boolean visible;

  public FallingBall(BufferedImage[] image) {
    //take Buffered image in constructor for reuse
    this.spriteImages = image;
    this.visible  = true;
    this.x = 0;
    this.y = 0;
  }

  public static BufferedImage[] draw(Color color) {
    int j;
    BufferedImage[] img = new BufferedImage[1];
    img[0] = new BufferedImage(SPRITE_WIDTH, SPRITE_WIDTH,	BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = img[0].createGraphics();
    g2.setColor(color);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    j = Math.abs(img.length/2);
    double theta = j * Math.PI / (2 * img.length);
    double x = SPRITE_WIDTH * Math.abs(Math.cos(theta)) / 2.0;
    double y = SPRITE_WIDTH * Math.abs(Math.sin(theta)) / 2.0;
    int x1 = (int) ((SPRITE_WIDTH / 2.0) - x);
    int y1 = (int) ((SPRITE_WIDTH / 2.0) - y);
    int x2 = (int) ((SPRITE_WIDTH / 2.0) + x);
    int y2 = (int) ((SPRITE_WIDTH / 2.0) + y);
    g2.setStroke(new BasicStroke(5));
    g2.fillOval(x1+2, y1+2, x2-4, y2-4);
    g2.dispose();
    return img;
  }
  public static void main(String[] args) {

  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public void setX(int mX) {
    this.x = mX + (int)Math.random()*500;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public void setY(int mY) {
    this.y = mY + (int)Math.random()*5;
  }

  @Override
  public BufferedImage[] getSpriteImages() {
    return spriteImages;
  }

  @Override
  public int getWidth(){
    return SPRITE_WIDTH;
  }

  @Override
  public int getHeight() {
    return SPRITE_WIDTH;
  }

  @Override
  public boolean isVisible() {
    return visible;
  }


}
