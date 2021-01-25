import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import freecell.freecell.model.Card;
import freecell.freecell.model.Card.Rank;
import freecell.freecell.model.Card.Suit;
import freecell.freecell.model.FreecellOperations;
import freecell.freecell.PileType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

import freecell.freecell.model.FreecellModel;


/**
 * A test class for the Freecell Model. Tests ensure that a freecell model is constructed
 * correctly.
 */
public class FreecellModelTest {

  FreecellOperations testModel = new FreecellModel();
  List<Card> deck = testModel.getDeck();


  @Test
  public void testGetDeck() {
    // checks deck size is 52
    assertEquals(52, testModel.getDeck().size());

    // checks that all suits and ranks are in the deck
    for (Suit s : Suit.values()) {
      for (Rank r : Rank.values()) {
        assertTrue(testModel.getDeck().contains(new Card(r, s)));
      }
    }

    // checks that there are no duplicate cards in the deck
    Set<Card> cardSet = new HashSet<Card>(testModel.getDeck());
    assertEquals(cardSet.size(), testModel.getDeck().size());
  }


  @Test
  public void testStartGame() {
    List<Card> reverseDeck = testModel.getDeck();
    Collections.reverse(reverseDeck);

    FreecellOperations testModelShuffle = new FreecellModel();

    // start game without shuffling
    testModel.startGame(deck, 8, 4, false);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♠, 9♠, 4♥, Q♥, 7♦, 2♣, 10♣\n"
        + "C2: 2♠, 10♠, 5♥, K♥, 8♦, 3♣, J♣\n"
        + "C3: 3♠, J♠, 6♥, A♦, 9♦, 4♣, Q♣\n"
        + "C4: 4♠, Q♠, 7♥, 2♦, 10♦, 5♣, K♣\n"
        + "C5: 5♠, K♠, 8♥, 3♦, J♦, 6♣\n"
        + "C6: 6♠, A♥, 9♥, 4♦, Q♦, 7♣\n"
        + "C7: 7♠, 2♥, 10♥, 5♦, K♦, 8♣\n"
        + "C8: 8♠, 3♥, J♥, 6♦, A♣, 9♣", testModel.getGameState());

    // start game by shuffling the same deck used above
    testModelShuffle.startGame(deck, 8, 4, true);
    assertNotEquals(testModelShuffle.getGameState(), testModel.getGameState());

    // testing start game with the same deck, but different cascade and open piles
    testModel.startGame(deck, 6, 10, false);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "O6:\n"
        + "O7:\n"
        + "O8:\n"
        + "O9:\n"
        + "O10:\n"
        + "C1: A♠, 7♠, K♠, 6♥, Q♥, 5♦, J♦, 4♣, 10♣\n"
        + "C2: 2♠, 8♠, A♥, 7♥, K♥, 6♦, Q♦, 5♣, J♣\n"
        + "C3: 3♠, 9♠, 2♥, 8♥, A♦, 7♦, K♦, 6♣, Q♣\n"
        + "C4: 4♠, 10♠, 3♥, 9♥, 2♦, 8♦, A♣, 7♣, K♣\n"
        + "C5: 5♠, J♠, 4♥, 10♥, 3♦, 9♦, 2♣, 8♣\n"
        + "C6: 6♠, Q♠, 5♥, J♥, 4♦, 10♦, 3♣, 9♣", testModel.getGameState());

    // testing start game with a different (reversed) deck
    testModel.startGame(reverseDeck, 5, 8, false);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "O6:\n"
        + "O7:\n"
        + "O8:\n"
        + "C1: K♣, 8♣, 3♣, J♦, 6♦, A♦, 9♥, 4♥, Q♠, 7♠, 2♠\n"
        + "C2: Q♣, 7♣, 2♣, 10♦, 5♦, K♥, 8♥, 3♥, J♠, 6♠, A♠\n"
        + "C3: J♣, 6♣, A♣, 9♦, 4♦, Q♥, 7♥, 2♥, 10♠, 5♠\n"
        + "C4: 10♣, 5♣, K♦, 8♦, 3♦, J♥, 6♥, A♥, 9♠, 4♠\n"
        + "C5: 9♣, 4♣, Q♦, 7♦, 2♦, 10♥, 5♥, K♠, 8♠, 3♠", testModel.getGameState());
  }


  // start game with an invalid deck
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameInvalidDeck() {
    List<Card> invalidDeck = new ArrayList<>();
    invalidDeck.add(new Card(Rank.ACE, Suit.SPADES));
    invalidDeck.add(new Card(Rank.FIVE, Suit.HEARTS));

    testModel.startGame(invalidDeck, 8, 4, false);
  }

  // start game with invalid number of cascade piles
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameInvalidCascade() {
    testModel.startGame(testModel.getDeck(), 2, 4, false);
  }


  // start game with invalid number of open piles
  @Test(expected = IllegalArgumentException.class)
  public void testStartGameInvalidOpen() {
    testModel.startGame(testModel.getDeck(), 5, 0, true);
  }


  @Test
  public void testMove() {
    List<Card> reverseDeck = testModel.getDeck();
    Collections.reverse(reverseDeck);
    testModel.startGame(reverseDeck, 6, 4, false);

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
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", testModel.getGameState());

    // move from cascade to empty foundation pile
    testModel.move(PileType.CASCADE, 3, 8, PileType.FOUNDATION,
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
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", testModel.getGameState());

    // move from cascade to open pile
    testModel.move(PileType.CASCADE, 1, 8, PileType.OPEN, 0);
    testModel.move(PileType.CASCADE, 1, 7, PileType.OPEN, 1);
    testModel.move(PileType.CASCADE, 1, 6, PileType.OPEN, 2);
    testModel.move(PileType.CASCADE, 1, 5, PileType.OPEN, 3);

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
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", testModel.getGameState());

    // move from cascade to cascade pile
    testModel.move(PileType.CASCADE, 1, 4, PileType.CASCADE, 2);

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
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", testModel.getGameState());

    // move from cascade to empty foundation pile
    testModel.move(PileType.CASCADE, 2, 9, PileType.FOUNDATION, 1);

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
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", testModel.getGameState());

    // move from cascade to non-empty foundation pile
    testModel.move(PileType.CASCADE, 2, 8, PileType.FOUNDATION, 0);

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
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", testModel.getGameState());

    // move from open to non-empty foundation pile
    testModel.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);

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
        + "C6: 8♣, 2♣, 9♦, 3♦, 10♥, 4♥, J♠, 5♠", testModel.getGameState());

    // move from open to cascade pile

  }


  // move when game hasn't started
  @Test(expected = IllegalStateException.class)
  public void testMoveNotStarted() {
    testModel.move(PileType.CASCADE, 1, 8, PileType.OPEN, 0);
  }

  // move from a foundation pile
  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromFoundation() {
    testModel.startGame(deck, 8, 5, false);

    testModel.move(PileType.FOUNDATION, 0, 0, PileType.OPEN, 2);
  }

  // move to non-empty open pile
  @Test(expected = IllegalArgumentException.class)
  public void testMoveToNonEmptyOpenPile() {
    testModel.startGame(deck, 8, 5, false);

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
        + "C8: 8♠, 3♥, J♥, 6♦, A♣, 9♣", testModel.getGameState());

    testModel.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);

    testModel.move(PileType.CASCADE, 0, 5, PileType.OPEN, 0);
  }


  // move to cascade of same suit color (red)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveToCascadeSameSuitColorRed() {
    List<Card> reverseDeck = testModel.getDeck();
    Collections.reverse(reverseDeck);

    testModel.startGame(reverseDeck, 8, 5, false);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", testModel.getGameState());

    testModel.move(PileType.CASCADE, 4, 2, PileType.OPEN, 0);
    testModel.move(PileType.CASCADE, 5, 2, PileType.OPEN, 1);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", testModel.getGameState());

    testModel.move(PileType.CASCADE, 5, 1, PileType.CASCADE, 4);
  }

  // move to cascade of same suit color (black)
  @Test(expected = IllegalArgumentException.class)
  public void testMoveToCascadeSameSuitColorBlack() {
    List<Card> reverseDeck = testModel.getDeck();
    Collections.reverse(reverseDeck);

    testModel.startGame(reverseDeck, 8, 5, false);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", testModel.getGameState());

    testModel.move(PileType.CASCADE, 1, 2, PileType.CASCADE, 0);

  }

  // move to cascade of different suit color but moved card's rank is not one less than rank
  // of top card of destination cascade
  @Test(expected = IllegalArgumentException.class)
  public void testMoveToCascadeNotOneLess() {
    List<Card> reverseDeck = testModel.getDeck();
    Collections.reverse(reverseDeck);

    testModel.startGame(reverseDeck, 8, 5, false);
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", testModel.getGameState());

    testModel.move(PileType.CASCADE, 5, 2, PileType.OPEN, 0);
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", testModel.getGameState());

    testModel.move(PileType.CASCADE, 5, 2, PileType.CASCADE, 0);
  }


  // move non-ace to empty foundation pile
  @Test(expected = IllegalArgumentException.class)
  public void testMoveNonAceToEmptyFoundation() {
    List<Card> reverseDeck = testModel.getDeck();
    Collections.reverse(reverseDeck);

    testModel.startGame(reverseDeck, 8, 5, false);
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", testModel.getGameState());

    testModel.move(PileType.CASCADE, 0, 1, PileType.FOUNDATION, 0);
  }


  // move to non-empty foundation pile of different suit
  @Test(expected = IllegalArgumentException.class)
  public void testMoveDifferentSuitFoundation() {
    List<Card> reverseDeck = testModel.getDeck();
    Collections.reverse(reverseDeck);

    testModel.startGame(reverseDeck, 8, 5, false);
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", testModel.getGameState());

    testModel.move(PileType.CASCADE, 3, 1, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 5, 1, PileType.OPEN, 0);

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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", testModel.getGameState());

    testModel.move(PileType.CASCADE, 5, 2, PileType.FOUNDATION, 0);
  }


  // move to foundation pile of same suit, but rank is not one greater
  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameSuitRankNotOneGreater() {
    List<Card> reverseDeck = testModel.getDeck();
    Collections.reverse(reverseDeck);

    testModel.startGame(reverseDeck, 8, 5, false);
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", testModel.getGameState());

    testModel.move(PileType.CASCADE, 3, 1, PileType.FOUNDATION, 0);
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
        + "C8: 6♣, J♦, 3♦, 8♥, K♠, 5♠", testModel.getGameState());

    testModel.move(PileType.CASCADE, 1, 1, PileType.FOUNDATION, 0);
  }


  @Test
  public void testIsGameOver() {
    List<Card> reverseDeck = testModel.getDeck();
    Collections.reverse(reverseDeck);

    testModel.startGame(reverseDeck, 8, 5, false);

    assertEquals(false, testModel.isGameOver());

    // placing all Spades into F1
    testModel.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 0);
    assertEquals(false, testModel.isGameOver());

    // placing all Hearts into F2
    testModel.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 1);
    testModel.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 1);
    testModel.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 1);
    testModel.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 1);
    testModel.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 1);
    testModel.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 1);
    testModel.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 1);
    testModel.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 1);
    testModel.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 1);
    testModel.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 1);
    testModel.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 1);
    testModel.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 1);
    testModel.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 1);
    assertEquals(false, testModel.isGameOver());

    // placing all Diamonds into F3
    testModel.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 2);
    testModel.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 2);
    testModel.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 2);
    testModel.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 2);
    testModel.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 2);
    testModel.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 2);
    testModel.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);
    testModel.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 2);
    testModel.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 2);
    testModel.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 2);
    testModel.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 2);
    testModel.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 2);
    testModel.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 2);
    assertEquals(false, testModel.isGameOver());

    // placing all Clubs into F4
    testModel.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 3);
    testModel.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 3);
    testModel.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 3);
    testModel.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 3);
    testModel.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 3);
    testModel.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 3);
    testModel.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION, 3);
    testModel.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION, 3);
    testModel.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 3);
    testModel.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 3);
    testModel.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 3);
    testModel.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 3);
    testModel.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 3);

    assertEquals("F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "F4: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4:\n"
        + "C5:\n"
        + "C6:\n"
        + "C7:\n"
        + "C8:", testModel.getGameState());

    assertEquals(true, testModel.isGameOver());
  }


  @Test
  public void testGetCard() {
    List<Card> reverseDeck = testModel.getDeck();
    Collections.reverse(reverseDeck);

    testModel.startGame(reverseDeck, 8, 5, false);
    testModel.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    testModel.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION, 0);

    testModel.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);

    assertEquals("F1: A♠, 2♠, 3♠, 4♠, 5♠\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: Q♠\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "O5:\n"
        + "C1: K♣, 5♣, 10♦, 2♦, 7♥\n"
        + "C2: Q♣, 4♣, 9♦, A♦, 6♥, J♠\n"
        + "C3: J♣, 3♣, 8♦, K♥, 5♥, 10♠\n"
        + "C4: 10♣, 2♣, 7♦, Q♥, 4♥, 9♠\n"
        + "C5: 9♣, A♣, 6♦, J♥, 3♥, 8♠\n"
        + "C6: 8♣, K♦, 5♦, 10♥, 2♥, 7♠\n"
        + "C7: 7♣, Q♦, 4♦, 9♥, A♥, 6♠\n"
        + "C8: 6♣, J♦, 3♦, 8♥, K♠", testModel.getGameState());

    // getting card from cascade pile
    assertEquals(testModel.getCard(PileType.CASCADE, 0, 3), new Card(Rank.TWO, Suit.DIAMONDS));

    // getting a card from a foundation pile
    assertEquals(testModel.getCard(PileType.FOUNDATION, 0, 2), new Card(Rank.THREE, Suit.SPADES));

    // getting a card from an open pile
    assertEquals(testModel.getCard(PileType.OPEN, 0, 0), new Card(Rank.QUEEN, Suit.SPADES));
  }

  // getting a card when the game has not started
  @Test(expected = IllegalStateException.class)
  public void testGetCardNotStarted() {
    testModel.getCard(PileType.FOUNDATION, 0, 1);
  }

  // getting a card with a negative card index
  @Test(expected = IllegalArgumentException.class)
  public void testCardIndexNegative() {
    testModel.startGame(deck, 8, 5, false);

    testModel.getCard(PileType.CASCADE, 0, -1);
  }

  // getting a card with a negative pile number
  @Test(expected = IllegalArgumentException.class)
  public void testPileNumNegative() {
    testModel.startGame(deck, 8, 5, false);

    testModel.getCard(PileType.CASCADE, -3, 0);
  }

  // getting a card who's index is greater than the pile's size
  @Test(expected = IllegalArgumentException.class)
  public void testCardIndexGreaterThanPileSize() {
    testModel.startGame(deck, 8, 5, false);

    testModel.getCard(PileType.CASCADE, 0, 100);
  }


  // getting a card who's Pilenum is greater than the number of existing piles
  @Test(expected = IllegalArgumentException.class)
  public void testPileNumGreaterThanAmountOfPiles() {
    testModel.startGame(deck, 8, 5, false);

    testModel.getCard(PileType.CASCADE, 100, 0);
  }


  @Test
  public void testGetGameState() {
    testModel.startGame(deck, 8, 4, false);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♠, 9♠, 4♥, Q♥, 7♦, 2♣, 10♣\n"
        + "C2: 2♠, 10♠, 5♥, K♥, 8♦, 3♣, J♣\n"
        + "C3: 3♠, J♠, 6♥, A♦, 9♦, 4♣, Q♣\n"
        + "C4: 4♠, Q♠, 7♥, 2♦, 10♦, 5♣, K♣\n"
        + "C5: 5♠, K♠, 8♥, 3♦, J♦, 6♣\n"
        + "C6: 6♠, A♥, 9♥, 4♦, Q♦, 7♣\n"
        + "C7: 7♠, 2♥, 10♥, 5♦, K♦, 8♣\n"
        + "C8: 8♠, 3♥, J♥, 6♦, A♣, 9♣", testModel.getGameState());

    testModel.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1: 10♣\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♠, 9♠, 4♥, Q♥, 7♦, 2♣\n"
        + "C2: 2♠, 10♠, 5♥, K♥, 8♦, 3♣, J♣\n"
        + "C3: 3♠, J♠, 6♥, A♦, 9♦, 4♣, Q♣\n"
        + "C4: 4♠, Q♠, 7♥, 2♦, 10♦, 5♣, K♣\n"
        + "C5: 5♠, K♠, 8♥, 3♦, J♦, 6♣\n"
        + "C6: 6♠, A♥, 9♥, 4♦, Q♦, 7♣\n"
        + "C7: 7♠, 2♥, 10♥, 5♦, K♦, 8♣\n"
        + "C8: 8♠, 3♥, J♥, 6♦, A♣, 9♣", testModel.getGameState());
  }


}





