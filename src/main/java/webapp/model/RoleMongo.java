package webapp.model;

import java.math.BigInteger;
import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

@Entity(name = "Role")
@Table(name = "roles")
//@Document(collection = "role")
public class RoleMongo{

	@Id
	private BigInteger id;
	
	private String name;
	
	public RoleMongo() {
	}
	
	public RoleMongo(String name) {
		super();
		this.name = name;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role{" + "id=" + id + ", name=" + name + "}";
	}
	
	public static Comparator<RoleMongo> nameComparator = new Comparator<RoleMongo>() {
		public int compare(RoleMongo r1, RoleMongo r2) {
			String RoleName1 = r1.getName().toUpperCase();
			String RoleName2 = r2.getName().toUpperCase();

			return RoleName1.compareTo(RoleName2);
		}
	};
}

