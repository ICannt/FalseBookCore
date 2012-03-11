package com.bukkit.gemo.FalseBook.Values;

public class ValueInteger
{
  private String name;
  private int value;

  public ValueInteger(String name, int value)
  {
    this.name = name;
    this.value = value;
  }
  
  public ValueInteger(Integer name, int value)
  {
    this.name = name.toString();
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public int getValue() {
    return this.value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String toString()
  {
    return String.valueOf(this.value);
  }
}