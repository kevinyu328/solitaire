package freecell.freecell.controller;

import freecell.freecell.model.FreecellOperations;
import java.util.List;

/**
 * Represents an interface of the Freecell controller, parameterized over the card type.
 *
 * @param <K> card type
 */
public interface IFreecellController<K> {


  /**
   * Starts a new game of Freecell with the given arguments.
   *
   * @param deck        the deck used in the game
   * @param model       model used to play the game
   * @param numCascades number of cascade piles
   * @param numOpens    number of open piles
   * @param shuffle     if true, shuffle the deck. else deal the deck as-is
   * @throws IllegalArgumentException when a null deck or model is passed in
   * @throws IllegalStateException    when the append fails or when there are no more inputs to
   *                                  process
   */
  void playGame(List<K> deck, FreecellOperations<K> model, int numCascades,
      int numOpens, boolean shuffle);

}
