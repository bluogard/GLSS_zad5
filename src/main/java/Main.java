import lombok.extern.slf4j.Slf4j;


import java.sql.*;
import java.util.Scanner;

@Slf4j
public class Main {

    static final String DB_URL = "jdbc:mysql://localhost:3306/bank";
    static final String USER = "root";
    static final String PASS = "root";

    static Operations operations = new Operations();

    private static boolean continueFlag = true;
    private static int option, id;
    private static String surname;
    private static String name,address,city,country,phone,email;


    public static void main(String[] args){




        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS)) {

            while(continueFlag){

                log.info("Wybierz operację do wykonania:");
                log.info("1. Wyświetl dane użytkownika o podanym nazwisku");
                log.info("2. Zmień numer telefonu klienta");
                log.info("3. Dodaj użytkownika");
                log.info("4. Usuń pożyczkę");
                log.info("5. Wyjdź z aplikacji");
                Scanner scannerForSwitch = new Scanner(System.in);
                option = scannerForSwitch.nextInt();
                Scanner scannerForData = new Scanner(System.in);


                switch(option){
                    case 1:
                        log.info("Podaj nazwisko klienta do wypisania jego danych:");
                        surname = scannerForData.nextLine();
                        operations.selectCustomer(conn,surname);
                        break;
                    case 2:
                        log.info("Podaj nazwisko klienta, którego numer telefonu chcesz zmienić:");
                        surname = scannerForData.nextLine();

                        log.info("Podaj nowy numer:");
                        phone = scannerForData.nextLine();

                        operations.updateCustomer(conn,surname,phone);
                        break;
                    case 3:
                        log.info("Podaj wartości do wprowadzenia:");

                        log.info("Podaj imię klienta:");
                        name = scannerForData.nextLine();

                        log.info("Podaj nazwisko klienta:");
                        surname = scannerForData.nextLine();

                        log.info("Podaj ulicę klienta:");
                        address =scannerForData.nextLine();

                        log.info("Podaj miasto klienta:");
                        city = scannerForData.nextLine();

                        log.info("Podaj państwo klienta:");
                        country = scannerForData.nextLine();

                        log.info("Podaj nr telefonu klienta:");
                        phone = scannerForData.nextLine();

                        log.info("Podaj email klienta:");
                        email = scannerForData.nextLine();

                        operations.insertIntoCustomer(conn,name,surname,address,city,country,phone,email);
                        break;

                    case 4:
                        log.info("Podaj id pożyczki klienta do usunięcia:");
                        id = scannerForData.nextInt();
                        operations.deleteLoan(conn,id);
                        break;
                    case 5:
                        log.info("Zakończono działanie aplikacji");
                        continueFlag = false;
                        break;
                    default:
                        log.info("Wybrano złą opcję");
                        break;

                }

            }




        }catch(SQLException e) {
            e.printStackTrace();
        }finally {

            log.info("Zamykanie połączenia");
            operations.closeConnection();

        }
    }
}
