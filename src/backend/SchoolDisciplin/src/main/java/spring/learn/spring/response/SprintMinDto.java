package spring.learn.spring.response;

import java.io.Serializable;

public class SprintMinDto implements Serializable {
    private String value;
    private String label;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public SprintMinDto() {
    }

    public SprintMinDto(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
