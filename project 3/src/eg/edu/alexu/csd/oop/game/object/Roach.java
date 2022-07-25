package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;


/**
 * Created by Mohamed Abdelrehim on 5/8/2017.
 */
public class Roach extends ImageObject implements GameObject {

  public Roach (int posX, int posY, int type, int leftMargin, int rightMargin) {
    super(posX, posY, type, ".\\resources\\roach_hero.png", leftMargin, rightMargin);
  }

  @Override
  public void setY(int y) {

  }

}
