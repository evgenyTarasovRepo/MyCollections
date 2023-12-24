package set;

import java.util.*;
import java.util.function.Consumer;

public class MySet<E> implements Set<E> {
    private final Map<E, Boolean> mySet =  new HashMap<>();;
    static Boolean PLACE_HOLDER = true;

    @Override
    public int size() {
        return mySet.size();
    }

    @Override
    public boolean isEmpty() {
        return mySet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return mySet.containsKey(o);
    }

    @Override
    public Iterator<E> iterator() {
        return mySet.keySet().iterator();
    }

    @Override
    public Object[] toArray() {
        return mySet.keySet().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return mySet.keySet().toArray(a);
    }

    @Override
    public boolean add(E e) {
        mySet.put(e, PLACE_HOLDER);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        mySet.remove(o);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            return false;
        }
        for (Object item : c) {
            if (!c.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            return false;
        }
        for (E item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Iterator<E> elementsIterator = this.iterator();
        Consumer<E> action = element -> {
            if (!c.contains(element)) {
                elementsIterator.remove();
            }
        };
        elementsIterator.forEachRemaining(action);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object item : c) {
            if (this.contains(item)) remove(item);
        }
        return false;
    }

    @Override
    public void clear() {
        mySet.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySet<?> mySet1 = (MySet<?>) o;
        return mySet.equals(mySet1.mySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mySet);
    }
}
