package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.object.MediumBin;

import java.util.Iterator;

/**
 * Created by Mohamed Abdelrehim on 5/19/2017.
 */
public class MediumState extends State {

  public MediumState(MyWorld world) {
    super(world);
    this.speed = 3;
    this.controlSpeed = 20;
    this.leftBin = new MediumBin((width/3) - 90, (int)(this.height*0.8) + 35, 1,
        0, width - 350);
    this.rightBin = new MediumBin((width/3) + 194, (int)(this.height*0.8) + 35, 1,
        280, width);
    controllable.add(leftBin);
    controllable.add(rightBin);
    controllable.add(roach);
    leftBin.addObserver(this);
    rightBin.addObserver(this);
  }

}
