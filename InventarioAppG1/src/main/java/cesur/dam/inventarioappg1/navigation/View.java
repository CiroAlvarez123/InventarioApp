package cesur.dam.inventarioappg1.navigation;

public enum View {
    LOGIN("login-view.fxml"),
    MAIN("main-view.fxml"),
    CREATE("create-view.fxml");

    private final String file;

    View(String file) { this.file = file; }

    public String getFile() { return file; }
}