package com.bukkit.gemo.FalseBook.Values;

import com.bukkit.gemo.FalseBook.Exceptions.ValueNotFoundException;
import com.bukkit.gemo.FalseBook.Exceptions.ValueNotParsableException;
import java.util.TreeMap;

public class ValueStringList
{
  public static String delimiter = "<;>";
  private String name;
  private TreeMap<String, ValueString> valueList = new TreeMap<String, ValueString>();

  public ValueStringList(String name) {
    this.name = name;
  }

  public ValueStringList(String name, String importString) {
    this.name = name;
    importString = importString.trim().replace(name + "=", "");
    String[] split = importString.split(delimiter);
    for (String part : split)
      try {
        addValue(part);
      } catch (Exception e) {
        throw new ValueNotParsableException("VALUE '" + part + "' can not be parsed to ValueString.");
      }
  }

  public String getName()
  {
    return this.name;
  }

  public boolean hasValue(String name) {
    return this.valueList.containsKey(name);
  }

  public ValueString addValue(ValueString value) {
    removeValue(value.getName());
    this.valueList.put(value.getName(), value);
    return value;
  }

  public ValueString addValue(String name, String value) {
    return addValue(new ValueString(name, value));
  }

  public ValueString addValue(String value) {
    return addValue(new ValueString(this.valueList.size() + 1, value));
  }

  public ValueString removeValue(String name) {
    return this.valueList.remove(name);
  }

  public String getValue(String name) {
    try {
      return getNativeValue(name).getValue(); } catch (Exception e) {
    }
    throw new ValueNotFoundException("VALUE '" + name + "' was not found (NULL).");
  }

  public ValueString getNativeValue(String name)
  {
    return this.valueList.get(name);
  }

  public String exportList() {
    String export = "";
    for (ValueString val : this.valueList.values()) {
      export = export + val.getValue() + delimiter;
    }
    return export;
  }

  public String toString()
  {
    return exportList();
  }
}