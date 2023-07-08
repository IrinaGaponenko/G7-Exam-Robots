import java.util.*;
import java.util.stream.Collectors;

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

    private void assignRandomButtons() {
        List<Character> buttonList = new ArrayList<>(actionButtons.length());
        for (char c : actionButtons.toCharArray()) {
            buttonList.add(c);
        }

        activeButtons.clear();
        Random random = new Random();
        int count = 0;
        while (count < 5 && !buttonList.isEmpty()) {
            int randomIndex = random.nextInt(buttonList.size());
            char button = buttonList.remove(randomIndex);
            activeButtons.add(button);
            count++;
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


