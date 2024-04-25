package com.epam.bsp.queue;

import java.util.NoSuchElementException;

/**
 * Fifo implementation with two stacks only
 * NOTE: Lifo interface is defined within stack-task-java project, you may re-use
 * the existing implementation (you should have created it at this point).
 *
 * @param <E> the type of elements in this implementation
 */
public class QueueViaStacks<E> implements Fifo<E> {

    //fields shouldn't be changed.
    private final Lifo<E> stack1 = new LifoImpl<>();
    private final Lifo<E> stack2 = new LifoImpl<>();

    /**
     * Returns true if the queue is empty.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @return whether the queue is empty.
     */
    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }

    /**
     * Returns the number of elements within the queue.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @return the number of elements within the queue.
     */
    public int size() {
        return stack1.size() + stack2.size();
    }

    /**
     * Adds a given element to the queue's tail.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @param e element to be added
     * @return the added element.
     */
    public E push(E e) {
        stack1.push(e);
        return e;
    }

    /**
     * Returns the head element and removes it.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @return the head element
     * @throws java.util.NoSuchElementException if the queue is empty.
     */
    public E pop() {
        if(empty()){
            throw new NoSuchElementException();
        }
        if(stack2.empty()){
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
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
        if(stack2.empty()){
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }
}
