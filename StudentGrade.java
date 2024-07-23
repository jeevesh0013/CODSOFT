import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGrade {
    private JFrame frame;
    private JTextField[] markFields;
    private JTextField totalMarkField;
    private JTextField averageMarkField;
    private JTextField gradeMarkField;
    private JTextField totalMarksPossibleField;

    public void createUI() {
        frame = new JFrame("Marks Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null );

        JLabel[] subjectLabels = new JLabel[5];
        markFields = new JTextField[5];

        for (int i = 0; i < 5; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + " : ");
            subjectLabels[i].setBounds(30, 30 + (i * 30), 100, 25);
            frame.add(subjectLabels[i]);

            markFields[i] = new JTextField();
            markFields[i].setBounds(150, 30 + (i * 30), 100, 25);
            markFields[i].getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    calculateResult();
                }
                public void removeUpdate(DocumentEvent e) {
                    calculateResult();
                }
                public void insertUpdate(DocumentEvent e) {
                    calculateResult();
                }
            });
            frame.add(markFields[i]);
        }

        JLabel totalMarksPossibleLabel = new JLabel("Total Marks Possible: ");
        totalMarksPossibleLabel.setBounds(30, 200, 150, 25);
        frame.add(totalMarksPossibleLabel);

        totalMarksPossibleField = new JTextField();
        totalMarksPossibleField.setBounds(180, 200, 100, 25);
        totalMarksPossibleField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                calculateResult();
            }
            public void removeUpdate(DocumentEvent e) {
                calculateResult();
            }
            public void insertUpdate(DocumentEvent e) {
                calculateResult();
            }
        });
        frame.add(totalMarksPossibleField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(50, 240, 120, 30);
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });
        frame.add(calculateButton);

        JLabel totalMarkLabel = new JLabel("Total Marks: ");
        totalMarkLabel.setBounds(30, 280, 100, 25);
        frame.add(totalMarkLabel);

        totalMarkField = new JTextField();
        totalMarkField.setBounds(140, 280, 100, 25);
        totalMarkField.setEditable(false);
        frame.add(totalMarkField);

        JLabel averagePercentLabel = new JLabel("Average %: ");
        averagePercentLabel.setBounds(30, 320, 100, 25);
        frame.add(averagePercentLabel);

        averageMarkField = new JTextField();
        averageMarkField.setBounds(140, 320, 100, 25);
        averageMarkField.setEditable(false);
        frame.add(averageMarkField);

        JLabel gradeLabel = new JLabel("Grade: ");
        gradeLabel.setBounds(30, 360, 100, 25);
        frame.add(gradeLabel);

        gradeMarkField = new JTextField();
        gradeMarkField.setBounds(140, 360, 100, 25);
        gradeMarkField.setEditable(false);
        frame.add(gradeMarkField);

        frame.setVisible(true);
    }

    private void calculateResult() {
        try {
            int totalMarks = 0;
            for (JTextField markField : markFields) {
                int marks = Integer.parseInt(markField.getText());
                totalMarks += marks;
            }

            int totalMarksPossible = Integer.parseInt(totalMarksPossibleField.getText());
            double averageMark = ((double) totalMarks / totalMarksPossible) * 100;
            String grade = calculateGrade(averageMark);

            totalMarkField.setText(String.valueOf(totalMarks));
            averageMarkField.setText(String.format("%.2f", averageMark));
            gradeMarkField.setText(grade);
        } catch (NumberFormatException e) {
            totalMarkField.setText("");
            averageMarkField.setText("");
            gradeMarkField.setText("");
        }
    }

    private String calculateGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        StudentGrade calc = new StudentGrade();
        calc.createUI();
    }
}
