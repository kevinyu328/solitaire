package freecell.freecell.model;

/**
 * A factory class that creates an instance of a Freecell game.
 */
public class FreecellModelCreator {

  /**
   * Represents the game types that are possible for a game of Freecell.
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }


  /**
   * Creates a Freecell game with either a single move or multi move game.
   *
   * @param type the type of game to be created
   * @return either a single move or multi move Freecell game
   */
  public static FreecellOperations create(GameType type) {
    switch (type) {
      case SINGLEMOVE:
        return new FreecellModel();
      case MULTIMOVE:
        return new FreecellModelMultiMove();
      default:
        throw new IllegalArgumentException("Not a valid game type.");
    }
  }

}
