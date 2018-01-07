package com.ifmo.iad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vaadin.ui.UI;
import org.glassfish.jersey.client.ClientConfig;
import org.mindrot.jbcrypt.BCrypt;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class RestClient {
    ClientConfig config;
    Client client;
    WebTarget webtarget;

    public RestClient(){
        config = new ClientConfig();
        client = ClientBuilder.newClient(config);
        webtarget = client.target("http://192.168.43.170:8090");
    }

    public String getAnswerText(){

        String answer =
        webtarget.path("user").
                path("results").
                request(MediaType.APPLICATION_JSON).
                get(String.class);
        System.out.println(answer);
        System.out.println(answer);
        System.out.println(answer);
//        System.out.println(answer.substring(0,answer.indexOf("}{")+1));
        System.out.println("_____________-");
        UserEntity user = new Gson().fromJson(answer.substring(0,answer.indexOf("}{")+1),UserEntity.class);
//        UserEntity user = new Gson().fromJson(answer,UserEntity.class);
        return answer.substring(0,answer.indexOf("}{")+1);
    }

    public boolean login (String login, String password){
        String hash =
                webtarget.path("login")
                        .queryParam("login",login)
                        .request()
                        .get(String.class);


        System.out.println(hash);
        boolean valid = false;
        try{
            valid = BCrypt.checkpw(password, hash);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        return valid;
    }
    public PointEntity checkDot(BatmanParams point){
        String answer =
                webtarget.path("user")
                        .path("check")
                        .queryParam("x",point.getX())
                        .queryParam("y",point.getY())
                        .queryParam("zoom",point.getZoom())
                        .queryParam("login", UI.getCurrent().getSession().getAttribute("user"))
                        .request()
                        .get(String.class);
        System.out.println(answer);
        return new Gson().fromJson(answer,PointEntity.class);
    }

    public List<PointEntity> getPointsByRange (double range){
        String answer =
                webtarget.path("user")
                        .path("results-by-zoom")
                        .queryParam("zoom",range)
                        .queryParam("login", UI.getCurrent().getSession().getAttribute("user"))
                        .request()
                        .get(String.class);
        System.out.println(answer);

        Type listType = new TypeToken<LinkedList<PointEntity>>(){}.getType();
        return new Gson().fromJson(answer,listType);
    }

    public boolean signup (String login, String password){

        String answer =
                webtarget.path("register")
                        .queryParam("login",login)
                        .queryParam("password",BCrypt.hashpw(password,BCrypt.gensalt()))
                        .request()
                        .get(String.class);

        System.out.println(answer);
        return Boolean.parseBoolean(answer);
    }



}
