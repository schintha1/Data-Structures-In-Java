class DoubleLinkedListNode {
    //  Declaration of Nodes
    DoubleLinkedListNode next, prev;
    int data;

    //  Constructor
    DoubleLinkedListNode(int data)
    {
        this.data = data;
        next = null;
        prev = null;
    }
}

class HashTableChainingDoubleLinkedList {
    //  Declaration of Hash Table
    DoubleLinkedListNode[] hashTable;

    //  Stores the size of HashTable
    int size;

    //  Constructor
    HashTableChainingDoubleLinkedList(int hashTableSize)
    {
        //  Creating an empty Hash Table
        hashTable = new DoubleLinkedListNode[hashTableSize];
        size = 0;
    }

    //  Function to check if hash table is empty
    public boolean isEmpty()
    {
        return size == 0;
    }

    //  Function to clear/delete all elements from Hash Table
    public void clear()
    {
        //  Capacity of Hash Table
        int len = hashTable.length;

        //  Creating new empty Hash Table of same initial capacity
        hashTable = new DoubleLinkedListNode[len];
        size = 0;
    }

    //  Function that returns size of Hash Table
    public int getSize() {return size; }

    //  Function to insert a value/element
    public void insert(int value)
    {
        size++;

        //  gets the position/index where the value should be stored
        int position = hash(value);

        //  create a node for storing the value
        DoubleLinkedListNode node = new DoubleLinkedListNode(value);

        DoubleLinkedListNode start = hashTable[position];

        if(hashTable[position] == null)
        {
            hashTable[position] = node;
        }
        else {
            node.next = start;
            start.prev = node;
            hashTable[position] = node;
        }
    }

    //  Function to remove an element
    public void remove(int value)
    {
        try {
        //  gets the position where the value to be deleted exists
        int position = hash(value);

        DoubleLinkedListNode start = hashTable[position];

        DoubleLinkedListNode  end = start;

        //  If value found at start
        if (start.data == value) {
            size--;
            if(start.next == null) {
                // removing the value
                hashTable[position] = null;
                return;
            }

            start = start.next;
            start.prev = null;
            hashTable[position] = start;

            return;
        }

        while (end.next != null && end.next.data != value)
            end  =  end.next;

        if (end.next  ==  null)  {
            System.out.println("\nElement not found\n");
            return;
        }

        end.next.next.prev = end;
        end.next = end.next.next;

        hashTable[position] = start;
        } catch (Exception e) {
            System.out.println("\nElement not found\n");
        }
    }

    //  Definition of Hash function
    private int hash(Integer x) {
        int hashValue = x.hashCode();
        hashValue %= hashTable.length;

        if(hashValue < 0) {
            hashValue += hashTable.length;
        }

        return hashValue;
    }

    //  Function to  print hash table
    public void printHashTable() {
        System.out.println();
        for (int i =0; i<hashTable.length; i++) {
            System.out.println("At Index "+ i +  " ");

            DoubleLinkedListNode start = hashTable[i];

            while (start != null) {
                System.out.println(start.data+ " ");
                start = start.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        HashTableChainingDoubleLinkedList hashTable = new HashTableChainingDoubleLinkedList(5);

        hashTable.insert(99);
        hashTable.insert(23);
        hashTable.insert(36);
        hashTable.insert(47);
        hashTable.insert(80);
        hashTable.insert(92);
        hashTable.insert(49);

        hashTable.printHashTable();

        hashTable.remove(99);
    }
}


