package com.kdbk.fiszki.Instance;

public class GameSettingsInstance {
    private static GameSettingsInstance instance = null;

    private static String gameMode = "";
    private static String name = "";
    private static String language ="";
    private static String  selectData ="";

    private static int points=0, allWords=0;


    public static GameSettingsInstance getInstance() {
        if (instance == null) {
            instance = new GameSettingsInstance();
        }
        return instance;
    }


    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        GameSettingsInstance.gameMode = gameMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameCategory) {
        GameSettingsInstance.name = nameCategory;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        GameSettingsInstance.language = language;
    }

    public String getSelectData() {
        return selectData;
    }

    public void setSelectData(String selectData) {
        GameSettingsInstance.selectData = selectData;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        GameSettingsInstance.points = points;
    }

    public int getAllWords() {
        return allWords;
    }

    public void setAllWords(int allWords) {
        GameSettingsInstance.allWords = allWords;
    }
}
