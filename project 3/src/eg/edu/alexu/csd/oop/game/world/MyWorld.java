package eg.edu.alexu.csd.oop.game.world;


import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Mohamed Abdelrehim on 5/17/2017.
 */
public class MyWorld implements World {

  private State currentState;
  private State EASY;
  private State MEDIUM;
  private State HARD;
  private State GAME_OVER;

  private AudioStream BGM;


  private MyWorld() {

    // background Music
    /*try {
      InputStream music = new FileInputStream(".\\resources\\cool_music.wav");
      this.BGM = new AudioStream(music);
      AudioPlayer.player.start(BGM);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }*/
    this.EASY = new EasyState(ourInstance);
    this.MEDIUM = new MediumState(ourInstance);
    this.HARD = new HardState(ourInstance);

    /*this.GAME_OVER = new GameOverState(ourInstance);
    this.TRANSITION = new TransitionState(ourInstance);*/
    setCurrentState(this.HARD);
  }
  private static MyWorld ourInstance = new MyWorld();

  @Override
  public List<GameObject> getConstantObjects() {
    return currentState.getConstantObjects();
  }

  @Override
  public List<GameObject> getMovableObjects() {
    return currentState.getMovableObjects();
  }

  @Override
  public List<GameObject> getControlableObjects() {
    return currentState.getControlableObjects();
  }

  @Override
  public int getWidth() {
    return currentState.getWidth();
  }

  @Override
  public int getHeight() {
    return currentState.getHeight();
  }

  @Override
  public boolean refresh() {
    return currentState.refresh();
  }

  @Override
  public String getStatus() {
    return currentState.getStatus();
  }

  @Override
  public int getSpeed() {
    return currentState.getSpeed();
  }

  @Override
  public int getControlSpeed() {
    return currentState.getControlSpeed();
  }

  public State getCurrentState() {
    return currentState;
  }

  public State getEASY() {
    return EASY;
  }

  public State getMEDIUM() {
    return MEDIUM;
  }

  public State getHARD() {
    return HARD;
  }

  public State getGAME_OVER() {
    return GAME_OVER;
  }


  public void setCurrentState(State currentState) {
    this.currentState = currentState;
  }

  public static MyWorld getInstance() {
    return ourInstance;
  }

}
