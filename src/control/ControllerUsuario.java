
package control;

import DAO.AlunoDAO;
import DAO.Conexao;
import javax.swing.JOptionPane;
import model.Aluno;
import view.UsuarioFrame;
import java.sql.Connection;
import java.sql.SQLException;

public class ControllerUsuario {
    private UsuarioFrame view;
    private Aluno aluno;

    public ControllerUsuario(UsuarioFrame view, Aluno aluno) {
        this.view = view;
        this.aluno = aluno;
    }
    
    public void remover(){
        String usuario = aluno.getUsuario();
        int option = JOptionPane.showConfirmDialog(view, 
                "Deseja realmente excluir " + usuario + "?");
        if(option != 1){
            Conexao conexao = new Conexao();
            try {
                Connection conn = conexao.getConnection();
                AlunoDAO dao = new AlunoDAO(conn);
                dao.excluir(aluno);
                JOptionPane.showMessageDialog(view, "Excluido com sucesso!");
            } catch(SQLException e){
                JOptionPane.showMessageDialog(view, "Falha na conexao!");
            }
        }
    }
    
    public void atualizar(){
        String usuario = view.getLblUsuario().getText();
        String novaSenha = view.getTxtNovaSenha().getText();
        Aluno aluno = new Aluno("", usuario, novaSenha);
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            AlunoDAO dao = new AlunoDAO(conn);
            dao.atualizar(aluno);
            JOptionPane.showMessageDialog(view, "Senha atualizada com sucesso!");
        } catch(SQLException e){
                JOptionPane.showMessageDialog(view, "Falha na conexao!");
            }
    }
    
    
}
