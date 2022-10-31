public class Player {
    private String name;
    private int jerseyNumber;
    private int grade;
    private Position position;


    public Player(int jerseyNumber, NameGenerator nameGenerator) {
        this.jerseyNumber = jerseyNumber;
        this.name = nameGenerator.generateRandomName();

        RandomGenerator randomGenerator = new RandomGenerator();
        this.grade = randomGenerator.generateRandomNumber(0, 100);
    }

    public static Player createGoalKeeperPlayer(int jerseyNumber, NameGenerator nameGenerator) {
        Player player = new Player(jerseyNumber, nameGenerator);
        player.position = Position.GOAL_KEEPER;

        return player;
    }

    public static Player createExcellentPlayer(int jerseyNumber, NameGenerator nameGenerator) {
        Player player = new Player(jerseyNumber, nameGenerator);
        player.grade = 100;

        return player;
    }

    public static Player createPlayerWithGrade(int jerseyNumber, int grade, NameGenerator nameGenerator) {
        Player player = new Player(jerseyNumber, nameGenerator);
        player.grade = grade;

        return player;
    }

    public String getName() {
        return name;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public int getGrade() {
        return grade;
    }

    public Position getPosition() {
        return position;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean hasPosition() {
        return this.position != null;
    }

    enum Position {
        GOAL_KEEPER,
        DEFENDER,
        MIDFIELDER,
        ATTACKER
    }
}
