package br.college.ufu.patternrecognitionwork.starter.option;


import br.college.ufu.patternrecognitionwork.starter.Menu;

public abstract class MenuOption {

    private Menu parent;

    private Integer value;

    private String message;

    public MenuOption(Menu parent, Integer value, String message) {
        this.parent = parent;
        this.value = value;
        this.message = message;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract void execute();

    public void back() {
        parent.execute();
    }

    @Override
    public String toString() {
        return String.format("%d - %s\n", value, message);
    }
}
