package com.environment;

public class WindowTools {

  public WindowTools() {
  }

  public static int GetScreenHeight() {
    System.out.println("[INFO] WindowTools.GetScreenHeight working...");
    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
  }

  public static int GetScreenWidth() {
    System.out.println("[INFO] WindowTools.GetScreenWidth working...");
    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
  }

}
