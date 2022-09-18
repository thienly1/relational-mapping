package se.lexicon.relationalmapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.relationalmapping.dao.AddressDao;
import se.lexicon.relationalmapping.dao.AppUserDao;
import se.lexicon.relationalmapping.dao.CarDao;
import se.lexicon.relationalmapping.dao.StatusDao;
import se.lexicon.relationalmapping.entity.Address;
import se.lexicon.relationalmapping.entity.AppUser;
import se.lexicon.relationalmapping.entity.Car;
import se.lexicon.relationalmapping.entity.Status;
import se.lexicon.relationalmapping.repository.AddressRepository;
import se.lexicon.relationalmapping.repository.AppUserRepository;
import se.lexicon.relationalmapping.repository.CarRepository;
import se.lexicon.relationalmapping.repository.StatusRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@SpringBootApplication
public class RelationalMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelationalMappingApplication.class, args);
	}

	@Profile(value = "dev")
	@Transactional
	@Component
	class myCommandLineRunner implements CommandLineRunner{

		private final EntityManager entityManager;
		private final AppUserDao appUserDao;
		private final AddressDao addressDao;
		private final CarDao carDao;
		private final StatusDao statusDao;
		private final AddressRepository addressRepository;
		private final AppUserRepository appUserRepository;
		private final StatusRepository statusRepository;
		private final CarRepository carRepository;

		@Autowired
		public myCommandLineRunner(EntityManager entityManager, AppUserDao appUserDao, AddressDao addressDao, CarDao carDao, StatusDao statusDao, AddressRepository addressRepository, AppUserRepository appUserRepository, StatusRepository statusRepository, CarRepository carRepository) {
			this.entityManager= entityManager;
			this.appUserDao = appUserDao;
			this.addressDao = addressDao;
			this.carDao = carDao;
			this.statusDao = statusDao;
			this.addressRepository = addressRepository;
			this.appUserRepository = appUserRepository;
			this.statusRepository = statusRepository;
			this.carRepository = carRepository;
		}
		@Override
		public void run(String... args) throws Exception {

			Address address1 = new Address("Borgmästaregatan", "35236", "växjö");
			Address address2 = new Address("storgatan", "35232", "växjö");

			AppUser appUser1 = new AppUser("ly@mail.com", "ly", "135246");
			AppUser appUser2= new AppUser("mai@mail.com", "mai", "123456");
			appUserDao.save(appUser1);
			appUserDao.save(appUser2);
			appUser1.setAddress(address2);
			appUser2.setAddress(address1);
			entityManager.flush();
			System.out.println("appUser1 = " + appUser1);
			System.out.println("appUser2 = " + appUser2);

			Car car1 = carDao.save(new Car("ABC123", "volvo", "2012", LocalDate.of(2012,5,23)));
			Car car2= carDao.save(new Car("NBC123", "Toyota", "2020", LocalDate.of(2020,4,12)));
			Car car3 = carDao.save(new Car("XYZ123", "volvo", "2022", LocalDate.of(2022,9,16)));

			appUser1.addNewCar(car1);
			appUser1.addNewCar(car2);
			appUser1.getOwnedCars().forEach(System.out::println);

			Status status1= statusDao.save(new Status("Coding"));
			Status status2 = statusDao.save(new Status("Done"));
			Status status3 = statusDao.save(new Status("haven't started yet"));

			status1.addCar(car3);
			status1.addCar(car1);
			status2.addCar(car1);
			status2.addCar(car2);
			status3.addCar(car3);
			status3.addCar(car2);

			System.out.println("-----------CRUD Repository-------------------");

			addressRepository.findAllByCity("växjö").forEach(System.out::println);

			System.out.println(appUserRepository.findByEmailIgnoreCase("mai@mail.com"));
			System.out.println(appUserRepository.findAppUserByEmailAndPassword("mai@mail.com", "123456"));
			appUserRepository.findByNameContaining("ma").forEach(System.out::println);
			appUserRepository.findAllByAddress_AddressId(1).forEach(System.out::println);
			appUserRepository.findAllByAddress_City("växjö").forEach(System.out::println);

			System.out.println(carRepository.findCarByRegNumberIs("ABC123"));
			carRepository.findAllByRegDateAfter(LocalDate.of(2019,6,12)).forEach(System.out::println);
			carRepository.findAllByRegDateBefore(LocalDate.of(2020,12,12)).forEach(System.out::println);
			carRepository.findAllByRegDateBetween(LocalDate.of(2018,12,12),
					LocalDate.of(2021,1,12)).forEach(System.out::println);
			carRepository.findAllByStatusCodesContains(status1).forEach(System.out::println);






		}
	}

}
