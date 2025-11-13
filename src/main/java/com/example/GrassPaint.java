package com.example;

import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatDarkLaf;

public class GrassPaint {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        FlatDarkLaf.setup();
      } catch (Exception e) {
        e.printStackTrace();
      }
      new Draw();
    });
  }
}
