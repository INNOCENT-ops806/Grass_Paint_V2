package com.example;

import com.environment.WindowTools;
import javax.swing.JFrame;

public class Draw extends JFrame {
  public Draw() {
    super("Grass_Paint");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WindowTools.GetScreenWidth(), WindowTools.GetScreenHeight());
    this.setVisible(true);
  }

}
