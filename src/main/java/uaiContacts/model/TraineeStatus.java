package uaiContacts.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="traineeStatus")
public class TraineeStatus {

    @Id
    @GeneratedValue
    private int traineeStatusId;
	private String value;
	
	public TraineeStatus(){
		
	}
	
	public TraineeStatus(String value, int id) {
		super();
		this.value = value;
	}

    public int getId() {
        return traineeStatusId;
    }

    public void setId(int id) {
        this.traineeStatusId = id;
    }

    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

    @Override
    public boolean equals(Object object) {
        if (object instanceof TraineeStatus){
            TraineeStatus value = (TraineeStatus) object;
            return value.traineeStatusId == traineeStatusId;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return traineeStatusId;
    }
}
