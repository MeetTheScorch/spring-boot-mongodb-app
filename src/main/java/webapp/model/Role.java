package webapp.model;

import java.util.Comparator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
public class Role {

	@Id
	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	public static Comparator<Role> nameComparator = new Comparator<Role>() {
		public int compare(Role r1, Role r2) {
			String RoleName1 = r1.getName().toUpperCase();
			String RoleName2 = r2.getName().toUpperCase();

			return RoleName1.compareTo(RoleName2);
		}
	};
}

