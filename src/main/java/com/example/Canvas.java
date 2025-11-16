package com.example;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

//@SuppressWarnings("unused")
public class Canvas extends JPanel {
  private int X1, Y1, X2, Y2;
  private Graphics2D g;
  private Image img, background;// :NOTE: I don't have a know how about this -> undoTemp, redoTemp;

  public Canvas() {
    // this.setBackground(Color.WHITE);
    this.setBackground(new Color(50, 50, 50));
    this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    System.out.println("[INFO] Canvas working...");
  }

  public void saveFile(File file) {
    try {
      ImageIO.write((RenderedImage) img, "PNG", file);
    } catch (IOException ex) {
      System.err.println(
          "[ERROR] Could not save file\n Please retry again later or close the application and retry saving");
    }
  }

  private BufferedImage copyImage(Image img) {
    BufferedImage copyOfImage = new BufferedImage(getSize().width,
        getSize().height, BufferedImage.TYPE_INT_RGB);
    Graphics g = copyOfImage.createGraphics();
    g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    return copyOfImage;
  }

  public void openFile(File file) {
    try {
      img = ImageIO.read(file);
      g = (Graphics2D) img.getGraphics();
    } catch (IOException ex) {
      System.err.println("[ERROR] Could not open file\n Please try again later or open a different file");
    }
  }

  private void setImage(Image img) {
    g = (Graphics2D) img.getGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    g.setPaint(Color.black);
    this.img = img;
    repaint();
  }

  public void clear() {
    if (background != null) {
      setImage(copyImage(background));
    } else {
      g.setPaint(Color.white);
      g.fillRect(0, 0, getSize().width, getSize().height);
      g.setPaint(Color.black);
    }
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

  public void undo() {
    // :TODO: Implement functionality
  }

  public void redo() {
    // :TODO: Implement functionality
  }

}
