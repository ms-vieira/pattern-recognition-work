package br.college.ufu.patternrecognitionwork.math;


import br.college.ufu.patternrecognitionwork.data.Line;

import java.util.Iterator;

public class EuclideanDistance {

    public Double evaluate(Line lineOne, Line lineTwo) {
        Iterator<Double> attributesDataLineOne = lineOne.getAttributes().iterator();
        Iterator<Double> attributesDataLineTwo = lineTwo.getAttributes().iterator();

        Double squareSum = 0.0;

        while (attributesDataLineOne.hasNext()) {
            Double attributeOne = attributesDataLineOne.next();
            Double attributeTwo = attributesDataLineTwo.next();

            squareSum += Math.pow(attributeOne - attributeTwo, 2);
        }

        return Math.sqrt(squareSum);
    }
}
