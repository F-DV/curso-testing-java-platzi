package com.platzi.javatests.payments;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PaymentProcessorTest {

    @Test
    public void payment_is_correct() { //Simulacion de pago correcto

        PaymentGateway paymentGateway = Mockito.mock(PaymentGateway.class); //Simulamos el PaymentGateway con mockito

        Mockito.when(paymentGateway.requestPayment(Mockito.any()))
                .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.OK)); //Realizamos la simulaicon para cualquier entrada que devuelva Ok

        PaymentProcessor paymentProcessor = new PaymentProcessor(paymentGateway); //Le pasamos el paymentGateway al procesador como parametro

        assertTrue(paymentProcessor.makePayment(1000)); //Condicion para simular que devuelva lo programado en este caso true para el pago realizado
    }

    @Test
    public void payment_is_wrong() { //Simulacion de pago incorrecto

        PaymentGateway paymentGateway = Mockito.mock(PaymentGateway.class); //Simulamos el PaymentGateway con mockito

        Mockito.when(paymentGateway.requestPayment(Mockito.any()))
                .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.ERROR)); //Realizamos la simulaicon para cualquier entrada que devuelva Ok

        PaymentProcessor paymentProcessor = new PaymentProcessor(paymentGateway); //Le pasamos el paymentGateway al procesador como parametro

        assertFalse(paymentProcessor.makePayment(1000)); //Condicion para simular que devuelva lo programado en este caso true para el pago realizado
    }

}