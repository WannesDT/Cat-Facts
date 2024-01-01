package com.example.catfacts.data

/**
 * Returns a new [List] with the element at the specified [index] replaced by the provided [elem].
 * If the [index] is out of bounds, the original list is returned unchanged.
 *
 * @param index The index at which to update the element.
 * @param elem The new element to be placed at the specified [index].
 * @return A new [List] with the updated element, or the original list if the [index] is out of bounds.
 * @param E The type of elements in the iterable.
 */
fun <E> Iterable<E>.updated(index: Int, elem: E) = mapIndexed { i, existing -> if (i == index) elem else existing }
