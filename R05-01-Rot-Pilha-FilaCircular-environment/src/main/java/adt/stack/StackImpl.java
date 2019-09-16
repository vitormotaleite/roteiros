package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if(array != null && array.length > 0) {
			for(int i = 0; i < array.length; i++) {
				if((array[i]).equals(top)) {
					return array[i];
				}
			}
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		if (array == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		int tamanho = 0;
		for (int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				tamanho += 1;
			}
		}
		if (tamanho == array.length) {
			return true;
		}
		return false;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T pop() throws StackUnderflowException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
