package evanricchi.com.outerspacemanager.outerspacemanager.models;

public class ApiUser {
    private String username;

    private Double gas;

    private Double gasModifier;

    private Double minerals;

    private Double mineralsModifier;

    private Double points;

    public String getUser(){
        return username;
    }

    public int getGas(){
        int gaz = ((int)(gas*100))/100;
        return gaz;
    }

    public Double getGasModifier() { return gasModifier; }

    public int getMinerals(){
        int mine = ((int)(minerals*100))/100;
        return mine;
    }

    public Double getMineralsModifier() { return mineralsModifier; }

    public int getPoints(){
        int point = ((int)(points*100))/100;
        return point;
    }

    public String getUsername() {
        return username;
    }
}
