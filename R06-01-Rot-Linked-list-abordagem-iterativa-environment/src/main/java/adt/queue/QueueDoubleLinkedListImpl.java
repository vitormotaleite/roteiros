package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} else {
			list.insert(element);
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			T retorno = ((DoubleLinkedListImpl<T>) list).getHead().getData();
			list.removeFirst();
			return retorno;
		}
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		} else {
			return ((DoubleLinkedListImpl<T>) list).getHead().getData();
		}
	}

	@Override
	public boolean isEmpty() {
		boolean retorno = false;
		if (list.size() == 0) {
			retorno = true;
		}
		return retorno;
	}

	@Override
	public boolean isFull() {
		boolean retorno = false;
		if (list.size() == this.size) {
			retorno = true;
		}
		return retorno;
	}

}
