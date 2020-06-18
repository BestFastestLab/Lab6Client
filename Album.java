public class Album {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Integer length; //Поле может быть null, Значение поля должно быть больше 0
    public Album(){

    }
    public Album(String name, Integer length){
        this.name=name;
        this.length=length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}
