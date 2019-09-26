package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		if(this.data == null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int size() {
		if(isEmpty()) {
			return 0;
		}
		else {
			return 1 + this.next.size();
		}
	}

	@Override
	public T search(T element) {
		if(isEmpty()) {
			return null;
		}
		else {
			if(this.data == element) {
				return data;
			}
			else {
				return this.next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if(isEmpty()) {
			this.data = element;
			this.next = new RecursiveSingleLinkedListImpl<>();
		}
		else {
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(isEmpty()) {
			
		}
		else {
			if(this.data == element) {
				this.data = this.next.getData();
				this.next = next.next;
			}
			else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size()];
		transformaArray(array,this,0);
		return array;
	}
	
	private Object transformaArray(T[] array, RecursiveSingleLinkedListImpl<T> node, int index) {
		if(node.isEmpty()) {
			return null;
		}
		else {
			array[index] = node.getData();
			return transformaArray(array, node.next, index + 1);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
