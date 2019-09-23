package problems;


/**
 * Dado dois arrays ordenados em ordem crescente, encontrar a k-esima estatistica de ordem 
 * da uniao ordenada deles. 
 * 
 * Restricoes:
 * - os arrays nao possuem elementos em comum e nem repetidos
 * - k eh um numero compreendido entre 1 e array1.length + array2.length
 * - caso o k-esima estatistica de ordem nao exista, o metodo deve retornar null
 * - voce nao pode usar memoria extra
 * - seu algoritmo deve ter complexidade O(array1.length + array2.length). 
 * - voce nao pode usar nenhum metodo pronto de manipulacao de arrays, exceto length.
 * 
 * @author adalbertocajueiro
 *
 */
public class OrderStatisticsSortedUnion<T extends Comparable<T>> {
	public T statisticsOrder(T[] array1, T[] array2, int k) {
		
		if((k > 0) && (k <= (array1.length + array2.length) )) {
			
			T[] arrayOrdenado = (T[]) new Integer[array1.length + array2.length];
			int j = 0;
			
			if(array1.length <= array2.length) {
				j = array1.length;
			}
			else if(array2.length <= array1.length) {
				j = array2.length;
			}
			
			for(int i = 0; i < j; i ++) {
				if(array1[i].compareTo(array2[i]) > 0) {
					arrayOrdenado[i] = array2[i];
					arrayOrdenado[i + 1] = array1[i];
				}
				else if(array1[i].compareTo(array2[i]) < 0) {
					arrayOrdenado[i] = array1[i];
					arrayOrdenado[i + 1] = array2[i];
				}
			}
			T retorno = arrayOrdenado[k - 1];
			
			return retorno;
		}
		return null;
	}
}
// nessa situação só a duas coisas para fazer: parar de tentar ou desistir de tudo.
// continuar tentando fazer o impossivel é burrice.
// sabia de nada.
// pensando seriamente se eu vou ter um futuro nessa profissão.