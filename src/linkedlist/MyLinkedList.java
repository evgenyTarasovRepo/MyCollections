package linkedlist;

import java.util.*;

public class MyLinkedList<E> implements List<E> {

    Node<E> first;
    Node<E> last;
    int size = 0;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;

        for (Node<E> currNode = first; currNode != null; currNode = currNode.next) {
            arr[i++] = currNode.item;
        }

        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    public void addFirst(E element) {
        linkFirst(element);
    }

    public void addLast(E element) {
        linkLast(element);
    }

    @Override
    public boolean add(E e) {
//        if (this.size == 0) {
//            first = new Node<>(null, e, null);
//            this.last = first;
//        }
//         else if (this.size == 1) {
//            last = new Node<>(this.first, e, null);
//            this.first = last;
//        }
//        else {
//            Node<E> newItem = new Node<>(this.last, e, null);
//            this.last.next = newItem;
//            this.last = newItem;
//       }
//        size++;
        linkLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> nodeForRemove = findElement(o);
        Node<E> prevItem = nodeForRemove.prev;
        Node<E> nextItem = nodeForRemove.next;

        if (size == 1) {
            this.first = null;
            this.last = null;
            size--;
            return true;
        } else {
            if (nodeForRemove == first) {
                first = nextItem;
                nodeForRemove.prev = null;
                size--;
                return true;
            }
            if (nodeForRemove == last) {
                last = prevItem;
                nodeForRemove.next = null;
                size--;
                return true;
            }
            if (nextItem != null && prevItem != null) {
                prevItem.next = nextItem;
                nextItem.prev = prevItem;
                size--;
                return true;
            }
        }
        nodeForRemove.item = null;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E item : c) {
            add(item);
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        for (E item : c) {
            add(index++, item);
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object item : c) {
            remove(item);
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object item : this) {
            if (!c.contains(item)) {
                this.remove(item);
            }
        }
        return false;
    }

    @Override
    public void clear() {
        this.first.next = null;
        this.first = null;
        this.last.prev = null;
        this.last = null;

        size = 0;
    }

    @Override
    public E get(int index) {
        return findElemByIndex(index).item;
    }

    public E getFirst(){
        Node<E> firstElement = first;
        if (firstElement == null) {
            throw new NullPointerException();
        }
        return firstElement.item;
    }

    public E getLast(){
        Node<E> lastElement = last;
        if (lastElement == null) {
            throw new NullPointerException();
        }
        return lastElement.item;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = findElemByIndex(index);
        E elem = node.item;
        node.item = element;

        return elem;
    }

    @Override
    public void add(int index, E element) {
        Node<E> newElement = new Node<>(null, element, null);
        if (index == 0) {
            addFirst(element);
        }
        else if (index == size) {
            addLast(element);
        }
        else {
            int i = 0;
            for (Node<E> currItem = first; currItem != null; currItem = currItem.next) {
                if (i == index) {
                    Node<E> prev = currItem.prev; //1
                    Node<E> next = currItem.next; //3

                    next.prev = currItem;
                    prev.next = newElement;
                    newElement.prev = prev;
                    newElement.next = currItem;
                    currItem.prev = newElement;
                }
                i++;
            }
        }
        size++;
    }

    public E pop() {
        return removeFirst();
    }

    @Override
    public E remove(int index) {
        Node<E> nodeForRemove = findElemByIndex(index);
        E element = nodeForRemove.item;
        Node<E> prevItem = nodeForRemove.prev;
        Node<E> nextItem = nodeForRemove.next;
        if (size == 1) {
            this.first = null;
            this.last = null;
        } else {
            if (nodeForRemove == first) {
                first = nextItem;
                nodeForRemove.prev = null;
            }
            if (nodeForRemove == last) {
                last = prevItem;
                nodeForRemove.next = null;
            }
            if (nextItem != null && prevItem != null) {
                prevItem.next = nextItem;
                nextItem.prev = prevItem;
            }
        }
        nodeForRemove.item = null;
        size--;
        return element;
    }

    public E removeLast() {
        E element = last.item;
        Node<E> removedItem = last;
        Node<E> prev = last.prev;
        removedItem.prev = null;
        removedItem.item =null;
        last = prev;

        if (prev == null) {
            last = null;
        } else {
            prev.next = null;
        }
        size--;

        return element;
    }

    public E removeFirst() {
        E element = first.item;
        Node<E> removedItem = first;
        Node<E> next = first.next;
        removedItem.next = null;
        removedItem.item = null;
        first = next;

        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }

        size--;
        return element;
    }


    @Override
    public int indexOf(Object o) {
        int index = 0;

        if (o == null) {
            for (Node<E> i = first; i != null; i = i.next) {
                if (i.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<E> i = first; i != null; i = i.next) {
                if(o.equals(i.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;

        if (o == null) {
            for (Node<E> i = last; i != null; i = i.prev) {
                if (i.item == null) {
                    return index;
                }
                index--;
            }
        } else {
            for (Node<E> i = last; i != null; i = i.prev) {
                if (o.equals(i.item)) {
                    return index;
                }
                index--;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ElementIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ElementIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private class ElementIterator implements ListIterator<E> {
        private Node<E> current;
        private Node<E> last;
        private int position;

        public ElementIterator() {
        }

        ElementIterator(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }

            this.position = index;
            int i = 0;

            for (Node<E> x = first; x != null; x = x.next) {
                if (i == index) {
                    this.current = x;
                }
                i++;
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
          if (!hasNext()) {
              throw new NoSuchElementException();
          }
          last = current;
          current = current.next;
          position++;
          return last.getItem();
        }

        @Override
        public boolean hasPrevious() {
            if (position > 0) {
                return true;
            }
            return false;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            current = last;
            position--;
            return last.getItem();
        }

        @Override
        public int nextIndex() {
            return position;
        }

        @Override
        public int previousIndex() {
            if (position == 0) {
                return -1;
            }
            return position - 1;
        }

        @Override
        public void remove() {
            if (last == null) {
                throw new IllegalArgumentException();
            }
            MyLinkedList.this.remove(last.getItem());
        }

        @Override
        public void set(E e) {
            if (last == null) {
                throw new IllegalArgumentException();
            }
            last.item = e;
        }

        @Override
        public void add(E e) {
            if (last == null) {
                throw new IllegalArgumentException();
            }
            MyLinkedList.this.remove(e);
        }
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public E getItem() {
            return item;
        }
    }

    private void linkFirst(E element) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, element, f);
        first = newNode;

        if (f == null) {
            last = newNode;
        }
        else {
            f.prev = newNode;
        }
        size++;
    }

    private void linkLast(E element) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, element, null);
        last = newNode;

        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    private Node<E> findElemByIndex(int index) {
        int i = 0;
        Node<E> neededItem;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            for (Node<E> currElem = first; currElem != null; currElem = currElem.next) {
                if (index == i) {
                    neededItem = currElem;
                    return neededItem;
                }
                i++;
            }
        }
        return null;
    }

    private Node<E> findElement(Object o) {
        Node<E> tempNode;

        if (o == null) {
            for (Node<E> currNode = first; currNode != null; currNode = currNode.next) {
                if (currNode.equals(null)) {
                    tempNode = currNode;
                    return tempNode;
                }
            }
        } else {
            for (Node<E> currNode = first; currNode != null; currNode = currNode.next) {
                if(o.equals(currNode.item)) {
                    return currNode;
                }

            }
        }
        return null;
    }

}
