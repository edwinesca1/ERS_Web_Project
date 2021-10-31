package com.revature.models;

public class ReimbursementDisplay {
	
	private int reimbId;
	private double amount;
	private String reimbSubmitted;
	private String reimbResolved;
	private String description;
	private int reimbAuthor;
	private int reimbResolver;
	private int reimbStatusId;
	private int reimbTypeId;
	private String aName;
	private String aLastname;
	private String afullName;
	private String rName;
	private String rLastname;
	private String rfullName;
	private String reimbStatus;
	private String reimbType;
	
	public ReimbursementDisplay() {
		
	}
	
	public ReimbursementDisplay(int reimbId, double amount, String reimbSubmitted, String reimbResolved, String description,
			int reimbAuthor, int reimbResolver, int reimbStatusId, int reimbTypeId, String aName, String aLastname,
			String rName, String rLastname, String reimbStatus, String reimbType) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.description = description;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.aName = aName;
		this.aLastname = aLastname;
		this.afullName = aName + " " + aLastname;
		this.rName = rName;
		this.rLastname = rLastname;
		this.rfullName = rName + " " + rLastname;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReimbSubmitted() {
		return reimbSubmitted;
	}

	public void setReimbSubmitted(String reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}

	public String getReimbResolved() {
		return reimbResolved;
	}

	public void setReimbResolved(String reimbResolved) {
		this.reimbResolved = reimbResolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReimbAuthor() {
		return reimbAuthor;
	}

	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}

	public int getReimbResolver() {
		return reimbResolver;
	}

	public void setReimbResolver(int reimbResolver) {
		this.reimbResolver = reimbResolver;
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getaLastname() {
		return aLastname;
	}

	public void setaLastname(String aLastname) {
		this.aLastname = aLastname;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getrLastname() {
		return rLastname;
	}

	public void setrLastname(String rLastname) {
		this.rLastname = rLastname;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public String getReimbType() {
		return reimbType;
	}

	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}

	@Override
	public String toString() {
		return "ReimbursementDisplay [reimbId=" + reimbId + ", amount=" + amount + ", reimbSubmitted=" + reimbSubmitted
				+ ", reimbResolved=" + reimbResolved + ", description=" + description + ", reimbAuthor=" + reimbAuthor
				+ ", reimbResolver=" + reimbResolver + ", reimbStatusId=" + reimbStatusId + ", reimbTypeId="
				+ reimbTypeId + ", aName=" + aName + ", aLastname=" + aLastname + ", aFullName=" + afullName + ", rName=" + rName + ", rLastname="
				+ rLastname + ", rFullName=" + rfullName + ", reimbStatus=" + reimbStatus + ", reimbType=" + reimbType + "]";
	}

	public String getRfullName() {
		return rfullName;
	}

	public void setRfullName(String rfullName) {
		this.rfullName = rfullName;
	}

	public String getAfullName() {
		return afullName;
	}

	public void setAfullName(String afullName) {
		this.afullName = afullName;
	}
	
}
