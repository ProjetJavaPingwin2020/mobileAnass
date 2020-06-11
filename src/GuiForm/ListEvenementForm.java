/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiForm;

import Entity.Demande;
import Entity.Evenement;
import Services.ServiceEvenement;
import Services.UserService;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.facebook.ui.LikeButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
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
public class ListEvenementForm extends BaseForm{
    
      ServiceEvenement sa = new ServiceEvenement();
    String path;
    Form detailsForm;
    TextField rdesc;

    public ListEvenementForm(Resources res) {
        
        super("", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Tous les Evenements");
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
         for (Evenement a : sa.getList2())
      {
          addStringValue("", AddItem(a,res));
       }
         
         

    }

    public Container AddItem(Evenement a,Resources theme) {
        ConnectionRequest con = new ConnectionRequest();
        Container parent = new Container(BoxLayout.x());
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label lnom = new Label("Nom :" +a.getNomEvenement());
         lnom.getAllStyles().setFgColor(0x000000);
            
        Label ldesc = new Label("Description :" +a.getDescription());
         ldesc.getAllStyles().setFgColor(0x000000);
            
        Label ladresess = new Label("Adresse :" +a.getAdresse());
         ladresess.getAllStyles().setFgColor(0x000000);
            
//        String strd = "1"+a.getDateEvenement().substring(231,237) +"000000";
//        System.out.println("aaaaaaaaaaaaaaaaa");
//       System.out.println(a.getDateEvenement().substring(231,237));
//       System.out.println("aaaaaaaaaaaaaaaaa");
//        long timestamp = Long.parseLong(strd);
//        Date d = new Date(timestamp );
        SpanLabel ldate = new SpanLabel("Date "+a.getDateEvenement().toString());
       
        
        Button btacc = new Button(sa.getButtonName(a.getIdEvenement()));
        Button btadh = new Button(sa.getButtonName(a.getIdEvenement()));
        C1.add(lnom);
        C1.add(ldesc);
        C1.add(ladresess);
        C1.add(ldate);
        C1.add(btacc);
        C1.setLeadComponent(btacc);
        String url = "http://localhost/integration/test1.1/web/images/" + a.getNomImage();
        EncodedImage enc = EncodedImage.createFromImage(theme.getImage("eco_nuevo.png"), false);
        Image image = URLImage.createToStorage(enc,"img"+a.getNomImage(), url, URLImage.RESIZE_SCALE);
        EncodedImage enc2 = EncodedImage.createFromImage(theme.getImage("eco_nuevo.png"), false);
        Image image2 = URLImage.createToStorage(enc2,"img"+a.getNomImage(), url);
        

        ImageViewer img= new ImageViewer(image);
        ImageViewer img2= new ImageViewer(image2);
        parent.add(img);
        parent.add(C1);

         btadh.addActionListener(evt->{
             Dialog.show("Error", "aaaaaa!", "ok", null);
             
         });
        btacc.addActionListener(evt->{
            Dialog ip = new InfiniteProgress().showInifiniteBlocking();
            ServiceEvenement sa = new ServiceEvenement();
            Demande demande = new Demande(a.getIdEvenement(), UserService.user.getId());
            sa.ajoutAdheration(demande);
            Label lrate = new Label("voter !");
            rdesc = new TextField("","exprimez-vous !");
            Slider SN = createStarRankSlider();
                    ArrayList <Evenement> eve = new ArrayList();
         System.out.println(a.getIdEvenement());
               eve=sa.getratting(a.getIdEvenement());
                for (Evenement e : eve)
                {a.setRatting(e.getRatting());
               
                }
                
        SN.setProgress((int)a.getRatting());

            detailsForm = new Form("details",BoxLayout.y());
            SpanLabel welcome = new SpanLabel("Bienvenue a l'Evnénement "+a.getNomEvenement());
            Button btvote = new Button("Voter ");
            btvote.addActionListener(e->{
                float f2 = (float) SN.getProgress();
                sa.ajoutVote(a.getIdEvenement(), f2, rdesc.getText());
                Dialog.show("Info", "Votre vote a été enregistré!", "ok", null);
                
            });
            Button btretour = new Button("retour");
            btretour.addActionListener(re->{
                Dialog ip2 = new InfiniteProgress().showInifiniteBlocking();
                ListEvenementForm l = new ListEvenementForm(theme);
                l.show();
            });
            SN.addActionListener(e->{
                float f2 = (float) SN.getProgress();
                System.out.println(f2);
                 btvote.setText("Voter "+f2 );
            });
            detailsForm.add(welcome);
            
           detailsForm.add(img2);
            
            detailsForm.addAll(FlowLayout.encloseCenter(lrate),FlowLayout.encloseCenter(SN));
            detailsForm.addAll(rdesc,btvote);
            detailsForm.add(createMail());
            detailsForm.add(btretour);   
            detailsForm.show();
        });

        return parent;
    }
    
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}

private Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}


        public void sendMail(String to,String sujet,String body) {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/journal/journal/sendmail.php?email="+ to+"&sujet="+sujet+"&body="+body);

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                System.err.println("Mail Sent");
            }
        });

        NetworkManager.getInstance().addToQueue(req);
    }

 public Container createMail() {
        Container message = new Container(new BoxLayout(BoxLayout.Y_AXIS));        
        ComponentGroup gp = new ComponentGroup();
        message.addComponent(gp);
        
        final TextField to = new TextField("huntkingdompingwin@gmail.com");
        to.setUIID("TextFieldBlack");
        to.setHint("TO:");
        gp.addComponent(to);

        final TextField subject = new TextField("Contacter Huntkingdom");
        subject.setHint("Sujet");
        subject.setUIID("TextFieldBlack");
        gp.addComponent(subject);
        final TextField body = new TextField("Qu'en pensez-vous de notre événement?");
        body.setSingleLineTextArea(false);
        body.setRows(4);
        body.setUIID("TextFieldBlack");
        body.setHint("Message Body");
        gp.addComponent(body);
        
        ComponentGroup buttonGroup = new ComponentGroup();
        Button btn = new Button("Envoyer");
        buttonGroup.addComponent(btn);
        message.addComponent(buttonGroup);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sendMail(to.getText(),subject.getText(),body.getText());
            }
        });
                
        return message;
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
}
