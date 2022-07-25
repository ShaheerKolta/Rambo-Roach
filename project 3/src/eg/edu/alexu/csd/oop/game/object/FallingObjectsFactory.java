package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.List;

 /* Created by Mohamed Abdelrehim on 5/13/2017.*/

// factory + flyweight
public class FallingObjectsFactory {

  private static HashMap<BufferedImage, Color> colorMap = new HashMap<>();
  private static HashMap<Color, GameObject> ballMap = new HashMap<>();
  private static HashMap<Color, GameObject> rectMap = new HashMap<>();
  public static int num = 0;

  public static GameObject getFlyingObject(Color color) {

    List<Class> importedClasses = getClasses(".\\resources\\FallingBall.jar",
        ".\\resources\\FallingRect.jar");
    Class FallingBall = importedClasses.get(0);
    Class FallingRect = importedClasses.get(1);

    try {
      Constructor constructorBall = FallingBall.getDeclaredConstructor(BufferedImage[].class);
      Constructor constructorRect = FallingRect.getDeclaredConstructor(BufferedImage[].class);
      Method drawBall = FallingBall.getDeclaredMethod("draw", Color.class);
      Method drawRect = FallingRect.getDeclaredMethod("draw", Color.class);

      if (num%3 == 0) {
        GameObject ball =  ballMap.get(color);
        GameObject retVal;
        if (ball == null) {
          retVal = (GameObject)constructorBall.newInstance(drawBall.invoke(null, color));
          ballMap.put(color, retVal);
          colorMap.put(retVal.getSpriteImages()[0], color);
          //System.out.println("creating");
        } else {
          BufferedImage[] x = ball.getSpriteImages();
          retVal = (GameObject)constructorBall.newInstance(new Object[]{x});
          colorMap.put(retVal.getSpriteImages()[0], color);
          //System.out.println("reusing");
        }
        num ++;
        return retVal;
      }
      else {
        GameObject rect =  rectMap.get(color);
        GameObject retVal;
        if (rect == null) {
          retVal = (GameObject)constructorRect.newInstance(drawRect.invoke(null, color));
          rectMap.put(color, retVal);
          colorMap.put(retVal.getSpriteImages()[0], color);
          //System.out.println("creating");
        } else {
          BufferedImage[] x;
          x = rect.getSpriteImages();
          retVal = (GameObject)constructorRect.newInstance(new Object[]{x});
          colorMap.put(retVal.getSpriteImages()[0], color);
          //System.out.println("reusing --");
        }
        num ++;
        return retVal;
      }

    } catch (NoSuchMethodException e) {
      System.out.println("method not found");
      e.printStackTrace();
      return null;
    } catch (IllegalAccessException e) {
      System.out.println("Illegal access");
      e.printStackTrace();
      return null;
    } catch (InstantiationException e) {
      System.out.println("bad instantiation");
      e.printStackTrace();
      return null;
    } catch (InvocationTargetException e) {
      System.out.println("bad instantiation");
      e.printStackTrace();
      return null;
    }

  }

  public static Color getColor(GameObject subject) {
    Color retVal = colorMap.get(subject.getSpriteImages()[0]);
    if (retVal == null) {
      System.out.println("item not found in colorMap");
      return null;
    }
    else {
      return retVal;
    }
  }

  private static List<Class> getClasses (String pathFallingBall, String pathFallingRect) {
    List importedClasses = new ArrayList<>();
    List<URL> urlOne = new ArrayList<>();
    List<URL> urlTwo = new ArrayList<>();
    File fileOne = new File(pathFallingBall);
    File fileTwo = new File(pathFallingRect);
    try {
      urlOne.add(fileOne.toURI().toURL());
      urlTwo.add(fileTwo.toURI().toURL());
      String classOneName = "eg.edu.alexu.csd.oop.game.FallingBall";
      String classTwoName = "eg.edu.alexu.csd.oop.game.FallingRect";
      URLClassLoader classLoaderOne = new URLClassLoader(urlOne.toArray(new URL[urlOne.size()]));
      URLClassLoader classLoaderTwo = new URLClassLoader(urlTwo.toArray(new URL[urlTwo.size()]));
      Class classOne = classLoaderOne.loadClass(classOneName);
      Class classTwo = classLoaderTwo.loadClass(classTwoName);
      importedClasses.add(classOne);
      importedClasses.add(classTwo);
    } catch (MalformedURLException e) {
      System.out.println("bad url");
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.out.println("class not found");
      e.printStackTrace();
    }

    return importedClasses;
  }

}


