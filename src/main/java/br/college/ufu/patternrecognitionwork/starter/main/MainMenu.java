package br.college.ufu.patternrecognitionwork.starter.main;


import br.college.ufu.patternrecognitionwork.starter.Menu;

public class MainMenu extends Menu {

    public MainMenu() {
        super(null);
        this.addMenuOptions(
                new MainOption(this, 1, "With Z-Score", true),
                new MainOption(this, 2, "Without Z-Score", false)
        );
    }
}
