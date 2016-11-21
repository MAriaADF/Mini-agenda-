package com.example.hellen.agenda_proyecto;

/**
 * creado por Hellen López
 */
public class Agenda {

    public int idNota;
    public String titulo;
    public String hora;
    public String lugar;
    public String descripcion;
    public String fecha;

    /*
     *Constructor Vacio
     */
    public Agenda() {
    }

    /* se resiven los siguientes parametros
     *@param titulo, hora, lugar, descripcion y fecha
     */
    public Agenda(String titulo, String hora, String lugar, String descripcion, String fecha ) {
        this.titulo = titulo;
        this.hora = hora;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    /*
     *@return el id de la nota
     */
    public int getId() {
        return idNota;
    }

    /*
     *@param del id de la nota
     */
    public void setId(int idDatos) {
        this.idNota = idDatos;
    }

    /*
     *@return el titulo de la nota
     */
    public String getTitulo() {
        return titulo;
    }

    /*
     *@param del titulo de la nota
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /*
     *@return el lugar de la nota
     */
    public String getLugar() {
        return lugar;
    }

    /*
     *@param del lugar de la nota
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /*
     *@return la hora de la nota
     */
    public String getHora() {
        return hora;
    }

    /*
     *@param de la hora de la nota
     */
    public void setHora(String Hora) {
        this.hora = Hora;
    }

    /*
     *@return la descripcion de la nota
     */
    public String getDescripcion() {
        return descripcion;
    }

    /*
     *@param de la descripcion de la nota
     */
    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    /*
         *@return la fecha de la nota
         */
    public String getFecha() {
        return fecha;
    }

    /*
     *@param  de la fecha de la nota
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /*//metodo que formata a data para dd/mm/yyyy para ser retornado
    private String defineDatata(){
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String defineData = formato.format(fecha);
        return defineData;
    }*/
    @Override
    public String toString(){

        return titulo +" a las " + getHora();
    }

}
