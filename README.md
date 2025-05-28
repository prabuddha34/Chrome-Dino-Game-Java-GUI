
  ##  🦖 DINO GAME - JAVA SWING 

📦 FILE: DinoGameMain.java
💻 REQUIREMENTS: JDK 8 or above
🖼️ IMAGE ASSETS NEEDED:
    - dino-run.gif
    - dino-dead.png
    - dino-jump.png
    - cactus1.png

Place these image files in the same directory as the `.java` file or in your resources folder.

---------------------------------------------------
📋 HOW TO RUN
---------------------------------------------------

1. Compile the code:
    javac DinoGameMain.java

2. Run the game:
    java DinoGameMain

NOTE: Make sure your working directory contains the image assets
      or that the resources path is correctly set in your project.

---------------------------------------------------
🎮 GAME CONTROLS
---------------------------------------------------

[SPACEBAR] - Jump
[SPACEBAR] (after Game Over) - Restart

---------------------------------------------------
🕹️ GAME MECHANICS
---------------------------------------------------

- Dinosaur runs automatically.
- Jump over cacti to avoid collisions.
- Score increases over time.
- One hit = Game Over.
- Press SPACE to restart after crashing.

---------------------------------------------------
📦 STRUCTURE & LOGIC
---------------------------------------------------

- Main class: DinoGameMain (extends JFrame)
- Inner class: Block (for Dino and Cactus objects)
- Timers:
    - gameLoop (~60 FPS) handles movement, collision, and rendering
    - placeCactusTimer periodically respawns cactus

- Images are drawn using:
    Graphics.drawImage()

- Game state is managed with a simple flag: gameOver

---------------------------------------------------
🛠️ FEATURES TO IMPROVE
---------------------------------------------------

- Add multiple types of obstacles
- Add ground and background scenery
- Add sound effects
- Improve collision accuracy
- Add start screen and pause functionality

---------------------------------------------------
🧑‍💻 AUTHOR
---------------------------------------------------

Built with ❤️ using Java Swing
Feel free to fork and expand!

===================================================
