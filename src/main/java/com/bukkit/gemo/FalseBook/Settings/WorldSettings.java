package com.bukkit.gemo.FalseBook.Settings;

import com.bukkit.gemo.FalseBook.Core.FalseBookCore;
import com.bukkit.gemo.FalseBook.Values.ValueBoolean;
import com.bukkit.gemo.FalseBook.Values.ValueBooleanList;
import com.bukkit.gemo.FalseBook.Values.ValueDouble;
import com.bukkit.gemo.FalseBook.Values.ValueDoubleList;
import com.bukkit.gemo.FalseBook.Values.ValueFloat;
import com.bukkit.gemo.FalseBook.Values.ValueFloatList;
import com.bukkit.gemo.FalseBook.Values.ValueInteger;
import com.bukkit.gemo.FalseBook.Values.ValueIntegerList;
import com.bukkit.gemo.FalseBook.Values.ValueLocation;
import com.bukkit.gemo.FalseBook.Values.ValueLocationList;
import com.bukkit.gemo.FalseBook.Values.ValueString;
import com.bukkit.gemo.FalseBook.Values.ValueStringList;
import com.bukkit.gemo.utils.BlockUtils;
import com.bukkit.gemo.utils.FlatFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import org.bukkit.Location;

public class WorldSettings {

    private TreeMap<String, ValueBoolean> booleanMap = new TreeMap<String, ValueBoolean>();
    private TreeMap<String, ValueBooleanList> booleanListMap = new TreeMap<String, ValueBooleanList>();
    private TreeMap<String, ValueDouble> doubleMap = new TreeMap<String, ValueDouble>();
    private TreeMap<String, ValueDoubleList> doubleListMap = new TreeMap<String, ValueDoubleList>();
    private TreeMap<String, ValueFloat> floatMap = new TreeMap<String, ValueFloat>();
    private TreeMap<String, ValueFloatList> floatListMap = new TreeMap<String, ValueFloatList>();
    private TreeMap<String, ValueInteger> integerMap = new TreeMap<String, ValueInteger>();
    private TreeMap<String, ValueIntegerList> integerListMap = new TreeMap<String, ValueIntegerList>();
    private TreeMap<String, ValueLocation> locationMap = new TreeMap<String, ValueLocation>();
    private TreeMap<String, ValueLocationList> locationListMap = new TreeMap<String, ValueLocationList>();
    private TreeMap<String, ValueString> stringMap = new TreeMap<String, ValueString>();
    private TreeMap<String, ValueStringList> stringListMap = new TreeMap<String, ValueStringList>();

    public boolean getBoolean(String name) {
        return ((ValueBoolean) this.booleanMap.get(name)).getValue();
    }

    public ValueBooleanList getBooleanMap(String name) {
        return (ValueBooleanList) this.booleanListMap.get(name);
    }

    public boolean isKeyInBooleanList(String name) {
        return this.booleanListMap.containsKey(name);
    }

    public ValueBoolean addBoolean(ValueBoolean value) {
        return (ValueBoolean) this.booleanMap.put(value.getName(), value);
    }

    public ValueBoolean addBoolean(String name, boolean value) {
        return addBoolean(new ValueBoolean(name, value));
    }

    public ValueBooleanList addBooleanList(ValueBooleanList value) {
        return (ValueBooleanList) this.booleanListMap.put(value.getName(), value);
    }

    public ValueBooleanList addBooleanList(String name, ArrayList<Boolean> valueList) {
        String txt = name + "=";
        for (Iterator<Boolean> localIterator = valueList.iterator(); localIterator.hasNext();) {
            boolean val = ((Boolean) localIterator.next()).booleanValue();
            txt = txt + val + ValueBooleanList.delimiter;
        }
        return (ValueBooleanList) this.booleanListMap.put(name, new ValueBooleanList(name, txt));
    }

    public ValueBooleanList addBooleanList(String name, String importString) {
        return addBooleanList(new ValueBooleanList(name, importString));
    }

    public double getDouble(String name) {
        return ((ValueDouble) this.doubleMap.get(name)).getValue();
    }

    public ValueDoubleList getDoubleMap(String name) {
        return (ValueDoubleList) this.doubleListMap.get(name);
    }

    public boolean isKeyInDoubleList(String name) {
        return this.doubleListMap.containsKey(name);
    }

    public ValueDouble addDouble(ValueDouble value) {
        return (ValueDouble) this.doubleMap.put(value.getName(), value);
    }

    public ValueDouble addDouble(String name, double value) {
        return addDouble(new ValueDouble(name, value));
    }

    public ValueDoubleList addDoubleList(ValueDoubleList value) {
        return (ValueDoubleList) this.doubleListMap.put(value.getName(), value);
    }

    public ValueDoubleList addDoubleList(String name, ArrayList<Double> valueList) {
        String txt = name + "=";
        for (Iterator localIterator = valueList.iterator(); localIterator.hasNext();) {
            double val = ((Double) localIterator.next()).doubleValue();
            txt = txt + val + ValueDoubleList.delimiter;
        }
        return (ValueDoubleList) this.doubleListMap.put(name, new ValueDoubleList(name, txt));
    }

    public ValueDoubleList addDoubleList(String name, String importString) {
        return addDoubleList(new ValueDoubleList(name, importString));
    }

    public float getFloat(String name) {
        return ((ValueFloat) this.floatMap.get(name)).getValue();
    }

    public ValueFloatList getFloatMap(String name) {
        return (ValueFloatList) this.floatListMap.get(name);
    }

    public boolean isKeyInFloatList(String name) {
        return this.floatListMap.containsKey(name);
    }

    public ValueFloat addFloat(ValueFloat value) {
        return (ValueFloat) this.floatMap.put(value.getName(), value);
    }

    public ValueFloat addFloat(String name, float value) {
        return addFloat(new ValueFloat(name, value));
    }

    public ValueFloatList addFloatList(ValueFloatList value) {
        return (ValueFloatList) this.floatListMap.put(value.getName(), value);
    }

    public ValueFloatList addFloatList(String name, ArrayList<Float> valueList) {
        String txt = name + "=";
        for (Iterator<Float> localIterator = valueList.iterator(); localIterator.hasNext();) {
            float val = ((Float) localIterator.next()).floatValue();
            txt = txt + val + ValueFloatList.delimiter;
        }
        return (ValueFloatList) this.floatListMap.put(name, new ValueFloatList(name, txt));
    }

    public ValueFloatList addFloatList(String name, String importString) {
        return addFloatList(new ValueFloatList(name, importString));
    }

    public int getInteger(String name) {
        return ((ValueInteger) this.integerMap.get(name)).getValue();
    }

    public ValueIntegerList getIntegerMap(String name) {
        return (ValueIntegerList) this.integerListMap.get(name);
    }

    public boolean isKeyInIntegerList(String name) {
        return this.integerListMap.containsKey(name);
    }

    public ValueInteger addInteger(ValueInteger value) {
        return (ValueInteger) this.integerMap.put(value.getName(), value);
    }

    public ValueInteger addInteger(String name, int value) {
        return addInteger(new ValueInteger(name, value));
    }

    public ValueIntegerList addIntegerList(ValueIntegerList value) {
        return (ValueIntegerList) this.integerListMap.put(value.getName(), value);
    }

    public ValueIntegerList addIntegerList(String name, ArrayList<Integer> valueList) {
        String txt = name + "=";
        for (Iterator<Integer> localIterator = valueList.iterator(); localIterator.hasNext();) {
            int val = ((Integer) localIterator.next()).intValue();
            txt = txt + val + ValueIntegerList.delimiter;
        }
        return (ValueIntegerList) this.integerListMap.put(name, new ValueIntegerList(name, txt));
    }

    public ValueIntegerList addIntegerList(String name, String importString) {
        return addIntegerList(new ValueIntegerList(name, importString));
    }

    public Location getLocation(String name) {
        return ((ValueLocation) this.locationMap.get(name)).getValue();
    }

    public ValueLocationList getLocationMap(String name) {
        return (ValueLocationList) this.locationListMap.get(name);
    }

    public boolean isKeyInLocationList(String name) {
        return this.locationListMap.containsKey(name);
    }

    public ValueLocation addLocation(ValueLocation value) {
        return (ValueLocation) this.locationMap.put(value.getName(), value);
    }

    public ValueLocation addLocation(String name, Location value) {
        return addLocation(new ValueLocation(name, value));
    }

    public ValueLocationList addLocationList(ValueLocationList value) {
        return (ValueLocationList) this.locationListMap.put(value.getName(), value);
    }

    public ValueLocationList addLocationList(String name, String importString) {
        return addLocationList(new ValueLocationList(name, importString));
    }

    public String getString(String name) {
        return ((ValueString) this.stringMap.get(name)).getValue();
    }

    public ValueStringList getStringMap(String name) {
        return (ValueStringList) this.stringListMap.get(name);
    }

    public boolean isKeyInStringList(String name) {
        return this.stringListMap.containsKey(name);
    }

    public ValueString addString(ValueString value) {
        return (ValueString) this.stringMap.put(value.getName(), value);
    }

    public ValueString addString(String name, String value) {
        return addString(new ValueString(name, value));
    }

    public ValueStringList addStringList(ValueStringList value) {
        return (ValueStringList) this.stringListMap.put(value.getName(), value);
    }

    public ValueStringList addStringList(String name, String importString) {
        return addStringList(new ValueStringList(name, importString));
    }

    public boolean save(File dataFolder, String fileName) {
        dataFolder.mkdirs();
        File file = new File(dataFolder, fileName);
        boolean fileExisted = file.exists();
        try {
            String path = dataFolder.getPath();
            FlatFile config = new FlatFile(path + "\\" + fileName, fileExisted);

            for (ValueBoolean value : this.booleanMap.values()) {
                config.setBoolean(value.getName(), value.getValue());
            }
            for (ValueBooleanList value : this.booleanListMap.values()) {
                config.setString(value.getName(), value.exportList());
            }

            for (ValueDouble value : this.doubleMap.values()) {
                config.setDouble(value.getName(), value.getValue());
            }
            for (ValueDoubleList value : this.doubleListMap.values()) {
                config.setString(value.getName(), value.exportList());
            }

            for (ValueFloat value : this.floatMap.values()) {
                config.setFloat(value.getName(), value.getValue());
            }
            for (ValueFloatList value : this.floatListMap.values()) {
                config.setString(value.getName(), value.exportList());
            }

            for (ValueInteger value : this.integerMap.values()) {
                config.setInt(value.getName(), value.getValue());
            }
            for (ValueIntegerList value : this.integerListMap.values()) {
                config.setString(value.getName(), value.exportList());
            }

            for (ValueLocation value : this.locationMap.values()) {
                config.setString(value.getName(), BlockUtils.LocationToString(value.getValue()));
            }
            for (ValueLocationList value : this.locationListMap.values()) {
                config.setString(value.getName(), value.exportList());
            }

            for (ValueString value : this.stringMap.values()) {
                config.setString(value.getName(), value.getValue());
            }
            for (ValueStringList value : this.stringListMap.values()) {
                config.setString(value.getName(), value.exportList());
            }

            if (fileExisted) {
                file.delete();
            }
            config.writeFile();
            return true;
        } catch (IOException e) {
            FalseBookCore.printInConsole("Error while saving file: " + file.getName());
            e.printStackTrace();
        }
        return false;
    }
}