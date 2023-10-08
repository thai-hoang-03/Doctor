package Doctor;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class DoctorManagementProgram {
     private Map<String, Doctor> doctorDatabase;

    public DoctorManagementProgram() {
        this.doctorDatabase = new HashMap<>();
    }

    public void displayMenu() {
        System.out.println("\n======= Doctor Management =======");
        System.out.println("1. Add Doctor");
        System.out.println("2. Update Doctor");
        System.out.println("3. Delete Doctor");
        System.out.println("4. Search Doctor");
        System.out.println("5. Exit");
    }

    public void addDoctor(Doctor doctor) throws Exception {
        if (doctorDatabase == null) {
            throw new Exception("Database does not exist");
        }

        if (doctor == null || doctor.getCode() == null) {
            throw new Exception("Data does not exist");
        }

        if (doctorDatabase.containsKey(doctor.getCode())) {
            throw new Exception("Doctor code [" + doctor.getCode() + "] is duplicate");
        }

        doctorDatabase.put(doctor.getCode(), doctor);
        System.out.println("Status: Doctor added");
    }

    public void updateDoctor(Doctor updatedDoctor) throws Exception {
        if (doctorDatabase == null) {
            throw new Exception("Database does not exist");
        }

        if (updatedDoctor == null || updatedDoctor.getCode() == null) {
            throw new Exception("Data does not exist");
        }

        if (!doctorDatabase.containsKey(updatedDoctor.getCode())) {
            throw new Exception("Doctor code doesn't exist");
        }

        Doctor existingDoctor = doctorDatabase.get(updatedDoctor.getCode());

        // Update only non-blank fields
        if (updatedDoctor.getName() != null && !updatedDoctor.getName().isEmpty()) {
            existingDoctor.setName(updatedDoctor.getName());
        }

        if (updatedDoctor.getSpecialization() != null && !updatedDoctor.getSpecialization().isEmpty()) {
            existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
        }

        if (updatedDoctor.getAvailability() >= 0) {
            existingDoctor.setAvailability(updatedDoctor.getAvailability());
        }

        System.out.println("Status: Doctor updated");
    }

    public void deleteDoctor(String code) throws Exception {
        if (doctorDatabase == null) {
            throw new Exception("Database does not exist");
        }

        if (code == null) {
            throw new Exception("Data does not exist");
        }

        if (!doctorDatabase.containsKey(code)) {
            throw new Exception("Doctor code doesn't exist");
        }

        doctorDatabase.remove(code);
        System.out.println("Status: Doctor deleted");
    }

    public void searchDoctor(String input) throws Exception {
        if (doctorDatabase == null) {
            throw new Exception("Database does not exist");
        }

        System.out.println("--------  Result ------------");

        for (Doctor doctor : doctorDatabase.values()) {
            // Implement your search logic here
            if (doctor.getName().toLowerCase().contains(input.toLowerCase())
                    || doctor.getSpecialization().toLowerCase().contains(input.toLowerCase())) {
                System.out.println("Code: " + doctor.getCode());
                System.out.println("Name: " + doctor.getName());
                System.out.println("Specialization: " + doctor.getSpecialization());
                System.out.println("Availability: " + doctor.getAvailability());
                System.out.println();
            }
        }
    }

    public void runProgram() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        addDoctor(getDoctorInput(scanner));
                        break;

                    case 2:
                        updateDoctor(getDoctorInput(scanner));
                        break;

                    case 3:
                        System.out.print("Enter Doctor Code to delete: ");
                        String deleteCode = scanner.next();
                        deleteDoctor(deleteCode);
                        break;

                    case 4:
                        System.out.print("Enter search text: ");
                        String searchText = scanner.next();
                        searchDoctor(searchText);
                        break;

                    case 5:
                        System.out.println("Exiting the program. Goodbye!");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private Doctor getDoctorInput(Scanner scanner) {
        System.out.print("Enter Code: ");
        String code = scanner.next();

        System.out.print("Enter Name: ");
        String name = scanner.next();

        System.out.print("Enter Specialization: ");
        String specialization = scanner.next();

        System.out.print("Enter Availability: ");
        int availability = scanner.nextInt();

        return new Doctor(code, name, specialization, availability);
    }
    
}
