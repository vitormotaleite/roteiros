package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode<T>(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode<T>(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		@SuppressWarnings("unchecked")
		SkipListNode<T> [] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> x = this.root;
		for(int i = this.maxHeight - 1; i >= 0; i--) {
			while(x.forward[i].getKey() < key) {
				x = x.forward[i];
			}
			update[i] = x;
		}
		x = x.forward[0];
		
		if(x.getKey() == key) {
			x.setValue(newValue);
		}
		else {
			x = new SkipListNode<T>(key, height, newValue);
			for(int i = 0; i < height; i++) {
				x.forward[i] = update[i].forward[i];
				update[i].forward[i] = x;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T> [] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> x = this.root;
		for(int i = this.maxHeight - 1; i >= 0; i--) {
			while(x.forward[i].getKey() < key) {
				x = x.forward[i];
			}
			update[i] = x;
		}
		x = x.forward[0];
		
		if (x.getKey() == key) {
			for(int i = 0; i < this.maxHeight; i++) {
				if(update[i].forward[i] != x) {
					break;
				}
				update[i].forward[i] = x.forward[i];
			}
		}
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> x = this.root;
		for(int i = this.maxHeight; i > 0; i--) {
			while(x.getForward(i).getKey() < key) {
				x = x.getForward(i);
			}
			x = x.getForward(1);
		}
		if(x.getKey() == key) {
			return (SkipListNode<T>) x.getValue();
		}
		else {
			return null;
		}
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public SkipListNode<T>[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
