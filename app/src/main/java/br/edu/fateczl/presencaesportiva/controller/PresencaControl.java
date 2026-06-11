package br.edu.fateczl.presencaesportiva.controller;

import java.time.LocalDate;
import java.util.List;

import br.edu.fateczl.presencaesportiva.DAO.MatriculaDAO;
import br.edu.fateczl.presencaesportiva.DAO.MatriculaDAOImplementation;
import br.edu.fateczl.presencaesportiva.DAO.PresencaDAO;
import br.edu.fateczl.presencaesportiva.DAO.PresencaDAOImplementation;
import br.edu.fateczl.presencaesportiva.model.Matricula;
import br.edu.fateczl.presencaesportiva.model.Presenca;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PresencaControl {
    private ObservableList<Presenca> lista = FXCollections.observableArrayList();
    private PresencaDAO dao = new PresencaDAOImplementation();

    private LongProperty id = new SimpleLongProperty(0);
    private ObjectProperty<Matricula> matricula = new SimpleObjectProperty<>(null);
    private ObjectProperty<LocalDate> dia = new SimpleObjectProperty<>(LocalDate.now());
    private BooleanProperty status = new SimpleBooleanProperty(false);
    
    public ObservableList<Presenca> getLista() {
        return lista;
    }

    public void fromEntity( Presenca presenca ) {
        if (presenca != null) {
            id.set( presenca.getId() );
            matricula.set( presenca.getMatricula() );
            dia.set( presenca.getDia() );
            status.set( presenca.getStatus() );
        }
    }

    public void limparCampos() { 
        id.set(0);
        matricula.set(null);
        dia.set(LocalDate.now());
        
        status.set(false);
    }

    public Presenca toEntity() {
        return new Presenca(
            id.get(),
            matricula.get(),
            dia.get(),
            status.get()
        );
    }
    public void salvar() {
        Presenca presenca = toEntity();
        if (presenca.getId() == 0) {
            dao.cadastrar(presenca);
        } else {
            dao.atualizar(id.get(), presenca);
        }
        carregar();
    }

    private void carregar() {
        lista.clear();
        lista.addAll(
            dao.buscarPorMatriculaEDia(0, LocalDate.now()));
    }

    public void pesquisar(String modalidade, LocalDate dia) {
        lista.clear();
        lista.addAll(
            dao.buscarPorMatriculaEDia(0, LocalDate.now()));
    }

    public void excluir(int indice) {
        Presenca p = lista.get(indice);
        dao.apagar(p);
        carregar();
    }

    public void carregarPorTurmaEDia(String nomeTurma, LocalDate dia) {
    lista.clear();
    // busca matrículas da turma no MatriculaDAO
    MatriculaDAO matriculaDAO = new MatriculaDAOImplementation();
    List<Matricula> matriculas = matriculaDAO.pesquisarPorTurma(nomeTurma);

    // para cada matrícula, verifica se já existe presença naquele dia
    // se sim, carrega; se não, cria com status false
    for (Matricula m : matriculas) {
        Presenca p = dao.buscarPorMatriculaEDia(m.getId(), dia);
        if (p == null) {
            p = new Presenca(0, m, dia, false); // novo, ainda não salvo
        }
        lista.add(p);
    }
    }

    public void salvarTodos() {
        for (Presenca p : lista) {
            if (p.getId() == 0) {
                dao.cadastrar(p);  // novo
            } else {
                dao.atualizar(p.getId(), p); // atualiza existente
            }
        }
        carregar();
    }

    // Getters para as propriedades - usado para o binding
    public LongProperty idProperty() {
        return id;
    }
    public ObjectProperty<Matricula> matriculaProperty(){
        return matricula;
    }
    public ObjectProperty<LocalDate> diaProperty() {
        return dia;
    }
    public BooleanProperty statusProperty() {
        return status;
    }

}
