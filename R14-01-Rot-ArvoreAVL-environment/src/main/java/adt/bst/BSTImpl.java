package adt.bst;

import java.util.ArrayList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return this.height2(this.root, -1);
	}
	
	private int height2(BSTNode<T> node, int height) {

		if (!node.isEmpty()) {
			int left = height2((BSTNode<T>) node.getLeft(), height + 1);
			int right = height2((BSTNode<T>) node.getRight(), height + 1);

			height = Math.max(left, right);
		}
		return height;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BSTNode<T> search(T element) {
		if(!isEmpty()) {
			return search2(this.root, element);
		}
		else {
			return new BSTNode.Builder().build();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private BSTNode<T> search2(BSTNode<T> node, T element) {

		BSTNode<T> result;

		if (node.isEmpty())
			result = new BSTNode.Builder().build();

		else if (element.compareTo(node.getData()) == 0)
			result = node;
		else if (element.compareTo(node.getData()) > 0)
			result = search2((BSTNode<T>) node.getRight(), element);
		else
			result = search2((BSTNode<T>) node.getLeft(), element);

		return result;
	}

	@Override
	public void insert(T element) {
		insert2(this.root, element);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void insert2(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder().parent(node).build());
			node.setRight(new BSTNode.Builder().parent(node).build());
		} else {
			if (element.compareTo(node.getData()) > 0)
				insert2((BSTNode<T>) node.getRight(), element);

			else if (element.compareTo(node.getData()) < 0)
				insert2((BSTNode<T>) node.getLeft(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if(isEmpty()) {
			return null;
		}
		else {
			return maximum2(this.root);
		}
	}
	
	private BSTNode<T> maximum2(BSTNode<T> node) {
		if (!node.getRight().isEmpty()) {
			return maximum2((BSTNode<T>) node.getRight());
		} else
			return node;
	}

	@Override
	public BSTNode<T> minimum() {
		if(isEmpty()) {
			return null;
		}
		else {
			return minimum2(this.root);
		}
	}
	
	private BSTNode<T> minimum2(BSTNode<T> node) {
		if (!node.getLeft().isEmpty()) {
			return minimum2((BSTNode<T>) node.getLeft());
		} else
			return node;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty())
				return minimum2((BSTNode<T>) node.getRight());
			else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while (parent != null && parent.getData().compareTo(node.getData()) < 0) {
					node = parent;
					parent = (BSTNode<T>) node.getParent();
				}
				return parent;
			}
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty())
				return maximum2((BSTNode<T>) node.getLeft());
			else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while (parent != null && parent.getData().compareTo(node.getData()) > 0) {
					node = parent;
					parent = (BSTNode<T>) node.getParent();
				}
				return parent;
			}
		}
		return null;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);

			} else if (oneChild(node)) {
				if (node.getParent() != null) {
					if (!node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}

					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
			} else {
				T sucessorNode = sucessor(node.getData()).getData();
				remove(sucessorNode);
				node.setData(sucessorNode);
			}
		}
	}
	
	private boolean oneChild(BSTNode<T> node) {

		return ((node.getRight().isEmpty() && !node.getLeft().isEmpty()
				|| node.getLeft().isEmpty() && !node.getRight().isEmpty()));
	}

	@Override
	public T[] preOrder() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			preOrder2(this.root, aux);

			aux.toArray(result);
		}
		return result;
	}
	
	private void preOrder2(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			array.add(node.getData());
			preOrder2((BSTNode<T>) node.getLeft(), array);
			preOrder2((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] order() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			Order2(this.root, aux);

			aux.toArray(result);
		}
		return result;
	}
	
	private void Order2(BSTNode<T> node, List<T> array) {

		if (!node.isEmpty()) {
			Order2((BSTNode<T>) node.getLeft(), array);
			array.add(node.getData());
			Order2((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] postOrder() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			postOrder2(this.root, aux);

			aux.toArray(result);
		}
		return result;
	}
	
	private void postOrder2(BSTNode<T> node, List<T> array) {

		if (!node.isEmpty()) {
			postOrder2((BSTNode<T>) node.getLeft(), array);
			postOrder2((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
