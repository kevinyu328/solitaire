package freecell.freecell.model;

/**
 * A class to represent a playing card.
 */
public class Card {


  /**
   * Represents the possible suits for a playing card with its corresponding symbols.
   */
  public enum Suit {
    SPADES("♠"), HEARTS("♥"), DIAMONDS("♦"), CLUBS("♣");

    private final String symbol;

    Suit(String symbol) {
      this.symbol = symbol;
    }

    public String getSymbol() {
      return symbol;
    }
  }

  /**
   * Represents the possible ranks for a playing card with their corresponding symbols.
   */
  public enum Rank {
    ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
    JACK("J"), QUEEN("Q"), KING("K");

    private final String rank;

    Rank(String rank) {
      this.rank = rank;
    }

    public String getRank() {
      return rank;
    }

  }

  private final Rank rank;
  private final Suit suit;

  /**
   * Constructs a card with a rank and suit.
   *
   * @param rank the rank of the card
   * @param suit the suit of the card.
   * @throws IllegalArgumentException if used an invalid rank or suit.
   */
  public Card(Rank rank, Suit suit) {
    if (rank instanceof Rank) {
      this.rank = rank;
    } else {
      throw new IllegalArgumentException("Inputted rank is invalid.");
    }
    if (suit instanceof Suit) {
      this.suit = suit;
    } else {
      throw new IllegalArgumentException("Inputted suit is invalid.");
    }
  }


  @Override
  public String toString() {
    return String.format("%s%s", rank.getRank(), suit.getSymbol());
  }


  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Card) {
      return ((Card) obj).rank == this.rank && ((Card) obj).suit == this.suit;
    } else {
      return false;
    }
  }


  @Override
  public int hashCode() {
    return rank.hashCode() + suit.hashCode();
  }


  /**
   * Gets the suit of the card.
   * @return the suit of the card
   */
  public Suit getSuit() {
    return this.suit;
  }

  /**
   * Gets the rank of the card.
   * @return the rank of the card
   */
  public Rank getRank() {
    return this.rank;
  }


}
