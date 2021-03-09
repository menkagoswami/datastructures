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

public class PostfixEvaluation {

	private static boolean isOperator(char c) {
		return c == '+' || c == '*' || c == '%' || c == '/' || c == '-';
	}

	private static int calculate(char c, int temp1, int temp2) {
		if (c == '+')
			return (temp2 + temp1);
		if (c == '*')
			return (temp2 * temp1);
		if (c == '-')
			return (temp2 - temp1);
		if (c == '/')
			return (temp2 + temp1);
		else
			return 0;
	}

	public static int postfixCalculate(String expr) throws StackUnderflowException {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < expr.length(); i++) {
			char ch = expr.charAt(i);
			if (!isOperator(ch)) {
				stack.push(Integer.parseInt(String.valueOf(ch)));
			}
			if (isOperator(ch)) {
				int temp1 = stack.pop();
				int temp2 = stack.pop();
				int result = calculate(ch, temp1, temp2);
				stack.push(result);
			}
		}
		System.out.println(stack);
		return stack.peek();
	}

	public static void main(String[] args) throws StackUnderflowException {
		PostfixEvaluation.postfixCalculate("231*+9-");
	}
}
