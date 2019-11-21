package adt.heap.extended;


import java.util.List;
import java.util.PriorityQueue;

public class HeapMergeImpl extends PriorityQueue<Integer> implements HeapMerge {

	@Override
	public Integer[] mergeArraysAndOrder(List<Integer[]> arrays) {
		return null;
		// nao entendi como fazer essa questao sem usar size.
	}

	@Override
	public int sumRange(int k1, int k2) {
		int soma = 0;
		if(k2 - k1 == 1) {
			return 0;
		} else {
			for(int i = k1; i < k2 -1; i++) {
				soma += k1 + k2;
			}
			return soma;
		}
		// questao impossivel, sem memoria extra nao tem como observar o array nem a fila.
	}
	
	// me sentindo bem ignorante agora.
	// sempre tem o proximo periodo.
	// se tiver algum monitor lendo isso, gostaria muito de saber como foi que voce foi aprovado
	// em LEDA e EDA (ou que materiais utilizou para estudar).
	// periodo que vem, tento fazer essa prova de novo.
}
