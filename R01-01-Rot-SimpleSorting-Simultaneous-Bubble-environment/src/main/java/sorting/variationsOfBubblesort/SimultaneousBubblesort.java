package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */
public class SimultaneousBubblesort<T extends Comparable<T>> extends
		AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int comeco = leftIndex;
		int fim = rightIndex;
		
		if (comeco < rightIndex) {
			if(array[comeco].compareTo(array[comeco + 1]) > 0) {
				util.Util.swap(array, comeco, comeco + 1);
			}
			comeco++;
			sort(array, comeco, rightIndex);
		}
		if(fim > leftIndex) {
			if(array[fim].compareTo(array[fim - 1]) < 0) {
				util.Util.swap(array, fim, fim - 1);
			}
			fim--;
			sort(array, leftIndex, fim);
		}
	}
}
