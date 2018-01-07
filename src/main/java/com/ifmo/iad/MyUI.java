package com.ifmo.iad;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.vaadin.hezamu.canvas.Canvas;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyUI.class)
    public static class Servlet extends VaadinServlet {
    }
    public static Authentication AUTH;
    @Override
    protected void init(VaadinRequest request) {
        AUTH = new Authentication();
        new Navigator(this, this);
        getNavigator().addView(LoginPage.NAME, LoginPage.class);
        getNavigator().addView(SignUpPage.NAME,SignUpPage.class);
        getNavigator().setErrorView(LoginPage.class);
        Page.getCurrent().addUriFragmentChangedListener(new Page.UriFragmentChangedListener() {
            @Override
            public void uriFragmentChanged(Page.UriFragmentChangedEvent event) {
                router(event.getUriFragment());
            }
        });
        router("");
    }
    private void router(String route){
        if(!(route.equals("")||route.equals("!Login"))){
            Notification.show(route);
        }
        if(getSession().getAttribute("user") != null){
            getNavigator().addView(SecurePage.NAME, SecurePage.class);
            getNavigator().addView(OtherSecurePage.NAME, OtherSecurePage.class);
            if(route.equals("!OtherSecure")){
                getNavigator().navigateTo(OtherSecurePage.NAME);
            }else{
                getNavigator().navigateTo(SecurePage.NAME);
            }
        }else{
            if(route.equals("!SignUp")){
                Notification.show("Welcome");
                getNavigator().navigateTo(SignUpPage.NAME);
            }else{
                getNavigator().navigateTo(LoginPage.NAME);
            }
        }
    }

}
