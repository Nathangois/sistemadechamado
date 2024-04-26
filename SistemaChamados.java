import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SistemaChamados {

    private ArrayList<Chamado> listaChamados;
    private JTable table;
    private DefaultTableModel model;

    public SistemaChamados() {
        listaChamados = new ArrayList<>();
        initializeTable();
    }

    private void initializeTable() {
        String[] colunas = {"Índice", "Motivo", "Urgência"};
        model = new DefaultTableModel(colunas, 0);
        table = new JTable(model);
    }

    public void adicionarChamado(String motivo, int urgencia) {
        Chamado novoChamado = new Chamado(motivo, urgencia);
        listaChamados.add(novoChamado);
        refreshTable();
    }

    public void removerChamado(int indice) {
        if (indice >= 0 && indice < listaChamados.size()) {
            listaChamados.remove(indice);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(null, "Índice inválido!");
        }
    }

    public void editarChamado(int indice, String novoMotivo, int novaUrgencia) {     
        if (indice >= 0 && indice < listaChamados.size()) {         
            Chamado chamadoEditado = listaChamados.get(indice);         
            chamadoEditado.setMotivo(novoMotivo);         
            chamadoEditado.setUrgencia(novaUrgencia);         
            refreshTable();     } 
            else {
                JOptionPane.showMessageDialog(null, "Índice inválido!");} 
            }

    private void refreshTable() {
        model.setRowCount(0);
        for (int i = 0; i < listaChamados.size(); i++) {
            Chamado chamado = listaChamados.get(i);
            Object[] linha = {i, chamado.getMotivo(), chamado.getUrgencia()};
            model.addRow(linha);
        }
    }

    public void exibirChamados() {
        if (listaChamados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lista de chamados vazia.");
            return;
        }

        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Lista de Chamados", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        SistemaChamados sistema = new SistemaChamados();

        while (true) {
            String[] opcoes = {"Adicionar Chamado", "Remover Chamado", "Exibir Chamado", "Editar Chamados", "Sair"};
            int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Sistema de Chamados",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

            switch (escolha) {
                case 0: // Adicionar Chamado
                    String motivo = JOptionPane.showInputDialog("Digite o motivo do chamado:");
                    int urgencia = Integer.parseInt(JOptionPane.showInputDialog("Digite o nível de urgência (1 a 10):"));
                    sistema.adicionarChamado(motivo, urgencia);
                    break;
                case 1: // Remover Chamado
                    int indice = Integer.parseInt(JOptionPane.showInputDialog("Digite o índice do chamado a ser removido:"));
                    sistema.removerChamado(indice);
                    break;
                case 2: // Exibir Chamados
                    sistema.exibirChamados();
                    break;
                case 3: // Editar Chamados
                    int indiceEditar = Integer.parseInt(JOptionPane.showInputDialog("Digite o índice do chamado a ser editado:"));
                    String novoMotivo = JOptionPane.showInputDialog("Digite o novo motivo do chamado:");
                    int novaUrgencia = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo nível de urgência (1 a 10):"));
                    sistema.editarChamado(indiceEditar, novoMotivo, novaUrgencia);
                    sistema.exibirChamados();
                    break;
                case 4: // Sair
                    System.exit(0);
            }
        }
    }
}

class Chamado {
    private String motivo;
    private int urgencia;

    public Chamado(String motivo, int urgencia) {
        this.motivo = motivo;
        this.urgencia = urgencia;
    }

    public String getMotivo() {
        return motivo;
    }

    public int getUrgencia() {
        return urgencia;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public void setUrgencia(int urgencia) {
        this.urgencia = urgencia;
    }
}