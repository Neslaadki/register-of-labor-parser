import java.io.Serializable;

/*
    Объект, который будет получен при парсинге странице.
    Он будет транслирован в строчку таблицы БД.
 */

public class FunctionObject implements Serializable {
    private String standart_number;
    private String code;
    private String name;
    private String skill;

    public FunctionObject(String standart_number, String code, String name, String skill) {
        this.standart_number = standart_number;
        this.code = code;
        this.name = name;
        this.skill = skill;
    }

    public String getStandart_number() {
        return standart_number;
    }

    public void setStandart_number(String standart_number) {
        this.standart_number = standart_number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
