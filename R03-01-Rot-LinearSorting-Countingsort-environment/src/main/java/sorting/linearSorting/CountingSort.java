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
		
		Integer[] arrayOrdenado = new Integer[array.length];
		
		for(int l = 0; l < arrayOrdenado.length; l++) {
			arrayOrdenado[l] = 0;
		}
		
		int maior = Integer.MAX_VALUE;
		int menor = Integer.MIN_VALUE;
		
		for (int j = leftIndex; j < rightIndex; j++) {
			if (array[j] > maior) {
				maior = array[j];
			}
			if (array[j] < menor) {
				menor = array[j];
			}
		}
		Integer[] array2 = new Integer[(maior - menor) + 1];
		
		for(int i = 0; i < array2.length; i++) {
			array2[i] = 0;
		}
		
		for(int k = leftIndex; k < rightIndex; k++) {
			array2[array[k]] += 1 ;
		}
		
		for(int z = 0; z < array2.length; z++) {
			array2[z + 1] += array2[z];
		}
		
		for(int x = rightIndex; x > leftIndex; x--) {
			array2[array[x]] -= 1;
			arrayOrdenado[array2[array[x]]] = array[x];
		}
	}

}
