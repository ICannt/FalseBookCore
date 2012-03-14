package com.bukkit.gemo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;

public class FlatFile {

    private String FileName;
    private ArrayList<FlatFileData> Data;
    private BufferedReader reader;
    private Writer writer;
    private boolean isReading = false;
    private boolean isWriting = false;

    public FlatFile(String FileName, boolean read)
            throws IOException {
        this.FileName = FileName;
        this.Data = new ArrayList<FlatFileData>();
        if (read) {
            readFile();
        }
    }

    public void regenerateFile(String FileName)
            throws IOException {
        this.writer = new BufferedWriter(new FileWriter("plugins/" + FileName));
        this.writer.flush();
        this.writer.close();
    }

    public boolean readFile()
            throws IOException {
        boolean FileExisted = true;
        if (!new File("plugins/" + this.FileName).exists()) {
            regenerateFile(this.FileName);
            FileExisted = false;
        }

        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream("plugins/" + this.FileName), "ISO-8859-1"));
        String line = this.reader.readLine();

        while (line != null) {
            line = line.trim();
            if (line.startsWith("#")) {
                continue;
            }
            String[] split = line.split("=");
            if (split.length > 1) {
                this.Data.add(new FlatFileData(split[0], split[1]));
            }
            line = this.reader.readLine();
        }
        this.reader.close();
        return FileExisted;
    }

    public void writeFile()
            throws IOException {
        this.writer = new BufferedWriter(new FileWriter("plugins/" + this.FileName));
        for (int i = 0; i < this.Data.size(); i++) {
            this.writer.write(this.Data.get(i).getName() + "=" + this.Data.get(i).getValue() + "\r\n");
        }
        this.writer.close();
    }

    public void clearAll() {
        this.Data.clear();
    }

    public void closeFile(boolean read)
            throws IOException {
        if (read) {
            if (this.isReading) {
                this.isReading = false;
            }
        } else if (this.isWriting) {
            this.writer.flush();
            this.writer.close();
            this.isWriting = false;
        }
    }

    public void addBoolean(String name, Boolean value) {
        if (propertyExists(name)) {
            return;
        }
        this.Data.add(new FlatFileData(name, value.toString()));
    }

    public void addInt(String name, Integer value) {
        if (propertyExists(name)) {
            return;
        }
        this.Data.add(new FlatFileData(name, value.toString()));
    }

    public void addDouble(String name, Double value) {
        if (propertyExists(name)) {
            return;
        }
        this.Data.add(new FlatFileData(name, value.toString()));
    }

    public void addFloat(String name, Float value) {
        if (propertyExists(name)) {
            return;
        }
        this.Data.add(new FlatFileData(name, value.toString()));
    }

    public void addString(String name, String value) {
        if (propertyExists(name)) {
            return;
        }
        this.Data.add(new FlatFileData(name, value));
    }

    public void addBooleanArrayList(String name, ArrayList<Boolean> value, String delimiter) {
        if (propertyExists(name)) {
            return;
        }
        String str = "";
        for (int i = 0; i < value.size() - 1; i++) {
            str = str + value.get(i) + delimiter;
        }
        if (value.size() > 0) {
            str = str + value.get(value.size() - 1);
        }
        this.Data.add(new FlatFileData(name, str));
    }

    public void addIntArrayList(String name, ArrayList<Integer> value, String delimiter) {
        if (propertyExists(name)) {
            return;
        }
        String str = "";
        for (int i = 0; i < value.size() - 1; i++) {
            str = str + value.get(i) + delimiter;
        }
        if (value.size() > 0) {
            str = str + value.get(value.size() - 1);
        }
        this.Data.add(new FlatFileData(name, str));
    }

    public void addDoubleArrayList(String name, ArrayList<Double> value, String delimiter) {
        if (propertyExists(name)) {
            return;
        }
        String str = "";
        for (int i = 0; i < value.size() - 1; i++) {
            str = str + value.get(i) + delimiter;
        }
        if (value.size() > 0) {
            str = str + value.get(value.size() - 1);
        }
        this.Data.add(new FlatFileData(name, str));
    }

    public void addFloatArrayList(String name, ArrayList<Float> value, String delimiter) {
        if (propertyExists(name)) {
            return;
        }
        String str = "";
        for (int i = 0; i < value.size() - 1; i++) {
            str = str + value.get(i) + delimiter;
        }
        if (value.size() > 0) {
            str = str + value.get(value.size() - 1);
        }
        this.Data.add(new FlatFileData(name, str));
    }

    public void addStringArrayList(String name, ArrayList<String> value, String delimiter) {
        if (propertyExists(name)) {
            return;
        }
        String str = "";
        for (int i = 0; i < value.size() - 1; i++) {
            str = str + value.get(i) + delimiter;
        }
        if (value.size() > 0) {
            str = str + value.get(value.size() - 1);
        }
        this.Data.add(new FlatFileData(name, str));
    }

    public boolean propertyExists(String name) {
        for (int i = 0; i < this.Data.size(); i++) {
            if (this.Data.get(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private int getPropertyID(String name) {
        for (int i = 0; i < this.Data.size(); i++) {
            if (this.Data.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public boolean setBoolean(String name, Boolean value) {
        if (!propertyExists(name)) {
            addBoolean(name, value);
        }
        this.Data.get(getPropertyID(name)).setValue(value.toString());
        return value;
    }

    public int setInt(String name, Integer value) {
        if (!propertyExists(name)) {
            addInt(name, value);
        }
        this.Data.get(getPropertyID(name)).setValue(value.toString());
        return value;
    }

    public double setDouble(String name, Double value) {
        if (!propertyExists(name)) {
            addDouble(name, value);
        }
        this.Data.get(getPropertyID(name)).setValue(value.toString());
        return value;
    }

    public float setFloat(String name, Float value) {
        if (!propertyExists(name)) {
            addFloat(name, value);
        }
        this.Data.get(getPropertyID(name)).setValue(value.toString());
        return value;
    }

    public String setString(String name, String value) {
        if (!propertyExists(name)) {
            addString(name, value);
        }
        this.Data.get(getPropertyID(name)).setValue(value);
        return value;
    }

    public ArrayList<Boolean> setBooleanArrayList(String name, ArrayList<Boolean> value, String delimiter) {
        String str = "";
        for (int i = 0; i < value.size() - 1; i++) {
            str = str + value.get(i) + delimiter;
        }
        if (value.size() > 0) {
            str = str + value.get(value.size() - 1);
        }

        if (!propertyExists(name)) {
            addBooleanArrayList(name, value, delimiter);
        }
        this.Data.get(getPropertyID(name)).setValue(str);
        return value;
    }

    public ArrayList<Integer> setIntArrayList(String name, ArrayList<Integer> value, String delimiter) {
        String str = "";
        for (int i = 0; i < value.size() - 1; i++) {
            str = str + value.get(i) + delimiter;
        }
        if (value.size() > 0) {
            str = str + value.get(value.size() - 1);
        }

        if (!propertyExists(name)) {
            addIntArrayList(name, value, delimiter);
        }
        this.Data.get(getPropertyID(name)).setValue(str);
        return value;
    }

    public ArrayList<Double> setDoubleArrayList(String name, ArrayList<Double> value, String delimiter) {
        String str = "";
        for (int i = 0; i < value.size() - 1; i++) {
            str = str + value.get(i) + delimiter;
        }
        if (value.size() > 0) {
            str = str + value.get(value.size() - 1);
        }

        if (!propertyExists(name)) {
            addDoubleArrayList(name, value, delimiter);
        }
        this.Data.get(getPropertyID(name)).setValue(str);
        return value;
    }

    public ArrayList<Float> setFloatArrayList(String name, ArrayList<Float> value, String delimiter) {
        String str = "";
        for (int i = 0; i < value.size() - 1; i++) {
            str = str + value.get(i) + delimiter;
        }
        if (value.size() > 0) {
            str = str + value.get(value.size() - 1);
        }

        if (!propertyExists(name)) {
            addFloatArrayList(name, value, delimiter);
        }
        this.Data.get(getPropertyID(name)).setValue(str);
        return value;
    }

    public ArrayList<String> setStringArrayList(String name, ArrayList<String> value, String delimiter) {
        String str = "";
        for (int i = 0; i < value.size() - 1; i++) {
            str = str + value.get(i) + delimiter;
        }
        if (value.size() > 0) {
            str = str + value.get(value.size() - 1);
        }

        if (!propertyExists(name)) {
            addStringArrayList(name, value, delimiter);
        }
        this.Data.get(getPropertyID(name)).setValue(str);
        return value;
    }

    public boolean getBoolean(String name, boolean standard) {
        if (!propertyExists(name)) {
            return standard;
        }
        return Boolean.valueOf(this.Data.get(getPropertyID(name)).getValue()).booleanValue();
    }

    public int getInt(String name, int standard) {
        if (!propertyExists(name)) {
            return standard;
        }
        return Integer.valueOf(this.Data.get(getPropertyID(name)).getValue()).intValue();
    }

    public double getDouble(String name, double standard) {
        if (!propertyExists(name)) {
            return standard;
        }
        return Double.valueOf(this.Data.get(getPropertyID(name)).getValue()).doubleValue();
    }

    public float getFloat(String name, float standard) {
        if (!propertyExists(name)) {
            return standard;
        }
        return Float.valueOf(this.Data.get(getPropertyID(name)).getValue()).floatValue();
    }

    public String getString(String name, String standard) {
        if (!propertyExists(name)) {
            return standard;
        }
        return this.Data.get(getPropertyID(name)).getValue();
    }

    public ArrayList<Boolean> getBooleanArrayList(String name, String delimiter) {
        ArrayList<Boolean> list = new ArrayList<Boolean>();
        if (!propertyExists(name)) {
            addBooleanArrayList(name, list, delimiter);
            return list;
        }

        String[] data = this.Data.get(getPropertyID(name)).getValue().trim().split(delimiter);
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].trim();
            list.add(Boolean.valueOf(data[i]));
        }
        return list;
    }

    public ArrayList<Integer> getIntArrayList(String name, String delimiter) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (!propertyExists(name)) {
            addIntArrayList(name, list, delimiter);
            return list;
        }

        String[] data = this.Data.get(getPropertyID(name)).getValue().trim().split(delimiter);
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].trim();
            list.add(Integer.valueOf(data[i]));
        }
        return list;
    }

    public ArrayList<Double> getDoubleArrayList(String name, String delimiter) {
        ArrayList<Double> list = new ArrayList<Double>();

        if (!propertyExists(name)) {
            addDoubleArrayList(name, list, delimiter);
            return list;
        }

        String[] data = this.Data.get(getPropertyID(name)).getValue().trim().split(delimiter);
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].trim();
            list.add(Double.valueOf(data[i]));
        }
        return list;
    }

    public ArrayList<Float> getFloatArrayList(String name, String delimiter) {
        ArrayList<Float> list = new ArrayList<Float>();

        if (!propertyExists(name)) {
            addFloatArrayList(name, list, delimiter);
            return list;
        }

        String[] data = this.Data.get(getPropertyID(name)).getValue().trim().split(delimiter);
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].trim();
            list.add(Float.valueOf(data[i]));
        }
        return list;
    }

    public ArrayList<String> getStringArrayList(String name, String delimiter) {
        ArrayList<String> list = new ArrayList<String>();

        if (!propertyExists(name)) {
            addStringArrayList(name, list, delimiter);
            return list;
        }

        String[] data = this.Data.get(getPropertyID(name)).getValue().trim().split(delimiter);
        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
        }
        return list;
    }
}