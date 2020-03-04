
/**
 * Takes a string, solves math expressions within it, 
 * then returns the input but with the equations replaced with their solutions.
 * 
 * @author Martin Mueller
 *
 */
public class MathSolver {
	//instance variables
	String input;
	String[] equations;
	Stack stack;
	//solves math equations in a string and returns the answers in the string
	public String solve(String text) {
		input = text;
		equations = removeEquations();
		//if there aren't any equations to solve, just return
		if(equations[0] == null) {
			return input;
		}
		replaceVariables();
		for(String string : equations) {
			System.out.println(string);
		}
		convertToPostfix();
		for(String string : equations) {
			System.out.println(string);
		}
		solveEquations();
		for(String string : equations) {
			System.out.println(string);
		}
		combine();
		for(String string : equations) {
			System.out.println(string);
		}
		return input;
	}
	//removes equations from a given input, 
	//stores placeholder characters for the answers, 
	//and returns an array containing the equations
	private String[] removeEquations() {
		String newInput = "";
		String output = "";
		String[] parts = input.split("\\*\\*\\*");
		if(parts.length == 1) {
			return new String[1];
		} else {
			for(int i=0; i<parts.length; i++) {
				if(i%2 == 0) {
					newInput += parts[i];
				} else {
					newInput += "***";
					output += parts[i]+"%";
				}
			}
		}
		input = newInput;
		return output.split("%");
		
	}
	//replaces variables in equations with numbers
	private void replaceVariables() {
		String[] newEquations = new String[equations.length];
		//for each equation...
		for(int i=0; i<equations.length; i++) {
			//...checks to see if there are variables
			if(equations[i].contains(":")) {
				String[] array = equations[i].split(":");
				if(array[0].contains(",")) { //handles multiple variables
					String variables[] = array[0].split(",");
					for(int j=0; j<variables.length; j++) {
						String string[] = variables[j].split("=");
						array[1] = array[1].replaceAll(string[0], string[1]);
					}
					newEquations[i] = array[1];
				} else { //handles a single variable
					String variable[] = array[0].split("=");
					newEquations[i] = array[1].replaceAll(variable[0], variable[1]);
				}
			} else { //otherwise, ignore it
				newEquations[i] = equations[i];
			}
		}
		equations = newEquations;
	}
	//converts equations to postfix notation
	private void convertToPostfix() {
		String[] newEquations = new String[equations.length];
		for(int i=0; i<equations.length; i++) {
			stack = new Stack();
			String answer = "";
			String number = "";
			for(int j=0; j<equations[i].length(); j++) {
				char c = equations[i].charAt(j);
				if(Character.isDigit(c) || c == '.') {
					number += Character.toString(c);
				} else {
					answer += number+" ";
					number = "";
					if(c == '(') {
						stack.push(Character.toString(c));
					} else if(c == ')') {
						while(!stack.isEmpty() && !stack.peek().equals("(")) {
							answer += stack.pop()+" ";
						}
						if(!stack.isEmpty() && !stack.peek().equals("(")) {
							throw new RuntimeException("Invalid Expression");
						} else {
							stack.pop();
						}
					} else {
						while(!stack.isEmpty() && !hasPrecedence(Character.toString(c), stack.peek())) {
							answer += stack.pop()+" ";
						}
						stack.push(Character.toString(c));
					}
				}
			}
			//if there's still a number to add to the expression, do it
			if(!number.equals("")) {
				answer += number+" ";
			}
			//empty the stack
			while(!stack.isEmpty()) {
				answer += stack.pop()+" ";
			}
			//get rid of the space at the end
			if(answer.length() > 1 && answer.charAt(answer.length()-1) == ' ') {
				answer = answer.substring(0, answer.length()-1);
			}
			newEquations[i] = answer;
		}
		equations = newEquations;
	}
	//determines if an operator a has greater or equal precedence compared to operator b
	private boolean hasPrecedence(String a, String b) {
		if(a.equals("+") || a.equals("-")) {
			return false;
		} else {
			if(b.equals("*") || b.equals("/")) {
				return false;
			} else {
				return true;
			}
		}
	}
	//solves equations in postfix form
	private void solveEquations() {
		String[] newEquations = new String[equations.length];
		for(int i=0; i<equations.length; i++) {
			stack = new Stack();
			String[] strings = equations[i].split(" ");
			for(String string : strings) {
				if(string.matches("\\d+(\\.\\d+)?")) {
					stack.push(string);
				} else if(string.matches("\\+")) {
					stack.push(Double.toString(Double.parseDouble(stack.pop()) + Double.parseDouble(stack.pop())));
				} else if(string.matches("-")) {
					stack.push(Double.toString((-Double.parseDouble(stack.pop())) + Double.parseDouble(stack.pop())));
				} else if(string.matches("\\*")) {
					stack.push(Double.toString(Double.parseDouble(stack.pop()) * Double.parseDouble(stack.pop())));
				} else if(string.matches("/")) {
					stack.push(Double.toString((1.0/Double.parseDouble(stack.pop())) * Double.parseDouble(stack.pop())));
				}
			}
			newEquations[i] = stack.pop();
		}
		equations = newEquations;
	}
	//combines solved equations and a string with placeholder characters
	private void combine() {
		for(String equation : equations) {
			input = input.replaceFirst("\\*\\*\\*", equation);
		}
	}
	
}
