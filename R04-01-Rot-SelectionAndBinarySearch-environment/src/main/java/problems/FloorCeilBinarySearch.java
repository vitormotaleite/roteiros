package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {
	
	@Override
	public Integer floor(Integer[] array, Integer x) {
		
		Integer floor = null;
		int inicio,fim;
		int meio; 
		inicio = 0;
		fim = array.length - 1;
		 
		while(inicio <= fim){
		  
			meio = (inicio + fim) / 2;
		  
			if(array[meio] == x) {
				return array[meio];
			}
			else if(array[meio] > x){
				fim = meio - 1;
			}
			else {
				floor = array[meio];
				inicio = meio + 1;
			}
		}
		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		
		Integer ceil = null;
		int inicio,fim;
		int meio;
		inicio = 0;
		fim = array.length - 1;
		 
		while(inicio <= fim){
		  
			meio = (inicio + fim) / 2;
		  
			if(array[meio] == x) {
				return array[meio];
			}
			else if(array[meio] > x){
				ceil = array[meio];
				fim = meio - 1;
			}
			else {
				inicio = meio + 1;
			}
		}
		return ceil;
	}
}