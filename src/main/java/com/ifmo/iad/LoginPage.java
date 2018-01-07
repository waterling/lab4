package com.ifmo.iad;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

public class LoginPage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "Login";
    private boolean hasMuted = true;
    public LoginPage(){

        Resource res = new ThemeResource("batmanMain.mp4");
        Resource res2 = new ThemeResource("batmanImg.png");
        Resource muteRes = new ThemeResource("mute.png");
        Image muteImg = new Image();
        muteImg.addStyleName("mute-img");
        muteImg.setSource(muteRes);
        muteImg.setWidth("100px");
        muteImg.setHeight("100px");
        Image img = new Image();



        img.setSource(res2);
        img.setWidth("100%");
        img.addStyleName("bg-img");
        Video bgVideo = new Video();
        bgVideo.setMuted(hasMuted);
        bgVideo.setSource(res);
        bgVideo.setAutoplay(true);
        bgVideo.setLoop(true);
        bgVideo.setShowControls(false);
        bgVideo.setSizeFull();
        bgVideo.setHtmlContentAllowed(true);
        bgVideo.addStyleName("bg-video");
        AbsoluteLayout absoluteLayout = new AbsoluteLayout();
        absoluteLayout.setWidth("100%");
        absoluteLayout.setHeight("100%");


        // top-level component properties
        setWidth("100.0%");
        setHeight("100.0%");
        absoluteLayout.addStyleName("outlined");
        Panel panel = new Panel("Вход");
        panel.setSizeUndefined();
        panel.addStyleName("absolutelayout-childcomponent");
        absoluteLayout.addComponent(panel,"top: 10%; left: 38%");
        absoluteLayout.addComponent(bgVideo,"top: 0%; left: 0%");
        absoluteLayout.addComponent(img,"top: 0%; left: 0%");
        absoluteLayout.addComponent(muteImg,"top: 50px; right: 50px");
        addComponent(absoluteLayout);
        muteImg.addClickListener(clickEvent -> {
            hasMuted=!hasMuted;
            if (hasMuted){
                muteImg.setSource(muteRes);

            }else{
                muteImg.setSource(new ThemeResource("unMute.png"));
            }
            bgVideo.setMuted(hasMuted);
        });
        FormLayout content = new FormLayout();
        TextField username = new TextField("Имя пользователя");
        username.setValue("myuser");
        content.addComponent(username);
        PasswordField password = new PasswordField("Пароль");
        password.setValue("mypass");
        content.addComponent(password);

        Button send = new Button("Войти");
        send.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        send.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if(MyUI.AUTH.authenticate(username.getValue(),password.getValue() )){
                    VaadinSession.getCurrent().setAttribute("user", username.getValue());
                    getUI().getNavigator().addView(SecurePage.NAME, SecurePage.class);
                    getUI().getNavigator().addView(OtherSecurePage.NAME, OtherSecurePage.class);
                    Page.getCurrent().setUriFragment("!"+SecurePage.NAME);
                }else{
                    Notification.show("Неправильно введены данные", Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        Button signUp = new Button("Регистрация");
        signUp.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Page.getCurrent().setUriFragment("!"+SignUpPage.NAME);
            }
        });
        content.addComponent(send);
        content.addComponent(signUp);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
//        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
