package models;

public class Jovenes {

    private String dni;
    private String nom;
    private String apePa;
    private String apeMa;
    private String fechNac;
    private String sexo;
    private String dniPadre;
    private String dniMadre;
    private String nivel;
    private String grado;
    private String codigoModular;
    private String añoEduc;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApePa() {
        return apePa;
    }

    public void setApePa(String apePa) {
        this.apePa = apePa;
    }

    public String getApeMa() {
        return apeMa;
    }

    public void setApeMa(String apeMa) {
        this.apeMa = apeMa;
    }

    public String getFechNac() {
        return fechNac;
    }

    public void setFechNac(String fechNac) {
        this.fechNac = fechNac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDniPadre() {
        return dniPadre;
    }

    public void setDniPadre(String dniPadre) {
        this.dniPadre = dniPadre;
    }

    public String getDniMadre() {
        return dniMadre;
    }

    public void setDniMadre(String dniMadre) {
        this.dniMadre = dniMadre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getCodigoModular() {
        return codigoModular;
    }

    public void setCodigoModular(String codigoModular) {
        this.codigoModular = codigoModular;
    }

    public String getAñoEduc() {
        return añoEduc;
    }

    public void setAñoEduc(String añoEduc) {
        this.añoEduc = añoEduc;
    }

    public Jovenes() {
    }

    public Jovenes(String dni, String nom, String apePa, String apeMa, String fechNac, String sexo, String dniPadre, String dniMadre, String nivel, String grado, String codigoModular, String añoEduc) {
        this.dni = dni;
        this.nom = nom;
        this.apePa = apePa;
        this.apeMa = apeMa;
        this.fechNac = fechNac;
        this.sexo = sexo;
        this.dniPadre = dniPadre;
        this.dniMadre = dniMadre;
        this.nivel = nivel;
        this.grado = grado;
        this.codigoModular = codigoModular;
        this.añoEduc = añoEduc;
    }

    
    
    
    
}
