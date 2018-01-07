package com.ifmo.iad;

import com.vaadin.ui.Notification;
import org.vaadin.hezamu.canvas.Canvas;

import java.util.List;


public class DrawBatmanCanvas {
    private Canvas canvas;
    private int plusX = (7 * 20) + 250;
    private int plusY = (4 * 20) + 250;
    private int multiplier = 5;
    private int range;

    public DrawBatmanCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void drawBatman(int range){
//        canvas.fillRect(0,0,100,100);
        canvas.clear();
        double zoom = range * multiplier;
        this.range = range;
        canvas.setWidth("773px");
        canvas.setHeight("600px");
        canvas.beginPath();
        canvas.setLineWidth(0.1);
        canvas.setStrokeStyle("#2aa5a0");
        canvas.moveTo(-7 * zoom + plusX, -0 + plusY);
        double xz;
        double x;
        double absX;
        double y;

        for (xz = -7 * zoom; xz < -3 * zoom; xz ++) {
            x = xz / zoom;
            absX = Math.abs(x);
            y = 1.5 * Math.sqrt((-Math.abs(absX - 1)) * Math.abs(3 - absX) / ((absX - 1) * (3 - absX))) * (1 + Math.abs(absX - 3) / (absX - 3)) * Math.sqrt(1 - (x / 7) * (x / 7)) + (4.5 + 0.75 * (Math.abs(x - 0.5) + Math.abs(x + 0.5)) - 2.75 * (Math.abs(x - 0.75) + Math.abs(x + 0.75))) * (1 + Math.abs(1 - absX) / (1 - absX));
            canvas.lineTo(xz + plusX, -y * zoom + plusY);
        }
        for (xz = -3 * zoom; xz < -1 * zoom - 1; xz++) {
            x = xz / zoom;
            absX = Math.abs(x);
            y = (2.71052 + 1.5 - 0.5 * absX - 1.35526 * Math.sqrt(4 - (absX - 1) * (absX - 1)));
            canvas.lineTo(xz + plusX, -y * zoom + plusY);
        }
        canvas.lineTo(-1 * zoom + plusX, -3 * zoom + plusY);
        canvas.lineTo((-0.5 * zoom) + plusX, -(2.2 * zoom) + plusY);
        canvas.lineTo((0.5 * zoom) + plusX, -(2.2 * zoom) + plusY);
        canvas.lineTo(1 * zoom + plusX, -3 * zoom + plusY);
        for (xz = 1 * zoom + 1; xz < 3 * zoom + 1; xz++) {
            x = xz / zoom;
            absX = Math.abs(x);
            y = (2.71052 + 1.5 - 0.5 * absX - 1.35526 * Math.sqrt(4 - (absX - 1) * (absX - 1)));
            canvas.lineTo(xz + plusX, -y * zoom + plusY);
        }
        for (xz = 3 * zoom + 1; xz < 7 * zoom + 1; xz++) {
            x = xz / zoom;
            absX = Math.abs(x);
            y = 1.5 * Math.sqrt((-Math.abs(absX - 1)) * Math.abs(3 - absX) / ((absX - 1) * (3 - absX))) * (1 + Math.abs(absX - 3) / (absX - 3)) * Math.sqrt(1 - (x / 7) * (x / 7)) + (4.5 + 0.75 * (Math.abs(x - 0.5) + Math.abs(x + 0.5)) - 2.75 * (Math.abs(x - 0.75) + Math.abs(x + 0.75))) * (1 + Math.abs(1 - absX) / (1 - absX));
            canvas.lineTo(xz + plusX, -y * zoom + plusY);
        }

        for (xz = 7 * zoom; xz > 4 * zoom; xz--) {
            x = xz / zoom;
            absX = Math.abs(x);
            y = (-3) * Math.sqrt(1 - (x / 7) * (x / 7)) * Math.sqrt(Math.abs(absX - 4) / (absX - 4));
            canvas.lineTo(xz + plusX, -y * zoom + plusY);
        }
        for (xz = 4 * zoom; xz > -4 * zoom; xz--) {
            x = xz / zoom;
            absX = Math.abs(x);
            y = Math.abs(x / 2) - 0.0913722 * x * x - 3 + Math.sqrt(1 - (Math.abs(absX - 2) - 1) * (Math.abs(absX - 2) - 1));
            canvas.lineTo(xz + plusX, -y * zoom + plusY);
        }

        for (xz = -4 * zoom - 1; xz > -7 * zoom - 1; xz--) {
            x = xz / zoom;
            absX = Math.abs(x);
            y = (-3) * Math.sqrt(1 - (x / 7) * (x / 7)) * Math.sqrt(Math.abs(absX - 4) / (absX - 4));
            canvas.lineTo(xz + plusX, -y * zoom + plusY);
        }
        canvas.setFillStyle("#2aa5a0");
        canvas.fill();


        canvas.setLineWidth(0.5);
        canvas.beginPath();
        canvas.setStrokeStyle("black");
        canvas.moveTo(0, plusY);
        canvas.lineTo(2 * plusX, plusY);
        canvas.lineTo(2 * plusX - 5, plusY - 3);
        canvas.moveTo(2 * plusX - 5, plusY + 3);
        canvas.lineTo(2 * plusX, plusY);


        canvas.moveTo(plusX, 2 * plusY);
        canvas.lineTo(plusX, 0);
        canvas.lineTo(plusX - 3, 0 + 5);
        canvas.moveTo(plusX + 3, 0 + 5);
        canvas.lineTo(plusX, 0);
        canvas.stroke();
        // Разметка
        //    По вектору Х
        canvas.moveTo(-7 * zoom + plusX, plusY - 3);
        canvas.lineTo(-7 * zoom + plusX, plusY + 3);
        canvas.moveTo(plusX - (7 * zoom) / 2, plusY - 3);
        canvas.lineTo(plusX - (7 * zoom) / 2, plusY + 3);
        canvas.moveTo(plusX + (7 * zoom) / 2, plusY - 3);
        canvas.lineTo(plusX + (7 * zoom) / 2, plusY + 3);
        canvas.moveTo(plusX + (7 * zoom), plusY - 3);
        canvas.lineTo(plusX + (7 * zoom), plusY + 3);
        //    По вектору Y
        canvas.moveTo(plusX - 3, plusY - 7 * zoom / 2);
        canvas.lineTo(plusX + 3, plusY - 7 * zoom / 2);
        canvas.moveTo(plusX - 3, plusY + 7 * zoom / 2);
        canvas.lineTo(plusX + 3, plusY + 7 * zoom / 2);

        //    Числа для разметки
        canvas.setFont("10px \"Tahoma\"");
        canvas.setFillStyle("black");
        canvas.fillText("-" + range, -7 * zoom - 5 + plusX, plusY - 8,20);
        canvas.fillText("-" + range + "/2", plusX - (7 * zoom) / 2 - 10, plusY - 8,20);
        canvas.fillText(range + "/2", plusX + (7 * zoom) / 2 - 10, plusY - 8,20);
        canvas.fillText(""+range, plusX + (7 * zoom) - 5, plusY - 8,20);

        canvas.fillText(range + "/2", plusX - 30, plusY - 7 * zoom / 2 + 3,20);
        canvas.fillText("-" + range + "/2", plusX - 30, plusY + 7 * zoom / 2 + 3,20);



        this.canvas.stroke();
        this.canvas.closePath();

    }

    public void addMouseDownListener(){

    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public int getPlusX() {
        return plusX;
    }

    public void setPlusX(int plusX) {
        this.plusX = plusX;
    }

    public int getPlusY() {
        return plusY;
    }

    public void setPlusY(int plusY) {
        this.plusY = plusY;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public BatmanParams drawDotWhenMouseDown(int xDot, int yDot){
        canvas.beginPath();
        long neededX = Math.round((xDot- plusX)/7/multiplier);
        long neededY = -Math.round((yDot- plusY)/7/multiplier);
        canvas.setFillStyle("rgb(255,92,90)");
        canvas.arc(neededX * 7 * multiplier + plusX, -neededY * 7 * multiplier + plusY, 3, 0, Math.PI * 2,false);
        canvas.fill();
        canvas.closePath();
        Notification.show("X: "+neededX+", Y: "+neededY);
        return new BatmanParams((int)neededX,(int)neededY,(int)this.range);
    }

    public void drawDotFromList(List<PointEntity> listOfPoints){
        listOfPoints.forEach(object->drawDot(object.getX(),object.getY(),object.isInBatman()));
    }
    public void drawDot (double x, double y, boolean isInBatman){
        canvas.beginPath();
        if (isInBatman){
            canvas.setFillStyle("green");
        }else{
            canvas.setFillStyle("red");
        }
        canvas.arc(x * 7 * multiplier + plusX, -y * 7 * multiplier + plusY, 3, 0, Math.PI * 2,false);
        canvas.fill();
        canvas.closePath();
    }

    public void drawDotOnButtonClick(int x, int y){
        canvas.beginPath();
        canvas.setFillStyle("rgb(255,92,90)");
        canvas.arc(x * 7 * multiplier + plusX, -y * 7 * multiplier + plusY, 3, 0, Math.PI * 2,false);
        canvas.fill();
        canvas.closePath();
    }

}
