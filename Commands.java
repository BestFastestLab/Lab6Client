import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Commands implements Serializable { //Строковые команды->объекты, хранящие имя команды и ее аргумент
    private String name;
    private ArrayList<Commands> masOfCommands = new ArrayList<Commands>();
    private String fileName;
    private Long id;
    private MusicBand band;
    private MusicGenre genre;
    private String bandName;
    private String result;
    private LocalDate creationDate;
    private byte code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public MusicBand getBand() {
        return band;
    }

    public void setBand(MusicBand band) {
        this.band = band;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public ArrayList<Commands> getMasOfCommands() {
        return masOfCommands;
    }

    @Override
    public String toString() {
        return "Commands{" +
                "name='" + name + '\'' +
                ", masOfCommands=" + masOfCommands +
                ", fileName='" + fileName + '\'' +
                ", id=" + id +
                ", band=" + band +
                ", genre=" + genre +
                ", bandName='" + bandName + '\'' +
                ", result='" + result + '\'' +
                ", creationDate=" + creationDate +
                ", code=" + code +
                '}';
    }
}