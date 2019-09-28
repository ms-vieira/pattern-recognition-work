package br.college.ufu.patternrecognitionwork.data;

import br.college.ufu.patternrecognitionwork.math.ZScore;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class File implements Serializable {

    private static final long serialVersionUID = 8433492005981236130L;

    private Long numberSamples;

    private Long numberAttribute;

    private Set<Long> idClasses;

    private List<Line> lines;

    private Map<Long, List<Line>> dataLineClass;

    public File(InputStream dataFile, Boolean useZScore) {
        this.lines = new ArrayList<>();
        this.idClasses = new HashSet<>();
        this.dataLineClass = new HashMap<>();

        initializeFile(dataFile, useZScore);
    }

    private List<Number> computeLine(String firstLine) {
        List<Number> result = new ArrayList<>();

        String[] fileWords = firstLine.split("\\s");

        Arrays
                .asList(fileWords)
                .forEach(element -> {
                    if (element.contains("."))
                        result.add(Double.parseDouble(element));
                    else
                        result.add(Long.parseLong(element));
                });

        return result;
    }

    private void initializeFile(InputStream arquivoDados, Boolean useZScore) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(arquivoDados));

        Boolean firstLineComputed = false;

        Iterator<String> linesIterator = bufferedReader.lines().iterator();

        while (linesIterator.hasNext()) {
            List<Number> computedLine = computeLine(linesIterator.next());

            if (!firstLineComputed) {
                this.numberSamples = (Long) computedLine.get(0);
                this.numberAttribute = (Long) computedLine.get(1);

                firstLineComputed = true;
            } else {
                Line line = new Line(
                        (Long) computedLine.get(this.numberAttribute.intValue()),
                        computedLine.subList(0, this.numberAttribute.intValue())
                                .stream()
                                .map(element -> (Double) element)
                                .collect(Collectors.toList())
                );

                Long idClasse = line.getIdClass();

                this.lines.add(line);
                this.idClasses.add(idClasse);

                if (!this.dataLineClass.containsKey(idClasse))
                    this.dataLineClass.put(idClasse, new ArrayList<>());

                this.dataLineClass.get(idClasse).add(line);
            }
        }

        if (useZScore) {
            ZScore zScore = new ZScore();

            for (int i = 0; i < numberAttribute; i++) {
                final int indexFinal = i;
                List<Double> attributes = lines
                        .stream()
                        .map(element -> element.getAttributes().get(indexFinal))
                        .collect(Collectors.toList());

                Double average = zScore.average(attributes);
                Double standardDeviation = zScore.standardDeviation(attributes, average);

                for (int j = 0; j < numberSamples; j++) {
                    Double atributo = lines.get(j).getAttributes().get(i);
                    Double zScoreEvaluated = zScore.evaluate(atributo, average, standardDeviation);

                    lines.get(j).getAttributes().set(i, zScoreEvaluated);
                }
            }
        }
    }

    public Long getNumberSamples() {
        return numberSamples;
    }

    public Long getNumberAttribute() {
        return numberAttribute;
    }

    public Set<Long> getIdClasses() {
        return idClasses;
    }

    public List<Line> getLines() {
        return lines;
    }

    public Map<Long, List<Line>> getDataLineClass() {
        return dataLineClass;
    }
}
