package cs3500.freecell.hw03;

import cs3500.freecell.hw02.Card;
import cs3500.freecell.hw02.FreecellOperations;
import cs3500.freecell.hw02.PileType;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents a controller for a Freecell game. Takes in moves as a string with three inputs: a
 * source pile, card index, and destination pile.
 */
public class FreecellController implements IFreecellController<Card> {


  private final Appendable out;
  private final Scanner scan;

  private String nextString;

  private PileType sourcePile;
  private int sourcePileNum;
  private int cardIndex;
  private PileType destinationPile;
  private int destinationPileNum;

  private boolean validSourcePile;
  private boolean validSourceNum;
  private boolean validDestPile;
  private boolean validDestNum;


  /**
   * A constructor for a Freecell controller.
   *
   * @param rd the input from the user
   * @param ap the output of the controller
   */
  public FreecellController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = ap;
    scan = new Scanner(rd);
  }


  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model, int numCascades,
      int numOpens, boolean shuffle) {

    if (deck == null || model == null) {
      throw new IllegalArgumentException("A null deck or model cannot be passed in.");
    } else {
      try {
        model.startGame(deck, numCascades, numOpens, shuffle);
        append(model.getGameState());
      } catch (IllegalArgumentException iae) {
        try {
          out.append("Could not start game.");
          return;
        } catch (IOException ioe) {
          throw new IllegalStateException("Append failed", ioe);
        }
      }
    }

    while (!model.isGameOver()) {

      validSourcePile = false;
      validSourceNum = false;
      boolean validCardIndex = false;
      validDestPile = false;
      validDestNum = false;

      try {
        while (!validSourcePile || !validSourceNum) {
          nextString = scan.next();

          if (nextString.equalsIgnoreCase("q")) {
            try {
              out.append("Game quit prematurely.");
              return;
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          } else {
            getPile("source");
          }
        }

        while (!validCardIndex) {
          String index = scan.next();
          if (index.equalsIgnoreCase("q")) {
            try {
              out.append("Game quit prematurely.");
              return;
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          } else {
            try {
              cardIndex = Integer.valueOf(index) - 1;
              validCardIndex = true;
            } catch (NumberFormatException nfe) {
              append("Invalid index. Please re-enter a valid card index.");
            }
          }
        }

        while (!validDestPile || !validDestNum) {
          nextString = scan.next();

          if (nextString.equalsIgnoreCase("q")) {
            try {
              out.append("Game quit prematurely.");
              return;
            } catch (IOException ioe) {
              throw new IllegalStateException("Append failed", ioe);
            }
          } else {
            getPile("destination");
          }
        }
      } catch (NoSuchElementException nse) {
        throw new IllegalStateException("No more valid inputs to process.", nse);
      }

      if (validSourcePile && validSourceNum && validDestPile && validDestNum) {
        try {
          model.move(sourcePile, sourcePileNum, cardIndex, destinationPile, destinationPileNum);
          append(model.getGameState());
          if (model.isGameOver()) {
            append("Game over.");
            return;
          }
        } catch (IllegalArgumentException | IllegalStateException exception) {
          try {
            String errorMessage = exception.getMessage();
            out.append("Invalid move. Try again. ").append(errorMessage + "\n");
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
        }
      }
    }


  }


  /**
   * Appends the given message to the appendable out with a new line at the end.
   *
   * @param message the message to be appended
   */
  private void append(String message) {
    try {
      out.append(message).append("\n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }


  /**
   * Gets the pile indicated by the user input.
   *
   * @param pileType either "source" or "destination" for whether the input is the source or
   *                 destination pile
   */

  private void getPile(String pileType) {
    String sourceType;
    String sourceNum;

    String[] sourceAsArray = nextString.split("", 2);

    sourceType = sourceAsArray[0];
    sourceNum = sourceAsArray[1];

    switch (sourceType) {
      case "C":
        if (pileType.equals("source")) {
          sourcePile = PileType.CASCADE;
          validSourcePile = true;
        } else if (pileType.equals("destination")) {
          destinationPile = PileType.CASCADE;
          validDestPile = true;
        }
        break;
      case "F":
        if (pileType.equals("source")) {
          sourcePile = PileType.FOUNDATION;
          validSourcePile = true;
        } else if (pileType.equals("destination")) {
          destinationPile = PileType.FOUNDATION;
          validDestPile = true;
        }
        break;
      case "O":
        if (pileType.equals("source")) {
          sourcePile = PileType.OPEN;
          validSourcePile = true;
        } else if (pileType.equals("destination")) {
          destinationPile = PileType.OPEN;
          validDestPile = true;
        }
        break;
      default:
        if (pileType.equals("source")) {
          sourcePile = null;
          validSourcePile = false;
        } else if (pileType.equals("destination")) {
          destinationPile = null;
          validDestPile = false;
        }
        append("Invalid pile type. The pile type must be either C, F, or O. "
            + "Please re-enter a valid pile type and pile number.");
    }

    if (validSourcePile || validDestPile) {
      if (pileType.equals("source")) {
        try {
          sourcePileNum = Integer.valueOf(sourceNum) - 1;
          validSourceNum = true;
        } catch (NumberFormatException nfe) {
          append("Invalid index. Please re-enter a valid source pile index.");
          validSourcePile = false;
        }
      } else if (pileType.equals("destination")) {
        try {
          destinationPileNum = Integer.valueOf(sourceNum) - 1;
          validDestNum = true;
        } catch (NumberFormatException nfe) {
          append("Invalid index. Please re-enter a valid destination pile index.");
          validDestPile = false;
        }

      }
    }
  }


}

