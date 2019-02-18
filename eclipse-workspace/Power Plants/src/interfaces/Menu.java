package interfaces;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.neodatis.odb.Objects;

import control.Control;
import control.DistributorControl;
import control.PlantControl;
import control.ServiceZoneControl;
import entities.Delivery;
import entities.DistributionLine;
import entities.DistributionNetwork;
import entities.Distributor;
import entities.HydraulicPlant;
import entities.NuclearPlant;
import entities.PanelType;
import entities.Plant;
import entities.ServiceZone;
import entities.SolarPlant;
import entities.ThermalPlant;
import util.PlantTypeParser;

public class Menu {

	private String menu, addPlantMenu, updatePlantMenu, searchMenu;
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
				addDistributorMenu();
				break;
			case "4":
				updateDistributorMenu();
				break;
			case "5":
				addServiceZoneMenu();
				break;
			case "6":
				updateZoneMenu();
				break;
			case "7":
				addDeliveryMenu();
				break;
			case "8":
				searchMenu();
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
		// COMMON ATTRIBUTES FOR ALL PLANTS
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
		
		if(plantControl.plantExists(name)) {
			System.out.println("A plant with that name already exists!");
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
		// ATTRIBUTES FOR HYDRAULIC PLANTS
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
		// ATTRIBUTES FOR SOLAR PLANTS
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
		// ATTRIBUTES FOR NUCLEAR PLANTS
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
		// ATTRIBUTES FOR THERMAL PLANTS
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
			System.out.println("There are no plants of this type available!");
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
			// Shouldn't happen
			System.out.println("---ERROR: Plant not found after choice!---");
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
		}
		// UPDATE NUCLEAR PLANT
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
			Object object = objects.next();
			System.out.println(i + ") (" + PlantTypeParser.parseType(object.getClass()) + ") " + object.toString());
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

		//
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
		ArrayList<Delivery> deliveries = (ArrayList<Delivery>) plant.getDeliveries();
		deliveries.add(delivery);
		plant.setDeliveries(deliveries);
		// TODO: NO FUNCIONA
		System.out.println(plant.toString());
		plantControl.addPlant(plant);
		System.out.println("Delivery added successfully!");
	}
	// END Add Delivery
	
	// START QUERIES
	private void printSearchMenu() {
		if (searchMenu == null) {
			searchMenu = "Available queries:\n";
			searchMenu += "1) Search for plants opened before date\n";
			searchMenu += "2) Search for service zones by number of distribution lines\n";
			searchMenu += "3) Search for distributors by received energy from solar or thermal plants\n";
			searchMenu += "4) Search for deliveries from nuclear plants by date and nuclear waste\n";
		}
		System.out.println(searchMenu);
	}

	private void searchMenu() {
		printSearchMenu();
		switch (scanner.nextLine()) {
		case "1":
			searchPlantsDateMenu();
			break;
		case "2":
			searchZonesNumLinesMenu();
			break;
		case "3":
			searchDistributorsSolarThermalMenu();
			break;
		case "4":
			searchDeliveriesNuclearPlantsMenu();
			break;
		default:
			System.out.println("Invalid option!");
			break;
		}
	}

	private void searchPlantsDateMenu() {
		Date date;
		System.out.println("Type the date (dd-mm-yyyy): ");
		try {
			date = dateFormat.parse(scanner.nextLine());
		} catch (ParseException e) {
			System.out.println("Date format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}

		ArrayList<Plant> plants = plantControl.getPlantsBeforeDate(date);
		int size = plants.size();
		if (plants == null || size == 0) {
			System.out.println("There are no plants opened before that date.");
			return;
		}

		System.out.println("(" + size + ") plants opened before " + date.toString() + ": ");
		for (Plant plant : plants) {
			System.out.println("Name: " + plant.getName());
			System.out.println("Type: " + PlantTypeParser.parseType(plant.getClass()));
			System.out.println("Avg. production: " + plant.getAvgProduction());
			System.out.println("Max. production: " + plant.getMaxProduction());
			System.out.println("Date opened: " + plant.getStartedWorkingDate());
			System.out.println("");
		}
	}
	
	private void searchZonesNumLinesMenu() {
		System.out.println("Type the minimum number of lines (not including): ");
		int numLines;
		try {
			numLines = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Number format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}

		ArrayList<ServiceZone> zones = serviceZoneControl.getZonesNumLines(numLines, distributorControl);

		int size = zones.size();
		if (size == 0) {
			System.out.println("There are no zones with more lines than specified.");
			return;
		}

		System.out.println("(" + size + ") zones with more than " + numLines + " lines:");
		for (ServiceZone current : zones) {
			System.out.println("Name: " + current.getName());
			System.out.println("Avg. Consumption: " + current.getAvgConsumption());
			System.out.println("Num. Consumers: " + current.getNumConsumers());
			System.out.println("");
		}
	}

	// TODO: FALTA PROBAR
	private void searchDistributorsSolarThermalMenu() {
		ArrayList<Distributor> distributors = plantControl.getSolarThermalDistributors();
		int size = distributors.size();
		if (size == 0) {
			System.out.println("There are no zones with more lines than specified.");
			return;
		}

		System.out.println("(" + size + ") distributors: ");
		for (Distributor current : distributors) {
			System.out.println("Name: " + current.getName());
		}
	}

	// TODO: FALTA PROBAR
	private void searchDeliveriesNuclearPlantsMenu() {
		System.out.println("Type the vol. of produced waste (more than):");
		double waste;
		try {
			waste = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Number format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}

		Date date;
		System.out.println("Type the date (dd-mm-yyyy) (after this date): ");
		try {
			date = dateFormat.parse(scanner.nextLine());
		} catch (ParseException e) {
			System.out.println("Date format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}

		ArrayList<Delivery> deliveries = plantControl.getDeliveriesByWasteAndDate(waste, date);
		int size = deliveries.size();
		if (size == 0) {
			System.out.println("There are no deliveries with the specified data.");
			return;
		}

		System.out.println("(" + size + ") deliveries found: ");
		for (Delivery current : deliveries) {
			System.out.println(current.toString());
		}
	}
	// END QUERIES

	// Parte Yevgeny

	// add distributor
	private void addDistributorMenu() {
		System.out.println("Type new distributor name");
		String name = scanner.nextLine();

		if (distributorControl.findDistributor(name) == null && !name.equals("")) {
			distributorControl.addDistributor(new Distributor(name, new ArrayList<DistributionNetwork>()));
			System.out.println("Distributor added successfully!");
		} else {
			System.out.println("There is already a distributor with that name or its empty");
			System.out.println("Operation cancelled.");
		}
	}

	// update distributor
	private void updateDistributorMenu() {
		Distributor distributor = selectDistributor();

		if (distributor != null) {
			System.out.println("1) Add new distribution network\n" + "2) Add new distribution line\n"
					+ "3) Delete distribution network\n" + "4) Delete distribution line\n" + "5) Delete distributor");
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				addNetwork(distributor);
				break;
			case "2":
				addLine(distributor);
				break;
			case "3":
				deleteNetwork(distributor);
				break;
			case "4":
				deleteLine(distributor);
				break;
			case "5":
				deleteDistributor(distributor);
				break;
			default:
				System.out.println("Incorrect option!");
				break;
			}
		}
	}

	private void addNetwork(Distributor distributor) {
		distributorControl.addNetwork(distributor);
		System.out.println("Empty network added successfully");
	}

	private void addLine(Distributor distributor) {
		DistributionNetwork network = selectDistributionNetwork(distributor);
		if (network != null) {
			distributorControl.addLine(network);
			System.out.println("Empty line added successfully");
		}
	}

	private void deleteLine(Distributor distributor) {
		DistributionNetwork network = selectDistributionNetwork(distributor);
		if (network != null) {
			DistributionLine line = selectDistributionLine(network);
			if (line != null) {
				distributorControl.deleteLine(line, network);
				System.out.println("Line deleted successfully");
			}
		}
	}

	private void deleteNetwork(Distributor distributor) {
		DistributionNetwork network = selectDistributionNetwork(distributor);

		if (network != null) {
			if (!network.getLines().isEmpty()) {
				System.out.println("Atention! \nNetwork contain elements. Continue(y/n)");
				String option = scanner.nextLine();
				while (!option.equals("y") && !option.equals("n")) {
					System.out.println("Invalid option");
				}
				if (option.equals("y")) {
					distributorControl.deleteNetwork(network, distributor);
					System.out.println("Network deleted successfully");
					return;
				} else {
					System.out.println("Operation cancelled");
					return;
				}
			}
			distributorControl.deleteNetwork(network, distributor);
			System.out.println("Network deleted successfully");
		}
	}

	private void deleteDistributor(Distributor distributor) {
		if (!distributor.getNetworks().isEmpty()) {
			System.out.println("Atention! \nDistributor contain elements. Continue(y/n)");
			String option = scanner.nextLine();
			while (!option.equals("y") && !option.equals("n")) {
				System.out.println("Invalid option");
			}
			if (option.equals("y")) {
				distributorControl.deleteDistributor(distributor);
				System.out.println("Distributor deleted successfully");
			} else {
				System.out.println("Operation cancelled");
			}
		} else {
			distributorControl.deleteDistributor(distributor);
			System.out.println("Distributor deleted successfully");
		}
	}

	// add service zone
	private void addServiceZoneMenu() {
		ServiceZone newZone = new ServiceZone(null, 0, 0, null);

		System.out.println("Type new service zone name");
		String name = scanner.nextLine().trim();
		if (name.equals("") || serviceZoneControl.findServiceZone(name) != null) {
			System.out.println("Name already exissts or its empty \ntry again");
			return;
		} else {
			newZone.setName(name);
		}

		System.out.println("Type number of consumers for new service zone");
		int numConsumers;
		try {
			numConsumers = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Please type a number\nOperation cancelled");
			return;
		}
		newZone.setNumConsumers(numConsumers);

		System.out.println("Type province for new service zone");
		newZone.setProvince(scanner.nextLine());

		System.out.println("Type average consumption for new service zone");
		double avgConsumption;
		try {
			avgConsumption = Double.parseDouble(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Please type a number\nOperation cancelled");
			return;
		}
		newZone.setAvgConsumption(avgConsumption);

		serviceZoneControl.addZone(newZone);
		System.out.println("New Service Zone added successully");
	}

	// update service zone
	private void updateZoneMenu() {
		ServiceZone zone = selectServiceZone();
		
		if (zone != null) {
			System.out.println("1) Modify average consumption\n" + "2) Modify consumers number\n"
					+ "3) Register new distribution line\n" + "4) Remove existing distribution line\n");
			String option = scanner.nextLine();

			switch (option) {
			case "1":
				updateAvgConsumption(zone);
				break;
			case "2":
				updateNumConsumers(zone);
				break;
			case "3":
				registerDistributionLine(zone);
				break;
			case "4":
				removeDistributionLine(zone);
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
		}
	}

	private void updateNumConsumers(ServiceZone zone) {
		System.out.println("Type number of consumers for the service zone");
		int numConsumers;
		try {
			numConsumers = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Please type a number\nOperation cancelled");
			return;
		}
		zone.setNumConsumers(numConsumers);
		serviceZoneControl.addZone(zone);
		System.out.println("Update complete");
	}

	private void updateAvgConsumption(ServiceZone zone) {
		System.out.println("Type average consumption for the service zone");
		double avgConsumption;
		try {
			avgConsumption = Double.parseDouble(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Please type a number\nOperation cancelled");
			return;
		}
		zone.setAvgConsumption(avgConsumption);
		serviceZoneControl.addZone(zone);
		System.out.println("Update complete");
	}

	private void registerDistributionLine(ServiceZone zone) {
		Distributor distributor = selectDistributor();
		if (distributor != null) {
			DistributionNetwork network = selectDistributionNetwork(distributor);
			if (network != null) {
				DistributionLine line = selectDistributionLine(network);
				if (line != null) {
					if (!line.getZones().isEmpty()) {
						for (ServiceZone z : line.getZones()) {
							if (z.equals(zone)) {
								System.out.println("This line already registered for that zone \nOperation cancelled");
							} else {
								serviceZoneControl.registerDistributionLine(zone, line);
								System.out.println("Distribution line added");
							}
						}
					} else {
						serviceZoneControl.registerDistributionLine(zone, line);
						System.out.println("Distribution line added");
					}
				}
			}
		}
	}

	private void removeDistributionLine(ServiceZone zone) {
		Distributor distributor = selectDistributor();
		if (distributor != null) {
			DistributionNetwork network = selectDistributionNetwork(distributor);
			if (network != null) {
				DistributionLine line = selectDistributionLine(network);
				if (line != null) {
					if (!line.getZones().isEmpty()) {
						for (ServiceZone z : line.getZones()) {
							if (z.equals(zone)) {
								serviceZoneControl.deleteDistributionLine(zone, line);
								System.out.println("Distribution line removed");
								return;
							} else {
								System.out.println("This line is not registered \nOperation cancelled");
								return;
							}
						}
					} else {
						System.out.println("This line don't have any zone registered \nOperation cancelled");
					}
				}
			}
		}
	}
	
	private ServiceZone selectServiceZone() {
		int i = 0;
		int index = 1;
		Objects<ServiceZone> zones = serviceZoneControl.getServiceZones();

		System.out.println("Select the service zone");
		while (zones.hasNext()) {
			ServiceZone zone = zones.next();
			System.out.println((i + 1) + ") " + zone.toString());
			++i;
		}
		zones.reset();
		try {
			index = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Please type a number\nOperation cancelled");
			return null;
		}
		if (index > zones.size() || index <= 0) {
			System.out.println("Invalid Option\nOperation cancelled");
			return null;
		}
		i = 0;
		while (zones.hasNext()) {
			if (index == i + 1) {
				return zones.next();
			}
			zones.next();
			++i;
		}
		return null;
	}

	private DistributionNetwork selectDistributionNetwork(Distributor distributor) {
		List<DistributionNetwork> networks = distributor.getNetworks();
		int index = 1;

		System.out.println("Select the network");
		for (int i = 0; i < networks.size(); ++i) {
			System.out.println((i + 1) + ") " + networks.get(i).toString());
		}
		try {
			index = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Please type a number\nOperation cancelled");
			return null;
		}
		if (index > networks.size() || index <= 0) {
			System.out.println("Invalid Option\nOperation cancelled");
			return null;
		}
		return networks.get(index - 1);
	}

	private DistributionLine selectDistributionLine(DistributionNetwork network) {
		List<DistributionLine> lines = network.getLines();
		int index = 1;

		System.out.println("Select the line");
		for (int i = 0; i < lines.size(); ++i) {
			System.out.println((i + 1) + ") " + lines.get(i).toString());
		}
		try {
			index = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Please type a number\nOperation cancelled");
			return null;
		}
		if (index > lines.size() || index <= 0) {
			System.out.println("Invalid Option\nOperation cancelled");
			return null;
		}
		return lines.get(index - 1);
	}

	private Distributor selectDistributor() {
		int i = 0;
		int index = 1;
		Objects<Distributor> distributors = distributorControl.getDistributors();

		System.out.println("Select the distributor");
		while (distributors.hasNext()) {
			Distributor distributor = distributors.next();
			System.out.println((i + 1) + ") " + distributor.toString());
			++i;
		}
		distributors.reset();
		try {
			index = Integer.parseInt(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Please type a number\nOperation cancelled");
			return null;
		}
		if (index > distributors.size() || index <= 0) {
			System.out.println("Invalid Option\nOperation cancelled");
			return null;
		}
		i = 0;
		while (distributors.hasNext()) {
			if (index == i + 1) {
				return distributors.next();
			}
			distributors.next();
			++i;
		}
		return null;
	}

}
