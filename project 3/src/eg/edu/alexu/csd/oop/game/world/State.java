package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.object.Bin;
import eg.edu.alexu.csd.oop.game.object.FallingObjectsFactory;
import eg.edu.alexu.csd.oop.game.object.Roach;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Mohamed Abdelrehim on 5/17/2017.
 */
public abstract class State implements Observer, World {

  MyWorld myWorld;
  protected static int createCounter;
  protected static int score;
  protected static int num;
  protected static int count;
  protected int width;
  protected int height;
  protected int speed;
  protected int controlSpeed;
  protected List<GameObject> controllable;
  protected List<GameObject> movable;
  protected List<GameObject> constant;
  protected List<GameObject> intersecting;
  protected Roach roach;
  protected Bin leftBin;
  protected Bin rightBin;
  protected Color[] availableColors = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE,
      Color.MAGENTA, Color.PINK};


  public State(MyWorld world) {
    this.intersecting = new ArrayList<>();
    this.myWorld = world;
    this.movable = new LinkedList<>();
    this.num = 0;
    this.count = 0;
    this.constant = new LinkedList<>();
    this.controllable = new LinkedList<>();
    this.width = 1500;
    this.height = 900;
    this.createCounter = 0;
    this.score = 0;
    this.roach = new Roach(width/3, (int)(this.height*0.775), 0, 94, width - 250);
  }
  protected Color getRandomColor() {
    return availableColors[(int)(Math.random()*availableColors.length)];
  }

 /* @Override
  public abstract boolean refresh();*/

  @Override
  public String getStatus() {
    return "Score:  " + this.score;
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public int getControlSpeed() {
    return this.controlSpeed;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public  List<GameObject> getConstantObjects() {
    return this.constant;
  }

  @Override
  public List<GameObject> getMovableObjects() {
    return this.movable;
  }

  @Override
  public List<GameObject> getControlableObjects(){
    return this.controllable;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public boolean refresh() {

    if (num < 20 && createCounter % 60 == 0) {
      GameObject x = FallingObjectsFactory.getFlyingObject(getRandomColor());
      x.setY(110);
      if (count%2 == 0) {
        x.setX(0);
      } else {
        x.setX(width);
      }
      movable.add(x);
      num ++;
      count++;
    }
    createCounter++;
    //TODO if intersect num --

    if (!intersecting.isEmpty()) {
      System.out.println("decreasing movable size");
      Iterator<GameObject> iterator = intersecting.iterator();
      while (iterator.hasNext()) {
        movable.remove(iterator.next());
        num--;
      }
    }
    intersecting.clear();
    Iterator<GameObject> iterator = movable.iterator();
    while (iterator.hasNext()) {
      GameObject current = iterator.next();
      int xPos = current.getX();
      if (xPos > width*0.7) {
        current.setX(current.getX() - 1);
      } else if (xPos < width - width*0.7){
        current.setX((current.getX() + 1));
      } else {
        current.setY((current.getY() + 1));
      }
      if (current.getY() > height) {
        if (count % 2 == 0) {
          current.setX(0 - 100);
        } else {
          current.setX(width + 100);
        }
        current.setY(110);
        count ++;
      }
      if(intersect(current, leftBin) || intersect(current, rightBin)) {
        if (intersect(current, leftBin)) {
          current.setY(height - (constant.size() + 1)*60);
          current.setX(0);
          constant.add(current);
          leftBin.addToBin(current);
          intersecting.add(current);
        }
        if (intersect(current, rightBin)) {
          current.setY(height - (constant.size() + 1) *60);
          current.setX(width - 60);
          constant.add(current);
          rightBin.addToBin(current);
          intersecting.add(current);
        }

        break;
      }


    }

    return true;
  }

  @Override
  public void update(Observable o, Object arg) {
    if (o.equals(this.leftBin)) {
      if (arg.equals("threeConsecutive")) {
        System.out.println("notification received");
        this.score ++;
        System.out.println(this.score);
        leftBin.removeLastThree();
        for (int i = 0; i < 3; i++) {
          constant.remove(constant.size() - 1);
        }
      }
    }

    if (o.equals(this.rightBin)) {
      if (arg.equals("threeConsecutive")) {
        System.out.println("notification received");
        this.score ++;
        System.out.println("" + this.score);
        rightBin.removeLastThree();
        for (int i = 0; i < 3; i++) {
          constant.remove(constant.size() - 1);
        }
      }
    }

    /*if (arg.equals("maxSize")) {
      myWorld.setCurrentState(myWorld.getGAME_OVER());
    }*/
  }

  protected boolean intersect(GameObject o1, GameObject o2){
    return (Math.abs((o1.getX()+o1.getWidth()/2) - (o2.getX()+o2.getWidth()/2)) <= o1.getWidth()) &&
        (Math.abs((o1.getY()+o1.getHeight()/2) - (o2.getY()+o2.getHeight()/12)) <= o1.getHeight());
  }

}
