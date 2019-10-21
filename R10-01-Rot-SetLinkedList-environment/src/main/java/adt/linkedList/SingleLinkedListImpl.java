package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		if(this.head.isNIL()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = this.head;
		
		while(!aux.isNIL()) {
			size++;
			aux = aux.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		T retorno = null;
		if(!isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			while(!aux.isNIL()) {
				if(aux.getData().equals(element)) {
					retorno = aux.getData();
				}
				aux = aux.getNext();
			}
		}
		return retorno;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> aux = this.head;
		if(head.isNIL()) {
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<>(element,new SingleLinkedListNode<>());
			newHead.next = head;
			head = newHead;
		}
		else {
			while(!aux.next.isNIL()) {
				aux = aux.next;
			}
			
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(element,new SingleLinkedListNode<>());
			newNode.next = aux.next;
			aux.next = newNode;
		}	
	}

	@Override
	public void remove(T element) {
		if(head.getData().equals(element)) {
			head = head.next;
		}
		else {
			SingleLinkedListNode<T> aux = head;
			SingleLinkedListNode<T> previous = null;
			while((!aux.isNIL()) && (!aux.getData().equals(element))) {
				previous = aux;
				aux = aux.next;
			}
			if(!aux.isNIL()) {
				previous.next = aux.next;
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] retorno = (T[]) new Object[size()];
		SingleLinkedListNode<T> aux = head;
		int contador = 0;
		while(!aux.isNIL()) {
			retorno[contador] = aux.getData();
			aux = aux.next;
			contador++;
		}
		return retorno;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
