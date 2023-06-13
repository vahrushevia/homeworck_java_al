package lasson4;


public class MyHashMap {
    private Node[] table;
    private int size;
    
    public MyHashMap() {
        table = new Node[16]; // Размер внутреннего массива узлов
        size = 0;
    }
    
    public Object put(Integer key, Integer value) {
        int index = getIndex(key);
        
        if (table[index] == null) {
            table[index] = new Node(key, value);
            size++;
        } else {
            Node currentNode = table[index];
            while (true) {
                if (currentNode.key.equals(key)) {
                    Object oldValue = currentNode.value;
                    currentNode.value = value;
                    return oldValue;
                }
                
                if (currentNode.next == null) {
                    currentNode.next = new Node(key, value);
                    size++;
                    break;
                }
                
                currentNode = currentNode.next;
            }
        }
        
        return null;
    }
    
    public Object get(Integer key) {
        int index = getIndex(key);
        
        Node currentNode = table[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
            
            currentNode = currentNode.next;
        }
        
        return null;
    }
    
    public Object remove(Integer key) {
        int index = getIndex(key);
        
        Node currentNode = table[index];
        Node previousNode = null;
        
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                if (previousNode == null) {
                    table[index] = currentNode.next;
                } else {
                    previousNode.next = currentNode.next;
                }
                
                size--;
                return currentNode.value;
            }
            
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        
        return null;
    }
    
    public Object replace(Integer key, Integer value) {
        int index = getIndex(key);
        
        Node currentNode = table[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                Object oldValue = currentNode.value;
                currentNode.value = value;
                return oldValue;
            }
            
            currentNode = currentNode.next;
        }
        
        return null;
    }
    
    public int size() {
        return size;
    }
    
    public boolean containsKey(Integer key) {
        int index = getIndex(key);
        
        Node currentNode = table[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return true;
            }
            
            currentNode = currentNode.next;
        }
        
        return false;
    }
    
    public boolean containsValue(Integer value) {
        for (int i = 0; i < table.length; i++) {
            Node currentNode = table[i];
            while (currentNode != null) {
                if (currentNode.value.equals(value)) {
                    return true;
                }
                
                currentNode = currentNode.next;
            }
        }
        
        return false;
    }
    
    private int getIndex(Integer key) {
        return key.hashCode() % table.length;
    }
    
    private class Node {
        private Integer key;
        private Integer value;
        private Node next;
        
        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}