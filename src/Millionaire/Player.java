package Millionaire;

public class Player {
    private String name;
    private int totalWinnings;

    public Player(String name, int totalWinnings) {
        this.name = name;
        this.totalWinnings = totalWinnings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalWinnings() {
        return totalWinnings;
    }

    public void setTotalWinnings(int totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name = '" + name + '\'' +
                ", totalWinnings = " + totalWinnings +
                '}';
    }
}
