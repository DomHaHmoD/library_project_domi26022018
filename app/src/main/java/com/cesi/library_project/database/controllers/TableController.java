package com.cesi.library_project.database.controllers;

import com.cesi.library_project.database.db.LibraryDatabase;
import com.cesi.library_project.database.models.Music;
import com.cesi.library_project.database.models.Table;
import com.sun.istack.internal.NotNull;
import za.co.neilson.sqlite.orm.ObjectModel;
import za.co.neilson.sqlite.orm.jdbc.JdbcObjectModel;

import java.sql.ResultSet;
import java.util.HashMap;

public class TableController extends AbstractController<Table> {
    private static final TableController CATEGORY_CONTROLLER = new TableController ();

    public static TableController getInstance() {
        return CATEGORY_CONTROLLER;
    }

    private TableController() {
        super();
    }

    @NotNull
    @Override
    protected Class<Table> getModelClass() {
        return Table.class;
    }

    @Override
    protected ObjectModel<Table, ResultSet, HashMap<String, Object>> createJDBCObject(LibraryDatabase instance) throws NoSuchFieldException, ClassNotFoundException {
        return new JdbcObjectModel<Table>(instance) {
        };
    }
}
