package com._31536000.util;

public interface SortedTree<E> extends java.util.SortedSet<E>, java.util.Queue<E>{
	@Override
	public java.util.Comparator<? super E> comparator();
	public java.util.Iterator<E> descendingIterator();
	@Override
	public E first();
	@Override
	public E last();
	public E higher(E e);
	public E ceiling(E e);
	public E floor(E e);
	public E lower(E e);
	public E get(int i);
	public int indexOf(E e);
	public int lastIndexOf(E e);
	public E remove(int i);
	public SortedTree<E> headTree(E toElement, boolean inclusive);
	public SortedTree<E> tailTree(E toElement, boolean inclusive);
	public SortedTree<E> subTree(E fromElement, E toElement);
	public E binarySearch(java.util.function.Predicate<E> predicate);
}
