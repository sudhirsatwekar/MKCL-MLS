/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.vo.Filter.java
 * Created On: Dec 26, 2011
 */

package org.mkcl.els.common.vo;

import com.google.gson.Gson;


/**
 * The Class Filter.
 *
 * @author vishals
 * @version v1.0.0
 */
public class Filter {

    /** The group op. */
    private String groupOp;

    /** The rules. */
    private Rule[] rules;

    /**
     * Gets the group op.
     *
     * @return the group op
     */
    public String getGroupOp() {
        return groupOp;
    }

    /**
     * Sets the group op.
     *
     * @param groupOp the new group op
     */
    public void setGroupOp(final String groupOp) {
        this.groupOp = groupOp;
    }

    /**
     * Gets the rules.
     *
     * @return the rules
     */
    public Rule[] getRules() {
        return rules;
    }

    /**
     * Sets the rules.
     *
     * @param rules the new rules
     */
    public void setRules(final Rule[] rules) {
        this.rules = rules;
    }

    /**
     * Creates the filter based on Json string.
     *
     * @param json the json
     * @return the filter
     * @author nileshp
     * @since v1.0.0
     */
    public static Filter create(final String json) {
        Gson gson = new Gson();
        Filter filter = gson.fromJson(json, Filter.class);
        return filter;
    }

    /**
     * To s ql.
     *
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    public String toSQl() {
        StringBuilder sql = new StringBuilder();
        String delim = "";
        for (Rule rule : this.rules) {
            sql = sql.append(delim).append(
                    resolveToSqlCondition(rule.getField(), rule.getOp(),
                            rule.getData()));
            delim = " " + this.groupOp + " ";
        }
        return " AND ( " + sql.toString() + " ) ";
    }

    /**
     * Resolve to sql condition.
     *
     * @param field the field
     * @param op the op
     * @param data the data
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    private String resolveToSqlCondition(final String field, final String op,
            final String data) {
        String sqlOp = "";
        if (op.equals("eq")) {
            sqlOp = field + "='" + data + "'";
        } else if (op.equals("ne")) {
            sqlOp = field + "!='" + data + "'";
        } else if (op.equals("bw")) {
            sqlOp = " ( " + field + " LIKE '" + data + "%') ";
        } else if (op.equals("bn")) {
            sqlOp = " ( " + field + " NOT LIKE '"  + data + "%') ";
        } else if (op.equals("ew")) {
            sqlOp = " ( " + field + " LIKE '%" + data + "') ";
        } else if (op.equals("en")) {
            sqlOp = " ( " + field + " NOT LIKE '%" + data + "') ";
        } else if (op.equals("cn")) {
            sqlOp = " ( " + field + " LIKE '%" + data + "%') ";
        } else if (op.equals("nc")) {
            sqlOp = " ( " + field + " NOT LIKE '%" + data + "%') ";
        } else if (op.equals("nu")) {
            sqlOp = " (" + field + " IS NULL) ";
        } else if (op.equals("nn")) {
            sqlOp = " (" + field + " IS NOT NULL) ";
        } else if (op.equals("in")) {
            sqlOp = " (" + field + " IN ( " + data + ")) ";
        } else if (op.equals("ni")) {
            sqlOp = " (" + field + " NOT IN ( " + data + ")) ";
        }
        return sqlOp;
    }
}
