package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		if(!isEmpty()) {
			SingleLinkedListNode<T> aux = head;
			while(!aux.isNIL()) {
				remove(aux.getData(), aux.getNext(), aux);
				aux = aux.getNext();
			}
		}
	}
	
	private void remove(T element, SingleLinkedListNode<T> node, SingleLinkedListNode<T> anterior) {
		if(!node.isNIL()) {
			if(node.getData().equals(element)) {
				anterior.setNext(node.getNext());
				removeDuplicates();
			} else {
				remove(element, node.getNext(), node);
			}
		}
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		if(!this.isEmpty() ) {
			SingleLinkedListImpl<T> list = (SingleLinkedListImpl<T>) otherSet;
		    SingleLinkedListNode<T> aux = list.getHead();
		    while(!aux.isNIL()) {
		        if(this.search(aux.getData()) == null) {
		            this.insert(aux.getData());
                }
                aux = aux.getNext();
            }
        }
        return this;
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
SetLinkedList<T> aux = new SetLinkedListImpl<>();
        
        if(!this.isEmpty() && !otherSet.isEmpty()){
			SingleLinkedListImpl<T> list = (SingleLinkedListImpl<T>) otherSet;
		    SingleLinkedListNode<T> auxHead = list.getHead();
            while(!auxHead.isNIL()){
                if(this.search(auxHead.getData()) != null){
                    aux.insert(auxHead.getData());
                }
                auxHead = auxHead.getNext();
            }
        }
        return aux;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
	    SingleLinkedListNode<T> aux = head.getNext();
	    SingleLinkedListNode<T> anterior = head;
	    
	    while(!aux.isNIL()) {
	    	anterior = aux;
	    	aux = aux.getNext();
	    }
		SingleLinkedListImpl<T> list = (SingleLinkedListImpl<T>) otherSet;
	    SingleLinkedListNode<T> node = list.getHead();
	    anterior.setNext(node);
	    
	    this.removeDuplicates();

	}
}