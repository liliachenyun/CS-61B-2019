package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    private class Itr implements Iterator<T> {
        private int index;

        public void Itr() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < capacity();
        }

        @Override
        public T next() {
            T currentThing = rb[index];
            index += 1;
            return currentThing;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {return false;}
        if (this == o) {return true;}
        if (o.getClass() != this.getClass()) {return false;}
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if(fillCount != other.fillCount) {return false;}
        Iterator<T> Itr1 = this.iterator();
        Iterator<T> Itr2 = other.iterator();
        while (Itr1.hasNext()) {
            if (Itr1.next() != Itr2.next()) {return false;}
        }
        if (Itr2.hasNext()) {return false;}
        return true;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (!this.isFull()) {
            rb[last] = x;
            last = (last + 1) % rb.length;
            fillCount += 1;
        return;
        } else {
            throw new RuntimeException("Ring Buffer Overflow");
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (!this.isEmpty()) {
            T item = rb [first];
            rb[first] = null;
            first = (first + 1 + rb.length) % rb.length;
            fillCount -= 1;
            return item;
        } else {
            throw new RuntimeException("Ring Buffer Underflow");
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        return rb[first];
    }
}

