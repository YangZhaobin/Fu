package com.yzb.test.ds.draw;

import lombok.Data;
import org.omg.CORBA.Object;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@Data
public class Draw<E> {

    /**
     * 存储节点信息
     */
    private E[] vertices;
    /**
     * 存储边信息（邻接矩阵）
     */
    private  int[][] arcs;

    /**
     * 图的节点数
     */
    private int vexnum;

    /**
     * 记录节点是否已被遍历
     */
    private boolean[] visited;

    public Draw(int n) {
        vexnum = n;
        vertices = (E[])new Object[n];
        arcs = new int[n][n];
        visited = new boolean[n];
        for (int i = 0; i < vexnum; i++)
            for (int j = 0; j < vexnum; j++)
                arcs[i][j] = 0;
    }

    /**
     * 添加边
     */
    public void addEdge(int i, int j) {
        if (i == j)
            return;
        // 无向图对称的
        arcs[i][j] = 1;
        arcs[j][i] = 1;
    }

    public void visit(int i) {
        System.out.print(vertices[i] + " ");
    }

    /**
     * 深度优先遍历（递归实现）
     */
    public void dfs() {
        // 初始化节点访问标记
        for (int i = 0; i < visited.length; i++)
            visited[i] = false;

        // 开始深度优先遍历
        for (int i = 0; i < vexnum; i++)
            if (!visited[i]) traverseD(i);
    }

    /**
     * 从第i节点开始深度优先遍历
     */
    public void traverseD(int i) {
        // 标记第i个节点已遍历
        visited[i] = true;
        // 打印当前已经遍历的节点
        visit(i);
        // 遍历邻接矩阵中第i个节点的直接连通节点
        for(int j = 0; j < vexnum; j++)
            if (arcs[i][j] == 1 && !visited[j])
                traverseD(j);
    }

    public  void dfsNR(){
        // 初始化所有的节点的访问标志
        for (int i = 0; i < visited.length; i++)
            visited[i] = false;

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < vexnum; i++){
            if (!visited[i]) {
                visited[i] = true;
                visit(i);
                stack.push(i);
            }
            while(!stack.isEmpty()) {
                // 当前出栈的节点
                int k = stack.pop();
                for (int j = 0; j < vexnum; j++) {
                    // 如果是相邻的节点且没有访问过.
                    if (arcs[k][j] == 1 && !visited[j]) {
                        visited[j] = true;
                        visit(j);
                        stack.push(j);
                        // 这条路结束,返回上一个节点.
                        break;
                    }
                }
            }
        }
    }

    public void bfsNR(){
        // 初始化所有的节点的访问标志
        for (int i = 0; i < visited.length; i++)
            visited[i] = false;

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < vexnum; i++){
            if (!visited[i]) {
                visited[i] = true;
                visit(i);
                queue.add(i);
                while(!queue.isEmpty()) {
                    // 当前队首的节点
                    int k = queue.poll();
                    for (int j = 0; j < vexnum; j++) {
                        // 如果是相邻的节点且没有访问过.
                        if (arcs[k][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            visit(j);
                            queue.add(j);
                        }
                    }
                }
            }

        }
    }

    /**
     *  输出邻接矩阵
     */
    public void printfArcs() {
        for (int i = 0; i < arcs.length; i++) {
            for(int j = 0; j < arcs[0].length; j++)
                System.out.print(arcs[i][j] + "\t");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Draw<Character> draw = new Draw<>(9);
        Character[] vertices = {'A','B','C','D','E','F','G','H','I'};
        draw.setVertices(vertices);
        // 添加边
        draw.addEdge(0, 1);
        draw.addEdge(0, 5);
        draw.addEdge(1, 0);
        draw.addEdge(1, 2);
        draw.addEdge(1, 6);
        draw.addEdge(1, 8);
        draw.addEdge(2, 1);
        draw.addEdge(2, 3);
        draw.addEdge(2, 8);
        draw.addEdge(3, 2);
        draw.addEdge(3, 4);
        draw.addEdge(3, 6);
        draw.addEdge(3, 7);
        draw.addEdge(3, 8);
        draw.addEdge(4, 3);
        draw.addEdge(4, 5);
        draw.addEdge(4, 7);
        draw.addEdge(5, 0);
        draw.addEdge(5, 4);
        draw.addEdge(5, 6);
        draw.addEdge(6, 1);
        draw.addEdge(6, 3);
        draw.addEdge(6, 5);
        draw.addEdge(6, 7);
        draw.addEdge(7, 3);
        draw.addEdge(7, 4);
        draw.addEdge(7, 6);
        draw.addEdge(8, 1);
        draw.addEdge(8, 2);
        draw.addEdge(8, 3);
        draw.printfArcs();

        System.out.print("深度优先遍历（递归）：");
        draw.dfs();

        System.out.println();
        System.out.print("深度优先遍历（非递归）：");
        draw.dfsNR();

        System.out.println();
        System.out.print("广度优先遍历（非递归）：");
        draw.bfsNR();
    }
}
