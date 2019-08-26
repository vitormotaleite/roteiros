package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for(int i = leftIndex; i <= rightIndex; i++){
	        for(int j = leftIndex + 1; j <= rightIndex; j++){
	            if(array[i].compareTo(array[j]) > 0){
	            	util.Util.swap(array, i, j);
	            }
	        }
	    }
	}
}
