package com.ifmo.iad;

import com.vaadin.data.Binder;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import java.util.Objects;

public class SignUpPage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "SignUp";
    private boolean hasMuted = true;

    public SignUpPage() {
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
        bgVideo.setSource(res);
        bgVideo.setAutoplay(true);
        bgVideo.setLoop(true);
        bgVideo.setShowControls(false);
        bgVideo.setSizeFull();
        bgVideo.setHtmlContentAllowed(true);
        bgVideo.setMuted(true);
        bgVideo.addStyleName("bg-video");
        AbsoluteLayout absoluteLayout = new AbsoluteLayout();
        absoluteLayout.setWidth("100%");
        absoluteLayout.setHeight("100%");

        // top-level component properties
        setWidth("100.0%");
        setHeight("100.0%");
        absoluteLayout.addStyleName("outlined");
        Panel panel = new Panel("Регистрация");
        panel.setSizeUndefined();
        panel.addStyleName("absolutelayout-childcomponent");
        absoluteLayout.addComponent(panel,"top: 10%; left: 38%");
        absoluteLayout.addComponent(bgVideo,"top: 0%; left: 0%");
        absoluteLayout.addComponent(img,"top: 0%; left: 0%");
        absoluteLayout.addComponent(muteImg,"top: 50px; right: 50px");
        addComponent(absoluteLayout);

        FormLayout layout = new FormLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        panel.setContent(layout);
        muteImg.addClickListener(clickEvent -> {
            hasMuted=!hasMuted;
            if (hasMuted){
                muteImg.setSource(muteRes);
            }else{
                muteImg.setSource(new ThemeResource("unMute.png"));
            }
            bgVideo.setMuted(hasMuted);
        });

        TextField userNameField = new TextField("Имя пользователя");
        PasswordField passwordField = new PasswordField("Пароль");
        PasswordField confirmPasswordField = new PasswordField("Повторите пароль");

        Binder<Account> binder = new Binder<>();

        binder.forField(userNameField)
                .asRequired("Имя пользователя не может быть пустым")
                .bind(Account::getUserName, Account::setUserName);


        binder.forField(passwordField)
                .asRequired("Пароль не может быть пустым")
                .withValidator(new StringLengthValidator(
                        "Пароль должен быть длиннее 7 символов", 7, null))
                .bind(Account::getPassword, Account::setPassword);

        binder.forField(confirmPasswordField)
                .asRequired("Нужно повторить пароль")
                .bind(Account::getPassword, (person, password) -> {
                });

        binder.withValidator(Validator.from(account ->
                        passwordField.isEmpty() ||
                                confirmPasswordField.isEmpty() ||
                                Objects.equals(passwordField.getValue(), confirmPasswordField.getValue()),
                "Введите пароль и повторите его"));

        Label validationStatus = new Label();
        binder.setStatusLabel(validationStatus);

        binder.setBean(new Account());

        Button registerButton = new Button("Регистрация");
        Button login = new Button("Авторизация");
        layout.addComponents(validationStatus, userNameField, passwordField, confirmPasswordField, registerButton, login);
//        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        registerButton.setEnabled(false);
        registerButton.addClickListener(
                clickEvent -> {
//                    getUI().getNavigator().removeView(SignUpPage.NAME);
                    Account acc = binder.getBean();
                    RestClient restClient = new RestClient();
                    if(restClient.signup(acc.getUserName(),acc.getPassword())){
                        Notification.show("Регистрация прошла успешно");
                        Page.getCurrent().setUriFragment("!" + LoginPage.NAME);
                    }else {
                        Notification.show("Этот пользователь уже зарегестрирован", Notification.Type.ERROR_MESSAGE);
                    }

                });
        login.addClickListener(
                clickEvent -> {
                        Page.getCurrent().setUriFragment("!" + LoginPage.NAME);
                });

        binder.addStatusChangeListener(
                event -> registerButton.setEnabled(binder.isValid()));

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
