package interfaces;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import control.Control;
import control.DistributorControl;
import control.PlantControl;
import control.ServiceZoneControl;
import entities.Delivery;
import entities.Distributor;
import entities.HydraulicPlant;
import entities.NuclearPlant;
import entities.PanelType;
import entities.Plant;
import entities.SolarPlant;
import entities.ThermalPlant;

public class Menu {

	private String menu, addPlantMenu, updatePlantMenu;
	private Scanner scanner;
	private SimpleDateFormat dateFormat;
	private PlantControl plantControl;
	private DistributorControl distributorControl;
	private ServiceZoneControl serviceZoneControl;

	public Menu(PlantControl plantControl, DistributorControl distributorControl,
			ServiceZoneControl serviceZoneControl) {
		this.plantControl = plantControl;
		this.distributorControl = distributorControl;
		this.serviceZoneControl = serviceZoneControl;
		scanner = new Scanner(System.in);
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	}

	private void printMenu() {
		if (menu == null) {
			menu = "----MENU----\n";
			menu += "1) Add power plants\n";
			menu += "2) Update power plants\n";
			menu += "3) Add distributors\n";
			menu += "4) Update distributors\n";
			menu += "5) Add service zones\n";
			menu += "6) Update service zones\n";
			menu += "7) Add delivery\n";
			menu += "8) Search\n";
			menu += "9) EXIT\n";
		}
		System.out.println(menu);
	}

	public void startMenu() {
		String answ;
		do {
			printMenu();
			System.out.println("Option:");
			answ = scanner.nextLine();
			switch (answ) {
			case "1":
				menuAddPlant();
				break;
			case "2":
				menuUpdatePlant();
				break;
			case "3":

				break;
			case "4":

				break;
			case "5":

				break;
			case "6":

				break;
			case "7":
				addDeliveryMenu();
				break;
			case "8":

				break;
			case "9":
				closeProgram();
				break;
			default:
				System.out.println("Incorrect option.");
				break;
			}
		} while (!answ.equals("9"));
	}

	private void closeProgram() {
		System.out.println("BYE!");
		scanner.close();
		Control.closeOdb();
	}

	// START Add Plants
	private void printAddPlantMenu() {
		if (addPlantMenu == null) {
			addPlantMenu = "1) Add hydraulic plant\n";
			addPlantMenu += "2) Add solar plant\n";
			addPlantMenu += "3) Add nuclear plant\n";
			addPlantMenu += "4) Add thermal plant\n";
		}
		System.out.println(addPlantMenu);
	}

	private void menuAddPlant() {
		printAddPlantMenu();
		String answ = scanner.nextLine();
		switch (answ) {
		case "1":
			addPlant(HydraulicPlant.class);
			break;
		case "2":
			addPlant(SolarPlant.class);
			break;
		case "3":
			addPlant(NuclearPlant.class);
			break;
		case "4":
			addPlant(ThermalPlant.class);
			break;
		default:
			System.out.println("Incorrect option!");
			break;
		}
	}

	private void addPlant(Class plantType) {
		String name;
		Date date;
		double avgProduction, maxProduction;

		System.out.println("Type the plant's name: ");
		name = scanner.nextLine();
		if (name.length() == 0) {
			System.out.println("Name cannot be empty!");
			System.out.println("Operation cancelled.");
			return;
		}

		System.out.println("Type the avg. production:");
		try {
			avgProduction = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Number format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}

		System.out.println("Type the max. production:");
		try {
			maxProduction = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Number format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}

		System.out.println("Type the started working date (dd-mm-yyyy): ");
		try {
			date = dateFormat.parse(scanner.nextLine());
		} catch (ParseException e) {
			System.out.println("Date format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}

		if (plantType == HydraulicPlant.class) {
			double maxCapacity, occupation;
			int numTurbines;
			System.out.println("Type the max. capacity:");
			try {
				maxCapacity = Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}

			System.out.println("Type the occupation:");
			try {
				occupation = Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}

			System.out.println("Type the num. turbines:");
			try {
				numTurbines = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}

			HydraulicPlant hp = new HydraulicPlant(name, avgProduction, maxProduction, date, new ArrayList<Delivery>(),
					maxCapacity, occupation, numTurbines);

			plantControl.addPlant(hp);
			System.out.println("Added successfully!");
			return;
		}
		if (plantType == SolarPlant.class) {
			double solarPanelSurface, yearlyAvgSunHours;
			PanelType panelType;
			System.out.println("Type the panel's surface area:");
			try {
				solarPanelSurface = Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}

			System.out.println("Type the yearly average sun hours:");
			try {
				yearlyAvgSunHours = Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}

			System.out.println("Type the panel type:");
			for (int i = 0; i < PanelType.values().length; ++i) {
				System.out.println((i + 1) + ") " + PanelType.values()[i]);
			}
			try {
				int choice = Integer.parseInt(scanner.nextLine());
				if (choice > 0 && choice <= PanelType.values().length) {
					panelType = PanelType.values()[choice - 1];
				} else {
					System.out.println("Type not valid!");
					System.out.println("Operation cancelled.");
					return;
				}
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}

			SolarPlant sp = new SolarPlant(name, avgProduction, maxProduction, date, new ArrayList<Delivery>(),
					solarPanelSurface, yearlyAvgSunHours, panelType);

			plantControl.addPlant(sp);
			System.out.println("Added successfully!");
			return;
		}
		if (plantType == NuclearPlant.class) {
			int numReactors;
			double volConsumedPlutonium, volProducedWaste;
			System.out.println("Type the num. reactors:");
			try {
				numReactors = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}

			System.out.println("Type the vol. of consumed plutonium:");
			try {
				volConsumedPlutonium = Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}

			System.out.println("Type the vol. of produced waste:");
			try {
				volProducedWaste = Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}

			NuclearPlant np = new NuclearPlant(name, avgProduction, maxProduction, date, new ArrayList<Delivery>(),
					numReactors, volConsumedPlutonium, volProducedWaste);

			plantControl.addPlant(np);
			System.out.println("Added successfully!");
			return;
		}
		if (plantType == ThermalPlant.class) {
			int numFurnaces;
			double volConsumedCarbon, volGasEmissions;
			System.out.println("Type the num. of furances:");
			try {
				numFurnaces = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}

			System.out.println("Type the vol. of consumed carbon:");
			try {
				volConsumedCarbon = Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}

			System.out.println("Type the vol. of gas emissions:");
			try {
				volGasEmissions = Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}

			ThermalPlant tp = new ThermalPlant(name, avgProduction, maxProduction, date, new ArrayList<Delivery>(),
					numFurnaces, volConsumedCarbon, volGasEmissions);

			plantControl.addPlant(tp);
			System.out.println("Added successfully!");
			return;
		}

	}
	// END Add Plants

	// START Update Plants
	private void printUpdatePlantMenu() {
		if (updatePlantMenu == null) {
			updatePlantMenu = "1) Update hydraulic plants\n";
			updatePlantMenu += "2) Update solar plants\n";
			updatePlantMenu += "3) Update nuclear plants\n";
			updatePlantMenu += "4) Update thermal plants\n";
		}
		System.out.println(updatePlantMenu);
	}

	private void menuUpdatePlant() {
		printUpdatePlantMenu();
		String answ = scanner.nextLine();
		switch (answ) {
		case "1":
			updatePlant(HydraulicPlant.class);
			break;
		case "2":
			updatePlant(SolarPlant.class);
			break;
		case "3":
			updatePlant(NuclearPlant.class);
			break;
		case "4":
			updatePlant(ThermalPlant.class);
			break;
		default:
			System.out.println("Incorrect option!");
			break;
		}
	}

	private void updatePlant(Class plantType) {
		Objects<Object> objects = plantControl.getPlants(plantType);
		int size = objects.size();
		if (size == 0) {
			System.out.println("There are no plants available!");
			System.out.println("Operation cancelled.");
			return;
		}
		int i = 1;
		while (objects.hasNext()) {
			System.out.println(i + ") " + objects.next().toString());
			++i;
		}
		System.out.println("Choose the plant to update:");
		int choice;
		try {
			choice = Integer.parseInt(scanner.nextLine());
			if (choice < 1 || choice > size) {
				System.out.println("Index not valid!");
				System.out.println("Operation cancelled.");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("Number format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}

		Plant wantedPlant = null;
		i = 1;
		objects.reset();
		while (i <= choice && objects.hasNext()) {
			if (i == choice) {
				wantedPlant = (Plant) objects.next();
			}
			++i;
		}
		if (wantedPlant == null) {
			System.out.println("---ERROR: Plant not found after choice!");
			return;
		}
		System.out.println("Modifying: " + wantedPlant.toString());

		// UPDATE HYDRAULIC PLANT
		if (wantedPlant.getClass() == HydraulicPlant.class) {
			wantedPlant = (HydraulicPlant) wantedPlant;
			System.out.println("1) Modify avg. production");
			System.out.println("2) Modify occupation");
			try {
				choice = Integer.parseInt(scanner.nextLine());
				if (choice < 1 || choice > 2) {
					System.out.println("Index not valid!");
					System.out.println("Operation cancelled.");
					return;
				}
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}
			switch (choice) {
			case 1:
				System.out.println("Type the new avg. production:");
				double avgProduction;
				try {
					avgProduction = Double.parseDouble(scanner.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Number format not correct!");
					System.out.println("Operation cancelled.");
					return;
				}
				wantedPlant.setAvgProduction(avgProduction);
				break;
			case 2:
				System.out.println("Type the new occupation:");
				double occupation;
				try {
					occupation = Double.parseDouble(scanner.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Number format not correct!");
					System.out.println("Operation cancelled.");
					return;
				}
				((HydraulicPlant) wantedPlant).setOccupation(occupation);
				break;
			}
			System.out.println(wantedPlant.toString());
		}
		// UPDATE SOLAR PLANT
		if (wantedPlant.getClass() == SolarPlant.class) {
			wantedPlant = ((SolarPlant) wantedPlant);
			boolean changedPanelSurface = false;
			do {
				System.out.println("1) Modify avg. production");
				System.out.println("2) Modify max. production");
				System.out.println("3) Modify panel surface");
				System.out.println("4) Stop modifying plant");
				try {
					choice = Integer.parseInt(scanner.nextLine());
					if (choice < 1 || choice > 4) {
						System.out.println("Index not valid!");
						System.out.println("Operation cancelled.");
						return;
					}
				} catch (NumberFormatException e) {
					System.out.println("Number format not correct!");
					System.out.println("Operation cancelled.");
					return;
				}
				switch (choice) {
				case 1:
					System.out.println("Type the new avg. production");
					double avgProduction;
					try {
						avgProduction = Double.parseDouble(scanner.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Number format not correct!");
						System.out.println("Operation cancelled.");
						return;
					}
					wantedPlant.setAvgProduction(avgProduction);
					break;
				case 2:
					if (!changedPanelSurface) {
						System.out.println("Panel surface must be changed before changing max. production");
						break;
					}
					System.out.println("Type the new max. production:");
					double maxProduction;
					try {
						maxProduction = Double.parseDouble(scanner.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Number format not correct!");
						System.out.println("Operation cancelled.");
						return;
					}
					wantedPlant.setMaxProduction(maxProduction);
					changedPanelSurface = false;
					break;
				case 3:
					System.out.println("Type the new panel surface:");
					double solarPanelSurface;
					try {
						solarPanelSurface = Double.parseDouble(scanner.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Number format not correct!");
						System.out.println("Operation cancelled.");
						return;
					}
					((SolarPlant) wantedPlant).setSolarPanelSurface(solarPanelSurface);
					changedPanelSurface = true;
					break;
				case 4:
					System.out.println(wantedPlant.toString());
					break;
				}
			} while (choice != 4);
		} // UPDATE NUCLEAR PLANT
		if (wantedPlant.getClass() == NuclearPlant.class) {
			wantedPlant = (NuclearPlant) wantedPlant;
			System.out.println("1) Modify num. reactors");
			System.out.println("2) Modify avg. production");
			try {
				choice = Integer.parseInt(scanner.nextLine());
				if (choice < 1 || choice > 2) {
					System.out.println("Index not valid!");
					System.out.println("Operation cancelled.");
					return;
				}
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}
			switch (choice) {
			case 1:
				System.out.println("Type the new num. reactors:");
				int numReactors;
				try {
					numReactors = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Number format not correct!");
					System.out.println("Operation cancelled.");
					return;
				}

				System.out.println("Type the new vol. of consumed plutonium:");
				double volConsumedPlutonium;
				try {
					volConsumedPlutonium = Double.parseDouble(scanner.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Number format not correct!");
					System.out.println("Operation cancelled.");
					return;
				}

				System.out.println("Type the new vol. of nuclear waste:");
				double volNuclearWaste;
				try {
					volNuclearWaste = Double.parseDouble(scanner.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Number format not correct!");
					System.out.println("Operation cancelled.");
					return;
				}
				((NuclearPlant) wantedPlant).setNumReactors(numReactors);
				((NuclearPlant) wantedPlant).setVolConsumedPlutonium(volConsumedPlutonium);
				((NuclearPlant) wantedPlant).setVolProducedWaste(volNuclearWaste);
			case 2:
				System.out.println("Type the new avg. production:");
				double avgProduction;
				try {
					avgProduction = Double.parseDouble(scanner.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Number format not correct!");
					System.out.println("Operation cancelled.");
					return;
				}
				wantedPlant.setAvgProduction(avgProduction);
				break;
			}
			System.out.println(wantedPlant.toString());
		}
		// UPDATE THERMAL PLANT
		if (wantedPlant.getClass() == ThermalPlant.class) {
			wantedPlant = (ThermalPlant) wantedPlant;
			System.out.println("1) Modify avg. production");
			System.out.println("2) Modify num. furnaces");
			try {
				choice = Integer.parseInt(scanner.nextLine());
				if (choice < 1 || choice > 2) {
					System.out.println("Index not valid!");
					System.out.println("Operation cancelled.");
					return;
				}
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}
			switch (choice) {
			case 1:
				System.out.println("Type the new avg. production:");
				double avgProduction;
				try {
					avgProduction = Double.parseDouble(scanner.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Number format not correct!");
					System.out.println("Operation cancelled.");
					return;
				}
				wantedPlant.setAvgProduction(avgProduction);
				break;
			case 2:
				System.out.println("Type the new num. of furnaces:");
				int numFurnaces;
				try {
					numFurnaces = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Number format not correct!");
					System.out.println("Operation cancelled.");
					return;
				}
				System.out.println("Type the new vol. of consumed carbon:");
				double consumedCarabon;
				try {
					consumedCarabon = Double.parseDouble(scanner.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Number format not correct!");
					System.out.println("Operation cancelled.");
					return;
				}
				System.out.println("Type the new vol. of emitted gases:");
				double emittedGases;
				try {
					emittedGases = Double.parseDouble(scanner.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Number format not correct!");
					System.out.println("Operation cancelled.");
					return;
				}
				((ThermalPlant) wantedPlant).setNumFurnaces(numFurnaces);
				((ThermalPlant) wantedPlant).setVolConsumedCarbon(consumedCarabon);
				((ThermalPlant) wantedPlant).setVolGasEmissions(emittedGases);
				break;
			}
			System.out.println(wantedPlant.toString());
		}
		// SAVE CHANGES
		plantControl.addPlant(wantedPlant);
		System.out.println("Updated successfully!");
	}
	// END Update Plants

	// START Add Delivery
	// TODO: FALTA PROBAR
	private void addDeliveryMenu() {
		Plant plant = null;
		Distributor distributor = null;
		Date date;
		double quantity;

		Objects<Object> objects = plantControl.getAllPlants();
		int size = objects.size();
		if (size == 0) {
			System.out.println("There are no plants available!");
			System.out.println("Operation cancelled.");
			return;
		}
		int i = 1;
		while (objects.hasNext()) {
			System.out.println(i + ") " + objects.next().toString());
			++i;
		}
		System.out.println("Type the power plant delivering the power: ");
		int choice;
		try {
			choice = Integer.parseInt(scanner.nextLine());
			if (choice < 1 || choice > size) {
				System.out.println("Index not valid!");
				System.out.println("Operation cancelled.");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("Number format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}
		i = 1;
		objects.reset();
		while (i <= choice && objects.hasNext()) {
			if (i == choice) {
				plant = (Plant) objects.next();
			}
			++i;
		}
		if (plant == null) {
			System.out.println("---ERROR: Plant not found after choice!");
			return;
		}

		Objects<Distributor> distributors = distributorControl.getDistributors();
		size = distributors.size();
		if (size == 0) {
			System.out.println("There are no distributors available!");
			System.out.println("Operation cancelled.");
			return;
		}
		i = 1;
		while (distributors.hasNext()) {
			System.out.println(i + ") " + distributors.next().toString());
			++i;
		}
		System.out.println("Type the distributor who gets the power delivered: ");
		try {
			choice = Integer.parseInt(scanner.nextLine());
			if (choice < 1 || choice > size) {
				System.out.println("Index not valid!");
				System.out.println("Operation cancelled.");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("Number format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}
		i = 1;
		distributors.reset();
		while (i <= choice && distributors.hasNext()) {
			if (i == choice) {
				distributor = distributors.next();
			}
			++i;
		}
		if (distributor == null) {
			System.out.println("---ERROR: Distributor not found after choice!");
			return;
		}

		System.out.println("Type the date (dd-mm-yyyy): ");
		try {
			date = dateFormat.parse(scanner.nextLine());
		} catch (ParseException e) {
			System.out.println("Date format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}

		System.out.println("Type the quantity of energy delivered: ");
		try {
			quantity = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Number format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}

		Delivery delivery = new Delivery(quantity, date, distributor);
		plant.getDeliveries().add(delivery);
		plantControl.addPlant(plant);
		System.out.println("Delivery added successfully!");
	}
	// END Add Delivery

	// Parte Yevgeny

	//
	public void addDistributorMenu() {
		System.out.println("Type new distributor name");
		String name = scanner.nextLine();

		if (distributorControl.distributorExists(name)) {
			distributorControl.addDistributor(new Distributor(name, new ArrayList()));
			System.out.println("Distributor added successfully!");
		} else {
			System.out.println("There is already a distributor with that name!");
			System.out.println("Operation cancelled.");
		}
	}

	public void updateDistributorMenu() {
		System.out.println("1) Add new distribution network\n" + "2) Add new distribution line\n"
				+ "3) Delete distribution network\n" + "4) Delete distribution line\n" + "5) Delete distributor");

		String option = scanner.nextLine();
		switch (option) {
		case "1":

			break;
		case "2":

			break;
		case "3":

			break;
		case "4":

			break;
		case "5":

			break;

		default:
			System.out.println("Incorrect option!");
			break;
		}
	}
	
	public void addNetworkMenu() {
		Objects<Distributor> distributors = distributorControl.getDistributors();
		ArrayList list = new ArrayList();
		int i = 0, j = 0;
		
		while (distributors.hasNext()) {
			list.add(distributors.next());
			System.out.println((i + 1) + ")\t" + list.get(i).toString());
			++i;
		}
		System.out.println("Select the distributor");
		String index = scanner.nextLine();
		try {
			j = Integer.parseInt(index);
		} catch(NumberFormatException e) {
			System.out.println("Incorrect option!");
		}
		
	}

}
