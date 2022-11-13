package exercise1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Team {
    private String name;
    private List<Player> players = new ArrayList<Player>();
    private final int NUM_PLAYERS = 11;

    public Team(String name) {
        this.name = name;
        this.createPlayers();
    }

    private void createPlayers() {
        NameGenerator maleNameGenerator = new NameGenerator("MaleNameList.txt");
        NameGenerator femaleNameGenerator = new NameGenerator("FemaleNameList.txt");

        for (int i = 0; i < NUM_PLAYERS; i++) {
            int jerseyNumber = generateUniqueJerseyNumber();

            Player newPlayer;
            if (i % 2 == 0) {
                newPlayer = new Player(jerseyNumber, maleNameGenerator);
            } else {
                newPlayer = new Player(jerseyNumber, femaleNameGenerator);
            }

            players.add(newPlayer);
        }
    }

    public int getNUM_PLAYERS() {
        return NUM_PLAYERS;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public static Team createTeamWithFormation(String name, Map<Player.Position, Integer> formation) {
        Team team = new Team(name);
        team.assignPositions(formation);

        return team;
    }

    public void assignPositions(Map<Player.Position, Integer> formation) {
        if (!isValidFormation(formation)) {
            throw new IllegalArgumentException("Formation is illegal!");
        }

        List<Player> unassignedPlayers = new ArrayList<>(this.players);

        for (Map.Entry<Player.Position, Integer> entry : formation.entrySet()) {
            samplePlayersAndAssignPosition(unassignedPlayers, entry.getKey(), entry.getValue());
        }
    }

    public void printToFile(String filename) throws IOException {
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write(this.toString());
    }

    @Override
    public String toString() {
        String strBuffer = String.format("Team Name: %s \n", this.name);

        for (Player player : this.players) {
            strBuffer += "  Player #" + player.getJerseyNumber() + " - Name: "
                    + player.getName() + ", Position: " + player.getPosition()
                    + ", Grade: " + player.getGrade()+ "\n";
        }

        return strBuffer;
    }

    private int generateUniqueJerseyNumber() {
        List<Integer> ExistingJerseyNumbers = players.stream()
                .map(p -> p.getJerseyNumber()).collect(Collectors.toList());

        int randomNumber;
        do {
            randomNumber = RandomGenerator.generateRandomNumber(1, 100);
        } while (ExistingJerseyNumbers.contains(randomNumber));

        return randomNumber;
    }

    private boolean isValidFormation(Map<Player.Position, Integer> formation) {
        int playersCounter = 0;

        for (Map.Entry<Player.Position, Integer> entry : formation.entrySet()) {
            switch (entry.getKey()) {
                case GOAL_KEEPER: {
                    if (entry.getValue() != 1) {
                        return false;
                    }
                    playersCounter += 1;
                    break;
                }
                default: {
                    if (entry.getValue() < 2) {
                        return false;
                    }
                    playersCounter += entry.getValue();
                }
            }
        }

        return (playersCounter == NUM_PLAYERS);
    }

    private void samplePlayersAndAssignPosition(List<Player> unassignedPlayers, Player.Position position, int count) {
        for (int i = 0; i < count; i++) {
            Player randomPlayer = RandomGenerator.generateRandomObject(unassignedPlayers);
            randomPlayer.setPosition(position);
            unassignedPlayers.remove(randomPlayer);
        }
    }
}
