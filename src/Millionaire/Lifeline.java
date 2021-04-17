package Millionaire;

public abstract class Lifeline {
    // Static variables to ensure these lifelines aren't used repeatedly throughout the game
    private static boolean used5050Lifeline;
    private static boolean usedAudienceLifeline;
    private static boolean usedFriendLifeline;

    public Lifeline() {
    }

    public static boolean isUsed5050Lifeline() {
        return used5050Lifeline;
    }

    public static void setUsed5050Lifeline(boolean used5050Lifeline) {
        Lifeline.used5050Lifeline = used5050Lifeline;
    }

    public static boolean isUsedAudienceLifeline() {
        return usedAudienceLifeline;
    }

    public static void setUsedAudienceLifeline(boolean usedAudienceLifeline) {
        Lifeline.usedAudienceLifeline = usedAudienceLifeline;
    }

    public static boolean isUsedFriendLifeline() {
        return usedFriendLifeline;
    }

    public static void setUsedFriendLifeline(boolean usedFriendLifeline) {
        Lifeline.usedFriendLifeline = usedFriendLifeline;
    }

    // Abstract class to be defined by subclasses
    public abstract Question useLifeline(Question question);
}
