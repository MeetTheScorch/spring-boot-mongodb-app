package webapp.model;

import java.util.Comparator;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

	@Id
	private String id;

	private String username;

	private String email;

	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@Transient
	private String passwordOld;

	private List<Role> roles;

	public User() {
	}

	public User(String username, String email, String password, String passwordConfirm,
			String passwordOld, List<Role> roles) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwordOld = passwordOld;
		this.passwordConfirm = passwordConfirm;
		this.roles = roles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getPasswordOld() {
		return passwordOld;
	}

	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String rolesToString() {
		String result = "";
		for (Role role : roles) {
			result += role.getName();
			if (roles.indexOf(role) != roles.size() - 1)
				result += ", ";
		}
		return result;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", roles=" + rolesToString()
				+ ", pass=" + password + ", cpass=" + passwordConfirm + ", opass=" + passwordOld + "}";
	}

	public static Comparator<User> nameComparator = new Comparator<User>() {
		public int compare(User u1, User u2) {
			String UserName1 = u1.getUsername().toUpperCase();
			String UserName2 = u2.getUsername().toUpperCase();

			return UserName1.compareTo(UserName2);
		}
	};
}
