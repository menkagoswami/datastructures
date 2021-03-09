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

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

public class AdjacencyMatrixGraph<T> implements Graph<T> {

	private int index = -1;
	private int numEdges = 0;
	private int numNodes = 0;
	private int size = 10;
	@SuppressWarnings("unchecked")
	private Pair<T>[][] matrix = new Pair[size][size];
	private Map<T, Integer> indexMap = new HashMap<>();
	private Type type;

	public AdjacencyMatrixGraph(Type type) {
		this.type = type;
	}

	@SuppressWarnings("hiding")
	private class Pair<T> {
		private T node;
		private double weight;

		private Pair(T node, double weight) {
			this.node = node;
			this.weight = weight;
		}

		private Pair() {
			// TODO Auto-generated constructor stub
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Graph<T> edge(T node1, T node2, double weight) {
		assert (node1 != null && node2 != null && weight > 0);
		int index1 = getIndex(node1);
		int index2 = getIndex(node2);
		if (index == size) {
			size = size * 2;
			Pair<T>[][] newMatrix = new Pair[size][size];
			for (int i = 0; i < index; i++) {
				for (int j = 0; j < index; j++) {
					newMatrix[i][j] = matrix[i][j];

				}
			}
			matrix = newMatrix;
		}
		matrix[index1][index2] = new Pair<T>(node2, weight);
		if (type == Type.UNDIRECTED)
			matrix[index2][index1] = new Pair<T>(node1, weight);
		numEdges++;
		return this;
	}

	private int getIndex(T node1) {
		Integer value = indexMap.get(node1);
		if (value == null) {
			index++;
			indexMap.put(node1, index);
			numNodes++;
			return index;
		} else {
			return value;
		}

	}

	@Override
	public int numEdges() {
		return numEdges;
	}

	@Override
	public int numNodes() {
		return numNodes;
	}

	@Override
	public String toString() {
		Pair<T> pair = new Pair<T>();
		StringBuffer key = new StringBuffer();

		for (int i = 0; i <= index; i++) {
			int k = i;
			Optional<Entry<T, Integer>> node = this.indexMap.entrySet().stream().filter(e -> e.getValue().equals(k))
					.findFirst();
			StringBuffer values = new StringBuffer();
			for (int j = 1; j <= index; j++) {
				pair = matrix[i][j];
				if (pair != null)
					values.append(pair.node).append(pair.weight);
				else
					continue;
			}
			if (!StringUtils.isEmpty(values))
				key.append(node.get().getKey()).append("->").append(values);
			key.append('\n');
		}
		return key.toString();
	}

	public static void main(String[] args) {
		Graph<String> graph = new AdjacencyMatrixGraph<String>(Graph.Type.DIRECTED);
		graph.edge("Delhi", "Bangalore", 100).edge("Delhi", "Canada", 200).edge("Delhi", "Mumbai", 300)
				.edge("Bangalore", "Gujrat", 400).edge("Canada", "Gujrat", 500).edge("Mumbai", "Hyderabad", 600)
				.edge("Canada", "Hyderabad", 900);
		// System.out.println(graph);
		System.out.println(graph.numEdges());
		System.out.println(graph.numNodes());
		graph.dfs("Delhi");
		graph.bfs("Delhi");

	}

	@Override
	public void bfs(T node) {
		Queue<T> queue = new LinkedList<T>();
		Set<T> visitedNodes = new HashSet<T>();
		queue.add(node);
		visitedNodes.add(node);
		Pair<T> pair = null;
		while (!queue.isEmpty()) {
			T removedNode = queue.remove();
			System.out.print(removedNode + " ");
			int row = this.indexMap.get(removedNode);
			for (int column = 0; column <= index; column++) {
				pair = matrix[row][column];
				if (pair != null && !visitedNodes.contains(pair.node)) {
					visitedNodes.add(pair.node);
					queue.add(pair.node);
				}
			}
		}
		System.out.println();

	}

	@Override
	public void dfs(T node) {
		Stack<T> stack = new Stack<T>();
		stack.push(node);
		Set<T> visitedNodes = new HashSet<T>();
		visitedNodes.add(node);
		Pair<T> pair = null;
		while (!stack.isEmpty()) {
			T topMost = stack.pop();
			System.out.print(topMost + " ");
			int row = this.indexMap.get(topMost);
			for (int col = 0; col <= index; col++) {
				pair = matrix[row][col];
				if (pair != null && !visitedNodes.contains(pair.node)) {
					visitedNodes.add(pair.node);
					stack.push(pair.node);
				}
			}

		}
		System.out.println();

	}
}
