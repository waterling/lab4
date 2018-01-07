package com.ifmo.iad;

import com.vaadin.annotations.Widgetset;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.hezamu.canvas.Canvas;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class SecurePage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    private Label secure;
    private Label currentUser;
    private Button otherSecure;
    private Button logout;
    private Canvas canvas;
    public static final String NAME = "Secure";
    private VerticalLayout mainLayout;
    private VerticalLayout header;
    private VerticalLayout content;
    private VerticalLayout menu;
    private HorizontalLayout footer;

    private HorizontalLayout firstHeaderRow;
    private HorizontalLayout secondHeaderRow;
    private VerticalLayout names;

    private Panel firstMenu;
    private Panel secondMenu;
    private Panel panel1;
    private Panel panel2;
    private Window windowMenu;

    private Button menuButton;
    private Grid<PointEntity> table;
    private List<PointEntity> listOfPoints;

    public SecurePage() {
        table = new Grid<>();
        table.setWidth("773px");
        listOfPoints = new LinkedList<>();
        ListDataProvider<PointEntity> dataProvider = new ListDataProvider<>(listOfPoints);
        panel1 = new Panel();
        panel2 = new Panel();
        canvas = new Canvas();
        DrawBatmanCanvas batmanCanvas = new DrawBatmanCanvas(canvas);
        mainLayout = new VerticalLayout();
        header = new VerticalLayout();
        content = new VerticalLayout();
        content.setStyleName("content-container");
        menu = new VerticalLayout();
        footer = new HorizontalLayout();
        firstHeaderRow = new HorizontalLayout();
        secondHeaderRow = new HorizontalLayout();
        panel1.setStyleName("headerFirstRow");
        panel2.setStyleName("headerSecondRow");
        panel1.setContent(firstHeaderRow);
        panel2.setContent(secondHeaderRow);
        firstHeaderRow.setWidth("773px");
        secondHeaderRow.setWidth("773px");
        header.addComponents(panel1, panel2);

        mainLayout.addComponents(header, content, footer);


        windowMenu = new Window("Меню управления");
        windowMenu.setWidth(500.0f, Unit.PIXELS);
        windowMenu.addStyleName("window-menu");
        windowMenu.setResizable(false);
        firstMenu = new Panel();
        secondMenu = new Panel();
        menu = new VerticalLayout();
        windowMenu.setContent(menu);
        Button submit = new Button("Поставить точку");
        submit.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(Button.ClickEvent event) {

            }
        });

        menu.addComponents(firstMenu, secondMenu, submit);
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        RadioButtonGroup range = new RadioButtonGroup<>("", data);
        range.addValueChangeListener(event -> {
            Notification.show(String.valueOf(Integer.parseInt(String.valueOf(event.getValue()))));
            batmanCanvas.drawBatman(Integer.parseInt(String.valueOf(event.getValue())));
            listOfPoints.clear();
            RestClient rest = new RestClient();
            listOfPoints.addAll(rest.getPointsByRange(Integer.parseInt(String.valueOf(event.getValue()))));
            table.setDataProvider(new ListDataProvider<>(listOfPoints));
            batmanCanvas.drawDotFromList(listOfPoints);
        });
        range.setItemCaptionGenerator(Object::toString);
        range.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);


        VerticalLayout temp = new VerticalLayout();
        VerticalLayout temp2 = new VerticalLayout();
        Label rangeLabel = new Label("Choose Range");
        temp.addComponents(rangeLabel, range);

        firstMenu.setContent(temp);
        secondMenu.setContent(temp2);
        addComponent(mainLayout);

        List<Integer> xCoord = Arrays.asList(-4, -3, -2, -1, 0, 1, 2, 3, 4);


        Label xLabel = new Label("Choose X");
        Label yLabel = new Label("Choose Y");
        RadioButtonGroup xRadioGroup = new RadioButtonGroup<>("", xCoord);
        xRadioGroup.setItemCaptionGenerator(Object::toString);
        TextField yField = new TextField();
        yField.setPlaceholder("Введите Y");
        yField.addValueChangeListener(valueChangeEvent -> {
            try {
                int newInt = Integer.parseInt(valueChangeEvent.getValue());
                if (newInt >= -5 && newInt <= 5) {
                    if (!xRadioGroup.isEmpty()) {
                        submit.setEnabled(true);
                    } else {
                        xRadioGroup.setComponentError(new UserError("Выберите Х"));
                    }
                    yField.setComponentError(null);

                } else {
                    yField.setComponentError(new UserError("От -5 до 5"));
                    submit.setEnabled(false);
                }

            } catch (NumberFormatException e) {
                submit.setEnabled(false);
                yField.setComponentError(new UserError("Это должно быть целое число"));
            }
        });
        submit.setEnabled(false);
        submit.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(Button.ClickEvent event) {
                Notification.show("X: " + String.valueOf(xRadioGroup.getValue()) + "Y: " + yField.getValue());


                batmanCanvas.drawDotOnButtonClick(Integer.parseInt(String.valueOf(xRadioGroup.getValue())), Integer.parseInt(yField.getValue()));
                RestClient rest = new RestClient();
                PointEntity point = rest.checkDot(
                        new BatmanParams(
                                Integer.parseInt(String.valueOf(xRadioGroup.getValue())),
                                Integer.parseInt(yField.getValue()),
                                Integer.parseInt(String.valueOf(range.getValue()))));
                listOfPoints.add(point);
                table.setDataProvider(new ListDataProvider<>(listOfPoints));
                batmanCanvas.drawDot(point.getX(), point.getY(), point.isInBatman());


            }
        });

        canvas.addMouseDownListener(mouseEventDetails -> {
            batmanCanvas.drawDotWhenMouseDown(mouseEventDetails.getRelativeX(), mouseEventDetails.getRelativeY());
            RestClient rest = new RestClient();
            int neededX = Math.round((mouseEventDetails.getRelativeX() - batmanCanvas.getPlusX()) / 7 / batmanCanvas.getMultiplier());
            int neededY = -Math.round((mouseEventDetails.getRelativeY() - batmanCanvas.getPlusY()) / 7 / batmanCanvas.getMultiplier());
            PointEntity point = rest.checkDot(new BatmanParams(neededX, neededY, batmanCanvas.getRange()));
            listOfPoints.add(point);
            table.setDataProvider(new ListDataProvider<>(listOfPoints));
            batmanCanvas.drawDot(point.getX(), point.getY(), point.isInBatman());
        });

        range.setSelectedItem(data.get(7));

        xRadioGroup.addValueChangeListener(event -> {
            xRadioGroup.setComponentError(null);
            if (yField.getComponentError() == null && !yField.getValue().isEmpty()) {
                submit.setEnabled(true);
            }
            Notification.show(String.valueOf(Integer.parseInt(String.valueOf(event.getValue()))));
        });
        xRadioGroup.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        temp2.addComponents(xLabel, xRadioGroup, yLabel, yField);

        otherSecure = new Button("OtherSecure");
        otherSecure.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(Button.ClickEvent event) {
                Page.getCurrent().setUriFragment("!" + OtherSecurePage.NAME);
            }
        });


        menuButton = new Button("Меню управления");
        menuButton.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(Button.ClickEvent event) {
                Page.getCurrent().getUI().addWindow(windowMenu);
            }
        });
        logout = new Button("Выйти");
        logout.setIcon(VaadinIcons.EXIT);
        logout.addStyleName("logout");
        logout.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().removeView(SecurePage.NAME);
                getUI().getNavigator().removeView(OtherSecurePage.NAME);
                VaadinSession.getCurrent().setAttribute("user", null);
                Page.getCurrent().setUriFragment("");
            }
        });

        names = new VerticalLayout();
        secure = new Label("Плохотнюк Вадим");
        Label secure2 = new Label("Юсюмбели Владислав");
        Label secure3 = new Label("P3211");
        names.addComponents(secure,secure2,secure3);
        Resource res = new ThemeResource("logobatman.png");
        Image image = new Image(null, res);
        currentUser = new Label();
        firstHeaderRow.addComponents(image,names);
        image.setStyleName("logo-batman");
//        secondHeaderRow.addComponent(otherSecure);
        secondHeaderRow.addComponent(menuButton);
        secondHeaderRow.addComponent(currentUser);
        secondHeaderRow.addComponent(logout);

        table.setDataProvider(dataProvider);
        table.addColumn(PointEntity::getX)
                .setId("XNameColumn")
                .setCaption("X");
        table.addColumn(PointEntity::getY)
                .setId("YNameColumn")
                .setCaption("Y");
        table.addColumn(PointEntity::getZoom)
                .setId("ZoomNameColumn")
                .setCaption("R");
        table.addColumn(PointEntity::isInBatman)
                .setId("InBatmanNameColumn")
                .setCaption("В бэтмэне");
        table.addColumn(PointEntity::getCurrentTime)
                .setId("CurrTimeNameColumn")
                .setCaption("Время");
        table.addColumn(PointEntity::getProcessTime)
                .setId("ProcTimeNameColumn")
                .setCaption("Время работы");

        content.addComponents(canvas,table);

    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        currentUser.setValue("Добро пожаловать, " + VaadinSession.getCurrent().getAttribute("user").toString()+"!");
    }
}
