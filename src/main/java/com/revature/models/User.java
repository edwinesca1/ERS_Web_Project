package com.revature.models;

public class User {

		private int userId;
		private String username;
		private String password;
		private String fName;
		private String lName;
		private String email;
		private int userRole;
		
		public User() {
			
		}
		
		public User(int userId, String username, String password, String fName, String lName, String email,
				int userRole) {
			super();
			this.userId = userId;
			this.username = username;
			this.password = password;
			this.fName = fName;
			this.lName = lName;
			this.email = email;
			this.userRole = userRole;
		}

		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getfName() {
			return fName;
		}
		public void setfName(String fName) {
			this.fName = fName;
		}
		public String getlName() {
			return lName;
		}
		public void setlName(String lName) {
			this.lName = lName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getUserRole() {
			return userRole;
		}
		public void setUserRole(int userRole) {
			this.userRole = userRole;
		}
		
		@Override
		public String toString() {
			return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", fName=" + fName
					+ ", lName=" + lName + ", email=" + email + ", userRole=" + userRole + "]";
		}
}
