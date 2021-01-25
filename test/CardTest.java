import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cs3500.freecell.hw02.Card;
import cs3500.freecell.hw02.Card.Rank;
import cs3500.freecell.hw02.Card.Suit;
import org.junit.Test;

/**
 * Test class for Cards. Tests ensure that Cards are constructed correctly.
 */
public class CardTest {



  @Test(expected =  IllegalArgumentException.class)
  public void testInvalidCardRank() {
    new Card(null, Suit.DIAMONDS);
  }

  @Test(expected =  IllegalArgumentException.class)
  public void testInvalidCardSuit() {
    new Card(Rank.NINE, null);
  }


  @Test(expected =  IllegalArgumentException.class)
  public void testInvalidCardNull() {
    new Card(null, null);
  }


  @Test
  public void testToString() {
    assertEquals("A♠", new Card(Rank.ACE, Suit.SPADES).toString());
    assertEquals("3♣", new Card(Rank.THREE, Suit.CLUBS).toString());
    assertEquals("8♦", new Card(Rank.EIGHT, Suit.DIAMONDS).toString());
    assertEquals("J♥", new Card(Rank.JACK, Suit.HEARTS).toString());
  }


  @Test
  public void testHashCodeEquals() {
    Card kingSpades = new Card(Rank.KING, Suit.SPADES);
    Card kingSpades2 = new Card(Rank.KING, Suit.SPADES);
    Card kingDiamonds = new Card(Rank.KING, Suit.DIAMONDS);

    assertEquals(kingSpades, kingSpades2);
    assertEquals(kingSpades.hashCode(), kingSpades2.hashCode());
    assertNotEquals(kingSpades, kingDiamonds);
    assertNotEquals(kingSpades.hashCode(), kingDiamonds.hashCode());
  }



}
