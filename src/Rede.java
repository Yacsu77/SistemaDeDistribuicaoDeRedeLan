import java.util.ArrayList;

public class Rede {
    private ArrayList<Aparelho> aparelhos = new ArrayList<>();
    private ArrayList<Roteador> roteadores = new ArrayList<>();
    private String nome;

    public Rede(String nome) {
        this.nome = nome;
    }

    public void adicionarRoteador(Roteador roteador) {
        roteadores.add(roteador);
        System.out.println("\n🛜 Novo roteador " + roteador.getId() + " na rede " + nome);
    }

    public void adicionarAparelho(Aparelho aparelho) {
        aparelhos.add(aparelho);
        for (Roteador r : roteadores) {
            if (r.getId() == aparelho.getRouter()) {
                r.conectarAparelho(aparelho.getId());
                break;
            }
        }
    }

    public void enviarMensagem(int origem, int destino, String mensagem) {
        System.out.println("\n=================================");
        System.out.println("✉️  INICIANDO ENVIO de " + origem + " para " + destino);
        System.out.println("=================================");

        Aparelho remetente = getAparelho(origem);
        if (remetente == null) {
            System.out.println("❌ Remetente não encontrado!");
            return;
        }

        Roteador roteadorInicial = getRoteador(remetente.getRouter());
        if (roteadorInicial == null) {
            System.out.println("❌ Roteador do remetente não encontrado!");
            return;
        }

        boolean entregue = roteadorInicial.rotearMensagem(destino, mensagem, new ArrayList<>());

        if (entregue) {
            System.out.println("\n✅ Mensagem entregue com sucesso!");
        } else {
            System.out.println("\n❌ Falha na entrega da mensagem!");
        }
    }

    private Aparelho getAparelho(int id) {
        for (Aparelho a : aparelhos) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    private Roteador getRoteador(int id) {
        for (Roteador r : roteadores) {
            if (r.getId() == id) return r;
        }
        return null;
    }
}