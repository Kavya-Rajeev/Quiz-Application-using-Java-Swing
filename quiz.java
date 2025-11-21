/******************************************
JAVA SWINGS PROJECT TO CREATE A SIMPLE QUIZ APPLICATION
Register no: 231BCADA10
Date:20-09-2025
******************************************/

import javax.swing.*;
import java.awt.*;

public class quiz {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("GK Quiz Application");	// creating the frame 
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	JPanel background = new JPanel() 
	{
    		Image bg = new ImageIcon("C:/Users/Rajeev O/OneDrive/Pictures/Picture1.png").getImage();	// putting a bg image for the app
   		protected void paintComponent(Graphics g) 
		{
        	super.paintComponent(g);
        	g.drawImage(bg,0,0,getWidth(),getHeight(),this);
    		}
	};

	background.setLayout(null);
	frame.setContentPane(background);

        JLabel questionLabel = new JLabel("",SwingConstants.CENTER);	// label to show each question,empty for now 
	questionLabel.setFont(new Font("Arial",Font.BOLD,16));
	questionLabel.setForeground(Color.BLACK);
	questionLabel.setBounds(50,20,500,40);
        background.add(questionLabel);

        JRadioButton option1 = new JRadioButton();	// making radio button for 4 optional answers,empty for now
        JRadioButton option2 = new JRadioButton();
        JRadioButton option3 = new JRadioButton();
        JRadioButton option4 = new JRadioButton();

	option1.setBounds(150,80,300,30);
	option2.setBounds(150,110,300,30);
	option3.setBounds(150,140,300,30);
	option4.setBounds(150,170,300,30);

	option1.setOpaque(false);
	option2.setOpaque(false);
	option3.setOpaque(false);
	option4.setOpaque(false);
		
	option1.setForeground(Color.BLACK);
	option2.setForeground(Color.BLACK);
	option3.setForeground(Color.BLACK);
	option4.setForeground(Color.BLACK);

	ButtonGroup optionsGroup = new ButtonGroup();	// grouping the buttons,allows selecting 1 at a time
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

	
	background.add(option1);
	background.add(option2);
	background.add(option3);
	background.add(option4);


        JButton nextButton = new JButton("Next");	// use MyButton for 'next' in green colour and 'submit' in orange
	nextButton.setBounds(150, 260, 100, 30);
	nextButton.setBackground(Color.GREEN);
        nextButton.setForeground(Color.BLACK);

        JButton submitButton = new JButton("Submit");
	submitButton.setBounds(300, 260, 100, 30);
	submitButton.setBackground(Color.ORANGE);
        submitButton.setForeground(Color.BLACK);

	background.add(nextButton);
	background.add(submitButton);

	
        String[] questions = {
            "Q1: Which is the capital of India?",
            "Q2: Java is developed by?",
            "Q3: Which company owns Android?",
            "Q4: Which is the smallest planet in our solar system?",
            "Q5: Which keyword is used to inherit a class in Java?"
        };

        String[][] options = {
            {"Delhi", "Mumbai", "Kolkata", "Chennai"},
            {"Microsoft", "Sun Microsystems", "Google", "Oracle"},
            {"Apple", "Google", "Microsoft", "Samsung"},
            {"Mercury", "Mars", "Venus", "Earth"},
            {"super", "this", "extends", "implements"}
        };

        String[] answers = {"Delhi", "Sun Microsystems", "Google", "Mercury", "extends"};

        // trackers to track question and score
        final int[] currentQuestion = {0};
        final int[] score = {0};

        // to load question method
        Runnable loadQuestion = () -> {
            optionsGroup.clearSelection();
            if (currentQuestion[0] < questions.length) 
	    {
                questionLabel.setText(questions[currentQuestion[0]]);
                option1.setText(options[currentQuestion[0]][0]);
                option2.setText(options[currentQuestion[0]][1]);
                option3.setText(options[currentQuestion[0]][2]);
                option4.setText(options[currentQuestion[0]][3]);
            }
        };

        // to check answer method
        Runnable checkAnswer = () -> {
            String selected = null;
            if (option1.isSelected()) 
		selected = option1.getText();
            else if (option2.isSelected()) 
		selected = option2.getText();
            else if (option3.isSelected()) 
		selected = option3.getText();
            else if (option4.isSelected()) 
		selected = option4.getText();

            if (selected != null && selected.equals(answers[currentQuestion[0]])) 	// to increase score when they select correct option
		{    score[0]++;
        	}
        };

        nextButton.addActionListener(e -> {

    if (currentQuestion[0] < questions.length - 1) 
	{ 
	checkAnswer.run(); // check current answer
        currentQuestion[0]++; // move to next question
        loadQuestion.run();
     	}
	else {
        JOptionPane.showMessageDialog(frame, "You have reached the last question. Click Submit to see your score!");
        nextButton.setEnabled(false); // disable next
	submitButton.setEnabled(true);	//enable submit
	}
    
});

        // submit button action
	submitButton.addActionListener(e -> {
    	checkAnswer.run(); // check last answer

    	String message = "Quiz Over! Your Score: " + score[0] + "/" + questions.length + "\nDo you want to restart the quiz?";
	
    	int response = JOptionPane.showConfirmDialog(
            frame,
            message,
            "Quiz Finished",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
    	);

    	if (response == JOptionPane.YES_OPTION) 
	{
        currentQuestion[0] = 0;
        score[0] = 0;
        nextButton.setEnabled(true);
	submitButton.setEnabled(false);
        loadQuestion.run();
    	} 
	else if (response == JOptionPane.NO_OPTION) 
	{
        frame.dispose();	// closes the window
    	}
	});

       	submitButton.setEnabled(false);
	loadQuestion.run();	// loading the first question
        frame.setVisible(true); // To make the frame visible
    }
}

