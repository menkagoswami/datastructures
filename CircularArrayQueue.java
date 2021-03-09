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

@SuppressWarnings("unchecked")
public class CircularArrayQueue<T> {

	private int fe; // arrayIndex
	private int re; // arrayIndex
	private int capacity;
	private T[] arr;

	private CircularArrayQueue() {
		this.fe = -1;
		this.re = -1;
		this.capacity = 10;
		this.arr = (T[]) new Object[capacity];
	}

	private CircularArrayQueue(int capacity) {
		this.fe = -1;
		this.re = -1;
		this.capacity = capacity;
		this.arr = (T[]) new Object[capacity];
	}

	public CircularArrayQueue<T> enqueue(T element) {
		if (fe == 0 && re == capacity - 1)
			throw new RuntimeException("Queue is full");
		if (isEmpty()) {
			fe++;
			arr[fe] = element;
			re++;
			return this;
		}
		re++;
		arr[re] = element;

		return this;
	}

	public boolean isEmpty() {
		if (fe == re)
			return true;
		return false;
	}

	public T dequeue() {
		if (isEmpty())
			throw new RuntimeException("Queue is empty");
		T temp = arr[fe];
		fe++;
		return temp;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = fe; i <= re; i++) {
			sb.append(arr[i]).append('\n');
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		CircularArrayQueue<Integer> queue = new CircularArrayQueue<Integer>();
		queue.enqueue(20).enqueue(12).enqueue(1).enqueue(5).enqueue(8);
		System.out.println(queue.toString());

	}

}
