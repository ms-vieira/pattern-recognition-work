package br.college.ufu.patternrecognitionwork.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Line implements Serializable {

    private static final long serialVersionUID = -5534947682679209921L;

    private Long idClass;

    private List<Double> attributes;

    public Line() {
        this.attributes = new ArrayList<>();
    }

    public Line(Long idClass, List<Double> attributes) {
        this.idClass = idClass;
        this.attributes = attributes;
    }

    public Long getIdClass() {
        return idClass;
    }

    public void setIdClass(Long idClass) {
        this.idClass = idClass;
    }

    public List<Double> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Double> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(idClass, line.idClass) &&
                Objects.equals(attributes, line.attributes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idClass, attributes);
    }

    @Override
    public String toString() {
        return "DataLine{" +
                "idClass=" + idClass +
                ", attributes=" + attributes +
                '}';
    }
}
