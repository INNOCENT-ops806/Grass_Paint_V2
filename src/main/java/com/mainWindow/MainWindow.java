package com.mainWindow;

import com.menu.*;
import com.formdev.flatlaf.*;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

public final class MainWindow extends JFrame {

  public MainWindow() {
    super("Grass Paint");
    int width = 900, height = 600;
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Failed to initialize flatlaf:");
    }
    JMenuBar menu_bar = new JMenuBar();
    this.setJMenuBar(menu_bar);
    Menus.addMenus(menu_bar);

    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setSize(width, height);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

}
