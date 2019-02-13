package interfaces;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.neodatis.odb.Objects;

import control.Control;
import control.PlantControl;
import entities.Delivery;
import entities.HydraulicPlant;
import entities.NuclearPlant;
import entities.PanelType;
import entities.SolarPlant;
import entities.ThermalPlant;

public class Menu {
	
	private String menu, addPlantMenu, updatePlantMenu;
	private Scanner scanner;
	private SimpleDateFormat dateFormat;
	private PlantControl plantControl;
	
	public Menu(PlantControl plantControl) {
		this.plantControl = plantControl;
		scanner = new Scanner(System.in);
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	}
	
	private void printMenu() {
		if(menu == null) {
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
			switch(answ) {
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
		// TODO: ODB CLOSE
	}
	
	// START Add Plants
	private void printAddPlantMenu() {
		if(addPlantMenu == null) {
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
		switch(answ) {
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
		if(name.length() == 0) {
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
		
		if(plantType == HydraulicPlant.class) {
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
			
			HydraulicPlant hp = new HydraulicPlant(name, avgProduction, maxProduction,
					date, new ArrayList<Delivery>(), maxCapacity, occupation, numTurbines);
			
			plantControl.addHPlant(hp);
			System.out.println("Added successfully!");
			return;
		}
		if(plantType == SolarPlant.class) {
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
			for(int i = 0; i < PanelType.values().length; ++i) {
				System.out.println((i + 1) + ") " + PanelType.values()[i]);
			}
			try {
				int choice = Integer.parseInt(scanner.nextLine());
				if(choice > 0 && choice <= PanelType.values().length) {
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
			
			SolarPlant sp = new SolarPlant(name, avgProduction, maxProduction,
					date, new ArrayList<Delivery>(), solarPanelSurface, yearlyAvgSunHours, panelType);
			
			plantControl.addSPlant(sp);
			System.out.println("Added successfully!");
			return;
		}
		if(plantType == NuclearPlant.class) {
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
			
			NuclearPlant np = new NuclearPlant(name, avgProduction, maxProduction,
					date, new ArrayList<Delivery>(), numReactors, volConsumedPlutonium, volProducedWaste);
			
			plantControl.addNPlant(np);
			System.out.println("Added successfully!");
			return;
		}
		if(plantType == ThermalPlant.class) {
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
			
			ThermalPlant tp = new ThermalPlant(name, avgProduction, maxProduction,
					date, new ArrayList<Delivery>(), numFurnaces, volConsumedCarbon, volGasEmissions);
			
			plantControl.addTPlant(tp);
			System.out.println("Added successfully!");
			return;
		}
		
	}
	
	private void addHPlantMenu() {
		String name;
		Date date;
		double avgProduction, maxProduction, maxCapacity, occupation;
		int numTurbines;
		
		System.out.println("Type the plant's name: ");
		name = scanner.nextLine();
		if(name.length() == 0) {
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
		
		HydraulicPlant hp = new HydraulicPlant(name, avgProduction, maxProduction,
				date, new ArrayList<Delivery>(), maxCapacity, occupation, numTurbines);
		
		plantControl.addHPlant(hp);
		System.out.println("Added successfully!");
	}
	
	private void addSPlantMenu() {
		String name;
		Date date;
		double avgProduction, maxProduction, solarPanelSurface, yearlyAvgSunHours;
		PanelType panelType;
		
		System.out.println("Type the plant's name: ");
		name = scanner.nextLine();
		if(name.length() == 0) {
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
		for(int i = 0; i < PanelType.values().length; ++i) {
			System.out.println((i + 1) + ") " + PanelType.values()[i]);
		}
		try {
			int choice = Integer.parseInt(scanner.nextLine());
			if(choice > 0 && choice <= PanelType.values().length) {
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
		
		SolarPlant sp = new SolarPlant(name, avgProduction, maxProduction,
				date, new ArrayList<Delivery>(), solarPanelSurface, yearlyAvgSunHours, panelType);
		
		plantControl.addSPlant(sp);
		System.out.println("Added successfully!");
	}
	
	private void addNPlantMenu() {
		String name;
		Date date;
		double avgProduction, maxProduction, volConsumedPlutonium, volProducedWaste;
		int numReactors;
		
		System.out.println("Type the plant's name: ");
		name = scanner.nextLine();
		if(name.length() == 0) {
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
		
		NuclearPlant np = new NuclearPlant(name, avgProduction, maxProduction,
				date, new ArrayList<Delivery>(), numReactors, volConsumedPlutonium, volProducedWaste);
		
		plantControl.addNPlant(np);
		System.out.println("Added successfully!");
	}
	
	private void addTPlantMenu() {
		String name;
		Date date;
		double avgProduction, maxProduction, volConsumedCarbon, volGasEmissions;
		int numFurnaces;
		
		System.out.println("Type the plant's name: ");
		name = scanner.nextLine();
		if(name.length() == 0) {
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
		
		ThermalPlant tp = new ThermalPlant(name, avgProduction, maxProduction,
				date, new ArrayList<Delivery>(), numFurnaces, volConsumedCarbon, volGasEmissions);
		
		plantControl.addTPlant(tp);
		System.out.println("Added successfully!");
	}
	// END Add Plants
	
	// START Update Plants
	private void printUpdatePlantMenu() {
		if(updatePlantMenu == null) {
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
		switch(answ) {
			case "1":
				updateHPlant();
				break;
			case "2":
				updateSPlant();
				break;
			case "3":
				updateNPlant();
				break;
			case "4":
				updateTPlant();
				break;
			default:
				System.out.println("Incorrect option!");
				break;
		}
	}
	
	private void updateHPlant() {
		Objects<HydraulicPlant> objects = plantControl.getAllHPlants();
		int size = objects.size();
		if(size == 0) {
			System.out.println("There are no thermal plants available!");
			System.out.println("Operation cancelled.");
			return;
		}
		int i = 1;
		while(objects.hasNext()) {
			System.out.println(i + ") " + objects.next().toString());
			++i;
		}
		System.out.println("Choose the plant to update:");
		int choice;
		try {
			choice = Integer.parseInt(scanner.nextLine());
			if(choice < 1 || choice > size) {
				System.out.println("Index not valid!");
				System.out.println("Operation cancelled.");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("Number format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}
		
		HydraulicPlant hPlant = null;
		i = 1;
		objects.reset();
		while(i <= choice && objects.hasNext()) {
			if(i == choice) {
				hPlant = objects.next();
			}
			++i;
		}
		if(hPlant == null) {
			System.out.println("---ERROR: Plant not found after choice!");
			return;
		}
		System.out.println("Modifying: " + hPlant.toString());
		
		System.out.println("1) Modify avg. production");
		System.out.println("2) Modify occupation");
		try {
			choice = Integer.parseInt(scanner.nextLine());
			if(choice < 1 || choice > 2) {
				System.out.println("Index not valid!");
				System.out.println("Operation cancelled.");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("Number format not correct!");
			System.out.println("Operation cancelled.");
			return;
		}
		switch(choice) {
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
			hPlant.setAvgProduction(avgProduction);
			break;
		case 2:
			System.out.println("Type the new occupation");
			double occupation;
			try {
				occupation = Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Number format not correct!");
				System.out.println("Operation cancelled.");
				return;
			}
			hPlant.setOccupation(occupation);
			break;
		}
		plantControl.addHPlant(hPlant);
		System.out.println("Updated successfully!");
	}
	
	private void updateSPlant() {
		
	}

	private void updateNPlant() {
	
	}

	private void updateTPlant() {
	
	}
	
	// END Update Plants
	
}
