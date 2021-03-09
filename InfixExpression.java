/* 
* Copyright (c) 2021, Menka Goswami
* All rights reserved.
* 
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
* * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
* * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
* * Neither the name of the <copyright holder> nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
* 
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package datastructurelib;

public class InfixExpression {
	static String str = "(2*(4*5))";

	public static void main(String[] args) throws StackUnderflowException {

		int res = InfixExpression.infixEvaluation(str);
		System.out.println(res);

	}

	private static boolean isOpenBracket(char c) {
		return c == '(' || c == '{' || c == '[';
	}

	private static boolean isCloseBracket(char c) {
		return c == ')' || c == '}' || c == ']';
	}

	private static boolean isOperator(char c) {
		return c == '+' || c == '*' || c == '%' || c == '/' || c == '-';
	}

	private static boolean isNum(char c) {
		return c >= 48 && c <= 57;
	}

	private static int operatorPrecedence(char top, char c) {
		if (top == '+' && c == '+')
			return 0;
		if (top == '-' && c == '-')
			return 0;
		if (top == '+' && c == '-')
			return 0;
		if (top == '-' && c == '+')
			return 0;
		if ((top == '*' && c == '/') || (top == '*' && c == '%') || (top == '*' && c == '*'))
			return 0;
		if ((top == '/' && c == '*') || (top == '/' && c == '%') || (top == '/' && c == '/'))
			return 0;
		if ((top == '%' && c == '*') || (top == '%' && c == '%') || (top == '%' && c == '/'))
			return 0;

		if ((top == '+' && c == '*') || (top == '-' && c == '*'))
			return 1;
		if ((top == '+' && c == '/') || (top == '-' && c == '/'))
			return 1;
		if ((top == '+' && c == '%') || (top == '-' && c == '%'))
			return 1;
		if ((top == '+' && c == '(') || (top == '-' && c == '(') || (top == '+' && c == ')')
				|| (top == '-' && c == ')'))
			return 1;

		if ((top == '*' && c == ')') || (top == '*' && c == '('))
			return 1;
		if ((top == '/' && c == ')') || (top == '/' && c == '('))
			return 1;
		if ((top == '%' && c == ')') || (top == '%' && c == '('))
			return 1;

		if ((top == '*' && c == '+') || (top == '*' && c == '+'))
			return 2;
		if ((top == '/' && c == '+') || (top == '/' && c == '+'))
			return 2;
		if ((top == '%' && c == '+') || (top == '%' && c == '+'))
			return 2;
		if ((top == '(' && c == '+') || (top == '(' && c == '-') || (top == '(' && c == '*')
				|| (top == '(' && c == '/'))
			return 4;
		else
			return -1;
	}

	public static int evaluate(int a, int b, char operator) {
		int value = 0;
		switch (operator) {
		case '+':
			value = a + b;
			break;
		case '-':
			if (a > b)
				value = a - b;
			else
				value = b - a;
			break;
		case '*':
			value = a * b;
			break;

		case '/':
			if (b != 0)
				value = a / b;
			break;
		}
		return value;
	}

	public static int infixEvaluation(String str) throws StackUnderflowException {
		Stack<Integer> operandStack = new Stack<>();
		Stack<Character> operatorStack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			System.out.println(ch);
			if (isNum(ch)) {
				operandStack.push(ch - 48);
			}
			if (isOpenBracket(ch))
				operatorStack.push(ch);
			if (isOperator(ch)) {
				if (operatorStack.length() == 0)
					operatorStack.push(ch);
				else {
					char op = operatorStack.peek();
					int precedence = operatorPrecedence(op, ch);
					calculateAsPerPrecedence(precedence, operandStack, operatorStack, op, ch);
				}
			}
			if (isCloseBracket(ch)) {
				while (!(operatorStack.peek() == '(')) {
					int temp = operandStack.pop();
					char op = operatorStack.peek();
					System.out.println(operandStack.peek());
					int value = evaluate(operandStack.peek(), temp, op);
					operandStack.pop();
					operandStack.push(value);
					operatorStack.pop();
				}
				operatorStack.pop();
			}
		}
		while (!(operandStack.isEmpty() || operatorStack.isEmpty())) {
			int op = operandStack.pop();
			int res = evaluate(op, operandStack.pop(), operatorStack.pop());
			operandStack.push(res);
		}
		System.out.println(operandStack.peek());
		return operandStack.peek();
	}

	private static void calculateAsPerPrecedence(int precedence, Stack<Integer> operandStack,
			Stack<Character> operatorStack, char op, char ch) throws StackUnderflowException {
		if (precedence == 0) {
			int temp = operandStack.pop();
			int value = evaluate(temp, operandStack.peek(), op);
			operandStack.pop();
			operandStack.push(value);
			operatorStack.pop();
			operatorStack.push(ch);
		}
		if (precedence == 1) {
			operatorStack.push(ch);
		}
		if (precedence == 2) {
			int temp = operandStack.pop();
			int value = evaluate(temp, operandStack.peek(), op);
			operandStack.pop();
			operandStack.push(value);
			operatorStack.pop();
		}
		if (precedence == 4) {
			operatorStack.push(ch);
		}

	}
}
