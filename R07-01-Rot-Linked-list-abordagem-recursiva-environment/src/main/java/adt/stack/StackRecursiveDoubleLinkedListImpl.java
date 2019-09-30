package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if (this.size >= 0 && !isFull()) {
				((RecursiveDoubleLinkedListImpl<T>) this.top).insert(element);
			} else {
				throw new StackOverflowException();
			}
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T pop = null;
		if (this.size >= 0 && !this.top.isEmpty()) {
			pop = top();
			top.removeLast();
		} else {
			throw new StackUnderflowException();
		}
		if (pop == null) {
			throw new StackUnderflowException();
		}
		return pop;
	}

	@Override
	public T top() {
		if (this.size >= 0 && this.top.isEmpty()) {
			return null;
		}
		return top((RecursiveDoubleLinkedListImpl<T>) this.top);
	}

	public T top(RecursiveDoubleLinkedListImpl<T> tmp) {
		if (tmp.getNext().isEmpty()) {
			return tmp.getData();
		}
		return top((RecursiveDoubleLinkedListImpl<T>) tmp.getNext());
	}

	@Override
	public boolean isEmpty() {
		return ((RecursiveDoubleLinkedListImpl<T>) this.top).isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.size == ((RecursiveDoubleLinkedListImpl<T>) this.top).size();
	}

}
