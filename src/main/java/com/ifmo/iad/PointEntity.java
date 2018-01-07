package com.ifmo.iad;

import java.util.Date;

public class PointEntity {
    private Long id;
    private double x;
    private double y;
    private double zoom;
    private boolean inBatman;
    private long currentTime;
    private long processTime;

    public PointEntity(Long id, double x, double y, double zoom, boolean inBatman, long currentTime, long processTime, UserEntity userEntity) {
        this.x = x;
        this.y = y;
        this.zoom = zoom;
        this.inBatman = inBatman;
        this.currentTime = currentTime;
        this.processTime = processTime;
        this.userEntity = userEntity;
    }

    public PointEntity(double x, double y, double zoom, boolean inBatman) {
        this.x = x;
        this.y = y;
        this.zoom = zoom;
        this.inBatman = inBatman;
    }
    public PointEntity(double x, double y, double zoom) {
        this.x = x;
        this.y = y;
        this.zoom = zoom;
    }

    public PointEntity() {
    }


    private UserEntity userEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public boolean isInBatman() {
        return inBatman;
    }

    public void setInBatman(boolean inBatman) {
        this.inBatman = inBatman;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
