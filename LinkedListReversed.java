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

public class LinkedListReversed {

	public static void main(String[] args) {
		LinkedImpl list1 = new LinkedImpl();
		list1.appendToTail(6).appendToTail(1).appendToTail(7);
		LinkedImpl list2 = new LinkedImpl();
		list2.appendToTail(2).appendToTail(9).appendToTail(5);
		LinkedImpl list3 = new LinkedImpl();
		int carry = 0;
		addListInReverse(list1, list2, list3, carry);
	}

	private static LinkedImpl addListInReverse(LinkedImpl list1, LinkedImpl list2, LinkedImpl list3, int carry) {
		Node p1 = list1.head;
		Node p2 = list2.head;
		Node q1 = p1.next;
		Node q2 = p2.next;
		Node a;
		Node b;

		int num1, carry1, num2;
		while (q1 != null || q2 != null) {
			num1 = q1.data + q2.data;
			carry = num1 / 10;
			num1 = num1 % 10;
			list3.appendToTail(num1);

			num2 = p1.data + p2.data + carry;
			carry1 = num2 / 10;
			num2 = num2 % 10;
			list3.appendToTail(num2);

			ReverseLinkedList.reverse(list3);
			a = list3.head;
			b = a.next;
			System.out.println(list3);

			p1 = q1;
			p2 = q2;
			q1 = q1.next;
			q2 = q2.next;
			System.out.println(p1.data);
			System.out.println(p2.data);
			System.out.println(q1.data);
			System.out.println(q2.data);
		}
		System.out.println(list3);
		return list3;

	}
}
