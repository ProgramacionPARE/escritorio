
package modelos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author sistema
 */
public class Rest {
    
    static private final String url = "http://localhost:9000/";
    
    
    
    public static String login(Estacionamiento estacionamiento) {
        HttpClient client = HttpClients.custom().build();
//        try {
//            List<NameValuePair> login = new ArrayList<>(1);
//            login.add(new BasicNameValuePair("email" , "magdalena@pare.com.mx"));
//            login.add(new BasicNameValuePair("password" ,"Ib1EN0T]7E;R2o-"));
//        
//            HttpUriRequest request = RequestBuilder.post().setUri(url+"auth/local")
//                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
//                .setEntity(new UrlEncodedFormEntity(login)).build();
//            
//            HttpResponse response = client.execute(request);
//            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            String data = "";
//            while ((data = rd.readLine()) != null) {
//              System.out.println(data);
//              JSONObject token =(JSONObject)JSONValue.parse(data);
//              estacionamiento.setToken((String)token.get("token"));
//            }
//
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
        return "";
    }
    
    public static String sendAuto(Auto a,Estacionamiento estacionamiento){
     //  HttpClient client = HttpClients.custom().build();
        
//        try {
//            List<NameValuePair> auto = new ArrayList<>(1);
//            
//            auto.add(new BasicNameValuePair("serie" , a.getSerie()));
//            auto.add(new BasicNameValuePair("id_tarifa" ,String.valueOf(a.getTarifa().getId())));
//            
//            HttpUriRequest request = RequestBuilder.post().setUri(url+"api/autos")
//                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
//                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + estacionamiento.getToken())     
//                .setEntity(new UrlEncodedFormEntity(auto)).build();
//            
//            HttpResponse response = client.execute(request);
//            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            String data = "";
//            while ((data = rd.readLine()) != null) {
//              System.out.println(data);
//            }
//
//
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
        return "";
    }
    
    
    
}
