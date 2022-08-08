package org.example.generic.bridge;

/**
 * @author: zyh
 * @date: 2022/8/8
 */
public class Node<T> {

    public T data;

    public Node(T data) {
        this.data = data;
    }

    public void setData(T data) {
        System.out.println("Node setData()");
        this.data = data;
    }
}
