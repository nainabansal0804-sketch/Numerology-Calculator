import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class NumerologyGUI implements ActionListener {

    JFrame frame;
    JPanel mainPanel, topPanel, centerPanel, bottomPanel;
    JLabel titleLabel, dobLabel;
    JTextField dobField;
    JButton calculateBtn, clearBtn;
    JTextArea resultArea;

    public NumerologyGUI() {
        frame = new JFrame("Numerology Calculator");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Panel
        topPanel = new JPanel();
        topPanel.setBackground(new Color(45, 45, 90));
        topPanel.setPreferredSize(new Dimension(500, 80));
        titleLabel = new JLabel("🔢 Numerology Calculator");
        titleLabel.setFont(new Font("stencil", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);

        // Center Panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        centerPanel.setBackground(new Color(230, 230, 255));

        dobLabel = new JLabel("Enter Your Date Of Birth (DDMMYYYY):");
        dobLabel.setFont(new Font("Kristen ITC", Font.BOLD, 14));

        dobField = new JTextField();
        dobField.setFont(new Font("Kristen ITC", Font.PLAIN, 16));
        dobField.setBorder(new LineBorder(Color.BLUE, 2));

        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        inputPanel.setBackground(new Color(230, 230, 255));
        inputPanel.add(dobLabel);
        inputPanel.add(dobField);

        resultArea = new JTextArea(5, 20);
        resultArea.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setBorder(new TitledBorder("Result--Your Traits"));
        resultArea.setBackground(new Color(245, 245, 245));

        centerPanel.add(inputPanel);
        centerPanel.add(new JScrollPane(resultArea));

        // Bottom Panel
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(180, 180, 255));
        bottomPanel.setPreferredSize(new Dimension(500, 80));

        calculateBtn = new JButton("Calculate");
        calculateBtn.setBackground(new Color(60, 180, 75));
        calculateBtn.setForeground(Color.WHITE);
        calculateBtn.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));

        clearBtn = new JButton("Clear");
        clearBtn.setBackground(new Color(220, 50, 50));
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));

        calculateBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        bottomPanel.add(calculateBtn);
        bottomPanel.add(clearBtn);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Reduce to single digit
    public int reduceToSingleDigit(int num) {
        while (num > 9) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }

    public String getTraits(int number) {
        switch (number) {
            case 1: return "You are Independent, Innovative, Assertiveness, Risk taker, Your position in society is good, Couragious, Inspire others.";
            case 2: return "Harmony, Sensitivity, Cooperation, Intuition, Diplomacy, Good in partnership, Good in mediating, listening, and nurturing.";
            case 3: return "You are The Creative Communicator, charismatic, energetic, and magnetic.";
            case 4: return "Practicality, stability, Reliability, Hard work.";
            case 5: return "You are Overconfident, impatient, restless, great communicators, adaptive to change.";
            case 6: return "You are truth worthy, Family supportive, Service, love, name in the society, ready to help others, bring comfort and healing to others, fun-loving energy";
            case 7: return "You are confused Analysis, deep thinker, truth speaker";
            case 8: return "You are Egotistical, materialistic, manipulative, greedy but business savvy and Ambitious.";
            case 9: return "You are Compassionate, generous, artistic, humanitarian.";
            default: return "Invalid input.";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateBtn) {
            String dob = dobField.getText();
            int sum = 0;

            for (int i = 0; i < dob.length(); i++) {
                char ch = dob.charAt(i);
                if (Character.isDigit(ch)) {
                    sum += ch - '0';
                }
            }

            if (sum == 0) {
                resultArea.setText("Please enter a valid Date of Birth.");
                return;
            }

            int lifePath = reduceToSingleDigit(sum);
            resultArea.setText(
                "Life Path Number: " + lifePath + "\n\n" +
                "Characteristics:\n" + getTraits(lifePath)
            );
        }

        if (e.getSource() == clearBtn) {
            dobField.setText("");
            resultArea.setText("");
        }
    }

    public static void main(String[] args) {
        new NumerologyGUI();
    }
}
