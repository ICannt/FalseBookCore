package com.bukkit.gemo.FalseBook.Values;

import com.bukkit.gemo.FalseBook.Exceptions.ValueNotFoundException;
import com.bukkit.gemo.FalseBook.Exceptions.ValueNotParsableException;
import java.util.ArrayList;

public class ValueIntegerList
{
  public static String delimiter = ",";
  private String name;
  private ArrayList<ValueInteger> valueList = new ArrayList<ValueInteger>();

  public ValueIntegerList(String name) {
    this.name = name;
  }

  public ValueIntegerList(String name, String importString) {
    this.name = name;
    importString = importString.trim().replace(name + "=", "").replace(" ", "");
    String[] split = importString.split(delimiter);
    for (String part : split)
      try {
        addValue(Integer.valueOf(part).intValue());
      } catch (Exception e) {
        throw new ValueNotParsableException("VALUE '" + part + "' can not be parsed to ValueInteger.");
      }
  }

  public String getName()
  {
    return this.name;
  }

  public boolean hasValue(int value) {
    for (ValueInteger thisValue : this.valueList) {
      if (thisValue.getValue() == value) {
        return true;
      }
    }
    return false;
  }

  public ValueInteger addValue(ValueInteger value) {
    removeValue(value.getValue());
    this.valueList.add(value);
    return value;
  }

  public ValueInteger addValue(String name, int value) {
    return addValue(new ValueInteger(name, value));
  }

  public ValueInteger addValue(int value) {
    return addValue(new ValueInteger(this.valueList.size() + 1, value));
  }

  public boolean removeValue(int value)
  {
    boolean found = false;
    for (int index = this.valueList.size() - 1; index >= 0; index--) {
      ValueInteger thisVal = this.valueList.get(index);
      if (thisVal.getValue() == value) {
        this.valueList.remove(index);
        found = true;
      }
    }
    return found;
  }

  public int getValue(int index) {
    try {
      return this.valueList.get(index).getValue(); } catch (Exception e) {
    }
    throw new ValueNotFoundException("VALUE with index " + index + " was not found (SIZE: " + this.valueList.size() + ").");
  }

  public ArrayList<ValueInteger> getAll()
  {
    return this.valueList;
  }

  public String exportList() {
    String export = "";
    for (ValueInteger val : this.valueList) {
      export = export + val.getValue() + delimiter;
    }
    return export;
  }

  public String toString()
  {
    return exportList();
  }
}