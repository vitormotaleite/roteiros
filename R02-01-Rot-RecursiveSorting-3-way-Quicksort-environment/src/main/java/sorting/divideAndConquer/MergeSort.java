package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		boolean validacao = checagemDeEntrada(array, leftIndex, rightIndex);

		if (validacao) {
			int meio = (rightIndex + leftIndex) / 2;

			sort(array, leftIndex, meio);
			sort(array, meio + 1, rightIndex);

			merge(array, leftIndex, rightIndex, meio);
		}

	}

	private void merge(T[] array, int leftIndex, int rightIndex, int middleIndex) {

		T[] arrayAux = Arrays.copyOf(array, array.length);

		int comeco = leftIndex;
		int meio = middleIndex + 1;
		int primeiro = leftIndex;

		while (comeco <= middleIndex && meio <= rightIndex) {

			if (arrayAux[meio].compareTo(arrayAux[comeco]) > 0) {
				array[primeiro] = arrayAux[comeco];
				comeco++;
			} else {
				array[primeiro] = arrayAux[meio];
				meio++;
			}
			primeiro++;
		}

		while (comeco <= middleIndex) {
			array[primeiro] = arrayAux[comeco];
			comeco++;
			primeiro++;
		}

		while (meio <= rightIndex) {
			array[primeiro] = arrayAux[meio];
			meio++;
			primeiro++;
		}

	}

	private boolean checagemDeEntrada(T[] array, int leftIndex, int rightIndex) {

		if (array == null)
			return false;
		if (array.length == 0)
			return false;
		if (leftIndex < 0 || rightIndex < 0)
			return false;
		if (leftIndex >= rightIndex)
			return false;
		if (rightIndex >= array.length)
			return false;

		return true;
	}
}
