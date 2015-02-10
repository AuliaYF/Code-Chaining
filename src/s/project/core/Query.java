/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s.project.core;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author auliayf
 */
public class Query {

    static HashMap<String, String> mapJoin = new HashMap<>();

    static {
        mapJoin.put("", Join.EMPTY);
        mapJoin.put("left", Join.LEFT);
        mapJoin.put("right", Join.RIGHT);
        mapJoin.put("innert", Join.INNER);
        mapJoin.put("full", Join.FULL);
    }
    String table;

    ArrayList<String> mapSelect = new ArrayList<>();
    ArrayList<String> mapWhere = new ArrayList<>();

    Join join = null;

    public Query(String table) {
        this.table = table;
    }

    public Query select(String str) {
        mapSelect.add(str);
        return this;
    }

    public Query join(String table, String arg, String mode) {
        this.join = new Join();

        this.join.table = table;
        this.join.arg = arg;
        this.join.mode = mapJoin.get(mode);

        return this;
    }

    public Query where(String column, String arg) {
        this.mapWhere.add(column + " = '" + arg + "'");

        return this;
    }

    public Query where(String where) {
        this.mapWhere.add(where);

        return this;
    }

    public String get() {
        StringBuilder builder = new StringBuilder();
        if (mapSelect.size() < 1) {
            builder.append("SELECT * FROM " + table);
        } else if (mapSelect.size() > 0) {
            builder.append("SELECT (");

            boolean first = true;
            for (String str : mapSelect) {
                if (!first) {
                    builder.append(", ");
                }
                builder.append(str);
                first = false;
            }

            builder.append(") FROM " + table);
        }
        if (join != null) {
            builder.append(this.join.mode + " JOIN " + this.join.table + " ON " + this.join.arg);
        }
        if (mapWhere.size() > 0) {
            builder.append(" WHERE ");

            boolean first = true;
            for (String str : mapWhere) {
                if (!first) {
                    builder.append(" AND ");
                }
                builder.append(str);
                first = false;
            }
        }
        return builder.toString();
    }

    static class Join {

        public static String table;
        public static String arg;
        public static String mode;

        public static final String LEFT = "LEFT";
        public static final String RIGHT = "RIGHT";
        public static final String INNER = "INNER";
        public static final String FULL = "FULL";
        public static final String EMPTY = "";
    }
}
