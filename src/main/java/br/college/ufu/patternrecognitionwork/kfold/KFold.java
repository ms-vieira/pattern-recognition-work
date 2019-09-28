package br.college.ufu.patternrecognitionwork.kfold;

import br.college.ufu.patternrecognitionwork.data.File;
import br.college.ufu.patternrecognitionwork.data.Line;
import br.college.ufu.patternrecognitionwork.knn.Knn;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class KFold {

    private Long numberFolds;

    private File fileTraining;

    private Map<Long, List<Line>> folds;

    public KFold(Long numberFolds, File fileTraining) {
        this.numberFolds = numberFolds;
        this.fileTraining = fileTraining;
        this.folds = new HashMap<>();

        initializeFolds();
    }

    private void initializeFolds() {
        ArrayList<Line> lines = new ArrayList<>(fileTraining.getLines());
        Collections.shuffle(lines);

        int numberRecords = lines.size();
        int recordsFold = (int) (numberRecords / this.numberFolds);
        int remainder = numberRecords - (recordsFold * this.numberFolds.intValue());

        int[] fold = new int[numberFolds.intValue()];

        for (int i = 0; i < fold.length; i++)
            fold[i] = recordsFold;

        int aux = 0;

        while (remainder > 0) {
            fold[aux]++;
            remainder--;
            aux++;
        }

        int i = 0;

        for (; i < numberFolds; i++)
            folds.put((long) i, lines.subList(i * fold[i], (i * fold[i]) + fold[i]));
    }

    public BigDecimal test(Long numeroVizinhos) {
        Long hits = folds
                .keySet()
                .stream()
                .map(element -> {
                    List<Line> trainingLines = folds
                            .keySet()
                            .stream()
                            .filter(foldId -> !foldId.equals(element))
                            .map(foldId -> folds.get(foldId))
                            .flatMap(List::stream)
                            .collect(Collectors.toList());

                    return folds
                            .get(element)
                            .stream()
                            .map(foldLine -> {
                                Knn knn = new Knn(numeroVizinhos, trainingLines);
                                List<Line> vizinhos = knn.getNext(foldLine);
                                Long topClass = knn.getClassTopRated(vizinhos);

                                if (topClass.equals(foldLine.getIdClass()))
                                    return 1L;

                                return 0L;
                            })
                            .reduce(0L, Long::sum);
                })
                .reduce(0L, Long::sum);

        BigDecimal result = new BigDecimal((hits / (this.fileTraining.getNumberSamples() * 1.0)) * 100)
                .setScale(3, RoundingMode.HALF_UP);

        System.out.printf("Hits: %s\n", result);

        return result;
    }
}
