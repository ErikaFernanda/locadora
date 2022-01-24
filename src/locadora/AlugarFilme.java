package locadora;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AlugarFilme extends JPanel {

    Locadora locadora;

    JTextField cliente;
    JTextField filme;
    JTextField datadelocacao;
    JTextField preco;
    JTextField datadedevolucao;

    JLabel clientetxt;
    JLabel filmetxt;
    JLabel datadelocacaotxt;
    JLabel precotxt;
    JLabel datadedevolucaotxt;

    JButton Salvarl;
    JButton SalvarEdit;

    JComboBox listClient;
    JComboBox listFilm;

    public AlugarFilme(Locadora locadora) {
        this.locadora = locadora;
        setSize(500, 500);
        setLayout(new GridLayout(2, 1));

        datadelocacao = new JTextField();
        preco = new JTextField();
        datadedevolucao = new JTextField();

        clientetxt = new JLabel("Cliente:");
        filmetxt = new JLabel("Filme:");
        datadelocacaotxt = new JLabel("Data de locação:");
        precotxt = new JLabel("Preço:");
        datadedevolucaotxt = new JLabel("Data de devolução:");

        Salvarl = new JButton("Salvar");
        SalvarEdit = new JButton("Salvar alteração");

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2));
        painel.add(clientetxt);
        listClient = new JComboBox(new DefaultComboBoxModel());
        locadora.bd.ClientesCombox(listClient, painel);

        painel.add(filmetxt);
        listFilm = new JComboBox(new DefaultComboBoxModel());
        locadora.bd.FilmesCombox(listFilm, painel);

        painel.add(datadelocacaotxt);
        painel.add(datadelocacao);

        painel.add(datadedevolucaotxt);
        painel.add(datadedevolucao);

        painel.add(precotxt);
        painel.add(preco);

        JPanel painel2 = new JPanel();
        painel2.setLayout(new GridLayout(6, 1));
        painel2.add(Salvarl);
        painel2.add(SalvarEdit);
        SalvarEdit.setVisible(false);
        Salvarl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                locadora.bd.addLocacao(locadora.listaFilmes.tabela, listClient.getSelectedItem(),
                        listFilm.getSelectedItem(), datadelocacao.getText(), preco.getText(),
                        datadedevolucao.getText());

            }
        });

        this.add(painel);
        this.add(painel2);

        setVisible(true);
    }

    public void editLocacao(JTable tabela) {
        Salvarl.setVisible(false);
        SalvarEdit.setVisible(true);
        cliente.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
        filme.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
        datadelocacao.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
        datadedevolucao.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
        preco.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 5).toString());
        filme.setEditable(false);
        cliente.setEditable(false);

        SalvarEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                locadora.bd.editarLocacao(locadora.listaLocacoes.tabela, 57, 9, datadelocacao.getText(),
                        datadedevolucao.getText(), preco.getText());

            }
        });

    }

}
