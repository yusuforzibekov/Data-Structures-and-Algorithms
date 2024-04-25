package com.epam.bsp.stack;

//put your code where needed

import java.util.LinkedList;
import java.util.NoSuchElementException;

public  class LifoImpl<E> implements Lifo<E> {
    private Node<E> head;
    private int size;


    private static class Node<E>{
        E data;
        Node<E> next;

        Node(E data){
            this.data = data;
            this.next = null;
        }
    }
    /**
     * Returns true if the stack is empty.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @return whether the stack is empty.
     */
    @Override
    public boolean empty() {
        return size == 0;
    }

    /**
     * Returns the number of elements within the stack.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @return the number of elements within the stack.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Adds a given element to the top of the stack.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @param e element to be added
     * @return the added element.
     */
    @Override
    public E push(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;
        return e;
    }

    /**
     * Returns the top element and removes it.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @return the top element
     * @throws java.util.NoSuchElementException if the stack is empty.
     */
    @Override
    public E pop() {
        if(empty()){
            throw new NoSuchElementException();
        }
        E popped = head.data;
        head = head.next;
        size--;
        return popped;
    }

    /**
     * Returns the top element.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @return the top element
     * @throws java.util.NoSuchElementException if the stack is empty.
     */
    @Override
    public E peek() {
        if(empty()){
            throw new NoSuchElementException();
        }
        return head.data;
    }
}
