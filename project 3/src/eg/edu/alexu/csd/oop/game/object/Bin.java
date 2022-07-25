package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Abdelrehim on 5/8/2017.
 */
public abstract class Bin extends ImageObject{

  protected List<GameObject> shapeStack;
  protected int MAX_SIZE;
  protected int consecutiveCount = 1;
  protected List<Integer> consecutiveCountStack;

  public Bin(int posX, int posY, int type, int leftMargin, int rightMargin, int stackSize) {
    super(posX, posY, type, ".\\resources\\bin.png", leftMargin, rightMargin);
    this.shapeStack = new ArrayList<>();
    this.MAX_SIZE = stackSize;
    this.consecutiveCountStack = new ArrayList<>();
    consecutiveCountStack.add(1);
  }

  public void removeLastThree() {
    System.out.println("shapeStack size" + shapeStack.size());
    shapeStack.remove(shapeStack.size() - 1);
    shapeStack.remove(shapeStack.size() - 1);
    shapeStack.remove(shapeStack.size() - 1);
  }

  /*public List<GameObject> getShapeStack() {
    return this.shapeStack;
  }*/

  public void addToBin(GameObject input) {
    if (shapeStack.isEmpty()) {
      System.out.println("ball inside");
      shapeStack.add(input);
    }

    else {
      shapeStack.add(input);
      System.out.println("ball inside");
      Color in = FallingObjectsFactory.getColor(input);

      if (shapeStack.size() > 1) {
        Color top = FallingObjectsFactory.getColor(shapeStack.get(shapeStack.size() - 2));

        if (in.equals(top)) {
          int x = consecutiveCountStack.get(consecutiveCountStack.size() - 1);
          consecutiveCountStack.remove(consecutiveCountStack.size() - 1);
          consecutiveCountStack.add(++x);
          consecutiveCount++;
        }
        else {
          consecutiveCountStack.add(1);
        }

      }


      if (consecutiveCountStack.get(consecutiveCountStack.size() - 1) == 3) {
        setChanged();
        notifyObservers("threeConsecutive");
        System.out.println("three consecutive");
        clearChanged();
        consecutiveCountStack.remove(consecutiveCountStack.size() - 1);
        consecutiveCount = 0;
      }


      if (shapeStack.size() > MAX_SIZE) {
        setChanged();
        System.out.println("maxSize");
        notifyObservers("maxSize");
        clearChanged();
      }

    }

  }
  @Override
  public void setY(int y) {

  }

}
