package eg.edu.alexu.csd.oop.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

  public static void main(String[] args) {
    JMenuBar menuBar = new JMenuBar();;
    JMenu menu = new JMenu("File");
    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem pauseMenuItem = new JMenuItem("Pause");
    JMenuItem resumeMenuItem = new JMenuItem("Resume");
    menu.add(newMenuItem);
    menu.addSeparator();
    menu.add(pauseMenuItem);
    menu.add(resumeMenuItem);
    menuBar.add(menu);
    final GameEngine.GameController gameController = GameEngine.start("Cockroach Hero",
        eg.edu.alexu.csd.oop.game.world.MyWorld.getInstance(), menuBar, Color.DARK_GRAY);
    newMenuItem.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        gameController.changeWorld(eg.edu.alexu.csd.oop.game.world.MyWorld.getInstance());
      }
    });
    pauseMenuItem.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        gameController.pause();
      }
    });
    resumeMenuItem.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        gameController.resume();
      }
    });
  }
}


