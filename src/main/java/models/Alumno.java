package models;

public class Alumno {

    private String dniAlumno;
    private String nombreAlumno;
    private String apeMaterno;
    private String apePaterno;
    private String fechNacimiento;
    private String domicilio;
    private String dniPadre;
    private String dniMadre;
    private String dniApoderado;
    private int telefono1;
    private int telefono2;
    private String email;
    private String discapacidad;
    private String grupoSangui;
    private String alergias;
    private String nivel;
    private String grado;
    private String seccion;
    private String codigoModular;
    private String sexo;

    public String getDniAlumno() {
        return dniAlumno;
    }

    public void setDniAlumno(String dniAlumno) {
        this.dniAlumno = dniAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getFechNacimiento() {
        return fechNacimiento;
    }

    public void setFechNacimiento(String fechNacimiento) {
        this.fechNacimiento = fechNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
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

    public String getDniApoderado() {
        return dniApoderado;
    }

    public void setDniApoderado(String dniApoderado) {
        this.dniApoderado = dniApoderado;
    }

    public int getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(int telefono1) {
        this.telefono1 = telefono1;
    }

    public int getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(int telefono2) {
        this.telefono2 = telefono2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getGrupoSangui() {
        return grupoSangui;
    }

    public void setGrupoSangui(String grupoSangui) {
        this.grupoSangui = grupoSangui;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
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

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCodigoModular() {
        return codigoModular;
    }

    public void setCodigoModular(String codigoModular) {
        this.codigoModular = codigoModular;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Alumno() {
    }

    public Alumno(String dniAlumno, String nombreAlumno, String apeMaterno, String apePaterno, String fechNacimiento, String domicilio, String dniPadre, String dniMadre, String dniApoderado, int telefono1, int telefono2, String email, String discapacidad, String grupoSangui, String alergias, String nivel, String grado, String seccion, String codigoModular, String sexo) {
        this.dniAlumno = dniAlumno;
        this.nombreAlumno = nombreAlumno;
        this.apeMaterno = apeMaterno;
        this.apePaterno = apePaterno;
        this.fechNacimiento = fechNacimiento;
        this.domicilio = domicilio;
        this.dniPadre = dniPadre;
        this.dniMadre = dniMadre;
        this.dniApoderado = dniApoderado;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.email = email;
        this.discapacidad = discapacidad;
        this.grupoSangui = grupoSangui;
        this.alergias = alergias;
        this.nivel = nivel;
        this.grado = grado;
        this.seccion = seccion;
        this.codigoModular = codigoModular;
        this.sexo = sexo;
    }

    public void vaciarDatos() {
        // Reiniciar todos los valores a un estado inicial o predeterminado
        this.dniAlumno = "";
        this.nombreAlumno = "";
        this.apeMaterno = "";
        this.apePaterno = "";
        this.fechNacimiento = "";
        this.domicilio = "";
        this.dniPadre = "";
        this.dniMadre = "";
        this.dniApoderado = "";
        this.telefono1 = 0; // O el valor predeterminado que desees
        this.telefono2 = 0; // O el valor predeterminado que desees
        this.email = "";
        this.discapacidad = "";
        this.grupoSangui = "";
        this.alergias = "";
        this.nivel = "";
        this.grado = "";
        this.seccion = "";
        this.codigoModular = "";
        this.sexo = "";
    }
}
