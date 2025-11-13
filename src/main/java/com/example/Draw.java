package com.example;

import com.environment.WindowTools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;

@SuppressWarnings("unused")
public class Draw extends JFrame implements ActionListener {

  public Draw() {
    super("Grass_Paint");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WindowTools.GetScreenWidth(), WindowTools.GetScreenHeight());
    setEnvironmentalDecoration();
    this.setJMenuBar(menuBar());
    this.setLayout(new BorderLayout());
    this.add(equipmentPanel(), BorderLayout.NORTH);
    // this.add(sizePanel(), BorderLayout.WEST);
    System.out.println("[INFO] Draw working...");
    this.setVisible(true);
  }

  private List<JButton> addBtn_toEquipment() {
    List<JButton> buttons = new ArrayList<>();

    Icon pencilIcon = new ImageIcon(getClass().getResource("/icons/pencil.png"));
    JButton pencil = new JButton(pencilIcon);
    pencil.setPreferredSize(new Dimension(30, 30));
    pencil.addActionListener(this);
    buttons.add(pencil);

    JButton red = new JButton();
    red.setPreferredSize(new Dimension(30, 30));
    red.setBackground(Color.RED);
    red.addActionListener(this);
    buttons.add(red);

    JButton pink = new JButton();
    pink.setPreferredSize(new Dimension(30, 30));
    pink.setBackground(Color.PINK);
    pink.addActionListener(this);
    buttons.add(pink);

    JButton black = new JButton();
    black.setPreferredSize(new Dimension(30, 30));
    black.setBackground(Color.BLACK);
    black.addActionListener(this);
    buttons.add(black);

    JButton green = new JButton();
    green.setPreferredSize(new Dimension(30, 30));
    green.setBackground(Color.GREEN);
    green.addActionListener(this);
    buttons.add(green);

    JButton white = new JButton();
    white.setPreferredSize(new Dimension(30, 30));
    white.setBackground(Color.WHITE);
    white.addActionListener(this);
    buttons.add(white);

    JButton yellow = new JButton();
    yellow.setPreferredSize(new Dimension(30, 30));
    yellow.setBackground(Color.YELLOW);
    yellow.addActionListener(this);
    buttons.add(yellow);

    JButton blue = new JButton();
    blue.setPreferredSize(new Dimension(30, 30));
    blue.setBackground(Color.BLUE);
    blue.addActionListener(this);
    buttons.add(blue);

    JButton gray = new JButton();
    gray.setPreferredSize(new Dimension(30, 30));
    gray.setBackground(Color.GRAY);
    gray.addActionListener(this);
    buttons.add(gray);

    System.out.println("[INFO] addBtn_toEquipment working...");
    return buttons;
  }

  private JPanel sizePanel() {
    JPanel sizePnl = new JPanel();
    sizePnl.setBackground(Color.GREEN);
    JSlider slider = new JSlider(JSlider.VERTICAL);// :NOTE: Still need modification
    slider
        .setPreferredSize(new Dimension(WindowTools.GetScreenHeight(), (int) (WindowTools.GetScreenHeight() * 0.0005)));
    sizePnl.add(slider);
    System.out.println("[INFO] sizePanel working...");
    return sizePnl;
  }

  private JPanel equipmentPanel() {
    JPanel equipment = new JPanel();
    equipment.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    for (JButton btns : addBtn_toEquipment()) {
      equipment.add(btns);
    }
    System.out.println("[INFO] EquipmentPanel working...");
    return equipment;
  }

  private void setEnvironmentalDecoration() {
    if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
      System.setProperty("flatlaf.menuBarEmbedded", "false");
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
    Icon openIcon = new ImageIcon(getClass().getResource("/icons/new-window.png"));
    Icon exitIcon = new ImageIcon(getClass().getResource("/icons/exit.png"));

    JMenu fileMenu = new JMenu("File");

    JMenuItem open = new JMenuItem("Open File", openIcon);
    open.addActionListener(this);
    fileMenu.add(open);

    JMenuItem saveFile = new JMenuItem("Save File", saveIcon);
    saveFile.addActionListener(this);
    fileMenu.add(saveFile);

    fileMenu.addSeparator();
    JMenuItem exit = new JMenuItem("exit", exitIcon);
    exit.addActionListener(this);
    fileMenu.add(exit);

    System.out.println("[INFO] FileMenu working...");
    return fileMenu;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("exit")) {
      System.exit(0);
    } else if (e.getActionCommand().equals("open")) {
    }
    System.out.println("[INFO] actionPerformed working...");
  }
}
