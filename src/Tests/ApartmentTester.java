package Tests;

import java.util.ArrayList;

import Abstracts.BaseController;
import Abstracts.IRepository;
import Entities.ApartmentModel;
import Repositories.ApartmentRepository;

public class ApartmentTester extends BaseController {
	public ApartmentTester() throws Exception {
		super();
	}

	public void ExecuteTests() throws Exception {
		// create();
		// getAll();
		// getByID(8);
		// update(1);
		// deleteByID(3);
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
			System.out.println("ID: " + apartment.id + " , Apartment Number: " + apartment.apartmentNumber);
		}
	}

	private void getByID(int id) throws Exception {
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		ApartmentModel apartment = aRepo.getByID(id);

		if (apartment != null)
			System.out.println("ID: " + apartment.id + " , Apartment Number: " + apartment.apartmentNumber);
		else
			System.out.println("No such ID exist in apartment database.");
	}

	private void update(int id) throws Exception {
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		ApartmentModel apartment = aRepo.getByID(id);

		apartment.apartmentNumber = 665;

		aRepo.update(apartment);
	}

	private void deleteByID(int id) throws Exception {
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		aRepo.deleteByID(id);
	}
}
