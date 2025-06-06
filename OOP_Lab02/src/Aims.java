
public class Aims {

	public static void main(String[] args) {		       
		Cart anOrder  = new Cart();
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King","Animation", "Roger Allers", 87, 19.95f);
		anOrder.addDigitalVideoDisc(dvd1);

		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars","Science Fiction", "George Lucas", 87, 24.95f);
		anOrder.addDigitalVideoDisc(dvd2);

		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin","Animation", "HCT", 87, 18.99f);
		anOrder.addDigitalVideoDisc(dvd3);
		
		System.out.println("Total Cost is: ");
		System.out.println(anOrder.totalCost());
		
        //Remove Aladdin
        anOrder.removeDigitalVideoDisc(dvd3);
        //Cost after remove
        System.out.println("Total Cost is: ");
		System.out.println(anOrder.totalCost());
		
		//Remove DVD not in Cart
		DigitalVideoDisc dvd4 = new DigitalVideoDisc("Star Lion","Animation","Unknown", 87, 20.95f);
        anOrder.removeDigitalVideoDisc(dvd4);
	}

}
