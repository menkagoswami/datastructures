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

public class MinStack<T extends Comparable<T>> {

	private Stack<T> mainStack;
	private Stack<T> auxStack;

	public MinStack() {
		mainStack = new Stack<T>();
		auxStack = new Stack<T>();
	}

	public MinStack(int capacity) {
		mainStack = new Stack<T>(capacity);
		auxStack = new Stack<T>(capacity);
	}

	public MinStack<T> push(T element) {
		mainStack.push(element);
		if (auxStack.isEmpty()) {
			auxStack.push(element);
			return this;
		}
		try {
			T topElem = auxStack.peek();
			if (element.compareTo(topElem) <= 0)
				auxStack.push(element);
			else
				auxStack.push(topElem);
		} catch (StackUnderflowException ex) {
		}
		return this;
	}

	public T pop() throws StackUnderflowException {
		auxStack.pop();
		return mainStack.pop();
	}

	public T peek() throws StackUnderflowException {
		return mainStack.peek();
	}

	public boolean isEmpty() {
		return mainStack.isEmpty();
	}

	public String toString() {
		return mainStack.toString();
	}

	public T minimumElement() throws StackUnderflowException {
		return auxStack.peek();
	}

	public static void main(String[] args) {
		MinStack<Integer> stack = new MinStack<Integer>();
		stack.push(5).push(6).push(7).push(5).push(4).push(7).push(4).push(3).push(9);
		System.out.println(stack);
		try {
			while (true) {
				System.out.println("Minimum element: " + stack.minimumElement());
				System.out.println("Popped " + stack.pop());
			}
		} catch (StackUnderflowException e) {
			System.out.println("Stack is empty");
		}

	}
}
