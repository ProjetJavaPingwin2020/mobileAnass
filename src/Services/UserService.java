/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.FosUser;

import GuiForm.MenuForm;


import GuiForm.SignInForm;


import GuiForm.WelcomeForm;

import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author debba
 */
public class UserService {

    public static FosUser user = new FosUser();
    // public static User onlineId = new User();

    int temp;


    public void login(Resources res) {
        // TextField userlogin = (TextField) SignInForm.builder.findByName("Username", SignInForm.ctn);
        //TextField passlogin = (TextField) SignInForm.builder.findByName("Password", SignInForm.ctn);
        String userlog = SignInForm.username.getText();
        String passlog = SignInForm.password.getText();

        ConnectionRequest connectionRequest;
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(input, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    if (data.isEmpty()) {
                        Dialog.show("error", "Email not found ! please retry ", "Cancel", "ok");
                    } else {

                        user.setId((int) Float.parseFloat(data.get("id").toString()));
                        user.setNom(((String) data.get("nom")));
                        user.setMotdepasse(((String) data.get("password")));
//                        user.setTelephone((int) Integer.parseInt(data.get("numtel").toString()));
                        user.setAdresse(((String) data.get("adresse")));
                        user.setEmail(((String) data.get("email")));
                        user.setSurnom(((String) data.get("username")));
                        user.setGrade(((String) data.get("grade")));
//                        user.setRole(((String) data.get("role")));
                        /*Map<String, Object> data2 = (Map<String, Object>) (data.get("datenaissence"));
                        temp = (int) Float.parseFloat(data2.get("timestamp").toString());
                        Date myDate = new Date(temp * 1000L);
                        //user.setDateNaissence(myDate);*/
                        List<String> content = new ArrayList<>();
//                        content.addAll((Collection<? extends String>) (data.get("role")));
//                        user.setRole(content.get(1));

                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                System.out.println(user);
                System.out.println(user.getGrade());
                if (passlog.equals("")) {
                    Dialog.show("error", "Please put your password ! ", "cancel", "ok");
                    return;
                }
//                if (!(user.getMotdepasse().equals((passlog)))) {
//                    System.out.println(user.getMotdepasse());
//                    System.out.println(passlog);
//                    Dialog.show("error", "Wrong password please retry! ", "cancel", "ok");
//                    return;
//                }

                    if (user.getGrade().equals("0")) {

                    //new ProfileForm(res).show();
                    new MenuForm(res).show();

                } else if (user.getGrade().equals("1")) {
              //      new MenuFormAdmin(res).show();

                     new MenuForm(res).show();
                } else {
                    Dialog.show("error", "smthin wrong ", "cancel", "ok");

                }

            }
        };
        //       System.out.println(passlog);
        System.out.println(userlog);
//        connectionRequest.setUrl("http://localhost:8081/apijsonpi/web/app_dev.php/api/finduser/" + userlog);
     //   connectionRequest.setUrl("http://localhost/SymfonyPi/web/app_dev.php/mobile/finduser/" + userlog);
          connectionRequest.setUrl("http://localhost/integration/test1.1/web/app_dev.php/mobile/finduser/" + userlog);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
}
