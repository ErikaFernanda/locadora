package locadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class Locadora extends JFrame {

    JButton menuBotao;
    JPanel menuPainel;
    JPanel menuBarra;
    JPanel painel;

    JPanel boxbtn;

    JButton clientes_btn;
    JButton cadastrarC;
    JButton cadastrarF;
    JButton filmes_btn;
    JButton locacoes_btn;
    JButton alugar;
    JButton devolucao;

    JButton salvar;

    AlugarFilme alugarFilme;
    CadastrarCliente cadastrarCliente;
    CadastrarFilme cadastrarFilme;
    ListaClientes listaClientes;
    ListaFilmes listaFilmes;
    ListaLocacoes listaLocacoes;
    Bd bd;

    public Locadora() {
//      ...................Tela.............................    
//        this.setExtendedState(MAXIMIZED_BOTH);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("LOCADORA");
        this.setLayout(new BorderLayout());
        
//      ...................Menu.............................
        ImageIcon img_menu = new ImageIcon("/Z/fernandaerika/NetBeansProjects/Locadora/src/locadora/menu.png");
        JLabel img_film = new JLabel(new ImageIcon("/Z/fernandaerika/NetBeansProjects/Locadora/src/locadora/film.png"));

        menuBotao = new JButton(null, img_menu);
        menuBarra = new JPanel();
        menuPainel = new JPanel();
        boxbtn = new JPanel();
        JPanel painel0 = new JPanel();
        painel0.add(img_film);

        JPanel boxmenu = new JPanel();

        JPopupMenu menu = new JPopupMenu();

        JMenuItem cadastrarc = new JMenuItem("Cadastrar cliente");
        JMenuItem cadastrarf = new JMenuItem("Cadastrar filme");

        menu.add(cadastrarc);
        menu.add(cadastrarf);


        locacoes_btn = new JButton("Alugueis Ativos");

        filmes_btn = new JButton("Filmes");
        clientes_btn = new JButton("Clientes");


        clientes_btn.setBackground(Color.gray);

        filmes_btn.setBackground(Color.gray);

        locacoes_btn.setBackground(Color.gray);


        clientes_btn.setPreferredSize(new Dimension(190, 50));

        menuBotao.setPreferredSize(new Dimension(50, 50));
        menuBotao.setBackground(Color.RED);

        menuBotao.setBorderPainted(false);
        menuBotao.setFocusPainted(false);
        boxbtn.setBackground(Color.red);
        boxbtn.setLayout(new BorderLayout());
        boxbtn.add(menuBotao, BorderLayout.WEST);
        painel = new JPanel();
        painel.setLayout(new BorderLayout());
        menuBarra.setLayout(new BorderLayout());

        menuBarra.setBackground(Color.BLACK);

        painel.add(boxbtn, BorderLayout.PAGE_START);
        painel.add(menuBarra, BorderLayout.CENTER);

        painel0.setPreferredSize(new Dimension(100, 100));
        painel0.setBackground(Color.white);

        menuPainel.setPreferredSize(new Dimension(200, 100));
        menuPainel.setBackground(Color.WHITE);

        boxmenu.setLayout(new GridLayout(7, 2));
        boxmenu.setSize(900, 400);
        boxmenu.setBackground(Color.white);
        boxmenu.add(locacoes_btn);
        boxmenu.add(filmes_btn);
        boxmenu.add(clientes_btn);

        menuPainel.add(painel0);
        menuPainel.add(boxmenu);
        this.add(menuPainel, BorderLayout.WEST);
        this.add(painel);

        bd = new Bd();

        menuBotao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuPainel.isVisible()) {
                    menuPainel.setVisible(false);
                } else {
                    menuPainel.setVisible(true);
                }
            }
        });


        cadastrarc.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cadastrarCliente = new CadastrarCliente(Locadora.this);
                cadastrarCliente.setPreferredSize(menuBarra.getSize());
                menuBarra.removeAll();
                menuBarra.add(cadastrarCliente);
                menuBarra.revalidate();
                menuBarra.setVisible(true);

            }
        });
        cadastrarf.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cadastrarFilme = new CadastrarFilme(Locadora.this);
                cadastrarFilme.setPreferredSize(menuBarra.getSize());
                menuBarra.removeAll();
                menuBarra.add(cadastrarFilme);
                menuBarra.revalidate();
                menuBarra.setVisible(true);

            }
        });
        clientes_btn.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {

                    menu.show(clientes_btn, e.getX(), e.getY());

                }
                listaClientes = new ListaClientes(Locadora.this);
                listaClientes.setPreferredSize(menuBarra.getSize());
                menuBarra.removeAll();
                menuBarra.add(listaClientes);
                menuBarra.revalidate();
                menuBarra.setVisible(true);
                bd.returnClientes(listaClientes.tabela);

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
        });
        filmes_btn.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {

                    menu.show(filmes_btn, e.getX(), e.getY());

                }
                listaFilmes = new ListaFilmes(Locadora.this);
                listaFilmes.setPreferredSize(menuBarra.getSize());
                menuBarra.removeAll();
                menuBarra.add(listaFilmes);
                menuBarra.revalidate();
                menuBarra.setVisible(true);
                bd.returnFilmes(listaFilmes.tabela);
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
        });
        locacoes_btn.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {

                    menu.show(locacoes_btn, e.getX(), e.getY());

                }
                listaLocacoes = new ListaLocacoes(Locadora.this);
                listaLocacoes.setPreferredSize(menuBarra.getSize());
                menuBarra.removeAll();
                menuBarra.add(listaLocacoes);
                menuBarra.revalidate();
                menuBarra.setVisible(true);
                bd.returnAlugados(listaLocacoes.tabela);

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
        });
    }

    public static void main(String[] args) {
        Locadora telaLocadora = new Locadora();
        telaLocadora.setVisible(true);

    }

}
