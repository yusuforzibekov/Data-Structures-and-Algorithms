package com.epam.bsp.linked.list;

import java.util.List;

/**
 * Basic data structure for custom Linked List representation
 */
public class CustomLinkedList<E extends Comparable<E>> {
    /**
     * Internal class for representing a linked list node
     */
    protected static class CustomListNode<E> {
        /**
         * Data part
         */
        protected E element;
        /**
         * Link to the next node
         */
        protected CustomListNode<E> next;

        public CustomListNode() {
        }

        public CustomListNode(E element, CustomListNode<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    /**
     * Linked List head
     */
    protected CustomListNode<E> head;

    public CustomLinkedList() {
        head = null;
    }

    public CustomLinkedList(CustomLinkedList list) {
        head = list.head;
    }

    /**
     * Constructor from a list instance.
     * @param elements elements that should be inserted into the result linked list.
     */
    public CustomLinkedList(List<E> elements) {
        this();
        CustomListNode<E> curr = null;
        for(E element : elements) {
            if(head == null) {
                head = new CustomListNode<>(element, null);
                curr = head;
            } else {
                curr.next = new CustomListNode<>(element, null);
                curr = curr.next;
            }
        }
    }

    /**
     * Returns whether `this` instance is identical to `list` one, order of elements matters.
     * @param elements `list` for identification.
     * @return true if this linked list is identical to the given list, false otherwise.
     */
    public boolean check(List<E> elements) {
        CustomListNode<E> curr = head;
        for(E element : elements) {
            if(curr == null) {
                return false;
            }
            if(!element.equals(curr.element)) {
                return false;
            } else {
                curr = curr.next;
            }
        }
        return curr == null;
    }

    /**
     * Returns a merged (sorted) linked list using two given (sorted) linked lists: `this` and `sorted`.
     * @param sorted the second sorted linked list.
     * @return the result of merging two given lists.
     */
    public CustomLinkedList<E> merge(CustomLinkedList<E> sorted) {
        // Create a new linked list for the result
        CustomLinkedList<E> mergedList = new CustomLinkedList<>();

        // Initialize pointers for this list and the sorted list
        CustomListNode<E> thisCurrent = head;
        CustomListNode<E> sortedCurrent = sorted.head;

        // Create a dummy node to serve as the head of the merged list
        CustomListNode<E> dummyNode = new CustomListNode<>();
        CustomListNode<E> mergedCurrent = dummyNode;

        // Traverse both lists and merge them in sorted order
        while (thisCurrent != null && sortedCurrent != null) {
            if (thisCurrent.element.compareTo(sortedCurrent.element) <= 0) {
                mergedCurrent.next = thisCurrent;
                thisCurrent = thisCurrent.next;
            } else {
                mergedCurrent.next = sortedCurrent;
                sortedCurrent = sortedCurrent.next;
            }
            mergedCurrent = mergedCurrent.next;
        }

        // Append remaining nodes from either list
        if (thisCurrent != null) {
            mergedCurrent.next = thisCurrent;
        }
        if (sortedCurrent != null) {
            mergedCurrent.next = sortedCurrent;
        }

        // Set the head of the merged list
        mergedList.head = dummyNode.next;
        return mergedList;
    }
}
