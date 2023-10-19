package Appointmnent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String username;
    private String email;
    private String mobile;
    private String password;

    public User(String username,String email,String mobile, String password) {
        this.username = username;
        this.email =email;
        this.mobile = mobile;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }
}

class Appointment {
    private LocalDate date;
    private LocalTime time;
    private String serviceProvider;
    private String user;
    private String appointmentTitle;
    private String appointmentDescription;
    public Appointment(LocalDate date, LocalTime time, String serviceProvider,String appointmentTitle,String appointmentDescription, String user) {
        this.date = date;
        this.time = time;
        this.serviceProvider = serviceProvider;
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }
     
    public String getAppointmentTitle() {
		return appointmentTitle;
    }
    
    public String getAppointmentDescription() {
		return appointmentDescription;
    }

    public String getUser() {
        return user;
    }
}

public class AppointmentSystem {
    private List<User> users;
    private List<Appointment> appointments;
    private User loggedInUser;
    private Scanner scanner;

    public AppointmentSystem() {
        users = new ArrayList<User>();
        appointments = new ArrayList<Appointment>();
        loggedInUser = null;
        scanner = new Scanner(System.in);
    }

    public void registerUser(String username,String email,String mobile, String password) {
        User user = new User(username,email,mobile, password);
        users.add(user);
        System.out.println("User registered successfully.");
    }

    public void loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful. Welcome, " + loggedInUser.getUsername() + "!");
                return;
            }
        }
        System.out.println("Login failed.Please check your email 7 password");
    }

    public void scheduleAppointment(String appointmentTitle,LocalDate date, LocalTime time, String serviceProvider,String appointmentDescription) {
        if (loggedInUser != null) {
            Appointment appointment = new Appointment(date, time, serviceProvider,appointmentTitle,appointmentDescription, loggedInUser.getUsername());
            appointments.add(appointment);
            System.out.println("Appointment scheduled successfully.");
        } else {
            System.out.println("You need to log in to schedule an appointment.");
        }
    }

    public void viewAppointments() {
        if (loggedInUser != null) {
            System.out.println("Your Appointments:");
            for (Appointment appointment : appointments) {
                if (appointment.getUser().equals(loggedInUser.getUsername())) {
                    System.out.println("Appointment Title:" +appointment.getAppointmentTitle()+" Apoointment Date: " + appointment.getDate() +
                            " Appointment Time: " + appointment.getTime() +
                            " Appointment Provider: " + appointment.getServiceProvider()+
                            "Appointment Description:" +appointment.getAppointmentDescription()
                    		);
                }
            }
        } else {
            System.out.println("You need to log in to view appointments.");
        }
    }
    public static void main(String[] args) {
        AppointmentSystem appointmentSystem = new AppointmentSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Online Appointment Scheduling System");
            System.out.println("1.Register First");
            System.out.println("2. Login Here");
            System.out.println("3. Scheduled The Appointment");
            System.out.println("4. View The Appointments");
            System.out.println("5. Logout");
            System.out.print("Enter your option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String regEmail = scanner.nextLine();
                    System.out.print("Enter mobile: ");
                    String regMobile = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    appointmentSystem.registerUser(regUsername,regEmail,regMobile, regPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    appointmentSystem.loginUser(loginUsername, loginPassword);
                    break;
                case 3:
                    if (appointmentSystem.loggedInUser != null) {
                    	 System.out.print("Enter Appointment Title: ");
                         String appointmentTitle = scanner.nextLine();
                        System.out.print("Enter Appointment Date: ");
                        String dateString = scanner.nextLine();
                        LocalDate date = LocalDate.parse(dateString);
                        System.out.print("Enter Apppointment Time ");
                        String timeString = scanner.nextLine();
                        LocalTime time = LocalTime.parse(timeString);
                        System.out.print("Enter Appointment provider:");
                        String serviceProvider = scanner.nextLine();
                        System.out.print("Enter Appointment Description: ");
                        String appointmentDescription = scanner.nextLine();
                        appointmentSystem.scheduleAppointment(appointmentTitle,date, time, serviceProvider,appointmentDescription);
                    } else {
                        System.out.println("You need to log in to schedule an appointment.");
                    }
                    break;
                case 4:
                    appointmentSystem.viewAppointments();
                    break;
                case 5:
                    System.out.println("**********Thankyou to visit this application********");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println(" Please try again.");
                    break;
            }
        }
    }
}
