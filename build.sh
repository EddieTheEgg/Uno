#!/bin/bash

# UNO Game Build Script
echo "Building UNO Game..."

# Create build directory
mkdir -p build/classes

# Compile Java files
echo "Compiling Java source files..."
javac -d build/classes src/*.java

if [ $? -eq 0 ]; then
    echo "✅ Compilation successful!"
    echo "Run the game with: ./run.sh"
else
    echo "❌ Compilation failed!"
    exit 1
fi
