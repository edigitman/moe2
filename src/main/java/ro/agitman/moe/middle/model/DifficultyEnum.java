package ro.agitman.moe.middle.model;

import java.io.Serializable;

/**
 * Created by d-uu31cq on 19.08.2016.
 */
public enum DifficultyEnum implements Serializable {
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD");

    private String difficultyValue;

    DifficultyEnum(String difficultyValue) {
        this.difficultyValue = difficultyValue;
    }

    public String getDifficultyValue() {
        return difficultyValue;
    }
}
