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
		boolean retorno = false;
		if(top == array.length - 1) {
			retorno = true;
		}
		return retorno;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull()) {
			throw new StackOverflowException();
		}
		else if(element != null){
			top += 1;
			this.array[top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		else {
			return this.array[top--];
		}
	}

}
