The following README documents the changes made to class FreecellModel & Card.


FreecellModel
1) Changed all private fields and methods to protected so that the new FreecellModelMultiMove can have access to the fields and methods.

2) Move (case CASCADE) - changed a local variable movedCard so that it uses the inputted cardIndex from move() rather than the last card in the source pile.

3) Move (case CASCADE) - moved the condition that checks if the destinationPileNumber is less than the number of cascade piles to the beginning of the switch case.



Card
1) Changed Rank and Suit field to private as per feedback from grading of hw02
	⁃ Added getter methods for Rank & Suit
