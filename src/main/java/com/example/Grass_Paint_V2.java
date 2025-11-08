package com.example;

import javax.swing.SwingUtilities;

public class Grass_Paint_V2 {
  public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {
      new Draw();
    });
  }
}
