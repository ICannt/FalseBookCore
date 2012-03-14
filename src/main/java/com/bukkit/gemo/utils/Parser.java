package com.bukkit.gemo.utils;

import org.bukkit.util.Vector;

public abstract class Parser {

    public static boolean isIntegerOrEmpty(String line) {
        if (line.length() < 1) {
            return true;
        }
        try {
            Integer.valueOf(line);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isBoolean(String line) {
        try {
            Boolean.valueOf(line);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean getBoolean(String line, boolean defaultValue) {
        if (!isBoolean(line)) {
            return defaultValue;
        }
        try {
            return Boolean.valueOf(line).booleanValue();
        } catch (Exception e) {
        }
        return defaultValue;
    }

    public static boolean isInteger(String line) {
        try {
            Integer.valueOf(line);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static int getInteger(String line, int defaultValue) {
        if (!isInteger(line)) {
            return defaultValue;
        }
        try {
            int result = Integer.valueOf(line).intValue();
            return result;
        } catch (Exception e) {
        }
        return defaultValue;
    }

    public static int getIntegerFromOffsetLine(String line, int defaultValue) {
        String[] split = line.split("=");
        return getInteger(split[0], defaultValue);
    }

    public static boolean isDouble(String line) {
        try {
            Double.valueOf(line);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static double getDouble(String line) {
        if (!isDouble(line)) {
            return 0.0D;
        }
        try {
            double result = Double.valueOf(line).doubleValue();
            return result;
        } catch (Exception e) {
        }
        return 0.0D;
    }

    public static boolean isFloat(String line) {
        try {
            Float.valueOf(line);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static float getFloat(String line) {
        if (!isFloat(line)) {
            return 0.0F;
        }
        try {
            float result = Float.valueOf(line).floatValue();
            return result;
        } catch (Exception e) {
        }
        return 0.0F;
    }

    public static boolean isString(String line, String otherString) {
        return line.equalsIgnoreCase(otherString);
    }

    public static boolean isStringOrEmpty(String line, String otherString) {
        return (line.equalsIgnoreCase(otherString)) || (line.length() < 1);
    }

    public static boolean isBlock(String line) {
        if (line == null) {
            return false;
        }
        String[] split = line.split(":");
        short Data;
        try {
            if (split.length > 2) {
                return false;
            }
            int ID = Integer.valueOf(split[0]).intValue();
            Data = 0;
            if (split.length == 2) {
                Data = Short.valueOf(split[1]).shortValue();
            }
            return BlockUtils.isValidItemID(ID, Data);
        } catch (Exception e) {
            Data = 0;
            if (split.length == 2) {
                Data = Short.valueOf(split[1]).shortValue();
            }
        }
        return BlockUtils.isValidItemID(split[0], Data);
    }

    public static FBBlockType getBlock(String line) {
        if (!isBlock(line)) {
            return null;
        }
        if (line == null) {
            return null;
        }
        String[] split = line.split(":");
        FBBlockType result;
        try {
            if (split.length > 2) {
                return null;
            }
            result = new FBBlockType(Integer.valueOf(split[0]).intValue());
            if (split.length == 2) {
                result.setItemData(Short.valueOf(split[1]).shortValue());
            }
            return result;
        } catch (Exception e) {
            result = new FBBlockType(split[0]);
            if (split.length == 2) {
                result.setItemData(Short.valueOf(split[1]).shortValue());
            }
        }
        return result;
    }

    public static boolean isVector(String line) {
        String[] split = line.split(":");
        if (split.length == 3) {
            return (isInteger(split[0])) && (isInteger(split[1])) && (isInteger(split[2]));
        }
        return false;
    }

    public static Vector getVector(String line) {
        if (!isVector(line)) {
            return new Vector(0, 0, 0);
        }
        String[] split = line.split(":");
        return new Vector(getInteger(split[0], 0), getInteger(split[1], 0), getInteger(split[2], 0));
    }

    public static Vector getVectorFromOffsetLine(String line) {
        String[] split = line.split("=");
        if (split.length == 2) {
            return getVector(split[1]);
        }
        return new Vector(0, 0, 0);
    }

    public static boolean isIntegerWithOffset(String line) {
        String[] split = line.split("=");
        if (split.length == 1) {
            return isInteger(split[0]);
        }
        if (split.length == 2) {
            return (isInteger(split[0])) && (isVector(split[1]));
        }

        return false;
    }
}