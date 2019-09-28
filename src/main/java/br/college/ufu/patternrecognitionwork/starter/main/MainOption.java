package br.college.ufu.patternrecognitionwork.starter.main;

import br.college.ufu.patternrecognitionwork.data.File;
import br.college.ufu.patternrecognitionwork.kfold.KFold;
import br.college.ufu.patternrecognitionwork.starter.Menu;
import br.college.ufu.patternrecognitionwork.starter.option.MenuOption;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainOption extends MenuOption {

    private Boolean useZScore;

    public MainOption(Menu parent, Integer value, String message, Boolean useZScore) {
        super(parent, value, message);

        this.useZScore = useZScore;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        String pathArchive;
        Long numbersViziersParsed;
        Long numberKFoldParsed;

        try {
            System.out.print("Enter file path: ");
            pathArchive = scanner.nextLine();
            System.out.print("Enter the number of neighbors: ");
            String numeroVizinhos = scanner.nextLine();
            numbersViziersParsed = Long.parseLong(numeroVizinhos);
            System.out.print("Enter the number of partitions K-Fold: ");
            String numeroKFold = scanner.nextLine();
            numberKFoldParsed = Long.parseLong(numeroKFold);
        } catch (Exception ignore) {
            execute();
            return;
        }

        try {
            java.io.File file = new java.io.File(pathArchive);

            if (!file.exists()) {
                execute();
                return;
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            File dataFile = new File(fileInputStream, this.useZScore);

            KFold kFold = new KFold(numberKFoldParsed, dataFile);
            kFold.test(numbersViziersParsed);
        } catch (FileNotFoundException e) {
            execute();
        }
    }
}
