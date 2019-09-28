package br.college.ufu.patternrecognitionwork.starter;


import br.college.ufu.patternrecognitionwork.starter.option.MenuOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import static java.util.Objects.isNull;

public abstract class Menu {

    private Menu parent;

    private Collection<MenuOption> menuOptions;

    public Menu(Menu parent) {
        this.parent = parent;
        this.menuOptions = new ArrayList<>();
    }

    public Menu(Menu parent, MenuOption... menuOptions) {
        this(parent);
        this.menuOptions.addAll(Arrays.asList(menuOptions));
    }

    public Collection<MenuOption> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(Collection<MenuOption> menuOptions) {
        this.menuOptions = menuOptions;
    }

    public void addMenuOptions(MenuOption... menuOptions) {
        this.menuOptions.addAll(Arrays.asList(menuOptions));
    }

    public MenuOption getMenuOption(Integer answer) {
        for (MenuOption menuOption : menuOptions)
            if (menuOption.getValue().equals(answer))
                return menuOption;

        return null;
    }

    public void execute() {
        System.out.println(this);

        Scanner scanner = new Scanner(System.in);
        Integer answer = scanner.nextInt();
        MenuOption menuOption = getMenuOption(answer);

        if (isNull(menuOption))
            execute();

        menuOption.execute();
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (MenuOption menuOption : menuOptions)
            stringBuilder.append(menuOption);

        return stringBuilder.toString();
    }
}
