package com.epam.bsp.stack;

import java.util.Optional;
import java.util.Stack;

//put your code where needed


public class LifoWithMinimumImpl<E extends Comparable<E>> extends LifoImpl<E> implements LifoWithMinimum<E> {
    /**
     *
     * Returns the minimum element in the stack.
     * NOTE: O(1) complexity is expected for this operation.
     *
     * @return the minimum element in the stack.
     */

    private Stack<E> minimumStack;
    public LifoWithMinimumImpl() {
        super();
        minimumStack = new Stack<>();
    }
    @Override
    public E push(E e){
        super.push(e);
        if(minimumStack.isEmpty() || e.compareTo(minimumStack.peek()) <= 0){
            minimumStack.push(e);
        }else{
            minimumStack.push(minimumStack.peek());
        }
        return e;
    }
    @Override
    public E pop(){
        minimumStack.pop();
        return super.pop();
    }

    @Override
    public Optional<E> getMinimum() {
        if(minimumStack.isEmpty()){
            return Optional.empty();
        }else {
            return Optional.of(minimumStack.peek());
        }
    }
}