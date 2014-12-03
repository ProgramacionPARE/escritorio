package modelos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
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

    static private final String url = Configuracion.getInstancia().getUrl();

    public static String login(Estacionamiento estacionamiento) {
        HttpClient client = HttpClients.custom().build();
        try {
            List<NameValuePair> login = new ArrayList<>(1);
            login.add(new BasicNameValuePair("email", estacionamiento.getCorreo()));
            login.add(new BasicNameValuePair("password", estacionamiento.getContra()));

            HttpUriRequest request = RequestBuilder.post().setUri(url + "auth/local")
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .setEntity(new UrlEncodedFormEntity(login)).build();

            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String data = "";
            while ((data = rd.readLine()) != null) {
                System.out.println(data);
                JSONObject token = (JSONObject) JSONValue.parse(data);
                estacionamiento.setToken((String) token.get("token"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sendEstacionameinto(Estacionamiento estacionamiento) {
        HttpClient client = HttpClients.custom().build();
        if (estacionamiento.getIdRemoto().equals("")) {
            try {
                List<NameValuePair> resp = new ArrayList<>(1);

                resp.add(new BasicNameValuePair("nombre", estacionamiento.getDescripcion()));
                resp.add(new BasicNameValuePair("direccion", estacionamiento.getDireccion()));

                HttpUriRequest request = RequestBuilder.post().setUri(url + "api/estacionamientos")
                        .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                        .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + estacionamiento.getToken())
                        .setEntity(new UrlEncodedFormEntity(resp)).build();

                HttpResponse response = client.execute(request);
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String data = "";
                JSONParser parser = new JSONParser();
                while ((data = rd.readLine()) != null) {
                    try {
                        JSONObject e = (JSONObject) parser.parse(data);
                        estacionamiento.setIdRemoto((String) e.get("_id"));
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

    public static String sendAuto(final Auto auto,final Estacionamiento estacionamiento) {
          new Thread(new Runnable() {

            @Override
            public void run() {
                HttpClient client = HttpClients.custom().build();
                try {
                    List<NameValuePair> resp = new ArrayList<>(1);
                    resp.add(new BasicNameValuePair("id", String.valueOf(auto.getId())));
                    resp.add(new BasicNameValuePair("serie", auto.getSerie()));
                    resp.add(new BasicNameValuePair("tarifa", String.valueOf(auto.getTarifa().getId())));
                    resp.add(new BasicNameValuePair("progresivo", auto.getProgresivo()));
                    resp.add(new BasicNameValuePair("entrada_salida", auto.isDentro() ? "E" : "S"));
                    resp.add(new BasicNameValuePair("fecha_entrada", auto.getFechaEntrada()));
                    resp.add(new BasicNameValuePair("hora_entrada", auto.getHoraEntrada()));
                    resp.add(new BasicNameValuePair("fecha_salida", auto.getFechaSalida()));
                    resp.add(new BasicNameValuePair("hora_salida", auto.getHoraSalida()));
                    resp.add(new BasicNameValuePair("id_caseta", String.valueOf(auto.getCaseta().getId())));
                    resp.add(new BasicNameValuePair("id_estacionamiento", estacionamiento.getIdRemoto()));
                    resp.add(new BasicNameValuePair("monto", String.valueOf(auto.getMontoTangible())));
                    resp.add(new BasicNameValuePair("horas", String.valueOf(auto.getHorasTangibles())));
                    resp.add(new BasicNameValuePair("minutos", String.valueOf(auto.getMinutosTangibles())));
                    resp.add(new BasicNameValuePair("id_turno_entrada", String.valueOf(auto.getTurnoEntrada().getId())));
                    if (!auto.isDentro()) {
                        resp.add(new BasicNameValuePair("id_turno_salida", String.valueOf(auto.getTurnoSalida().getId())));
                    }
                    resp.add(new BasicNameValuePair("boleto_perdido", auto.isBoletoPerdido() ? "SI" : "NO"));
                    resp.add(new BasicNameValuePair("boleto_cancelado", auto.isBoletoCancelado() ? "SI" : "NO"));
                    resp.add(new BasicNameValuePair("boleto_manual", auto.isBoletoManual() ? "SI" : "NO"));
                    resp.add(new BasicNameValuePair("boleto_contra", auto.isBoletoContra() ? "SI" : "NO"));
                    resp.add(new BasicNameValuePair("marca", auto.getMarca()));
                    resp.add(new BasicNameValuePair("modelo", auto.getModelo()));
                    HttpUriRequest request;
                    if (auto.getIdRemoto() == null) {
                        request = RequestBuilder.post().setUri(url + "api/autos")
                                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + estacionamiento.getToken())
                                .setEntity(new UrlEncodedFormEntity(resp)).build();
                    } else {
                        request = RequestBuilder.put().setUri(url + "api/autos/" + auto.getIdRemoto())
                                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + estacionamiento.getToken())
                                .setEntity(new UrlEncodedFormEntity(resp)).build();
                    }

                    HttpResponse response = client.execute(request);
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    String data = "";
                    JSONParser parser = new JSONParser();
                    while ((data = rd.readLine()) != null) {
                        try {
                            if(data.equals("Unauthorized")){
                                login(estacionamiento);
                                sendAuto(auto,estacionamiento);
                            }else{
                                JSONObject e = (JSONObject) parser.parse(data);
                                auto.setIdRemoto((String) e.get("_id"));
                                if(auto.isDentro())
                                    auto.setEstadoServidor(1);
                                else 
                                    auto.setEstadoServidor(2);
                                auto.actualizar();
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(Rest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
         return "";
    }

    public static String sendTurno(final Turno turno,final Estacionamiento estacionamiento) {
        new Thread(new Runnable() {

            @Override
            public void run() {
               HttpClient client = HttpClients.custom().build();
                try {
                    List<NameValuePair> resp = new ArrayList<>(1);

                    resp.add(new BasicNameValuePair("id", String.valueOf(turno.getId())));
                    resp.add(new BasicNameValuePair("tipo_turno", turno.getTipoTurno()));
                    resp.add(new BasicNameValuePair("fecha_apertura", turno.getFechaApertura()));
                    resp.add(new BasicNameValuePair("hora_apertura", turno.getHoraApertura()));
                    resp.add(new BasicNameValuePair("fecha_cierre", turno.getFechaCierre()));
                    resp.add(new BasicNameValuePair("hora_cierre", turno.getHoraCierre()));
                    resp.add(new BasicNameValuePair("id_estacionamiento", estacionamiento.getIdRemoto()));
                    resp.add(new BasicNameValuePair("abierto_cerrado", turno.getHoraCierre()==null?"Abierto":"Cerrado"));

                    HttpUriRequest request;
                    if (turno.getIdRemoto() == null) {
                        request = RequestBuilder.post().setUri(url + "api/turnos")
                                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + estacionamiento.getToken())
                                .setEntity(new UrlEncodedFormEntity(resp)).build();
                    } else {
                        request = RequestBuilder.put().setUri(url + "api/turnos/" + turno.getIdRemoto())
                                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + estacionamiento.getToken())
                                .setEntity(new UrlEncodedFormEntity(resp)).build();
                    }

                    HttpResponse response = client.execute(request);
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    String data = "";
                    JSONParser parser = new JSONParser();
                    while ((data = rd.readLine()) != null) {
                        try {
                             if(data.equals("Unauthorized")){
                                login(estacionamiento);
                                sendTurno( turno, estacionamiento);
                            }else{
                            JSONObject e = (JSONObject) parser.parse(data);
                            turno.setIdRemoto((String) e.get("_id"));
                            if(turno.getFechaCierre()==null)
                                turno.setEstadoServidor(1);
                            else 
                                turno.setEstadoServidor(2);
                            turno.actualizar();
                             }
                        } catch (ParseException ex) {
                            Logger.getLogger(Rest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        return "";
    }

    public static String sendTurnoDetalle(final TurnoDetalles turnoDetalle,final Estacionamiento estacionamiento) {
          new Thread(new Runnable() {

            @Override
            public void run() {
                HttpClient client = HttpClients.custom().build();
                try {
                    List<NameValuePair> resp = new ArrayList<>(1);

                    resp.add(new BasicNameValuePair("id", String.valueOf(turnoDetalle.getId())));
                    resp.add(new BasicNameValuePair("id_turno", String.valueOf(turnoDetalle.getIdTurno())));
                    resp.add(new BasicNameValuePair("serie", turnoDetalle.getSerie()));
                    resp.add(new BasicNameValuePair("folio_inicial", String.valueOf(turnoDetalle.getFolioInicial())));
                    resp.add(new BasicNameValuePair("folio_final", String.valueOf(turnoDetalle.getFolioFinal())));
                    resp.add(new BasicNameValuePair("no_bol", String.valueOf(turnoDetalle.getNoBol())));
                    resp.add(new BasicNameValuePair("no_bol_turno_a", String.valueOf(turnoDetalle.getNoBolTurnoA())));
                    resp.add(new BasicNameValuePair("no_bol_cancelados", String.valueOf(turnoDetalle.getNoBolCancelados())));
                    resp.add(new BasicNameValuePair("no_bol_perdidos", String.valueOf(turnoDetalle.getNoBolPerdidos())));
                    resp.add(new BasicNameValuePair("no_bol_cobrados", String.valueOf(turnoDetalle.getNoBolCobrados())));
                    resp.add(new BasicNameValuePair("no_bol_contra", String.valueOf(turnoDetalle.getNoBolContra())));

                    resp.add(new BasicNameValuePair("no_bol_turno_s", String.valueOf(turnoDetalle.getNoBolTurnoS())));
                    resp.add(new BasicNameValuePair("total", String.valueOf(turnoDetalle.getTotal())));
                    resp.add(new BasicNameValuePair("id_estacionamiento", estacionamiento.getIdRemoto()));

                    HttpUriRequest request;
                    if (turnoDetalle.getIdRemoto() == null) {
                        request = RequestBuilder.post().setUri(url + "api/detalleTurnos")
                                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + estacionamiento.getToken())
                                .setEntity(new UrlEncodedFormEntity(resp)).build();
                    } else {
                        request = RequestBuilder.put().setUri(url + "api/detalleTurnos/" + turnoDetalle.getIdRemoto())
                                .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + estacionamiento.getToken())
                                .setEntity(new UrlEncodedFormEntity(resp)).build();
                    }

                    HttpResponse response = client.execute(request);
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    String data = "";
                    JSONParser parser = new JSONParser();
                    while ((data = rd.readLine()) != null) {
                        try {
                            if(data.equals("Unauthorized")){
                                login(estacionamiento);
                                sendTurnoDetalle(turnoDetalle,estacionamiento);
                            }else{
                                JSONObject e = (JSONObject) parser.parse(data);
                                turnoDetalle.setIdRemoto((String) e.get("_id"));

                                turnoDetalle.actualizar();
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(Rest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        return "";
    }
    
    public static void sendAutosOffline(final Estacionamiento estacionamiento){
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                ArrayList<Auto> autosOffline = Auto.getAutosOffline();
                Iterator<Auto> iterator = autosOffline.iterator();
                while(iterator.hasNext()){
                    try {
                        sendAuto(iterator.next(),estacionamiento);
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Rest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                }}).start();
    }
        
    public static void sendTurnosOffline(final Estacionamiento estacionamiento){
        new Thread(new Runnable() {

            @Override
            public void run() {
                ArrayList<Turno> turnosOffline = Turno.getTurnosOffline();
                Iterator<Turno> iterator = turnosOffline.iterator();
                while(iterator.hasNext()){
                     try {
                         sendTurno(iterator.next(),estacionamiento);
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Rest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
         }}).start();
    }
    
     public static void sendTurnoDetalleOffline( final Estacionamiento estacionamiento){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<TurnoDetalles> turnosOffline = TurnoDetalles.getTurnoDetalleOffline();
               Iterator<TurnoDetalles> iterator = turnosOffline.iterator();
               while(iterator.hasNext()){
                   try {
                       sendTurnoDetalle(iterator.next(),estacionamiento);
                       Thread.sleep(500);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Rest.class.getName()).log(Level.SEVERE, null, ex);
                   }  
               }  
         }}).start();
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
