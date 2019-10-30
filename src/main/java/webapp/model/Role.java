package webapp.model;

import java.math.BigInteger;
import java.util.Comparator;

public interface Role {

	BigInteger getId();
	void setId(BigInteger id);

	String getName();
	void setName(String name);
	
	public static Comparator<Role> nameComparator = new Comparator<Role>() {
		public int compare(Role r1, Role r2) {
			String RoleName1 = r1.getName().toUpperCase();
			String RoleName2 = r2.getName().toUpperCase();

			return RoleName1.compareTo(RoleName2);
		}
	};
}

