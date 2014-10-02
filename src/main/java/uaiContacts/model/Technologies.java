package uaiContacts.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Technologies {

    @Id
    @GeneratedValue
    private int technologiesID;
    private int coloursID;
	private String technology;
	private String version;
	private String description;
	
	public Technologies(){
		
	}
	
	public Technologies(String technology, String version, String description, int id, int coloursID) {
		super();
		this.technology = technology;
		this.version = version;
		this.description = description;
		this.coloursID = coloursID;
	}

    public int getId() {
        return technologiesID;
    }

    public void setId(int id) {
        this.technologiesID = id;
    }
    
    public int getcoloursID() {
        return coloursID;
    }

    public void setcoloursID(int coloursID) {
        this.coloursID = coloursID;
    }

    public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @Override
    public boolean equals(Object object) {
        if (object instanceof Technologies){
            Technologies technology = (Technologies) object;
            return technology.technologiesID == technologiesID;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return technologiesID;
    }
}
