import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

@SuppressWarnings({ "serial", "unused" })
public class ProjectFour extends JFrame implements KeyListener, ActionListener{ 

    private JTextArea textArea;
    private JFrame frame;
    private String lastTextAreaState;
    private TrapdoorStack stack;
    private MathSolver solver;
    private Dictionary dictionary;
  
    /**
        Default constructor to setup the screen.
        No need to edit this method.
    **/
    @SuppressWarnings("deprecation")
	public ProjectFour(){ 
        frame = new JFrame("CS271 Awesome Editor");
        textArea = new JTextArea();
        lastTextAreaState = "";
        JMenuBar menuBar = new JMenuBar();
        JMenu menuItems = new JMenu("File");
  
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem quitItem = new JMenuItem("Quit");
  
        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        quitItem.addActionListener(this);
  
        menuItems.add(newItem);
        menuItems.add(openItem);
        menuItems.add(saveItem);
        menuItems.add(quitItem);
  
        JMenu doStuffMenu = new JMenu("Actions");
        
        //adds backend stuff for Undo and Redo
        try {
        	stack = new TrapdoorStack(10);
        } catch(SmallTrapdoorStackException e) {
        	System.out.println(e);
        }
        //add backend stuff for math solving
        solver = new MathSolver();
        //add beackend stuff for spell checking
        dictionary = new Dictionary("dictionary.txt");
        
        JMenuItem undoItem = new JMenuItem("Undo");
        JMenuItem redoItem = new JMenuItem("Redo");
        JMenuItem solveMathItem = new JMenuItem("Solve Math");
        JMenuItem spellCheckItem = new JMenuItem("Spell Check");
  
        undoItem.addActionListener(this);
        redoItem.addActionListener(this);
        solveMathItem.addActionListener(this);
        spellCheckItem.addActionListener(this);
  
        doStuffMenu.add(undoItem);
        doStuffMenu.add(redoItem);
        doStuffMenu.add(solveMathItem);
        doStuffMenu.add(spellCheckItem);
  
        menuBar.add(menuItems);
        menuBar.add(doStuffMenu);
  
        frame.setJMenuBar(menuBar);
        //kill the program instead of just hiding the frame
        frame.addWindowListener(new java.awt.event.WindowAdapter(){
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                System.exit(0);
            }
        });
        
        textArea.addKeyListener(this);        
        frame.add(textArea);
        frame.setSize(600, 600);
        frame.show();
    } 
    
    /**
        This method will prompt the user to choose a folder to save a file.
        No need to edit this method.
    **/
    private void saveFile(){
        JFileChooser fileChooser = new JFileChooser("Save");
        int answer = fileChooser.showSaveDialog(null);
        if(answer == JFileChooser.APPROVE_OPTION){
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try{ 
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                output.write(textArea.getText());
                output.flush();
                output.close();
            } catch(Exception e){
                JOptionPane.showMessageDialog(frame, e.getMessage());
            }
            JOptionPane.showMessageDialog(frame, "File Saved.");
        } else{
            JOptionPane.showMessageDialog(frame, "Canceled Save Request.");
        }                
    }
    
    /**
        This method will prompt the user to choose a file to open.
        No need to edit this method.
    **/
    private void openFile(){
        JFileChooser fileChooser = new JFileChooser("Open");
        int answer = fileChooser.showOpenDialog(null);
        if(answer == JFileChooser.APPROVE_OPTION){ 
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try{
                String line;
                String outputText;
                @SuppressWarnings("resource")
				BufferedReader fin = new BufferedReader(new FileReader(file));
                outputText = fin.readLine();
                while((line = fin.readLine())!=null){ 
                    outputText += "\n" + line;
                }
                textArea.setText(outputText);
            } catch(Exception evt){ 
                JOptionPane.showMessageDialog(frame, evt.getMessage());
                return;
            }
            JOptionPane.showMessageDialog(frame, "Successfully opened file.");
        } else{
            JOptionPane.showMessageDialog(frame, "Cancelled file opening.");
        }
       
    }
  
    /**
        Not used in this project but needed because of the interface.
    **/
    public void keyPressed(KeyEvent e){
    }
    
    public void keyReleased(KeyEvent e){
    	//ensure the key typed put something in the textArea
    	if(!lastTextAreaState.equals(textArea.getText())) {
    		stack.push(textArea.getText()); //stores the new state of the textArea
    		lastTextAreaState = textArea.getText();
    	}
    }
    
    /**
        Method to handle keystrokes.
    **/
    public void keyTyped(KeyEvent e){
        System.out.println("typed:"+e+"\n");
    }
  
    /**
        If a menu option is chosen, do something...
        New, Save, Open and Quit are all functional and should not be changed.
        Read the above code and figure out how to add methods to accomplish the 
        tasks in the "Actions" menu bar. In other words, complete the code such 
        that undo, redo, solve math and spell check all work.
    **/
    public void actionPerformed(ActionEvent e){ 
        String action = e.getActionCommand();
        System.out.println(e.getActionCommand());
        if(action.equals("New")){ 
            textArea.setText(""); //creating a new document, simple, clear text.
        } else if(action.equals("Save")){
            saveFile(); //saving a file, done in another method
        } else if(action.equals("Open")){ 
            openFile(); //opening a file, similar to saving one
        } else if(action.equals("Quit")){ 
            System.exit(0);
        } else if(action.equals("Undo")) {
        	textArea.setText(stack.undo()); //triggers the stack's undo function
        } else if(action.equals("Redo")) {
        	textArea.setText(stack.redo()); //triggers the stack's redo function
        } else if(action.equals("Solve Math")) {
        	textArea.setText(solver.solve(textArea.getText())); //replaces math equations with answers
        	//ensure there were equations that were solved
        	if(!lastTextAreaState.equals(textArea.getText())) {
        		stack.push(textArea.getText()); //stores the new state of the textArea
        		lastTextAreaState = textArea.getText();
        	}
        } else if(action.equals("Spell Check")) { //offers suggestions for misspelled words at the end of the file
        	DictionarySuggestion[] dictionarySuggestions = dictionary.getSuggestions(textArea.getText());
        	if(dictionarySuggestions == null) { //if there are no spelling errors, say so
        		JOptionPane.showMessageDialog(frame, "No spelling errors found.");
        	} else { //offer corrections if there are errors and available suggestions
        		for(DictionarySuggestion entry : dictionarySuggestions) {
        			if(entry.getSuggestions() != null) {
        				String choice = (String)JOptionPane.showInputDialog(
        						frame, "Misspelled Word: "+entry.getMisspelledWord()+"\nSuggestions:",
        						"Spell Checker", JOptionPane.QUESTION_MESSAGE, null,
        						entry.getSuggestions(), entry.getSuggestions()[0]
        						);
        				if(choice != null) {
        					textArea.setText(textArea.getText().replaceFirst(entry.getMisspelledWord(), choice));
        				}
        			} else {
        				JOptionPane.showMessageDialog(frame, "Misspelled Word: "+entry.getMisspelledWord()+"\n Suggestions: N/A");
        			}
        		}
        	}
        }
    }
  
    public static void main(String args[]){ 
        @SuppressWarnings("unused")
		ProjectFour pf = new ProjectFour();
    } 
} 