package uaiContacts.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Trainees {

    @Id
    @GeneratedValue
    private int traineesID;
    @ManyToOne
    private Technologies technologies;
    @ManyToOne
    private TraineeStatus traineestatus;
    private int customersID;
	private String name;
	private String surname;
	private String phoneDetails;
	private String email;
	
	public Trainees(){
		
	}
	
	public Trainees(String name, String surname, String phoneDetails, String email, int id, Technologies technologies, TraineeStatus traineestatus, int customersID) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phoneDetails = phoneDetails;
		this.technologies = technologies;
		this.traineestatus = traineestatus;
		this.customersID = customersID;
	}

    public int getId() {
        return traineesID;
    }

    public void setId(int id) {
        this.traineesID = id;
    }
    
    public Technologies gettechnologies() {
        return technologies;
    }

    public void settechnologies(Technologies technologies) {
        this.technologies = technologies;
    }
    
    public TraineeStatus gettraineestatus() {
        return traineestatus;
    }

    public void settraineestatus(TraineeStatus traineestatus) {
        this.traineestatus = traineestatus;
    }
    
    public int getcustomersID() {
        return customersID;
    }

    public void setcustomersID(int customersID) {
        this.customersID = customersID;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneDetails() {
		return phoneDetails;
	}

	public void setPhoneDetails(String phoneDetails) {
		this.phoneDetails = phoneDetails;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    @Override
    public boolean equals(Object object) {
        if (object instanceof Trainees){
            Trainees contact = (Trainees) object;
            return contact.traineesID == traineesID;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return traineesID;
    }
}
