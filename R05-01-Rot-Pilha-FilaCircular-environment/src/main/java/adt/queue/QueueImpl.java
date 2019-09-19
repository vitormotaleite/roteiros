package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if(isEmpty()) {
			return null;
		}
		else {
			return this.array[0];
		}
	}

	@Override
	public boolean isEmpty() {
		boolean retorno = false;
		if(tail < 0) {
			retorno = true;
		}
		return retorno;
	}

	@Override
	public boolean isFull() {
		boolean retorno = false;
		if(tail == array.length - 1) {
			retorno = true;
		}
		return retorno;
	}

	private void shiftLeft() {
		int i = 1;
		while (this.array[i] != null) {
			this.array[i - 1] = this.array[i];
			i += 1;
		}
		this.tail -= 1;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}
		
		if (element != null) {
			this.tail += 1;
			this.array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		else {
			T saida = this.array[0];
			this.array[0] = null;
			shiftLeft();
			return saida;
		}
	}
}
