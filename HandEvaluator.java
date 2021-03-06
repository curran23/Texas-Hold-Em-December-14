import java.util.*;

public class HandEvaluator {
	
	ArrayList<Card> playerHand = new ArrayList<Card>(2);
	
	public HandEvaluator() {
		
	}

	
	public String isPair(ArrayList<Card> aHand, ArrayList<Card> aTable) {
		
		String returnString = "";
		String[] cardRanks = new String[2];
		
		// Build an array with the values of the player hand
		for (int i=0; i < 2; i ++) {
			cardRanks[i] = aHand.get(i).getRank();
		}
		
		// If you have a pocket pair
		if (aHand.get(0).rank == aHand.get(1).rank) {
			returnString = "Pair";
		}
		
		
		// For all values on the table
		for (int j = 0; j < aTable.size() - 1; j++) {
			// Take that value and compare to all other values on the table
			for (int k = j+1; k < aTable.size(); k++) {
				if (aTable.get(j).rank == aTable.get(k).rank) {
					returnString = "Table Pair" + "\n";
					}
			}
		}
		
		// For both values in the card ranks array
		for (int i = 0; i < cardRanks.length; i++) {
			// Compare both values to all values on the table (looking for pairs only)
			for (int l = 0; l < aTable.size(); l++) {
				if (aTable.get(l).getRank() == cardRanks[i]) {
					returnString = "Pair";
				}
			}
		}
		
		return returnString + "\n";
	}
	
	public String isThreeOfAKind(ArrayList<Card> aHand, ArrayList<Card> aTable) {
		
		String returnString = "";
		String[] cardRanks = new String[2];
		String[] tableRanks = new String[5];
		
		
		for (int i=0; i < 2; i ++) {
			cardRanks[i] = aHand.get(i).getRank();
		}
		
		for (int i=0; i < 5; i ++) {
			tableRanks[i] = aTable.get(i).getRank();
		}
		
		// If you have a pocket pair, make one pass through the table to see 
		// if any other cards will give you three of a kind
		if (aHand.get(0).rank == aHand.get(1).rank) {
			for (int i = 0; i < aTable.size(); i++) {
				if (aTable.get(i).rank == aHand.get(0).rank) {
					returnString = "Three of a Kind, pocket pair";
				}
				else {
					// Check for a pair on the board
					for (int j = 0; j < aTable.size() - 1; j++) {
						// Loop again right away to compare the first card with all the cards on the table
						// This will find all pairs, regardless of the cards' location on the table
						for (int k = j+1; k < aTable.size(); k++) {
							if (aTable.get(j).rank == aTable.get(k).rank) {
								// If a pair exists, check to see if either player cards
								// create three of a kind
								if (cardRanks[0] == aTable.get(j).rank) {
									returnString = "Three of a Kind, table pair";
								}
								else if (cardRanks[1] == aTable.get(j).rank) {
									returnString = "Three of a Kind, table pair";
								}
								// If neither of the player's cards match the existing pair, there could
								// be a Three of a Kind on the table by itself. Check the remaining cards
								// for a 3rd matching card if we find a pair earlier in the table
								else {
									for (int l = k + 1; l < aTable.size(); l ++) {
										if (aTable.get(j).rank == aTable.get(l).rank) {
											returnString = "Three of a Kind, all on the table";
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// If not a pocket pair, 3 of a kind is still possible
		else {	
			// Check for a pair on the board
			for (int j = 0; j < aTable.size() - 1; j++) {
				// Loop again right away to compare the first card with all the cards on the table
				// This will find all pairs, regardless of the cards' location on the table
				for (int k = j+1; k < aTable.size(); k++) {
					if (aTable.get(j).rank == aTable.get(k).rank) {
						// If a pair exists, check to see if either player cards
						// create three of a kind
						if (cardRanks[0] == aTable.get(j).rank) {
							returnString = "Three of a Kind, table pair";
						}
						else if (cardRanks[1] == aTable.get(j).rank) {
							returnString = "Three of a Kind, table pair";
						}
						// If neither of the player's cards match the existing pair, there could
						// be a Three of a Kind on the table by itself. Check the remaining cards
						// for a 3rd matching card if we find a pair earlier in the table
						else {
							for (int l = k + 1; l < aTable.size(); l ++) {
								if (aTable.get(j).rank == aTable.get(l).rank) {
									returnString = "Three of a Kind, all on the table";
								}
							}
						}
					}
				}
			}
		}
		return returnString + "\n";
	}
	
	public String isFourOfAKind(ArrayList<Card> aHand, ArrayList<Card> aTable) {
		String returnString = "";
		
		String[] cardRanks = new String[2];
		String[] tableRanks = new String[5];
		
		
		for (int i=0; i < 2; i ++) {
			cardRanks[i] = aHand.get(i).getRank();
		}
		
		for (int i=0; i < 5; i++) {
			tableRanks[i] = aTable.get(i).getRank();
		}
		
		// If you have a pocket pair
		if (cardRanks[0] == cardRanks[1]) {
			// Loop through the cards on the table to find any table pairs
			for (int i = 0; i < aTable.size() - 1; i++) {
				for (int j = i + 1; j < aTable.size(); j++) {
					// Confirm a pair on the table
					if (tableRanks[i] == tableRanks[j]) {
						// Make sure table pair matches the players pair
						if (tableRanks[j] == cardRanks[0]) {
							returnString = "Four of a kind, pocket pair";
						}
					}
				}
			}
		}
		
		// No pocket pair, either have three on the board and match one player card or
		// four of a kind on the board is possible
		else {
			// Use a triple for loop to evaluate all possible combinations of three cards 
			// on the table
			for (int i = 0; i < aTable.size(); i++) {
				for (int j = i + 1; j < aTable.size(); j++) {
					for (int k = j + 1; k < aTable.size(); k++) {
						// If there is a pair
						if (tableRanks[j] == tableRanks[i]) {
							// If you have three of a kind
							if (tableRanks[k] == tableRanks[j]) {
								if (tableRanks[k] == cardRanks[0] || tableRanks[k] == cardRanks[1]) {
									returnString = "Four of a kind, three on the board";
								}
								
								// If the cards on the board don't match either of the players cards
								else {
									// Loop to see if there is four of a kind on the board
									for (int l = k + 1; l < aTable.size(); l++) {
										if (tableRanks[l] == tableRanks[k]) {
											returnString = "Four of a kind, all on the board";
										}
									}
								}
							}
						}
					}
				}
			}
			
		}
		
		return returnString;
		
	}
	
	public String isTwoPair(ArrayList<Card> aHand, ArrayList<Card> aTable) {
		
		String returnString = "";
		// Variable will be set to true if a pair is on the board
		boolean boardPair = false;
		String[] cardRanks = new String[2];
		String[] tableRanks = new String[5];
		
		
		for (int i=0; i < 2; i ++) {
			cardRanks[i] = aHand.get(i).getRank();
		}
		
		for (int i=0; i < 5; i++) {
			tableRanks[i] = aTable.get(i).getRank();
		}
		
		// Check for a pocket pair
		if (cardRanks[0] == cardRanks[1]) {
			// Check table for a pair that is not the same as the pocket pair, i.e. not four of a kind
			for (int i = 0; i < aTable.size() -1; i++) {
				for (int j = i + 1; j < aTable.size(); j++) {
					if (tableRanks[i] == tableRanks[j]) {
						if (tableRanks[i] != cardRanks[0]) {
							returnString = "Two pair, pocket pair";
						}
					}
				}
			}
		}
		
		// Not a pocket pair
		else {
			// First find a pair on the table, because there is no point in passing through the table
			// looking for a pair to match one of the players cards if there isn't a pair on the board
			for (int i = 0; i < aTable.size() -1; i++) {
				for (int j = i + 1; j < aTable.size(); j++) {
					if (tableRanks[i] == tableRanks[j]) {
						// Boolean used to reduce run time by not having a triple for loop
						boardPair = true;
					}
				}
			}
			
			// Loop through the table one time looking for a single pair between the table and player's hand
			for (int i = 0; i < aTable.size(); i++) {
				if (tableRanks[i] == cardRanks[0] || tableRanks[i] == cardRanks[1]) {
					// If you find a pair, check to see if there is a pair on the board
					if (boardPair) {
						returnString = "Two Pair, pair on the board";
					}
				}
			}
			
		}
		return returnString;
	}
	
	/**public String isFullHouse(ArrayList<Card> aHand, ArrayList<Card> aTable) {
		
		String returnString = "";
		// Variable will be set to true if a pair is on the board
		// Variable will be set to true if three of a kind is on the board
		boolean pocketPairAndBoardThreeOfAKind = false;
		String[] cardRanks = new String[2];
		String[] tableRanks = new String[5];
		
		for (int i=0; i < 2; i ++) {
			cardRanks[i] = aHand.get(i).getRank();
		}
		
		for (int i=0; i < 5; i++) {
			tableRanks[i] = aTable.get(i).getRank();
		}
		
		// Check for a pocket pair
		if (cardRanks[0] == cardRanks[1]) {
			// If there is a pocket pair, I need to find three of a kind on the board
			// or a card to make three of a kind with the players pocket pair and a separate pair on the board
			for (int a = 0; a < aTable.size(); a++) {
				// If the pocket pair has a matching card on the table
				if (tableRanks[a] == cardRanks[0]) {
					pocketPairAndBoardThreeOfAKind = true;
				}
				
				for (int b = 0; b < aTable.size() - 1; b++) {
					for (int c = 0; c < aTable.size(); c++) {
						// If there is a pair on the board that is not the same as rank as the 
						// three of a kind
						if (tableRanks[b] == tableRanks[c] && tableRanks[b] != cardRanks[0]) {
							if (pocketPairAndBoardThreeOfAKind) {
								returnString = "Full house, three of a kind with pocket pair helping";
							}
						}
					}
				}
			}
			
			for (int i = 0; i < aTable.size() - 2; i++) {
				for (int j = i + 1; j < aTable.size() - 1; j++) {
					for (int k = j + 1; k < aTable.size(); k++) {
						if (tableRanks[i] == tableRanks[j]) {
							// If there is a three of a kind on the board
							if (tableRanks[j] == tableRanks[k]) {
								returnString = "Full house, pocket pair";
							}
						}
					}
				}
			}
		}
		// No pocket pair means I need a three of a kind on the board and a table card to match one of the pocket cards
		// Also have to consider the situation where the entire full house is on the board and situation where both players cards
		// are used, one for a pair and one for three of a kind
		else {
			// Loop through one time to see if any of the table cards match a pocket card
			for (int i = 0; i < aTable.size(); i++) {
				// Using the ^ operator which is the XOR operator because I do not want the
				// scenario where both of the players cards have matching cards on the table
				if (cardRanks[0] == tableRanks[i] ^ cardRanks[1] == tableRanks[i]) {
					// There is a pair between the table and the player's cards
					for (int l = 0; l < aTable.size() - 2; l++) {
						for (int j = l + 1; j < aTable.size() - 1; j++) {
							for (int k = j + 1; k < aTable.size(); k++) {
								if (tableRanks[l] == tableRanks[j]) {
									// If there is a three of a kind on the board
									if (tableRanks[j] == tableRanks[k]) {
										returnString = "Full house, three of a kind on the board";
									}
								}
							}
						}
					}
				}
				// Check to see if both player cards will be used to make the full house
				else if (cardRanks[0] == tableRanks[i]) {
					for (int d = 0; d < aTable.size(); d++) {
						// If the player's second card matches a table card and that table card doesn't match the players first card
						if (cardRanks[1] == tableRanks[d] && tableRanks[d] != cardRanks[0]) {
							for (int e = d + 1; e < aTable.size(); e++) {
								
							}
						}
					}
				}
			}
		} 
		return returnString;
		
	} **/
	
	public String isFlush(ArrayList<Card> aHand, ArrayList<Card> aTable) {
		
		/**
		 * In order to have a flush, you must have five cards of the same suit. At this moment, all flushes will be treated
		 * equal so there will be no functionality for determining which suit is the highest.
		 */
		
		String returnString = "";
		
		// Variables used to count number of cards in each suit
		int heartsCount = 0;
		int diamondsCount = 0;
		int clubsCount = 0;
		int spadesCount = 0;
		
		for (int i=0; i < 2; i ++) {
			
			if (aHand.get(i).getSuit() == "Hearts") {
				heartsCount += 1;
			}
			
			if (aHand.get(i).getSuit() == "Diamonds") {
				diamondsCount += 1;
			}
			
			if (aHand.get(i).getSuit() == "Clubs") {
				clubsCount += 1;
			}
			
			if (aHand.get(i).getSuit() == "Spades") {
				spadesCount += 1;
			}
		}
		
		for (int i=0; i < 5; i++) {
			
			if (aTable.get(i).getSuit() == "Hearts") {
				heartsCount += 1;
			}
			
			if (aTable.get(i).getSuit() == "Diamonds") {
				diamondsCount += 1;
			}
			
			if (aTable.get(i).getSuit() == "Clubs") {
				clubsCount += 1;
			}
			
			if (aTable.get(i).getSuit() == "Spades") {
				spadesCount += 1;
			}
		}
		
		// Determine which suit gives us a flush, if any
		
		if (heartsCount >= 5) {
			returnString = "Flush of Hearts";
		}
		
		if (diamondsCount >= 5) {
			returnString = "Flush of Diamonds";
		}
		
		if (clubsCount >= 5) {
			returnString = "Flush of Clubs";
		}
		
		if (spadesCount >= 5) {
			returnString = "Flush of Spades";
		}
		
		return returnString;
	}
	
	/**public String isStraight(ArrayList<Card> aHand, ArrayList<Card> aTable) {
	
		
		 * A straight consists of five cards in numerical order. To check the table and hand for a straight, I will consider three
		 * possibilities. 1) Both of the players card help in making the straight. 2) One of the players cards helps make the straight.
		 * 3) There is a straight on the board. To check for a straight, I will make one large array with all the cards on the table
		 * and in the players hand and check for a straight in that array.
		 

		String returnString = "";
		String[] ranks = new String[7];
		// Variable will be used to count the number of cards in the straight
		int straightCounter = 0;
		ArrayList<Integer> values = new ArrayList<Integer>();

		for (int i=0; i < 2; i ++) {
			ranks[i] = aHand.get(i).getRank();
		}

		for (int i=2; i < 7; i++) {
			ranks[i] = aTable.get(i).getRank();
		}

		for (int i = 0; i < 7; i++) {
			values.add(Integer.parseInt(ranks[1]));
		}

		Collections.sort(values);

		for (int i = 0; i < values.size(); i++) {
			if (values.get(i) == (values.get(i + 1))) {
				straightCounter += 1;
			}
		}

		if (straightCounter >= 5) {
			returnString = "Straight";
		}
		
		return returnString;
	
	}	
	* Need a way to treat the face cards. Need to assign them values
	*/
		
		
}
