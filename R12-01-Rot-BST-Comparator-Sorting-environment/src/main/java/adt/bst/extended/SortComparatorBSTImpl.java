package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		while (!this.isEmpty())
			this.remove(this.root.getData());

		for (T element : array) {
			this.insert(element);
		}

		return this.order();
	}
	

	@Override
	public T[] reverseOrder() {
		@SuppressWarnings("unchecked")
		T[] arrayResult = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			reverseOrder2(this.root, aux);

			aux.toArray(arrayResult);
		}
		return arrayResult;
	}
	
	private void reverseOrder2(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			reverseOrder2((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
			reverseOrder2((BSTNode<T>) node.getLeft(), array);
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
