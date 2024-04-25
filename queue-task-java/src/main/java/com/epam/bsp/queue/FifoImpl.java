package com.epam.bsp.queue;

//put your code where needed

import java.util.NoSuchElementException;

public class FifoImpl<E> implements Fifo<E> {
    private int size;
    Node<E> head;
    Node<E> tail;
    private static class Node<E>{
        E data;
        Node<E> next;

        Node(E data){
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Returns true if the queue is empty.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @return whether the queue is empty.
     */
    @Override
    public boolean empty() {

        return size == 0;
    }

    /**
     * Returns the number of elements within the queue.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @return the number of elements within the queue.
     */
    @Override
    public int size() {

        return size;
    }

    /**
     * Adds a given element to the queue's tail.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @param e element to be added
     * @return the added element.
     */
    @Override
    public E push(E e) {
        Node<E> newNode = new Node<>(e);
        if(empty()){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return e;
    }

    /**
     * Returns the head element and removes it.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @return the head element
     * @throws java.util.NoSuchElementException if the queue is empty.
     */
    @Override
    public E pop() {
        if(empty()){
            throw new NoSuchElementException();
        }
        E popped = head.data;
        head = head.next;
        size--;
        if(empty()){
            tail = null;
        }
        return popped;
    }

    /**
     * Returns the head element.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @return the head element
     * @throws java.util.NoSuchElementException if the queue is empty.
     */
    public E peek() {
        if(empty()){
            throw new NoSuchElementException();
        }
        return head.data;
    }

}
