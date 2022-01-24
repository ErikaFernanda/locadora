 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locadora;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static locadora.Bd.getConnection;

/**
 *
 * @author fernandaerika
 */
public class Bd {

    ArrayList lista_c;
    ResultSet rs;

    private static String nome = "postgres";
    private static String senha = "1234";
    private static String url = "jdbc:postgresql://localhost:5432/locadora";
    private static Connection con;
    private Statement stm;
    private static String driver = "org.postgresql.Driver";

    public Bd() {

    }

    public static void main(String[] args) {
        new Bd();

    }

    public static void getConnection() {
        try {
            Class.forName(driver);
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(url, nome, senha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addCliente(String nometxt, String endereco, String cpf, String dc, boolean cp, String telefone, String idade) {
        try {
            getConnection();
            stm = con.createStatement();
            String sql = "insert into cliente(nome,endereco,cpf,data_cadastro,cliente_premium,telefone,idade)" + "values('" + nometxt + "', '" + endereco + "' , '" + cpf + "', '" + dc + "' , '" + cp + "', '" + telefone + "', '" + idade + "')";
            stm.executeUpdate(sql);

        } catch (Exception e) {
//            System.out.println("Erro:" + e);
            e.printStackTrace();
        }

    }

    void addFilme(String idioma_o, boolean legenda, String duracao, String genero, String classificacao, String titulo, String data_l) {
        try {
            getConnection();
            stm = con.createStatement();
            String sql = "insert into filme(idioma_original	,legenda	,duracao	,genero	,classificacao,titulo	,data_lancamento)" + "values('" + idioma_o + "', '" + legenda + "' , '" + duracao + "', '" + genero + "' , '" + classificacao + "', '" + titulo + "', '" + data_l + "')";
            stm.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }

    }

    void addLocacao(JTable tabela, Object idcliente, Object idfilme, String datal, String preco, String datad) {
        try {
            getConnection();
            stm = con.createStatement();
            String sql = "insert into locacao(id_cliente_locacao, id_filme_locacao, data_locacao, preco, data_devolucao)" + "values('" + idcliente.toString().replaceAll("[^0-9]*", "") + "', '" + idfilme.toString().substring(0, 3).replaceAll("[^0-9]*", "") + "', '" + datal + "', '" + preco + "', '" + datad + "')";
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(tabela, "Filme alugado com sucesso !!");

        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }

    }

    public String returnClientes(JTable tabela) {
        try {

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Id Cliente");
            modelo.addColumn("Nome");
            modelo.addColumn("CPF");
            modelo.addColumn("Data de cadastro");
            modelo.addColumn("Cliente premium");
            modelo.addColumn("Idade");
            modelo.addColumn("Endereço");
            modelo.addColumn("Telefone");
            getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM cliente");

            rs = pstm.executeQuery();

            while (rs.next()) {
                String id_cliente = rs.getString("id_cliente");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String data_cadastro = rs.getString("data_cadastro");
                String cliente_premium = rs.getString("cliente_premium");
               
                int idade = rs.getInt("idade");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                Object coluna[] = {id_cliente, nome, cpf, data_cadastro, cliente_premium, idade, endereco, telefone,};

                modelo.addRow(coluna);

            }
            

            tabela.setModel(modelo);
        } catch (Exception e) {
            System.out.println("Erro:" + e);
            
        }
        return null;

    }

    public String returnFilmes(JTable tabela) {
        try {

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("id_filme");
            modelo.addColumn("Idioma");
            modelo.addColumn("Legendado");
            modelo.addColumn("Duração");
            modelo.addColumn("Gênero");
            modelo.addColumn("Classificação");
            modelo.addColumn("Titulo");
            modelo.addColumn("Data de lançamento");
            getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM filme");

            rs = pstm.executeQuery();

            while (rs.next()) {
                String id_filme = rs.getString("id_filme");
                String idioma_original = rs.getString("idioma_original");
                boolean legenda = rs.getBoolean("legenda");
                String duracao = rs.getString("duracao");
                String genero = rs.getString("genero");
                int classificacao = rs.getInt("classificacao");
                String titulo = rs.getString("titulo");
                String data_lancamento = rs.getString("data_lancamento");
                Object coluna[] = {id_filme, idioma_original, legenda, duracao, genero, classificacao, titulo, data_lancamento};

                modelo.addRow(coluna);

            }

            tabela.setModel(modelo);

        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }
        return null;
    }

    public String returnAlugados(JTable tabela) {
        try {

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("id_locacao");
            modelo.addColumn("Cliente");
            modelo.addColumn("Titulo");
            modelo.addColumn("dataLocacao");
            modelo.addColumn("preco");
            modelo.addColumn("dataDevolucao");
            getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM locacao INNER JOIN cliente ON locacao.id_cliente_locacao = cliente.id_cliente INNER JOIN filme ON locacao.id_filme_locacao = filme.id_filme");

            rs = pstm.executeQuery();

            while (rs.next()) {
                String id_locacao = rs.getString("id_locacao");
                String id_cliente_locacao = rs.getString("nome");
                String id_filme_locacao = rs.getString("titulo");
                Date dataLocacao = rs.getDate("data_locacao");
                Double preco = rs.getDouble("preco");
                Date dataDevolucao = rs.getDate("data_devolucao");
                Object coluna[] = {id_locacao, id_cliente_locacao, id_filme_locacao, dataLocacao, preco, dataDevolucao};

                modelo.addRow(coluna);

            }

            tabela.setModel(modelo);

        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }
        return null;

    }

    public String returnBuscaLocacao(JTable tabela, JTextField busca, JComboBox cbox) {
        try {
            PreparedStatement pstm;
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("id_locacao");
            modelo.addColumn("id_cliente_locacao");
            modelo.addColumn("id_filme_locacao");
            modelo.addColumn("dataLocacao");
            modelo.addColumn("preco");
            modelo.addColumn("dataDevolucao");
            getConnection();


            pstm=con.prepareStatement("SELECT * FROM locacao INNER JOIN filme ON locacao.id_filme_locacao= id_filme INNER JOIN cliente ON locacao.id_cliente_locacao =id_cliente"+
                   " WHERE " + ((ComboBoxItem)cbox.getSelectedItem()).coluna + " = '" + busca.getText().toString().trim() + "'");
            rs = pstm.executeQuery();

            while (rs.next()) {

                String id_locacao = rs.getString("id_locacao");
                String nome = rs.getString("nome");
                String titulo = rs.getString("titulo");
                Date dataLocacao = rs.getDate("data_locacao");
                Double preco = rs.getDouble("preco");
                Date dataDevolucao = rs.getDate("data_devolucao");
                Object coluna[] = {id_locacao, nome, titulo, dataLocacao, preco, dataDevolucao};

                modelo.addRow(coluna);

            }

            tabela.setModel(modelo);
            if(tabela.getRowCount()==0){
                JOptionPane.showMessageDialog(tabela, "Locação" + busca.getText() + " não encontrada.");
            }

        } catch (Exception e) {

//            System.out.println("Erro:" + e);
           
            JOptionPane.showMessageDialog(tabela, "Formato de busca incorreto");
             e.printStackTrace();
        }
        return null;

    }

    public String returnBuscaCliente(JTable tabela, JTextField busca, JComboBox cbox) {
        try {
            PreparedStatement pstm;
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Id Cliente");
            modelo.addColumn("Nome");
            modelo.addColumn("CPF");
            modelo.addColumn("Data de cadastro");
            modelo.addColumn("Cliente premium");
            modelo.addColumn("Idade");
            modelo.addColumn("Endereço");
            modelo.addColumn("Telefone");
            getConnection();

            pstm = con.prepareStatement("SELECT * FROM cliente   WHERE " + ((ComboBoxItem)cbox.getSelectedItem()).coluna + " = '" + busca.getText().toString().trim() + "'");
            rs = pstm.executeQuery();

            while (rs.next()) {

                String id_cliente = rs.getString("id_cliente");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String data_cadastro = rs.getString("data_cadastro");
                String cliente_premium = rs.getString("cliente_premium");
                int idade = rs.getInt("idade");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                Object coluna[] = {id_cliente, nome, cpf, data_cadastro, cliente_premium, idade, endereco, telefone,};;

                modelo.addRow(coluna);

            }
            if(tabela.getRowCount()==0){
                JOptionPane.showMessageDialog(tabela, "Cliente" + busca.getText() + " não encontrado.");
            }

            tabela.setModel(modelo);

        } catch (Exception e) {

//            System.out.println("Erro:" + e);
//            e.printStackTrace();
            JOptionPane.showMessageDialog(tabela, "Cliente" + busca.getText() + " não encontrado.");
        }
        return null;

    }

    public String returnBuscaFilme(JTable tabela, JTextField busca, JComboBox cbox) {
        try {
            PreparedStatement pstm;
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("id_filme");
            modelo.addColumn("Idioma");
            modelo.addColumn("Legendado");
            modelo.addColumn("Duração");
            modelo.addColumn("Gênero");
            modelo.addColumn("Classificação");
            modelo.addColumn("Titulo");
            modelo.addColumn("Data de lançamento");
            getConnection();

            pstm = con.prepareStatement("SELECT * FROM filme  WHERE " + ((ComboBoxItem)cbox.getSelectedItem()).coluna + " = '" + busca.getText().toString().trim() + "'");
            rs = pstm.executeQuery();

            while (rs.next()) {

                String id_filme = rs.getString("id_filme");
                String idioma_original = rs.getString("idioma_original");
                boolean legenda = rs.getBoolean("legenda");
                String duracao = rs.getString("duracao");
                String genero = rs.getString("genero");
                int classificacao = rs.getInt("classificacao");
                String titulo = rs.getString("titulo");
                String data_lancamento = rs.getString("data_lancamento");
                Object coluna[] = {id_filme, idioma_original, legenda, duracao, genero, classificacao, titulo, data_lancamento};

                modelo.addRow(coluna);

            }

            tabela.setModel(modelo);

        } catch (Exception e) {

//            System.out.println("Erro:" + e);
//            e.printStackTrace();
            JOptionPane.showMessageDialog(tabela, "Filme" + busca.getText() + " não encontrado.");
        }
        return null;

    }

    public void deletarCliente(JTable tabela) {
        try {
            getConnection();
            stm = con.createStatement();
            String sql = ("DELETE FROM cliente WHERE id_cliente ='" + tabela.getValueAt(tabela.getSelectedRow(), 0) + "'");
            stm.executeUpdate(sql);
            System.out.println("Cliente excluido");
            JOptionPane.showMessageDialog(tabela, "Cliente excluido");
            returnClientes(tabela);
        } catch (Exception e) {
            System.out.println("Erro:" + e);
            JOptionPane.showMessageDialog(tabela, "Cliente com pendencias não pode ser excluido");

        }

    }

    public void deletarFilme(JTable tabela) {
        try {
            getConnection();
            stm = con.createStatement();
            String sql = ("DELETE FROM filme WHERE id_filme ='" + tabela.getValueAt(tabela.getSelectedRow(), 0) + "'");
            stm.executeUpdate(sql);
            System.out.println("Filme excluido");
            JOptionPane.showMessageDialog(tabela, "Filme excluido");
            returnFilmes(tabela);
        } catch (Exception e) {
            System.out.println("Erro:" + e);
            JOptionPane.showMessageDialog(tabela, "Filme alugado não pode ser excluido");
        }

    }

    public void deletarLocacao(JTable tabela) {
        try {
            getConnection();
            stm = con.createStatement();
            String sql = ("DELETE FROM locacao WHERE id_locacao ='" + tabela.getValueAt(tabela.getSelectedRow(), 0) + "'");
            stm.executeUpdate(sql);
            System.out.println("Filme devolvido");
            JOptionPane.showMessageDialog(tabela, "Filme devolvido");
            returnAlugados(tabela);
        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }

    }

    void editarCliente(JTable tabela, String nometxt, String endereco, String cpf, boolean cp, int telefone, int idade) {
        try {
            getConnection();
            stm = con.createStatement();
            String sql = "UPDATE cliente set nome ='" + nometxt + "' ,endereco='" + endereco + "',cpf='" + cpf + "',cliente_premium=" + cp +  ",telefone=" + telefone + ",idade=" + idade + " WHERE id_cliente=" + tabela.getValueAt(tabela.getSelectedRow(), 0);
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(tabela, "Cliente " + nometxt + " editado com sucesso !!");

        } catch (Exception e) {
//            System.out.println("Erro:" + e);
            e.printStackTrace();

        }

    }

    void editarFilme(JTable tabela, String idioma_o, boolean legenda, String duracao, String genero, int classificacao, String titulo, String data_l) {
        try {
            getConnection();
            stm = con.createStatement();
            String sql = "UPDATE filme set idioma_original='" + idioma_o + "' , legenda=" + legenda + "	,duracao='" + duracao + "'	,genero=	'" + genero + "',classificacao=" + classificacao + ",titulo='" + titulo + "'	,data_lancamento='" + data_l + "' WHERE id_filme=" + tabela.getValueAt(tabela.getSelectedRow(), 0);
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(tabela, "Filme " + titulo + " editado com sucesso !!");

        } catch (Exception e) {
//            System.out.println("Erro:" + e);
            e.printStackTrace();

        }

    }

    void editarLocacao(JTable tabela, int idcliente, int idfilme, String datal, String preco, String datad) {
        try {
            getConnection();
            stm = con.createStatement();
            String sql = "UPDATE locacao set id_cliente_locacao='" + idcliente + "' , id_filme_locacao='" + idfilme + "', data_locacao='" + datal + "', preco='" + preco + "', data_devolucao ='" + datad + "' WHERE id_locacao=" + tabela.getValueAt(tabela.getSelectedRow(), 0);
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(tabela, "Locação editada com sucesso !");

        } catch (Exception e) {
//            System.out.println("Erro:" + e);
            e.printStackTrace();

        }

    }

    public String clienteSelecionado(JTable tabela) {
        try {
            PreparedStatement pstm;
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Id Cliente");
            modelo.addColumn("Nome");
            modelo.addColumn("CPF");
            modelo.addColumn("Data de cadastro");
            modelo.addColumn("Cliente premium");
            modelo.addColumn("Idade");
            modelo.addColumn("Endereço");
            modelo.addColumn("Telefone");
            getConnection();

            pstm = con.prepareStatement("SELECT * FROM cliente  WHERE nome = '" + tabela.getValueAt(tabela.getSelectedRow(), 1) + "'");
            rs = pstm.executeQuery();

            while (rs.next()) {

                String id_cliente = rs.getString("id_cliente");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String data_cadastro = rs.getString("data_cadastro");
                String cliente_premium = rs.getString("cliente_premium");
                int idade = rs.getInt("idade");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                Object coluna[] = {id_cliente, nome, cpf, data_cadastro, cliente_premium, idade, endereco, telefone,};

                modelo.addRow(coluna);

            }

            tabela.setModel(modelo);
            

        } catch (Exception e) {

//            System.out.println("Erro:" + e);
            e.printStackTrace();
//            JOptionPane.showMessageDialog(tabela, "Cliente"+busca.getText()+" não encontrado.");
        }
        return null;

    }

    public String filmeSelecionado(JTable tabela) {
        try {
            PreparedStatement pstm;
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("id_filme");
            modelo.addColumn("Idioma");
            modelo.addColumn("Legendado");
            modelo.addColumn("Duração");
            modelo.addColumn("Gênero");
            modelo.addColumn("Classificação");
            modelo.addColumn("Titulo");
            modelo.addColumn("Data de lançamento");
            getConnection();
            pstm = con.prepareStatement("SELECT * FROM filme  WHERE titulo = '" + tabela.getValueAt(tabela.getSelectedRow(), 2) + "'");
            rs = pstm.executeQuery();

            while (rs.next()) {
                String id_filme = rs.getString("id_filme");
                String idioma_original = rs.getString("idioma_original");
                boolean legenda = rs.getBoolean("legenda");
                String duracao = rs.getString("duracao");
                String genero = rs.getString("genero");
                int classificacao = rs.getInt("classificacao");
                String titulo = rs.getString("titulo");
                String data_lancamento = rs.getString("data_lancamento");
                Object coluna[] = {id_filme, idioma_original, legenda, duracao, genero, classificacao, titulo, data_lancamento};

                modelo.addRow(coluna);

            }

            tabela.setModel(modelo);

        } catch (Exception e) {

//            System.out.println("Erro:" + e);
            e.printStackTrace();
//            JOptionPane.showMessageDialog(tabela, "Cliente"+busca.getText()+" não encontrado.");
        }
        return null;

    }

    public String filmeLocacao(JTable tabela) {
        try {
            PreparedStatement pstm;
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("id_locacao");
            modelo.addColumn("id_cliente_locacao");
            modelo.addColumn("id_filme_locacao");
            modelo.addColumn("dataLocacao");
            modelo.addColumn("preco");
            modelo.addColumn("dataDevolucao");
            getConnection();
            pstm = con.prepareStatement("SELECT * FROM locacao INNER JOIN filme ON locacao.id_filme_locacao= id_filme INNER JOIN cliente ON locacao.id_cliente_locacao =id_cliente"+
                   " WHERE id_filme_locacao = '" + tabela.getValueAt(tabela.getSelectedRow(), 0)+ "'");
            rs = pstm.executeQuery();

            while (rs.next()) {
                String id_locacao = rs.getString("id_locacao");
                String id_cliente_locacao = rs.getString("nome");
                String id_filme_locacao = rs.getString("titulo");
                Date dataLocacao = rs.getDate("data_locacao");
                Double preco = rs.getDouble("preco");
                Date dataDevolucao = rs.getDate("data_devolucao");
                Object coluna[] = {id_locacao, id_cliente_locacao, id_filme_locacao, dataLocacao, preco, dataDevolucao};

                modelo.addRow(coluna);

            }

            tabela.setModel(modelo);
            if(tabela.getRowCount()==0){
                JOptionPane.showMessageDialog(tabela, "Filme sem locação");
            }

        } catch (Exception e) {

//            System.out.println("Erro:" + e);
            e.printStackTrace();

        }
        return null;

    }
    public String clienteLocacao(JTable tabela) {
        try {
            PreparedStatement pstm;
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("id_locacao");
            modelo.addColumn("id_cliente_locacao");
            modelo.addColumn("id_filme_locacao");
            modelo.addColumn("dataLocacao");
            modelo.addColumn("preco");
            modelo.addColumn("dataDevolucao");
            getConnection();

            pstm = con.prepareStatement("SELECT * FROM locacao INNER JOIN filme ON locacao.id_filme_locacao= id_filme INNER JOIN cliente ON locacao.id_cliente_locacao =id_cliente"+
                   " WHERE id_cliente_locacao = '" + tabela.getValueAt(tabela.getSelectedRow(), 0)+ "'");
            rs = pstm.executeQuery();

            while (rs.next()) {
                String id_locacao = rs.getString("id_locacao");
                String id_cliente_locacao = rs.getString("nome");
                String id_filme_locacao = rs.getString("titulo");
                Date dataLocacao = rs.getDate("data_locacao");
                Double preco = rs.getDouble("preco");
                Date dataDevolucao = rs.getDate("data_devolucao");
                Object coluna[] = {id_locacao, id_cliente_locacao, id_filme_locacao, dataLocacao, preco, dataDevolucao};

                modelo.addRow(coluna);

            }

            tabela.setModel(modelo);
           if(tabela.getRowCount()==0){
                JOptionPane.showMessageDialog(tabela, "Cliente sem locação");
            }
//            returnClientes(tabela);

        } catch (Exception e) {

//            System.out.println("Erro:" + e);
            e.printStackTrace();

        }
        return null;

    }
    public String ClientesCombox(JComboBox listClient, JPanel p) {
        try {

            DefaultComboBoxModel modelo = new DefaultComboBoxModel();

            getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM cliente");

            rs = pstm.executeQuery();

            while (rs.next()) {
                String id_cliente = rs.getString("id_cliente");
                String nome = rs.getString("nome");

                modelo.addElement(id_cliente+"-"+nome);
//  
               
              
            }
            
            listClient.setModel(modelo);
            p.add(listClient);
             
        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }
        return null;

    }
    public String FilmesCombox(JComboBox listFilm, JPanel p) {
        try {

            DefaultComboBoxModel modelo = new DefaultComboBoxModel();

            getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM filme");

            rs = pstm.executeQuery();

            while (rs.next()) {
                String id_filme = rs.getString("id_filme");
                String titulo = rs.getString("titulo");


                modelo.addElement(id_filme+"-"+titulo);

               
              
            }
            
            listFilm.setModel(modelo);
            p.add(listFilm);
             
        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }
        return null;

    }

}

//java -jar architect.jar

