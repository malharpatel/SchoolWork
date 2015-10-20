/* DList.java */

package list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel node at the head of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 **/

public class DList extends List {

  /**
   *  (inherited)  size is the number of items in the list.
   *  head references the sentinel node.
   *  Note that the sentinel node does not store an item, and is not included
   *  in the count stored by the "size" field.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATION.
   **/

  protected DListNode head;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For every DListNode x in a DList, x.next != null.
   *  3)  For every DListNode x in a DList, x.prev != null.
   *  4)  For every DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For every DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  For every DList l, l.head.myList = null.  (Note that l.head is the
   *      sentinel.)
   *  7)  For every DListNode x in a DList l EXCEPT l.head (the sentinel),
   *      x.myList = l.
   *  8)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   **/

  /**
   *  newNode() calls the DListNode constructor.  Use this method to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method need be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *
   *  @param item the item to store in the node.
   *  @param list the list that owns this node.  (null for sentinels.)
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   *  @param edges checks whether the edgelist should contain nodes.
   **/
  protected DListNode newNode(Object item, DList list,
                              DListNode prev, DListNode next, boolean edges) {
    return new DListNode(item, list, prev, next, edges);
  }

  /**
   *  DList() constructs for an empty DList without an edgelist.
   **/
  public DList() {
    head = newNode(Integer.MIN_VALUE, null, null, null, false);
    head.prev = head;
    head.next = head;
    size = 0;
  }


  /**
   *  insertFront() inserts an item at the front of this DList.
   *
   *  @param item is the item to be inserted.
   *  @param whether the new node inserted should have an edge list.
   *
   *  Performance:  runs in O(1) time.
   **/
  public void insertFront(Object item, boolean edges) {
    DListNode front = newNode(item, this, head, head.next, edges);
    head.next.prev = front;
    head.next = front;
    size++;
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *
   *  @param item is the item to be inserted.
   *  @param whether the new node inserted should have an edge list.
   *
   *  Performance:  runs in O(1) time.
   **/
  public void insertBack(Object item, boolean edges) {
    DListNode back = newNode(item, this, head.prev, head, edges);
    head.prev.next = back;
    head.prev = back;
    size++;
  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return an "invalid" node--a node with the property that any
   *  attempt to use it will cause an exception.  (The sentinel is "invalid".)
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a ListNode at the front of this DList.
   *
   *  Performance:  runs in O(1) time.
   */
  public ListNode front() {
    if (head.next == null) {
      return newNode(Integer.MIN_VALUE, null, null, null, false);
    }
    return head.next;
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return an "invalid" node--a node with the property that any
   *  attempt to use it will cause an exception.  (The sentinel is "invalid".)
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a ListNode at the back of this DList.
   *
   *  Performance:  runs in O(1) time.
   */
  public ListNode back() {
    if (head.next == null) {
      return newNode(Integer.MIN_VALUE, null, null, null, false);
    }
    return head.prev;
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }
}