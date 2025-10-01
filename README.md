# Tokimon Memory Game

## Overview
Tokimon Memory Game is a fun and interactive JavaFX-based memory matching game featuring Tokimon characters. The game challenges players to test and improve their memory by matching pairs of Tokimon cards.  

This project was developed as part of the CMPT213 course, demonstrating Java programming, GUI design, and event-driven game logic.

---

## Features
- **Interactive GUI** using JavaFX
- **Score tracking** to monitor player performance
- **Tokimon characters** with unique images and attributes
- **Smooth animations** for card flipping

---

## How to Play
1. Launch the game using the JavaFX client.
2. Click on a card to flip it over and reveal a Tokimon.
3. Flip another card to find its matching pair.
4. If the two cards match, they remain visible; otherwise, they flip back.
5. Continue until all pairs are matched.
6. The game tracks the number of moves taken to reveal all pairs.

---

## Installation
1. Clone the repository:  
   ```bash
   https://github.com/AyushArora10hg/TokimonMemoryGame---CMPT213.git
2. Open the project in your IDE (IntelliJ, Eclipse, etc.).
3. Build the server with Maven:
   ```bash
   mvn clean install
4. Run the Spring Boot server:
   ```bash
   mvn spring-boot:run
5. Build and run the JavaFX client:
   ```bash
   mvn javafx:run

---

## Dependencies
This project uses the following libraries and tools:

- **Java 22 or higher** – Core language and runtime.
- **JavaFX 22** – GUI framework.
  - `javafx-controls`
- **JSON-Simple 1.1.1** – For reading and parsing JSON files.
- **JUnit 5** – For running unit tests (optional).

Dependencies are managed via Maven (`pom.xml`).

---

## License

This project is a **class project** developed for the CMPT213 course (Summer 2024) at Simon Fraser University.  

- The project is intended for **educational purposes only**.  
- It **cannot be used for commercial purposes**.

---

## Authors

- **Ayush Arora** – CMPT 213, Summer 2024
