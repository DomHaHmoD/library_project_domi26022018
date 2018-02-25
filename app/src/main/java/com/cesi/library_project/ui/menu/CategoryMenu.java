package com.cesi.library_project.ui.menu;

import com.cesi.library_project.database.controllers.CategoryController;
import com.cesi.library_project.database.models.Category;
import com.cesi.library_project.providers.Providers;
import com.cesi.library_project.providers.ui.AbstractComponentProvider;
import com.cesi.library_project.providers.ui.category.CategoryThumbnail;
import com.cesi.library_project.ui.DisplayController;
import com.cesi.library_project.ui.IComponentProvider;
import com.cesi.library_project.ui.listeners.ICategoryClicked;
import com.cesi.library_project.ui.scroll.ScrollContent;
import com.cesi.library_project.ui.test.MainAreaContent;
import com.cesi.library_project.utils.Fonts;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.xml.ws.Provider;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the whole left menu
 */
public class CategoryMenu implements IComponentProvider, ICategoryClicked {

    private GridLayout mGrid;
    private Composite mComposite;
    private ArrayList<CategoryThumbnail> mCategoriesItem;
    private MainAreaContent mParent;
    private ScrollContent mScrollProvider;
    private Category categories2;
    private org.eclipse.swt.widgets.Display Display;
    private boolean loaded;
    private org.eclipse.swt.widgets.Display mDisplay;
    private JLabel mLabelType;
    private Composite mCategoryItem;
    private Category category;

    public CategoryMenu(@NotNull MainAreaContent parent) {
        mParent = parent;
    }

    @Nullable
    @Override
    public Composite getComposite() {
        return mComposite;
    }

    /**
     * For each Category from the database, add a new CategpryItem
     * and add it to the internal list of UI
     *
     * @param composite inject the view into it
     */
    @Override
    public void implement(Composite composite) {
        List<Category> list = Providers.CATEGORY_PROVIDER
                .getTableController().list();

        if (mGrid == null) {
            mGrid = new GridLayout(1, true);

            mScrollProvider = new ScrollContent();
            mScrollProvider.implement(composite);
            mScrollProvider.setPreferredWidthProvider(rectangle -> SWT.DEFAULT);

            mComposite = new Composite(mScrollProvider.getComposite(), SWT.PUSH);
            mScrollProvider.setChild(this);

            GridData data = new GridData();
            data.verticalAlignment = SWT.FILL;
            data.horizontalAlignment = SWT.BEGINNING;
            data.grabExcessVerticalSpace = true;

            mGrid.marginWidth = mGrid.marginBottom = mGrid.marginHeight = 0;
            mGrid.marginTop = 0;
            mGrid.marginLeft = mGrid.marginRight = 0;
            mGrid.horizontalSpacing = mGrid.verticalSpacing = 0;
            mScrollProvider.getComposite().setLayoutData(data);
            mComposite.setLayout(mGrid);
        }

        mCategoriesItem = new ArrayList<CategoryThumbnail>();
            // menu de la médiathèque
        Composite temp_composite = new Composite(mComposite, SWT.NONE);
        //Composite temp_composite = new Composite(mScrollProvider, SWT.NONE);
        RowLayout layout = new RowLayout(SWT.VERTICAL);
        layout.marginTop = 12;
        layout.marginBottom = layout.marginLeft = 6;
        temp_composite.setLayout(layout);

        Label title = new Label(temp_composite, SWT.NONE);
        title.setText("Mediatheque");
        title.setForeground(DisplayController.getInstance()
                .getColor(120, 120, 120));

        for (Category category : list) {
            CategoryThumbnail item = Providers.CATEGORY_PROVIDER
                    .getThumbnailProvider(category);

            item.setCategoryParent(this);
            mCategoriesItem.add(item);
            item.implement(mComposite);
        }
            // add a lign separator
        Label separator = new Label(mComposite, SWT.HORIZONTAL | SWT.SEPARATOR);
        separator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // add by domi to test a second menu under the first menu
        mGrid.marginLeft = 12; // add to push rapport
        Composite temp_composite2 = new Composite(mComposite, SWT.NONE);
        RowLayout layout2 = new RowLayout(SWT.VERTICAL);
        layout2.marginTop = 12;
        layout2.marginBottom = layout2.marginLeft = 6;
        temp_composite2.setLayout(layout2);
            // add by domi to test a second menu under the first menu
        Label title2 = new Label(temp_composite2, SWT.NONE);
        title2.setText("Autre information");
        title2.setForeground(DisplayController.getInstance()
                .getColor(120, 120, 120));


            //CategoryThumbnail item = Providers.CATEGORY_PROVIDER.getThumbnailProvider(category);
            CategoryThumbnail item = Providers.CATEGORY_PROVIDER.getThumbnailProvider(categories2);
            item.setCategoryParent(this);
            mCategoriesItem.add(item);
            item.implement(mComposite);
            
            Label title3 = new Label(mComposite, SWT.NONE);

            // charge  the font
            Boolean loaded = Fonts.getInstance()
                //.init(mDisplay) // not necessary
                .loadFont("/com/cesi/resources/", "untitled-font-1.ttf");
            title3.setFont(Fonts.getInstance().getFont("untitled-font-1", 10));
            title3.setText ("Rapport");


            //title3.setText ("a" + "rapport"); // ko why?

        Label separator2 = new Label(mComposite, SWT.HORIZONTAL | SWT.SEPARATOR);
        separator2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    }

    /**
     * For each UI Element, dispose its ressources
     */
    @Override
    public void dispose() {
        for (IComponentProvider item : mCategoriesItem) {
            item.dispose();
        }
    }

    @Override
    public void onCategoryClicked(Category category) {
        mParent.onCategoryClicked(category);

        for (CategoryThumbnail item : mCategoriesItem) {
            item.onCategoryClicked(category);
        }
    }
}
