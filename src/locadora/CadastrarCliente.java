package locadora;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CadastrarCliente extends JPanel {

    Locadora locadora;

    JTextField campoNome;
    JTextField campoTelefone;
    JTextField campoEndereco;
    JTextField campoCPF;
    JTextField campoIdade;

    JCheckBox campoCliente_premium;

    JLabel nometxt;
    JLabel telefonetxt;
    JLabel enderecotxt;
    JLabel cpftxt;
    JLabel idadetxt;
    JLabel Data_cadastrotxt;
    JLabel Cliente_premiumtxt;

    JButton Salvarcc;
    JButton Editarcc;

    //
    public CadastrarCliente(Locadora locadora) {
        this.locadora = locadora;
        setPreferredSize(new Dimension(500, 500));
        setLayout(new GridLayout(2, 1));

        campoNome = new JTextField();
        campoTelefone = new JTextField();
        campoEndereco = new JTextField();
        campoCPF = new JTextField();
        campoIdade = new JTextField();

        campoCliente_premium = new JCheckBox("Sim");

        nometxt = new JLabel("Nome:");
        telefonetxt = new JLabel("Telefone:");
        enderecotxt = new JLabel("Endereço:");
        cpftxt = new JLabel("CPF:");
        idadetxt = new JLabel("Idade:");
        Data_cadastrotxt = new JLabel("Data de Cadastro:");
        Cliente_premiumtxt = new JLabel("Cliente Premium:");

        Salvarcc = new JButton("Salvar");
        Editarcc = new JButton("Salvar edição");

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(8, 1));
        painel.add(nometxt);
        painel.add(campoNome);

        painel.add(telefonetxt);
        painel.add(campoTelefone);

        painel.add(enderecotxt);
        painel.add(campoEndereco);

        painel.add(cpftxt);
        painel.add(campoCPF);

        painel.add(idadetxt);
        painel.add(campoIdade);

        painel.add(Cliente_premiumtxt);
        painel.add(campoCliente_premium);

        JPanel painel2 = new JPanel();
        painel2.setLayout(new GridLayout(6, 1));
        painel2.add(Salvarcc);
        painel2.add(Editarcc);
        Editarcc.setVisible(false);
        Salvarcc.addActionListener(new ActionListener() {

            // public String format(Date d) {
            // DateFormat format = new SimpleDateFormat("MM/dd/yy");
            // return format.format(d);
            // }

            @Override
            public void actionPerformed(ActionEvent e) {
                DateFormat format = new SimpleDateFormat("dd/MM/yy");
                Date d = new Date(System.currentTimeMillis());
                locadora.bd.addCliente(campoNome.getText(), campoEndereco.getText(), campoCPF.getText(),
                        format.format(d), campoCliente_premium.isSelected(), campoTelefone.getText(),
                        campoIdade.getText());

                JOptionPane.showMessageDialog(locadora,
                        "Cliente " + campoNome.getText() + " cadastrado com sucesso !!");
                campoNome.setText("");
                campoTelefone.setText("");
                campoEndereco.setText("");
                campoCPF.setText("");
                campoIdade.setText("");

                campoCliente_premium.setSelected(false);

            }
        });

        this.add(painel);
        this.add(painel2);

        setVisible(true);

    }

    public void editCliente(JTable tabela) {
        campoNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
        campoTelefone.setText(tabela.getValueAt(tabela.getSelectedRow(), 7).toString());
        campoEndereco.setText(tabela.getValueAt(tabela.getSelectedRow(), 6).toString());
        campoCPF.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
        campoIdade.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 5).toString());
        campoCliente_premium.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 4).toString());

        Salvarcc.setVisible(false);
        Editarcc.setVisible(true);
        Editarcc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                locadora.bd.editarCliente(locadora.listaClientes.tabela, campoNome.getText(), campoEndereco.getText(),
                        campoCPF.getText(), campoCliente_premium.isSelected(), 2, 2);

            }
        });

    }

}
