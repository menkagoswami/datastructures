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

import java.util.Hashtable;

public class LinkedImpl {
	Node head = null;

	public static void main(String[] args) {
		LinkedImpl list = new LinkedImpl();
		list.appendToTail(5);
		list.appendToTail(10);
		list.appendToTail(20);
		list.appendToTail(15);
		list.appendToTail(20);
		list.appendToTail(25);
		list.appendToTail(20);
		System.out.println(list);
		list.deleteNode(20);
		System.out.println(list);
		list.deleteNode(5);
		System.out.println(list);
		list.removeDuplicates(list);

	}

	private void removeDuplicates(LinkedImpl list) {
		Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();

	}

	public LinkedImpl appendToTail(int data) {
		Node end = new Node(data);
		if (this.head == null) { // Empty list
			this.head = end;
			return this;
		}
		Node n = this.head;
		while (n.next != null) {
			n = n.next;
		}
		n.next = end;
		return this;
	}

	public String toString() {
		if (this.head == null)
			return "||";
		StringBuilder list = new StringBuilder("");
		Node n = this.head;
		while (n != null) {
			list.append(n.data).append("->");
			n = n.next;
		}
		return list.append("||").toString();
	}

	public void deleteNode(int data) {
		if (this.head == null) // list is empty
			return;
		if (this.head.data == data) { // value to be deleted is at head
			head = head.next;
			return;
		}
		Node n = head;
		while (n != null && n.next != null) {
			if (n.next.data == data) {
				n.next = n.next.next;
			}
			n = n.next;
		}
		return;
	}
}
