package com.carlosribeiro;

import java.util.*;
public class ListaDeObjetos<E> extends ArrayList<E>
{	
	private static final long serialVersionUID = 1L;
	private int corrente = -1;

	public ListaDeObjetos (int tam)
	{	super(tam);
	}

	public boolean remover(int numero)
	{		
		int n = localizar(numero);
		if (n!=-1) {
			this.remove(n);
			return true;
		}
		else {
			return false;
		}
		
	}
	public E recuperar(int numero)
	{		
		int n = localizar(numero);
		if (n!=-1) {
			return this.get(n);
		}
		else {
			return null;
		}
			
	}
	private int localizar(int id)
	{		
		int i;
		for (i = 0; i < this.size(); i++)
		{	
			E umObj = this.get(i);
			if (umObj.equals(id))
			{	return i;
			}
		}
		return -1;
	}
	public E recuperarPrimeiro()
	{		
		if (!isEmpty()){
			corrente = 0;
			return this.get(corrente);
		}
		else {
			return null;
		}
	}
	public E recuperarProximo()
	{		
		if (corrente==-1){
			throw new ArrayIndexOutOfBoundsException(
				"Foi executado o método recuperarProximo sem ter sido chamado o recuperarPrimeiro");
		}
		else {
			corrente++;
			if (corrente<this.size()){
				return this.get(corrente);
			}
			else {
				corrente=-1;
				return null;
			}
		}
	}
}