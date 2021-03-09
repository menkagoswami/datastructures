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

public class LinkedList<T> {

	@SuppressWarnings("hiding")
	public class Node<T> {
		public Node<T> next = null;
		public T data;

		public Node(T d) {
			data = d;
		}

		public Node<T> setNext(Node<T> n) {
			next = n;
			return this;
		}

	}

	Node<T> head;

	public static void main(String[] args) {
		LinkedList<Integer> obj = new LinkedList<>();
		obj.addElementToTail(10).addElementToTail(20).addElementToTail(30).addElementToTail(40);
		System.out.println(obj);
		obj.clear();
		System.out.println(obj);
		obj.addElementToHead(20).addElementToHead(25).addElementToHead(35).addElementToHead(45).addElementToHead(55);
		System.out.println(obj);
	}

	LinkedList<T> addElementToHead(T data) {
		Node<T> node = new Node<>(data);
		Node<T> temp = this.head;
		this.head = node;
		head.next = temp;
		return this;
	}

	public LinkedList<T> addElementToTail(T data) {
		Node<T> node = new Node<>(data);
		if (this.head == null) {
			this.head = node;
			return this;
		}
		Node<T> current = this.head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = node;
		return this;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (this.head == null) {
			return sb.append("||").toString();
		}

		Node<T> current = this.head;
		while (current != null) {
			sb.append(current.data).append("->");
			current = current.next;

		}

		return sb.toString();
	}

	public void clear() {
		this.head = null;

	}

}
