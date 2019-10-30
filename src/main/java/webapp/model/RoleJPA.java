package webapp.model;

import java.math.BigInteger;
import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Role")
@Table(name = "roles")
public class RoleJPA{

	@Id
	@GeneratedValue
	private BigInteger id;
	
	private String name;
	
	public RoleJPA() {
	}
	
	public RoleJPA(String name) {
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
	
	public static Comparator<RoleJPA> nameComparator = new Comparator<RoleJPA>() {
		public int compare(RoleJPA r1, RoleJPA r2) {
			String RoleName1 = r1.getName().toUpperCase();
			String RoleName2 = r2.getName().toUpperCase();

			return RoleName1.compareTo(RoleName2);
		}
	};
}

