package model;

public class Amicizia {
	
	    private String amico1;
	    private String amico2;

	    // Costruttore
	    public Amicizia(String amico1, String amico2) {
	        this.amico1 = amico1;
	        this.amico2 = amico2;
	    }

	   
	    public String getAmico1() {
	        return amico1;
	    }

	    public void setAmico1(String amico1) {
	        this.amico1 = amico1;
	    }

	    public String getAmico2() {
	        return amico2;
	    }

	    public void setAmico2(String amico2) {
	        this.amico2 = amico2;
	    }

	    
	    @Override
	    public String toString() {
	        return "Amicizia{" +
	                "amico1='" + amico1 + '\'' +
	                ", amico2='" + amico2 + '\'' +
	                '}';
	    }
	}



