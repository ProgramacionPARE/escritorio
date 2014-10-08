///*
// * ProyectoAristaApp.java
// */
//
//package proyectopare;
//
//import org.jdesktop.application.Application;
//import org.jdesktop.application.SingleFrameApplication;
//
//
//public class ProyectoPareApp extends SingleFrameApplication {
//    private ProyectoPareView p;
//
//    
//    @Override protected void startup() {
//        p = new ProyectoPareView(this);
//        show(p);
//        p.initLogin();
//    }
//
//  
//    @Override protected void configureWindow(java.awt.Window root) {
//    }
//
//
//    public  ProyectoPareView getView() {
//        return p;
//    }
//    
//    public static ProyectoPareApp getApplication() {
//        return Application.getInstance(ProyectoPareApp.class);
//    }
//
//
//    public static void main(String[] args) {
//       launch(ProyectoPareApp.class, args);
//    }
//
//}

//class pojo1
//{
//String name;
//String age;
////generate setter and getters
//}
//
//once you set the variables in pojo1 class you can send that using the following code
//
//String postUrl="www.site.com";// put in your url
//Gson gson= new Gson();
//HttpPost post = new HttpPost(postUrl);
//StringEntity  postingString =new StringEntity(gson.toJson(pojo1));//convert your pojo to   json
//post.setEntity(postingString);
//post.setHeader("Content-type", "application/json");
//HttpResponse  response = httpClient.execute(post);
//
//and these are the imports
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//
//and for GSON
//
//import com.google.gson.Gson;