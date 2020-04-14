package ru.ifmo.jjd.exercises.lesson11.battleships;

import ru.ifmo.jjd.exercises.lesson11.battleships.engine.Cell;
import ru.ifmo.jjd.exercises.lesson11.battleships.engine.Player;

import java.util.Arrays;
import java.util.HashSet;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.StringHelper.*;

public class UserInteraction {
    public static int selectMainMenu() {
        return selectAlternative("""
                ================
                BATTLESHIPS GAME
                ================
                """, "Start new game", "How to play", "Exit");
    }

    public static String selectName() {
        String input;
        while (true) {
            println();
            input = uppercaseFirst(normalize(readLine("Please enter player name: ")));
            if (input.isBlank()) {
                println("Incorrect value. Please try again.");
            } else {
                return input;
            }
        }
    }

    public static boolean selectAI() {
        return selectAlternative("Select player: ", "Human", "Computer") == 1;
    }

    public static int[] selectFieldSize() {
        if (selectAlternative("Select player's battlefield size: ",
                "default: " + Settings.FIELD_HEIGHT_DEFAULT + " to " + Settings.FIELD_WIDTH_DEFAULT,
                "custom") == 0) {
            return new int[]{Settings.FIELD_HEIGHT_DEFAULT, Settings.FIELD_WIDTH_DEFAULT};
        }
        int[] result = new int[2];
        result[0] = selectFromRange("Specify field height: ",
                Settings.FIELD_HEIGHT_MIN, Settings.FIELD_HEIGHT_MAX);
        result[1] = selectFromRange("Specify field width: ",
                Settings.FIELD_WIDTH_MIN, Settings.FIELD_WIDTH_MAX);
        return result;
    }

    public static int[] selectFleet() {
        if (selectAlternative("Select fleet option:",
                "Use standard fleet of battleships:" + fleetToString(Settings.SHIP_LENGTHS_DEFAULT),
                "Assemble especial fleet") == 0) {
            return Settings.SHIP_LENGTHS_DEFAULT;
        }
        String[] alternatives = new String[Settings.SHIP_NAMES.length + 1];
        alternatives[0] = Settings.SHIP_NAMES[0] + " (1 tile)";
        for (int i = 1; i < Settings.SHIP_NAMES.length; i++) {
            alternatives[i] = Settings.SHIP_NAMES[i] + " (" + (i + 1) + " tiles)";
        }
        alternatives[alternatives.length - 1] = "Stop";
        int[] result = new int[Settings.SHIP_LIMIT];
        int idx = 0;
        int selection;
        while (true) {
            selection = selectAlternative("Add a fleet unit:", alternatives);
            if (selection == alternatives.length - 1) {
                break;
            }
            result[idx++] = selection + 1;
            println("Current fleet:" + fleetToString(result));
            if (idx == result.length) {
                println(Settings.SHIP_LIMIT + " is allowed maximum fleet size");
                break;
            }
        }
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 0) {
                result = Arrays.copyOf(result, i);
            }
        }
        return result;
    }

    public static void placeFleet(Player player, int[] shipLengths) {
        if (selectAlternative("Select a battle formation for player's fleet:",
                "Arrange the fleet randomly",
                "Arrange the fleet manually") == 0) {
            placeFleetRandomly(player);
        } else {
            placeFleetManually(player, shipLengths);
        }
    }

    public static void placeFleetRandomly(Player player) {
        player.placeRandomly();
        player.displayField();
    }

    private static void placeFleetManually(Player player, int[] shipLengths) {
        int[] frequencies;
        int alternativesCount;
        String[] alternatives;
        int[] alternativesLengths;
        restart:
        while (true) {
            int[] remaining = shipLengths.clone();
            player.clearField();
            while (true) { // selecting and placing a naval unit
                frequencies = getFrequencies(remaining);
                alternativesCount = 0;
                for (int frequency : frequencies) {
                    if (frequency != 0) alternativesCount++;
                }
                if (alternativesCount == 0) {
                    player.displayField();
                    println("The fleet is ready to battle");
                    return;
                }
                player.displayField();
                println("Available fleet:" + fleetToString(remaining));
                int selectedLength;
                alternatives = new String[alternativesCount + 2];
                alternativesLengths = new int[alternativesCount];
                for (int i = 0, j = 0; i < frequencies.length; i++) {
                    if (frequencies[i] != 0) {
                        alternatives[j] = Settings.SHIP_NAMES[i];
                        alternativesLengths[j] = i + 1;
                        j++;
                    }
                }
                alternatives[alternativesCount] = "Start over (clear the field)";
                alternatives[alternativesCount + 1] = "Arrange the fleet randomly";
                int selectedAlternative = selectAlternative("Select a fleet unit to place:",
                        alternatives);
                if (selectedAlternative == alternativesCount + 1) {
                    placeFleetRandomly(player);
                    return;
                } else if (selectedAlternative == alternativesCount) {
                    println("Restarting the fleet arrangement");
                    continue restart;
                } else {
                    selectedLength = alternativesLengths[selectedAlternative];
                }
                placeShipManually(player, selectedLength);
                for (int i = 0; i < remaining.length; i++) {
                    if (remaining[i] == selectedLength) {
                        remaining[i] = 0;
                        break;
                    }
                }
            }
        }
    }

    private static void placeShipManually(Player player, int length) {
        HashSet<Cell> invalidCells;
        while (true) {
            player.displayField();
            int[] coordinates = selectCell("Select the first tile for " + Settings.SHIP_NAMES[length - 1] + ": ");
            boolean isVertical = length == 1 || selectAlternative("Select direction: ",
                    "downwards", "rightwards") == 0;
            try {
                invalidCells = player.placeShip(coordinates[0], coordinates[1], length, isVertical);
            } catch (Exception e) {
                println("Impossible to place there. Please choose another location or direction");
                continue;
            }
            if (!invalidCells.isEmpty()) {
                println("Impossible to place there. Please choose another location or direction");
                continue;
            }
            break;
        }

    }

    public static int[] cellToInt(String cell) throws IllegalArgumentException {
        int row, col;
        cell = normalize(cell.toUpperCase());
        if (cell.length() < 2) {
            throw new IllegalArgumentException("Cell string value is incorrect");
        }
        if (cell.charAt(0) < 'A' || cell.charAt(0) > 'A' + Settings.FIELD_WIDTH_MAX) {
            throw new IllegalArgumentException("Cell string value is incorrect");
        }
        col = cell.charAt(0) - 'A';
        try {
            row = Integer.parseInt(cell.substring(1)) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cell string value is incorrect");
        }
        if (row < 0 || row >= Settings.FIELD_HEIGHT_MAX) {
            throw new IllegalArgumentException("Cell string value exceeds limits");
        }
        return new int[]{row, col};
    }

    public static String intToCell(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row >= Settings.FIELD_HEIGHT_MAX || col < 0 || col >= Settings.FIELD_WIDTH_MAX) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        return "" + (char) (col + 'A') + row;
    }

    // Returns index of selected alternative in given array of alternatives
    private static int selectAlternative(String message, String... alternatives) {
        if (message == null) {
            throw new NullPointerException("Message cannot be null");
        }
        if (alternatives == null || alternatives.length == 0) {
            throw new IllegalArgumentException("Alternatives array cannot be null or empty");
        }
        for (String alternative : alternatives) {
            if (isNullOrBlank(alternative)) {
                throw new IllegalArgumentException("Alternative cannot be null or blank");
            }
        }
        Integer input;
        StringBuilder displayMessage = new StringBuilder();
        displayMessage.append(message).append('\n');
        for (int i = 0; i < alternatives.length; i++) {
            displayMessage.append(i + 1).append(". ").append(alternatives[i]).append('\n');
        }
        return selectFromRange(displayMessage.toString(), 1, alternatives.length) - 1;
    }

    // Returns selected integer value from given range. Bounds are inclusive!
    private static int selectFromRange(String message, int lower, int upper) {
        if (message == null) {
            throw new NullPointerException("Message cannot be null");
        }
        if (upper <= lower) {
            throw new IllegalArgumentException("lower bound must be less than upper bound");
        }
        Integer input;
        while (true) {
            println();
            input = readInteger(message);
            if (input == null || input < lower || input > upper) {
                println("Incorrect value. Please try again.");
                println("Value must be in [" + lower + ";" + upper + "]");
            } else {
                readLine();
                break;
            }
        }
        return input;
    }

    public static int[] selectCell(String message) {
        int[] result = null;
        while (result == null) {
            try {
                result = cellToInt(readLine(message));
            } catch (IllegalArgumentException e) {
                println("Incorrect value. Please try again. Use letter-and-number, e.g. 'A1'");
            }
        }
        return result;
    }

    public static int[] selectTarget(String message) {
        String input;
        int[] result = null;
        while (result == null) {
            input = readLine(message);
            if (input.isBlank()) {
                return null;
            }
            try {
                result = cellToInt(input);
            } catch (IllegalArgumentException e) {
                println("Incorrect value. Please try again. Use letter-and-number, e.g. 'A1'");
            }
        }
        return result;
    }


    private static String fleetToString(int... lengths) {
        int[] frequencies = getFrequencies(lengths);
        StringBuilder fleet = new StringBuilder();
        for (int i = frequencies.length - 1; i >= 0; i--) {
            if (frequencies[i] != 0) {
                fleet.append("\n    ").append(frequencies[i]).append(" x ").append(Settings.SHIP_NAMES[i]);
            }
        }
        return fleet.toString();
    }

    private static int[] getFrequencies(int... lengths) {
        int[] frequencies = new int[Settings.SHIP_NAMES.length];
        for (int length : lengths) {
            if (length > 0 && length <= Settings.SHIP_NAMES.length) {
                frequencies[length - 1]++;
            }
        }
        return frequencies;
    }

    public static void help() {
        System.out.println("""
                                
                HOW TO PLAY
                                
                Battleships game is a strategy-type guessing game for two players.
                                
                Each player has its own fleet of naval units located on some battlefield (open sea).
                The locations of the fleets are concealed from the other player. 
                And the objective of the game is to find and destroy the opposing player's fleet before losing your 
                own.               
                                
                The battlefield is represented by a rectangle, consisting of tiles.
                Each naval unit occupies a line from one to several tiles the battlefield.
                                
                Players alternate turns trying to guess where the opposing players's fleet is located.
                Player selects a target tile and 'shoots' there. 
                The second player should response with the result: 
                - 'Miss' when the targeted tile was empty;
                - 'Hit' when the opponent succeeded - a ship was hit;
                - 'Sunk' when the successfully targeted ship has all its tiles hit and now goes to the bottom.
                                
                If player misses he passes the turn to the other player.
                But when player hits or sinks an enemy's ship he gets one more turn.
                                
                The game ends when one of the players has no fleet afloat.
                                
                In the given version of the game you can select and adjust a variety of options: 
                - for each player you can select who is going to play: a person or a computer; in any combinations
                - for each player you can adjust both battlefield dimensions
                - for each player you can assemble your own fleet of mighty battle ships in any combinations 
                - human player can arrange his fleet on the battlefield both manually or using randomly generated 
                    positions
                                
                Remember that none ships can be placed next to each other. That's a rule. 
                Each tile of a battle ship should be at least one tile from any tiles of any other ship.   
                                
                                
                                
                Most interactions with the game happens in console. Just follow-up the instructions shown.
                Set-up steps are based on selecting an option form given alternatives.
                Sea battle turns expect you to select a target for the next action.
                                
                During the main game stage of sea battle player can choose the current applied game mode, or in other 
                words - the selected tool:
                - Shooting: in this mode the selected target will be shot. It counts as a turn.
                - Marking: in this mode player can mark a tile as presumably empty. For example a tile next to an 
                  already sunk enemy ship. 
                - Un-Marking: in this mode player can remove marks previously placed in Marking mode.  
                                
                Both Marking and Un-Marking do not count as a turn and do not affect opponents fleet, they are only 
                for player's convenience.
                By default, the Shooting mode is selected.
                In order to switch the mode just leave the empty input for the next target selection dialog.
                                
                Each player has both his own and opponent's battlefields.
                Player's own battlefield is to the left. Here the player can see his fleet and places where his 
                opponent has already shot.
                Opponent's battlefield if to the right and it displays only know information: previous targets, already 
                hit tiles, sunk ships, and placed marks.
                                
                The game uses the following legend:
                -   - (blank tile) on player's battlefield this tile is definitely empty;
                      on opponent's battlefield this tile is yet concealed;
                - · - a placed mark on opponent's battlefield, presuming this tile does not contain a ship;
                - O - a tile occupied by one of your naval units; yet in service;
                - * - a tile that was shot but none battle ship was hit;
                - X - a hit tile of a player's or opponent's battle ship.
                                
                The game expects using a monospaced font in order to display the game contents properly.  
                """);
        Game.pause(15000);
    }

    public static Player.Mode selectMode() {
        Player.Mode[] modes = Player.Mode.values();
        String[] alternatives = new String[modes.length];
        for (int i = 0; i < alternatives.length; i++) {
            alternatives[i] = modes[i].desc;
        }
        return modes[selectAlternative("Select mode", alternatives)];
    }
}
