package webapp.model;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "User")
@Table(name = "users")
public class UserJPA{

	@Id
	@GeneratedValue
	private BigInteger id;

	private String username;

	private String email;

	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@Transient
	private String passwordOld;

	@ManyToMany
	@JoinTable(name = "privileges")
	private List<RoleJPA> roles;

	public UserJPA() {
	}

	public UserJPA(String username, String email, String password, String passwordConfirm,
			String passwordOld, List<RoleJPA> roles) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwordOld = passwordOld;
		this.passwordConfirm = passwordConfirm;
		this.roles = roles;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
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

	public List<RoleJPA> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleJPA> roles) {
		this.roles = roles;
	}

	public String rolesToString() {
		String result = "";
		for (RoleJPA role : roles) {
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

	public static Comparator<UserJPA> nameComparator = new Comparator<UserJPA>() {
		public int compare(UserJPA u1, UserJPA u2) {
			String UserName1 = u1.getUsername().toUpperCase();
			String UserName2 = u2.getUsername().toUpperCase();

			return UserName1.compareTo(UserName2);
		}
	};
}
