package br.college.ufu.patternrecognitionwork.math;

import java.util.List;

public class ZScore {

    public ZScore() {
    }

    public Double standardDeviation(List<Double> attributes, Double average) {
        return Math.sqrt(
                attributes
                        .stream()
                        .map(element -> Math.pow(element - average, 2))
                        .reduce(0D, Double::sum) / attributes.size()
        );
    }

    public Double average(List<Double> attributes) {
        return attributes
                .stream()
                .reduce(0D, Double::sum) / attributes.size();
    }

    public Double evaluate(Double attribute, Double average, Double standardDeviation) {
        return (attribute - average) / standardDeviation;
    }
}
