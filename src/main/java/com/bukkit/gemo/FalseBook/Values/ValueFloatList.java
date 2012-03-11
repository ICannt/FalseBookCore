package com.bukkit.gemo.FalseBook.Values;

import com.bukkit.gemo.FalseBook.Exceptions.ValueNotFoundException;
import com.bukkit.gemo.FalseBook.Exceptions.ValueNotParsableException;
import java.util.ArrayList;

public class ValueFloatList
{
  public static String delimiter = ",";
  private String name;
  private ArrayList<ValueFloat> valueList = new ArrayList<ValueFloat>();

  public ValueFloatList(String name) {
    this.name = name;
  }

  public ValueFloatList(String name, String importString) {
    this.name = name;
    importString = importString.trim().replace(name + "=", "").replace(" ", "");
    String[] split = importString.split(delimiter);
    for (String part : split)
      try {
        addValue(Integer.valueOf(part).intValue());
      } catch (Exception e) {
        throw new ValueNotParsableException("VALUE '" + part + "' can not be parsed to ValueFloat.");
      }
  }

  public String getName()
  {
    return this.name;
  }

  public boolean hasValue(float value) {
    for (ValueFloat thisValue : this.valueList) {
      if (thisValue.getValue() == value) {
        return true;
      }
    }
    return false;
  }

  public ValueFloat addValue(ValueFloat value) {
    removeValue(value.getValue());
    this.valueList.add(value);
    return value;
  }

  public ValueFloat addValue(String name, float value) {
    return addValue(new ValueFloat(name, value));
  }

  public ValueFloat addValue(float value) {
    return addValue(new ValueFloat(this.valueList.size() + 1, value));
  }

  public boolean removeValue(float value)
  {
    boolean found = false;
    for (int index = this.valueList.size() - 1; index >= 0; index--) {
      ValueFloat thisVal = this.valueList.get(index);
      if (thisVal.getValue() == value) {
        this.valueList.remove(index);
        found = true;
      }
    }
    return found;
  }

  public float getValue(int index) {
    try {
      return this.valueList.get(index).getValue(); } catch (Exception e) {
    }
    throw new ValueNotFoundException("VALUE with index " + index + " was not found (SIZE: " + this.valueList.size() + ").");
  }

  public ArrayList<ValueFloat> getAll()
  {
    return this.valueList;
  }

  public String exportList() {
    String export = "";
    for (ValueFloat val : this.valueList) {
      export = export + val.getValue() + delimiter;
    }
    return export;
  }

  public String toString()
  {
    return exportList();
  }
}