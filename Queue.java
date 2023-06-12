/** 
* To create private head, tail and size
* @param head the head for the node
* @param tail the tail for the node
* @param size the number of elements in list
*/
public class Queue<E> {
    private class Node<E> {
        E element;
        Node<E> next;
        /** 
        * The current Donor's matched will be the main Matched
        * @param matched the matched status of the account
        */ 
        public Node(E element) {
            this.element = element;
        }
    }

    private Node<E> head, tail;
    private int size;

    /** 
   * Returns the matched from the Queue.
   * 
   * if the size (no. of elements in the list) is 0 it will
   *    @return head.element
   * 
   * else
   *    null
   */ 
    public E getFirst() {
        if (size == 0)
            return null;
        else
            return head.element;
    }
    /** 
    * To add new element at the last of the Queue
    * @param e as element
    */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); 
        if (tail == null) {
            head = tail = newNode; 
            size++;
        }
        else if (newNode.element.equals(tail.element)) {
        }

        else {
            tail.next = newNode; 
            size++;
            tail = newNode;
        }
        
    }
    /** 
    * To remove the first element element at the last of the Queue
    * @return temp
    */
    public E removeFirst() {
        if (size == 0)
            return null;
        else {
            E temp = head.element;
            head = head.next;
            size--;
            if (head == null)
                tail = null;
            return temp;
        }
    }
    
    /** 
    * To return the result as a string
    * elements are seperated by comma
    * @return result
    */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null)
                result.append(", "); // Separate two elements with a comma
        }

        return result.append("]").toString();
    }
    /** 
    * To return head, tail or current based on the condition occured
    * @param index indicator
    * @throws IndexOutOfBoundsException if the index is invalid
    *   
    * @return current if the current = head
    */
    public E getNGO(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException ();
        }
        if (index == 0) {
            return head.element;
        }
        else if (index >= size){
            return tail.element;
        }
        else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.element;
        }
    }
    /** 
    * To return the size from Queue
    * @return size
    */
    public int size() {
        return size;
    }


}