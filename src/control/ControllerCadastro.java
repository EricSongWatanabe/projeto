
package control;

import DAO.AlunoDAO;
import DAO.Conexao;
import model.Aluno;
import view.CadastroFrame;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class ControllerCadastro {
    private CadastroFrame view;

    public ControllerCadastro(CadastroFrame view) {
        this.view = view;
    }
    
    public void salvarAluno(){
        String nome = view.getTxtEntradaNome().getText();
        String usuario = view.getTxtEntradaUsuario().getText();
        String senha = view.getTxtEntradaSenha().getText();
        
        Aluno aluno = new Aluno(nome, usuario, senha);
        Conexao conexao = new Conexao();
        
        try{
            Connection conn = conexao.getConnection();
            AlunoDAO dao = new AlunoDAO(conn);
            dao.inserir(aluno);
            JOptionPane.showMessageDialog(view, "Usuario Cadastrado!");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Usuario nao Cadastrado!");
        }
    }
}
