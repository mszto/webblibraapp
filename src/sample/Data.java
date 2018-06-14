package sample;


public interface Data {
    public Data createData(String fName, String sName, String title);

    public Data createData(String fName, String sName);
    public Data createData(String fName, int sName);

    public Data createData(String fName);

    public Data createData(String fName, String sName, int id);
}
