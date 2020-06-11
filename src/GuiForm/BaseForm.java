/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiForm;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author asus
 */
public class BaseForm extends Form{

  //  public static Produit eq = new Produit();

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    } 
        protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
       
    

        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
              
                
        ));
        tb.addMaterialCommandToSideMenu("Profil", FontImage.MATERIAL_SETTINGS, e -> {});
        tb.addMaterialCommandToSideMenu("Boutique ", FontImage.MATERIAL_UPDATE,  e -> {});
        tb.addMaterialCommandToSideMenu("Espèces", FontImage.MATERIAL_UPDATE, e -> {});
        tb.addMaterialCommandToSideMenu("Événement",  FontImage.MATERIAL_DETAILS, e -> new EvenementMenuForm(res).show());
        tb.addMaterialCommandToSideMenu("Formation",  FontImage.MATERIAL_DETAILS,e -> {});
        tb.addMaterialCommandToSideMenu("Information", FontImage.MATERIAL_CHAT_BUBBLE_OUTLINE, e -> {});
        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_SHOPPING_BASKET,e -> {});
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new SignInForm(res).show());

    }
}
