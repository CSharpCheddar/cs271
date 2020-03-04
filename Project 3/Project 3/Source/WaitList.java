
import java.util.Random;

/**
 * My algorithm is pretty simple. All I do is move VIPs to the front 
 * and keep everyone else where they are. Since VIPs tip so much, and 
 * their tip penalty is so steep, I want them served immediately. 
 * In contrast to the VIPs' high tips, the other types of guests have 
 * very low penalties and do not tip nearly as much. Doing some math with
 * rough averages, it's easy to see that a wait list of this size makes 
 * it more than worth it to just implement this algorithm. If the wait list 
 * were bigger, then I would create some sort of limitation as to how 
 * many people VIPs can skip. Also, it pays to keep the other clients 
 * where they are, because their tips depend on how many people skip 
 * them, and not when they are served. That's how I arrived at this solution.
 */
public class WaitList {
    
    private Guest[] data;
    private int size;
    private boolean shouldAdd;
    private Random random;
    
	public WaitList(){
        //initialize instance variables
		data = new Guest[10];
        size = 0;
        shouldAdd = false;
        random = new Random();
        //populate the list at the beginning
        for(int i=0; i<100; i++) {
        	if(size == data.length){
                resize();
            }
        	data[size++] = new Guest(size, "R");
        }
    }
    
    private void resize(){
        Guest[] tempArr = new Guest[3*data.length];
        for(int index=0; index<size; index++){
            tempArr[index] = data[index];
        }
        data = tempArr;
    }
    
    private void resizeDown(){
    	Guest[] tempArr = new Guest[(int)((1.0/3.0)*data.length)];
        for(int index=0; index<size; index++){
            tempArr[index] = data[index];
        }
        data = tempArr;
    }
    
    private void heapifyDown(int index){
        //ensure that there is at least a left child
        if(2*index+1 < size){
            if(2*index+2 < size){
                //I know we have both children
                Guest valueLeftChild = data[2*index + 1];
                Guest valueRightChild = data[2*index + 2];
                Guest smallestChild = 
                    (valueLeftChild.compareTo(valueRightChild) < 0) ? valueLeftChild : valueRightChild;
                if(data[index].compareTo(smallestChild) > 0){
                    if(smallestChild.compareTo(valueLeftChild) == 0){
                        //swap parent with left
                        Guest temp = data[index];
                        data[index] = data[2*index + 1];
                        data[2*index + 1] = temp;
                        heapifyDown(2*index+1);
                    } else{
                        //swap parent with right
                        Guest temp = data[index];
                        data[index] = data[2*index + 2];
                        data[2*index + 2] = temp;
                        heapifyDown(2*index+2);
                    }
                }
            } else{
                //I know I only have a left child
                if(data[index].compareTo(data[2*index+1]) > 0){
                    //swap parent with left
                    Guest temp = data[index];
                    data[index] = data[2*index + 1];
                    data[2*index + 1] = temp;
                    heapifyDown(2*index+1);
                }
            }
        }
        //no children
       
        
    }
    
    public Guest removeMin(){
        if(isEmpty()){
            throw new RuntimeException("Empty heap.");
        }
        Guest valueReturning = data[0];
        data[0] = data[--size];
        heapifyDown(0);        
        if(data.length > 10 && (int)(1.0/6.0 * data.length) > size ){
            resizeDown();
        }
        //add a new guest if 2 have been removed
        newGuest();
        return valueReturning;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    private void heapifyUp(int index){
        //I'm not at the root
        if(index != 0){
            //parent is located at (index - 1) / 2
            if(data[index].compareTo(data[(index - 1) / 2]) < 0){
                Guest temp = data[index];
                data[index] = data[(index - 1) / 2];
                data[index].skipped();
                data[(index - 1) / 2] = temp;
                heapifyUp((index - 1) / 2);
            }
        }
    }
    
    //adds a new guest to the list if 2 people have been removed
    private void newGuest() {
    	//if it's time to add, add
        if(shouldAdd) {
        	if(size == data.length){
                resize();
            }
        	int n = random.nextInt(100);
        	if(n < 60) {
        		//regular guest
        		data[size++] = new Guest(size, "R");
        	} else if(n < 85) {
        		//important guest
        		data[size++] = new Guest(size, "I");
        	} else {
        		//VIP guest
        		data[size++] = new Guest(0, "V");
        	}
        	heapifyUp(size-1);
        	shouldAdd = false;
        } else {
        	shouldAdd = true;
        }
    }
    
}
