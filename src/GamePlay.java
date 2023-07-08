import java.util.*;

public class GamePlay {
    private Robot robot1;
    private Robot robot2;
    private Scanner scanner;
    private String actionButtons;

    public GamePlay() {
        scanner = new Scanner(System.in);
        actionButtons = "QWEASDZXC";
        createRobots();
    }

    private void createRobots() {
        System.out.println("Enter the first robot's name:");
        String firstRobotName = scanner.nextLine();
        robot1 = new Robot(firstRobotName, actionButtons);

        while (true) {
            System.out.println("Enter the second robot's name:");
            String secondRobotName = scanner.nextLine();
            if (secondRobotName.equals(firstRobotName)) {
                System.out.println("The name must be unique. Please enter a different name for the second robot.");
            } else {
                robot2 = new Robot(secondRobotName, actionButtons);
                break;
            }
        }
    }

    private void performTurn(Robot currentRobot, Robot opponentRobot) {
        System.out.println(
                String.format("Action buttons for %s: %s", currentRobot.getName(), currentRobot.getActionButtons()));
        System.out.println(
                String.format("%s, please, press an action button (or press %s to end the game):", currentRobot.getName(), currentRobot.getExit())); //треба знайти як виводити список екшн баттонс

        String input = scanner.nextLine();

        if (input.equalsIgnoreCase(currentRobot.getExit())) {
            System.out.println("Game ended by the player.");
            System.exit(0);
        }

        if (input.isEmpty()) {
            System.out.println("Can't be empty!");
            performTurn(currentRobot, opponentRobot);
            return;
        }


        if (input.length() != 1) {
            input = input.substring(0, 1);
        }

        String button = input.toUpperCase();

        if (!Character.isLetter(button.charAt(0))) {
            System.out.println(
                    String.format("Invalid button."));
            performTurn(currentRobot, opponentRobot);
            return;
        }

        if (!currentRobot.getActionButtons().contains(button)) {
            System.out.println(
                    String.format("Invalid button."));
            performTurn(currentRobot, opponentRobot);
            return;
        }

        if (currentRobot.isButtonUsed(button)) {
            System.out.println(
                    String.format("Button is already used."));
            performTurn(currentRobot, opponentRobot); // Retry the turn for the same robot
            return;
        }

        currentRobot.markButtonAsUssed(button);

        if (opponentRobot.getActiveButtons().contains(button.charAt(0))) {
            opponentRobot.reduceHealth(20);
            opponentRobot.getActiveButtons().remove(button.charAt(0));
        }
    }

    public void play() {
        while (true) {
            performTurn(robot1, robot2);
            performTurn(robot2, robot1);

            if (robot1.getHealth() <= 0 && robot2.getHealth() <= 0) {
                System.out.println("Game over. It's a tie!");
                break;
            } else if (robot1.getHealth() <= 0) {
                System.out.println(String.format("%s wins!", robot2.getName()));
                break;
            } else if (robot2.getHealth() <= 0) {
                System.out.println(String.format("%s wins!", robot1.getName()));
                break;
            }

            System.out.println(String.format("%s: Health is %s", robot1.getName(), robot1.getHealth()));
            System.out.println(String.format("%s: Health is %s", robot2.getName(), robot2.getHealth()));
        }

        System.out.println("Game over.");
    }



}

