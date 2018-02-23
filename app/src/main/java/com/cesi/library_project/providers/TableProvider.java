package com.cesi.library_project.providers;

import com.cesi.library_project.database.controllers.MusicController;
import com.cesi.library_project.database.controllers.TableController;
import com.cesi.library_project.database.models.Music;
import com.cesi.library_project.database.models.Table;
import com.cesi.library_project.providers.ui.music.MusicForm;
import com.cesi.library_project.providers.ui.music.MusicThumbnail;
import com.cesi.library_project.providers.ui.table.TableForm;
import com.cesi.library_project.providers.ui.table.TableThumbnail;

public class TableProvider extends AbstractProvider<Table, TableThumbnail, TableController, TableForm> {
    @Override
    protected TableController createController() {
        return TableController.getInstance();
    }

    @Override
    public TableThumbnail getThumbnailProvider(Table object) {
        return new TableThumbnail(object);
    }

    @Override
    public TableThumbnail getPageProvider(Table object) {
        return null;
    }

    @Override
    public TableForm modifyObject(Table object) {
        return new TableForm(object);
    }

    @Override
    public TableForm createObject() {
        return new TableForm(null);
    }
}
