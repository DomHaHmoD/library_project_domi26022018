/**
 * MusicThumbnail
 * Generate the thumbnail to put into MainContent
 * Version: 1.0
 * Date: 01/03/2018
 * Team:Alex-JeanLuc-Domi
 * Author:Alex-JeanLuc-Domi
 **/

package com.cesi.library_project.providers.ui.music;

import com.cesi.library_project.database.controllers.AbstractController;
import com.cesi.library_project.database.models.Film;
import com.cesi.library_project.database.models.IIdSetter;
import com.cesi.library_project.database.models.Music;
import com.cesi.library_project.providers.AbstractProvider;
import com.cesi.library_project.providers.ui.AbstractComponentProvider;
import com.cesi.library_project.ui.DisplayController;
import com.cesi.library_project.ui.IComponentProvider;
import com.cesi.library_project.utils.Fonts;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class MusicThumbnail extends AbstractComponentProvider<Music> {

    private Composite mComposite;
    private Image mImage;
    private boolean loaded;
    private Display display;

    public MusicThumbnail(Music object) {
        super(object);
    }

    @Nullable
    @Override
    public Composite getComposite() {
        return null;
    }

    //@Override
    //public void implement(@NotNull Composite composite) {

    //}

    @Override
    public <MODEL extends IIdSetter> void implement(@NotNull Composite composite) {

        //proxy composite to display the internal component easily
        mComposite = new Composite(composite, SWT.NONE);
        mComposite.setBackground(DisplayController.getInstance().getColor(255, 255, 255));

        GridLayout layout = new GridLayout(1, false);
        layout.marginTop = layout.marginBottom = layout.marginLeft = layout.marginRight = 0;
        layout.marginHeight = layout.marginWidth = 0;
        layout.verticalSpacing = 6;
        mComposite.setLayout(layout);
            // to add image
        mImage = DisplayController.getInstance()
                .loadImage("/com/cesi/resources/thumbnail_music.png", 150);

        Label label = new Label(mComposite, SWT.NONE);
        label.setImage(mImage);

        label.addMouseListener(new MouseListener() {
            public AbstractProvider<IIdSetter, AbstractComponentProvider<IIdSetter>, AbstractController<IIdSetter>, AbstractComponentProvider<IIdSetter>> provider;

            @Override
            public void mouseDoubleClick(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseDown(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseUp(MouseEvent mouseEvent) {

                AbstractComponentProvider<IIdSetter> component = provider.createObject();
                System.out.println("on click " + component);

                if (component != null) {
                    Shell shell = new Shell(DisplayController.getInstance().getDisplay(),
                            SWT.SHELL_TRIM);

                    GridLayout layout = new GridLayout(1, true);
                    layout.horizontalSpacing = layout.verticalSpacing = 0;
                    layout.marginTop = layout.marginBottom = 0;
                    layout.marginLeft = layout.marginRight = 0;
                    layout.marginWidth = layout.marginHeight = 0;
                    shell.setLayout(layout);

                    component.implement(shell);

                    shell.open();
                }
            }
        });





            // to add title
        GridData data = new GridData();
        data.horizontalAlignment = SWT.CENTER;
        Label text = new Label(mComposite, SWT.NONE);
        text.setText(getModel().getMetaData().getTitle());
        text.setLayoutData(data);
            // to add note
        GridData data1 = new GridData();
        data1.horizontalAlignment = SWT.CENTER;
        Label text1 = new Label(mComposite, SWT.NONE);
        loaded &= Fonts.getInstance()
                .loadFont("/com/cesi/resources/untitled-font-1.ttf","untitled-font-1.ttf");
        text1.setFont(Fonts.getInstance().getFont("untitled-font-1", 12));

        // Set fore color
        Color color = new Color(display, 255, 215, 0);
        text1.setForeground(color);

        // switch to convert note in stars
        String converttext = Integer.toString(getModel().getMetaData().getNote());
        switch(converttext){
            case "0" : text1.setText(" ");
            break;
            case "1" : text1.setText("b");
                break;
            case "2" : text1.setText("bb");
                break;
            case "3" : text1.setText("bbb");
                break;
            case "4" : text1.setText("bbbb");
                break;
            case "5" : text1.setText("bbbbb");
                break;
        }

        text1.setLayoutData(data1);
    }

    @Override
    public void dispose() {
        mComposite.dispose();
    }
}
