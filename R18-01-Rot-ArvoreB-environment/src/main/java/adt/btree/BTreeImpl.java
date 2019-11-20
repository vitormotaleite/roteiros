package adt.btree;

import java.util.LinkedList;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {
		if (node == null || node.isEmpty()) {
			return -1;
		}
		
		if (node.isLeaf()) {
			return 0;
		}
		
		return 1 + height(node.getChildren().get(0));
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		LinkedList<BNode<T>> list = new LinkedList<BNode<T>>();
		depthLeftOrder2(list, root);
		@SuppressWarnings("unchecked")
		BNode<T>[] arrayAux = (BNode<T>[]) new BNode[list.size()];
		return list.toArray(arrayAux);
	}
	
	private void depthLeftOrder2(LinkedList<BNode<T>> listAux, BNode<T> node) {
		listAux.add(node);
		for (BNode<T> e : node.getChildren()) {
			depthLeftOrder2(listAux, e);
		}
	}

	@Override
	public int size() {
		return size2(root);
	}
	
	private int size2(BNode<T> node) {
		int result = node.size();
		for (BNode<T> e : node.getChildren()) {
			result += size2(e);
		}
		return result;
	}

	@Override
	public BNodePosition<T> search(T element) {
		return search2(root, element);
	}
	
	private BNodePosition<T> search2(BNode<T> node, T element) {
		BNodePosition<T> result = new BNodePosition<T>();
		int indexOf = node.getElements().indexOf(element);
		if (indexOf != -1) {
			result = new BNodePosition<T>(node, indexOf);
		} else {
			for (BNode<T> e : node.getChildren()) {
				BNodePosition<T> aux = search2(e, element);
				if (aux.node != null) {
					result = aux;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null && this.search(element).node == null) {
			insert2(this.root, element);
		}
	}
	
	private void insert2(BNode<T> node, T element) {
		if (node.isFull()) {
			split(node);
			if (node.getParent() != null)
				node = node.getParent();
		}
		if (node.getChildren().isEmpty()) {
			node.addElement(element);
		} else {
			int i;
			for (i = 0; i < node.size(); i++) {
				T e = node.getElements().get(i);
				if (e.compareTo(element) > 0) {
					insert2(node.getChildren().get(i), element);
					break;
				}
			}
			if (i == node.size()) {
				while (node.getChildren().size() <= i) {
					BNode<T> nNode = new BNode<T>(order);
					nNode.setParent(node);
					node.getChildren().add(nNode);
				}
				insert2(node.getChildren().get(i), element);
			}
		}
	}

	private void split(BNode<T> node) {
		int midIndex = node.getElements().size()/2;
		BNode<T> left = new BNode<>(getOrder());
		BNode<T> right = new BNode<>(getOrder());
		
		for (int i = 0; i < node.size(); i++) {
			if (i < midIndex) {
				left.getElements().add(node.getElementAt(i));
				
			} else if (i > midIndex) {
				right.getElements().add(node.getElementAt(i));
			}
		}
		
		T middle = node.getElementAt(midIndex);
		
		
		if (!node.isLeaf()) {
			LinkedList<BNode<T>> child = node.getChildren();
			
			if (child.size() > 0) {
				int childIndex = 0;
				
				for (int i = 0; i < child.size(); i++) {
					if (i <= midIndex) {
						left.addChild(i, child.get(i));
						
					} else {
						right.addChild(childIndex++, child.get(i));
					}
				}
			}
		}
		
		if (node.equals(getRoot())) {
			BNode<T> newRoot = new BNode<>(getOrder());
			newRoot.addElement(middle);
			node.setParent(newRoot);
			setRoot(newRoot);
			
			newRoot.addChild(0, left);
			newRoot.addChild(1, right);
			newRoot.getChildren().get(0).setParent(newRoot);
			newRoot.getChildren().get(1).setParent(newRoot);
			
		} else {
			node.addChild(0, left);
			node.addChild(1, right);
			promote(node);
		}
	}

	private void promote(BNode<T> node) {
		int midIndex = node.getElements().size()/2;
		T middle = node.getElementAt(midIndex);
		
		node.getElements().clear();
		node.addElement(middle);
		
		BNode<T> parent = node.getParent();
		
		if (parent != null || !parent.isEmpty()) {
			node.getChildren().get(0).setParent(parent);
			node.getChildren().get(1).setParent(parent);
			
			int index = parent.getChildren().indexOf(node);
			
			parent.addElement(middle);
			parent.addChild(index, node.getChildren().get(0));
			parent.addChild(index++, node.getChildren().get(1));
			
			node.getChildren().get(0).setParent(parent);
			node.getChildren().get(1).setParent(parent);
			
			parent.getChildren().remove(node);
		}
	}
	
	public int getOrder() {
		return order;
	}
	
	private void setRoot(BNode<T> root) {
		this.root = root;
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
