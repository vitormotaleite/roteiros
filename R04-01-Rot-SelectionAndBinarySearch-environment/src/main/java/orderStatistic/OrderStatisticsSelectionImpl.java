package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no 
	 *   array original
	 * - Nenhum array auxiliar deve ser criado e utilizado.
	 * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   como o selectionsort mas sem modificar nenhuma posicao do array).
	 * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null. 
	 * - Considerar que k varia de 1 a N 
	 * - Sugestao: o uso de recursao ajudara sua codificacao.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
		
		T menor = null;
		
		if (k > array.length) {
			return menor;
		
		} else {
			
			int indexMenor = selectionSort(array, 0, array.length - 1);
			menor = array[indexMenor];
			
			for (int h = 1; h <= k - 1; h++) {
				menor = menorElementoMaiorQue(array, menor);
			}

		}
		return menor;
	}

	private int selectionSort(T[] array, int leftIndex, int rightIndex) { 

		int indexMenor = leftIndex;

		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if (array[j].compareTo(array[indexMenor]) < 0) {
				indexMenor = j;
			}
		}

		return indexMenor;

	}

	private T menorElementoMaiorQue(T[] array, T elemento) {
		
		int diferenca = 99999999;
		
		T indexFinal = array[0];
		
		for (T el : array) {
			if (el.compareTo(elemento) > 0) {
				int novaDiferenca = el.hashCode() - elemento.hashCode();
				if (novaDiferenca < diferenca) {
					diferenca = novaDiferenca;
					indexFinal = el;
				}
			}
		}
		
		return indexFinal;
	}
}
