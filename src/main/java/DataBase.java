import java.sql.*;


/*
    Класс осуществляет подключение с БД и предоставляет методы для добавления объекта в коллекцию
 */
public class DataBase {
    private Connection connection;
    private Statement statement;

    public DataBase() {


        try {
            connection = DriverManager.getConnection(Main.getURL(),Main.getLOGIN(),Main.getPASS());
            statement = connection.createStatement();
            createFunctionsTable();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка подключения к базе данных. Проверь url, логин и пароль");
            System.exit(0);
        }

    }


    private void createFunctionsTable() throws SQLException {
        this.statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS functions;");
        statement.execute("CREATE TABLE IF NOT EXISTS functions " +
                "(fun_name TEXT NOT NULL , " +
                " code TEXT NOT NULL , " +
                " standart_number TEXT NOT NULL , " +
                " skill TEXT NOT NULL);");
    }

    public void add(FunctionObject functionObject) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO functions (fun_name, code, standart_number, skill) " +
                "VALUES (?, ?, ?, ?);");
        ps.setString(1,functionObject.getName());
        ps.setString(2,functionObject.getCode());
        ps.setString(3,functionObject.getStandart_number());
        ps.setString(4,functionObject.getSkill());
        ps.execute();
    }



}
