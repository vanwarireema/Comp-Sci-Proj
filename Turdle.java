import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.util.List;
import java.util.Iterator;

/**
 * The Turdle class represents 
 * a main class that initializes
 * a GUI for the homescreen
 * 
 * 
 * @author Reema Vanwari, Chang He
 * @author Period - 1
 * @version 5/27/24
 */

public class Turdle
    extends JFrame
{
    private JFrame  frame;
    private JPanel  pan;
    private JPanel  midPan;
    private JPanel  botPan;
    private JLabel  lab1;
    private JLabel  lab2;
    private JButton button1;
    private JButton button2;
    /**
     * constructor for Turtle
     */

    public Turdle()
    {
        frame = new JFrame("Turdle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);

        pan = new JPanel(new BorderLayout());
        midPan = new JPanel();
        botPan = new JPanel();

        ImageIcon turtle = new ImageIcon("turtle.png");
        button1 = new JButton("wordle", turtle);
        button1.setHorizontalTextPosition(SwingConstants.LEFT);
        button1.setFont(new Font("Serif", Font.PLAIN, 30));
        button1.setPreferredSize(new Dimension(300, 150));
        button1.setBackground(new Color(131, 182, 127, 255));
        button1.setOpaque(true);
        button1.setBorderPainted(false);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                DisplayWordle wordleWindow = new DisplayWordle();
            }
        });
        midPan.add(button1);
        
        ImageIcon redTurtle = new ImageIcon("redturtle.png");
        button2 = new JButton("absurdle", redTurtle);
        button2.setFont(new Font("Serif", Font.PLAIN, 30));
        button2.setPreferredSize(new Dimension(300, 150));
        button2.setBackground(new Color(236, 108, 105, 255));
        button2.setOpaque(true);
        button2.setBorderPainted(false);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                DisplayAbsurdle absurdleWindow = new DisplayAbsurdle();
            }
        });
        botPan.add(button2);

        midPan.setPreferredSize(new Dimension(400, 200));
        botPan.setPreferredSize(new Dimension(400, 230));
        pan.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        midPan.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // pan.add(topPan, BorderLayout.NORTH);
        frame.add(new MenuPane(), BorderLayout.NORTH);
        pan.add(midPan, BorderLayout.CENTER);
        pan.add(botPan, BorderLayout.SOUTH);
        frame.add(pan);
        frame.setVisible(true);

    }
/**
 * class for the menupane
 */
    public class MenuPane
        extends JPanel
    {
        /**
         * 
         * constructor for menu pane
         * 
         */
        public MenuPane()
        {
            setLayout(new GridLayout(0, 1));
            setBorder(BorderFactory.createEmptyBorder(80, 50, 20, 50));
            lab1 = new JLabel("Welcome to Turdle!!");
            lab1.setFont(new Font("Serif", Font.BOLD, 40));

            lab1.setHorizontalAlignment(JLabel.CENTER);
            add(lab1);

            lab2 = new JLabel("choose either wordle or absurdle");
            lab2.setFont(new Font("Serif", Font.PLAIN, 24));
            lab2.setHorizontalAlignment(JLabel.CENTER);
            add(lab2);
        }
    }

    /**
     * 
     * @param args main method
     */
    public static void main(String[] args)
    {
        Turdle window = new Turdle();
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}