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

public class ListaLocacoes extends JPanel {

    Locadora locadora;
    String[] colunas;
    JTable tabela;

    JPanel boxbusca;
    JTextField busca;
    JButton btnbusca;

    GridBagConstraints gbc;
    GridBagConstraints gbc2;

    public ListaLocacoes(Locadora locadora) {

        this.locadora = locadora;
        setSize(500, 500);
        setLayout(new BorderLayout());

        ImageIcon img_menu = new ImageIcon(getClass().getResource("busca.png"));
        busca = new JTextField("  ");
        ComboBoxItem[] opcoes = { new ComboBoxItem("Codigo de locação", "id_locacao"), new ComboBoxItem("Nome", "nome"),
                new ComboBoxItem("Titulo", "titulo"),
                new ComboBoxItem("Data de locação", "data_locacao"), new ComboBoxItem("Preço", "preco"),
                new ComboBoxItem("Data de devolução", "data_devolucao") };
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
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setRowHeight(75);
        this.add(new JScrollPane(tabela), BorderLayout.CENTER);

        btnbusca.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                locadora.bd.returnBuscaLocacao(tabela, busca, opc);

            }
        });

        JPopupMenu menu = new JPopupMenu();
        JMenuItem editar = new JMenuItem("Editar");
        JMenuItem ver_cliente = new JMenuItem("Ver Cliente");
        JMenuItem ver_filme = new JMenuItem("Ver Filme");
        JMenuItem devolver = new JMenuItem("Devolver");

        menu.add(editar);
        menu.add(ver_cliente);
        menu.add(ver_filme);
        menu.add(devolver);
        ver_cliente.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                locadora.bd.clienteSelecionado(tabela);

            }
        });
        ver_filme.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                locadora.bd.filmeSelecionado(tabela);

            }
        });
        editar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                locadora.alugarFilme = new AlugarFilme(locadora);
                locadora.alugarFilme.setPreferredSize(locadora.menuBarra.getSize());
                locadora.menuBarra.removeAll();
                locadora.menuBarra.add(locadora.alugarFilme);
                locadora.menuBarra.revalidate();
                locadora.menuBarra.setVisible(true);
                locadora.alugarFilme.editLocacao(tabela);

            }
        });

        tabela.addMouseListener(new MouseListener() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {

                    Point point = e.getPoint();
                    int currentRow = tabela.rowAtPoint(point);

                    tabela.setRowSelectionInterval(currentRow, currentRow);
                    // System.out.println(tabela.getSelectedColumn() + " fghjgfjfg"+
                    // tabela.getSelectedRow());
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
        devolver.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                locadora.bd.deletarLocacao(tabela);

            }
        });

    }

}
