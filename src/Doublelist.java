    
import java.util.AbstractList;

/**
 *
 *  The Doublelist is a double linked list that holds data of any type similar to linkedlist but simpler 
 *  It does not extend from AbstractSequentialList and only implements a very small number of interfaces
 *  It doesn't have an iterator 
 * 
 * @author Muli
 * @param <Type> the type of element held by the list
 */
public class Doublelist <Type> extends AbstractList implements Cloneable{
    
    /**
     * stores the header of the list
     */
    Node header;
    
    /**
     * stores the trailer of the list
     */
    Node trailer;
    
    /**
     * stores the size of the linked list 
     */
    protected  int size;
    
    /**
    * Gets the node at the specified index 
    * The method is yet to be supported and invoking it throws an UnsupportedOperationException
     * @param index
     * @return 
    */
    @Override
    public Object get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
    /**
    * Returns the size of entries the list contains 
     * @return size the size of the list
    */
    @Override
    public int size() {
        return size;
    }
    
    
    
   /*
      The node contains an element added to the list 
    and pointers that points to the node behind it and to the node ahead of it
    */
    public class Node implements Comparable  {
    
   /**
    * is the type of the element passed 
    */
      Type type;
    /**
     * the next node in the linked list 
     */
    Node next;
    /**
     * the previous node in the linked list
     */
    Node prev;
 
    
    /*
      The element and pointers are initialiased to null
    */
    Node(){
        
        next = null;
        type = null;
        prev = null;
        
    }
    
    /**
     * Returns the element held by the node
     * @return the element of the node 
    */
    public Type getType(){
        return type;
    }
    
    /**
    *   Gets the next node in the list
    *   @return the next node in the list
    */
    public Node getNext(){
        return next;
    }
    
    /**
     * Gets the previous node in the list 
     * @return the previous node in the list 
    */
    public Node getPrev(){
        return prev;
    }
    
    /**
     * Sets the element to be stored by the node 
     * @param set the element to  be set
    */
    public void setType(Type set){
        type = set;
    }
    
    /**
     * Sets the next node after this in the list
     * @param set the node to be the next in the list
    */
    public void setNext(Node set){
        next = set;
    }
    
    /**
     * Sets the previous  node after this in the list
     * @param set the node to be the previous in the list
    */
    public void setPrev(Node set){
        prev = set;
    }
    
    /**
    * Converts the Node into string that can be printed to the console
    * @return ty string to be printed  
    */
    @Override
    public String toString(){
        
        String ty = null;
        
        if (type instanceof String)      
                                 ty = (String)type;
              
        else if (type instanceof Integer)
                                 ty = Integer.toString((Integer)type);
        
        else if (type instanceof Long) 
                                 ty = Long.toString((Long)type);
        
        else if (type instanceof Double) 
                                 ty = Double.toString((Double)type);
         
        else if (type instanceof Float) 
                                 ty = Float.toString((Float)type);
        
        else if (type instanceof Short) 
                                 ty = Short.toString((Short)type);
        
        else if (type instanceof Byte) 
                                 ty = Byte.toString((Byte)type);
        
        else 
                                 ty = type.toString();
        
        return ty;
    }
        
      /**
     * Compares another node with this node 
     * Returns a positive one if the passed node is greater
     * Returns a negative one if the passed node is less 
     * Returns zero if the passed node is equal 
     * @param o the node to be compared
     * @return compared one if more, negative one if less and zero if equal 
     * @throws UnsupportedOperationException if comparison is between any data that isn't an instance of Number
      */
        @Override
        public int compareTo(Object o) {
            
            int compared;
            
            if(!(type instanceof Number))
                     throw new UnsupportedOperationException("String , char and objects supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            else{
                
                int first = (Integer)type;
                Node ob = (Node)o;
                int second = (Integer)ob.getType();
                
                if (first < second)
                          compared = -1;
                else if (first == second)
                          compared = 0;
                else
                          compared = 1;
            }
            
            return compared;
        }
            
}
    

    /*
    The header node is initialised to null and its prevoius pointer set to null. Its next pointer is set to the trailer
    The trailer node is initialised to null and its next pointer set to null. Its previous pointer is set to the header
    The integer size is set to 0
    */
    Doublelist(){
        
        header = new Node();
        trailer = new Node();
        header.setPrev(null);
        header.setNext(trailer);
        trailer.setPrev(header);
        trailer.setNext(null);
        size = 0;
        
    }
    
    /**
     * Adds an element to the top of the list
     * @param t element to be added 
     */
    public void addFirst( Type t  ){
        
        addafter(header,t);
    }
    
    /**
     * Removes the first node in the list
     */
    public void removefirst(){
         
        remove(header.getNext());
        
    }
    
    /**
     * Adds an element to the bottom of the list
     * @param t element to be added
     */
    public void addLast(Type t){
        
       addbefore(trailer,t);
        
    }
    
    /**
     * Removes the last node from the list
     */
    public void removelast(){
        
        remove(trailer.getPrev());
        
    }
    
    /**
     * Removes a node from the list 
     * @throws NullPointerException when the list is empty
     * @param toberemoved the node to be removed 
     */
    public void remove(Node toberemoved){
        
        if (size == 0)
            throw new NullPointerException("The list is empty");
        
        Node oldnext = toberemoved.getNext();
        Node oldprev = toberemoved.getPrev();
        
        oldnext.setPrev(oldprev);
        oldprev.setNext(oldnext);
        
        toberemoved.setNext(null);
        toberemoved.setPrev(null);
         
        size --;
        
    }
    
    /**
     * Adds an element before a node in the list 
     * @throws NullPointerException when element is added before the header
     * @param pivot Node that element is added before
     * @param t element that is added
     */
    public void addbefore(Node pivot, Type t){
        
        if (pivot == header)
            throw new NullPointerException ("Cant add element before header");
        
        Node before = new Node();
        before.setType(t);
        Node oldbefore = pivot.getPrev();
        
        pivot.setPrev(before);
        before.setNext(pivot);
        
        oldbefore.setNext(before);
        before.setPrev(oldbefore);
        
        size++;
        
    }
    
    /**
     * Adds an element after a node in the list 
     * @throws NullPointerException when element is added after the trailer
     * @param pivot Node that element is added after
     * @param t element that is added
     */
    public void addafter(Node pivot, Type t){
        
        if(pivot == trailer)
            throw new NullPointerException ("Cant add element after trailer");
        
        Node after = new Node();
        after.setType(t);
        Node oldafter = pivot.getNext();
        
        after.setPrev(pivot);
        pivot.setNext(after);
        
        oldafter.setPrev(after);
        after.setNext(oldafter);
        
        size++;
    }
    
    /**
     * Gets the first node in the list 
     * @return the first node 
     */
    public Node getfirst(){
        
        if (size == 0)
            return null;
        Node first = header.getNext();
        return first;
        
    }
   
    /**
     * Gets the last node in the list 
     * @return the last node 
     */
    public Node getlast(){
        
        if (size == 0) 
            return null;
        
        Node last = trailer.getPrev();
        return last;
    }
    
    /**
     * Converts the list into an array
     * @return the array of elements in the list
     */
    @Override
    public Object[] toArray(){
        
        
        Object  o[] = new Object[size];
        Node cur = header.getNext();
        
        for(int i = 0 ; i < size ; i ++){
            
            o[i] = cur;
            cur = cur.getNext();
            
        }   
       
        return o;    
    }
    
    /**
     * Creates an exact copy of the list 
     * @return the clone of the list
     * @throws CloneNotSupportedException when a clone of the object cannot be made 
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        
        
        Doublelist<Type> clone = new Doublelist();
        // Initialize clone with our elements
        Node x = getfirst();
        for (; x != trailer; x = x.getNext()){
            clone.addLast(x.type);
            
        }
        return clone;
    }
        
    /**
     * Sorts the list passed to it and sorts it in ascending order
     * @param copylist list to be sorted 
     */
    private void sort( Doublelist copylist)  { 
        
         Node pivot;
         Node ins;
         Node end = copylist.getfirst();
         
         while(end != copylist.getlast()){
             
             pivot = end.getNext();
             copylist.remove(pivot);
             ins = end;
             
             while(copylist.hasprev(ins)  && ins.compareTo(pivot) == 1)
                               ins = ins.getPrev();
             
             copylist.addafter(ins, pivot.getType());
             
             if(ins.compareTo(end) == 0)
                   end = end.getNext();
         }
        
    }
    
    /**
     * Returns a clone of the list that invokes it 
     * @return  a list that is a clone
     * @throws CloneNotSupportedException 
     */
    public Doublelist sort() throws CloneNotSupportedException{
        Doublelist copy = (Doublelist<Type>) this.clone();
        sort(copy);
        return copy;
    }
    
    /**
     * Sorts the list that invokes it in ascending order 
     */
    public void sortlist(){    
        sort(this);     
    }
    
    /**
     * Gets whether a node has a node after it
     * @param n node that you want to check if it has a node after it
     * @return true when node has an element after it
     */
    private boolean hasnext(Node n){
        return n.getNext() != trailer;
    }
    
     /**
     * Gets whether a node has a node before it
     * @param n node that you want to check if it has a node before it
     * @return true when node has an element before it
     */
    private boolean hasprev(Node n){
        return n.getPrev() != header;
    }
    
    /**
     * Converts the list into a string that can be printed onto the console
     * @return string representation of the list
     */
    @Override
    public String toString(){
        
          if (header.getNext() == trailer)
             return "[]";
     
     Node start = header.getNext();
    String s;
    s = "[" + start.toString();
    Node cur = start.getNext();
    
       for (int i = 1 ; i < size ; i ++){
           
           s = s + " ," + cur.toString();
           cur = cur.getNext();
           
    }
    s = s +  "]";
    
    return s;
}
}

