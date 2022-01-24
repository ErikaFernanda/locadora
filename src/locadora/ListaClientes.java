package locadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ListaClientes extends JPanel {

    Locadora locadora;
    String[] colunas;
    JTable tabela;

    JPanel boxbusca;
    JTextField busca;
    JButton btnbusca;

    GridBagConstraints gbc;
    GridBagConstraints gbc2;

    public ListaClientes(Locadora locadora) {

        this.locadora = locadora;
        setSize(500, 500);
        setLayout(new BorderLayout());

        ImageIcon img_menu = new ImageIcon(getClass().getResource("busca.png"));
        busca = new JTextField("  ");
        ComboBoxItem[] opcoes = { new ComboBoxItem("Codigo do cliente", "id_cliente"), new ComboBoxItem("Nome", "nome"),
                new ComboBoxItem("CPF", "cpf"), new ComboBoxItem("Data de cadastro", "data_cadastro"),
                new ComboBoxItem("Cliente premium", "cliente_premium"), new ComboBoxItem("Idade", "idade"),
                new ComboBoxItem("Endereço", "endereco"), new ComboBoxItem("Telefone", "telefone") };
        JComboBox opc = new JComboBox(opcoes);
        opc.setBackground(Color.WHITE);

        btnbusca = new JButton(null, img_menu);
        boxbusca = new JPanel();

        busca.setPreferredSize(new Dimension(50, 30));
        btnbusca.setPreferredSize(new Dimension(50, 30));
        boxbusca.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        busca.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        boxbusca.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 50;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.fill = GridBagConstraints.BASELINE;
        btnbusca.setBackground(Color.WHITE);
        btnbusca.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));

        boxbusca.add(opc, gbc2);
        boxbusca.add(busca, gbc);
        boxbusca.add(btnbusca);
        boxbusca.setBackground(Color.white);
        this.add(boxbusca, BorderLayout.PAGE_START);
        // -----------------------------------tabela----------------------------------------------

        tabela = new JTable(new DefaultTableModel()) {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        tabela.setRowHeight(75);

        tabela.getTableHeader().setReorderingAllowed(false);
        this.add(new JScrollPane(tabela), BorderLayout.CENTER);
        btnbusca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                locadora.bd.returnBuscaCliente(tabela, busca, opc);

            }
        });

        JPopupMenu menu = new JPopupMenu();
        JMenuItem editar = new JMenuItem("Editar");
        JMenuItem excluir = new JMenuItem("Excluir Cliente");
        JMenuItem ver_locacoes = new JMenuItem("Ver locações");
        JMenuItem cadastrar = new JMenuItem("Cadastrar novo");

        menu.add(editar);
        menu.add(excluir);
        menu.add(ver_locacoes);
        menu.add(cadastrar);

        tabela.addMouseListener(new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {

                    Point point = e.getPoint();
                    int currentRow = tabela.rowAtPoint(point);

                    tabela.setRowSelectionInterval(currentRow, currentRow);
                    menu.show(tabela, e.getX(), e.getY());

                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        cadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                locadora.cadastrarCliente = new CadastrarCliente(locadora);
                locadora.cadastrarCliente.setPreferredSize(locadora.menuBarra.getSize());
                locadora.menuBarra.removeAll();
                locadora.menuBarra.add(locadora.cadastrarCliente);
                locadora.menuBarra.revalidate();
                locadora.menuBarra.setVisible(true);

            }
        });
        excluir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                locadora.bd.deletarCliente(tabela);

            }
        });
        editar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                locadora.cadastrarCliente = new CadastrarCliente(locadora);
                locadora.cadastrarCliente.setPreferredSize(locadora.menuBarra.getSize());
                locadora.menuBarra.removeAll();
                locadora.menuBarra.add(locadora.cadastrarCliente);
                locadora.menuBarra.revalidate();
                locadora.menuBarra.setVisible(true);
                locadora.cadastrarCliente.editCliente(tabela);

            }
        });
        ver_locacoes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                locadora.bd.clienteLocacao(tabela);

            }
        });

    }

}
