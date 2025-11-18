package com.example;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
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
  private Image img, undoTemp, redoTemp;
  private final Custom_Stack<Image> undoStack = new Custom_Stack<>(10);
  private final Custom_Stack<Image> redoStack = new Custom_Stack<>(10);
  private MouseListener listener;
  private MouseMotionAdapter motion;

  public Canvas() {
    this.setBackground(Color.WHITE);
    defaultListener();
    this.setBackground(new Color(250, 250, 250));
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

  private void saveToStack(Image img) {
    undoStack.push(copyImage(img));
  }

  public void defaultListener() {
    setDoubleBuffered(false);
    listener = new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        saveToStack(img);
        X2 = e.getX();
        Y2 = e.getY();
      }
    };
    motion = new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        X1 = e.getX();
        Y1 = e.getY();

        if (g != null) {
          g.drawLine(X2, Y2, X1, Y1);
          repaint();
          X2 = X1;
          Y2 = Y1;
        }
      }
    };
    addMouseListener(listener);
    addMouseMotionListener(motion);
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

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

  public void undo() {
    if (undoStack.size() > 0) {
      undoTemp = undoStack.pop();
      redoStack.push(img);
      setImage(undoTemp);
    }
  }

  public void redo() {
    if (redoStack.size() > 0) {
      redoTemp = redoStack.pop();
      undoStack.push(img);
      setImage(redoTemp);
    }
  }

  public void pencil() {
  }

}
