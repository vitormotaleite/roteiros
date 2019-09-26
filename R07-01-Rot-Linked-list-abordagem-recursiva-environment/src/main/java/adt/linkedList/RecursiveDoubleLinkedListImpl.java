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
		if(isEmpty()) {
			this.data = element;
			RecursiveDoubleLinkedListImpl<T> nil = new RecursiveDoubleLinkedListImpl<>();
			nil.previous = this;
			this.next = nil;
		}
		if(this.previous == null) {
			this.previous = new RecursiveDoubleLinkedListImpl<>();
		}
		else {
			this.next.insert(element);
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
	public void remove(T element) {
		if(isEmpty()) {
			return;
		}
		else {
			if(this.data == element) {
				if(this.previous.isEmpty() && this.next.isEmpty()) {
					this.data = null;
					this.next = null;
					this.previous = null;
				}
				else {
					data = next.getData();
					next = next.next;
					if(next != null) {
						((RecursiveDoubleLinkedListImpl<T>)this.next).previous = null;
					}
				}
			}
			else {
				next.remove(element);
			}
		}
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
		if(!isEmpty()) {
			if(this.next.isEmpty()) {
				this.previous.next = new RecursiveDoubleLinkedListImpl<>();
			}
			else {
				((RecursiveDoubleLinkedListImpl<T>)this.next).removeLast();
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
