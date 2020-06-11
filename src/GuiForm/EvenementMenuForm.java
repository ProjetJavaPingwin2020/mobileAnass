/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiForm;


import Services.UserService;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;


/**
 *
 * @author med
 */
public class EvenementMenuForm extends BaseForm{

   public static TextField tnom,tadresse,tcapital,image;
   Button btnajout,btnaff,btnmanage,btnadh;
    String path;

    

    public EvenementMenuForm(Resources res) {
        super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Espace Evénement");
        getContentPane().setScrollVisible(false);
        
        image = new TextField();
        btnajout = new Button("Ajouter événement");
        btnaff= new Button("Tous les événements");
        btnmanage= new Button("Gérer mes événements");
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
  
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 4) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 4);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3,FlowLayout.encloseCenter(new Label(res.getImage(""), "PictureWhiteBackgrond"))     )
                )
        ));

                    btnajout.addActionListener((e) -> {
                        Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                        new AddEvenementForm(res).show();
                 
        });
      
        btnaff.addActionListener((e)->{
            Dialog ip = new InfiniteProgress().showInifiniteBlocking();
            new ListEvenementForm(res).show();
        });
        
        
        btnmanage.addActionListener(e -> {
            Dialog ip = new InfiniteProgress().showInifiniteBlocking();
          new MyEvenementForm(res).show();
        });
        
    

        
        addStringValue("=>", btnaff);
        addStringValue("=>", btnajout);
        addStringValue("=>", btnmanage);
        
        
     

    
}

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
