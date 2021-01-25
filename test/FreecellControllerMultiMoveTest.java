import static org.junit.Assert.assertEquals;

import cs3500.freecell.hw02.Card;
import cs3500.freecell.hw02.FreecellOperations;
import cs3500.freecell.hw03.FreecellController;
import cs3500.freecell.hw04.FreecellModelCreator;
import cs3500.freecell.hw04.FreecellModelCreator.GameType;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import org.junit.Test;


/**
 * A test class for a Freecell Controller that also tests for multi-moves. Tests ensure that a
 * freecell controller works correctly.
 */
public class FreecellControllerMultiMoveTest {


  FreecellOperations modelMulti = FreecellModelCreator.create(GameType.MULTIMOVE);
  StringBuilder gameLog = new StringBuilder();


  // valid multi-move from cascade to cascade pile, then quit
  @Test
  public void testMultiMove() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    FreecellController c = new FreecellController(new StringReader("C5 6 O1 C3 7 C5 C5 5 C1 q"),
        gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 2♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠, 3♥, 2♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }

  // invalid multi-move from cascade to cascasde pile
  // (list of cards being moved is not a valid build), then quit
  @Test
  public void testInvalidMultiMoveNotValidBuild() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    FreecellController c = new FreecellController(new StringReader("C5 6 O1 C3 7 C5 C5 4 C1 q"),
        gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 2♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid move. Try again. Invalid card move to cascade pile.\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // invalid multi-move (not enough empty piles), then quit
  @Test
  public void testInvalidMultiMoveNotEnoughEmptyPiles() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    FreecellController c = new FreecellController(new StringReader(""
        + "C5 6 O1 "
        + "C3 7 C5 "
        + "C6 6 O2 "
        + "C7 6 O3 "
        + "C8 6 O4 "
        + "C4 7 O5 "
        + "C5 4 C1 q"),
        gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 2♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2: 7♠\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 2♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2: 7♠\n"
        + "O3: 6♠\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 2♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2: 7♠\n"
        + "O3: 6♠\n"
        + "O4: 5♠\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 2♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2: 7♠\n"
        + "O3: 6♠\n"
        + "O4: 5♠\n"
        + "O5: A♠\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 2♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠\n"
        + "Invalid move. Try again. This exceeds the maximum amount of cards that can be moved.\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // invalid multi-move from cascade to cascasde pile
  // (destination pile index is out of bounds), then quit
  @Test
  public void testInvalidMultiMoveBadDestinationPileIndex() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    FreecellController c = new FreecellController(new StringReader("C5 6 O1 C3 7 C5 C5 4 C20 q"),
        gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 2♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid move. Try again. Destination pile index is "
        + "greater than the number of cascade piles.\n"
        + "Game quit prematurely.", gameLog.toString());
  }

  // valid move to from cascade to open pile, then quit
  @Test
  public void testMoveToOpen() {
    FreecellController c = new FreecellController(new StringReader("C1 11 O3 q"), gameLog);
    c.playGame(modelMulti.getDeck(), modelMulti, 5, 4, false);
    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♠, 6♠, J♠, 3♥, 8♥, K♥, 5♦, 10♦, 2♣, 7♣, Q♣\n"
        + "C2: 2♠, 7♠, Q♠, 4♥, 9♥, A♦, 6♦, J♦, 3♣, 8♣, K♣\n"
        + "C3: 3♠, 8♠, K♠, 5♥, 10♥, 2♦, 7♦, Q♦, 4♣, 9♣\n"
        + "C4: 4♠, 9♠, A♥, 6♥, J♥, 3♦, 8♦, K♦, 5♣, 10♣\n"
        + "C5: 5♠, 10♠, 2♥, 7♥, Q♥, 4♦, 9♦, A♣, 6♣, J♣\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3: Q♣\n"
        + "O4:\n"
        + "C1: A♠, 6♠, J♠, 3♥, 8♥, K♥, 5♦, 10♦, 2♣, 7♣\n"
        + "C2: 2♠, 7♠, Q♠, 4♥, 9♥, A♦, 6♦, J♦, 3♣, 8♣, K♣\n"
        + "C3: 3♠, 8♠, K♠, 5♥, 10♥, 2♦, 7♦, Q♦, 4♣, 9♣\n"
        + "C4: 4♠, 9♠, A♥, 6♥, J♥, 3♦, 8♦, K♦, 5♣, 10♣\n"
        + "C5: 5♠, 10♠, 2♥, 7♥, Q♥, 4♦, 9♦, A♣, 6♣, J♣\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // valid move from cascade to foundation pile, then quit
  @Test
  public void testMoveToFoundation() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C4 7 F1 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1: A♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // valid moves from cascade to open pile,
  // cascade to cascade pile,
  // then quit
  @Test
  public void testMoveOpenAndCascade() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C5 6 O1 C3 7 C5 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 2♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // move from cascade to open,
  // open to cascade,
  // then quit
  @Test
  public void testMoveOpenToCascade() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C6 6 O1 C4 7 O2 O2 1 C6 q"),
        gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 7♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 7♠\n"
        + "O2: A♠\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 7♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, A♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }

  //////////////////////////////// SOURCE /////////////////////////////////

  // not C, F, O for source pile
  @Test
  public void testInvalidSourceType() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("T6 C1 7 O1 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid pile type. The pile type must be either C, F, or O. "
        + "Please re-enter a valid pile type and pile number.\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 4♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // C, F, O for source type, but pile number is not a number
  @Test
  public void testInvalidSourcePileNum() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C### C1 7 O1 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid index. Please re-enter a valid source pile index.\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 4♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // q as source
  @Test
  public void testQuitAsSource() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }

  //////////////////////////////// CARD INDEX /////////////////////////////////

  // card index is not a number
  @Test
  public void testCardIndexNotNum() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C1 R 7 O1 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid index. Please re-enter a valid card index.\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 4♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }

  // card index is Q
  @Test
  public void testIndexNotLastCard() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C1 Q O1"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }

  //////////////////////////////// DESTINATION /////////////////////////////////

  // not C, F, O for destination pile type
  @Test
  public void testInvalidDestType() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C1 7 B1 O2 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid pile type. The pile type must be either C, F, or O. "
        + "Please re-enter a valid pile type and pile number.\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2: 4♠\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // C, F, O for destination pile type, but pile number is not a number
  @Test
  public void testInvalidDestPileNum() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C1 7 OB O2 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid index. Please re-enter a valid destination pile index.\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2: 4♠\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // q for destination pile
  @Test
  public void testQuitDestinationPile() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C1 7 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // quit after invalid input
  @Test
  public void testQuitAfterInvalidInput() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C1 7 OB q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid index. Please re-enter a valid destination pile index.\n"
        + "Game quit prematurely.", gameLog.toString());
  }

  //////////////////////////////// MOVE /////////////////////////////////

  // valid inputs for controller, but moving from foundation pile
  @Test
  public void testMoveGameNotStarted() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C4 7 F1 F1 1 O1 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1: A♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid move. Try again. This move is not possible.\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // valid inputs for controller, but card index too big for specified source pile
  @Test
  public void testInvalidCardIndex() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C4 12 F1 C4 7 F1 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid move. Try again. Card index is out of bounds for the specified pile.\n"
        + "F1: A♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // valid inputs for controller, but move is an invalid move to a cascade pile
  @Test
  public void testInvalidMoveToCascade() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C4 7 C1 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid move. Try again. Invalid card move to cascade pile.\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // valid inputs for controller, but moving to a non-empty open pile
  @Test
  public void testInvalidMoveToNonEmptyOpen() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C4 7 O3 C8 6 O3 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3: A♠\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid move. Try again. Invalid move to an open pile.\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // valid inputs for controller, but invalid move to a non-empty foundation pile
  @Test
  public void testInvalidMoveToNonEmptyFoundation() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C4 7 F1 C8 6 F1 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "F1: A♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid move. Try again. Invalid move to a foundation pile.\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // valid inputs for controller, but invalid move to a empty foundation pile
  @Test
  public void testInvalidMoveToEmptyFoundation() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C6 6 F1 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid move. Try again. Invalid move to empty foundation pile.\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // valid inputs for controller, but source pile number is invalid
  @Test
  public void testInvalidSourcePileNumForMove() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C10 8 O1 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid move. Try again. Pile index is out of bounds.\n"
        + "Game quit prematurely.", gameLog.toString());
  }

  // valid inputs for controller, but destination pile number is invalid
  @Test
  public void testInvalidDestPileNumForMove() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C1 7 O100 q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid move. Try again. Invalid move to an open pile.\n"
        + "Game quit prematurely.", gameLog.toString());
  }

  //////////////////////////////// MISC /////////////////////////////////

  // null deck passed into playGame
  @Test(expected = IllegalArgumentException.class)
  public void testNullDeck() {
    FreecellController c = new FreecellController(new StringReader("C1 7 O1 q"), gameLog);
    c.playGame(null, modelMulti, 8, 5, false);
  }

  // null model passed into playGame
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    FreecellController c = new FreecellController(new StringReader("C1 7 O1 q"), gameLog);
    c.playGame(modelMulti.getDeck(), null, 8, 5, false);
  }


  // null readable passed into controller
  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    FreecellController c = new FreecellController(null, gameLog);
    c.playGame(modelMulti.getDeck(), modelMulti, 8, 5, false);
  }

  // null appendable passed into playGame
  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    FreecellController c = new FreecellController(new StringReader("C1 7 O1 q"), null);
    c.playGame(modelMulti.getDeck(), modelMulti, 8, 5, false);
  }


  // invalid number of cascade piles passed into playGame
  @Test
  public void testInvalidNumCascadesPlayGame() {
    FreecellController c = new FreecellController(new StringReader("C1 7 O1 q"), gameLog);
    c.playGame(modelMulti.getDeck(), modelMulti, 0, 5, false);

    assertEquals("Could not start game.", gameLog.toString());
  }


  // invalid number of open piles passed into playGame
  @Test
  public void testInvalidNumOpenPlayGame() {
    FreecellController c = new FreecellController(new StringReader("C1 7 O1 q"), gameLog);
    c.playGame(modelMulti.getDeck(), modelMulti, 8, 0, false);

    assertEquals("Could not start game.", gameLog.toString());
  }

  // no more inputs but game is not over nor quit
  @Test(expected = IllegalStateException.class)
  public void testNoMoreInputs() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("C1 7 B1 O2"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);
  }


  // multiple invalid inputs for controller in a row
  // continues to look for valid source
  @Test
  public void testMultipleInvalidMovesLookForSource() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("JW 99 CP OC QU q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid pile type. The pile type must be either C, F, or O. "
        + "Please re-enter a valid pile type and pile number.\n"
        + "Invalid pile type. The pile type must be either C, F, or O. "
        + "Please re-enter a valid pile type and pile number.\n"
        + "Invalid index. Please re-enter a valid source pile index.\n"
        + "Invalid index. Please re-enter a valid source pile index.\n"
        + "Invalid pile type. The pile type must be either C, F, or O. "
        + "Please re-enter a valid pile type and pile number.\n"
        + "Game quit prematurely.", gameLog.toString());
  }

  // multiple invalid inputs for controller in a row
  // valid source, but continues to look for index
  @Test
  public void testMultipleInvalidMovesLookForIndex() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("JW 99 CP OC QU C1 LI NJ q"),
        gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid pile type. The pile type must be either C, F, or O. "
        + "Please re-enter a valid pile type and pile number.\n"
        + "Invalid pile type. The pile type must be either C, F, or O. "
        + "Please re-enter a valid pile type and pile number.\n"
        + "Invalid index. Please re-enter a valid source pile index.\n"
        + "Invalid index. Please re-enter a valid source pile index.\n"
        + "Invalid pile type. The pile type must be either C, F, or O. "
        + "Please re-enter a valid pile type and pile number.\n"
        + "Invalid index. Please re-enter a valid card index.\n"
        + "Invalid index. Please re-enter a valid card index.\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // multiple invalid inputs for controller in a row
  // valid source and valid index, but continues to look for destination
  @Test
  public void testMultipleInvalidMovesLookForDest() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    FreecellController c = new FreecellController(new StringReader("JW 99 CP OC QU C1 LI NJ 7 "
        + "N9 FK q"), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(""
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠, 4♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠\n"
        + "Invalid pile type. The pile type must be either C, F, or O. "
        + "Please re-enter a valid pile type and pile number.\n"
        + "Invalid pile type. The pile type must be either C, F, or O. "
        + "Please re-enter a valid pile type and pile number.\n"
        + "Invalid index. Please re-enter a valid source pile index.\n"
        + "Invalid index. Please re-enter a valid source pile index.\n"
        + "Invalid pile type. The pile type must be either C, F, or O. "
        + "Please re-enter a valid pile type and pile number.\n"
        + "Invalid index. Please re-enter a valid card index.\n"
        + "Invalid index. Please re-enter a valid card index.\n"
        + "Invalid pile type. The pile type must be either C, F, or O. "
        + "Please re-enter a valid pile type and pile number.\n"
        + "Invalid index. Please re-enter a valid destination pile index.\n"
        + "Game quit prematurely.", gameLog.toString());
  }


  // game played to completion. No invalid inputs
  @Test
  public void testGameCompletion() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    FreecellController c = new FreecellController(new StringReader(""
        + "C4 7 F1 "
        + "C3 7 F1 "
        + "C2 7 F1 "
        + "C1 7 F1 "
        + "C8 6 F1 "
        + "C7 6 F1 "
        + "C6 6 F1 "
        + "C5 6 F1 "
        + "C4 6 F1 "
        + "C3 6 F1 "
        + "C2 6 F1 "
        + "C1 6 F1 "
        + "C8 5 F1 "
        + ""
        + "C7 5 F2 "
        + "C6 5 F2 "
        + "C5 5 F2 "
        + "C4 5 F2 "
        + "C3 5 F2 "
        + "C2 5 F2 "
        + "C1 5 F2 "
        + "C8 4 F2 "
        + "C7 4 F2 "
        + "C6 4 F2 "
        + "C5 4 F2 "
        + "C4 4 F2 "
        + "C3 4 F2 "
        + ""
        + "C2 4 F3 "
        + "C1 4 F3 "
        + "C8 3 F3 "
        + "C7 3 F3 "
        + "C6 3 F3 "
        + "C5 3 F3 "
        + "C4 3 F3 "
        + "C3 3 F3 "
        + "C2 3 F3 "
        + "C1 3 F3 "
        + "C8 2 F3 "
        + "C7 2 F3 "
        + "C6 2 F3 "
        + ""
        + "C5 2 F4 "
        + "C4 2 F4 "
        + "C3 2 F4 "
        + "C2 2 F4 "
        + "C1 2 F4 "
        + "C8 1 F4 "
        + "C7 1 F4 "
        + "C6 1 F4 "
        + "C5 1 F4 "
        + "C4 1 F4 "
        + "C3 1 F4 "
        + "C2 1 F4 "
        + "C1 1 F4 "), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(true, modelMulti.isGameOver());

    String[] lines = gameLog.toString().split("\n");
    assertEquals(902, lines.length);
    assertEquals("Game over.", lines[lines.length - 1]);
  }


  // input including valid moves interspersed with invalid moves, game played to completion
  @Test
  public void testGameCompletionInvalidMoves() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    StringBuilder gameLog = new StringBuilder();
    FreecellController c = new FreecellController(new StringReader(""
        + "C4 C5 7 F1 "
        + "Q9 C3 7 F1 "
        + "C2 7 F1 "
        + "C1 7 F1 "
        + "C8 6 F1 "
        + "C7 6 F1 "
        + "C6 6 F1 "
        + "C5 6 Q200 F1 "
        + "C4 6 F1 "
        + "C3 6 F1 "
        + "C2 6 F1 "
        + "C1 6 F1 "
        + "C8 5 F1 "
        + ""
        + "C7 5 F2 "
        + "C6 5 F2 "
        + "C5 5 F2 "
        + "C4 5 F2 "
        + "C3 5 F2 "
        + "C2 5 F2 "
        + "C1 5 F2 "
        + "C8 4 F2 "
        + "C7 78 F2 "
        + "C7 4 F2 "
        + "C6 4 F2 "
        + "C5 4 F2 "
        + "C4 4 F2 "
        + "C3 4 F2 "
        + ""
        + "C2 4 F3 "
        + "C1 4 F3 "
        + "C8 3 F3 "
        + "C7 3 F3 "
        + "C6 3 F3 "
        + "C5 3 F3 "
        + "C4 3 F3 "
        + "C3 3 F3 "
        + "C2 3 F3 "
        + "C1 3 F3 "
        + "C8 2 F3 "
        + "C7 2 F3 "
        + "C6 2 MM F3 "
        + ""
        + "C5 2 F4 "
        + "C4 2 F4 "
        + "C3 2 F4 "
        + "C2 2 F4 "
        + "C1 2 F4 "
        + "C8 1 F4 "
        + "C7 1 F4 "
        + "C6 1 F4 "
        + "C5 1 F4 "
        + "C4 1 F4 "
        + "C3 1 F4 "
        + "C2 1 F4 "
        + "C1 1 F4 "), gameLog);
    c.playGame(reverseDeck, modelMulti, 8, 5, false);

    assertEquals(true, modelMulti.isGameOver());

    String[] lines = gameLog.toString().split("\n");
    assertEquals(908, lines.length);
    assertEquals("Game over.", lines[lines.length - 1]);
  }
}

