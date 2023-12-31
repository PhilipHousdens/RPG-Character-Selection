package com.philip.cmu.chapter1.model.item;

import javafx.scene.input.DataFormat;

import java.io.Serializable;
import java.util.ArrayList;

public class BasedEqiupment extends ArrayList<BasedEqiupment> implements Serializable {
    public static final DataFormat DATA_FORMAT = new DataFormat("src.main.java.com.philip.cmu.chapter1.model.item.BasedEquipment");
    protected String name;
    protected String imgpath;

    public String getName() {
        return name;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
}
