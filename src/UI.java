import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UI extends JFrame {
    JTextField codField;
    JTextField nomeField;
    JTextArea txtCurriculo;

    JRadioButton checkboxFeminino;
    JRadioButton checkboxMasculino;
    ButtonGroup sexGroup;

    JComboBox<String> interesseComboBox;
    JComboBox<String> atuacaoComboBox;

    JButton salvarButton;
    JButton novoButton;
    JButton cancelButton;
    JButton antButton;
    JButton proxButton;

    JMenuItem salvarMenuItem;
    JMenuItem sairMenuItem;

    public UI(){
        //jframe
        super("User Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        //Menu da seção Arquivo
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu arqMenu = new JMenu("Arquivo");
        JMenu envSubMenu = new JMenu("Enviar");
        JMenu editMenu = new JMenu("Editar");

        envSubMenu.add(new JMenuItem("Email"));
        envSubMenu.add(new JMenuItem("Impressora"));
        arqMenu.add(envSubMenu);
        salvarMenuItem = new JMenuItem("Salvar");
        sairMenuItem = new JMenuItem("Sair");
        arqMenu.add(sairMenuItem);
        arqMenu.add(sairMenuItem);
        menuBar.add(arqMenu);
        menuBar.add(editMenu);

        // Painel principal
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setBorder(new EmptyBorder(5, 5, 5, 5));

        // componentes
        JLabel lblTitulo = new JLabel("Avaliação");
        lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 16));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        formContainer.add(lblTitulo);
        formContainer.add(Box.createVerticalStrut(15));
        //Campo codigo
        JLabel codiJLabel = new JLabel("Codigo");
        codField = new JTextField(15);
        JPanel painelCod = new JPanel();
        painelCod.add(codiJLabel);
        painelCod.add(codField);
        formContainer.add(painelCod);
        //Campo nome
        nomeField = new JTextField();
        formContainer.add(createLabelAndFieldPanel("Nome", nomeField));
        //Campo sexo com check box
        JPanel sexoPanel = new JPanel(new BorderLayout(5, 5));
        sexoPanel.add(new JLabel("Sexo:"), BorderLayout.WEST);
        checkboxFeminino = new JRadioButton("Feminino");
        checkboxMasculino = new JRadioButton("Masculino");
        sexGroup = new ButtonGroup();
        sexGroup.add(checkboxFeminino);
        sexGroup.add(checkboxMasculino);
        JPanel checkBox = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        checkBox.add(checkboxFeminino); 
        checkBox.add(checkboxMasculino);
        
        sexoPanel.add(checkBox, BorderLayout.CENTER);
        sexoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, sexoPanel.getPreferredSize().height)); 
        formContainer.add(sexoPanel);
        formContainer.add(Box.createVerticalStrut(10));
        
        //Campo curriculum
        JPanel curriculumPanel = new JPanel(new BorderLayout());
        curriculumPanel.add(new JLabel("Curriculum Vitae"), BorderLayout.NORTH);
        JTextArea txtCurriculum = new JTextArea(10, 20);
        curriculumPanel.add(new JScrollPane(txtCurriculum), BorderLayout.CENTER);
        formContainer.add(curriculumPanel);
        formContainer.add(Box.createVerticalStrut(10));
        
        //Seção
        JPanel areasSectionPanel = new JPanel(new BorderLayout());
        areasSectionPanel.add(new JLabel("Áreas"), BorderLayout.NORTH);
        JPanel areasFieldsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        areasFieldsPanel.add(new JLabel("Interesse:"));
        interesseComboBox = new JComboBox<>(new String[]{"Desenvolvedor", "Analista de Sistemas"});
        areasFieldsPanel.add(interesseComboBox);
        areasFieldsPanel.add(new JLabel("Atuação:"));
        atuacaoComboBox = new JComboBox<>(new String[]{"Programação", "Banco de Dados", "Redes", "Segurança da Informação"});
        areasFieldsPanel.add(atuacaoComboBox);
        areasSectionPanel.add(areasFieldsPanel, BorderLayout.CENTER);
        areasSectionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, areasSectionPanel.getPreferredSize().height));
        formContainer.add(areasSectionPanel);
        //Botões embaixo
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    
        novoButton = new JButton("Novo");
        cancelButton = new JButton("Cancelar");
        salvarButton = new JButton("Salvar");
        antButton = new JButton("Anterior");
        proxButton= new JButton("Proximo");
        
        buttonPanel.add(salvarButton);
        buttonPanel.add(novoButton);
        buttonPanel.add(proxButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(antButton);
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                salvarFicha();
            }
        });
        salvarMenuItem.addActionListener(e -> salvarFicha());
        novoButton.addActionListener(e -> limparFormulario());
        cancelButton.addActionListener(e -> sairProg());
        sairMenuItem.addActionListener(e -> sairProg());
        antButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Não implementada"));
        proxButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Não implementada"));
        getContentPane().add(formContainer, BorderLayout.CENTER);
        getContentPane().add(buttonPanel,BorderLayout.SOUTH);
    }
    private JPanel createLabelAndFieldPanel(String labelText, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel(labelText), BorderLayout.WEST);
        panel.add(component, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel.getPreferredSize().height));
        return panel;
    }
    //Salvar
    private void salvarFicha(){
        String cod = codField.getText();
        String nome = nomeField.getText();
        String curriculum = txtCurriculo.getText();
        String interesse = (String) interesseComboBox.getSelectedItem();
        String atuacao = (String) atuacaoComboBox.getSelectedItem();
        String sexo;

        if(nome == null || nome.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Campo 'Nome' é obrigatorio", "Error Validation", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (checkboxFeminino.isSelected()) {
            sexo = "Feminino";
        } else if (checkboxMasculino.isSelected()) {
            sexo = "Masculino";
        } else {
            sexo = "Não selecionado";
        }
        StringBuilder dado = new StringBuilder();
        dado.append("Dados da ficha\n\n");
        dado.append("Codigo: ").append(cod).append("\n");
        dado.append("Nome: ").append(nome).append("\n");
        dado.append("Sexo: ").append(sexo).append("\n");
        dado.append("Área de Interesse: ").append(interesse).append("\n");
        dado.append("Área de Atuação: ").append(atuacao).append("\n\n");
        dado.append("Currículo:\n").append(curriculum);

        JOptionPane.showMessageDialog(this, dado.toString(), "Ficha Salva!", JOptionPane.INFORMATION_MESSAGE);
    }
    private void limparFormulario(){
        codField.setText("");
        nomeField.setText("");
        txtCurriculo.setText("");
        sexGroup.clearSelection();
        interesseComboBox.setSelectedIndex(0);
        atuacaoComboBox.setSelectedIndex(0);
        JOptionPane.showMessageDialog(this, "Formulário limpo.", "Novo", JOptionPane.INFORMATION_MESSAGE);
    }
    private void sairProg(){
        int conf = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente fechar o programa?",
                "Confirmação de Saída",
                JOptionPane.YES_NO_OPTION
        );
        if (conf == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
