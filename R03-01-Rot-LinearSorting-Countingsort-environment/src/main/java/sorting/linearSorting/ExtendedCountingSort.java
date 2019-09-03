package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if (leftIndex < rightIndex && array != null && leftIndex >= 0 && rightIndex < array.length && rightIndex > 0) {	
			int menor = array[leftIndex];
			int maior = array[leftIndex];
			
			for (int i = leftIndex; i < rightIndex + 1; i++) {
				if (array[i] > maior) {
					maior = array[i];
				}
				if (array[i] < menor) {
					menor = array[i];
				}
			}
			Integer[] arrayTemporario = new Integer[maior - menor + 1];
	
			for (int j = 0; j < arrayTemporario.length; j++) {
				arrayTemporario[j] = 0;
			}
			
			for(int z = leftIndex; z < rightIndex + 1; z++) {
				arrayTemporario[array[z] - menor] += 1;
			}
			
			int x = leftIndex;
			int y = leftIndex;
			
			while (x < arrayTemporario.length) {
				while (arrayTemporario[x] != 0) {
					array[y] = x + menor;
					y += 1;
					arrayTemporario[x] -= 1;
				}
				x += 1;
			}	
		}
	}
}
