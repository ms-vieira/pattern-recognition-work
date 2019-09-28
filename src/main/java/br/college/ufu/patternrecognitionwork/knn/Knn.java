package br.college.ufu.patternrecognitionwork.knn;

import br.college.ufu.patternrecognitionwork.data.Line;
import br.college.ufu.patternrecognitionwork.math.EuclideanDistance;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Knn {

    private Long numberNext;

    private List<Line> lines;

    private EuclideanDistance euclideanDistance;

    public Knn(Long numberNext, List<Line> lines) {
        this.numberNext = numberNext;
        this.lines = lines;
        this.euclideanDistance = new EuclideanDistance();
    }

    public List<Line> getNext(Line lineTeste) {
        List<Pair<Line, Double>> distancias = new ArrayList<>();

        for (Line lineTreino : lines)
            distancias.add(new Pair<>(lineTreino, euclideanDistance.evaluate(lineTeste, lineTreino)));

        return distancias
                .stream()
                .sorted(Comparator.comparing(Pair::getValue))
                .map(Pair::getKey)
                .collect(Collectors.toList())
                .subList(0, numberNext.intValue());
    }

    public Long getClassTopRated(List<Line> kVizinhos) {
        Map<Long, Long> votes = new HashMap<>();

        kVizinhos.forEach(element -> {
            Long idClassed = element.getIdClass();

            if (!votes.containsKey(idClassed))
                votes.put(idClassed, 1L);
            else
                votes.put(idClassed, votes.get(idClassed) + 1);
        });

        Long biggerClass = 0L;
        Long biggerValue = 0L;

        for (Long idClasse : votes.keySet()) {
            Long value = votes.get(idClasse);

            if (value > biggerValue) {
                biggerValue = value;
                biggerClass = idClasse;
            }
        }

        return biggerClass;
    }
}
