# UNO Game

A Java implementation of the classic UNO card game with interactive terminal gameplay.

## Features

- 🎮 Interactive terminal-based gameplay
- 🤖 Play against computer opponents (bots)
- 🃏 Full UNO card set with all special cards
- 🎨 Color-coded cards with emojis for better visualization
- ⚡ Smart CPU opponents with strategic card play

## Game Rules

The game follows standard UNO rules:
- Each player starts with 7 cards
- Match color or number with the top card
- Special cards: +2, Skip, Reverse, Wild, Wild +4
- Say "UNO" when you have 1 card left
- First player to empty their hand wins!

## How to Run

### Prerequisites
- Java 8 or higher installed on your system

### Quick Start

1. **Build the game:**
   ```bash
   ./build.sh
   ```

2. **Run the game:**
   ```bash
   ./run.sh
   ```

## How to Play

1. When prompted, enter the number of computer players you want to play against
2. Enter your name
3. When choosing amount of bots, try to choose between 1-4 players otherwise gameplay will error when there are not enough cards to distirbute in the beginning
4. The game will display your cards and the current card in play
5. Type the exact name of the card you want to play (e.g., "Red 5", "Blue Skip", "Wild Red")
6. Type "Draw" to draw a card if you can't play
7. Type "Uno" when you have 2 cards left to declare UNO
8. Press Enter to continue between turns

## Card Types

- **Normal Cards**: Red, Green, Blue, Yellow (0-9)
- **+2 Cards**: Force next player to draw 2 cards
- **Skip Cards**: Skip the next player's turn
- **Reverse Cards**: Reverse the direction of play
- **Wild Cards**: Change the color to any color
- **Wild +4 Cards**: Change color and force next player to draw 4 cards

Enjoy playing UNO! 🎉

## What I Learned

### Java Programming Concepts
- **Object-Oriented Programming**: Created classes for `Card`, `Deck`, `Player`, and `Game`
- **Collections Framework**: Used `ArrayList` for managing cards and players
- **Exception Handling**: Implemented try-catch blocks for user input validation
- **Scanner Class**: Used for reading user input from the terminal
- **HashMap**: Created color-to-emoji mappings for better card visualization