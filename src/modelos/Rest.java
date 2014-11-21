
package modelos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Rest {
    
    static private final String url = "http://localhost:9000/";
    
    
    
    public static String login(Estacionamiento estacionamiento) {
        HttpClient client = HttpClients.custom().build();
        try {
            List<NameValuePair> login = new ArrayList<>(1);
            login.add(new BasicNameValuePair("email" , estacionamiento.getCorreo()));
            login.add(new BasicNameValuePair("password" ,estacionamiento.getContra()));
        
            HttpUriRequest request = RequestBuilder.post().setUri(url+"auth/local")
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .setEntity(new UrlEncodedFormEntity(login)).build();
            
            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String data = "";
            while ((data = rd.readLine()) != null) {
              System.out.println(data);
              JSONObject token =(JSONObject)JSONValue.parse(data);
              estacionamiento.setToken((String)token.get("token"));
            }

          } catch (IOException e) {
            e.printStackTrace();
          }
        return "";
    }
    
    public static String sendEstacionameinto(Estacionamiento estacionamiento){
        HttpClient client = HttpClients.custom().build();
        if(estacionamiento.getIdRemoto().equals("")){
        try {
            List<NameValuePair> resp = new ArrayList<>(1);
            
            resp.add(new BasicNameValuePair("nombre" , estacionamiento.getDescripcion()));
            resp.add(new BasicNameValuePair("direccion" , estacionamiento.getDireccion()));    
            
            
            HttpUriRequest request = RequestBuilder.post().setUri(url+"api/estacionamientos")
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + estacionamiento.getToken())     
                .setEntity(new UrlEncodedFormEntity(resp)).build();
            
            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String data = "";
            JSONParser parser = new JSONParser();
            while ((data = rd.readLine()) != null) {
                try {
                    JSONObject e =(JSONObject)parser.parse(data);
                    estacionamiento.setIdRemoto( (String)e.get("_id") );
                    estacionamiento.actualizar();
                
                } catch (ParseException ex) {
                    Logger.getLogger(Rest.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(data);
            }
            

          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        return "";
    }
    
    public static String sendAuto(Auto auto,Estacionamiento estacionamiento){
       HttpClient client = HttpClients.custom().build();
        
        try {
            List<NameValuePair> resp = new ArrayList<>(1);
            
            resp.add(new BasicNameValuePair("serie" , auto.getSerie()));
            resp.add(new BasicNameValuePair("tarifa" ,String.valueOf(auto.getTarifa().getId())));
            resp.add(new BasicNameValuePair("progresivo" , auto.getProgresivo()));
            
            resp.add(new BasicNameValuePair("entrada_salida" , auto.isDentro()?"E":"S"));
            resp.add(new BasicNameValuePair("fecha_entrada" , auto.getFechaEntrada()));
            resp.add(new BasicNameValuePair("hora_entrada" , auto.getHoraEntrada()));
            resp.add(new BasicNameValuePair("fecha_salida" , auto.getFechaSalida()));
            resp.add(new BasicNameValuePair("hora_salida" , auto.getHoraSalida()));
            
//            resp.add(new BasicNameValuePair("operador_entrada" , ""));
//            resp.add(new BasicNameValuePair("operador_salida" , auto.getHoraSalida()));
            resp.add(new BasicNameValuePair("id_caseta" , String.valueOf(auto.getCaseta().getId())));
            resp.add(new BasicNameValuePair("id_estacionamiento" , estacionamiento.getIdRemoto()));
            
            resp.add(new BasicNameValuePair("monto" , String.valueOf(auto.getMontoTangible())));
            resp.add(new BasicNameValuePair("horas" , String.valueOf(auto.getHorasTangibles())));
            resp.add(new BasicNameValuePair("minutos" , String.valueOf(auto.getHorasTangibles())));
            
            resp.add(new BasicNameValuePair("id_turno_entrada" , String.valueOf(auto.getTurnoEntrada().getId())));
            if(!auto.isDentro())
                resp.add(new BasicNameValuePair("id_turno_salida" , String.valueOf(auto.getTurnoSalida().getId())));

            resp.add(new BasicNameValuePair("boleto_perdido" , auto.isBoletoPerdido()?"SI":"NO"));
            resp.add(new BasicNameValuePair("boleto_cancelado" , auto.isBoletoPerdido()?"SI":"NO"));
            resp.add(new BasicNameValuePair("boleto_manual" , auto.isBoletoPerdido()?"SI":"NO"));
            resp.add(new BasicNameValuePair("boleto_contra" , auto.isBoletoPerdido()?"SI":"NO"));
            
            resp.add(new BasicNameValuePair("marca" , auto.getMarca()));
            resp.add(new BasicNameValuePair("modelo" , auto.getModelo()));
            HttpUriRequest request;
            if(auto.getIdRemoto()==null){
                 request = RequestBuilder.post().setUri(url+"api/autos")
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + estacionamiento.getToken())     
                    .setEntity(new UrlEncodedFormEntity(resp)).build();
            }else{
                request = RequestBuilder.put().setUri(url+"api/autos/"+auto.getIdRemoto())
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + estacionamiento.getToken())     
                    .setEntity(new UrlEncodedFormEntity(resp)).build();
            }
            
            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String data = "";
            while ((data = rd.readLine()) != null) {
              System.out.println(data);
            }


          } catch (IOException e) {
            e.printStackTrace();
          }
        return "";
    }
    
    
    
}


/*

 JSONParser parser=new JSONParser();

  System.out.println("=======decode=======");
                
  String s="[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
  Object obj=parser.parse(s);
  JSONArray array=(JSONArray)obj;
  System.out.println("======the 2nd element of array======");
  System.out.println(array.get(1));
  System.out.println();
                
  JSONObject obj2=(JSONObject)array.get(1);
  System.out.println("======field \"1\"==========");
  System.out.println(obj2.get("1"));    
*/