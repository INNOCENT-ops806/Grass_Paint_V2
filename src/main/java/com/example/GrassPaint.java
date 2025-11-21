package com.example;

import javax.swing.SwingUtilities;
import com.formdev.flatlaf.FlatLightLaf;

public class GrassPaint {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        FlatLightLaf.setup();
      } catch (Exception e) {
        e.printStackTrace();
      }
      new Draw();
    });
  }
}
