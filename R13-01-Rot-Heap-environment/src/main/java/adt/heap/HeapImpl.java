package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

   protected T[] heap;
   protected int index = -1;
   /**
    * O comparador é utilizado para fazer as comparações da heap. O ideal é
    * mudar apenas o comparator e mandar reordenar a heap usando esse
    * comparator. Assim os metodos da heap não precisam saber se vai funcionar
    * como max-heap ou min-heap.
    */
   protected Comparator<T> comparator;

   private static final int INITIAL_SIZE = 20;
   private static final int INCREASING_FACTOR = 10;

   /**
    * Construtor da classe. Note que de inicio a heap funciona como uma
    * min-heap.
    */
   @SuppressWarnings("unchecked")
   public HeapImpl(Comparator<T> comparator) {
      this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
      this.comparator = comparator;
   }

   // /////////////////// METODOS IMPLEMENTADOS
   private int parent(int i) {
      return (i - 1) / 2;
   }

   /**
    * Deve retornar o indice que representa o filho a esquerda do elemento
    * indexado pela posicao i no vetor
    */
   private int left(int i) {
      return (i * 2 + 1);
   }

   /**
    * Deve retornar o indice que representa o filho a direita do elemento
    * indexado pela posicao i no vetor
    */
   private int right(int i) {
      return (i * 2 + 1) + 1;
   }

   @Override
   public boolean isEmpty() {
      return (index == -1);
   }

   @SuppressWarnings("unchecked")
@Override
   public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		// for (T elem : this.heap) {
		// resp.add(elem);
		// }
		for (int i = 0; i < this.size(); i++) {
			resp.add(heap[i]);
		}
		return (T[]) resp.toArray(new Comparable[0]);
	}

   // ///////////// METODOS A IMPLEMENTAR
   /**
    * Valida o invariante de uma heap a partir de determinada posicao, que pode
    * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
    * (comparados usando o comparator) elementos na parte de cima da heap.
    */
   private void heapify(int position) {
      heapify2(this.heap, position);
   }
   
   private void heapify2(T[] array, int position) {
	   if (position >= 0) {
			int largest;
			int left = left(position);
			int right = right(position);
			if (left <= this.index && comparator.compare(array[left], array[position]) > 0) {
				largest = left;
			} else {
				largest = position;
			}
			if (right <= this.index && comparator.compare(array[right], array[largest]) > 0) {
				largest = right;
			}
			if (largest != position) {
				Util.swap(array, position, largest);
				heapify2(array, largest);
			}
		}
   }

   @Override
   public void insert(T element) {
      // ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
      if (index == heap.length - 1) {
         heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
      }
      // /////////////////////////////////////////////////////////////////
      insert2(this.heap, element);
   }
   
   private void insert2(T[] array, T element) {
	   this.index += 1;
	   int i = this.size() - 1;
	   while (i > 0 && comparator.compare(array[parent(i)], element) < 0) {
			array[i] = array[parent(i)];
			i = parent(i);
		}
		array[i] = element;
   }

   @Override
   public void buildHeap(T[] array) {
	   this.heap = array;
	   this.index = array.length - 1;
	   for(int i = (array.length - 1) / 2; i >= 0; i--) {
		   heapify(i);
	   }
   }

   @Override
   public T extractRootElement() {
	   if (!this.isEmpty()) {

			T element = this.heap[0];
			this.heap[0] = null;
			Util.swap(this.heap, 0, this.index);
			index--;

			this.heapify(0);
			return element;

		} else
			return null;
   }

   @Override
   public T rootElement() {
	   if (!this.isEmpty()) {
			return this.heap[0];
	   }
	   else {
		   return null;
	   }
   }

   @Override
   public T[] heapsort(T[] array) {
	   Comparator<T> comparatorAux = getComparator();
		this.setComparator((o1, o2) -> o2.compareTo(o1));

		this.buildHeap(array);

		@SuppressWarnings("unchecked")
		T[] arrayAux = (T[]) (new Comparable[this.size()]);

		for (int index = 0; index < arrayAux.length; index++) {
			arrayAux[index] = this.extractRootElement();
		}

		resetHeap(comparatorAux);
		return arrayAux;
   }
   
   private void resetHeap(Comparator<T> comparatorAux) {
		this.setComparator(comparatorAux);
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
	}

   @Override
   public int size() {
	   return this.index + 1;
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }

   public T[] getHeap() {
      return heap;
   }

}
