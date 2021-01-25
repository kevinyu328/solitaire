package freecell.freecell;

import freecell.freecell.controller.FreecellController;
import freecell.freecell.model.FreecellOperations;
import freecell.freecell.model.Card;
import freecell.freecell.model.FreecellModel;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * Run a Freecell game interactively on the console.
 */
public class Main {

  static FreecellOperations model = new FreecellModel();


  /**
   * Run a Tic Tac Toe game interactively on the console.
   */
  public static void main(String[] args) {
    List<Card> reverseDeck = model.getDeck();
    Collections.reverse(reverseDeck);

    new FreecellController(new InputStreamReader(System.in),
        System.out).playGame(reverseDeck, model, 8, 5, false);
  }

}
