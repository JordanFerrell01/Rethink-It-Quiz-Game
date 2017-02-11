import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RethinkIt extends JPanel implements ActionListener{
	int correctAns;
	Quiz quiz;
	int selected = -1;

	//Countdown
	JPanel cPanel = new JPanel();
	//questions
	JPanel qPanel = new JPanel();
	JTextArea qTextArea;
	//answers
	JPanel aPanel = new JPanel(new GridLayout(0, 2));
	JButton left = new JButton();
	JButton right = new JButton();
	JRadioButton[] responses;
	ButtonGroup group = new ButtonGroup();

	///TIMER STUFF
	JLabel timerLabel = new JLabel("Waiting...", SwingConstants.CENTER);
	int counter;
	Timer timer;
	int countDown = 25; 
	
	
	public class TimeClass implements ActionListener {
		int counter;
		
		public TimeClass(int counter) {
			this.counter = counter;
		}
		
		public void actionPerformed(ActionEvent tc) {
			counter--;
			
			if (counter >= 1){
				timerLabel.setText("Time left: " + counter);
			} else if (counter == 0){
				timer.stop();
				timerLabel.setText("Done!");
				Toolkit.getDefaultToolkit().beep();
				quiz.incrementer = quiz.numQs;
//				quiz.showSummary();				
			}
		}
	}
	
	public RethinkIt(String q, String[] options, int ans, Quiz quiz){
		this.quiz = quiz;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		correctAns = ans;
		//COUNTDOWN
		timerLabel.setText("Time left: " + countDown);		
		cPanel.add(timerLabel);		
		add(cPanel);
		
		TimeClass tc = new TimeClass(countDown);
		timer = new Timer(1000, tc);
		timer.start();
		
		//question

		//NEED TO USE JTextArea TO HAVE QUESTION TEXT WRAP SINCE JLABEL's DONT WRAP TEXT
		JTextArea qTextArea = new JTextArea(q);
		qTextArea.setWrapStyleWord(true);
		qTextArea.setLineWrap(true);	
		qTextArea.setOpaque(false);
		qTextArea.setEditable(false);
		qTextArea.setFocusable(false);
		qTextArea.setBackground(UIManager.getColor("Label.background"));
		qTextArea.setFont(UIManager.getFont("Label.font"));
		qTextArea.setBorder(UIManager.getBorder("Label.border"));	
		qTextArea.setSize(300, 200);
		qPanel.add(qTextArea);
		add(qPanel);
		
		//answer
//		responses = new JRadioButton[options.length];
//		for (int i = 0; i < options.length; i++) {
//			responses[i] = new JRadioButton(options[i]);
//			responses[i].addActionListener(this);
//			group.add(responses[i]);
//			aPanel.add(responses[i]);			
//		}
		
		left.setText(options[0]);
		left.setActionCommand("LEFT");
		left.addActionListener(this);	
		right.setText(options[1]);	
		right.setActionCommand("RIGHT");
		right.addActionListener(this);
		aPanel.add(left);
		aPanel.add(right);		
		add(aPanel);
	}
		

	
// **** ACTION LISTENERS **** //
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if ("LEFT".equals(e.getActionCommand())){
			selected = 0;
		} else {
			selected = 1;
		}
		if(selected == correctAns){
			quiz.incrementer++;
			quiz.hiScore++;
			selected = -1;
			quiz.next();
		} else {
			quiz.incrementer = 0;
			quiz.hiScore = 0;
			quiz.first();
		}				
		quiz.total++;	
	}
	
//	public void showResult(){
//		POP UP THE STUPID CORRECT ANSWER TEXT
//		String text = responses[selected].getText();
//		quiz.total++;
//		if(selected == correctAns){
//			JOptionPane.showMessageDialog(null, text + " is correct\n Well Done. ");
//		} else {
//			quiz.wrongs++;
//			JOptionPane.showMessageDialog(null, text + " is wrong\n Sorry. ");
//		}
//	}

}
