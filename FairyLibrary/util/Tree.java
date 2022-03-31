package com._31536000.util;


import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public interface Tree<E> extends Collection<E>{
	public Comparator<? super E> comparator();
	public Iterator<E> descendingIterator();
	public E first();
	public E last();
	public E higher(E e);
	public E ceiling(E e);
	public E floor(E e);
	public E lower(E e);
	public E get(int i);
	public int indexOf(E e);
	public int lastIndexOf(E e);
	public E remove(int i);
	public E[] toArray();
}
