package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}
	
	private int calculaPosicao(T element, int probe) {
		return ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
	}

	@Override
	public void insert(T element) {
		if (element != null && search(element) == null) {

			if (this.isFull()) {
				throw new HashtableOverflowException();
			} else {
				int probe = 0;

				int posicao = calculaPosicao(element, probe);

				while (this.table[posicao] != null && !this.table[posicao].equals(new DELETED())) {
					probe++;
					this.COLLISIONS++;
					posicao = calculaPosicao(element, probe);
				}

				this.table[posicao] = element;
				this.elements++;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int index = indexOf(element);
			if (index != -1) {
				this.table[index] = new DELETED();
				elements--;
			}

		}
	}

	@Override
	public T search(T element) {
		T saida = null;
		if (element != null && indexOf(element) != -1) {
			saida = element;

		}

		return saida;
	}

	@Override
	public int indexOf(T element) {
		if (element != null && !this.isEmpty()) {
			int probe = 0;
			int posicao = calculaPosicao(element, probe);

			while (this.table[posicao] != null && probe < this.capacity()) {
				if (this.table[posicao].equals(element)) {
					return posicao;
				} else {
					probe++;
					posicao = calculaPosicao(element, probe);

				}
			}

		}
		return -1;
	}
}
