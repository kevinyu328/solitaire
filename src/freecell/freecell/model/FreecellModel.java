package freecell.freecell.model;

import freecell.freecell.PileType;
import freecell.freecell.model.Card.Rank;
import freecell.freecell.model.Card.Suit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * A model for a freecell game that only allows for single moves. The game always starts with
 * four foundation piles.
 */
public class FreecellModel implements FreecellOperations<Card> {


  protected boolean isStarted;
  protected List<List<Card>> foundationPile;
  protected List<List<Card>> cascadePile;
  protected List<List<Card>> openPile;


  /**
   * Constructor for a freecell model.
   */
  public FreecellModel() {
    // Nothing should happen in the constructor. Game actually starts when startGame is called.
  }


  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<Card>();

    for (Suit s : Suit.values()) {
      for (Rank r : Rank.values()) {
        deck.add(new Card(r, s));
      }
    }
    return deck;
  }


  /**
   * Checks if the deck is a valid deck with 52 cards, no duplicates, and no invalid ranks or
   * suits.
   *
   * @return true if the deck is valid, false otherwise
   */
  protected boolean isValidDeck(List<Card> deck) {
    boolean containsAllCards = true;
    Set<Card> cardSet = new HashSet<Card>(deck);

    if (deck.size() == 52 && cardSet.size() == deck.size()) {
      for (Suit s : Suit.values()) {
        for (Rank r : Rank.values()) {
          if (!deck.contains(new Card(r, s))) {
            containsAllCards = false;
          }
        }
      }
    } else {
      return false;
    }
    return containsAllCards;
  }


  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {
    List<Card> deckCopy = new ArrayList<Card>();
    deckCopy.addAll(deck);
    //this.gameDeck = deckCopy;
    this.cascadePile = new ArrayList<>();
    this.openPile = new ArrayList<>();
    this.foundationPile = new ArrayList<>();

    if (isValidDeck(deckCopy) && numCascadePiles >= 4 && numOpenPiles >= 1) {

      for (int i = 0; i < numCascadePiles; i++) {
        this.cascadePile.add(new ArrayList<Card>());
      }

      for (int i = 0; i < numOpenPiles; i++) {
        this.openPile.add(new ArrayList<Card>());
      }

      for (int i = 0; i < 4; i++) {
        this.foundationPile.add(new ArrayList<Card>());
      }

      if (shuffle) {
        shuffleCards(deckCopy);

        for (int i = 0; i < deckCopy.size(); i++) {
          this.cascadePile.get(i % numCascadePiles).add(deckCopy.get(i));
        }

        isStarted = true;
      } else {
        for (int i = 0; i < deckCopy.size(); i++) {
          this.cascadePile.get(i % numCascadePiles).add(deckCopy.get(i));
        }
        isStarted = true;
      }
    } else {
      throw new IllegalArgumentException("Invalid parameters.");
    }
  }


  /**
   * Randomly shuffles the deck of cards.
   */
  protected void shuffleCards(List<Card> deck) {
    Random rand = new Random();

    for (int i = 0; i < deck.size(); i++) {
      int r = i + rand.nextInt(deck.size() - i);

      Card temp = deck.get(r);
      deck.set(r, deck.get(i));
      deck.set(i, temp);
    }
  }


  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {

    if (isStarted) {
      if (source == PileType.FOUNDATION || pileNumber < 0 || cardIndex < 0
          || getPile(source, pileNumber).size() != cardIndex + 1 ||
          source == null || destination == null || destPileNumber < 0) {
        throw new IllegalArgumentException("This move is not possible.");
      } else {
        Card movedCard = getCard(source, pileNumber, cardIndex);

        switch (destination) {
          case OPEN:
            if (destPileNumber < this.openPile.size()
                && this.openPile.get(destPileNumber).size() == 0) {
              this.openPile.get(destPileNumber).add(movedCard);
              getPile(source, pileNumber).remove(movedCard);
            } else {
              throw new IllegalArgumentException("Invalid move to an open pile.");
            }
            break;

          case CASCADE:
            if (destPileNumber < this.cascadePile.size()) {
              Card topCardCascade = getCard(PileType.CASCADE, destPileNumber,
                  this.cascadePile.get(destPileNumber).size() - 1);
              if (topCardCascade.getSuit() == Suit.SPADES
                  || topCardCascade.getSuit() == Suit.CLUBS) {
                if ((movedCard.getSuit() == Suit.HEARTS || movedCard.getSuit() == Suit.DIAMONDS) &&
                    (topCardCascade.getRank().ordinal() == movedCard.getRank().ordinal() + 1)) {
                  this.cascadePile.get(destPileNumber).add(movedCard);
                  getPile(source, pileNumber).remove(movedCard);
                } else {
                  throw new IllegalArgumentException("Invalid move to cascade pile.");
                }
              } else if (topCardCascade.getSuit() == Suit.HEARTS ||
                  topCardCascade.getSuit() == Suit.DIAMONDS) {
                if ((movedCard.getSuit() == Suit.SPADES || movedCard.getSuit() == Suit.CLUBS) &&
                    (topCardCascade.getRank().ordinal() == movedCard.getRank().ordinal() + 1)) {
                  this.cascadePile.get(destPileNumber).add(movedCard);
                  getPile(source, pileNumber).remove(movedCard);
                } else {
                  throw new IllegalArgumentException("Invalid move to cascade pile.");
                }
              }
            } else {
              throw new IllegalArgumentException(
                  "Index for the destination cascade pile is too big.");
            }

            break;

          case FOUNDATION:

            if (this.foundationPile.get(destPileNumber).size() == 0
                && destPileNumber < this.foundationPile.size()) {
              if (movedCard.getRank() == Rank.ACE) {
                this.foundationPile.get(destPileNumber).add(movedCard);
                getPile(source, pileNumber).remove(movedCard);
              } else {
                throw new IllegalArgumentException("Invalid move to empty foundation pile.");
              }
            } else {
              Card topCard = getCard(PileType.FOUNDATION, destPileNumber,
                  this.foundationPile.get(destPileNumber).size() - 1);
              if (this.foundationPile.get(destPileNumber).get(0).getSuit() == movedCard.getSuit() &&
                  topCard.getRank().ordinal() == movedCard.getRank().ordinal() - 1 &&
                  destPileNumber < this.foundationPile.size()) {
                this.foundationPile.get(destPileNumber).add(movedCard);
                getPile(source, pileNumber).remove(movedCard);
              } else {
                throw new IllegalArgumentException("Invalid move to a foundation pile.");
              }
            }
            break;
          default:
            throw new IllegalArgumentException("This is an invalid move.");
        }
      }
    } else {
      throw new IllegalStateException("Game has not started yet.");
    }
  }


  /**
   * Gets the pile specified by the pile type and pile number.
   *
   * @param source     the pile type
   * @param pileNumber the pile number for the pile type
   * @return the pile specified by the pile type and number
   */
  protected List<Card> getPile(PileType source, int pileNumber) {
    List<Card> pile;

    switch (source) {
      case CASCADE:
        if (pileNumber < this.cascadePile.size()) {
          pile = this.cascadePile.get(pileNumber);
        } else {
          throw new IllegalArgumentException("Pile number is greater than number of piles.");
        }
        break;
      case OPEN:
        if (pileNumber < this.openPile.size()) {
          pile = this.openPile.get(pileNumber);
        } else {
          throw new IllegalArgumentException("Pile number is greater than number of piles.");
        }
        break;
      case FOUNDATION:
        if (pileNumber < this.foundationPile.size()) {
          pile = this.foundationPile.get(pileNumber);
        } else {
          throw new IllegalArgumentException("Pile number is greater than number of piles.");
        }
        break;
      default:
        throw new IllegalArgumentException("Specified pile is not valid.");
    }
    return pile;
  }


  @Override
  public boolean isGameOver() {
    boolean allFoundationFull = true;
    if (isStarted) {
      for (int i = 0; i < 4; i++) {
        if (this.foundationPile.get(i).size() != 13) {
          allFoundationFull = false;
        }
      }
      return allFoundationFull;
    } else {
      return false;
    }
  }

  @Override
  public Card getCard(PileType pile, int pileNumber, int cardIndex) {
    Card specifiedCard;

    if (isStarted) {
      if (pileNumber < 0 || pileNumber >= this.cascadePile.size() || cardIndex < 0
          || pile == null) {
        throw new IllegalArgumentException("Pile index is out of bounds.");
      } else {
        switch (pile) {
          case CASCADE:
            if (this.cascadePile.get(pileNumber).size() > cardIndex) {
              specifiedCard = this.cascadePile.get(pileNumber).get(cardIndex);
            } else {
              throw new IllegalArgumentException(
                  "Card index is out of bounds for the specified pile.");
            }
            break;
          case OPEN:
            if (this.openPile.get(pileNumber).size() > cardIndex) {
              specifiedCard = this.openPile.get(pileNumber).get(cardIndex);
            } else {
              throw new IllegalArgumentException(
                  "Card index is out of bounds for the specified pile.");
            }
            break;
          case FOUNDATION:
            if (this.foundationPile.get(pileNumber).size() > cardIndex) {
              specifiedCard = this.foundationPile.get(pileNumber).get(cardIndex);
            } else {
              throw new IllegalArgumentException(
                  "Card index is out of bounds for the specified pile.");
            }
            break;
          default:
            throw new IllegalArgumentException("Specified card doesn't exist.");
        }
      }
      return specifiedCard;
    } else {
      throw new IllegalStateException("Game has not started.");
    }

  }

  @Override
  public String getGameState() {
    StringBuilder result = new StringBuilder("");

    if (isStarted) {

      for (int i = 0; i < this.foundationPile.size(); i++) {
        if (this.foundationPile.get(i).size() == 0) {
          result = result.append("F").append(Integer.toString(i + 1)).append(":\n");
        } else {
          result = result.append("F").append(Integer.toString(i + 1)).append(": ");
          for (int j = 0; j < this.foundationPile.get(i).size(); j++) {
            if (this.foundationPile.get(i).get(j)
                .equals(this.foundationPile.get(i).get(this.foundationPile.get(i).size() - 1))) {
              result = result.append(this.foundationPile.get(i).get(j).toString()).append("\n");
            } else {
              result = result.append(this.foundationPile.get(i).get(j).toString()).append(", ");
            }
          }
        }
      }

      for (int i = 0; i < this.openPile.size(); i++) {
        if (this.openPile.get(i).size() == 0) {
          result = result.append("O").append(Integer.toString(i + 1)).append(":\n");
        } else {
          result = result.append("O").append(Integer.toString(i + 1)).append(": ");
          result = result.append(this.openPile.get(i).get(0).toString()).append("\n");
        }
      }

      for (int i = 0; i < this.cascadePile.size(); i++) {
        if (this.cascadePile.size() - 1 == i && this.cascadePile.get(i).size() == 0) {
          result = result.append("C").append(Integer.toString(i + 1)).append(":");
        } else if (this.cascadePile.get(i).size() == 0) {
          result = result.append("C").append(Integer.toString(i + 1)).append(":\n");
        } else {
          result = result.append("C").append(Integer.toString(i + 1)).append(": ");
          for (int j = 0; j < this.cascadePile.get(i).size(); j++) {
            if (this.cascadePile.get(i).get(j)
                .equals(this.cascadePile.get(i).get(this.cascadePile.get(i).size() - 1))) {
              if (i == this.cascadePile.size() - 1) {
                result = result.append(this.cascadePile.get(i).get(j).toString());
              } else {
                result = result.append(this.cascadePile.get(i).get(j).toString()).append("\n");
              }
            } else {
              result = result.append(this.cascadePile.get(i).get(j).toString()).append(", ");
            }
          }
        }
      }
    } else {
      return "";
    }
    return result.toString();
  }


}

