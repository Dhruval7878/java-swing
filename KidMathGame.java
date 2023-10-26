import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KidMathGame extends JFrame implements ActionListener {
    private JLabel lbl;
    private JTextField ansField;
    private JButton button;
    private int count = -1;
    private int result = 0;
    private int[] nums = { 0, 0, 0 };
    private String operator = "";
    Random rndm = new Random();

    KidMathGame() {
        setTitle("Math Game");
        setSize(300, 150);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button = new JButton("Next");
        lbl = new JLabel("Start Game");
        ansField = new JTextField(5);
        add(lbl);
        add(ansField);
        add(button);
        ansField.setVisible(false);
        button.addActionListener(this);
    }

    public int[] generateAdditionQuestion(int[] nums) {
        nums[0] = rndm.nextInt(100);
        nums[1] = rndm.nextInt(nums[0]);
        nums[2] = nums[0] + nums[1];
        return nums;
    }

    public int[] generateSubtractionQuestion(int[] nums) {
        nums[0] = rndm.nextInt(100);
        nums[1] = rndm.nextInt(nums[0]);
        nums[2] = nums[0] - nums[1];
        return nums;
    }

    public int[] generateMultiplicationQuestion(int[] nums) {
        nums[0] = rndm.nextInt(10);
        nums[1] = rndm.nextInt(nums[0]);
        nums[2] = nums[0] * nums[1];
        return nums;
    }

    public int[] generateDivisionQuestion(int[] nums) {
        nums[0] = rndm.nextInt(10);
        nums[1] = rndm.nextInt(9) + 1;
        nums[2] = nums[0] / nums[1];
        return nums;
    }

    public void questions() {
        int operation = rndm.nextInt(4);
        switch (operation) {
            case 0:
                generateAdditionQuestion(nums);
                operator = "+";
                break;
            case 1:
                generateSubtractionQuestion(nums);
                operator = "-";
                break;
            case 2:
                generateMultiplicationQuestion(nums);
                operator = "*";
                break;
            case 3:
                if (nums[0] == 0) {
                    nums[1] = 1;
                }
                generateDivisionQuestion(nums);
                operator = "/";
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (count < 9) {
            ansField.setVisible(true);
            String userAnswerText = ansField.getText();
            if (!userAnswerText.isEmpty()) {
                int userAnswer = Integer.parseInt(userAnswerText);
                if (userAnswer == nums[2]) {
                    ++result;
                }
            }
            questions();
            ++count;
            lbl.setText(nums[0] + " " + operator + " " + nums[1]);
            ansField.setText("");
        } else if (count == 9) {
            ansField.setVisible(false);
            button.setVisible(false);
            lbl.setText("Total Questions 10 , total correct answers " + result + ".");
        }
    }

    public static void main(String[] args) {
        KidMathGame cal = new KidMathGame();
        cal.setVisible(true);
    }
}
