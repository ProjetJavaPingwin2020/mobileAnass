/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import Entity.Formation;
import Entity.Reservation;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
//import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author asus
 */
public class ServiceFormation {
    
      public ArrayList<Formation> formations;
    public  String  result="";
    public static ServiceFormation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceFormation() {
         req = new ConnectionRequest();
    }

    public static ServiceFormation getInstance() {
        if (instance == null) {
            instance = new ServiceFormation();
        }
        return instance;
    }
    
    public ArrayList<Formation> parseformations(String jsonText){
        try {
            formations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Formation f = new Formation();
                float IdFormation = Float.parseFloat(obj.get("id").toString());
                  f.setId((int)IdFormation);
                  f.setNom(obj.get("nom").toString());
                  f.setType(obj.get("type").toString());
                  
                  f.setLieu(obj.get("lieu").toString()); 
                  f.setDescription(obj.get("description").toString());
                  
                 
                  float NbrPlace = Float.parseFloat(obj.get("nbrplace").toString());
                  f.setNbrplace((int)NbrPlace);
                 
                  f.setNomImage(obj.get("nomImage").toString());
                  //Timestamp DateD = Timestamp.parseTimestamp(obj.get("DateD").toString());
                 // c.setDateD((Timestamp)DateD);
                 // c.setDateD(obj.get("DateD").toString());
                 /* DateFormat formatter;
                 formatter = new SimpleDateFormat("dd/MM/yyyy");
                 Date date;
                try {
                    date = (Date) formatter.parse(obj.get("DateD").toString());
                     java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
               c.setDateD(timeStampDate);
                } catch (ParseException ex) {
                    ex.getMessage();
                    
                }
             
                try {
                    date = (Date) formatter.parse(obj.get("DateF").toString());
                     java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
               c.setDateF(timeStampDate);
                } catch (ParseException ex) {
                    ex.getMessage();
                    
                }*/
              
                  
                 
                 
                formations.add(f);
            }
            
            
        } catch (IOException ex) {
            
        }
       
        return formations;
    }
    
    public ArrayList<Formation> getAllFormations(){
        String url = Statics.BASE_URL+"ListFormationMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                formations = parseformations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);     
                return formations;
    }
    public boolean AjouterReservation(Formation r) {
         
        System.out.println(UserService.user.getId());
        String url = Statics.BASE_URL + "addReservation/" + r.getId()+"/" + UserService.user.getId(); //création de l'URL
          System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
  
    public boolean AddFormation(Formation f) {
     
        String url = Statics.BASE_URL + "AddFormationMobile/?nom=" + f.getNom()+ "&type=" + f.getType()+ "&lieu=" + f.getLieu()+ "&description=" + f.getDescription()+ "&nbrplace=" + f.getNbrplace()+ "&nomImage=" + f.getNomImage(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
        public String DeleteFormation(Formation f) {
          String url = Statics.BASE_URL + "deleteFormationMobile/?id=" + f.getId();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);
                   

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> tasksListJson;
                    tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                   result=(String) tasksListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    
    public boolean EditFormation(Formation f) {
        String url = Statics.BASE_URL + "EditFormationMobile/?id="+f.getId()+"&nom=" + f.getNom()+ "&type=" + f.getType()+ "&lieu=" + f.getLieu()+ "&nbrplace=" + f.getNbrplace()+ "&description=" + f.getDescription(); //création de l'URL
            System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

     
    
    
   
    
    
   







    
}
