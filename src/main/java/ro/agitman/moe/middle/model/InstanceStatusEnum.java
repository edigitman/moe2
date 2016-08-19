package ro.agitman.moe.middle.model;

import java.io.Serializable;

/**
 * Created by d-uu31cq on 19.08.2016.
 */
public enum InstanceStatusEnum implements Serializable {

    // 1 - ciorna
    // 2 - pornit
    // 3 - inchis
    // 4 - spre corectare
    // 5 - corectat

    SKETCH("SKETCH"),
    STARTED("STARTED"),
    STOPPED("STOPPED"),
    REVIEWED("REVIEWED");

    private String status;

    private InstanceStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
