package com.example.oops;

class Role
{
	private String roleType;
	private int roleCode = 1001;

	public String getRoleType() {
	
		return roleType;
	}
	
	
  // Reading purpose
	public int getRoleCode() {
		return roleCode;
	}


	public void setRoleType(String roleType) {
		
		if(roleType.equalsIgnoreCase("Admin") || roleType.equalsIgnoreCase("User"))
		{
			System.out.println("You have entered correct roletype now enter password:");
			this.roleType = roleType;
			
		}
		else 
		{
			
			System.out.println(" roletype is not correct:");
		}
		
		
		
	}
	
	



}


public class EncapsulationExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Role r = new Role();
		System.out.println("Role Code :" + r.getRoleCode());
	
		r.setRoleType("User");
		System.out.println("Role Type : " + r.getRoleType());

	}

}
