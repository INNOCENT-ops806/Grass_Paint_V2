package com.example;

import com.environment.WindowTools;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
//import java.awt.Image;
//import java.awt.Point;
//import java.awt.Toolkit;//:NOTE: Used for custom cursor creation
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFileChooser;

//@SuppressWarnings("unused")
public class Draw extends JFrame implements ActionListener {
  private int saveCounter;
  private Canvas canvas;
  private JLabel filenameBar, thicknessStat;// :NOTE: not done here!
  private JFileChooser fileChooser;
  private File file;
  private Color color = Color.WHITE;
  private JButton pencil, eraser, color_picker, gray, red, pink, black, green, white, yellow, blue;
  private JSlider slider;
  private JMenuItem open;

  public Draw() {
    super("GrassPaint");
    // :NOTE: Global Variables initialization
    canvas = new Canvas();
    filenameBar = new JLabel("No file");
    thicknessStat = new JLabel();

    // :NOTE: Frame stuff
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setSize(900, 600);
    setEnvironmentalDecoration();
    this.setJMenuBar(menuBar());
    this.setLayout(new BorderLayout());
    this.add(equipmentPanel(), BorderLayout.NORTH);
    this.add(canvas, BorderLayout.CENTER);
    statusBarPanel().setVisible(true);
    this.add(statusBarPanel(), BorderLayout.SOUTH);
    this.add(sizePanel(), BorderLayout.WEST);
    this.setVisible(true);
    System.out.println("[INFO] Draw working...");
  }

  private List<JButton> addBtn_toEquipment() {
    List<JButton> buttons = new ArrayList<>();

    Icon pencilIcon = new ImageIcon(getClass().getResource("/icons/pencil.png"));
    pencil = new JButton(pencilIcon);
    pencil.setToolTipText("Pencil");
    pencil.setPreferredSize(new Dimension(48, 48));
    pencil.addActionListener(this);
    buttons.add(pencil);

    Icon eraserIcon = new ImageIcon(getClass().getResource("/icons/eraser.png"));
    eraser = new JButton(eraserIcon);
    eraser.setToolTipText("Eraser");
    eraser.setPreferredSize(new Dimension(48, 48));
    eraser.addActionListener(this);
    buttons.add(eraser);

    red = new JButton();
    red.setToolTipText("red");
    red.setPreferredSize(new Dimension(48, 48));
    red.setBackground(Color.RED);
    red.addActionListener(this);
    buttons.add(red);

    pink = new JButton();
    pink.setToolTipText("pink");
    pink.setPreferredSize(new Dimension(48, 48));
    pink.setBackground(Color.PINK);
    pink.addActionListener(this);
    buttons.add(pink);

    black = new JButton();
    black.setToolTipText("black");
    black.setPreferredSize(new Dimension(48, 48));
    black.setBackground(Color.BLACK);
    black.addActionListener(this);
    buttons.add(black);

    green = new JButton();
    green.setToolTipText("green");
    green.setPreferredSize(new Dimension(48, 48));
    green.setBackground(Color.GREEN);
    green.addActionListener(this);
    buttons.add(green);

    white = new JButton();
    white.setToolTipText("white");
    white.setPreferredSize(new Dimension(48, 48));
    white.setBackground(Color.WHITE);
    white.addActionListener(this);
    buttons.add(white);

    yellow = new JButton();
    yellow.setToolTipText("yellow");
    yellow.setPreferredSize(new Dimension(48, 48));
    yellow.setBackground(Color.YELLOW);
    yellow.addActionListener(this);
    buttons.add(yellow);

    blue = new JButton();
    blue.setToolTipText("blue");
    blue.setPreferredSize(new Dimension(48, 48));
    blue.setBackground(Color.BLUE);
    blue.addActionListener(this);
    buttons.add(blue);

    gray = new JButton();
    gray.setToolTipText("gray");
    gray.setPreferredSize(new Dimension(48, 48));
    gray.setBackground(Color.GRAY);
    gray.addActionListener(this);
    buttons.add(gray);

    Icon color_ChooserIcon = new ImageIcon(getClass().getResource("/icons/color-chooser.png"));
    color_picker = new JButton(color_ChooserIcon);
    color_picker.setToolTipText("Color Picker");
    color_picker.setPreferredSize(new Dimension(48, 48));
    color_picker.addActionListener(this);
    buttons.add(color_picker);

    System.out.println("[INFO] addBtn_toEquipment working...");
    return buttons;
  }

  ChangeListener thick = new ChangeListener() {
    public void stateChanged(ChangeEvent e) {
      thicknessStat.setText(String.format("%s",
          slider.getValue()));
      canvas.setThickness(slider.getValue());
    }
  };

  private JPanel sizePanel() {
    JPanel sizePnl = new JPanel();
    sizePnl.add(new JSeparator(JSeparator.VERTICAL));
    sizePnl.setPreferredSize(new Dimension(25, WindowTools.GetScreenHeight()));
    slider = new JSlider(JSlider.VERTICAL, 0, 100, 0);
    slider.addChangeListener(thick);
    slider
        .setPreferredSize(new Dimension(WindowTools.GetScreenHeight(), (int) (WindowTools.GetScreenHeight() * 0.70)));
    slider.setVisible(true);
    thicknessStat.setText(String.format("%s", slider.getValue()));
    sizePnl.add(slider);
    System.out.println("[INFO] sizePanel working...");
    return sizePnl;
  }

  private JPanel statusBarPanel() {
    JPanel statusPnl = new JPanel(new FlowLayout());
    statusPnl.add(filenameBar);
    return statusPnl;
  }

  private JPanel equipmentPanel() {
    JPanel equipment = new JPanel();
    for (JButton btns : addBtn_toEquipment()) {
      equipment.add(btns);
    }
    System.out.println("[INFO] EquipmentPanel working...");
    return equipment;
  }

  private void setEnvironmentalDecoration() {
    if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
      System.setProperty("flatlaf.menuBarEmbedded", "false");
      System.out.println("[SYSTEM] Linux");
    } else {
      System.out.println("[SYSTEM] Windows");
      System.setProperty("flatlaf.menuBarEmbedded", "true");
    }
    System.out.println("[INFO] SetEnvironmentalDecoration working...");
  }

  private JMenuBar menuBar() {
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(fileMenu());
    menuBar.add(editMenu());
    menuBar.add(helpMenu());
    System.out.println("[INFO] MenuBar working...");
    return menuBar;
  }

  private JMenu helpMenu() {
    JMenu helpMenu = new JMenu("Help");
    Icon licenseIcon = new ImageIcon(getClass().getResource("/icons/license.png"));
    Icon aboutIcon = new ImageIcon(getClass().getResource("/icons/about.png"));

    JMenuItem license = new JMenuItem("license", licenseIcon);
    license.addActionListener(this);
    helpMenu.add(license);
    helpMenu.addSeparator();

    JMenuItem about = new JMenuItem("about", aboutIcon);
    about.addActionListener(this);
    helpMenu.add(about);
    System.out.println("[INFO] HelpMenu working...");
    return helpMenu;
  }

  private JMenu editMenu() {
    JMenu editMenu = new JMenu("Edit");
    Icon undoIcon = new ImageIcon(getClass().getResource("/icons/undo.png"));
    Icon redoIcon = new ImageIcon(getClass().getResource("/icons/redo.png"));

    JMenuItem undo = new JMenuItem("undo", undoIcon);
    undo.addActionListener(this);
    editMenu.add(undo);

    JMenuItem redo = new JMenuItem("redo", redoIcon);
    redo.addActionListener(this);
    editMenu.add(redo);
    System.out.println("[INFO] EditMenu working...");
    return editMenu;
  }

  private JMenu fileMenu() {
    Icon saveIcon = new ImageIcon(getClass().getResource("/icons/save.png"));
    // Icon save_asIcon = new
    // ImageIcon(getClass().getResource("/icons/save-as.png"));
    Icon openIcon = new ImageIcon(getClass().getResource("/icons/new-window.png"));
    Icon exitIcon = new ImageIcon(getClass().getResource("/icons/exit.png"));

    JMenu fileMenu = new JMenu("File");

    open = new JMenuItem("Open File", openIcon);
    open.addActionListener(this);
    fileMenu.add(open);

    JMenuItem saveFile = new JMenuItem("Save File", saveIcon);
    saveFile.addActionListener(this);
    fileMenu.add(saveFile);

    /*
     * :NOTE: should be added when other image format are supported
     * JMenuItem save_as = new JMenuItem("Save As", save_asIcon);
     * save_as.addActionListener(this);
     * fileMenu.add(save_as);
     */

    fileMenu.addSeparator();
    JMenuItem exit = new JMenuItem("Exit", exitIcon);
    exit.addActionListener(this);
    fileMenu.add(exit);

    System.out.println("[INFO] FileMenu working...");
    return fileMenu;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Exit")) {
      System.out.println("[ACTION] Exit pressed...");
      System.exit(0);
    } else if (e.getActionCommand().equals("Save File")) {
      System.out.println("Save pressed...");
      if (saveCounter == 0) {
        fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          file = fileChooser.getSelectedFile();
          saveCounter = 1;
          filenameBar.setText(file.toString());
          canvas.saveFile(file);
        }
      } else {
        filenameBar.setText(file.toString());
        canvas.saveFile(file);
      }
    } else if (e.getSource() == open) {
      System.out.println("[ACTION] Open pressed...");
      if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
        fileChooser = new JFileChooser("~/");
      } else {
        fileChooser = new JFileChooser("C:\\");
      }
      fileChooser.setFileFilter(FileFilter.png);
      fileChooser = new JFileChooser();
      if (fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
        file = fileChooser.getSelectedFile();
        canvas.openFile(file);
        filenameBar.setText(file.toString());
      } else {
        System.out.println("[INFO] User cancelled opening file");
      }
    } else if (e.getActionCommand().equals("undo")) {
      System.out.println("[ACTION] Undo pressed...");
      canvas.undo();
    } else if (e.getActionCommand().equals("redo")) {
      System.out.println("[ACTION] Redo pressed...");
      canvas.redo();
    } else if (e.getActionCommand().equals("license")) {
      System.out.println("[ACTION] license pressed...");
    } else if (e.getActionCommand().equals("about")) {
      System.out.println("[ACTION] about pressed...");
    } else if (e.getSource() == pencil) {
      System.out.println("[ACTION] pencil pressed...");
      canvas.pencil();
    } else if (e.getSource() == eraser) {
      /*
       * :TODO: change the defaultCursor to custom eraser cursor.
       *
       * =================================================
       * The commented out code need complex implementation to check where the cursor
       * is in order to
       * continue using the custom cursor
       * =================================================
       *
       * Toolkit toolkit = Toolkit.getDefaultToolkit();
       * Image image = toolkit.getImage(getClass().getResource("/icons/eraser.png"));
       * Cursor cursor = toolkit.createCustomCursor(image, new Point(0, 0), "img");
       * this.setCursor(cursor);
       */
      System.out.println("[ACTION] eraser pressed...");
    } else if (e.getSource() == color_picker) {
      System.out.println("[ACTION] color_picker pressed...");
      color = JColorChooser.showDialog(canvas, "Pick your color!", color);
      if (color == null) {
        color = (Color.BLACK);
      }
      canvas.chooser(color);
    }

    // :NOTE: Color buttons
    else if (e.getSource() == red) {
      canvas.red();
    } else if (e.getSource() == black) {
      canvas.black();
    } else if (e.getSource() == blue) {
      canvas.blue();
    } else if (e.getSource() == pink) {
      canvas.pink();
    } else if (e.getSource() == green) {
      canvas.green();
    } else if (e.getSource() == yellow) {
      canvas.yellow();
    } else if (e.getSource() == white) {
      canvas.white();
    } else if (e.getSource() == gray) {
      canvas.gray();
    }
  }
}
