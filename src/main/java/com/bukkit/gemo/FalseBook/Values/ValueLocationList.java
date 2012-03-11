package com.bukkit.gemo.FalseBook.Values;

import com.bukkit.gemo.FalseBook.Exceptions.ValueNotFoundException;
import com.bukkit.gemo.FalseBook.Exceptions.ValueNotParsableException;
import com.bukkit.gemo.utils.BlockUtils;
import java.util.TreeMap;
import org.bukkit.Location;

public class ValueLocationList
{
  public static String delimiter = "<;>";
  private String name;
  private TreeMap<String, ValueLocation> valueList = new TreeMap<String, ValueLocation>();

  public ValueLocationList(String name) {
    this.name = name;
  }

  public ValueLocationList(String name, String importString) {
    this.name = name;
    importString = importString.trim().replace(name + "=", "");
    String[] split = importString.split(delimiter);
    for (String part : split)
      try {
        if (addValue(BlockUtils.LocationFromString(part)) == null)
          throw new ValueNotParsableException("VALUE '" + part + "' can not be parsed to ValueLocation.");
      }
      catch (Exception e) {
        throw new ValueNotParsableException("VALUE '" + part + "' can not be parsed to ValueLocation.");
      }
  }

  public String getName() {
    return this.name;
  }

  public boolean hasValue(String name) {
    return this.valueList.containsKey(name);
  }

  public ValueLocation addValue(ValueLocation value) {
    removeValue(value.getName());
    this.valueList.put(value.getName(), value);
    return value;
  }

  public ValueLocation addValue(String name, Location value) {
    return addValue(new ValueLocation(name, value));
  }

  public ValueLocation addValue(Location value) {
    return addValue(new ValueLocation(this.valueList.size() + 1, value));
  }

  public ValueLocation removeValue(String name) {
    return this.valueList.remove(name);
  }

  public Location getValue(String name) {
    try {
      return getNativeValue(name).getValue(); } catch (Exception e) {
    }
    throw new ValueNotFoundException("VALUE '" + name + "' was not found (NULL).");
  }

  public ValueLocation getNativeValue(String name)
  {
    return this.valueList.get(name);
  }

  public String exportList() {
    String export = "";
    for (ValueLocation val : this.valueList.values()) {
      export = export + BlockUtils.LocationToString(val.getValue()) + delimiter;
    }
    return export;
  }

  public String toString()
  {
    return exportList();
  }
}