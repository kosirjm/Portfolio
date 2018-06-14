/*****
 * Author   : brinkmwj
 * Date     : 2009-11-06
 * Sources  : All code is original
 */
#include "Graph.h"
#include <queue>
using namespace std;

/* I was running into critical error LNK1120: 1 so I looked at bickleydb 
code I added the for loop in the constructtor because of him
Turns out the error was because I initilized the project wrong
did not choose console option is MVS Oops that took some time to figure out*/

Graph::Graph(unsigned int numNodes){
  
   adjList.resize(numNodes);
   for (int i = 0; i < numNodes; i++) 
                adjList[i].edgeList.resize(numNodes);

}

int Graph::getCost(int node1, int node2){
	for(int i = 0; i < adjList[node1].edgeList.size(); i++)
		if(adjList[node1].edgeList[i].dest == node2)
			return adjList[node1].edgeList[i].cost;

	return -1;
}

//Add an edge from node1 to node2, and from node2 to node1, with
// the given cost. If the cost is < 0, throw a string exception.
void Graph::addEdge(int node1, int node2, double cost){
	if(cost<0)
		throw string("Cost cannot be negative, always stay positive!");
	
	adjList[node1].edgeList.push_back(*(new Edge(cost, node2)));
	adjList[node2].edgeList.push_back(*(new Edge(cost, node1)));

}

//Remove the edge from node1 to node2, and also from node2 to node1.
// If there are no such edges, then don't do anything.
void Graph::removeEdge(int node1, int node2){

	for (int i = 0; i < adjList[node1].edgeList.size(); i++)
		if(adjList[node1].edgeList[i].dest == node2)
			adjList[node1].edgeList.erase(adjList[node1].edgeList.begin()+i);
	
	for (int i = 0; i < adjList[node2].edgeList.size(); i++)
		if(adjList[node2].edgeList[i].dest == node1)
			adjList[node2].edgeList.erase(adjList[node2].edgeList.begin()+i);
}

//Searches if there is a path from node to node2
//Did with help of http://www.geeksforgeeks.org/find-if-there-is-a-path-between-two-vertices-in-a-given-graph/
 bool Graph::BFS(int node, int node2)
 {
	 if (node == node2)
        return true;
	 
	 int size = adjList.size();
	 int* visited = new int[size];
	 for (int i = 0; i < size; i++) 
			visited[i] = -1;
	 
	 queue<int> trav;
	 visited[node] = 1;
	 trav.push(node);	 

	  while(!(trav.empty()))
	  {
		  int cur = trav.front();
		  trav.pop();

		  for(int i = 0; i < adjList[cur].edgeList.size(); i++)
		  {
			 if(cur == node2)
              return true;

             if(visited[i] != 1)
			 {
                  visited[i] = 1;
				  trav.push(i);
			 }
                 
	     }
	  }
     return false;
 }