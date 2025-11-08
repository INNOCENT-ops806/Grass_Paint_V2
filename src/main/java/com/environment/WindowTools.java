package com.environment;

public class WindowTools {

  public WindowTools() {
  }

  public static int GetScreenHeight() {
    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
  }

  public static int GetScreenWidth() {
    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
  }

}
