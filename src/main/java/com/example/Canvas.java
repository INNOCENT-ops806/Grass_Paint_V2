package com.example;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

//@SuppressWarnings("unused")
public class Canvas extends JPanel {
  private int X1, Y1, X2, Y2;
  private Graphics2D g;
  private Image img, background, undoTemp, redoTemp;
  private final Custom_Stack<Image> undoStack = new Custom_Stack<>(10);
  private final Custom_Stack<Image> redoStack = new Custom_Stack<>(10);
  private MouseListener listener;
  private Rectangle shape;
  private MouseMotionAdapter motion;

  public Canvas() {
    defaultListener();
    setBackground(Color.WHITE);
    this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    this.setPreferredSize(new Dimension(900, 600));
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
      Image loadedImage = ImageIO.read(file);
      this.img = loadedImage;
      g = (Graphics2D) img.getGraphics();
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      super.repaint();
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

  public void paintComponent(Graphics g1) {
    // :NOTE: If there is no img buffered or it gets resized, create one
    if (img == null || img.getWidth(this) != getSize().width || img.getHeight(this) != getSize().height) {
      img = createImage(getSize().width, getSize().height);
      g = (Graphics2D) img.getGraphics();
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);
      shape = new Rectangle(0, 0, getSize().width - 1, getSize().height - 1);
    }
    g1.drawImage(img, 0, 0, null);
    if (shape != null) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.draw(shape);
    }
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
    removeMouseListener(listener);
    removeMouseMotionListener(motion);
    defaultListener();
  }

  public void chooser(Color color) {
    g.setPaint(color);
  }

  public void setBackground(Image img) {
    this.background = copyImage(img);
    setImage(copyImage(img));
  }

  public void setThickness(int thick) {
    g.setStroke(new BasicStroke(thick));
  }

  public void red() {
    g.setPaint(Color.RED);
  }

  public void pink() {
    g.setPaint(Color.PINK);
  }

  public void blue() {
    g.setPaint(Color.BLUE);
  }

  public void gray() {
    g.setPaint(Color.RED);
  }

  public void green() {
    g.setPaint(Color.GREEN);
  }

  public void white() {
    g.setPaint(Color.WHITE);
  }

  public void yellow() {
    g.setPaint(Color.YELLOW);
  }

  public void black() {
    g.setPaint(Color.BLACK);
  }
}
