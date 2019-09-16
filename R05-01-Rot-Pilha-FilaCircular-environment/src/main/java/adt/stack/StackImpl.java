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
		if(isEmpty()) {
			return null;
		}
		else {
			return array[top];	
		}
	}

	@Override
	public boolean isEmpty() {
		boolean retorno = false;
		if (top < 0) {
			retorno = true;
		}
		return retorno;
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
