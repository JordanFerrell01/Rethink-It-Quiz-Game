import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Quiz extends JFrame{
	JPanel p = new JPanel();
	CardLayout cards = new CardLayout();
	int numQs;
	int wrongs = 0;
	int total = 0;
	ArrayList index = new ArrayList();
	int incrementer = 0, hiScore = 0;
	
	String[][] answers = {
			{"White House", "Brown House"}
			, {"3", "2"}
			, {"50", "52"}
			, {"Pittsburgh", "Pennsylvania"}
			, {"Green", "Orange"}
			, {"10", "100"}
			, {"Pinochio", "Aladdin"}
			, {"46", "48"}
			, {"LEFT", "RIGHT"}
			, {"TRUE", "FALSE"}
			, {"Mars", "Earth"}
			, {"Oranges", "Grapes"}
			, {"Partridge", "Dove"}
			, {"Maine", "Alaska"}
			, {"Road Runner", "Tweety"}
			, {"Aramis", "Steve"}
			, {"Do not call", "Leave me alone"}
			, {"Me without coffee", "A woman scorned"}
			, {"Do it yourself", "Measure twice"}
			, {"Seductive woman", "Carnivorous plant"}
			, {"Avalanche", "Glacier"}
			, {"Cluster", "School"}
			, {"Four", "Three"}
			, {"India", "China"}
			, {"Directory assistance", "Police non emergency"}
			, {"Princess Peach", "Luigi"}
	};
	
	RethinkIt questions[] = {
		//parm 1 is Question, parm 2 is potential answers, parm 3 is answer, parm 4 is Class
			new RethinkIt(
					"Where does the president of the United States of America live?",
					answers[0],
					1,this
				)
			, new RethinkIt(
					"How many holes are there in a bowling ball?",
					answers[1],
					1,this
				)
			, new RethinkIt(
					"How many cards are there in a pack of cards?",
					answers[2],
					0,this
				) 
			, new RethinkIt(
					"Which is the only US state that starts with the letter 'P'?",
					answers[3],
					0,this
				) 	
			, new RethinkIt(
					"What color do you get if you mix blue and yellow paint together?",
					answers[4],
					1,this
				) 		
			, new RethinkIt(
					"How many years are there in a century?",
					answers[5],
					0,this
				) 			
			, new RethinkIt(
					"Which Disney character has a nose that grows longer every time he tells a lie?",
					answers[6],
					1,this
				) 
			, new RethinkIt(
					"How many hours are there in two days?",
					answers[7],
					0,this
				) 
			, new RethinkIt(
					"Which way is anti-clockwise, left or right?",
					answers[8],
					1,this
				) 
			, new RethinkIt(
					"True or false: the Sahara Desert is the smallest desert in the world?",
					answers[9],
					0,this
				) 
			, new RethinkIt(
					"Which planet is known as the Red Planet?",
					answers[10],
					1,this
				) 
			, new RethinkIt(
					"What type of fruit is dried to make raisins?",
					answers[11],
					0,this
				) 		
			, new RethinkIt(
					"In the 12 Days of Christmas song, what type of bird is sitting in a pear tree?",
					answers[12],
					1,this
				) 	
			, new RethinkIt(
					"Which American state is nearest to Russia?",
					answers[13],
					0,this
				) 	
			, new RethinkIt(
					"Wile E Coyote is always trying to catch what desert bird?",
					answers[14],
					1,this
				) 		
			, new RethinkIt(
					"Who was not one of the 'Three Musketeers' in Alexandre Dumas' popular novel of the same name?",
					answers[15],
					0,this
				) 	
			, new RethinkIt(
					"What phrase is used to describe the nationwide list of people who don't want to be contacted by telemarketers?",
					answers[16],
					1,this
				) 	
			, new RethinkIt(
					"According to the popular phrase about love and rejection, 'Hell hath no fury like' what?",
					answers[17],
					0,this
				) 
			, new RethinkIt(
					"According to a well-known piece of advice, 'If you want something done right, you have to' what?",
					answers[18],
					1,this
				) 
			, new RethinkIt(
					"What is a Venus flytrap?",
					answers[19],
					0,this
				) 
			, new RethinkIt(
					"What is the sudden flow of snow or ice down a cliff called?",
					answers[20],
					1,this
				) 
			, new RethinkIt(
					"What term is used to describe a group of fish?",
					answers[21],
					0,this
				) 
			, new RethinkIt(
					"In baseball, how many balls make a walk?",
					answers[22],
					1,this
				) 
			, new RethinkIt(
					"Where is fireworks first known to have been developed?",
					answers[23],
					0,this
				) 
			, new RethinkIt(
					"If you dial '411' on a telephone, who are you calling?",
					answers[24],
					1,this
				) 
			, new RethinkIt(
					"What is the name of Mario's brother in the 'Super Mario' video games?",
					answers[25],
					0,this
				) 
			
			
	};
	
	public static void main(String args[]) throws InterruptedException{
		new Quiz();
	}
	
	public Quiz() throws InterruptedException {
		super("Rethink It!");
		setResizable(true);
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p.setLayout(cards);
		numQs = questions.length;
		Random rand = new Random();
		int indexValue;
		boolean questionAdded = false;
		for (int i = 0; i < numQs; i++){
			while(!questionAdded){  // as long as the question hasnt been added
				indexValue = rand.nextInt(numQs);  // find a new random index number
				if (!index.contains(indexValue)){  // that index number cant already have been used
					index.add(indexValue); // add that number to the index
					p.add(questions[i],"q"+indexValue);  // add that question and indexValue
					questionAdded = true;  //bypass the where clause
				}
			}
			questionAdded = false;  //reset the bypass
		}
		cards.show(p, "q" + incrementer);
		add(p);
		setVisible(true);		
	}
	
	public void next() {
		if (incrementer < numQs){
			cards.show(p, "q" + hiScore);
		} else {
			showSummary();
		}
		
	}
	
	public void first() {
		cards.show(p, "q" + 0);
	}	
	
	public void showSummary(){
		JOptionPane.showMessageDialog(null, "All Done, here are your results:"
				+ "\nYou finished at " + hiScore + " out of " + numQs
			);
		System.exit(0);
	}

}
