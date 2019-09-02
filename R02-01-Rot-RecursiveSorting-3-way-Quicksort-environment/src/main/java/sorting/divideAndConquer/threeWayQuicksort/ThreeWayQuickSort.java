package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot 
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot. 
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente. 
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		boolean validacao = validacaoDeEntrada(array, leftIndex, rightIndex);

		if (validacao) {

			int comeco = leftIndex;
			int fim = rightIndex;
			int index = leftIndex + 1;

			int pivot = leftIndex;
			T valorPivot = array[pivot];

			while (index <= fim) {

				if (array[index].compareTo(valorPivot) < 0) {
					util.Util.swap(array, index, comeco);
					comeco++;
					index++;
				} else if (valorPivot.compareTo(array[index]) < 0) {
					util.Util.swap(array, index, fim);
					fim--;
				} else {
					index++;
				}
			}

			sort(array, leftIndex, comeco - 1);
			sort(array, fim + 1, rightIndex);
		}

	}

	private boolean validacaoDeEntrada(T[] array, int leftIndex, int rightIndex) {

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
