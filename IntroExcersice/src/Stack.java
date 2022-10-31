public class Stack<T> {
    T[] elements;
    int count;

    public Stack(int initSize) {
        this.elements = (T[])new Object[initSize];
        count = 0;
    }

    public void Push(T newElement) {
        if (count < elements.length - 1) {
            elements[count] = newElement;
        }
        else {
            T[] resizedArray = (T[])new Object[elements.length * 2];
            System.arraycopy(elements, 0, resizedArray, 0, elements.length);
            elements = resizedArray;
            resizedArray = null;
            elements[count] = newElement;
        }

        count++;
    }


    public T Pop() {
        if (count == 0) {
            return null;
        }

        T value = elements[count-1];
        count--;
        if (count < elements.length / 2) {
            T[] resizedArray = (T[])new Object[(int)(elements.length / 2)];
            System.arraycopy(elements, 0, resizedArray, 0, count);
            elements = resizedArray;
            resizedArray = null;
        }

        return value;
    }


    public boolean Empty() {
        return (count == 0);
    }
}
