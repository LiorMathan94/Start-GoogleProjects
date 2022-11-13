package exercise1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {
    private static NameGenerator nameGenerator;

    @BeforeEach
    void beforeEach() {
        nameGenerator = new NameGenerator("MaleNameList.txt");
    }

    @Test
    @DisplayName("Player's grade is between 0-100")
    void player_validGradeValue() {
        Player player = new Player(1, nameGenerator);
        assertTrue(0 <= player.getGrade() && player.getGrade() <= 100);
    }

    @Test
    @DisplayName("setGrade with illegal grade value should throw IllegalArgumentException")
    void setGrade_IllegalGrade_ThrowsException() {
        Player player = new Player(1, nameGenerator);
        int illegalGrade = RandomGenerator.generateRandomNumber(101, 200);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            player.setGrade(illegalGrade);});

        String expectedMessage = "Grade value must be between 0-100";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("createGoalKeeperPlayer should create player with GOAL_KEEPER position")
    void createGoalKeeperPlayer_Position_EqualsGOAL_KEEPER() {
        Player goalKeeperPlayer = Player.createGoalKeeperPlayer(1, nameGenerator);

        assertEquals(Player.Position.GOAL_KEEPER, goalKeeperPlayer.getPosition(),
                "createGoalKeeperPlayer does not assign player as goal keeper");
    }

    @Test
    @DisplayName("createExcellentPlayer should create player with grade 100")
    void createExcellentPlayer_Grade_Equals100() {
        Player excellentPlayer = Player.createExcellentPlayer(1, nameGenerator);

        assertEquals(100, excellentPlayer.getGrade(),
                "createExcellentPlayer does not assign garde 100 to the player");
    }

    @Test
    @DisplayName("createPlayerWithGrade should create player with a specific grade")
    void createPlayerWithGrade_Grade_EqualsSpecificGrade() {
        int randomGrade = RandomGenerator.generateRandomNumber(0, 100);
        Player newPlayer = Player.createPlayerWithGrade(1, randomGrade, nameGenerator);

        assertEquals(randomGrade, newPlayer.getGrade(),
                "createPlayerWithGrade does not assign the right garde to the player");
    }
}
