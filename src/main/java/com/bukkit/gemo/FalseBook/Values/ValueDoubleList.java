package com.bukkit.gemo.FalseBook.Values;

import com.bukkit.gemo.FalseBook.Exceptions.ValueNotFoundException;
import com.bukkit.gemo.FalseBook.Exceptions.ValueNotParsableException;
import java.util.ArrayList;

public class ValueDoubleList
{
  public static String delimiter = ",";
  private String name;
  private ArrayList<ValueDouble> valueList = new ArrayList<ValueDouble>();

  public ValueDoubleList(String name) {
    this.name = name;
  }

  public ValueDoubleList(String name, String importString) {
    this.name = name;
    importString = importString.trim().replace(name + "=", "").replace(" ", "");
    String[] split = importString.split(delimiter);
    for (String part : split)
      try {
        addValue(Integer.valueOf(part).intValue());
      } catch (Exception e) {
        throw new ValueNotParsableException("VALUE '" + part + "' can not be parsed to ValueDouble.");
      }
  }

  public String getName()
  {
    return this.name;
  }

  public boolean hasValue(double value) {
    for (ValueDouble thisValue : this.valueList) {
      if (thisValue.getValue() == value) {
        return true;
      }
    }
    return false;
  }

  public ValueDouble addValue(ValueDouble value) {
    removeValue(value.getValue());
    this.valueList.add(value);
    return value;
  }

  public ValueDouble addValue(String name, double value) {
    return addValue(new ValueDouble(name, value));
  }

  public ValueDouble addValue(double value) {
    return addValue(new ValueDouble(this.valueList.size() + 1, value));
  }

  public boolean removeValue(double value)
  {
    boolean found = false;
    for (int index = this.valueList.size() - 1; index >= 0; index--) {
      ValueDouble thisVal = this.valueList.get(index);
      if (thisVal.getValue() == value) {
        this.valueList.remove(index);
        found = true;
      }
    }
    return found;
  }

  public double getValue(int index) {
    try {
      return this.valueList.get(index).getValue(); } catch (Exception e) {
    }
    throw new ValueNotFoundException("VALUE with index " + index + " was not found (SIZE: " + this.valueList.size() + ").");
  }

  public ArrayList<ValueDouble> getAll()
  {
    return this.valueList;
  }

  public String exportList() {
    String export = "";
    for (ValueDouble val : this.valueList) {
      export = export + val.getValue() + delimiter;
    }
    return export;
  }

  public String toString()
  {
    return exportList();
  }
}