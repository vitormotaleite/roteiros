package adt.heap.extended;

import java.util.List;

/**
 * Extensao de heap (de inteiros) para fazer o merge ordenado de diversos arrays e para 
 * somar numeros da heap compreendidos entre duas estatisticas de ordem.
 *  
 *  Informacao necessaria apra uso da classe PriorityQueue
 *  - peek() - retorna o elemento do root sem remove-lo
 *  - poll() - retorna o elemento do root e remove-o da heap
 *  - add(ele) - acrescenta um novo elemento na Heap
 *   
 * @author Adalberto
 *
 */
public interface HeapMerge{
	/**
	 * Faz o merge dos arrays contidos na lista de arrays recebida como parametro.
	 * Os arrays dentro da lista podem estar desordenados. A saida deve estar ordenada
	 * em ordem ascendente. 
	 * 
	 * Restricoes:
	 * - Voce pode assumir que os elementos dos arrays sao diferentes
	 * - Voce pode usar apenas memoria extra em O(n) - tamanho do array interno da heap.
	 * - Voce pode usar apenas os metodos add() e poll() da heap.
	 * 
	 * @param arrays uma lista de arrays de inteiros.
	 * @return
	 */
	public Integer[] mergeArraysAndOrder(List<Integer[]> arrays);
	
	/**
	 * Retorna a soma dos elementos compreendidos entre a k1-esima estatistica de 
	 * ordem (exclusive) e k2-esima estatistica de ordem (exclusive) na heap.
	 * Exemplo: suponha uma min heap onde foram previsamente inseridos os 
	 * elementos [1,3,5,7,9,11,13,15,17]
	 * Seja k1 = 3 (o terceiro menor elemento eh 5)
	 * Seja k2 = 8 (o sexto menor elemento eh 15)
	 * A soma seria entao: 7+9+11+13 = 40
	 * 
	 * Restricoes:
	 * - Voce nao pode usar memoria extra.
	 * - Voce pode usar apenas o método poll() da heap.
	 * 
	 * @param k1  o k1-esimo menor elemento
	 * @param k2  o k2-esimo menor elemento
	 * @return
	 */
	public int sumRange(int k1, int k2);
}
