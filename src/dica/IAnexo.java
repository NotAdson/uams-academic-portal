package dica;

/**
 * A interface {@code IAnexo} define o contrato para anexos que podem ser adicionados a uma dica.
 */
public interface IAnexo {
    public String getResumo();
    public String getDetalhado();

    /**
     * Calcula o bônus associado ao anexo. O bônus é um valor numérico que representa a contribuição do anexo para a pontuação da dica.
     *
     * @return O valor do bônus calculado para o anexo.
     */
    public double calcularBonus();
}
