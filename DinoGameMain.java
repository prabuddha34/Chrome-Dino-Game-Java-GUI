import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DinoGameMain extends JFrame implements ActionListener, KeyListener {

    class Block {
        int x, y, width, height;
        Image img;

        Block(int x, int y, int width, int height, Image img) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }
    }

    JLabel scoreLabel = new JLabel();
    int score = 0;

    Image dinosaurImg, dinosaurDeadImg, dinosaurJumpImg, cactus1Img;

    int dinosaurWidth = 88, dinosaurHeight = 94;
    int dinosaurX = 50, dinosaurY = 250 - dinosaurHeight;
    Block dinosaur;

    int cactus1Width = 34, cactusHeight = 70;
    int cactusY = 250 - cactusHeight; // ✅ Fixed to match window height
    Block cactus;

    int velocityX = -12;
    int velocityY = 0;
    int gravity = 1;

    boolean gameOver = false;

    Timer gameLoop, placeCactusTimer;

    GamePanel gamePanel = new GamePanel();

    public DinoGameMain() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Dino Game");
        this.setSize(650, 350);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Score label setup
        scoreLabel.setText("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.add(scoreLabel, BorderLayout.CENTER);
        this.add(scorePanel, BorderLayout.NORTH);

        // Load images
        dinosaurImg = new ImageIcon(getClass().getResource("dino-run.gif")).getImage();
        dinosaurDeadImg = new ImageIcon(getClass().getResource("dino-dead.png")).getImage();
        dinosaurJumpImg = new ImageIcon(getClass().getResource("dino-jump.png")).getImage();
        cactus1Img = new ImageIcon(getClass().getResource("cactus1.png")).getImage();

        // Init game objects
        dinosaur = new Block(dinosaurX, dinosaurY, dinosaurWidth, dinosaurHeight, dinosaurImg);
        cactus = new Block(700, cactusY, cactus1Width, cactusHeight, cactus1Img);

        // Game panel setup
        gamePanel.setBackground(Color.lightGray);
        this.add(gamePanel, BorderLayout.CENTER);
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(this);

        // Timers
        gameLoop = new Timer(1000 / 60, this); // ~60 FPS
        gameLoop.start();

        placeCactusTimer = new Timer(1500, e -> placeCactus());
        placeCactusTimer.start();

        this.setVisible(true);
    }

    class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw(g);
        }
    }

    void draw(Graphics g) {
        g.drawImage(dinosaur.img, dinosaur.x, dinosaur.y, dinosaur.width, dinosaur.height, null);
        g.drawImage(cactus.img, cactus.x, cactus.y, cactus.width, cactus.height, null);

        g.setColor(Color.black);
        g.setFont(new Font("Courier", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + score, 10, 35);
        } else {
            g.drawString(String.valueOf(score), 10, 35);
        }
    }

    void move() {
        velocityY += gravity;
        dinosaur.y += velocityY;

        if (dinosaur.y > dinosaurY) {
            dinosaur.y = dinosaurY;
            velocityY = 0;
            dinosaur.img = dinosaurImg;
        }

        cactus.x += velocityX;

        if (collision(dinosaur, cactus)) {
            gameOver = true;
            dinosaur.img = dinosaurDeadImg;
        }

        if (!gameOver) score++;
        scoreLabel.setText("Score: " + score);
    }

    void placeCactus() {
        if (gameOver) return;
        cactus = new Block(700, cactusY, cactus1Width, cactusHeight, cactus1Img);
    }

    boolean collision(Block a, Block b) {
        return a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        gamePanel.repaint();
        if (gameOver) {
            gameLoop.stop();
            placeCactusTimer.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && dinosaur.y == dinosaurY && !gameOver) {
            velocityY = -17;
            dinosaur.img = dinosaurJumpImg;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && gameOver) {
            gameOver = false;
            score = 0;
            dinosaur.img = dinosaurImg;
            dinosaur.y = dinosaurY;
            cactus.x = 700;
            gameLoop.start();
            placeCactusTimer.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    public static void main(String[] args) {
        new DinoGameMain();
    }
}
