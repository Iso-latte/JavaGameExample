# Dodge A Block

Dodge A Block is an engaging Java-based arcade-style game where the player controls a character and attempts to dodge obstacles while collecting coins to earn points. The game features a dynamic camera, collision mechanics, and gravity simulation for realistic movement.

## Features

- **Dynamic Gameplay:** Control the player using space key to avoid walls and collect coins.
- **Gravity and Collision Mechanics:** Realistic physics to enhance gameplay experience.
- **Points System:** Earn points by collecting coins scattered throughout the game.
- **Reset Mechanism:** Automatic reset if the player falls out of bounds.
- **Simple Visual Design:** A clean and straightforward user interface.

## Demo

Watch the gameplay demo on [YouTube](https://www.youtube.com/watch?v=M0hDdyFHycE).

## How to Run

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/dodge-a-block.git
   ```
2. Navigate to the project directory:
   ```bash
   cd dodge-a-block
   ```
3. Compile the project:
   ```bash
   javac *.java
   ```
4. Run the main application:
   ```bash
   java App
   ```

## Code Highlights

### Player Object Example

```java
public class Player {
    GamePanel panel;
    int x, y, width, height;
    double xspeed, yspeed;
    Rectangle hitBox;
    int points = 0;

    public Player(int x, int y, GamePanel panel) {
        this.panel = panel;
        this.x = x;
        this.y = y;
        width = 75;
        height = 75;
        hitBox = new Rectangle(x, y, width, height);
    }

    public void set() {
        // Movement and collision logic
    }

    public void drawRight(Graphics2D gtd) {
        gtd.setColor(Color.BLACK);
        gtd.fillRect(x, y, width, height);
    }
}
```

## Technologies Used

- **Java:** Core programming language.
- **Swing and AWT:** For GUI and rendering.
- **YouTube:** Hosting the demo video.

## Future Enhancements

- Add sound effects and background music.
- Add scoreboard
- Random Terrain Gen
- Introduce new levels with increasing difficulty.
- Implement additional obstacles and power-ups.


---

Happy Gaming!

