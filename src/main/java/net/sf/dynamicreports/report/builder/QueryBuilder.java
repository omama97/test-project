/*
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2018 Ricardo Mariaca and the Dynamic Reports Contributors
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.dynamicreports.report.builder;

import net.sf.dynamicreports.report.base.DRQuery;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * <p>QueryBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class QueryBuilder extends AbstractBuilder<QueryBuilder, DRQuery> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for QueryBuilder.</p>
     *
     * @param text     a {@link java.lang.String} object.
     * @param language a {@link java.lang.String} object.
     */
    protected QueryBuilder(String text, String language) {
        super(new DRQuery(text, language));
    }

    /**
     * <p>getQuery.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.base.DRQuery} object.
     */
    public DRQuery getQuery() {
        return build();
    }
}