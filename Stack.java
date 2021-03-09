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

final public class Stack<T> {

	private int capacity = 10;
	private int top = -1;
	T arr[];

	@SuppressWarnings("unchecked")
	public Stack() {
		arr = (T[]) new Object[capacity];
	}

	@SuppressWarnings("unchecked")
	public Stack(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("ERROR: Stack size cannot be negative");
		this.capacity = capacity;
		arr = (T[]) new Object[capacity];
	}

	// push
	// pop
	// peek
	// isEmpty

	public Stack<T> push(T element) {
		if (top == capacity - 1) {
			extendArray();
		}
		top++;
		arr[top] = element;
		return this;
	}

	@SuppressWarnings("unchecked")
	private void extendArray() {
		capacity = 2 * capacity;
		T[] newArray = (T[]) new Object[capacity];
		for (int i = 0; i < arr.length; i++) {
			newArray[i] = arr[i];
		}

		arr = newArray;

	}

	public String toString() {
		if (top == -1) {
			return "<Empty stack>";
		}
		StringBuffer res = new StringBuffer();
		for (int i = top; i >= 0; i--) {
			res.append(i).append(':').append(arr[i]).append('\n');
		}

		return res.toString();

	}

	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException("Stack is empty");
		}
		T val = arr[top];
		arr[top] = null;
		top--;
		return val;
	}

	public boolean isEmpty() {
		return ((top == -1) ? true : false);
	}

	public T peek() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException("Stack is empty");
		return arr[top];
	}

	public int length() {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null)
				count++;
			else
				break;

		}
		return count;
	}

	public boolean contains(T element) {
		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == element) {
				flag = true;
				break;
			}
		}
		return flag;

	}

}

final class StackUnderflowException extends Exception {

	public StackUnderflowException(String s) {
		super(s);
	}
}