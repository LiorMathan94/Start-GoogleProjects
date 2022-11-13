package exercise1;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTests {
    Team team;

    @BeforeEach
    void setUp() {
        team = new Team("TeamName");
    }

    @Test
    @DisplayName("CreatePlayers should create NUM_PLAYERS players")
    void numPlayers_EqualsNUM_PLAYERS() {
        assertEquals(team.getNUM_PLAYERS(), team.getPlayers().size(),
                String.format("Num players in a team should be: %d", team.getNUM_PLAYERS()));
    }

    @Test
    @DisplayName("All jersey numbers should be unique")
    void generateUniqueJerseyNumber_PlayersJerseyNumber_AreUnique() {
        List<Integer> jerseyNumbersList = team.getPlayers().stream()
                .map(Player::getJerseyNumber).collect(Collectors.toList());

        Set<Integer> jerseyNumbersSet = new HashSet<>(jerseyNumbersList);

        assertEquals(jerseyNumbersList.size(), jerseyNumbersSet.size(), "Not all jersey numbers are unique");
    }

    @Test
    @DisplayName("assignPositions should throw IllegalArgumentException if goal keepers amount != 1")
    void assignPositions_IllegalAmountGOAL_KEEPER_ThrowsException() {
        Map<Player.Position, Integer> illegalFormation = new HashMap<>();
        illegalFormation.put(Player.Position.GOAL_KEEPER, 2);
        illegalFormation.put(Player.Position.ATTACKER, 2);
        illegalFormation.put(Player.Position.DEFENDER, 2);
        illegalFormation.put(Player.Position.MIDFIELDER, 5);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            team.assignPositions(illegalFormation);});

        String expectedMessage = "Formation is illegal!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("assignPositions should throw IllegalArgumentException if number of players != NUM_PLAYERS")
    void assignPositions_IllegalFormationNumPlayers_ThrowsException() {
        Map<Player.Position, Integer> illegalFormation = new HashMap<>();
        illegalFormation.put(Player.Position.GOAL_KEEPER, 1);
        illegalFormation.put(Player.Position.ATTACKER, 2);
        illegalFormation.put(Player.Position.DEFENDER, 2);
        illegalFormation.put(Player.Position.MIDFIELDER, 2);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            team.assignPositions(illegalFormation);});

        String expectedMessage = "Formation is illegal!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("assignPositions should throw IllegalArgumentException if attackers amount < 2")
    void assignPositions_IllegalAmountATTACKER_ThrowsException() {
        Map<Player.Position, Integer> illegalFormation = new HashMap<>();
        illegalFormation.put(Player.Position.GOAL_KEEPER, 1);
        illegalFormation.put(Player.Position.ATTACKER, 1);
        illegalFormation.put(Player.Position.DEFENDER, 4);
        illegalFormation.put(Player.Position.MIDFIELDER, 5);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            team.assignPositions(illegalFormation);});

        String expectedMessage = "Formation is illegal!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("assignPositions should throw IllegalArgumentException if defenders amount < 2")
    void assignPositions_IllegalAmountDEFENDER_ThrowsException() {
        Map<Player.Position, Integer> illegalFormation = new HashMap<>();
        illegalFormation.put(Player.Position.GOAL_KEEPER, 1);
        illegalFormation.put(Player.Position.ATTACKER, 4);
        illegalFormation.put(Player.Position.DEFENDER, 1);
        illegalFormation.put(Player.Position.MIDFIELDER, 5);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            team.assignPositions(illegalFormation);});

        String expectedMessage = "Formation is illegal!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("assignPositions should throw IllegalArgumentException if midfielders amount < 2")
    void assignPositions_IllegalAmountMIDFIELDER_ThrowsException() {
        Map<Player.Position, Integer> illegalFormation = new HashMap<>();
        illegalFormation.put(Player.Position.GOAL_KEEPER, 1);
        illegalFormation.put(Player.Position.ATTACKER, 4);
        illegalFormation.put(Player.Position.DEFENDER, 5);
        illegalFormation.put(Player.Position.MIDFIELDER, 1);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            team.assignPositions(illegalFormation);});

        String expectedMessage = "Formation is illegal!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @AfterEach
    void afterEach(){
        team = null;
    }
}
