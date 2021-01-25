import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.freecell.hw02.Card;
import cs3500.freecell.hw02.FreecellModel;
import cs3500.freecell.hw02.FreecellOperations;
import cs3500.freecell.hw02.PileType;
import cs3500.freecell.hw04.FreecellModelCreator;
import cs3500.freecell.hw04.FreecellModelCreator.GameType;
import cs3500.freecell.hw04.FreecellModelMultiMove;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * A test class for a Freecell model that allows multi-moves. Tests ensure that a multi-move
 * Freecell game works correctly.
 */
public class FreecellModelMultiMoveTest {

  /*
  TESTS WERE ONLY WRITTEN FOR MOVE METHOD WITHIN MULTIMOVE MODEL BECAUSE
  NO OTHER METHODS WERE OVERRIDDEN, AND SHOULD THUS BEHAVE THE SAME AS
  BEFORE.
   */

  FreecellOperations modelSingle = FreecellModelCreator.create(GameType.SINGLEMOVE);
  FreecellOperations modelMulti = FreecellModelCreator.create(GameType.MULTIMOVE);
  List<Card> deckMulti = modelMulti.getDeck();



  // tests if the factory class creates the correct game (single)
  @Test
  public void testModelCreatorSingle() {
    assertTrue(modelSingle instanceof FreecellModel);
  }


  // tests if the factory class creates the correct game (multi)
  @Test
  public void testModelCreatorMulti() {
    assertTrue(modelMulti instanceof FreecellModelMultiMove);
  }


  // test for move for a multimove Freecell game. All tests below tests moving one card.
  @Test
  public void testMoveOneCard() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);
    modelMulti.startGame(reverseDeck, 6, 4, false);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: K♣, 7♣, A♣, 8♦, 2♦, 9♥, 3♥, 10♠, 4♠\n"
        + "C2: Q♣, 6♣, K♦, 7♦, A♦, 8♥, 2♥, 9♠, 3♠\n"
        + "C3: J♣, 5♣, Q♦, 6♦, K♥, 7♥, A♥, 8♠, 2♠\n"
        + "C4: 10♣, 4♣, J♦, 5♦, Q♥, 6♥, K♠, 7♠, A♠\n"
        + "C5: 9♣, 3♣, 10♦, 4♦, J♥, 5♥, Q♠, 6♠\n"
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", modelMulti.getGameState());

    // move from cascade to empty foundation pile
    modelMulti.move(PileType.CASCADE, 3, 8, PileType.FOUNDATION,
        0);

    assertEquals("F1: A♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: K♣, 7♣, A♣, 8♦, 2♦, 9♥, 3♥, 10♠, 4♠\n"
        + "C2: Q♣, 6♣, K♦, 7♦, A♦, 8♥, 2♥, 9♠, 3♠\n"
        + "C3: J♣, 5♣, Q♦, 6♦, K♥, 7♥, A♥, 8♠, 2♠\n"
        + "C4: 10♣, 4♣, J♦, 5♦, Q♥, 6♥, K♠, 7♠\n"
        + "C5: 9♣, 3♣, 10♦, 4♦, J♥, 5♥, Q♠, 6♠\n"
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", modelMulti.getGameState());

    // move from cascade to open pile
    modelMulti.move(PileType.CASCADE, 1, 8, PileType.OPEN, 0);
    modelMulti.move(PileType.CASCADE, 1, 7, PileType.OPEN, 1);
    modelMulti.move(PileType.CASCADE, 1, 6, PileType.OPEN, 2);
    modelMulti.move(PileType.CASCADE, 1, 5, PileType.OPEN, 3);

    assertEquals("F1: A♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 3♠\n"
        + "O2: 9♠\n"
        + "O3: 2♥\n"
        + "O4: 8♥\n"
        + "C1: K♣, 7♣, A♣, 8♦, 2♦, 9♥, 3♥, 10♠, 4♠\n"
        + "C2: Q♣, 6♣, K♦, 7♦, A♦\n"
        + "C3: J♣, 5♣, Q♦, 6♦, K♥, 7♥, A♥, 8♠, 2♠\n"
        + "C4: 10♣, 4♣, J♦, 5♦, Q♥, 6♥, K♠, 7♠\n"
        + "C5: 9♣, 3♣, 10♦, 4♦, J♥, 5♥, Q♠, 6♠\n"
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", modelMulti.getGameState());

    // move from cascade to cascade pile
    modelMulti.move(PileType.CASCADE, 1, 4, PileType.CASCADE, 2);

    assertEquals("F1: A♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 3♠\n"
        + "O2: 9♠\n"
        + "O3: 2♥\n"
        + "O4: 8♥\n"
        + "C1: K♣, 7♣, A♣, 8♦, 2♦, 9♥, 3♥, 10♠, 4♠\n"
        + "C2: Q♣, 6♣, K♦, 7♦\n"
        + "C3: J♣, 5♣, Q♦, 6♦, K♥, 7♥, A♥, 8♠, 2♠, A♦\n"
        + "C4: 10♣, 4♣, J♦, 5♦, Q♥, 6♥, K♠, 7♠\n"
        + "C5: 9♣, 3♣, 10♦, 4♦, J♥, 5♥, Q♠, 6♠\n"
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", modelMulti.getGameState());

    // move from cascade to empty foundation pile
    modelMulti.move(PileType.CASCADE, 2, 9, PileType.FOUNDATION, 1);

    assertEquals("F1: A♠\n"
        + "F2: A♦\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 3♠\n"
        + "O2: 9♠\n"
        + "O3: 2♥\n"
        + "O4: 8♥\n"
        + "C1: K♣, 7♣, A♣, 8♦, 2♦, 9♥, 3♥, 10♠, 4♠\n"
        + "C2: Q♣, 6♣, K♦, 7♦\n"
        + "C3: J♣, 5♣, Q♦, 6♦, K♥, 7♥, A♥, 8♠, 2♠\n"
        + "C4: 10♣, 4♣, J♦, 5♦, Q♥, 6♥, K♠, 7♠\n"
        + "C5: 9♣, 3♣, 10♦, 4♦, J♥, 5♥, Q♠, 6♠\n"
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", modelMulti.getGameState());

    // move from cascade to non-empty foundation pile
    modelMulti.move(PileType.CASCADE, 2, 8, PileType.FOUNDATION, 0);

    assertEquals("F1: A♠, 2♠\n"
        + "F2: A♦\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 3♠\n"
        + "O2: 9♠\n"
        + "O3: 2♥\n"
        + "O4: 8♥\n"
        + "C1: K♣, 7♣, A♣, 8♦, 2♦, 9♥, 3♥, 10♠, 4♠\n"
        + "C2: Q♣, 6♣, K♦, 7♦\n"
        + "C3: J♣, 5♣, Q♦, 6♦, K♥, 7♥, A♥, 8♠\n"
        + "C4: 10♣, 4♣, J♦, 5♦, Q♥, 6♥, K♠, 7♠\n"
        + "C5: 9♣, 3♣, 10♦, 4♦, J♥, 5♥, Q♠, 6♠\n"
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", modelMulti.getGameState());

    // move from open to non-empty foundation pile
    modelMulti.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);

    assertEquals("F1: A♠, 2♠, 3♠\n"
        + "F2: A♦\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2: 9♠\n"
        + "O3: 2♥\n"
        + "O4: 8♥\n"
        + "C1: K♣, 7♣, A♣, 8♦, 2♦, 9♥, 3♥, 10♠, 4♠\n"
        + "C2: Q♣, 6♣, K♦, 7♦\n"
        + "C3: J♣, 5♣, Q♦, 6♦, K♥, 7♥, A♥, 8♠\n"
        + "C4: 10♣, 4♣, J♦, 5♦, Q♥, 6♥, K♠, 7♠\n"
        + "C5: 9♣, 3♣, 10♦, 4♦, J♥, 5♥, Q♠, 6♠\n"
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", modelMulti.getGameState());
  }


  // move two cards from cascade to cascade with 3 open piles
  @Test
  public void testMoveTwoCards() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 4, 5, PileType.OPEN, 0);
    modelMulti.move(PileType.CASCADE, 2, 6, PileType.CASCADE, 4);

    assertEquals("F1:\n"
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 4, 4, PileType.CASCADE, 0);

    assertEquals("F1:\n"
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

  }


  // multi move with too little empty piles available
  @Test(expected = IllegalArgumentException.class)
  public void testNotEnoughEmptyPiles() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 4, 5, PileType.OPEN, 0);
    modelMulti.move(PileType.CASCADE, 2, 6, PileType.CASCADE, 4);

    assertEquals("F1:\n"
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 4, 4, PileType.CASCADE, 0);

    assertEquals("F1:\n"
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 2, 5, PileType.OPEN, 1);
    modelMulti.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 2);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 8♠\n"
        + "O2: 10♠\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥, Q♠\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠, 3♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 4♠, 3♥, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 2, 4, PileType.CASCADE, 6);
    modelMulti.move(PileType.CASCADE, 0, 5, PileType.OPEN, 2);
    modelMulti.move(PileType.CASCADE, 6, 5, PileType.CASCADE, 0);
  }


  // move when game hasn't started
  @Test(expected = IllegalStateException.class)
  public void testMoveNotStarted() {
    modelMulti.move(PileType.CASCADE, 1, 8, PileType.OPEN, 0);
  }

  // move from a foundation pile
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromFoundation() {
    modelMulti.startGame(deckMulti, 8, 5, false);

    modelMulti.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 2);
  }

  // move to non-empty open pile
  @Test(expected = IllegalArgumentException.class)
  public void testMoveToNonEmptyOpenPile() {
    modelMulti.startGame(deckMulti, 8, 5, false);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: A♠, 9♠, 4♥, Q♥, 7♦, 2♣, 10♣\n"
        + "C2: 2♠, 10♠, 5♥, K♥, 8♦, 3♣, J♣\n"
        + "C3: 3♠, J♠, 6♥, A♦, 9♦, 4♣, Q♣\n"
        + "C4: 4♠, Q♠, 7♥, 2♦, 10♦, 5♣, K♣\n"
        + "C5: 5♠, K♠, 8♥, 3♦, J♦, 6♣\n"
        + "C6: 6♠, A♥, 9♥, 4♦, Q♦, 7♣\n"
        + "C7: 7♠, 2♥, 10♥, 5♦, K♦, 8♣\n"
        + "C8: 8♠, 3♥, J♥, 6♦, A♣, 9♣", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);

    modelMulti.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
  }


  // move to cascade of same suit color (red)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveToCascadeSameSuitColorRed() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 4, 2, PileType.OPEN, 0);
    modelMulti.move(PileType.CASCADE, 5, 2, PileType.OPEN, 1);

    assertEquals("F1:\n"
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
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠, 2♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠, A♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 5, 1, PileType.CASCADE, 4);
  }

  // move to cascade of same suit color (black)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveToCascadeSameSuitColorBlack() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 1, 2, PileType.CASCADE, 0);

  }

  // move to cascade of different suit color but moved card's rank is not one less than rank
  // of top card of destination cascade
  @Test(expected = IllegalArgumentException.class)
  public void testMoveToCascadeNotOneLess() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 5, 2, PileType.OPEN, 0);
    assertEquals("F1:\n"
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 5, 2, PileType.CASCADE, 0);
  }


  // move non-ace to empty foundation pile
  @Test(expected = IllegalArgumentException.class)
  public void testMoveNonAceToEmptyFoundation() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 0, 1, PileType.FOUNDATION, 0);
  }


  // move to non-empty foundation pile of different suit
  @Test(expected = IllegalArgumentException.class)
  public void testMoveDifferentSuitFoundation() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 3, 1, PileType.FOUNDATION, 0);
    modelMulti.move(PileType.CASCADE, 5, 1, PileType.OPEN, 0);

    assertEquals("F1: A♠\n"
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
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 5, 2, PileType.FOUNDATION, 0);
  }


  // move to foundation pile of same suit, but rank is not one greater
  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameSuitRankNotOneGreater() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 3, 1, PileType.FOUNDATION, 0);
    assertEquals("F1: A♠\n"
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 1, 1, PileType.FOUNDATION, 0);
  }


  // destination pile index out of bounds (negative) for moving to cascade pile
  @Test(expected = IllegalArgumentException.class)
  public void cascadeDestinationPileIndexOutofBounds() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 0, 6, PileType.CASCADE, -11);
  }


  // destination pile index out of bounds (too large) for moving to cascade pile
  @Test(expected = IllegalArgumentException.class)
  public void cascadeDestinationPileIndexOutofBoundsTooBig() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 0, 6, PileType.CASCADE, 11);
  }


  // destination pile index out of bounds for moving to foundation pile
  @Test(expected = IllegalArgumentException.class)
  public void foundationDestinationPileIndexOutofBounds() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, -11);
  }


  // destination pile index out of bounds for moving to foundation pile
  @Test(expected = IllegalArgumentException.class)
  public void openDestinationPileIndexOutofBounds() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 3, 6, PileType.OPEN, -11);
  }


  // multi-move where list of cards being moved is a valid build, but doesn't form
  // a valid build with the last card in the destination pile.
  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveDoesNotFormValidBuildWithDestination() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 4, 5, PileType.OPEN, 0);
    modelMulti.move(PileType.CASCADE, 2, 6, PileType.CASCADE, 4);

    assertEquals("F1:\n"
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 4, 4, PileType.CASCADE, 2);
  }


  // trying to move multiple cards that don't form an valid build
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidBuild() {
    List<Card> reverseDeck = modelMulti.getDeck();
    Collections.reverse(reverseDeck);

    modelMulti.startGame(reverseDeck, 8, 5, false);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", modelMulti.getGameState());

    modelMulti.move(PileType.CASCADE, 2, 4, PileType.CASCADE, 6);
  }





}
