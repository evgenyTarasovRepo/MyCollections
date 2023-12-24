package map;

import java.util.*;

public class MyMap<K, V> implements Map<K, V> {

    private class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

//        @Override
//        public boolean equals(Object o) {
//           if (this == o) return true;
//
//           Map.Entry<K, V> pair = (Map.Entry<K, V>) o;
//           if (key != null ? !key.equals(pair.getKey()) : pair.getValue() != null) return false;
//           return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);
//        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return key.equals(entry.key) && value.equals(entry.value);
        }

        @Override
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private static int BUCKETS_QUANTITY = 6;
    private Entry<K, V> table[];

    public MyMap() {
        table = new Entry[BUCKETS_QUANTITY];
    }

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int hash = getCellNumber(key);
        Entry<K, V> e = table[hash];
        if (e == null) return false;
        if (e.getKey().equals(key)) return true;
        while (e.next != null) {
            if (e.getKey().equals(key)) {
                return true;
            }
            e = e.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Entry<K,V>[] tab; V v;
        if ((tab = table) != null && size > 0) {
            for (Entry<K,V> e : tab) {
                for (; e != null; e = e.next) {
                    if ((v = e.value) == value ||
                            (value != null && value.equals(v)))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int hash = getCellNumber(key);
        Entry<K, V> entry = table[hash];

        if (entry == null) return null;
        while (entry.next != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        resizeMap();
        int hash = getCellNumber(key);
        Entry<K, V> entry = table[hash];

        if (entry == null) {
            table[hash] = new Entry<K, V>(key, value);
        } else {
            while (entry.next != null) {
                if (entry.getKey().equals(key)) {
                    V oldValue = entry.getValue();
                    entry.setValue(value);
                    return oldValue;
                }
                entry = entry.next;
            }
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
            entry.next = new Entry<K, V>(key, value);
        }
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        int hash = getCellNumber(key);
        Entry<K, V> entry = table[hash];

        if (entry == null) return null;

        if (entry.getKey().equals(key)) {
            table[hash] = entry.next;
            entry.next = null;
            size--;
            return entry.getValue();
        }
        Entry<K, V> prev = entry;
        entry = entry.next;
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                prev.next = entry.next;
                entry.next = null;
                size--;
                return entry.getValue();
            }
            prev = entry;
            entry = entry.next;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> entry : (Set<Map.Entry<K, V>>) (Set) m.entrySet()){
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        if (table != null && size > 0) {
            Arrays.fill(table, null);
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Entry<K,V> e : table) {
            for (; e != null; e = e.next) {
               set.add(e.key);
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> c = new ArrayList<>();
        for (Entry<K, V> e: table) {
            for (; e != null; e = e.next) {
                c.add(e.getValue());
            }
        }
        return c;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (Entry<K, V> e: table) {
            for (; e != null; e = e.next) {
                set.add(e);
            }
        }
        return set;
    }

    private int getCellNumber(Object key) {
        return key.hashCode() % BUCKETS_QUANTITY;
    }

    private void resizeMap() {
        if (size == table.length) {
            int i = 0;
            Entry<K, V>[] newTable = new Entry[BUCKETS_QUANTITY * 2];
            for (Entry<K, V> e : table) {
                for (; e != null; e = e.next) {
                    newTable[i] = e;
                }
            }
        }


    }
}
