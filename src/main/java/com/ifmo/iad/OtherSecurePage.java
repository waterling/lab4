package com.ifmo.iad;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Button.ClickEvent;
import javax.ws.rs.client.WebTarget;
import javax.xml.bind.JAXBException;
import java.io.IOException;


public class OtherSecurePage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    private Label otherSecure;
    public static final String NAME = "OtherSecure";
    private Button mainsecure;
    public OtherSecurePage() {
        RestClient restClient = new RestClient();
        Button testGet = new Button("Test Get");
        testGet.addClickListener(clickEvent -> {
                Notification.show(restClient.getAnswerText());
                System.out.println("fFDsffffffff__________________________________________________________________");
//            Notification.show("kik");
        });
        CssLayout main = new CssLayout();
        addComponent(main);
        CssLayout firstRow = new CssLayout();
        firstRow.setStyleName("headerFirstRow");
        CssLayout secondRow = new CssLayout();
        firstRow.setStyleName("headerSecondRow");
        main.addComponent(firstRow);
        main.addComponent(secondRow);

        mainsecure = new Button("Main Secure Area");
        mainsecure.addClickListener(new ClickListener(){
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                Page.getCurrent().setUriFragment("!"+SecurePage.NAME);
            }
        });
        otherSecure = new Label("Other Secure Page ...");
        addComponent(otherSecure);
        addComponent(mainsecure);
        addComponent(testGet);
    }

    public void enter(ViewChangeEvent event){

    }

}
