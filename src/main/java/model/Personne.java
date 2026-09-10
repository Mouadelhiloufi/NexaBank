package model;

public abstract class Personne {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String password;

    public Personne(int id, String name, String lastName, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Getters et Setters pour ID
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters et Setters pour Name (Nom)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters et Setters pour LastName (Prénom)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNom() {
        return this.name;
    }

    public void setNom(String nom) {
        this.name = nom;
    }

    public String getPrenom() {
        return this.lastName;
    }

    public void setPrenom(String prenom) {
        this.lastName = prenom;
    }

    // Getters et Setters pour Email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters et Setters pour Password
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}