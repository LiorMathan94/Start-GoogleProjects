package exercise1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<Player.Position, Integer> formation = new HashMap<>();
        formation.put(Player.Position.GOAL_KEEPER, 1);
        formation.put(Player.Position.DEFENDER, 3);
        formation.put(Player.Position.MIDFIELDER, 3);
        formation.put(Player.Position.ATTACKER, 4);

        Team team = Team.createTeamWithFormation("The Googlers", formation);
        team.printToFile("team.txt");
    }
}