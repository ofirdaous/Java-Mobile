package Tests;

import java.util.ArrayList;

import Abstracts.BaseController;
import Abstracts.IRepository;
import Entities.ApartmentModel;
import Enums.TenantType;
import Repositories.ApartmentRepository;

public class ApartmentTester extends BaseController {
	public ApartmentTester() throws Exception {
		super();
	}

	public void ExecuteTests() throws Exception {
		// create();
		// getAll();
		// getByID(3);
		// update(4);
		// deleteByID(1);
	}

	private void create() throws Exception {
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);

		ApartmentModel model = new ApartmentModel();
		model.apartmentNumber = 6;

		aRepo.create(model);
	}

	private void getAll() throws Exception {
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		ArrayList<ApartmentModel> apartmentList = aRepo.getAll();

		for (ApartmentModel apartment : apartmentList) {
			System.out.println("ID: " + apartment.id + " , Apartment Number: " + apartment.apartmentNumber
					+ ", First Name: " + apartment.firstName + ", Last Name: " + apartment.lastName
					+ ", Identity Numer: " + apartment.identityNumber + ", Years Seniority: " + apartment.yearsSeniority
					+ ", Type: " + apartment.tenantType);
		}
	}

	private void getByID(int id) throws Exception {
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		ApartmentModel apartment = aRepo.getByID(id);

		if (apartment != null)
			System.out.println("ID: " + apartment.id + " , Apartment Number: " + apartment.apartmentNumber
					+ ", First Name: " + apartment.firstName + ", Last Name: " + apartment.lastName
					+ ", Identity Numer: " + apartment.identityNumber + ", Years Seniority: " + apartment.yearsSeniority
					+ ", Type: " + apartment.tenantType);
		else
			System.out.println("No such ID exist in apartment database.");
	}

	private void update(int id) throws Exception {
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		ApartmentModel apartment = aRepo.getByID(id);

		apartment.apartmentNumber = 55;
		apartment.firstName = "Eli";
		apartment.lastName = "Bottle";
		apartment.identityNumber = "012345678";
		apartment.yearsSeniority = 5;
		apartment.tenantType = TenantType.Tenant;

		aRepo.update(apartment);
	}

	private void deleteByID(int id) throws Exception {
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		aRepo.deleteByID(id);
	}
}
