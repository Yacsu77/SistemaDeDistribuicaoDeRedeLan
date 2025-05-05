public class Main {
    public static void main(String[] args) {
        // Configuração da rede
        Rede minhaRede = new Rede("Minha Casa");
        Rede vizinhoRede = new Rede("Casa do Vizinho");

        // Criação dos roteadores
        Roteador r1 = new Roteador(1);
        Roteador r2 = new Roteador(2);
        Roteador r3 = new Roteador(3);
        Roteador rv1 = new Roteador(101);
        Roteador rv2 = new Roteador(102);

        // Configuração dos gateways
        r3.setGateway(true);
        rv1.setGateway(true);
        r3.adicionarVizinho(rv1);

        // Topologia da rede
        r1.adicionarVizinho(r2);
        r2.adicionarVizinho(r3);
        rv1.adicionarVizinho(rv2);

        // Adicionar roteadores às redes
        minhaRede.adicionarRoteador(r1);
        minhaRede.adicionarRoteador(r2);
        minhaRede.adicionarRoteador(r3);
        vizinhoRede.adicionarRoteador(rv1);
        vizinhoRede.adicionarRoteador(rv2);

        // Criar e conectar aparelhos
        Aparelho[] meusAparelhos = {
            new Aparelho(1, 1, minhaRede, "Celular"),
            new Aparelho(2, 1, minhaRede, "Notebook"),
            new Aparelho(3, 2, minhaRede, "TV"),
            new Aparelho(4, 3, minhaRede, "Tablet")
        };

        Aparelho[] aparelhosVizinho = {
            new Aparelho(101, 101, vizinhoRede, "Celular Vizinho"),
            new Aparelho(102, 102, vizinhoRede, "TV Vizinho")
        };

        // Conectar aparelhos
        for (Aparelho a : meusAparelhos) {
            minhaRede.adicionarAparelho(a);
        }
        for (Aparelho a : aparelhosVizinho) {
            vizinhoRede.adicionarAparelho(a);
        }

        // Teste de comunicação
        System.out.println("\n================ TESTE 1 ================");
        meusAparelhos[0].enviarMensagem(4, "Vamos assistir um filme?");
        
        System.out.println("\n================ TESTE 2 ================");
        aparelhosVizinho[0].enviarMensagem(1, "Olá da casa ao lado!");
        
        System.out.println("\n================ TESTE 3 ================");
        meusAparelhos[1].enviarMensagem(102, "Dados para análise");
    }
}