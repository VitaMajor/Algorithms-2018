package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    // Трудоемкость: T = O(N) в худшем случае; T = O(logN) в среднем
    // Ресурсоемкость: R = O(1)
    @Override
    public boolean remove(Object o) {

        if (root == null) {
            return false;
        }

        Node<T> parent = root;
        Node<T> moveable = root;

        int num;
        int moveableIsLeft = 0;

        while ((num = moveable.value.compareTo((T) o)) != 0) {

            parent = moveable;

            if (num > 0) {
                moveableIsLeft = 1;
                moveable = moveable.left;
            } else {
                moveable = moveable.right;
                moveableIsLeft = -1;
            }
            if (moveable == null) {
                return false;
            }
        }

        if (moveable.left != null && moveable.right != null) {

            Node<T> next = moveable.right;
            Node<T> before = moveable;

            while (next.left != null) {
                before = next;
                next = next.left;
            }
            if (root == moveable) {
                root = next;
            }
            if (before == moveable) {
                moveable.right = next.right;
            } else {
                before.left = next.right;
            }
            next.left = moveable.left;
            next.right = moveable.right;
            switch (moveableIsLeft) {
                case 1:
                    parent.left = next;
                    break;
                case -1:
                    parent.right = next;
                    break;
            }
        } else {
            if (moveable.right != null) {
                link(parent, moveable, moveable.right);
            } else if (moveable.left != null) {
                link(parent, moveable, moveable.left);
            } else {
                link(parent, moveable, null);
            }
        }

        size--;

        return true;
    }

    private void link(Node<T> parent, Node<T> moveable, Node<T> child) {
        if (parent == moveable) {
            root = child;
        } else if (parent.right == moveable) {
            parent.right = child;
        } else {
            parent.left = child;
        }
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {
            stack = new Stack<>();
            addToStack(root);
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        // Трудоемкость: T = O(1)
        // Ресурсоемкость: R = O(N)
        private Node<T> findNext() {
            return stack.pop();
        }

        private Stack<Node<T>> stack;

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            current = findNext();
            if (current.right != null) {
                addToStack(current.right);
            }
            return current.value;
        }

        private void addToStack(Node<T> root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        // Трудоемкость: T = O(N) в худшем случае; T = O(logN) в среднем
        // Ресурсоемкость: R = O(1)
        @Override
        public void remove() {
            BinaryTree.this.remove(current.value);
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
