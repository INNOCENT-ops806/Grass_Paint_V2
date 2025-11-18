package com.example;

import java.util.Stack;

public class Custom_Stack<T> extends Stack<T> {
  private final int maxSize;

  Custom_Stack(int size) {
    super();
    this.maxSize = size;
  }

  @Override
  public Object push(Object object) {
    while (this.size() > maxSize) {
      this.remove(0);
    }
    return super.push((T) object);
  }
}
