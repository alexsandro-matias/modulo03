package br.com.posgraduacao;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

//nome da classe diferente de Liveness para que não haja conflito com a anotação.
@Liveness
public class LivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("Aplicação viva");
    }
}
