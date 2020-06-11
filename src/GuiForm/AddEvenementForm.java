/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiForm;

import Entity.Evenement;
import Services.ServiceEvenement;
import Services.UserService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
//import com.codename1.ui.Calendar;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
//import static com.codename1.ui.events.ActionEvent.Type.Calendar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Date;
import java.util.Calendar;

import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author milim
 */
public class AddEvenementForm extends BaseForm{
       public static TextField tnom,tadresse,tdesc,image,picker;
   Button btnajout,btnaff,btnupload;
    String path;

     public static Picker dPicker;

    public AddEvenementForm(Resources res) {
        super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter un Evenement");
        getContentPane().setScrollVisible(false);
        
        image = new TextField();
        tnom = new TextField("","nom");
        tadresse = new TextField("","adresse");
        tdesc = new TextField("","description");
         picker = new TextField();
        dPicker = new Picker();
        dPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        dPicker.setDate(new Date());

        btnajout = new Button("ajouter");
        btnaff=new Button("Affichage");
        btnupload = new Button("parcourir image");
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        tb.addCommandToLeftBar("", res.getImage("left_arrow_50.png"), eve -> {    
            });
  
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

        tnom.setUIID("TextFieldBlack");
//        tnom.getStyle().setPaddingBottom(20);
        addStringValue("nom d'evenement", tnom);

        tadresse.setUIID("TextFieldBlack");
        addStringValue("adresse", tadresse);

        tdesc.setUIID("TextFieldBlack");
        addStringValue("description", tdesc); 
        dPicker.setUIID("TextFieldBlack");
        addStringValue("Date", dPicker); 

               
                    btnajout.addActionListener((e) -> {
            ServiceEvenement ser = new ServiceEvenement();
            
            FileUploader fc = new FileUploader("localhost/integration/test1.1/web/images/");
            System.out.println("owel el try");
            try {
                System.out.println("owel el try");
                String f = fc.upload(image.getText());
                image.setText(f);
                
                System.out.println("ba3d el upload ma3 : "+ f);
               
            } catch (Exception ex) {
            }
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        Date datecreation = new Date(System.currentTimeMillis());
                        SimpleDateFormat format = new 
                        SimpleDateFormat(DateFormatPatterns.ISO8601);
                        System.out.println(dPicker.getDate());
            Evenement a = new Evenement(0, tnom.getText(), tadresse.getText(),tdesc.getText(),UserService.user.getId(),image.getText());     
            a.setDateEvenement(dPicker.getDate());
           try {  
    if(!tnom.getText().isEmpty()&&!tadresse.getText().isEmpty()&&!tdesc.getText().isEmpty()){
               
                   ser.ajoutEvnt(a);
           Dialog.show("Info", "Evenement ajouté !", "ok", null);
              
           }else Dialog.show("Erreur", "Champs vide !", "ok", null);
    
  } catch(NumberFormatException ex){  
     
  }  
            
        });
//        
        btnaff.addActionListener((e)->{
//        ListAssociationForm a=new ListAssociationForm(res);
//        a.getF().show();
        });
        
        
        btnupload.addActionListener(e -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        path = (String) ev.getSource();
                        System.out.println("hetha l path :"+path.substring(7) + "w hetha :"+path);
                        Image img = null;
                        image.setText(path.substring(7));//image heya just label nsob feha fel path
                        try {
                            img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
                            System.out.println(img);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }, Display.GALLERY_IMAGE);
        });
    

        addStringValue("", btnupload);
     
        addStringValue("", btnajout);
     

    
}
    public static String pickerToString(Picker p) {

        Date s = p.getDate(); //dPicker howa l picker
        //System.out.println(s);
        Calendar c = Calendar.getInstance();
        c.setTime(s);
        String m1 = Integer.toString(c.get(Calendar.MONTH) + 1);
        String y1 = Integer.toString(c.get(Calendar.YEAR));
        String d1 = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        String date = y1 + "-" + m1 + "-" + d1;
        return date;
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
}
