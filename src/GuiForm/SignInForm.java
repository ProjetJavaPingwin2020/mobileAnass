/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiForm;

import Entity.FosUser;
import Services.UserService;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;


/**
 *
 * @author debba
 */
public class SignInForm extends BaseForm{
     public static Form f;
     
      static FosUser connectedUser=new FosUser();
    UserService auth = new UserService();
    public static TextField username,password;
    public static Resources res,theme;  
        public SignInForm(Resources res) {
        super(new BorderLayout());
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
         
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
// Form f = new Form(BoxLayout.y());
//               f.getToolbar().addCommandToLeftBar("Transporteur", null, (j) -> {
//                    LoginForm h = new LoginForm(res);
//                    h.show();
//
//                });
        username = new TextField("", "email", 20, TextField.ANY);
        password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(true);
        password.setSingleLineTextArea(true);
        
        username.getUnselectedStyle().setFgColor(0xffffff);
        password.getUnselectedStyle().setFgColor(0xffffff);
        Button signIn = new Button("Se connecter");
        Button signUp = new Button("S'inscrire");
      
       
        
        signIn.getUnselectedStyle().setFgColor(0xffffff);
        signUp.getUnselectedStyle().setFgColor(654111);
     
        
      //  signUp.addActionListener(e -> new SignUpForm(res).show());
      //  signUp.setUIID("Link");
      
        Label doneHaveAnAccount = new Label("Vous n'avez pas de compte?");
         Label dokk = new Label("tr");
        doneHaveAnAccount.getUnselectedStyle().setFgColor(0xff0000);
        Validator val = new Validator();

 
       val.addConstraint(password, new LengthConstraint(4));
    val.addConstraint(username,RegexConstraint.validEmail());

        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
              
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
      

  //      new WelcomeForm(theme).show();
        //e -> new NewsfeedForm(res).show()
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                auth.login(res);
            }
        });
    }
}
