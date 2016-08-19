package ro.agitman.moe.middle.model;

import java.io.Serializable;

/**
 * Created by d-uu31cq on 19.08.2016.
 */
public enum ItemTypeEnum implements Serializable{

    UNIQUE("UNIQUE"),
    MULTIPLE("MULTIPLE"),
    TEXT("TEXT");

    private String value;

    ItemTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
