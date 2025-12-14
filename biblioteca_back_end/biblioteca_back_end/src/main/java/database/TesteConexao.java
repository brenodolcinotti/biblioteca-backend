package database;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        System.out.println("🚀 Iniciando teste de conexão...");

        try {
            Connection conn = ConnectionFactory.getConnection();
            System.out.println("✅ Conectado com sucesso ao MySQL!");
            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Erro ao conectar:");
            e.printStackTrace();
        }
    }
}
