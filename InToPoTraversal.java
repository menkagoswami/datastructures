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

public class InToPoTraversal {

	private static boolean isOpenBracket(char c) {
		return c == '(' || c == '{' || c == '[';
	}

	private static boolean isCloseBracket(char c) {
		return c == ')' || c == '}' || c == ']';
	}

	private static boolean isOperator(char c) {
		return c == '+' || c == '*' || c == '%' || c == '/' || c == '-';
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
		if ((top == '*' && c == '+') || (top == '*' && c == '+'))
			return 2;
		if ((top == '/' && c == '+') || (top == '/' && c == '+'))
			return 2;
		if ((top == '%' && c == '+') || (top == '%' && c == '+'))
			return 2;
		else
			return -1;
	}

	public static boolean conversion(String expr) throws StackUnderflowException {
		Stack<Character> stack = new Stack<>();
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < expr.length(); i++) {
			char ch = expr.charAt(i);
			if (!isOpenBracket(ch) && !isCloseBracket(ch) && !isOperator(ch)) {
				// Its an operand,
				sb.append(ch);
			}
			if (isOpenBracket(ch)) {
				stack.push(ch);
			}
			if (isOperator(ch)) {
				if (stack.length() == 0)
					stack.push(ch);
				else {
					char peek = stack.peek();
					if (peek == '(')
						stack.push(ch);
					int precedence = operatorPrecedence(peek, ch);
					if (precedence == 0) {
						stack.push(stack.pop());
						sb.append(peek);
					}
					if (precedence == 1) {
						stack.push(ch);
					}
					if (precedence == 2) {
						char temp = stack.pop();
						stack.push(ch);
						sb.append(temp);

					}

				}

			}
			if (isCloseBracket(ch)) {
				while (!stack.peek().equals('(')) {
					char temp = stack.pop();
					sb.append(temp);
				}
				stack.pop();
			}

		}
		while (!stack.isEmpty())
			sb.append(stack.pop());
		System.out.println(sb.toString());
		if (stack.length() == 0) {
			return true;
		} else
			return false;
	}

	public static void main(String[] args) throws StackUnderflowException {
		if (conversion("A+B*C")) {
			System.out.println("<Infix to postfix successful>");
		} else {
			System.out.println("<Invalid expression>");
		}

	}

}

//test cases

//A*(B+C*D)+E

//(A+B)*(C/D)

//A+(B*C+D)+E

//A+B*C
