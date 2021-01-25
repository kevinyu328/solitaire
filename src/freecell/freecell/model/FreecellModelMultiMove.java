package freecell.freecell.model;

import freecell.freecell.PileType;
import freecell.freecell.model.Card.Rank;
import freecell.freecell.model.Card.Suit;
import java.util.ArrayList;
import java.util.List;


/**
 * A model for a Freecell game that allows for multi-moves. Extends the basic Freecell model.
 */
public class FreecellModelMultiMove extends FreecellModel {

  /**
   * A constructor for a freecell game that can move multiple cards at once.
   */
  public FreecellModelMultiMove() {
    // nothing happens in the constructor. Game starts when startGame is called.
  }


  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {

    if (isStarted) {
      if (source == PileType.FOUNDATION || pileNumber < 0 || destPileNumber < 0 ||
          cardIndex < 0 || source == null || destination == null) {
        throw new IllegalArgumentException("This move is not possible.");
      } else {
        Card movedCard = getCard(source, pileNumber, cardIndex);

        switch (destination) {
          case OPEN:
            if (destPileNumber < this.openPile.size()
                && this.openPile.get(destPileNumber).size() == 0
                && cardIndex + 1 == getPile(source, pileNumber).size()) {
              this.openPile.get(destPileNumber).add(movedCard);
              getPile(source, pileNumber).remove(movedCard);
            } else {
              throw new IllegalArgumentException("Invalid move to an open pile.");
            }
            break;

          case CASCADE:
            if (getPile(source, pileNumber).size() - cardIndex >
                (getEmptyPiles(this.openPile) + 1) * (Math
                    .pow(2, getEmptyPiles(this.cascadePile)))) {
              throw new IllegalArgumentException(
                  "This exceeds the maximum amount of cards that can be moved.");
            } else if (destPileNumber >= this.cascadePile.size()) {
              throw new IllegalArgumentException(
                  "Destination pile index is greater than the number of cascade piles.");
            } else {
              Card topCardCascade = getCard(PileType.CASCADE, destPileNumber,
                  this.cascadePile.get(destPileNumber).size() - 1);

              List<Card> movedCardsSubList = getPile(source, pileNumber)
                  .subList(cardIndex, getPile(source, pileNumber).size());
              List<Card> movedCardsList = new ArrayList<Card>();
              movedCardsList.addAll(movedCardsSubList);

              if (isValidBuild(movedCardsList) && isDifferentSuit(movedCard, topCardCascade) &&
                  movedCard.getRank().ordinal() + 1 == topCardCascade.getRank().ordinal()) {

                for (int i = 0; i < movedCardsList.size(); i++) {
                  this.cascadePile.get(destPileNumber).add(movedCardsList.get(i));
                  getPile(source, pileNumber).remove(movedCardsList.get(i));
                }
              } else {
                throw new IllegalArgumentException("Invalid card move to cascade pile.");
              }

            }
            break;

          case FOUNDATION:

            if (this.foundationPile.get(destPileNumber).size() == 0
                && destPileNumber < this.foundationPile.size()
                && cardIndex + 1 == getPile(source, pileNumber).size()) {
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
   * Returns the number of empty piles in the specified pile type.
   *
   * @param pileType the pileType in which to count the number of empty piles that exists
   * @return the number of empty piles
   */
  private int getEmptyPiles(List<List<Card>> pileType) {
    int numEmpty = 0;

    for (int i = 0; i < pileType.size(); i++) {
      if (pileType.get(i).size() == 0) {
        numEmpty = numEmpty + 1;
      }
    }
    return numEmpty;
  }


  /**
   * Determines whether or not the list of cards is a valid build.
   *
   * @param listCard the list of cards that need to be determined whether it is a valid build
   * @return true if the pile is a valid build list, false otherwise
   */
  private boolean isValidBuild(List<Card> listCard) {
    return isConsecutiveDescending(listCard) && isAlternatingColors(listCard);
  }


  /**
   * Determines whether or not the list of cards is in consecutive descending order.
   *
   * @param listCard the list of cards
   * @return true if the list of cards are in consecutive descending order, false otherwise
   */
  private boolean isConsecutiveDescending(List<Card> listCard) {
    boolean isConsecutiveDescending = true;

    for (int i = 0; i < listCard.size() - 1; i++) {
      Card currentCard = listCard.get(i);
      Card nextCard = listCard.get(i + 1);

      if (currentCard.getRank().ordinal() != nextCard.getRank().ordinal() + 1) {
        isConsecutiveDescending = false;
      }

    }
    return isConsecutiveDescending;
  }


  /**
   * Determines whether or not the list of cards is in alternating colors.
   *
   * @param listCard the list of cards
   * @return true if the list of cards are in alternating colors, false otherwise
   */
  private boolean isAlternatingColors(List<Card> listCard) {
    boolean isAlternatingColors = true;

    for (int i = 0; i < listCard.size() - 1; i++) {
      Card currentCard = listCard.get(i);
      Card nextCard = listCard.get(i + 1);

      if (currentCard.getSuit() == Suit.SPADES || currentCard.getSuit() == Suit.CLUBS) {
        if (nextCard.getSuit() == Suit.SPADES || nextCard.getSuit() == Suit.CLUBS) {
          isAlternatingColors = false;
        }
      } else if (currentCard.getSuit() == Suit.HEARTS || currentCard.getSuit() == Suit.DIAMONDS) {
        if (nextCard.getSuit() == Suit.HEARTS || nextCard.getSuit() == Suit.DIAMONDS) {
          isAlternatingColors = false;
        }
      }
    }
    return isAlternatingColors;
  }


  /**
   * Determines whether or not two cards have different suits.
   *
   * @param card1 the first card
   * @param card2 the second card
   * @return true if the cards have different suits, false if the have the same suit
   */
  private boolean isDifferentSuit(Card card1, Card card2) {
    boolean isDifferentSuit = true;

    if (card1.getSuit() == Suit.SPADES || card1.getSuit() == Suit.CLUBS) {
      if (card2.getSuit() == Suit.SPADES || card2.getSuit() == Suit.CLUBS) {
        isDifferentSuit = false;
      }
    } else if (card1.getSuit() == Suit.HEARTS || card1.getSuit() == Suit.DIAMONDS) {
      if (card2.getSuit() == Suit.HEARTS || card2.getSuit() == Suit.DIAMONDS) {
        isDifferentSuit = false;
      }
    }
    return isDifferentSuit;
  }


}
