#!/bin/bash

# UNO Game Run Script
echo "Starting UNO Game..."

# Check if classes are compiled
if [ ! -d "build/classes" ]; then
    echo "Classes not found. Building first..."
    ./build.sh
fi

# Run the game
echo "🎮 Launching UNO Game..."
java -cp build/classes Game
