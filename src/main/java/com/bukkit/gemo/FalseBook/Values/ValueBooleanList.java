package com.bukkit.gemo.FalseBook.Values;

import com.bukkit.gemo.FalseBook.Exceptions.ValueNotFoundException;
import com.bukkit.gemo.FalseBook.Exceptions.ValueNotParsableException;
import java.util.TreeMap;

public class ValueBooleanList {

    public static String delimiter = ",";
    private String name;
    private TreeMap<String, ValueBoolean> valueList = new TreeMap<String, ValueBoolean>();

    public ValueBooleanList(String name) {
        this.name = name;
    }

    public ValueBooleanList(String name, String importString) {
        this.name = name;
        importString = importString.trim().replace(name + "=", "").replace(" ", "");
        String[] split = importString.split(delimiter);
        for (String part : split) {
            try {
                addValue(Boolean.valueOf(part).booleanValue());
            } catch (Exception e) {
                throw new ValueNotParsableException("VALUE '" + part + "' can not be parsed to ValueBoolean.");
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public boolean hasValue(String name) {
        return this.valueList.containsKey(name);
    }

    public ValueBoolean addValue(ValueBoolean value) {
        removeValue(value.getName());
        this.valueList.put(value.getName(), value);
        return value;
    }

    public ValueBoolean addValue(String name, boolean value) {
        return addValue(new ValueBoolean(name, value));
    }

    public ValueBoolean addValue(boolean value) {
        return addValue(new ValueBoolean(this.valueList.size() + 1, value));
    }

    public ValueBoolean removeValue(String name) {
        return this.valueList.remove(name);
    }

    public boolean getValue(String name) {
        try {
            return getNativeValue(name).getValue();
        } catch (Exception e) {
        }
        throw new ValueNotFoundException("VALUE '" + name + "' was not found (NULL).");
    }

    public ValueBoolean getNativeValue(String name) {
        return this.valueList.get(name);
    }

    public String exportList() {
        String export = "";
        for (ValueBoolean val : this.valueList.values()) {
            export = export + val.getValue() + delimiter;
        }
        return export;
    }

    public String toString() {
        return exportList();
    }
}