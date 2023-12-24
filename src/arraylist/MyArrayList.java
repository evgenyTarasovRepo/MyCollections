package arraylist;

import java.util.*;

public class MyArrayList <E> implements List<E> {
    private E[] arr;
    private int size;
    private final int INITIAL_CAPACITY = 6;

    public MyArrayList() {
        arr = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (this.arr.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        E[] newArr = (E[]) new Object[this.size];
        System.arraycopy(arr, 0, newArr, 0, this.size);
        return newArr;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(arr, size, a.getClass());
        }
        System.arraycopy(arr, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E t) {
        if (arr.length == size) {
            resize();
        }
        arr[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size() - 1; i++) {
            if (arr[i].equals(o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E item : c) {
            this.add(item);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null) {
            return false;
        }
        if (c.isEmpty() || index < 0) {
            return false;
        }
        if (index > size) {
            index = size;
        }
        for (E item : c) {
            add(index++, item);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object item : this) {
            if (!c.contains(item)) {
                this.remove(item);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        arr = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index > arr.length) {
            throw new IndexOutOfBoundsException();
        }
        return arr[index] = element;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 0 || index == size) {
            add(element);
        } else if (arr.length == size) {
            E[] tempArr = arr;
            arr = (E[]) new Object[(size * 3) / 2 + 1];
//            System.arraycopy(tempArr, 0, arr, 0, index);
            System.arraycopy(tempArr, index, arr, index + 1, size() - index);

            set(index, element);
            size++;
        } else {
            E[] tempArr = arr;
//            System.arraycopy(tempArr, 0, arr, 0, index + 1);
            System.arraycopy(tempArr, index, arr, index + 1, size() - index);

            set(index, element);
            size++;
        }
    }

    @Override
    public E remove(int index) {
        E element = arr[index];
        if (index != this.size() - 1) {
            System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        }
        size--;
        return element;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size() - 1; i++) {
                if (arr[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size() - 1; i++) {
                if (o.equals(arr[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (arr[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(arr[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        List<E> newList = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            newList.add(arr[i]);
        }
        return newList;
    }

    private void resize() {
        E[] newArr = (E[]) new Object[(size * 3) / 2 + 1];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    class MyListIterator implements Iterator<E> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return MyArrayList.this.size() > index;
        }

        @Override
        public E next() {
            return MyArrayList.this.arr[index++];
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }
}

