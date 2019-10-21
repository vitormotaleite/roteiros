package adt.stack;

import adt.linkedList.SingleLinkedListNode;

/**
 * Classe que representa um apilha implementada usando-se um noh de uma lista
 * simplesmente ligada, como estrutura sobrejacente. 
 * 
 * Restricoes:
 * - voce DEVE obedecer a politica de acesso e complexidade dos metodos de pilha, ou seja, todos em O(1).
 * - voce NÃO pode usar memoria extra (estrutura auxiliar)
 * - qualquer metodo auxiliar que voce necessite DEVE ser implementado nesta classe
 * - voce NÃO pode modificar a classe SingleLinkedListNode
 * 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class StackSingleLinkedListNodeImpl<T> implements Stack<T> {
	
	private SingleLinkedListNode<T> top;

	/**
	 * A pilha para ser criada precisa ter um tamanho maximo
	 * @param tamanhoMaximo
	 */
	public StackSingleLinkedListNodeImpl(int tamanhoMaximo) {
		this.top = new SingleLinkedListNode<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull()) {
			throw new StackOverflowException();
		}
		this.top.getNext().setData(element);
		this.top = this.top.getNext();
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		T aux = this.top.getData();
		this.top.setData(null);
		return aux;
		
	}

	@Override
	public T top() {
		if(isEmpty()) {
			return null;
		}
		else {
			return this.top.getData();
		}
	}

	@Override
	public boolean isEmpty() {
		if(this.top.isNIL()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if(!this.top.isNIL() && !this.top.getNext().isNIL()) {
			return true;
		}
		else {
			return false;
		}
	}

}
