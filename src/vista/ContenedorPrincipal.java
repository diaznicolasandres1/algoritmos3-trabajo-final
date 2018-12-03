package vista;

import controlador.BotonCambiarTurnoEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import modelo.edificios.castillo.Castillo;
import modelo.edificios.cuartel.Cuartel;
import modelo.edificios.plazacentral.PlazaCentral;
import modelo.juego.Juego;
import modelo.unidades.Atacante;
import modelo.unidades.Unidad;
import modelo.unidades.aldeano.Aldeano;
import modelo.unidades.armadeasedio.ArmaDeAsedio;

import java.util.ArrayList;

public class ContenedorPrincipal extends BorderPane {

    private final CambiadorDeHandler cambiadorDeHandler;
    private final CreadorDeBotones creadorDeBotones;
    private final Juego juego;
    private final GridPane tablero = new GridPane();
    private final String jugadorUno;
    private final String jugadorDos;
    private final VBox izquierdo = new VBox();
    private final VBox derecho = new VBox();
    private HBox bottom = new HBox();

    public ContenedorPrincipal(String unJugador, String otroJugador) {
        this.juego = new Juego(unJugador, otroJugador);
        this.jugadorUno = unJugador;
        this.jugadorDos = otroJugador;
        this.dibujarMapaConCasilleroHandler();
        this.setCostados();
        this.crearBottom();
        this.creadorDeBotones = new CreadorDeBotones();
        this.cambiadorDeHandler = new CambiadorDeHandler(this.juego, this, this.tablero);
        Image fondo = new Image("/vista/imagenes/fondo_juego_1.jpg", 1600, 920, false, true);
        BackgroundImage imagenFondo = new BackgroundImage(fondo, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(imagenFondo));
    }

    private void setCostados() {

        Text tituloIzq = new Text(this.jugadorUno);
        Text tituloDer = new Text(this.jugadorDos);
        BotonCambiarTurnoEventHandler cambiadorTurno = new BotonCambiarTurnoEventHandler(this.juego, this);
        Boton botonFinalizarTurno1 = new Boton("Finalizar Turno", cambiadorTurno);
        Boton botonFinalizarTurno2 = new Boton("Finalizar Turno", cambiadorTurno);
        botonFinalizarTurno2.setPadding(new Insets(15));
        botonFinalizarTurno1.setPadding(new Insets(15));
        tituloIzq.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        tituloDer.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        tituloDer.setFill(Color.WHITE);
        tituloIzq.setFill(Color.WHITE);

        this.izquierdo.setAlignment(Pos.TOP_LEFT);
        this.derecho.setAlignment(Pos.TOP_RIGHT);
        this.izquierdo.setSpacing(20);
        this.derecho.setSpacing(20);
        this.derecho.setPadding(new Insets(25));
        this.izquierdo.setPadding(new Insets(25));
        this.izquierdo.getChildren().addAll(tituloIzq, botonFinalizarTurno1);
        this.derecho.getChildren().addAll(tituloDer, botonFinalizarTurno2);
        this.setLeft(this.izquierdo);
        this.setRight(this.derecho);
        this.setPadding(new Insets(25));
    }

    public void crearBottom() {
        this.bottom = new HBox();
        this.setjugadorActual();
        Label comienzo = new Label("Clickea en una unidad o edificio");
        comienzo.setTextFill(Color.WHITE);
        this.bottom.getChildren().add(comienzo);
        this.setBottom(this.bottom);
        this.bottom.setAlignment(Pos.CENTER);
        this.bottom.setPadding(new Insets(20));
    }

    private void setjugadorActual() {
        Label jugadorActual = new Label("Es el turno de: " + this.juego.getNombreJugadorActual());
        jugadorActual.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        jugadorActual.setPadding(new Insets(20));
        jugadorActual.setTextFill(Color.WHITE);
        this.bottom.getChildren().add(jugadorActual);
    }

    public void dibujarMapaConCasilleroHandler() {
        DibujadorDeMapa dibujadorDeMapa = new DibujadorDeMapa(this.juego, this.tablero);
        dibujadorDeMapa.dibujarMapaConCasilleroHandler(this);
        this.setCenter(this.tablero);
    }

    public void dibujarMetodosAldeano(Aldeano aldeano) {
        this.bottom.getChildren().clear();
        ArrayList<Button> botones = this.creadorDeBotones.crearBotonesPara(aldeano, this.juego, this);
        this.configurarBottom();
        botones.forEach(boton -> this.bottom.getChildren().add(boton));
    }

    public void dibujarMetodosCuartel(Cuartel cuartel) {
        this.bottom.getChildren().clear();
        ArrayList<Button> botones = this.creadorDeBotones.crearBotonesPara(cuartel, this.juego, this);
        this.configurarBottom();
        botones.forEach(boton -> this.bottom.getChildren().add(boton));
    }

    public void dibujarMetodosArmaDeAsedio(ArmaDeAsedio armaDeAsedio) {
        this.bottom.getChildren().clear();
        ArrayList<Button> botones = this.creadorDeBotones.crearBotonesPara(armaDeAsedio, this.juego, this);
        this.configurarBottom();
        botones.forEach(boton -> this.bottom.getChildren().add(boton));
    }

    public void dibujarMetodosPlazaCentral(PlazaCentral plaza) {
        this.bottom.getChildren().clear();
        ArrayList<Button> botones = this.creadorDeBotones.crearBotonesPara(plaza, this.juego, this);
        this.configurarBottom();
        botones.forEach(boton -> this.bottom.getChildren().add(boton));
    }

    public void dibujarMetodoEspadachinOArquero(Atacante atacante) {
        this.bottom.getChildren().clear();
        ArrayList<Button> botones = this.creadorDeBotones.crearBotonesPara(atacante, this.juego, this);
        this.configurarBottom();
        botones.forEach(boton -> this.bottom.getChildren().add(boton));
    }

    public void dibujarMetodosCastillo(Castillo castillo) {
        this.bottom.getChildren().clear();
        ArrayList<Button> botones = this.creadorDeBotones.crearBotonesPara(castillo, this.juego, this);
        this.configurarBottom();
        botones.forEach(boton -> this.bottom.getChildren().add(boton));
    }

    private void configurarBottom() {
        this.setjugadorActual();
        this.bottom.setSpacing(10);
        this.bottom.setAlignment(Pos.CENTER);
    }

    /* Cambiadores de handlers */

    public void cambiarHandlerConstruirCuartel(Aldeano aldeano) {
        this.cambiadorDeHandler.cambiadorAConstruirCuartelFin(aldeano);
    }

    public void cambiarHandlerConstuirPlazaCentral(Aldeano aldeano) {
        this.cambiadorDeHandler.cambiadorAConstruirPlazaCentralFin(aldeano);
    }

    public void cambiarHandlerRepararEdificio(Aldeano aldeano) {
        this.cambiadorDeHandler.cambiadorRepararEdificio(aldeano);
    }

    public void cambiarHandlerMoverUnidad(Unidad unidad) {
        this.cambiadorDeHandler.cambiadorMoverUnidad(unidad);
    }

    public void cambiarHandlerAtaque(Atacante atacante) {
        this.cambiadorDeHandler.cambiarHandlerAtaque(atacante);
    }
}
