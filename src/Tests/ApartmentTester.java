package Tests;

import java.util.ArrayList;

import Abstracts.BaseController;
import Abstracts.IRepository;
import Entities.ApartmentModel;
import Repositories.ApartmentRepository;

public class ApartmentTester extends BaseController {
	public ApartmentTester(){
		super();
	}

	public void ExecuteTests() throws Exception{
		//Create();
		//GetAll();
		//GetByID(8);
		//Update(1);
		//DeleteByID(3);
	}

	private void Create() throws Exception{
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);

		ApartmentModel model = new ApartmentModel();
		model.ApartmentNumber = 6;

		aRepo.Create(model);
	}

	private void GetAll() throws Exception{
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		ArrayList<ApartmentModel> apartmentList = aRepo.GetAll();

		for(ApartmentModel apartment : apartmentList){
			System.out.println("ID: " + apartment.ID + " , Apartment Number: " + apartment.ApartmentNumber);
		}
	}

	private void GetByID(int id) throws Exception{
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		ApartmentModel apartment = aRepo.GetByID(id);

		if(apartment != null)
			System.out.println("ID: " + apartment.ID + " , Apartment Number: " + apartment.ApartmentNumber);
		else
			System.out.println("No such ID exist in apartment database.");
	}

	private void Update(int id) throws Exception{
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		ApartmentModel apartment = aRepo.GetByID(id);

		apartment.ApartmentNumber = 665;

		aRepo.Update(apartment);
	}

	private void DeleteByID(int id) throws Exception{
		IRepository<ApartmentModel> aRepo = new ApartmentRepository(settings);
		aRepo.DeleteByID(id);
	}
}
