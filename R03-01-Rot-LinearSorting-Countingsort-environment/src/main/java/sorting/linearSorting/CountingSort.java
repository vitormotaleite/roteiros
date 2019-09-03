package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		 
		int maior = 0;
		for (int j = leftIndex; j < rightIndex + 1; j++) {
			if (array[j] > maior) {
				maior = array[j];
			}
		}
		Integer[] array2 = new Integer[maior + 1];
			
		for(int i = 0; i < array2.length; i++) {
			array2[i] = 0;
		}
			
		for(int k = 0; k < rightIndex + 1; k++) {
			array2[array[k]] += 1 ;
		}
			
		for(int z = 1; z < array2.length; z++) {
			array2[z] += array2[z - 1];
		}
			
		Integer[] arrayOrdenado = new Integer[array.length];
			
		for(int x = rightIndex; x >= 0; x--) {
			arrayOrdenado[array2[array[x]] - 1] = array[x];
			array2[array[x]] -= 1;
		}
			
		for (int i = 0; i < array.length; i++) {
			array[i] = arrayOrdenado[i];
		}
	}
}
