package hust.soict.dsai.aims.media;

import java.util.Comparator;

public abstract class Media {
    protected int id;         
    protected String title;  
    protected String category;
    protected float cost;
    
    public abstract boolean isMatch(String title);
    
    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public float getCost() {
		return cost;
	}


	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true; 
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false; 
	    }
	    Media media = (Media) obj; 
	    return this.title != null && this.title.equalsIgnoreCase(media.title); 
	}
	
	public void play() {
		
	};
	
	public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
}

