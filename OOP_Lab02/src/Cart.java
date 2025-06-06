
public class Cart {
	public static final int MAX_NUMBERS_ORDERED = 20;
	private DigitalVideoDisc itemOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
	private int qtyOrdered = 0;
	
    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered < 20) {
            itemOrdered[qtyOrdered] = disc;
            qtyOrdered++;
            System.out.println("Added DVD");
        } else {
            System.out.println("Cart is full");
        }
    }
    
    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        int check = 0;
        for (int i=0;i<qtyOrdered;i++) {
            if (itemOrdered[i].equals(disc)) {  
                check = 1;
                for (int j=i;j<qtyOrdered-1;j++) {
                    itemOrdered[j] = itemOrdered[j+1];
                }
                itemOrdered[qtyOrdered-1] = null; 
                qtyOrdered--; 
                System.out.println("Removed DVD");
                break;
            }
        }
        if (check==0) {
            System.out.println("Not found DVD");
        }
    }
    
    public float totalCost() {
        float cost = 0;
        for (int i=0;i<qtyOrdered;i++) {
            cost += itemOrdered[i].getCost();  
        }
        return cost;
    }

}
