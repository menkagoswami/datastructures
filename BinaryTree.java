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

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T> {

	interface BehaviourAtNode<T> {
		public void actionAtNode(T nodeData);
	}

	interface BehaviourByNode<T> {
		public void actionByNode(T nodeData);
	}

	@SuppressWarnings("hiding")
	public class Node<T> {

		private T data;
		private Node<T> left = null;
		private Node<T> right = null;
		private boolean visited;

		private Node(T data) {
			this.data = data;
		}
	}

	private Node<T> root;

	public BinaryTree<T> add(T data) {
		Node<T> node = new Node<>(data);
		if (root == null) {
			root = node;
			return this;
		}

		Queue<Node<T>> q = new LinkedList<>();
		q.add(root);
		while (true) {
			Node<T> n = q.remove();
			if (n.left == null || n.right == null) {
				if (n.left == null)
					n.left = node;
				else
					n.right = node;
				return this;
			}
			q.add(n.left);
			q.add(n.right);
		}
	}

	public String toString() {
		if (root == null)
			return "<Empty>";
		StringBuffer sb = new StringBuffer();
		Queue<Node<T>> q1 = new LinkedList<>();
		Queue<Node<T>> q2 = new LinkedList<>();
		q1.add(root);
		int level = 0;
		Queue<Node<T>> qParent = q1;
		Queue<Node<T>> qChildren = q2;
		Queue<Node<T>> temp;
		sb.append("level 0:");
		while (!qParent.isEmpty() || !qChildren.isEmpty()) {
			if (qParent.isEmpty()) {
				// Switch queues
				temp = qParent;
				qParent = qChildren;
				qChildren = temp;
				level++;
				sb.append("\nlevel " + level + ":");
			}
			Node<T> node = qParent.remove();
			sb.append(" ").append(node.data);
			if (node.left != null)
				qChildren.add(node.left);
			if (node.right != null)
				qChildren.add(node.right);
		}
		return sb.toString();
	}

	public void bfs(BehaviourAtNode<T> behaviour) {
		if (this.root == null)
			return;

		Queue<Node<T>> q = new LinkedList<>();
		q.add(this.root);
		while (!q.isEmpty()) {
			Node<T> node = q.remove();
			behaviour.actionAtNode(node.data);
			if (node.left != null)
				q.add(node.left);
			if (node.right != null)
				q.add(node.right);
		}
	}

	static class MyBehaviourPrint implements BehaviourAtNode<Integer> {
		public void actionAtNode(Integer nodeData) {
			System.out.print(" ");
			System.out.print(nodeData);
		}
	}

	static class MyBehaviourAdd implements BehaviourAtNode<Integer> {
		int sum = 0;

		public void actionAtNode(Integer nodeData) {
			sum += ((Integer) nodeData).intValue();
		}
	}

	static class MyBehaviourLargest implements BehaviourAtNode<Integer> {
		int largest = Integer.MIN_VALUE;

		public void actionAtNode(Integer nodeData) {
			int nodeValue = ((Integer) nodeData).intValue();
			if (nodeValue > largest)
				largest = nodeValue;
		}
	}

	static class NodeBehaviourPrint implements BehaviourByNode<Integer> {

		@Override
		public void actionByNode(Integer nodeData) {
			System.out.print(nodeData);
			System.out.print(" ");
		}
	}

	public void dfs(BehaviourByNode<T> behaviour) throws StackUnderflowException {
		if (this.root == null)
			return;
		Stack<Node<T>> s = new Stack<BinaryTree<T>.Node<T>>();
		s.push(this.root);
		while (!s.isEmpty()) {
			Node<T> node = s.pop();
			behaviour.actionByNode(node.data);
			if (node.right != null)
				s.push(node.right);
			if (node.left != null)
				s.push(node.left);

		}
	}

	public void postOrderTraversal() throws StackUnderflowException {
		if (this.root == null) {
			return;
		}
		Stack<Node<T>> stack = new Stack<BinaryTree<T>.Node<T>>();
		stack.push(this.root);

		while (!stack.isEmpty()) {
			Node<T> node = stack.peek();
			if (node.right != null && node.right.visited == false) {
				stack.push(node.right);
			}
			if (node.left != null && node.left.visited == false) {
				stack.push(node.left);
			}

			if (node.left == null && node.right == null) {

				Node<T> leaf = stack.peek();
				leaf.visited = true;
				Node<T> del = stack.pop();
				System.out.print(del.data);
				System.out.print(" ");

			}
			if ((node.left != null && node.right != null)
					&& (node.left.visited == true && node.right.visited == true)) {
				Node<T> parent = stack.pop();
				parent.visited = true;
				parent.left.visited = false;
				parent.right.visited = false;
				System.out.print(parent.data);
				System.out.print(" ");
			}

		}
	}

	public Node<T> returnRightMostLeafNode() throws StackUnderflowException {
		Node<T> n = root;
		if (this.root == null || (this.root.left == null && this.root.right == null)) {

			this.root = null;
			return n;
		}
		Node<T> node;
		while (true) {
			if (n.right != null) {
				if (n.right.right == null) {
					node = n.right;
					n.right = null;
					return node;
				} else {
					n = n.right;
				}

			} else if (n.left != null) {
				if (n.left.left == null) {
					node = n.left;
					n.left = null;
					return node;
				} else {
					n = n.left;
				}
			}
		}
	}

	public void inOrderTraversal() throws StackUnderflowException {
		if (this.root == null)
			return;
		Stack<Node<T>> stack = new Stack<Node<T>>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node<T> node = stack.peek();
			// System.out.println("print " +node.data);
			if (node.right != null && node.right.visited == false) {
				stack.pop();
				stack.push(node.right);
				node.right.visited = true;
				// System.out.println("print " +node.right.data);
				stack.push(node);
				// System.out.println("print " +node.data);

			}
			if (node.left != null && node.left.visited == false) {
				stack.push(node.left);
				// System.out.println("print " +node.left.data);
			}

			if (node.left == null && node.right == null) {

				Node<T> leaf = stack.pop();
				leaf.visited = true;
				System.out.print(" ");
				System.out.print(leaf.data);

			}

			if (node.left != null && node.right != null) {
				if (node.left.visited == true && node.right.visited == true) {

					Node<T> parent = stack.pop();
					parent.visited = true;
					System.out.print(" ");
					System.out.print(parent.data);
				}
			}
		}
	}

	public void deleteNode(T delNode) throws StackUnderflowException {
		if (this.root == null)
			throw new StackUnderflowException("Tree is empty");
		Node<T> node = this.root;
		Stack<Node<T>> stack = new Stack<BinaryTree<T>.Node<T>>();
		stack.push(node);
		while (!stack.isEmpty()) {
			Node<T> topNode = stack.peek();
			stack.pop();
			System.out.println("Current node: " + topNode.data);
			if (topNode.left != null && topNode.left.visited == false) {
				stack.push(topNode.left);
				topNode.left.visited = true;
				topNode.visited = true;
			}
			if (topNode.right != null && topNode.right.visited == false) {
				stack.push(topNode.right);
				topNode.right.visited = true;
				topNode.visited = true;
			}
			if (topNode.data == delNode) {
				Node<T> availableNode = this.returnRightMostLeafNode();
				topNode.data = availableNode.data;
				break;
			}

		}
	}

	public static void main(String[] args) throws StackUnderflowException {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		for (int i = 0; i < 15; i++)
			binaryTree.add(i);

		System.out.println("The tree is:");
		System.out.println(binaryTree);

		MyBehaviourPrint behaviourPrint = new MyBehaviourPrint();
		binaryTree.bfs(behaviourPrint);
		System.out.println();
		binaryTree.deleteNode(14);

		MyBehaviourAdd behaviourAdd = new MyBehaviourAdd();
		binaryTree.bfs(behaviourAdd);
		System.out.println("Sum of all the nodes is " + behaviourAdd.sum);
		MyBehaviourLargest behaviourLargest = new MyBehaviourLargest();
		binaryTree.bfs(behaviourLargest);
		System.out.println("Largest integer in the tree is " + behaviourLargest.largest);

		NodeBehaviourPrint print = new NodeBehaviourPrint();
		binaryTree.dfs(print);

		binaryTree.postOrderTraversal();
		binaryTree.postOrderTraversal();
		binaryTree.postOrderTraversal();
		binaryTree.inOrderTraversal();
	}

}
