package com.carpurchase.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the list of cars using a LinkedList implementation
 * and handles file I/O for car_list.txt with sorting capabilities
 */
public class CarListManager {
    private static final String DATA_DIR = "E:\\Exam-Result Management System\\Car_Purchase System\\src\\main\\webapp\\WEB-INF\\data";
    private static final String CAR_LIST_FILE = "car_list.txt";
    
    private Car head; // Head of the linked list
    private int size; // Size of the linked list
    
    public CarListManager() {
        this.head = null;
        this.size = 0;
        loadCarsFromFile(); // Load existing cars when manager is created
    }
    
    /**
     * Adds a new car to the linked list and saves to file
     * @param car The car to add
     */
    public void addCar(Car car) {
        // Add to the beginning of the linked list (O(1) operation)
        if (head == null) {
            head = car;
        } else {
            car.setNext(head);
            head = car;
        }
        size++;
        
        // Save to file
        saveToFile();
    }
    
    /**
     * Gets all cars in the linked list
     * @return List of all cars
     */
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        Car current = head;
        
        while (current != null) {
            cars.add(current);
            current = current.getNext();
        }
        
        return cars;
    }
    
    /**
     * Gets cars by seller ID
     * @param sellerId Seller's ID
     * @return List of cars owned by seller
     */
    public List<Car> getCarsBySellerID(String sellerId) {
        List<Car> cars = new ArrayList<>();
        Car current = head;
        
        while (current != null) {
            if (current.getSellerId().equals(sellerId)) {
                cars.add(current);
            }
            current = current.getNext();
        }
        
        return cars;
    }
    
    /**
     * Gets a car by ID
     * @param id Car ID to find
     * @return Car if found, null otherwise
     */
    public Car getCarById(String id) {
        Car current = head;
        
        while (current != null) {
            if (current.getId().equals(id)) {
                return current;
            }
            current = current.getNext();
        }
        
        return null;
    }
    
    /**
     * Updates a car in the list
     * @param updatedCar Car with updated values
     * @return true if car was found and updated, false otherwise
     */
    public boolean updateCar(Car updatedCar) {
        if (updatedCar == null || updatedCar.getId() == null) {
            return false;
        }
        
        boolean found = false;
        Car current = head;
        
        while (current != null) {
            if (current.getId().equals(updatedCar.getId())) {
                // Found the car to update
                // Preserve the next reference to maintain linked list structure
                updatedCar.setNext(current.getNext());
                
                // Now replace the current car with updatedCar in the linked list
                if (current == head) {
                    head = updatedCar;
                } else {
                    // Find the previous node to update its next pointer
                    Car prev = head;
                    while (prev != null && prev.getNext() != current) {
                        prev = prev.getNext();
                    }
                    if (prev != null) {
                        prev.setNext(updatedCar);
                    }
                }
                
                found = true;
                break;
            }
            current = current.getNext();
        }
        
        if (found) {
            // Save all cars to file
            rewriteFile();
            return true;
        }
        
        return false;
    }
    
    /**
     * Removes a car from the linked list
     * @param id ID of the car to remove
     * @return true if removed, false if not found
     */
    public boolean removeCar(String id) {
        if (head == null) {
            return false;
        }
        
        // Special case: remove head
        if (head.getId().equals(id)) {
            head = head.getNext();
            size--;
            saveToFile();
            return true;
        }
        
        // Search list for car
        Car current = head;
        while (current.getNext() != null) {
            if (current.getNext().getId().equals(id)) {
                current.setNext(current.getNext().getNext());
                size--;
                saveToFile();
                return true;
            }
            current = current.getNext();
        }
        
        return false;
    }
    
    /**
     * Deletes a car by ID - Added for compatibility with my-listing.jsp
     * @param carId ID of the car to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteCar(String carId) {
        return removeCar(carId);
    }
    
    /**
     * Saves a list of cars to the file (used by external methods)
     * @param cars List of cars to save
     * @return true if successful, false otherwise
     */
    public boolean saveAllCars(List<Car> cars) {
        try {
            // First clear the linked list
            head = null;
            size = 0;
            
            // Add all cars to the linked list
            for (Car car : cars) {
                car.setNext(null); // Clear any existing references
                if (head == null) {
                    head = car;
                } else {
                    car.setNext(head);
                    head = car;
                }
                size++;
            }
            
            // Save to file
            rewriteFile();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Basic file save method
     */
    private void saveToFile() {
        rewriteFile(); // Just use our more reliable method
    }
    
    /**
     * More reliable method to completely rewrite the file with current car data
     */
    private void rewriteFile() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        File file = new File(dir, CAR_LIST_FILE);
        
        try {
            // Create a string builder to hold all the file content
            StringBuilder content = new StringBuilder();
            
            // Add each car's data
            Car current = head;
            while (current != null) {
                content.append("==== CAR LISTING RECORD ====\n");
                content.append("Listing ID: " + current.getId() + "\n");
                appendIfNotNull(content, "Title", current.getTitle());
                appendIfNotNull(content, "Make", current.getMake());
                appendIfNotNull(content, "Model", current.getModel());
                content.append("Year: " + current.getYear() + "\n");
                appendIfNotNull(content, "Body Type", current.getBodyType());
                appendIfNotNull(content, "Condition", current.getCondition());
                appendIfNotNull(content, "Trim", current.getTrim());
                appendIfNotNull(content, "VIN", current.getVin());
                content.append("Mileage: " + current.getMileage() + "\n");
                appendIfNotNull(content, "Exterior Color", current.getExteriorColor());
                appendIfNotNull(content, "Interior Color", current.getInteriorColor());
                appendIfNotNull(content, "Transmission", current.getTransmission());
                appendIfNotNull(content, "Fuel Type", current.getFuelType());
                appendIfNotNull(content, "Doors", current.getDoors());
                appendIfNotNull(content, "Drive Type", current.getDriveType());
                appendIfNotNull(content, "Engine", current.getEngine());
                content.append("Price: " + current.getPrice() + "\n");
                appendIfNotNull(content, "Description", current.getDescription());
                
                // Handle features
                if (current.getFeatures() != null && !current.getFeatures().isEmpty()) {
                    content.append("Features: ");
                    content.append(String.join(", ", current.getFeatures()));
                    content.append("\n");
                } else {
                    content.append("Features: \n");
                }
                
                // Handle photos
                if (current.getPhotos() != null && !current.getPhotos().isEmpty()) {
                    content.append("Photos: ");
                    content.append(String.join(", ", current.getPhotos()));
                    content.append("\n");
                } else {
                    content.append("Photos: \n");
                }
                
                appendIfNotNull(content, "Seller ID", current.getSellerId());
                appendIfNotNull(content, "Contact Name", current.getContactName());
                appendIfNotNull(content, "Contact Email", current.getContactEmail());
                appendIfNotNull(content, "Contact Phone", current.getContactPhone());
                appendIfNotNull(content, "Location", current.getLocation());
                appendIfNotNull(content, "Listing Date", current.getListingDate());
                appendIfNotNull(content, "Status", current.getStatus());
                content.append("-----------------------------\n");
                
                current = current.getNext();
            }
            
            // Write the content to the file
            try (FileWriter writer = new FileWriter(file, false)) {
                writer.write(content.toString());
            }
            
            System.out.println("Cars saved to file successfully (complete rewrite).");
            
        } catch (IOException e) {
            System.err.println("Error saving car list to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Helper method to append a field to the content only if it's not null
     */
    private void appendIfNotNull(StringBuilder content, String field, String value) {
        content.append(field).append(": ");
        if (value != null) {
            content.append(value);
        }
        content.append("\n");
    }
    
    /**
     * Loads cars from the car_list.txt file into the linked list
     * This method is improved to handle potential file format issues
     */
    private void loadCarsFromFile() {
        File file = new File(DATA_DIR, CAR_LIST_FILE);
        
        if (!file.exists()) {
            System.out.println("Car list file does not exist yet. Starting with empty list.");
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Car currentCar = null;
            boolean inRecord = false;
            boolean foundFirstRecord = false;
            
            while ((line = br.readLine()) != null) {
                line = line.trim();
                
                // Skip any lines before the first car record marker
                if (!foundFirstRecord && !line.equals("==== CAR LISTING RECORD ====")) {
                    // Skip this line - it's likely metadata
                    continue;
                }
                
                if (line.equals("==== CAR LISTING RECORD ====")) {
                    // Start of a new car record
                    foundFirstRecord = true;
                    inRecord = true;
                    continue;
                }
                
                if (line.equals("-----------------------------")) {
                    // End of a car record, add to linked list
                    if (currentCar != null) {
                        // Insert at the beginning for efficiency
                        currentCar.setNext(head);
                        head = currentCar;
                        size++;
                    }
                    inRecord = false;
                    currentCar = null;
                    continue;
                }
                
                // Process car data
                if (inRecord && line.contains(": ")) {
                    String[] parts = line.split(": ", 2);
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        
                        // Process key-value pair
                        if (key.equals("Listing ID")) {
                            currentCar = new Car(value);
                        } else if (currentCar != null) {
                            processCarField(currentCar, key, value);
                        }
                    }
                }
            }
            
            System.out.println("Loaded " + size + " cars from file.");
            
        } catch (IOException e) {
            System.err.println("Error loading car list from file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Processes a key-value pair from the file and sets the appropriate field on the car
     */
    private void processCarField(Car car, String key, String value) {
        // Skip empty values
        if (value.isEmpty()) {
            return;
        }
        
        switch (key) {
            case "Title":
                car.setTitle(value);
                break;
            case "Make":
                car.setMake(value);
                break;
            case "Model":
                car.setModel(value);
                break;
            case "Year":
                try {
                    car.setYear(Integer.parseInt(value));
                } catch (NumberFormatException e) {
                    car.setYear(0);
                }
                break;
            case "Body Type":
                car.setBodyType(value);
                break;
            case "Condition":
                car.setCondition(value);
                break;
            case "Trim":
                car.setTrim(value);
                break;
            case "VIN":
                car.setVin(value);
                break;
            case "Mileage":
                try {
                    car.setMileage(Integer.parseInt(value));
                } catch (NumberFormatException e) {
                    car.setMileage(0);
                }
                break;
            case "Exterior Color":
                car.setExteriorColor(value);
                break;
            case "Interior Color":
                car.setInteriorColor(value);
                break;
            case "Transmission":
                car.setTransmission(value);
                break;
            case "Fuel Type":
                car.setFuelType(value);
                break;
            case "Doors":
                car.setDoors(value);
                break;
            case "Drive Type":
                car.setDriveType(value);
                break;
            case "Engine":
                car.setEngine(value);
                break;
            case "Price":
                try {
                    car.setPrice(Double.parseDouble(value));
                } catch (NumberFormatException e) {
                    car.setPrice(0);
                }
                break;
            case "Description":
                car.setDescription(value);
                break;
            case "Features":
                if (!value.isEmpty()) {
                    String[] features = value.split(", ");
                    for (String feature : features) {
                        car.addFeature(feature);
                    }
                }
                break;
            case "Photos":
                if (!value.isEmpty()) {
                    String[] photos = value.split(", ");
                    for (String photo : photos) {
                        car.addPhoto(photo);
                    }
                }
                break;
            case "Seller ID":
                car.setSellerId(value);
                break;
            case "Contact Name":
                car.setContactName(value);
                break;
            case "Contact Email":
                car.setContactEmail(value);
                break;
            case "Contact Phone":
                car.setContactPhone(value);
                break;
            case "Location":
                car.setLocation(value);
                break;
            case "Listing Date":
                car.setListingDate(value);
                break;
            case "Status":
                car.setStatus(value);
                break;
        }
    }
    
    /**
     * Gets the size of the linked list
     * @return Number of cars
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Gets all cars sorted by price using Merge Sort algorithm
     * 
     * @param ascending true for ascending, false for descending order
     * @return List of sorted cars
     */
    public List<Car> getAllCarsSortedByPrice(boolean ascending) {
        // Convert linked list to array for merge sort
        Car[] carsArray = linkedListToArray();
        
        // Apply merge sort
        mergeSortByPrice(carsArray, 0, size - 1, ascending);
        
        // Convert sorted array back to list
        List<Car> sortedCars = new ArrayList<>();
        for (Car car : carsArray) {
            sortedCars.add(car);
        }
        
        return sortedCars;
    }
    
    /**
     * Converts the linked list to an array for sorting
     * @return Array of cars
     */
    private Car[] linkedListToArray() {
        Car[] array = new Car[size];
        Car current = head;
        int index = 0;
        
        while (current != null) {
            array[index++] = current;
            current = current.getNext();
        }
        
        return array;
    }
    
    /**
     * Implementation of Merge Sort algorithm to sort cars by price
     * 
     * @param cars Array of cars to sort
     * @param left Start index
     * @param right End index
     * @param ascending true for ascending, false for descending order
     */
    private void mergeSortByPrice(Car[] cars, int left, int right, boolean ascending) {
        if (left < right) {
            // Find the middle point
            int middle = left + (right - left) / 2;
            
            // Sort first and second halves
            mergeSortByPrice(cars, left, middle, ascending);
            mergeSortByPrice(cars, middle + 1, right, ascending);
            
            // Merge the sorted halves
            merge(cars, left, middle, right, ascending);
        }
    }
    
    /**
     * Merges two subarrays of cars sorted by price
     * 
     * @param cars Array of cars
     * @param left Start index
     * @param middle Middle index
     * @param right End index
     * @param ascending true for ascending, false for descending order
     */
    private void merge(Car[] cars, int left, int middle, int right, boolean ascending) {
        // Find sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;
        
        // Create temp arrays
        Car[] leftArray = new Car[n1];
        Car[] rightArray = new Car[n2];
        
        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            leftArray[i] = cars[left + i];
        for (int j = 0; j < n2; ++j)
            rightArray[j] = cars[middle + 1 + j];
        
        // Merge the temp arrays
        int i = 0, j = 0;
        int k = left;
        
        while (i < n1 && j < n2) {
            if (ascending) {
                if (leftArray[i].getPrice() <= rightArray[j].getPrice()) {
                    cars[k] = leftArray[i];
                    i++;
                } else {
                    cars[k] = rightArray[j];
                    j++;
                }
            } else {
                if (leftArray[i].getPrice() >= rightArray[j].getPrice()) {
                    cars[k] = leftArray[i];
                    i++;
                } else {
                    cars[k] = rightArray[j];
                    j++;
                }
            }
            k++;
        }
        
        // Copy remaining elements of leftArray[] if any
        while (i < n1) {
            cars[k] = leftArray[i];
            i++;
            k++;
        }
        
        // Copy remaining elements of rightArray[] if any
        while (j < n2) {
            cars[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    /**
     * Searches for cars by make and/or model (case insensitive)
     * 
     * @param make Car make to search for (can be null)
     * @param model Car model to search for (can be null)
     * @return List of cars matching the search criteria
     */
    public List<Car> searchCars(String make, String model) {
        List<Car> results = new ArrayList<>();
        Car current = head;
        
        while (current != null) {
            boolean matchesMake = make == null || make.isEmpty() || 
                                 current.getMake().toLowerCase().contains(make.toLowerCase());
            boolean matchesModel = model == null || model.isEmpty() || 
                                  current.getModel().toLowerCase().contains(model.toLowerCase());
            
            if (matchesMake && matchesModel) {
                results.add(current);
            }
            
            current = current.getNext();
        }
        
        return results;
    }
}