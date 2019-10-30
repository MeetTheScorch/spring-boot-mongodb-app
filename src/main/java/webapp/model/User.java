package webapp.model;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

public interface User {

	public BigInteger getId();
	public void setId(BigInteger id);

	public String getUsername();
	public void setUsername(String name);

	public String getEmail();
	public void setEmail(String email);

	public String getPassword();
	public void setPassword(String password);

	public String getPasswordConfirm();
	public void setPasswordConfirm(String passwordConfirm);
	
	public String getPasswordOld();
	public void setPasswordOld(String passwordOld);

	public List<Role> getRoles();
	public void setRoles(List<Role> roles);

	public String rolesToString();

	public static Comparator<User> nameComparator = new Comparator<User>() {
		public int compare(User u1, User u2) {
			String UserName1 = u1.getUsername().toUpperCase();
			String UserName2 = u2.getUsername().toUpperCase();

			return UserName1.compareTo(UserName2);
		}
	};
}
