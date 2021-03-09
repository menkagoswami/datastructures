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

public class Partition {
	public static void main(String[] args) {
		LinkedImpl li = new LinkedImpl();
		li.appendToTail(3).appendToTail(5).appendToTail(8).appendToTail(5).appendToTail(10).appendToTail(2)
				.appendToTail(1);
		int x = 5;
		System.out.println(li);
		LinkedImpl li2 = partitionList(li, x);
		System.out.println(li2);
	}

	private static LinkedImpl partitionList(LinkedImpl li, int threashold) {
		LinkedImpl list = new LinkedImpl();
		Node tracker = null;
		Node n = li.head;
		while (n != null) {
			Node newNode = new Node(n.data);
			if (n.data < threashold) {
				// Insert at head
				Node temp = list.head;
				list.head = newNode;
				newNode.next = temp;
				if (tracker == null) {
					// This is the first node to be inerted
					tracker = newNode;
				}
			} else {
				if (tracker == null) {
					// This is the first node to be inserted
					list.head = newNode;
					tracker = newNode;
				} else {
					// Insert at the tracker's position
					Node temp = tracker.next;
					tracker.next = newNode;
					newNode.next = temp;
				}
			}
			n = n.next;
		}
		return list;
	}
}
