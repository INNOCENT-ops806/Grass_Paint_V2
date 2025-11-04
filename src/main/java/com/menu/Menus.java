package com.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public final class Menus {
  private Menus() {
  }

  public static void addMenus(JMenuBar menu_bar) {
    addFileMenu(menu_bar);
    addEditMenu(menu_bar);
    addViewMenu(menu_bar);
    addUndoMenu(menu_bar);
    addRedoMenu(menu_bar);
  }

  private static void addFileMenu(JMenuBar menu_bar) {
    JMenu fileMenu = new JMenu("File");
    fileMenu.add(new JMenuItem("New"));
    fileMenu.add(new JMenuItem("Open"));
    fileMenu.addSeparator();
    fileMenu.add(new JMenuItem("Recent"));
    fileMenu.addSeparator();
    fileMenu.add(new JMenuItem("Save"));
    fileMenu.add(new JMenuItem("Save As"));
    fileMenu.addSeparator();
    fileMenu.add(new JMenuItem("Share"));
    fileMenu.addSeparator();
    fileMenu.add(new JMenuItem("Exit"));
    menu_bar.add(fileMenu);

  }

  private static void addEditMenu(JMenuBar menu_bar) {
    JMenu editMenu = new JMenu("Edit");
    menu_bar.add(editMenu);
  }

  private static void addViewMenu(JMenuBar menu_bar) {
    JMenu viewMenu = new JMenu("View");
    viewMenu.add(new JMenuItem("Zoom"));
    viewMenu.addSeparator();
    viewMenu.add(new JMenuItem("fullScreen"));
    menu_bar.add(viewMenu);
  }

  private static void addUndoMenu(JMenuBar menu_bar) {
    JMenu undo = new JMenu("Undo");
    menu_bar.add(undo);
  }
  private static void addRedoMenu(JMenuBar menu_bar) {
    JMenu redo = new JMenu("Redo");
    menu_bar.add(redo);
  }


}
