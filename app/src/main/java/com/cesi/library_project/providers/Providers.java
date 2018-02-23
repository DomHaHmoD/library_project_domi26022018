package com.cesi.library_project.providers;

import com.cesi.library_project.database.models.*;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class Providers<MODEL extends IIdSetter> {

    private Providers() {

    }

    public static final CategoryProvider CATEGORY_PROVIDER = new CategoryProvider();

    public static final FilmProvider FILM_PROVIDER = new FilmProvider();

    public static final MusicProvider MUSIC_PROVIDER = new MusicProvider();

    public static final TableProvider TABLE_PROVIDER = new TableProvider();

    @Nullable
    public static AbstractProvider getProvider(String klass) {
        if(Film.class.getSimpleName().equals(klass)) {
            return FILM_PROVIDER;
        } else if(Music.class.getSimpleName().equals(klass)) {
            return MUSIC_PROVIDER;
        } else if (Table.class.getSimpleName().equals(klass)) {
            return TABLE_PROVIDER;
        }

        return null;
    }
}
