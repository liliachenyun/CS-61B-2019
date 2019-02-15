public class ArrayDeque<T> {
    private T[] items;
    private int head;
    private int tail;
    private int size;
    private int cap; // The capacity of the array.
    private int basic = 8;

    public ArrayDeque() {
        head = 0;
        tail = 0;
        size = 0;
        cap = basic;
        items = (T[]) new Object[cap];
    }

    private void resize() {
        if (head == tail && size != 0) {
            /* Expand the array and the situation should be considered that all the elements
           are removed from array, meanwhile tail and head are pointed to the same index.*/
            T[] a = (T[]) new Object[cap * 2];
            System.arraycopy(items, head, a, 0, cap - head);
            System.arraycopy(items, 0, a, cap - head, head);
            head = 0;
            tail = size;
            cap *= 2;
            items = a;
        } else if (cap <= basic) {
            /** It's not necessary to shorten the array
            when the capacity of array is already the minimum.*/
            return;
        } else { 
            /* Shorten the array when the cap is bigger than the minimum.*/
            T[] a = (T[]) new Object[cap / 2];
            if (head <= tail) {
                System.arraycopy(items, head, a, 0, tail - head);
            } else {
                System.arraycopy(items, head, a, 0, cap - head);
                System.arraycopy(items, 0, a, cap - head, size - cap + head);
            }
            head = 0;
            tail = size;
            cap /= 2;
            items = a;
        }
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        size += 1;
        if (head == 0) {
            head += cap - 1;
        } else {
            head -= 1;
        }
        items[head] = item;
        if (head == tail) {
            this.resize();
        }
    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        items[tail] = item;
        size += 1;
        if (tail == cap - 1) {
            tail = 0;
        } else { tail += 1;
        }
        if (head == tail) {
            this.resize();
        }
    }

    /* Removes and returns the item at the front of the deque.
       If no such item exists, returns null. */
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        size -= 1;
        T first = items[head];
        items[head] = null;
        if (head == cap - 1) {
            head = 0;
        } else {
            head += 1;
        }
        if (size < cap / 4) {
            this.resize();
        }
        return first;
    }

    /* Removes and returns the item at the back of the deque.
       If no such item exists, returns null. */
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        size -= 1;
        if (tail == 0) {
            T last = items[cap - 1];
            items [cap - 1] = null;
            tail += cap - 1;
            if (size < cap / 4) {
                this.resize();
            }
            return last;
        } else {
            T last = items[tail - 1];
            items [tail - 1] = null;
            tail -= 1;
            if (size < cap / 4) {
                this.resize();
            }
            return last;
        }
    }


    // Returns the number of items in the deque.
    public int size() {
        return size;
    }

    // Returns true id deque is empty, false otherwise.
    public boolean isEmpty() {
        if (tail == head) {
            return (true);
        } else {
            return (false);
        }
    }

    /* Prints the items in the deque from first to last, separated by a space.
       Once all the items have been printed, print out a new line. */
    public void printDeque() {
        if (this.isEmpty()) {
            System.out.println("Nothing in the list!");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (head + i < cap) {
                System.out.print(items[head + i] + " ");
            } else {
                System.out.print(items[head + i - cap] + " ");
            }
        }
        System.out.print("\n");
        return;
    }

    /* Gets the item at the given index, where 0 is the front.
       1 is the next item, and so forth.
       If no such item exists, returns null.
       Must not alter the deque! */
    public T get(int index) {
        if (index >= size) {
            return null;
        } else if (this.isEmpty()) {
            return null;
        } else if (head + index < cap) {
            return items[head + index];
        } else {
            return items[head + index - cap];
        }
    }

    /* Creating a deep copy means that you create an entirely new ArrayDeque,
       with the exact same items as other. However, they should be different objects.
       i.e. if you change other, the new ArrayDeque you created should not change as well. */
    /**
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.cap];
        head = other.head;
        tail = other.tail;
        cap = other.cap;
        basic = other.basic;
        size = other.size;
        System.arraycopy(other.items, 0, items, 0,cap);
    } */

    /**
     * public static void main(String[] args) {
        ArrayDeque L = new ArrayDeque();
        L.addLast("o");
        L.addFirst("l");
        L.addFirst("l");
        L.addFirst("e");
        L.addLast(" ");
        L.addLast("W");
        ArrayDeque M = new ArrayDeque(L);
        M.addLast("o");
        M.addLast("r");
        M.addLast("l");
        M.addLast("d");
        M.addLast("!");
        M.addFirst("H");
        M.printDeque();
        System.out.println("Size: " + L.size());
        L.printDeque();
        System.out.println("Size: " + L.size());
        System.out.println("Size: " + L.get(2));
        System.out.println("Size: " + L.size());
    } */
}
