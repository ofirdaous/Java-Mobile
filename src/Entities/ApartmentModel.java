package Entities;

import Enums.TenantType;

public class ApartmentModel {
	public int id;
	public int apartmentNumber;
	public int yearsSeniority;
	public String firstName;
	public String lastName;
	public String identityNumber;
	public TenantType tenantType = TenantType.Tenant;
}
