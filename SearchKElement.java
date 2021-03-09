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

public class SearchKElement {
	public static void main(String[] args) {
		LinkedImpl li = new LinkedImpl();
		li.appendToTail(5).appendToTail(6).appendToTail(8).appendToTail(9).appendToTail(10).appendToTail(12)
				.appendToTail(15);
		int size = findSize(li);
		System.out.println(size);
		int data = findKElement(li, size, 6);
		System.out.println(data);
	}

	private static int findSize(LinkedImpl li) {
		Node n = li.head;
		int size = 0;
		if (n == null)
			return 0;
		else
			size = 1;
		while (n != null && n.next != null) {
			n = n.next;
			++size;
		}
		return size;
	}

	private static int findKElement(LinkedImpl li, int size, int k) {
		int counter = 0;
		Node n = li.head;
		while (n != null && n.next != null) {
			if (size - k == counter) {
				return n.data;
			} else {
				n = n.next;
				counter++;
			}
		}

		return n.data;
	}
}
