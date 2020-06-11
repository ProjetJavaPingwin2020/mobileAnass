/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiForm;

import Entity.Evenement;
import Services.ServiceEvenement;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author milim
 */
public class MyEvenementForm extends BaseForm {
        ServiceEvenement sa = new ServiceEvenement();
    String path;
  
    Form detailsForm;

    public MyEvenementForm(Resources res) {

        super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Mes Evenements");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 4) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 4);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3, FlowLayout.encloseCenter(new Label(res.getImage(""), "PictureWhiteBackgrond")))
                )
        ));
        for (Evenement a : sa.getListMyEvenements()) {
            addStringValue("", AddItem(a, res));
        }

    }

    public Container AddItem(Evenement a, Resources theme) {
        ConnectionRequest con = new ConnectionRequest();
        Container parent = new Container(BoxLayout.x());
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
      
         Label lnom = new Label("Nom :" +a.getNomEvenement());
         lnom.getAllStyles().setFgColor(0x000000);
            
        Label ldesc = new Label("Description :" +a.getDescription());
         ldesc.getAllStyles().setFgColor(0x000000);
            
        Label ladresess = new Label("Adresse :" +a.getAdresse());
         ladresess.getAllStyles().setFgColor(0x000000);

        SpanLabel ldate = new SpanLabel("Depuis le :" + a.getDateEvenement().toString());
        Button btacc = new Button("Modifier");
        Button btsup = new Button("Supprimer");

        C1.add(lnom);
        C1.add(ldesc);
        C1.add(ladresess);
        C1.add(ldate);
        C1.add(btacc);
        C1.add(btsup);
        String url = "http://localhost/integration/test1.1/web/images/" + a.getNomImage();
        EncodedImage enc = EncodedImage.createFromImage(theme.getImage("eco_nuevo.png"), false);
        Image image = URLImage.createToStorage(enc,"img"+a.getNomImage(), url, URLImage.RESIZE_SCALE);
        EncodedImage enc2 = EncodedImage.createFromImage(theme.getImage("eco_nuevo.png"), false);
        Image image2 = URLImage.createToStorage(enc2,"img"+a.getNomImage(), url);

        ImageViewer img = new ImageViewer(image);
        ImageViewer img2 = new ImageViewer(image2);
        parent.add(img);
        parent.add(C1);

        btacc.addActionListener(evt -> {
            ServiceEvenement sa = new ServiceEvenement();
            TextField tnom = new TextField(a.getNomEvenement(), "Nom");
            TextField tadresse = new TextField(a.getAdresse(), "Adresse");
            TextField tdes = new TextField(a.getDescription()+"", "Description");
            tnom.setUIID("TextFieldBlack");
            tadresse.setUIID("TextFieldBlack");
            tdes.setUIID("TextFieldBlack");

            detailsForm = new Form("Modifier event", BoxLayout.y());
            SpanLabel welcome = new SpanLabel("Modifier les donner de l'Evenement " + a.getNomEvenement());

            Button btretour = new Button("retour");
            Button btupd = new Button("modifier");
            
            detailsForm.add(welcome);
            detailsForm.add(tnom);
            detailsForm.add(tadresse);
            detailsForm.add(tdes);
            detailsForm.add(btupd);
            detailsForm.add(btretour);
            detailsForm.show();
            btupd.addActionListener(up -> {
             
    
    if(!tnom.getText().isEmpty()&&!tadresse.getText().isEmpty()&&!tdes.getText().isEmpty()){
               
                   a.setDescription(tdes.getText());
                   a.setNomEvenement(tnom.getText());
                   a.setAdresse(tadresse.getText());
                  sa.updateEvnt(a);
           
                 Dialog.show("Info", "evénement modifié !", "ok", null);
                  new MyEvenementForm(theme).show();
           }else Dialog.show("Erreur", "Champs vide !", "ok", null);
    
            });
            btretour.addActionListener(re -> {

                MyEvenementForm l = new MyEvenementForm(theme);
                l.show();
            });
        });
        btsup.addActionListener(e -> {
            sa.deleteEvnt(a);
            
            Dialog.show("Info", "Evenement supprimé !", "ok", null);
             new MyEvenementForm(theme).show();
        });

        return parent;
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
}
