package cl.bice.lab;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import cl.bice.lab.util.HttpCliente;

@SpringBootTest
class MsProxyIndicesEconomicosApplicationTests {

	@Test
	void contextLoads() {
		String url="http://localhost:8091/MsRestProxyIndicesEconomicos/service/v1/findCommodotyCompra";
		String valorRetorno="[{key=oro, name=Precio del Oro, dólares por onza, unit=dolar, date=1582848000, value=1642.7}, {key=cobre, name=Precio del Cobre, dólares por libra, unit=dolar, date=1582848000, value=2.56}, {key=plata, name=Precio de la Plata, dólares por onza, unit=dolar, date=1582848000, value=17.75}]";
		HttpCliente cliente=new HttpCliente();
		Object result=cliente.clienteGet(url);
		//Valida que servicio retorne los valores correspondiente al dia 28-02-2020
		assertEquals(valorRetorno, result.toString());
		
	}

}
