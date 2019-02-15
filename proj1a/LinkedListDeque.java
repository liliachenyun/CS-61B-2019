public class LinkedListDeque<T> {
    private IntNode sentinel;
    private int size;

    private class IntNode {
        private IntNode prev;
        private T item;
        private IntNode next;

        public IntNode(IntNode p, T i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
    public LinkedListDeque(T item) {
        sentinel = new IntNode(null, null, null);
        sentinel.next =  new IntNode(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    } */

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        sentinel.next = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        sentinel.prev = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /* Removes and returns the item at the front of the deque.
       If no such item exists, returns null. */
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev.prev = null;
        sentinel.next.prev.next = null;
        sentinel.next.prev = sentinel;
        size -= 1;
        return first;
    }

    /* Removes and returns the item at the back of the deque.
       If no such item exists, returns null. */
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next.prev = null;
        sentinel.prev.next.next = null;
        sentinel.prev.next = sentinel;
        size -= 1;
        return last;
    }

    // Returns true id deque is empty, false otherwise.
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return (true);
        } else {
            return (false);
        }
    }

    // Returns the number of items in the deque.
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space.
       Once all the items have been printed, print out a new line. */
    public void printDeque() {
        if (this.isEmpty()) {
            System.out.println("Nothing in the list!");
            return;
        }
        IntNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
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
        }
        IntNode p = sentinel;
        if (index <= size / 2) {
            for (int i = 0; i <= index; i++) {
                p = p.next;
            }
            return (p.item);
        } else {
            for (int i = 0; i < size - index; i++) {
                p = p.prev;
            }
            return (p.item);
        }
    }

    //Same as get, but uses recursion.
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        } else if (this.isEmpty()) {
            return null;
        } else if (index <= size / 2) {
            return getRecursivePos(index, sentinel.next).item;
        } else {
            return getRecursiveNeg(size - index - 1, sentinel.prev).item;
        }
    }

    //Private helper method for getRecursive to find target by next IntNode.
    private IntNode getRecursivePos(int index, IntNode p) {
        if (index == 0) {
            return p;
        }
        return getRecursivePos(index - 1, p.next);
    }

    //Private helper method for getRecursive to find target by previous IntNode.
    private IntNode getRecursiveNeg(int index, IntNode p) {
        if (index == 0) {
            return p;
        }
        return getRecursiveNeg(index - 1, p.prev);
    }

    /* Create an entirely new LinkedListDeque, with the exact same items as other.
       However, they should be different objects.
       I.e. if you change other, the new LinkedListDeque you created should not change as well.  */
    /**
    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        IntNode p = other.sentinel.next;
        for (int i = 0; i < other.size(); i++)  {
            addLast(other.get(i));
            size += 1; }
    } */

    /**
     * public static void main(String[] args) {
        LinkedListDeque L = new LinkedListDeque();
        System.out.println(L.get(3));
        L.addFirst("World");
        L.addFirst("Hello");
        L.addLast(2019);
        L.printDeque();
        LinkedListDeque M = new LinkedListDeque(L);
        M.printDeque();
        L.removeLast();
        System.out.println(L.size());
        L.printDeque();
        M.printDeque();
    } */
}
