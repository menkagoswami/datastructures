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

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AdjacencyListGraph<T> implements Graph<T> {

	private class Pair {
		private T destination;
		private double weight;

		private Pair(T destination, double weight) {
			this.destination = destination;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return this.destination.toString() + " (" + this.weight + ")";
		}

		@Override
		public int hashCode() {
			return this.destination.hashCode() + (int) this.weight;
		}

		@Override
		public boolean equals(Object o) {
			@SuppressWarnings("unchecked")
			Pair pair = (Pair) o;
			return this.destination.equals(pair.destination) && this.weight == pair.weight;
		}
	}

	private Type type;

	public AdjacencyListGraph(Type type) {
		this.type = type;
	}

	private Map<T, Set<Pair>> map = new Hashtable<T, Set<Pair>>();
	private int numEdges = 0;

	@Override
	public Graph<T> edge(T node1, T node2, double weight) {
		assert (node1 != null && node2 != null && weight > 0);
		if (addToMap(node1, node2, weight) == false)
			return this;
		if (type == Type.UNDIRECTED)
			addToMap(node2, node1, weight);
		numEdges++;
		return this;
	}

	private boolean addToMap(T node1, T node2, double weight) {
		Set<Pair> set = map.get(node1);
		if (set == null) {
			set = new HashSet<Pair>();
		}
		Pair pair = new Pair(node2, weight);
		if (set.contains(pair))
			return false;
		set.add(pair);
		map.put(node1, set);
		return true;
	}

	@Override
	public String toString() {
		if (map.size() == 0)
			return "<Empty Graph>";
		StringBuffer ret = new StringBuffer("");
		for (T key : map.keySet()) {
			ret.append(key.toString() + " -> ");
			Set<Pair> set = map.get(key);
			for (Pair pair : set) {
				ret.append(pair.toString() + " ");
			}
			ret.append('\n');
		}
		return ret.toString();
	}

	@Override
	public int numEdges() {
		return numEdges;
	}

	@Override
	public int numNodes() {
		return map.size();
	}

	public static void main(String[] args) {
		Graph<String> graph = new AdjacencyListGraph<String>(Graph.Type.UNDIRECTED);
		graph.edge("Delhi", "Mumbai", 100).edge("Bangalore", "Canada", 200).edge("Bangalore", "Toronto", 400)
				.edge("Canada", "Toronto", 600).edge("Delhi", "Bangalore", 900).edge("Mumbai", "Gujrat", 800)
				.edge("Mumbai", "Gujrat", 800).edge("Gujrat", "Delhi", 1000);

		graph.bfs("Gujrat");

		graph.dfs("Delhi");

	}

	@Override
	public void bfs(T node) {
		if (this.map.size() == 0)
			return;
		Queue<T> queue = new LinkedList<>();
		Set<T> visited = new HashSet<T>();
		// System.out.println(map.keySet());
		if (map.keySet().contains(node))
			queue.add(node);
		while (!queue.isEmpty()) {
			T key = queue.remove();
			if (visited.contains(key))
				continue;
			else
				visited.add(key);
			System.out.print(key);
			System.out.print(" ");
			Set<Pair> values = this.map.get(key);
			if (values == null)
				continue;
			for (Pair pair : values) {
				queue.add(pair.destination);
			}
		}
	}

	@Override
	public void dfs(T node) {
		if (this.map.size() == 0)
			return;
		Stack<T> stack = new Stack<T>();
		stack.push(node);
		Set<T> visitedNodes = new HashSet<T>();
		while (!stack.isEmpty()) {
			T topNode = null;
			try {
				topNode = stack.pop();
			} catch (StackUnderflowException e) {

			}
			if (visitedNodes.contains(topNode))
				continue;
			else
				visitedNodes.add(topNode);
			System.out.print(topNode);
			System.out.print(" ");
			Set<Pair> neighbours = map.get(topNode);
			for (Pair pair : neighbours) {
				if (visitedNodes.contains(pair.destination))
					continue;
				else
					stack.push(pair.destination);
				// System.out.println(i++);
			}
		}
	}

}
