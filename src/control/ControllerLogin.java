
package control;

import DAO.AlunoDAO;
import DAO.Conexao;
import model.Aluno;
import view.LoginFrame;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import view.UsuarioFrame;

public class ControllerLogin {
    private LoginFrame view;

    public ControllerLogin(LoginFrame view) {
        this.view = view;
    }
    
    public void loginAluno(){
        Aluno aluno = new Aluno(null, view.getTxtEntradaUsuario().getText(), 
                                      view.getTxtEntradaSenha().getText());   
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            AlunoDAO dao = new AlunoDAO(conn);
            ResultSet res = dao.consultar(aluno);
            if(res.next()){
                JOptionPane.showMessageDialog(view, "Login feito!");
                String nome = res.getString("nome");
                String usuario = res.getString("usuario");
                String senha = res.getString("senha");
                UsuarioFrame viewUsuario = new UsuarioFrame(new Aluno(nome, usuario, senha));
                viewUsuario.setVisible(true);
                view.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(view, "Login nao foi efetuado!");
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexao!");
        }
    }
}
