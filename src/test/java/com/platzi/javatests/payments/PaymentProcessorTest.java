package com.platzi.javatests.payments;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PaymentProcessorTest {

    //Declaramos las clases que vamos a usar como globales
    PaymentGateway paymentGateway;
    PaymentProcessor paymentProcessor;

    @Before                 //Marcamos con @Before para que JUnit ejecute antes de cualquier test
    public void setup(){    //Creamos configuracion para los test, en este caso simulamos e instanciamos
        paymentGateway = Mockito.mock(PaymentGateway.class); //Simulamos el PaymentGateway con mockito
        paymentProcessor = new PaymentProcessor(paymentGateway); //Le pasamos el paymentGateway al procesador como parametro
    }

    @Test
    public void payment_is_correct() { //Simulacion de pago correcto
        Mockito.when(paymentGateway.requestPayment(Mockito.any()))
                .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.OK)); //Realizamos la simulaicon para cualquier entrada que devuelva Ok

        assertTrue(paymentProcessor.makePayment(1000)); //Condicion para simular que devuelva lo programado en este caso true para el pago realizado
    }

    @Test
    public void payment_is_wrong() { //Simulacion de pago incorrecto

        Mockito.when(paymentGateway.requestPayment(Mockito.any()))
                .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.ERROR)); //Realizamos la simulaicon para cualquier entrada que devuelva Error

        assertFalse(paymentProcessor.makePayment(1000)); //Condicion para simular que devuelva lo programado en este caso true para el pago realizado
    }

}