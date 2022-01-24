package locadora;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CadastrarFilme extends JPanel {

    Locadora locadora;

    JTextField campoTitulo;
    JTextField campoIdiomaoriginal;
    JCheckBox campoLegenda;
    JTextField campoDuracao;
    JTextField campoGenero;
    JTextField campoClassificacao;
    JTextField campoData_lancamento;

    JLabel titulotxt;
    JLabel idiomaoriginaltxt;
    JLabel legendatxt;
    JLabel duracaotxt;
    JLabel generotxt;
    JLabel classificacaotxt;
    JLabel data_lancamentotxt;

    JButton Salvarcf;
    JButton Editarf;
    JButton Salvarl;

    public CadastrarFilme(Locadora locadora) {
        this.locadora = locadora;
        setPreferredSize(new Dimension(500, 500));
        setLayout(new GridLayout(2, 1));

        campoTitulo = new JTextField();
        campoIdiomaoriginal = new JTextField();
        campoLegenda = new JCheckBox("Sim");
        campoDuracao = new JTextField();
        campoGenero = new JTextField();
        campoClassificacao = new JTextField();
        campoData_lancamento = new JTextField();

        titulotxt = new JLabel("Titulo:");
        idiomaoriginaltxt = new JLabel("Idioma Original:");
        legendatxt = new JLabel("Legenda:");
        duracaotxt = new JLabel("Duração:");
        generotxt = new JLabel("Genero:");
        classificacaotxt = new JLabel("Classificação:");
        data_lancamentotxt = new JLabel("Data de lançamento:");

        Salvarcf = new JButton("Salvar");
        Salvarl = new JButton("Salvar locação");
        Editarf = new JButton("Salvar edição");

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(8, 1));
        painel.add(titulotxt);
        painel.add(campoTitulo);

        painel.add(idiomaoriginaltxt);
        painel.add(campoIdiomaoriginal);

        painel.add(legendatxt);
        painel.add(campoLegenda);

        painel.add(duracaotxt);
        painel.add(campoDuracao);

        painel.add(generotxt);
        painel.add(campoGenero);

        painel.add(classificacaotxt);
        painel.add(campoClassificacao);

        painel.add(data_lancamentotxt);
        painel.add(campoData_lancamento);

        JPanel painel2 = new JPanel();
        painel2.setLayout(new GridLayout(6, 1));
        painel2.add(Salvarcf);
        painel2.add(Editarf);
        painel2.add(Salvarl);
        Editarf.setVisible(false);
        Salvarl.setVisible(false);

        Salvarcf.addActionListener(new ActionListener() {
           

            @Override
            public void actionPerformed(ActionEvent e) {
                locadora.bd.addFilme(campoIdiomaoriginal.getText(), campoLegenda.isSelected(),campoDuracao.getText(), campoGenero.getText(), campoClassificacao.getText(), campoTitulo.getText(), campoData_lancamento.getText());
                JOptionPane.showMessageDialog(locadora, "Filme " + campoTitulo.getText() + " cadastrado com sucesso !!");
                campoTitulo.setText("");
                campoIdiomaoriginal.setText("");
                campoLegenda.setSelected(false);
                campoDuracao.setText("");
                campoGenero.setText("");
                campoClassificacao.setText("");
                campoData_lancamento.setText("");

            }
        });

        this.add(painel);
        this.add(painel2);

        setVisible(true);

    }

    public static void main(String[] args) {

    }

    public void editFilme(JTable tabela) {
        campoIdiomaoriginal.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
        campoLegenda.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
        campoDuracao.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
        campoGenero.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
        campoClassificacao.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 5).toString());
        campoTitulo.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 6).toString());
        campoData_lancamento.setText((String) tabela.getValueAt(tabela.getSelectedRow(), 7).toString());

        Salvarcf.setVisible(false);
        Editarf.setVisible(true);
        Editarf.addActionListener(new ActionListener() {
//            public String format(Date d) {
//                DateFormat format = new SimpleDateFormat("dd/MM/yy");
//                return format.format(d);
//            }

            @Override
            public void actionPerformed(ActionEvent e) {
                

                locadora.bd.editarFilme(locadora.listaFilmes.tabela, campoIdiomaoriginal.getText(), campoLegenda.isSelected(), campoDuracao.getText(), campoGenero.getText(), 2, campoTitulo.getText(), campoData_lancamento.getText());

            }
        });

    }


}
