import java.util.ArrayList;

public class Roteador {
    private int id;
    private ArrayList<Integer> aparelhosConectados = new ArrayList<>();
    private ArrayList<Roteador> vizinhos = new ArrayList<>();
    private boolean isGateway;

    public Roteador(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void conectarAparelho(int idAparelho) {
        aparelhosConectados.add(idAparelho);
        System.out.println("📱 Aparelho " + idAparelho + " conectado ao roteador " + id);
    }

    public void adicionarVizinho(Roteador vizinho) {
        if (!vizinhos.contains(vizinho)) {
            vizinhos.add(vizinho);
            vizinho.vizinhos.add(this);
            System.out.println("🔗 Roteador " + id + " conectado com roteador " + vizinho.id);
        }
    }

    public boolean rotearMensagem(int destino, String mensagem, ArrayList<Integer> caminho) {
        caminho.add(this.id);
        System.out.println("\n🔄 Roteador " + id + " recebeu mensagem para " + destino + 
                         " | Caminho atual: " + caminho);

        if (temAparelho(destino)) {
            System.out.println("🎯 Roteador " + id + " ENTREGOU para aparelho " + destino);
            return true;
        }

        for (Roteador vizinho : vizinhos) {
            if (!caminho.contains(vizinho.id)) {
                System.out.println("➡️  Roteador " + id + " encaminhando para roteador " + vizinho.id);
                if (vizinho.rotearMensagem(destino, mensagem, caminho)) {
                    System.out.println("🔄 Roteador " + id + " confirmando entrega (caminho de volta)");
                    return true;
                }
            }
        }

        System.out.println("❌ Roteador " + id + " não encontrou destino");
        return false;
    }

    private boolean temAparelho(int idAparelho) {
        return aparelhosConectados.contains(idAparelho);
    }

    public void setGateway(boolean isGateway) {
        this.isGateway = isGateway;
        if (isGateway) System.out.println("🌉 Roteador " + id + " configurado como gateway");
    }
}