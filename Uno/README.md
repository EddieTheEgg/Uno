# UNO Game

A Java implementation of the classic UNO card game with interactive terminal gameplay.

## Features

- 🎮 Interactive terminal-based gameplay
- 🤖 Play against computer opponents (bots)
- 🃏 Full UNO card set with all special cards
- 🎨 Color-coded cards with emojis for better visualization
- ⚡ Smart AI opponents with strategic card play

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

### Manual Compilation

If you prefer to compile manually:

```bash
# Create build directory
mkdir -p build/classes

# Compile the Java files
javac -d build/classes src/main/java/com/uno/*.java

# Run the game
java -cp build/classes com.uno.Game
```

## How to Play

1. When prompted, enter the number of computer players you want to play against
2. Enter your name
3. The game will display your cards and the current card in play
4. Type the exact name of the card you want to play (e.g., "Red 5", "Blue Skip", "Wild Red")
5. Type "Draw" to draw a card if you can't play
6. Type "Uno" when you have 2 cards left to declare UNO
7. Press Enter to continue between turns

## Project Structure

```
Uno/
├── src/main/java/com/uno/    # Source code
│   ├── Game.java             # Main game logic
│   ├── Card.java             # Card class
│   ├── Deck.java             # Deck management
│   └── Player.java           # Player class
├── build/                    # Compiled classes (created after build)
├── build.sh                  # Build script
├── run.sh                    # Run script
└── README.md                 # This file
```

## Card Types

- **Normal Cards**: Red, Green, Blue, Yellow (0-9)
- **+2 Cards**: Force next player to draw 2 cards
- **Skip Cards**: Skip the next player's turn
- **Reverse Cards**: Reverse the direction of play
- **Wild Cards**: Change the color to any color
- **Wild +4 Cards**: Change color and force next player to draw 4 cards

Enjoy playing UNO! 🎉
