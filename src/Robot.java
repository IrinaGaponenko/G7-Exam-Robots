import java.util.*;

public class Robot {
    private String name;
    private int health;
    private Set<Character> activeButtons;
    private String actionButtons;
    private Set<String> usedButtons;
    private String exitGame = "P";

    public Robot(String name, String actionButtons) {
        this.name = name;
        this.health = 100;
        this.activeButtons = new HashSet<>();
        this.actionButtons = actionButtons;
        this.usedButtons = new HashSet<String>();
        assignRandomButtons();
    }

    private char getRandomKey() {
        int randIdx = new Random().nextInt(actionButtons.length());
        char randChar = actionButtons.charAt(randIdx);
        actionButtons = actionButtons.replace(String.valueOf(randChar), "");
        return randChar;
    }
    private void assignRandomButtons() {
        List<Character> buttonList = new ArrayList<>();
        for (char c : actionButtons.toCharArray()) {
            buttonList.add(c);
        }
        Collections.shuffle(buttonList);
        for (int i = 0; i < 5; i++) {
            char button = buttonList.get(i);
            activeButtons.add(button);
        }
    }
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public Set<Character> getActiveButtons() {
        return activeButtons;
    }
    public String getActionButtons() {
        return actionButtons;
    }
    public boolean isButtonUsed(String button) {
        return usedButtons.contains(button);
    }
    public void markButtonAsUssed(String button) {
        usedButtons.add(button);
    }
    public void reduceHealth(int damage) {
        health -= damage;
    }
    public String getExit() {
        return exitGame;
    }
}


