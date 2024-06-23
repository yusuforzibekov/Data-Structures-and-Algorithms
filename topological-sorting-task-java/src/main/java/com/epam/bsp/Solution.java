package com.epam.bsp;

import java.util.List;

import org.javatuples.Pair;
import java.util.*;

public class Solution {

    /**
     * Returns any appropriate order of tasks that complies with all dependencies.
     * 
     * NOTE: There is at least one eligible order.
     * 
     * For example, you have five tasks from 0 to 4 and the dependency list [(0,1), (3,4), (4,1)].
     * One of the eligible orders is [0, 3, 4, 1, 2].
     * 
     * @param numTasks the number of tasks.
     * @return any appropriate tasks order that complies with the dependencies.
     */
    public static List<Integer> findTasksOrder(int numTasks, List<Pair<Integer, Integer>> dependencies) {
        // Build adjacency list to represent the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numTasks; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numTasks];
        
        // Populate the adjacency list and calculate the indegree of each task
        for (Pair<Integer, Integer> dependency : dependencies) {
            int taskA = dependency.getValue0();
            int taskB = dependency.getValue1();
            graph.get(taskA).add(taskB);
            indegree[taskB]++;
        }

        // Perform topological sort
        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // Add tasks with 0 indegree to the queue
        for (int i = 0; i < numTasks; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int task = queue.poll();
            order.add(task);

            // Decrease the indegree of adjacent tasks
            for (int adjacent : graph.get(task)) {
                indegree[adjacent]--;
                if (indegree[adjacent] == 0) {
                    queue.offer(adjacent);
                }
            }
        }

        return order;
    }

}
