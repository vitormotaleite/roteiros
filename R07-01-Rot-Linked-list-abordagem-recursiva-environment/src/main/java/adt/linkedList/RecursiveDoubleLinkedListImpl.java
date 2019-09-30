package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}
	
	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next, 
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data,next);
		this.previous = previous;
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				RecursiveDoubleLinkedListImpl<T> tmp = new RecursiveDoubleLinkedListImpl<T>();
				tmp.previous = this;
				this.next = tmp;
				this.data = element;
			} else {
				this.getNext().insert(element);
			}
		}
	}
	
	@Override
	public void insertFirst(T element) {
		RecursiveDoubleLinkedListImpl<T> old = new RecursiveDoubleLinkedListImpl<>();
		old.data = this.data;
		old.next = this.next;
		old.previous = this;
		this.data = element;
		this.next = old;
	}

	@Override
	public void removeFirst() {
		if(!isEmpty()) {
			this.data = this.next.getData();
			this.next = this.next.next;
			this.previous = new RecursiveDoubleLinkedListImpl<>();
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.getNext().isEmpty()) {
				this.next = null;
				this.data = null;
				this.previous = null;
			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
