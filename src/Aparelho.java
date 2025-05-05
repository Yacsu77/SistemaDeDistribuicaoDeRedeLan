public class Aparelho {
    private int id;
    private int router;
    private Rede rede;
    private String nome;

    public Aparelho(int id, int router, Rede rede, String nome) {
        this.id = id;
        this.router = router;
        this.rede = rede;
        this.nome = nome;
    }

    // Método para enviar mensagem
    public void enviarMensagem(int destino, String mensagem) {
        System.out.println("\n📤 " + nome + " (" + id + ") está enviando: \"" + mensagem + "\"");
        rede.enviarMensagem(id, destino, mensagem);
    }

    // Método para receber mensagem
    public void receberMensagem(int remetente, String mensagem) {
        System.out.println("\n📥 " + nome + " (" + id + ") RECEBEU: \"" + mensagem + 
                         "\" de " + remetente);
    }

    // Getters
    public int getId() { return id; }
    public int getRouter() { return router; }
}